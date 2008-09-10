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

	private IStickyNote newSource;
	private ILinkableElement newTarget;

	private IStickyNote oldSource;
	private ILinkableElement oldTarget;

	/**
	 * @param link Lien à reconnecter
	 * @param newSource nouvelle source
	 * @param newTarget nouvelle cible
	 */
	public LinkReconnectCommand(ILink link, IStickyNote newSource, ILinkableElement newTarget) {
		this.link = link;
		this.newSource = newSource;
		this.newTarget = newTarget;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldSource = link.getSource();
		oldTarget = link.getTarget();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		link.reconnect(newSource, newTarget);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		link.reconnect(oldSource, oldTarget);
	}

}
