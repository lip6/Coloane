package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

/**
 * Commande pour deplacer un ILocatedElement
 */
public class LocatedElementSetConstraintCmd extends Command {

	/** Enregistre la nouvelle taille et le nouvel endroit */
	private final Point newLocation;
	/** Enregistre l'ancienne taille et le nouvel endroit */
	private Point oldLocation;
	private Dimension delta;

	/** Noeud a manipuler */
	private final ILocatedElement locElement;

	/** Arcs sur lesquels ont doit déplacer les points d'inflexion */
	private final List<IArc> arcsForPI = new ArrayList<IArc>();

	/** Arcs sur lesquels ont doit déplacer les attributs */
	private final List<IAttribute> arcsForAttr = new ArrayList<IAttribute>();
	private final Map<IArc, Dimension> arcsMiddleDelta = new HashMap<IArc, Dimension>();

	/** Attributs devant être déplacé */
	private final List<IAttribute> attributes = new ArrayList<IAttribute>();

	/**
	 * Constructeur
	 * @param locElement élément à déplacer
	 * @param newBounds Nouvelles limites
	 */
	public LocatedElementSetConstraintCmd(ILocatedElement locElement, Rectangle newBounds) {
		super(Messages.NodeSetConstraintCmd_0);
		if (locElement == null || newBounds == null) {
			throw new NullPointerException();
		}
		this.locElement = locElement;
		this.newLocation = newBounds.getLocation().getCopy();
		this.newLocation.x = Math.max(this.newLocation.x, 0);
		this.newLocation.y = Math.max(this.newLocation.y, 0);

		ColoaneEditor ce = (ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		GraphicalViewer viewer = (GraphicalViewer) ce.getAdapter(GraphicalViewer.class);

		// Liste des objet sélectionner pour déterminer comment déplacer les attributs des arcs et des noeuds ainsi que les PI
		StructuredSelection s = (StructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getSelectionProvider().getSelection();
		List< ? > selection = s.toList();

		if (locElement instanceof INode) {
			INode node = (INode) locElement;

			// Création de la liste d'arcs devant être déplacé (les points d'inflexions)
			for (IArc in : node.getIncomingArcs()) {
				INode src = in.getSource();
				if (selection.contains(viewer.getEditPartRegistry().get(src))) {
					arcsForPI.add(in);
				}

				// Ajout des attributs non sélectionné des arcs du noeuds
				for (IAttribute arcAttr : in.getAttributes()) {
					if (!selection.contains(viewer.getEditPartRegistry().get(arcAttr))) {
						arcsForAttr.add(arcAttr);
					}
				}
			}
			// Ajout des attributs non sélectionné des arcs du noeuds
			for (IArc out : node.getOutcomingArcs()) {
				for (IAttribute arcAttr : out.getAttributes()) {
					if (!selection.contains(viewer.getEditPartRegistry().get(arcAttr))) {
						arcsForAttr.add(arcAttr);
					}
				}
			}
		}

		// Création de la liste des attributs devant être déplacé
		if (locElement instanceof IElement) {
			IElement element = (IElement) locElement;

			for (IAttribute attr : element.getDrawableAttributes()) {
				if (!selection.contains(viewer.getEditPartRegistry().get(attr))) {
					attributes.add(attr);
				}
			}
		}
	}

	/**
	 * On peut toujours deplacer un noeud.
	 * Le redimensionnement est bloque automatiquement par les EditPolicy
	 * @return true
	 */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldLocation = new Point(locElement.getLocationInfo().getLocation());
		delta = newLocation.getDifference(oldLocation);

		// Mise à jour des milieux des arcs dont des attributs doivent être déplacés
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				arc.getGraphicInfo().updateMiddlePoint();
			}
		}

		// Déplacement de l'élément ainsi que les PI des arcs attachés dans le cas d'un noeud et certaines condition
		locElement.getLocationInfo().setLocation(newLocation);
		for (IArc arc : arcsForPI) {
			arc.modifyInflexPoints(delta.width, delta.height);
		}

		// Calcul du delta de déplacement à appliquer aux attributs des arcs
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				Point oldMiddle = arc.getGraphicInfo().getMiddlePoint();
				Point newMiddle = arc.getGraphicInfo().findMiddlePoint();
				Dimension middleDelta = newMiddle.getDifference(oldMiddle);
				arcsMiddleDelta.put(arc, middleDelta);
			}
		}

		// Déplacement des attributs des arcs attachés au noeud
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				Dimension middleDelta = arcsMiddleDelta.get(arc);
				if (middleDelta != null) {
					arcAttr.getGraphicInfo().setLocation(arcAttr.getGraphicInfo().getLocation().getTranslated(middleDelta));
				}
			}
		}

		// Déplacement des attributs attachés à cet élément (locElement)
		for (IAttribute attr : attributes) {
			attr.getGraphicInfo().setLocation(attr.getGraphicInfo().getLocation().getTranslated(delta));
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		locElement.getLocationInfo().setLocation(newLocation);
		for (IArc arc : arcsForPI) {
			arc.modifyInflexPoints(delta.width, delta.height);
		}
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				Dimension middleDelta = arcsMiddleDelta.get(arc);
				if (middleDelta != null) {
					arcAttr.getGraphicInfo().setLocation(arcAttr.getGraphicInfo().getLocation().getTranslated(middleDelta));
				}
			}
		}
		for (IAttribute attr : attributes) {
			attr.getGraphicInfo().setLocation(attr.getGraphicInfo().getLocation().getTranslated(delta));
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		locElement.getLocationInfo().setLocation(oldLocation);
		for (IArc arc : arcsForPI) {
			arc.modifyInflexPoints(-delta.width, -delta.height);
		}
		for (IAttribute arcAttr : arcsForAttr) {
			if (arcAttr.getReference() instanceof IArc) {
				IArc arc = (IArc) arcAttr.getReference();
				Dimension middleDelta = arcsMiddleDelta.get(arc);
				if (middleDelta != null) {
					arcAttr.getGraphicInfo().setLocation(arcAttr.getGraphicInfo().getLocation().getTranslated(middleDelta.getNegated()));
				}
			}
		}
		for (IAttribute attr : attributes) {
			attr.getGraphicInfo().setLocation(attr.getGraphicInfo().getLocation().getTranslated(delta.getNegated()));
		}
	}

}
