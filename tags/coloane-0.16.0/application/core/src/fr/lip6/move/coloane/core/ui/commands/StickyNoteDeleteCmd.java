package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'une note du modele
 */
public class StickyNoteDeleteCmd extends Command {

	/** Noeud a retirer */
	private final IStickyNote stickyNote;

	/** Graphe contenant le noeud */
	private final ICoreGraph graph;

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
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.deleteSticky(stickyNote);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.addSticky(stickyNote);
	}
}
