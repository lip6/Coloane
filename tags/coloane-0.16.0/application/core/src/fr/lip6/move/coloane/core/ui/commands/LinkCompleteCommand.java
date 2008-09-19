package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Commande qui va finaliser la création du lien
 */
public class LinkCompleteCommand extends Command {

	private ICoreGraph graph;
	private IStickyNote source;
	private ILinkableElement target;

	private ILink link;

	/**
	 * @param graph graphe
	 * @param e1 première élément du lien
	 * @param e2 second élément du lien
	 * @throws IllegalArgumentException si aucun des éléments n'est une note
	 */
	public LinkCompleteCommand(IGraph graph, ILinkableElement e1, ILinkableElement e2) throws IllegalArgumentException {
		if (!(e1 instanceof IStickyNote ^ e2 instanceof IStickyNote)) {
			throw new IllegalArgumentException("e1 xor e2 must be an instance of IStickyNote (e1=" + e1.getClass() + "; e2=" + e2.getClass() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		this.graph = (ICoreGraph) graph;

		// Pour simplifier le modèle, on place la note en source du lien.
		if (e1 instanceof IStickyNote) {
			this.source = (IStickyNote) e1;
			this.target = e2;
		} else {
			this.source = (IStickyNote) e2;
			this.target = e1;
		}
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
