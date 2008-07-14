package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.ui.commands.NodeSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * TODO : A documenter
 * @author jbvoron
 *
 */
public class NodeMoveAction extends SelectionAction {
	/** Déplacement vers le haut */
	public static final String UP = "move.up"; //$NON-NLS-1$

	/** Déplacement vers le bas */
	public static final String DOWN = "move.down"; //$NON-NLS-1$

	/** Déplacement vers la gauche */
	public static final String LEFT = "move.left"; //$NON-NLS-1$

	/** Déplacement vers la droite */
	public static final String RIGHT = "move.right"; //$NON-NLS-1$

	private int dx = 0;
	private int dy = 0;

	/**
	 * @param part
	 * @param action voir les constantes défini dans NodeMoveAction
	 */
	public NodeMoveAction(IWorkbenchPart part, String action) {
		super(part);
		setId(action);
		if (UP.equals(action)) {
			dy = -1;
		} else if (DOWN.equals(action)) {
			dy = 1;
		} else if (LEFT.equals(action)) {
			dx = -1;
		} else if (RIGHT.equals(action)) {
			dx = 1;
		}
	}

	@Override
	protected final void init() {
		super.init();
		setEnabled(false);
	}

	@Override
	protected final boolean calculateEnabled() {
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof NodeEditPart) {
				return true;
			}
		}
		return false;
	}

	@Override
	public final void run() {
		CompoundCommand cc = new CompoundCommand();
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof NodeEditPart) {
				INode node = (INode) ((EditPart) obj).getModel();
				ILocationInfo graphicInfo = node.getGraphicInfo();
				cc.add(new NodeSetConstraintCmd(node, new Rectangle(
						graphicInfo.getLocation().x + dx,
						graphicInfo.getLocation().y + dy,
						graphicInfo.getSize().width,
						graphicInfo.getSize().height)));
			}
		}
		execute(cc);
	}
}
