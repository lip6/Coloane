package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Delete a link (between a node {@link ILinkableElement} and a note {@link IStickyNote})
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins 
 */
public class LinkDeleteCommand extends Command {
	/** The graph */
	private ICoreGraph graph;
	/** The link to delete */
	private ILink link;

	/**
	 * @param graph The current graph
	 * @param link The link to delete
	 */
	public LinkDeleteCommand(IGraph graph, ILink link) {
		this.graph = (ICoreGraph) graph;
		this.link = link;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.deleteLink(link);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.addLink(link);
	}
}
