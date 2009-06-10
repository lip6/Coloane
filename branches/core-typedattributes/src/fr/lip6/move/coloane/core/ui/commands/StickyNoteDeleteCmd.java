package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'une note du modele
 */
public class StickyNoteDeleteCmd extends Command {

	/** Noeud a retirer */
	private final IStickyNote stickyNote;

	/** Graphe contenant le noeud */
	private final ICoreGraph graph;

	/** Liens reliés à cette note */
	private List<ILink> links;

	/**
	 * Constructeur
	 * @param graph Le graphe qui contient la note
	 * @param stickyNote La note concernée par la suppression
	 */
	public StickyNoteDeleteCmd(IGraph graph, IStickyNote stickyNote) {
		super(Messages.StickyNoteDeleteCmd_0);
		this.graph = (ICoreGraph) graph;
		this.stickyNote = stickyNote;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		links = new ArrayList<ILink>(stickyNote.getLinks());
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.deleteSticky(stickyNote);
		for (ILink link : links) {
			graph.deleteLink(link);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.addSticky(stickyNote);
		for (ILink link : links) {
			graph.addLink(link);
		}
	}
}
