package fr.lip6.move.coloane.core.model.interfaces;

/**
 * Interface for a sticky link
 */
public interface ILink {

	/**
	 * Reconnect the link with a new sticky note or a new element
	 * @param newNote New sticky note
	 * @param newTarget New element
	 */
	void reconnect(IStickyNote newNote, ILinkableElement newTarget);

	/**
	 * @return The element that is linked to the sticky note
	 */
	ILinkableElement getElement();

	/**
	 * @return The sitcky note
	 */
	IStickyNote getNote();
}
