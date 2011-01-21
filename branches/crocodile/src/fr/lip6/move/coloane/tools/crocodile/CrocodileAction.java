package fr.lip6.move.coloane.tools.crocodile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

public class CrocodileAction implements IService {

	@Override
	public final List<IResult> run(IGraph model, IProgressMonitor monitor)
			throws ServiceException {
		try {
			File tmpFile = File.createTempFile("tmp", ".gml");
			tmpFile.deleteOnExit();
			
			ExportToGML exporter = new ExportToGML();
			// TODO use a sub-monitor instead of monitor
			exporter.export(model, tmpFile.getAbsolutePath(), monitor);
			
			CommandLine cmdLine = new CommandLine("Crocodile");
			cmdLine.addArgument(tmpFile.getAbsolutePath());
			
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

			Executor executor = new DefaultExecutor();
			executor.setExitValue(0);
			//executor.execute(cmdLine, resultHandler);
			//throw new ServiceException(executor.getWorkingDirectory().getAbsolutePath());

			// some time later the result handler callback was invoked so we
			// can safely request the exit value
			//resultHandler.waitFor();
			//int exitValue = resultHandler.getExitValue();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		} catch (ExtensionException e) {
			throw new ServiceException(e.getMessage());
		}
		//} catch (InterruptedException e) {
		//	throw new ServiceException(e.getMessage());
		//}
		
		return null;
	}

}
