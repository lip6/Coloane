package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un noeud du modele
 */
public class StickyNoteDeleteCmd extends Command {

	/** Noeud a retirer */
	private final IStickyNote stickyNote;

	/** Graphe contenant le noeud */
	private final GraphModel graph;

	/**
	 * Constructeur
	 * @param graph Le graphe qui contient la note
	 * @param stickyNote La note concernée par la suppression
	 */
	public StickyNoteDeleteCmd(IGraph graph, IStickyNote n) {
		this.graph = (GraphModel) graph;
		this.stickyNote = n;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		this.redo(); // Execute
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		graph.deleteSticky(stickyNote);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		graph.addSticky(stickyNote);
	}
}
