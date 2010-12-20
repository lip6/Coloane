package fr.lip6.move.coloane.core.model.interfaces;

/**
 * Interface for a sticky link
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public interface ILink {

	/**
	 * Connect the sticky link to its note and its element
	 * Basically, this method tells the note and the element to add the link to their links list.
	 */
	void connect();
	
	/**
	 * Disconnect a sticky link from its note and its element.<br>
	 * Basically, this method tells the note and the element to remove the link from their links list.
	 */
	void disconnect();
	
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
