package fr.lip6.move.coloane.core.ui.actions;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import fr.lip6.move.coloane.core.ui.commands.NodeSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.editpart.ElementEditPart;
import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

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
	protected void init() {
		super.init();
		setEnabled(false);
	}

	@Override
	protected boolean calculateEnabled() {
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof ElementEditPart) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void run() {
		CompoundCommand cc = new CompoundCommand();
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof ElementEditPart) {
				INodeImpl node = (INodeImpl) ((EditPart) obj).getModel();
				INodeGraphicInfo graphicInfo = node.getGraphicInfo();
				cc.add(new NodeSetConstraintCmd(node, new Rectangle(
						graphicInfo.getLocation().x + dx,
						graphicInfo.getLocation().y + dy,
						graphicInfo.getWidth(),
						graphicInfo.getHeight())));
			}
		}
		execute(cc);
	}
}
