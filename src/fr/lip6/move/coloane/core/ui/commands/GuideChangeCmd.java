package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRulerProvider;

import org.eclipse.gef.commands.Command;

/**
 * Change the guide associated to an element
 */
public class GuideChangeCmd extends Command {
	/** The moveable element */
	private ILocatedElement locatedElement;

	/** Old and new guides */
	private EditorGuide oldGuide, newGuide;

	/** Old and new align */
	private int oldAlign, newAlign;

	/** Is the guide horizontal ? <code>true</code> if it is */
	private boolean horizontal;

	/**
	 * Constructor
	 * @param locatedElement The element
	 * @param horizontal Is the current guide horizontal ? <true> if it is
	 */
	public GuideChangeCmd(ILocatedElement locatedElement, boolean horizontal) {
		super();
		this.locatedElement = locatedElement;
		this.horizontal = horizontal;
	}

	/**
	 * Switch guide
	 * @param oldGuide The old guide
	 * @param newGuide The new guide
	 * @param newAlignment The new align indicator
	 */
	protected final void changeGuide(EditorGuide oldGuide, EditorGuide newGuide, int newAlignment) {
		if (oldGuide != null && oldGuide != newGuide) {
			oldGuide.detachElement(locatedElement);
		}

		// You need to re-attach the part even if the oldGuide and the newGuide
		// are the same because the alignment could have changed
		if (newGuide != null) {
			newGuide.attachElement(locatedElement, newAlignment);
		}
	}

	/**
	 * Set the new guide
	 * @param guide The new guide
	 * @param alignment The new align indicator
	 */
	public final void setNewGuide(EditorGuide guide, int alignment) {
		newGuide = guide;
		newAlign = alignment;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Cache the old values
		oldGuide = locatedElement.getGuide(EditorRulerProvider.VERTICAL_ORIENTATION);
		if (horizontal) { oldGuide = locatedElement.getGuide(EditorRulerProvider.HORIZONTAL_ORIENTATION); }
		if (oldGuide != null) {	oldAlign = oldGuide.getAlignment(locatedElement); }
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		changeGuide(oldGuide, newGuide, newAlign);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		changeGuide(newGuide, oldGuide, oldAlign);
	}
}
