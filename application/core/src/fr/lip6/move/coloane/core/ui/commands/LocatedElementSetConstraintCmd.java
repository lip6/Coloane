package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

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

		StructuredSelection s = (StructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getSelectionProvider().getSelection();
		List< ? > selection = s.toList();

		// Création de la liste d'arcs devant être déplacé (les points d'inflexions)
		if (locElement instanceof INode) {
			INode node = (INode) locElement;

			for (IArc in : node.getIncomingArcs()) {
				INode src = in.getSource();
				if (selection.contains(viewer.getEditPartRegistry().get(src))) {
					arcsForPI.add(in);
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

		// Cas un peu particulier pour le déplacement des attributs d'arc
		if (locElement instanceof IAttribute) {
			IAttribute attr = (IAttribute) locElement;
			if (attr.getReference() instanceof IArc) {
				IArc arc = (IArc) attr.getReference();
				if (selection.contains(viewer.getEditPartRegistry().get(arc.getSource()))
						&& selection.contains(viewer.getEditPartRegistry().get(arc.getTarget()))) {
					delta = new Dimension();
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
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		locElement.getLocationInfo().setLocation(newLocation);
		for (IArc arc : arcsForPI) {
			arc.modifyInflexPoints(delta.width, delta.height);
			arc.updateAttributesPosition();
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
			arc.updateAttributesPosition();
		}
		for (IAttribute attr : attributes) {
			attr.getGraphicInfo().setLocation(attr.getGraphicInfo().getLocation().getTranslated(delta.getNegated()));
		}
	}

}
