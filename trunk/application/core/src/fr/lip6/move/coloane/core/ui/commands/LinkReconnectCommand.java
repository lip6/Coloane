package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;

import org.eclipse.gef.commands.Command;

/**
 * Commande pour la reconnexion d'un lien.
 */
public class LinkReconnectCommand extends Command {

	private ILink link;

	private IStickyNote newNote;
	private ILinkableElement newElement;

	private IStickyNote oldNote;
	private ILinkableElement oldElement;

	/**
	 * @param link Lien à reconnecter
	 * @param newNote nouvelle source
	 * @param newElement nouvelle cible
	 */
	public LinkReconnectCommand(ILink link, IStickyNote newNote, ILinkableElement newElement) {
		this.link = link;
		this.newNote = newNote;
		this.newElement = newElement;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldNote = link.getNote();
		oldElement = link.getElement();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		link.reconnect(newNote, newElement);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		link.reconnect(oldNote, oldElement);
	}

}
