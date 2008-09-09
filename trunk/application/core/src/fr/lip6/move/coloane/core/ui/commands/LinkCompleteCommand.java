package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Commande qui va finaliser la création du lien
 */
public class LinkCompleteCommand extends Command {

	private ICoreGraph graph;
	private ILinkableElement source;
	private ILinkableElement target;

	private ILink link;

	/**
	 * @param graph graphe
	 * @param source source du lien
	 * @param target cible du lien
	 */
	public LinkCompleteCommand(IGraph graph, ILinkableElement source, ILinkableElement target) {
		this.graph = (ICoreGraph) graph;
		this.source = source;
		this.target = target;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		link = graph.createLink(source, target);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.addLink(link);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.deleteLink(link);
	}
}
