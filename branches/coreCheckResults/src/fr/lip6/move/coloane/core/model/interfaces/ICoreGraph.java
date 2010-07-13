package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.List;

/**
 * Private graph interface dedicated to core use
 * 
 * @author Jean-Baptiste Voron
 */
public interface ICoreGraph extends IGraph {
	/** Event raised when a sticky is added to the graph */
	String STICKY_ADD_PROP = "Model.AddSticky"; //$NON-NLS-1$

	/** .Event raised when a sticky note is removed from the graph */
	String STICKY_REMOVED_PROP = "Model.RemovingSticky"; //$NON-NLS-1$

	/** Event raised when a sticky link is added to the graph */
	String LINK_ADD_PROP = "Model.AddLink"; //$NON-NLS-1$

	/** Event raised when a sticky link is removed from the graph */
	String LINK_REMOVED_PROP = "Model.RemovingLink"; //$NON-NLS-1$

	/**
	 * Add an existing sticky note to the graph
	 * @param sticky The sticky note to add to the graph
	 */
	void addSticky(IStickyNote sticky);

	/**
	 * Create a sticky note and add it to the graph
	 * @return The created sticky note
	 */
	IStickyNote createStickyNote();

	/**
	 * Remove the sticky note from the graph
	 * @param note The sticky note to remove
	 * @return <code>false</code> if the designed note does not exist
	 */
	boolean deleteSticky(IStickyNote note);

	/**
	 * @return All graph sticky notes
	 */
	List<IStickyNote> getStickyNotes();

	/**
	 * Add an existing link to the graph 
	 * @param link The sticky link to add to the graph
	 */
	void addLink(ILink link);

	/**
	 * Create a sticky link and add it to the graph
	 * @param note The sticky note
	 * @param element The element linked to the sticky note
	 * @return The sticky link
	 */
	ILink createLink(IStickyNote note, ILinkableElement element);

	/**
	 * Remove a sticky link from  the graph
	 * @param link The sticky link to remove from the graph
	 * @return <code>true</code> if everything went fine
	 */
	boolean deleteLink(ILink link);

	/**
	 * @return All graph sticky links
	 */
	List<ILink> getLinks();
}
