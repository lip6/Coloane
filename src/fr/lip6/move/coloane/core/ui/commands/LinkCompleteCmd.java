package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.LinkModel;
import fr.lip6.move.coloane.core.model.StickyNoteModel;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Create (finalize) the creation of a link between a model element ({@link ILinkableElement}) and a sticky note ({@link StickyNoteModel})
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class LinkCompleteCmd extends Command {

	/** The sticky note */
	private IStickyNote note = null;
	/** The model element linked to the sticky note */
	private ILinkableElement element;

	/** The link */
	private ILink link;
	
	/**
	 * Constructor
	 * @param graph The current graph
	 * @param source The source of the link
	 * @param target The target of the link
	 * @throws IllegalArgumentException If no element is a note ({@link IStickyNote})
	 */
	public LinkCompleteCmd(IGraph graph, ILinkableElement source, ILinkableElement target) throws IllegalArgumentException {
		// The note is always the source of the sticky link
		if (source instanceof IStickyNote) {
			this.note = (IStickyNote) source;
			this.element = target;
		} else if (target instanceof IStickyNote) {
			this.note = (IStickyNote) target;
			this.element = source;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		// @see Constructor... if no note has been identified, the source or the target is not the good type
		if (this.note == null) {
			return false;
		}
		return true;
	};

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.link = new LinkModel(note, element);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.link.getElement().addLink(link);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.link.getElement().removeLink(link);
	}
}
