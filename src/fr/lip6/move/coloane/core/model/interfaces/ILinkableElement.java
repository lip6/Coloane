package fr.lip6.move.coloane.core.model.interfaces;

import java.util.List;

/**
 * The element that implement this interface can be linked to a sticky note.
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public interface ILinkableElement {
	/**
	 * Add a sticky link to the element.
	 * @param link The link to add
	 */
	void addLink(ILink link);

	/**
	 * Remove a sticky link from the element
	 * @param link The link to remove 
	 * @return <code>true</code> if the link has been correctly removed
	 */
	boolean removeLink(ILink link);

	/**
	 * @return All element sticky links
	 */
	List<ILink> getLinks();
}
