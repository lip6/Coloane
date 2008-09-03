package fr.lip6.move.coloane.extension.exportToDOT;

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
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {	}

	public void export(IGraph model, String filePath, IProgressMonitor monitor) throws ColoaneException {

		FileOutputStream writer;
		try {
			int totalWork = model.getNodes().size() + model.getArcs().size();
			monitor.beginTask("Export to dot", totalWork);
			// Creation du fichier
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
			BufferedWriter writerBuffer = new BufferedWriter(new OutputStreamWriter(writer));
			
			// Ecriture
			writerBuffer.write(DotConverter.translateModel(model, monitor));
			
			// Fin del'écriture : nettoyage et fermeture
			writerBuffer.flush();
			writer.flush();
			writerBuffer.close();
			writer.close();
		} catch (FileNotFoundException fe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Echec lors de la création du fichier : Nom de fichier invalide");
			throw new ColoaneException("Invalid filename !");
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Erreur lors de l'écriture dans le fichier");
			throw new ColoaneException("Write error :" + ioe.getMessage());
		}
		monitor.done();
	}
}
