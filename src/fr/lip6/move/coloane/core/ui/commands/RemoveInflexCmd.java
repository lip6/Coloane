package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.gef.commands.Command;

/**
 * Command that remove all bendpoints from the considered arc
 */
public class RemoveInflexCmd extends Command {

	/** Arc on wich the action is done */
	private final IArc arc;

	/** The list of bendpoints attached to this arc (required in cas of UNDO) */
	private final List<AbsoluteBendpoint> previousList = new ArrayList<AbsoluteBendpoint>();

	/**
	 * Constructor
	 * @param arc Arc on wich the action is done
	 */
	public RemoveInflexCmd(IArc arc) {
		super(Messages.ArcChangeCurve_0);
		this.arc = (IArc) arc;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.previousList.clear();
		this.previousList.addAll(arc.getInflexPoints());
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		arc.removeAllInflexPoints();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		for (AbsoluteBendpoint p : this.previousList) {
			arc.addInflexPoint(p);
		}
	}
}
