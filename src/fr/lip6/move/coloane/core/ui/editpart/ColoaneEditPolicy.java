package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.StickyNoteModel;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.commands.GuideChangeCmd;
import fr.lip6.move.coloane.core.ui.commands.LocatedElementSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.StickyNoteCreateCmd;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
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
 * Set of rules that define the graphical behavior of the model
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */

public class ColoaneEditPolicy extends XYLayoutEditPolicy {

	/**
	 * Constructor
	 * @param layout Layout used by the editor in order to display the model
	 */
	public ColoaneEditPolicy(XYLayout layout) {
		super();
		setXyLayout(layout);
	}

	/**
	 * Model children policy
	 * @param child The child editpart
	 * @return a {@link NonResizableEditPolicy} except in case of sticky notes 
	 */
	@Override
	protected final EditPolicy createChildEditPolicy(EditPart child) {

		// In case of sticky note, the child is resizable...
		if (child instanceof StickyEditPart) {
			return new ResizableEditPolicy();
		}

		// In all other cases, children cannot be resized.
		return new NonResizableEditPolicy() {
			@Override
			protected List<Object> createSelectionHandles() {
				return new ArrayList<Object>(); // Must return a empty array list (and not null)
			}
		};
	}

	/**
	 * Handle a creation request
	 * @param request The request of node/note creation
	 * @return A command to create the node/note
	 */
	@Override
	protected final Command getCreateCommand(CreateRequest request) {
		Object childClass = request.getNewObjectType();

		// If the object is a node
		if (childClass == INode.class) {
			IGraph graph = (IGraph) getHost().getModel();
			INodeFormalism nodeFormalism = (INodeFormalism) request.getNewObject();
			return new NodeCreateCmd(graph, nodeFormalism, ((Rectangle) getConstraintFor(request)).getLocation());
		}

		// If the object is a note
		if (childClass == StickyNoteModel.class) {
			IGraph graph = (IGraph) getHost().getModel();
			return new StickyNoteCreateCmd(graph, (Rectangle) getConstraintFor(request));
		}

		// Otherwise, there is a problem
		return null;
	}

	/**
	 * Handle a node move<br>
	 * @param request The move request 
	 * @param child The edit part concerned by the move
	 * @param constraint The new position (it's a rectangle)
	 * @return A command that specifies the move (to be executed later)
	 */
	@Override
	protected final Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		Command result = null;

		// Considering move commands
		if (request.getType().equals(REQ_MOVE_CHILDREN) && (constraint instanceof Rectangle)) {
			Point newLocation = ((Rectangle) constraint).getLocation();

			// For a sticky note
			if (child instanceof StickyEditPart) {
				//result = new StickyNoteSetConstraintCmd((IStickyNote) child.getModel(), newLocation);
			// For another LocatedElement
			} else if (child.getModel() instanceof ILocatedElement) {
				result = new LocatedElementSetConstraintCmd((ILocatedElement) child.getModel(), newLocation);
			}
		}

		// Detecting when an object is attached to a guide (horizontal / vertical)
		if (child.getModel() instanceof ILocatedElement && (request.getType().equals(REQ_MOVE_CHILDREN) || request.getType().equals(REQ_ALIGN_CHILDREN))) {
			ILocatedElement locatedElement = (ILocatedElement) child.getModel();
			
			// If the object is attached to a guide
			result = chainGuideAttachmentCommand(request, locatedElement, result, true);
			result = chainGuideAttachmentCommand(request, locatedElement, result, false);
			// If the object is detach from a guide
			result = chainGuideDetachmentCommand(request, locatedElement, result, true);
			result = chainGuideDetachmentCommand(request, locatedElement, result, false);
		} 
		return result;
	}

	/** {@inheritDoc} */
	@Override
	protected final Command createChangeConstraintCommand(EditPart editPart, Object obj) {
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
		
		// By default, try with the vertical guide
		String keyGuide = SnapToGuides.KEY_VERTICAL_GUIDE;
		String keyAnchor = SnapToGuides.KEY_VERTICAL_ANCHOR;
		// Unless it's clearly specified to try with horizontal guide 
		if (horizontal) {
			keyGuide = SnapToGuides.KEY_HORIZONTAL_GUIDE;
			keyAnchor = SnapToGuides.KEY_HORIZONTAL_ANCHOR;
		}

		// Find a guide in extended data (from the request)
		Integer guidePos = (Integer) request.getExtendedData().get(keyGuide);

		// If a guide is existing
		if (guidePos != null) {
			int alignment = ((Integer) request.getExtendedData().get(keyAnchor)).intValue();

			// Declare a new guide for this object (or replace the old one)
			GuideChangeCmd changeGuideCommand = new GuideChangeCmd(locatedElement, horizontal);
			changeGuideCommand.setNewGuide(findGuideAt(guidePos.intValue(), horizontal), alignment);
			result = result.chain(changeGuideCommand); // Chain the two commands (move + attach)
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
			result = result.chain(new GuideChangeCmd(locatedElement, horizontal));
		}

		return result;
	}

	/**
	 * Find the correct guide given a position and an orientation
	 * @param pos The position
	 * @param horizontal Is the guide horizontal ? 
	 * @return The guide
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
