package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Commande pour ajouter une nouvelle note
 */
public class StickyNoteCreateCommand extends Command {

	/** Nouveau noeud */
	private IStickyNote note;

	/** Graphe */
	private final GraphModel graph;

	private Point location;
	private Dimension dimension;

	/**
	 * Creer une commande qui ajoutera la note au graphe
	 *
	 * @param note Le nouveau noeud à ajouter
	 * @param b Les limites du noeud; (la taille peut être (-1, -1))
	 */
	public StickyNoteCreateCommand(IGraph graph, Rectangle b) {
		super(Messages.StickyNoteCreateCommand_0);
		this.graph = (GraphModel) graph;
		this.location = b.getLocation();
		this.dimension = b.getSize();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public final boolean canExecute() {
		return graph != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		note = graph.createStickyNote();
		note.setLocation(location);
		note.setSize(this.dimension);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		graph.addSticky(note);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		graph.deleteSticky(note);
	}
}
