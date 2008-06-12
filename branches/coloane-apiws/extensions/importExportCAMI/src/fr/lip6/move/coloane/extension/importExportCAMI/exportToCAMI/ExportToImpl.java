package fr.lip6.move.coloane.extension.importExportCAMI.exportToCAMI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.core.motor.formalism.Messages;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {}

	/**
	 * Exporte un modeladapter en modele en fonction de son formalisme
	 * @param modelToExport le model a enregistrer
	 * @param filePath nom du fichier desire sans les extentions
	 * @throws ColoaneException en cas d'echec
	 */
	public void export(IModelImpl modelToExport, String filePath) throws ColoaneException {
		FileOutputStream writer;
		
		// Controle sur le nom du fichier
		if (filePath.equalsIgnoreCase("") || filePath == null) { //$NON-NLS-1$
			throw new ColoaneException(Messages.FormalismManager_5);
		}

		// Creation du fichier
		
		try {
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
		} catch (FileNotFoundException e1) {
			throw new ColoaneException(Messages.FormalismManager_7 + filePath); //$NON-NLS-2$
		}
		
		BufferedWriter writerBuffer = new BufferedWriter(new OutputStreamWriter(writer));

		// Traduction du modele entier
		try {
			Vector<String> cami = modelToExport.getGenericModel().translate();
			for (String line : cami) {
				writerBuffer.write(line);
				writerBuffer.newLine();
			}
		} catch (Exception e) {
			throw new ColoaneException(Messages.FormalismManager_9);
		}

		try {
			writerBuffer.flush();
			writer.flush();
			writerBuffer.close();
			writer.close();
		} catch (IOException e) {
			throw new ColoaneException(Messages.FormalismManager_10);
		}
	}

}
