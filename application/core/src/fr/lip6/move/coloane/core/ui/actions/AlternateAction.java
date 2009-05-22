package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.commands.AlternateFigureCmd;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action that changes the status of the figure of a formalism element (replaced by its alternate figure).<br>
 *
 * @author Jean-Baptiste Voron
 */
public class AlternateAction extends SelectionAction {

	/** Action ID */
	public static final String ID = "node.rotate"; //$NON-NLS-1$

	/**
	 * Constructor
	 * @param part The workbench where come from the action
	 */
	public AlternateAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void init() {
		setId(ID);
		setText("Rotate"); //$NON-NLS-1$
		setToolTipText("Rotate Node"); //$NON-NLS-1$

		// Image associated to the action
        ImageDescriptor icon = ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/rotate.png"); //$NON-NLS-1$
        if (icon != null) { setImageDescriptor(icon); }
		setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean calculateEnabled() {
		return (getSelectedNode().size() > 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void run() {
		CompoundCommand commandsGroup = new CompoundCommand();
		for (INode node : getSelectedNode()) {
			commandsGroup.add(new AlternateFigureCmd(node));
		}
		execute(commandsGroup);
	}

	/**
	 * Get arcs from the current selection.
	 * @return A list of {@link IArc}
	 */
	@SuppressWarnings("unchecked")
	private List<INode> getSelectedNode() {
		List<INode> selectedRotatableNodes = new ArrayList<INode>();
		List<Object> objects = getSelectedObjects();

		// If the current selection is empty... return !
		if (objects.isEmpty()) {
			return null;
		}

		// All selected objects must be arcs
		for (Object object : objects) {
			if (!(object instanceof NodeEditPart)) {
				continue;
			}
			NodeEditPart part = (NodeEditPart) object;
			selectedRotatableNodes.add((INode) part.getModel());
		}
		return selectedRotatableNodes;
	}
}
