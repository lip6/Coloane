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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.tools.crocodile;

import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;

/**
 * 
 * @author Maximilien Colange
 *
 */
public class CrocodileAction implements IService {
	/** The logger for the class */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private static IOConsole myConsole;

	private final URI toolUri;
	
	private FormulaDialog inputDialog;
	private final Display display;
	
	private int choice = FormulaDialog.SRG_ID;

	/**
	 * Public constructor for the action
	 * 
	 * @throws ServiceException if the architecture cannot be determined
	 * @throws IOException if the tool location cannot be resolved
	 * @throws URISyntaxException I don't know
	 */
	public CrocodileAction() throws ServiceException, IOException, URISyntaxException {
		String consoleName = "Crocodile console";

		ConsolePlugin consolePlugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = consolePlugin.getConsoleManager();
		IConsole[] consoleVector = conMan.getConsoles();
		int i;
		for (i = 0; i < consoleVector.length; ++i) {
			if (consoleVector[i].getName().equals(consoleName)) {
				myConsole = (IOConsole) consoleVector[i];
				break;
			}
		}
		if (i == consoleVector.length) {
			myConsole = new IOConsole(consoleName, null);
			conMan.addConsoles(new IConsole[]{myConsole});
		}

		URL tmpURL = FileLocator.toFileURL(Activator.getDefault().getBundle().getResource("crocodile-binaries/Crocodile-" + getArchOS()));

		// use of the multi-argument constructor for URI in order to escape appropriately illegal characters
		toolUri = new URI(tmpURL.getProtocol(), tmpURL.getPath(), null);
		LOGGER.fine("Location of the binary : " + toolUri);

		File crocExec = new File(toolUri);
		if (!crocExec.setExecutable(true)) {
			LOGGER.severe("unable to make the command-line tool executable [" + toolUri + "]");
			throw new ServiceException("unable to make the command-line tool executable");
		}

		display = Display.getDefault();
		inputDialog = new FormulaDialog(display.getActiveShell());
	}
	
	/**
	 * This method implements the action to be run when the Crocodile button in menu is clicked
	 * 
	 * @return a list of results
	 * @param model the model on which Crocodile is run
	 * @param monitor a monitor to monitor the generation of the state space
	 * @throws ServiceException if the generation of the state space fails
	 */
	public final List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
		// TODO define a number of ticks

		SubMonitor progress = SubMonitor.convert(monitor);
		try {
			display.syncExec(
					  new Runnable() {
					    public void run() {
					    	/// TODO : simplify this method when input for formulae is implemented
					    	int reponse = 0;
					    	reponse = inputDialog.open();
					    	setChoice(reponse);
					    	switch (reponse) {
					    	case FormulaDialog.SRG_ID:
					    		System.out.println("Choice : state space generation");
					    		break;
					    	case FormulaDialog.FORM_ID:
					    		System.out.println("Choice : input a formula");
					    		break;
					    	case FormulaDialog.FILE_ID:
					    		System.out.println("Choice : input a file for formulae");
					    		break;
					    	default:
					    	}
					    }
					  }
					  );

			CommandLine cmdLine = new CommandLine(toolUri.getPath());
			cmdLine.addArgument(exportModel(model, progress));

			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

			OutputStream outputStream = myConsole.newOutputStream();
			ExecuteStreamHandler streamHandler = new PumpStreamHandler(outputStream);

			Executor executor = new DefaultExecutor();
			executor.setExitValue(0);
			executor.setStreamHandler(streamHandler);
			executor.execute(cmdLine, resultHandler);

			// some time later the result handler callback was invoked so we
			// can safely request the exit value
			resultHandler.waitFor();

			return null;

		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		} catch (ExtensionException e) {
			throw new ServiceException(e.getMessage());
		} catch (InterruptedException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * A method that returns the correct Crocodile executable suffix, depending on the OS and architecture
	 * @return the suffix of the corresponding Crocodile executable
	 * @throws ServiceException if the suffix could not be determined
	 */
	private String getArchOS() throws ServiceException {
		String osName = System.getProperty("os.name").toLowerCase();
		String archName = System.getProperty("os.arch").toLowerCase();
		if (osName.contains("mac os x")) {
			return "Darwin";
		} else if (osName.contains("linux")) {
			String result;
			result = "linux";
			if (archName.contains("64")) {
				result = result + "64";
				return result;
			} else if (archName.contains("86")) {
				result = result + "32";
				return result;
			}
		}

		throw new ServiceException("System architecture not supported : " + osName + " " + archName);
	}
	
	/**
	 * Sets the choice attribute
	 * 
	 * @param c the new value for <code>choice</code>
	 */
	private void setChoice(int c) {
		choice = c;
	}
	
	/**
	 * Exports the model (in GML format) to a temp file, and returns the absolute path to this file.
	 * 
	 * @param model the model to export
	 * @param monitor the monitor
	 * @return the absolute path to a temp file that contains the GML export of the model
	 * @throws IOException if a problem occurs with the temp file
	 * @throws ExtensionException if a problem occurs during the export to GML
	 */
	private String exportModel(IGraph model, SubMonitor monitor) throws IOException, ExtensionException {
		File tmpFile = File.createTempFile("tmp", ".gml");
		tmpFile.deleteOnExit();

		ExportToGML exporter = new ExportToGML();
		/// TODO find an appropriate number of ticks for the sub-monitor
		exporter.export(model, tmpFile.getAbsolutePath(), monitor.newChild(10));

		return tmpFile.getAbsolutePath();
	}
}
