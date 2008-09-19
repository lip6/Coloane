package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;

import org.eclipse.gef.commands.Command;

/**
 * Commande pour l'initialisation de la création d'un lien.
 */
public class LinkCreateCommand extends Command {
	private ILinkableElement source;

	/**
	 * @param source source du lien
	 */
	public LinkCreateCommand(ILinkableElement source) {
		this.source = source;
	}

	/**
	 * @return la source
	 */
	public final ILinkableElement getSource() {
		return source;
	}
}
