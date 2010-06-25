package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.commands.LocatedElementSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action qui va déplacer la selection d'un pixel dans un sens.<br>
 * Les sens de déplacements possible sont définies par des constantes dans cette classe.
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
	 * @param part le workBenchPart associé
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

	/** {@inheritDoc} */
	@Override
	protected final void init() {
		super.init();
		setEnabled(false);
	}

	/** {@inheritDoc} */
	@Override
	protected final boolean calculateEnabled() {
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof NodeEditPart) {
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final void run() {
		CompoundCommand cc = new CompoundCommand();
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof EditPart) {
				ILocatedElement node = (ILocatedElement) ((EditPart) obj).getModel();
				Point location = node.getLocationInfo().getLocation();
				cc.add(new LocatedElementSetConstraintCmd((ILocatedElement) node, new Rectangle(
						location.x + dx,
						location.y + dy,
						0,
						0)));
			}
		}
		execute(cc);
	}
}
