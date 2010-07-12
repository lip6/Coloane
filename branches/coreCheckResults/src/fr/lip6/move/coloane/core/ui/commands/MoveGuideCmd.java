package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

/**
 * Move a guide with all attached nodes
 */
public class MoveGuideCmd extends Command {

	/** The guide that is moved */
	private EditorGuide guide;

	/** Guide move according its axis */
	private int delta;
	
	/** Objects that are sticked to the guide have to be moved too */
	private CompoundCommand moveElementsCommand;
	
	/**
	 * Constructor
	 * @param guide The guide that is moved
	 * @param delta The move
	 */
	public MoveGuideCmd(EditorGuide guide, int delta) {
		super(Messages.MoveGuideCommand_0);
		this.guide = guide;
		this.delta = delta;
		
		// Must move all elements... Create commands for all attached nodes
		moveElementsCommand = new CompoundCommand();
		for (ILocatedElement attachedElement : guide.getModelObjects()) {
			Point location = attachedElement.getLocationInfo().getLocation().getCopy();
			if (guide.isHorizontal()) {
				// Move along the horizontal axis
				location.y += delta;
			} else {
				// Move along the vertical axis
				location.x += delta;
			}
			// Add the element move command to the compound command
			moveElementsCommand.add(new LocatedElementSetConstraintCmd(attachedElement, location));
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		guide.setPosition(guide.getPosition() + delta);
		moveElementsCommand.execute();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		guide.setPosition(guide.getPosition() - delta);
		moveElementsCommand.undo();
	}
}
