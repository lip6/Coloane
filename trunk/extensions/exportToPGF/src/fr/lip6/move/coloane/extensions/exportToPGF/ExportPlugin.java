package fr.lip6.move.coloane.extensions.exportToPGF;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.extensions.exportToPGF.converters.UnknownFormalismException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public final class ExportPlugin implements IExportTo {

	public void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ColoaneException {
		Logger logger = Logger.getLogger("fr.lip6.move.coloane.core");
		Exporter exporter = new Exporter(monitor);
		int totalWork = graph.getNodes().size() + graph.getArcs().size();
		monitor.beginTask("Export to PGF", totalWork);
		try {
			logger.finer("Opening file " + filePath);
			BufferedWriter outputBuffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))));
			logger.finer("Writing to file");
			outputBuffer.write(exporter.export(graph).toString());
			outputBuffer.close();
		} catch (FileNotFoundException fe) {
			logger.warning("Invalid file name");
			throw new ColoaneException("Invalid filename !");
		} catch (IOException ioe) {
			logger.warning("Cannot write to file");
			throw new ColoaneException("Write error :" + ioe.getMessage());
		} catch (UnknownFormalismException e) {
			logger.warning("No converter for formalism " + graph.getFormalism().getId());
		}
		monitor.done();
	}

}
