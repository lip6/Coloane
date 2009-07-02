package fr.lip6.move.coloane.extension.importExportRomeo.exportToRomeo;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

/**
 * Export models to CAMI format
 *
 * @author Jean-Baptiste Voron
 */
public class ExportToImpl implements IExportTo {
	
	// the indent level for pretty printing
	private int indent = 0;
	/**
	 * Export a model to Romeo formatted file
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
		monitor.beginTask("Export to Romeo", totalWork);

		try {
			// File creation
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
			BufferedWriter sb = new BufferedWriter(new OutputStreamWriter(writer));

			// header
			sb.append("<TPN name=\"" + filePath + "\">\n");
			indent++;
			for (INode node : model.getNodes()) {
				if ("place".equals(node.getNodeFormalism().getName())) {
					exportPlace(node, sb);
				} else {
					exportTransition(node, sb);
				}
			}

			// trailer
			sb.append("</TPN>\n");


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

	private void exportTransition(INode node, BufferedWriter sb) throws IOException {
		indent(sb);
		sb.append("<transition ");
		sb.append("id=\"" + node.getId() + "\" ");
		if ("".equals(node.getAttribute("label").getValue())) {
			sb.append("label=\"" + node.getAttribute("name").getValue() + "\" public=\"0\"");
		} else {
			sb.append("label=\"" + node.getAttribute("name").getValue() + "\" public=\"1\"");
		}
		sb.append("eft=\"" + node.getAttribute("earliestFiringTime").getValue() + "\" ");
		String lft = node.getAttribute("latestFiringTime").getValue();
		try {
			sb.append("lft=\"" + Integer.parseInt(lft) + "\" ");
		} catch (NumberFormatException e) {
			sb.append("lft=\"-1\" ");
		}
		sb.append(">\n");

		indent++;
		exportNodeGraphics(node, sb);

		indent--;
		indent(sb);
		sb.append("</transition>\n");
	}

	private void exportPlace(INode node, BufferedWriter sb) throws IOException {
		indent(sb);
		sb.append("<place ");
		sb.append("id=\"" + node.getId() + "\" ");
		sb.append("label=\"" + node.getAttribute("name").getValue() + "\" ");
		sb.append("initialMarking=\"" + node.getAttribute("marking").getValue() + "\" ");
		sb.append(">\n");
		indent++;
		exportNodeGraphics(node, sb);

		indent(sb);
		// not really sure what this reflects ?
		sb.append("<scheduling gamma=\"0\" omega=\"0\"/>\n");

		indent--;
		indent(sb);
		sb.append("</place>\n");
	}

	/** 
	 * Export the graphical position of a node and its name tag.
	 * @param node the node (place or transition)
	 * @param sb the output
	 * @throws IOException if write problems
	 */
	private void exportNodeGraphics(INode node, BufferedWriter sb) throws IOException {
		indent(sb);
		sb.append("<graphics>\n");
		indent++;
		// node position
		Point loc = node.getGraphicInfo().getLocation();
		indent(sb);
		sb.append("<position x=\"" + loc.x + "\" y=\"" + loc.y + "\"/>\n");

		// label position (delta)
		Point loctag = node.getAttribute("name").getGraphicInfo().getLocation();
		indent(sb);
		sb.append("<deltaLabel deltax=\"" + (loctag.x - loc.x) + "\" deltay==\"" + (loctag.y - loc.y) + "\"/>\n");
		indent--;

		indent(sb);
		sb.append("</graphics>\n");
	}
	/**
	 * Adds 2 whitespace per indent level.
	 * @param sb to add to
	 * @throws IOException if write problems
	 */
	private void indent(BufferedWriter sb) throws IOException {
		for (int i = 0; i < indent; i++) {
			sb.append("  ");
		}
	}


}
