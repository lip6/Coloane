package fr.lip6.move.coloane.tools.crocodile;

import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 
 * @author Maximilien Colange
 *
 */
public class CrocodileAction implements IService {
	
	/**
	 * Public constructor for the action
	 */
	public CrocodileAction() {
	}
	
	/**
	 * This method implements the action to be run when the Crocodile button in menu is clicked
	 * 
	 * @return a list of results
	 * @param model the model on which Crocodile is run
	 * @param monitor a monitor to monitor the generation of the state space
	 * @throws ServiceException if the generation of the state space fails
	 */
	public final List<IResult> run(IGraph model, IProgressMonitor monitor)
			throws ServiceException {
		try {
			File tmpFile = File.createTempFile("tmp", ".gml");
			tmpFile.deleteOnExit();

			ExportToGML exporter = new ExportToGML();
			// TODO use a sub-monitor instead of monitor
			exporter.export(model, tmpFile.getAbsolutePath(), monitor);

			String archName = getArchOS();

			//CommandLine cmdLine = new CommandLine("./Crocodile-" + archName);
			//cmdLine.addArgument(tmpFile.getAbsolutePath());
			CommandLine cmdLine = new CommandLine("echo");
			cmdLine.addArgument("ceci est un test");

			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

			ExecuteStreamHandler streamHandler = new PumpStreamHandler();
			byte[] inputArray = new byte[320000];
			ByteArrayInputStream inputStream = new ByteArrayInputStream(inputArray);
			streamHandler.setProcessOutputStream(inputStream);
			streamHandler.setProcessErrorStream(inputStream);

			Executor executor = new DefaultExecutor();
			executor.setExitValue(0);
			executor.setStreamHandler(streamHandler);
			executor.execute(cmdLine, resultHandler);
			executor.execute(cmdLine);

			// some time later the result handler callback was invoked so we
			// can safely request the exit value
			resultHandler.waitFor();
			//int exitValue = resultHandler.getExitValue();
			IResult crocodileResult = new Result("Crocodile Result");
			ISubResult crocodileSubResult = new SubResult("Crocodile SubResult");
			byte[] resultArray = new byte[inputStream.available()];
			inputStream.read(resultArray, 0, inputStream.available());
			crocodileSubResult.addTextualResult(new String(resultArray));
			crocodileResult.addSubResult(crocodileSubResult);

			List<IResult> result = new ArrayList<IResult>();
			result.add(crocodileResult);

			return result;

//			if (exitValue != 0) {
//				throw new ServiceException("Crocodile execution has failed !");
//			}
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		} catch (ExtensionException e) {
			throw new ServiceException(e.getMessage());
		} catch (InterruptedException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * A method that returns the correct crocodile executable suffix, depending on the OS and architecture
	 * @return the suffix of the corresponding crocodile executable
	 * @throws ServiceException if the suffix could not be determined
	 */
	private String getArchOS() throws ServiceException {
		String result;
		String osName = System.getProperty("os.name").toLowerCase();
		String archName = System.getProperty("os.arch").toLowerCase();
		if (osName.contains("mac os x")) {
			result = "Darwin";
		} else if (osName.contains("linux")) {
			result = "linux";
			if (archName.contains("64")) {
				result = result + "64";
			} else {
				result = result + "32";
			}
		} else {
			throw new ServiceException("System architecture not supported : " + osName + " " + archName);
		}
		return result;
	}
}
