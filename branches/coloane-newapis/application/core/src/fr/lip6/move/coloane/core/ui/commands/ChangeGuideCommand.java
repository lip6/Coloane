package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;

import org.eclipse.gef.commands.Command;

/**
 * Commande dédiée au changement de guide pour un élément
 */
public class ChangeGuideCommand extends Command {
	/** L'élément concerné */
	private ILocatedElement locatedElement;

	/** Ancien et nouveau guide */
	private EditorGuide oldGuide, newGuide;

	/** Ancien et nouvel alignement */
	private int oldAlign, newAlign;

	/** Configration du guide qui doit être changé : <code>true</code> pour un guide horizontal */
	private boolean horizontal;

	/**
	 * Constructeur
	 * @param locatedElement L'élément de modèle concerné par ce changement
	 * @param horizontal Indicateur de configuration du guide : <code>true</code> pour un guide horizontal
	 */
	public ChangeGuideCommand(ILocatedElement locatedElement, boolean horizontal) {
		super();
		this.locatedElement = locatedElement;
		this.horizontal = horizontal;
	}

	/**
	 * Change un ancien guide pour un nouveau
	 * @param oldGuide L'ancien guide
	 * @param newGuide Le nouveau guide
	 * @param newAlignment Le nouvel alignement à prendre en compte
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
	 * Positionne le nouveau guide
	 * @param guide Le nouveau guide
	 * @param alignment Le nouvel alignement à prendre en compte
	 */
	public final void setNewGuide(EditorGuide guide, int alignment) {
		newGuide = guide;
		newAlign = alignment;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Cache the old values
		oldGuide = locatedElement.getVerticalGuide();
		if (horizontal) { oldGuide = locatedElement.getHorizontalGuide(); }
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
