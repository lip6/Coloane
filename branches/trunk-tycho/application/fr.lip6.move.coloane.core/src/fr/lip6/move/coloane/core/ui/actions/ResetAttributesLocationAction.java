package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.commands.AttributeSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.editpart.ArcEditPart;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action that reset all attributes location of the current element.<br>
 * At least one arc or node must be selected.
 *
 * @author Jean-Baptiste Voron
 */
public class ResetAttributesLocationAction extends SelectionAction {

	/** Action ID */
	public static final String ID = "object.resetattributeslocation"; //$NON-NLS-1$

	/**
	 * Constructor
	 * @param part The workbench where come from the action
	 */
	public ResetAttributesLocationAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void init() {
		setId(ID);
		setText("Reset Attributes Location"); //$NON-NLS-1$
		setToolTipText("Reset all the attributes location"); //$NON-NLS-1$

		// Image associated to the action
        ImageDescriptor icon = ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/reset_position.png"); //$NON-NLS-1$
        if (icon != null) { setImageDescriptor(icon); }
		setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean calculateEnabled() {
		List<IAttribute> selectedNodes = this.getSelectionRelatedAttributes();

		// Must check whether nodes are selected or not
		if (selectedNodes == null) { return false; }

		return (this.getSelectionRelatedAttributes().size() > 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void run() {
		CompoundCommand commandsGroup = new CompoundCommand();
		List<IAttribute> attributesToReset = this.getSelectionRelatedAttributes();

		// Check whether the current selection is empty or not
		if (attributesToReset == null) { return; }

		
		for (IAttribute attribute : attributesToReset) {
			commandsGroup.add(new AttributeSetConstraintCmd(attribute, new Point(-1,-1)));
		}
		execute(commandsGroup);
	}

	/**
	 * Get arcs from the current selection.
	 * @return A list of {@link IArc} or <code>null</code> if the selection is empty
	 */
	@SuppressWarnings("unchecked")
	private List<IAttribute> getSelectionRelatedAttributes() {
		List<Object> objects = getSelectedObjects();
		List<IAttribute> attributesToReset = new ArrayList<IAttribute>();

		// If the current selection is empty... return !
		if (objects.isEmpty()) {
			return null;
		}

		// All selected objects must be arcs
		for (Object object : objects) {
			if (object instanceof ArcEditPart) {
				IArc arc = (IArc) (((ArcEditPart) object).getModel());
				attributesToReset.addAll(arc.getDrawableAttributes());
			} else if (object instanceof NodeEditPart) {
				INode node = (INode) (((NodeEditPart) object).getModel());
				attributesToReset.addAll(node.getDrawableAttributes());
			}
		}
		return attributesToReset;
	}
}
