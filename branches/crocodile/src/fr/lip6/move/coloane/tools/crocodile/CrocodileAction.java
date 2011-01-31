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
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
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
	private static IOConsole myConsole;
	private static String toolLocation;

	/**
	 * Public constructor for the action
	 * 
	 * @throws ServiceException if the architecture cannot be determined
	 */
	public CrocodileAction() throws ServiceException {
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

		String prefix = Activator.getDefault().getBundle().getLocation().replace("reference:file:", "");
		toolLocation = prefix + "crocodile-binaries/Crocodile-" + getArchOS();

		File crocExec = new File(toolLocation);
		if (!crocExec.setExecutable(true)) {
			throw new ServiceException("unable to make the command-line tool executable");
		}
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
			File tmpFile = File.createTempFile("tmp", ".gml");
			tmpFile.deleteOnExit();

			ExportToGML exporter = new ExportToGML();
			// TODO find an appropriate number of ticks for the sub-monitor
			exporter.export(model, tmpFile.getAbsolutePath(), progress.newChild(10));

			CommandLine cmdLine = new CommandLine(toolLocation);
			cmdLine.addArgument(tmpFile.getAbsolutePath());

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
}
