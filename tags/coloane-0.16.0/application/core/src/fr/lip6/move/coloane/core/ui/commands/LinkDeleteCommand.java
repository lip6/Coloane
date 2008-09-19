package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Commande pour la suppression d'un lien
 */
public class LinkDeleteCommand extends Command {
	private ICoreGraph graph;
	private ILink link;

	/**
	 * @param graph graphe
	 * @param link lien à supprimer
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
