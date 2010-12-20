package fr.lip6.move.coloane.interfaces.objects.services;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * Message that will be displayed on a console
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public final class ConsoleMessage {
	
	/** Message Types */
	public static final int SIMPLE_MESSAGE = 0;
	public static final int COPYRIGHT_MESSAGE = 1;
	public static final int ERROR_MESSAGE = 2;
	public static final int DEBUG_MESSAGE = 3;
	public static final int WARNING_MESSAGE = 4;
	
	/** The real message */
	private final String message;

	/**
	 * The type of the message.<br> 
	 * This type will determine the color and the font style used to display the message 
	 */
	private int messageType;

	/**
	 * Constructor
	 * @param message The real message
	 * @param messageType The message type
	 */
	public ConsoleMessage(String message, int messageType) {
		this.message = message;
		this.messageType = messageType;
	}
	
	/**
	 * @return The color of the message according to its type
	 */
	public Color getColor() {
		switch(this.messageType) {
		case SIMPLE_MESSAGE :
			return ColorConstants.black;
		case COPYRIGHT_MESSAGE :
			return ColorConstants.lightGray;
		case ERROR_MESSAGE :
			return ColorConstants.red;
		case DEBUG_MESSAGE :
			return ColorConstants.blue;
		case WARNING_MESSAGE :
			return new Color(null, 255, 124, 10);
		default:
			return ColorConstants.black;
		}
	}
	
	/**
	 * @return The color of the message according to its type
	 */
	public int getFontType() {
		switch(this.messageType) {
		case SIMPLE_MESSAGE :
			return SWT.NORMAL;
		case COPYRIGHT_MESSAGE :
			return SWT.BOLD;
		case ERROR_MESSAGE :
			return SWT.BOLD;
		case DEBUG_MESSAGE :
			return SWT.NORMAL;
		case WARNING_MESSAGE :
			return SWT.BOLD;
		default:
			return SWT.NORMAL;
		}
	}
	
	/**
	 * @return The (real) message
	 */
	public String getMessage() {
		return message;
	}
}
