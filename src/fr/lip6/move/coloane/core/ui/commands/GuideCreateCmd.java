package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRuler;
import org.eclipse.gef.commands.Command;

/**
 * Create a guide
 */
public class GuideCreateCmd extends Command {
	/** The new guide */
	private EditorGuide guide;

	/** The rule where is attached the new guide */
	private EditorRuler ruler;

	/** The guide position */
	private int position;

	/**
	 * Constructor
	 * @param ruler The ruler where the guide is attached to
	 * @param position The guide position
	 */
	public GuideCreateCmd(EditorRuler ruler, int position) {
		super(Messages.CreateGuideCommand_0);
		this.ruler = ruler;
		this.position = position;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if (guide == null) {
			guide = new EditorGuide(ruler.getGuidesOrientation());
		}
		guide.setPosition(position);
		ruler.addGuide(guide);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		ruler.removeGuide(guide);
	}
}
