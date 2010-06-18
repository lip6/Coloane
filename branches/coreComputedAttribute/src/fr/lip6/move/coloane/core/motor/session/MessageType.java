package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * Énumération de type sûrs définissant tous les types de message qu'on pourra
 * afficher dans la console.
 *
 * @see Session
 */
public final class MessageType {
	/** Liste des constantes */
	public static final MessageType FINE = new MessageType("fine", true, ColorConstants.lightGray, SWT.NORMAL); //$NON-NLS-1$
	public static final MessageType DEBUG = new MessageType("debug", true, ColorConstants.blue, SWT.NORMAL); //$NON-NLS-1$
	public static final MessageType INFO = new MessageType("info", true, ColorConstants.black, SWT.BOLD); //$NON-NLS-1$
	public static final MessageType WARNING = new MessageType("warning", true, new Color(null, 255, 124, 10), SWT.BOLD); //$NON-NLS-1$
	public static final MessageType ERROR = new MessageType("error", true, ColorConstants.red, SWT.BOLD); //$NON-NLS-1$
	/*************************/

	private final String name;
	private boolean activateOnWrite;
	private Color color;
	private int fontStyle;

	/**
	 * @param name nom définissant ce type
	 * @param activateOnWrite si <code>true</code>, la console sera mise en avant
	 * 				plan lors de l'envoi d'un message.
	 * @param color Couleur
	 * @param fontStyle Style SWT pour la police : SWT.NORMAL, SWT.BOLD, SWT.ITALIC
	 */
	private MessageType(String name, boolean activateOnWrite, Color color, int fontStyle) {
		this.name = name;
		this.activateOnWrite = activateOnWrite;
		this.color = color;
		this.fontStyle = fontStyle;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Cette méthode applique les propriétés de formattage définies par ce type.
	 * @param mcs objet qui doit être modifié.
	 */
	public void applyType(MessageConsoleStream mcs) {
		mcs.setActivateOnWrite(activateOnWrite);
		mcs.setColor(color);
		mcs.setFontStyle(fontStyle);
	}

	/**
	 * @param apiTypeMessage type de message défini dans IReceptMessage.
	 * @return le MessageType associé.
	 */
	public static MessageType getAssociatedType(int apiTypeMessage) {
		switch(apiTypeMessage) {
		case IReceptMessage.ADMINISTRATOR_MESSAGE :
			return INFO;
		case IReceptMessage.COPYRIGHT_MESSAGE :
			return FINE;
		case IReceptMessage.ERROR_MESSAGE :
			return ERROR;
		case IReceptMessage.TRACE_MESSAGE :
			return INFO;
		case IReceptMessage.WARNING_MESSAGE :
			return WARNING;
		default:
			throw new AssertionError("Type inconnu : " + apiTypeMessage); //$NON-NLS-1$
		}
	}
}
