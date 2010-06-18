package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.StickyNoteModel;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.commands.ChangeGuideCommand;
import fr.lip6.move.coloane.core.ui.commands.LocatedElementSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.StickyNoteCreateCommand;
import fr.lip6.move.coloane.core.ui.commands.StickyNoteSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.rulers.RulerProvider;

/**
 * Ensemble de regles definissant le comportement du modele vis a vis de ses
 * noeuds fils. <i>Attention ! Le modele est l'unique conteneur de tous les
 * autres objets.</i> Les regles definies concernent :
 * <ul>
 * <li>La creation d'un noeud fils</li>
 * <li>Le deplacement d'un noeud fils</li>
 * </ul>
 */

public class ColoaneEditPolicy extends XYLayoutEditPolicy {

	/**
	 * Constructeur
	 * @param layout layout de l'editPart.
	 */
	public ColoaneEditPolicy(XYLayout layout) {
		super();
		setXyLayout(layout);
	}

	/**
	 * Le modele (en tant que conteneur) doit definir une politique vis a vis de ses enfants
	 * @param child editPart de l'enfant
	 * @return NonResizableEditPolicy sauf dans le cas de la note.
	 * @see ColoaneEditPolicy
	 */
	@Override
	protected final EditPolicy createChildEditPolicy(EditPart child) {

		// Dans le cas des notes on ne touche a rien
		if (child instanceof StickyEditPart) {
			return new ResizableEditPolicy();
		}

		/*
		 * Cette politique interdit aux enfants d'etre redimensionnes<br>
		 * La redefinition de la methode interne supprime toute trace de cadre de selection.
		 */
		return new NonResizableEditPolicy() {
			@Override
			protected List<Object> createSelectionHandles() {
				return new ArrayList<Object>(); // Doit retourner une arraylist vide et non null
			}
		};
	}
	
	

	/**
	 * Traitement d'une demande d'ajout de noeud
	 * @param request La requete formulee
	 * @return commande associée à cette requête ou <tt>null</tt>
	 */
	@Override
	protected final Command getCreateCommand(CreateRequest request) {
		Object childClass = request.getNewObjectType();

		// Si l'objet a ajouter est un noeud... OK
		if (childClass == INode.class) {
			IGraph graph = (IGraph) getHost().getModel();
			INodeFormalism nodeFormalism = (INodeFormalism) request.getNewObject();

			// On applique la commande de creation du noeud
			return new NodeCreateCmd(graph, nodeFormalism.getName(), (Rectangle) getConstraintFor(request));
		}

		// Si l'objet a ajouter est une note... OK
		if (childClass == StickyNoteModel.class) {
			IGraph graph = (IGraph) getHost().getModel();

			// On applique la commande de creation du noeud
			return new StickyNoteCreateCommand(graph, (Rectangle) getConstraintFor(request));
		}

		// Sinon... On ne permet pas l'ajout !
		return null;
	}

	/**
	 * Traitement du deplacement d'un noeud<br>
	 * <b>Attention : Le redimensionnement n'est pas permis !</b>
	 * @param request La requete formulée
	 * @param child L'EditPart concernée
	 * @param constraint La nouvelle position demandée
	 * @return commande associée à cette requête ou <tt>null</tt>
	 */
	@Override
	protected final Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		Command result = null;

		// Dans le cas d'une note
		if (child instanceof StickyEditPart && constraint instanceof Rectangle) {
			result = new StickyNoteSetConstraintCmd((IStickyNote) child.getModel(), (Rectangle) constraint);		// Dans le cas d'un ILocatedElement (INode, IAttribute, TipModel)

		// Dans le cas d'un LocatedElement
		} else if (child.getModel() instanceof ILocatedElement && constraint instanceof Rectangle) {
			result = new LocatedElementSetConstraintCmd((ILocatedElement) child.getModel(), (Rectangle) constraint);

		}

		if (child.getModel() instanceof ILocatedElement && (request.getType().equals(REQ_MOVE_CHILDREN) || request.getType().equals(REQ_ALIGN_CHILDREN))) {
			ILocatedElement locatedElement = (ILocatedElement) child.getModel();
			result = chainGuideAttachmentCommand(request, locatedElement, result, true);
			result = chainGuideAttachmentCommand(request, locatedElement, result, false);
			result = chainGuideDetachmentCommand(request, locatedElement, result, true);
			result = chainGuideDetachmentCommand(request, locatedElement, result, false);
		}

		// Dans tous les autres cas, on forwarde au pere
		// return super.createChangeConstraintCommand(request, child, constraint);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	protected final Command createChangeConstraintCommand(EditPart arg0, Object arg1) {
		return null;
	}

	/**
	 * Returns a command that chains a given command to the command which attaches a subpart to the guide.
	 * @param request the attachment request
	 * @param locatedElement the subpart to attach
	 * @param cmd the command to chain
	 * @param horizontal indicates whether the guide is horizontal
	 * @return the command representing the chaining
	 */
	protected final Command chainGuideAttachmentCommand(Request request, ILocatedElement locatedElement, Command cmd, boolean horizontal) {
		Command result = cmd;
		String keyGuide = SnapToGuides.KEY_VERTICAL_GUIDE;
		String keyAnchor = SnapToGuides.KEY_VERTICAL_ANCHOR;
		if (horizontal) {
			keyGuide = SnapToGuides.KEY_HORIZONTAL_GUIDE;
			keyAnchor = SnapToGuides.KEY_HORIZONTAL_ANCHOR;
		}

		// Attach to guide, if one is given
		Integer guidePos = (Integer) request.getExtendedData().get(keyGuide);

		if (guidePos != null) {
			int alignment = ((Integer) request.getExtendedData().get(keyAnchor)).intValue();

			ChangeGuideCommand cgm = new ChangeGuideCommand(locatedElement, horizontal);
			cgm.setNewGuide(findGuideAt(guidePos.intValue(), horizontal), alignment);
			result = result.chain(cgm);
		}

		return result;

	}

	/**
	 * Returns a command that chains a given command to the command which detaches a subpart from the guide.
	 * @param request the detachment request
	 * @param locatedElement the subpart to detach
	 * @param cmd the command to chain
	 * @param horizontal indicates whether the guide is horizontal
	 * @return the command representing the chaining
	 */

	protected final Command chainGuideDetachmentCommand(Request request, ILocatedElement locatedElement, Command cmd, boolean horizontal) {
		Command result = cmd;
		String key = SnapToGuides.KEY_VERTICAL_GUIDE;
		if (horizontal) {
			key = SnapToGuides.KEY_HORIZONTAL_GUIDE;
		}

		// Detach from guide, if none is given
		Integer guidePos = (Integer) request.getExtendedData().get(key);

		if (guidePos == null) {
			result = result.chain(new ChangeGuideCommand(locatedElement, horizontal));
		}

		return result;
	}

	/**
	 * Trouve le guide en considérant une position et une orientation
	 * @param pos La position
	 * @param horizontal L'orientation
	 * @return Le guide trouvé
	 */
	private EditorGuide findGuideAt(int pos, boolean horizontal) {
		String property = RulerProvider.PROPERTY_HORIZONTAL_RULER;
		if (horizontal) {
			property = RulerProvider.PROPERTY_VERTICAL_RULER;
		}
		RulerProvider provider = ((RulerProvider) getHost().getViewer().getProperty(property));
		return (EditorGuide) provider.getGuideAt(pos);
	}
}
