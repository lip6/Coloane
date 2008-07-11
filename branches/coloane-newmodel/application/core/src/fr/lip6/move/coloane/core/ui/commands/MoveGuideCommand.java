package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

public class MoveGuideCommand extends Command {

	/** Déplacement appliqué au guide */
	private int delta;

	/** Guide concerné par la déplacement */
	private EditorGuide guide;

	/**
	 * Constructeur
	 * @param guide le guide concerné par le déplacement
	 * @param delta le déplacement appliqué au guide
	 */
	public MoveGuideCommand(EditorGuide guide, int delta) {
		super("Move a guide");
		this.guide = guide;
		this.delta = delta;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public final void execute() {
		guide.setPosition(guide.getPosition() + delta);
		Iterator<ILocatedElement> iter = guide.getModelObjects().iterator();
		while (iter.hasNext()) {
			ILocatedElement locatedElement = iter.next();
			Point location = locatedElement.getLocationInfo().getLocation().getCopy();
			if (guide.isHorizontal()) {
				location.y += delta;
			} else {
				location.x += delta;
			}
			locatedElement.getLocationInfo().setLocation(location);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public final void undo() {
		guide.setPosition(guide.getPosition() - delta);
		Iterator<ILocatedElement> iter = guide.getModelObjects().iterator();
		while (iter.hasNext()) {
			ILocatedElement locatedElement = iter.next();
			Point location = locatedElement.getLocationInfo().getLocation().getCopy();
			if (guide.isHorizontal()) {
				location.y -= delta;
			} else {
				location.x -= delta;
			}
			locatedElement.getLocationInfo().setLocation(location);
		}
	}
}
