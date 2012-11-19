/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

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
	 * Create a new link between a sticky note and a node.
	 * @param note The sticky note
	 * @param node The graph node
	 * @return The created link, that can also be obtained through the sticky note
	 */
	ILink createLink(IStickyNote note, INode node);
	
	/**
	 * Create a new link between a sticky note and an arc.
	 * @param note The sticky note
	 * @param arc The graph arc
	 * @return The created link, that can also be obtained through the sticky note
	 */
	ILink createLink(IStickyNote note, IArc arc);

	/**
	 * Remove the link between a sticky note and an element
	 * @param link The link to remove
	 */
	void deleteLink(ILink link);
	
}
