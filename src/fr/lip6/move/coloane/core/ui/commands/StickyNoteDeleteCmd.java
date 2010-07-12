package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Delete a sticky note
 */
public class StickyNoteDeleteCmd extends Command {

	/** Note to delete */
	private final IStickyNote note;

	/** The graph that holds the note */
	private final ICoreGraph graph;

	/** Links used by this note */
	private List<ILink> links;

	/**
	 * Constructor
	 * @param graph The graph that holds the note
	 * @param stickyNote The note
	 */
	public StickyNoteDeleteCmd(IGraph graph, IStickyNote stickyNote) {
		super(Messages.StickyNoteDeleteCmd_0);
		this.graph = (ICoreGraph) graph;
		this.note = stickyNote;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		links = new ArrayList<ILink>(note.getLinks());
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.deleteSticky(note);
		for (ILink link : links) {
			graph.deleteLink(link);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.addSticky(note);
		for (ILink link : links) {
			graph.addLink(link);
		}
	}
}
