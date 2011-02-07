/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.api.IApiObserver;
import fr.lip6.move.coloane.interfaces.objects.services.ConsoleMessage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * Handle console messages display coming from an API.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class ConsoleMessageObserver implements IApiObserver {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** Console handled by this observer */
	private MessageConsole console;
	
	public ConsoleMessageObserver(MessageConsole console) {
		this.console = console;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void update(IApi api, Object newMessages) {
		LOGGER.warning("Console messages should be updated"); //$NON-NLS-1$

		final List<ConsoleMessage> messages = (List<ConsoleMessage>) newMessages;
		final MessageConsole console = this.console;
		
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {

				for (ConsoleMessage message : messages) {
					MessageConsoleStream consoleStream = console.newMessageStream();
					consoleStream.setColor(message.getColor());
					consoleStream.setFontStyle(message.getFontType());
					consoleStream.println(message.getMessage());
					try {
						consoleStream.flush();
						consoleStream.close();
					} catch (IOException e) {
						LOGGER.warning("Something went wrong with the MessageConsole"); //$NON-NLS-1$
					}
				}
			}
		});
	}
}
