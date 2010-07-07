package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;

import org.eclipse.gef.commands.Command;

/**
 * Begin the creation of a link (virtual link between an object and a note)
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class LinkCreateCommand extends Command {
	/** The object which is linked to a note */
	private ILinkableElement source;

	/**
	 * Constructor
	 * @param source Source link
	 */
	public LinkCreateCommand(ILinkableElement source) {
		this.source = source;
	}

	/**
	 * @return The source link
	 */
	public final ILinkableElement getSource() {
		return source;
	}
}
