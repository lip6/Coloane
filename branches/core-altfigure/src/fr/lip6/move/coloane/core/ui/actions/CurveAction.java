package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.commands.ArcChangeCurve;
import fr.lip6.move.coloane.core.ui.editpart.ArcEditPart;
import fr.lip6.move.coloane.interfaces.model.IArc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action that change the curvature statuc of an arc.<br>
 * The arc must be selected.
 *
 * @author Jean-Baptiste Voron
 */
public class CurveAction extends SelectionAction {

	/** Action ID */
	public static final String ID = "arc.curve"; //$NON-NLS-1$

	/**
	 * Constructor
	 * @param part The workbench where come from the action
	 */
	public CurveAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void init() {
		setId(ID);
		setText("Curve / Straight"); //$NON-NLS-1$
		setToolTipText("Curve or Straight arcs"); //$NON-NLS-1$

		// Image associated to the action
        ImageDescriptor icon = ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/curve.png"); //$NON-NLS-1$
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
		for (IArc arc : getSelectedNode()) {
			commandsGroup.add(new ArcChangeCurve(arc));
		}
		execute(commandsGroup);
	}

	/**
	 * Get arcs from the current selection.
	 * @return A list of {@link IArc}
	 */
	@SuppressWarnings("unchecked")
	private List<IArc> getSelectedNode() {
		List<IArc> selectedArcs = new ArrayList<IArc>();
		List<Object> objects = getSelectedObjects();

		// If the current selection is empty... return !
		if (objects.isEmpty()) {
			return null;
		}

		// All selected objects must be arcs
		for (Object object : objects) {
			if (!(object instanceof ArcEditPart)) {
				continue;
			}
			ArcEditPart part = (ArcEditPart) object;
			selectedArcs.add((IArc) part.getModel());
		}
		return selectedArcs;
	}
}
