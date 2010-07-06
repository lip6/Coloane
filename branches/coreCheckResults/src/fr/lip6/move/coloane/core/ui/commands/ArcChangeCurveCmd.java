package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.gef.commands.Command;

/**
 * Command that allow to curve a straight arc and to straight a curved arc.
 * 
 * @author Jean-Baptiste Voron
 */
public class ArcChangeCurveCmd extends Command {

	/** Arc on which the action is done */
	private final IArc arc;

	/**
	 * Constructor
	 * @param arc Arc on which the action is done
	 */
	public ArcChangeCurveCmd(IArc arc) {
		super(Messages.ArcChangeCurve_0);
		this.arc = (IArc) arc;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		arc.getGraphicInfo().setCurve(!arc.getGraphicInfo().getCurve());
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		redo();
	}
}
