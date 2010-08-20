package fr.lip6.move.coloane.extensions.importExportCAMI.exportToCAMI;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.AbsoluteBendpoint;

/**
 * Export models to CAMI format
 *
 * @author Jean-Baptiste Voron
 */
public class ExportToImpl implements IExportTo {

	/** Maximum length of a CAMI command **/
	private static int MAXLENGHT = 255;

	/**
	 * Default constructor
	 */
	public ExportToImpl() { }

	/**
	 * Export a model to CAMI formatted file
	 * @param model The model to export
	 * @param filePath The path of the destination file
	 * @param monitor A monitor to follow the export progression
	 * @throws ExtensionException Something wrong has happened.
	 */
	public final void export(IGraph model, String filePath, IProgressMonitor monitor) throws ExtensionException {
		FileOutputStream writer;

		// Filename checks
		if (filePath.equalsIgnoreCase("") || filePath == null) { //$NON-NLS-1$
			throw new ExtensionException("The filename is not correct. Please provide a valid filename");
		}

		int totalWork = model.getNodes().size() + model.getArcs().size();
		monitor.beginTask("Export to CAMI", totalWork);

		try {
			// File creation
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
			BufferedWriter writerBuffer = new BufferedWriter(new OutputStreamWriter(writer));

			// Translation
			Collection<String> cami = translateModel(model, monitor);
			for (String line : cami) {
				writerBuffer.write(line);
				writerBuffer.newLine();
			}

			// End of writing : clean & close
			writerBuffer.flush();
			writer.flush();
			writerBuffer.close();
			writer.close();
		} catch (FileNotFoundException fe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Echec lors de la création du fichier : Nom de fichier invalide");
			throw new ExtensionException("Invalid filename !");
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Erreur lors de l'écriture dans le fichier");
			throw new ExtensionException("Write error :" + ioe.getMessage());
		}
		monitor.done();
	}

	/**
	 * Translate a model into CAMI commands
	 * @param model The model to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of CAMI commands
	 */
	private Collection<String> translateModel(IGraph model, IProgressMonitor monitor) {
		List<String> toReturn = new ArrayList<String>();

		// Add model node (top level)
		toReturn.add(new String("CN(3:net,1)")); //$NON-NLS-1$

		// Attributes
		for (IAttribute attribute : model.getAttributes()) {
			toReturn.addAll(this.translateAttribute(attribute));
		}

		// Nodes
		monitor.subTask("Export nodes");
		for (INode node : model.getNodes()) {
			toReturn.addAll(this.translateNode(node));
			monitor.worked(1);
		}

		// Arcs
		monitor.subTask("Export arcs");
		for (IArc arc : model.getArcs()) {
			toReturn.addAll(this.translateArc(arc));
			monitor.worked(1);
		}

		return toReturn;
	}

	/**
	 * Translate an arc into CAMI commands
	 * @param arc The arc to convert
	 * @return A collection of CAMI commands (describing the arc)
	 */
	private Collection<String> translateArc(IArc arc) {
		List<String> toReturn = new ArrayList<String>();

		// Arc itself
		StringBuffer buffer = new StringBuffer();
		buffer.append("CA("); //$NON-NLS-1$
		buffer.append(arc.getArcFormalism().getName().length() + ":" + arc.getArcFormalism().getName() + ","); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(arc.getId() + ","); //$NON-NLS-1$
		buffer.append(arc.getSource().getId() + "," + arc.getTarget().getId()); //$NON-NLS-1$
		buffer.append(")"); //$NON-NLS-1$
		toReturn.add(buffer.toString());

		// Bend Points
		for (AbsoluteBendpoint pi : arc.getInflexPoints()) {
			toReturn.add(new String("PI(-1," + arc.getId() + "," + pi.x + "," + pi.y + ",-1)"));  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
		}

		// Arc attributes
		for (IAttribute att : arc.getAttributes()) {
			toReturn.addAll(this.translateAttribute(att));
		}

		return toReturn;
	}

	/**
	 * Translate an attribute into CAMI commands
	 * @param attribute The attribute to convert
	 * @return A collection of CAMI commands (describing the attribute)
	 */
	private Collection<String> translateAttribute(IAttribute attribute) {
		List<String> toReturn = new ArrayList<String>();
		String attributeValue = attribute.getValue();

		// No need to translate if the attribute value is empty
		if (attributeValue.equals("")) { //$NON-NLS-1$
			return toReturn;
		}

		// Split string if it contains any new line (or return)
		String[] valueTable = attributeValue.split("(\n\r)|(\r\n)|(\n)|(\r)"); //$NON-NLS-1$

		// If there is only one line and the length less than 255, the attribute is single line
		if (valueTable.length == 1 && valueTable[0].length() > 0 && valueTable[0].length() <= MAXLENGHT) {

				StringBuffer buffer = new StringBuffer();
				buffer.append("CT(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				buffer.append(valueTable[0].length() + ":" + valueTable[0]); //$NON-NLS-1$
				buffer.append(")"); //$NON-NLS-1$
				toReturn.add(buffer.toString());

		// Otherwise, this attribute is composed of multiple lines
		} else {
			int lineCounter = 1; // Line counter

			for (int i = 0; i < valueTable.length; i++) {

				// Test for each line if the length is less than 255
				if (valueTable[i].length() < MAXLENGHT) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					buffer.append(lineCounter++ + ","); //$NON-NLS-1$
					buffer.append(1 + ","); // Framekit sucks //$NON-NLS-1$
					buffer.append(valueTable[i].length() + ":" + valueTable[i]); //$NON-NLS-1$
					buffer.append(")"); //$NON-NLS-1$
					toReturn.add(buffer.toString());
				} else {
					int start = 0;
					int end = MAXLENGHT;

					// Translate first 255 characters
					while (end < valueTable[i].length()) {
						String sub = valueTable[i].substring(start, end);
						StringBuffer buffer = new StringBuffer();
						buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						buffer.append(lineCounter++ + ","); //$NON-NLS-1$
						buffer.append(1 + ","); // Framekit sucks (again ?) //$NON-NLS-1$
						buffer.append(sub.length() + ":" + sub); //$NON-NLS-1$
						buffer.append(")"); //$NON-NLS-1$
						toReturn.add(buffer.toString());

						start += MAXLENGHT;
						end += MAXLENGHT;
					}

					// Translate all remaining characters
					String sub = valueTable[i].substring(start, valueTable[i].length());
					StringBuffer buffer = new StringBuffer();
					buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
					buffer.append(lineCounter++ + ","); //$NON-NLS-1$
					buffer.append(1 + ","); // FrameKit again... //$NON-NLS-1$
					buffer.append(sub.length() + ":" + sub); //$NON-NLS-1$
					buffer.append(")"); //$NON-NLS-1$
					toReturn.add(buffer.toString());
				}
			}

		}

		// Translate attribute location
		if (attribute.getGraphicInfo().getLocation().x != 0 || attribute.getGraphicInfo().getLocation().y != 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("PT(" + attribute.getReference().getId() + "," + attribute.getName().length() + ":" + attribute.getName() + ",");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
			buffer.append(attribute.getGraphicInfo().getLocation().x + "," + attribute.getGraphicInfo().getLocation().y); //$NON-NLS-1$
			buffer.append(")"); //$NON-NLS-1$
			toReturn.add(buffer.toString());
		}

		return toReturn;
	}

	/**
	 * Translate an arc into CAMI commandes
	 * @param node The node to convert
	 * @return A collection of CAMI commands (describing the node)
	 */
	private Collection<String> translateNode(INode node) {
		List<String> toReturn = new ArrayList<String>();
		toReturn.add(new String("CN(" + node.getNodeFormalism().getName().length() + ":" + node.getNodeFormalism().getName() + "," + node.getId() + ")")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		toReturn.add(new String("PO(" + node.getId() + "," + node.getGraphicInfo().getLocation().x + "," + node.getGraphicInfo().getLocation().y + ")"));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		// Node attributes
		for (IAttribute att : node.getAttributes()) { toReturn.addAll(this.translateAttribute(att));	}

		return toReturn;
	}

}
