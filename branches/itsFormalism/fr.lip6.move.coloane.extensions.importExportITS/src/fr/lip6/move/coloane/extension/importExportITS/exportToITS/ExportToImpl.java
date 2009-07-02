package fr.lip6.move.coloane.extension.importExportITS.exportToITS;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Export models to ITS XML format
 *
 * @author Yann
 */
public class ExportToImpl implements IExportTo {

	/**
	 * Export a model to ITS XML formatted file
	 * @param model The model to export
	 * @param filePath The path of the destination file
	 * @param monitor A monitor to follow the export progression
	 * @throws ColoaneException Something wrong has happened.
	 */
	public final void export(IGraph model, String filePath, IProgressMonitor monitor) throws ColoaneException {
		FileOutputStream writer;

		// Filename checks
		if (filePath.equalsIgnoreCase("") || filePath == null) { //$NON-NLS-1$
			throw new ColoaneException("The filename is not correct. Please provide a valid filename");
		}

		int totalWork = model.getNodes().size() + model.getArcs().size();
		monitor.beginTask("Export to ITS", totalWork);

		try {
			// File creation
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
			BufferedWriter sb = new BufferedWriter(new OutputStreamWriter(writer));

			// Translation
			// write header
			sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
			// TODO : add a schema reference
			sb.append("<model>\n");
			// First export nodes: should be only instances and synchronizations
			for (INode node : model.getNodes()) {
				sb.append("<");
				sb.append(node.getNodeFormalism().getName());
				sb.append(" ");
				// Add the id
				sb.append("id='" + Integer.toString(node.getId()) + "' ");
				for (IAttribute a : node.getAttributes()) {
					sb.append(a.getName());
					sb.append("='");
					sb.append(a.getValue());
					sb.append("' ");
				}
				sb.append("/>\n");
			}

			// Now export the arcs
			for (IArc arc : model.getArcs()) {
				sb.append("<");
				sb.append(arc.getArcFormalism().getName());
				sb.append(" ");
				INode src = arc.getSource();
				INode dest = arc.getTarget();
				// check the arc is in the right direction
				if (!"instance".equals(src.getNodeFormalism().getName())) {
					// swap src and dest if not
					INode tmp = src;
					src = dest;
					dest = tmp;
				}
				// now print source and dest id
				sb.append(" instance='" + src.getId() + "' ");
				sb.append(" synchronization='" + dest.getId() + "' ");
				// add the labels (and any other attributes ??)
				for (IAttribute a : arc.getAttributes()) {
					sb.append(a.getName());
					sb.append("='");
					sb.append(a.getValue());
					sb.append("' ");
				}
				sb.append("/>\n");

			}
			sb.append("</model>\n");			
			sb.newLine();
			// End of writing : clean & close
			sb.flush();
			writer.flush();
			sb.close();
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

