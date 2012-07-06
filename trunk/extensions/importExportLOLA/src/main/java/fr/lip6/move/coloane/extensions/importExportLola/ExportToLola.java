package fr.lip6.move.coloane.extensions.importExportLola;

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
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Exports a model to the LoLA format
 * 
 * @author Anas Aouad
 *
 */
public class ExportToLola implements IExportTo {

	/**
	 * Export a model to a LoLA formatted file
	 * @param graph The model to export
	 * @param filePath The resulting file's path
	 * @param monitor A monitor to follow the export progression
	 * @throws ExtensionException Something wrong happened
	 */
	public final void export(IGraph graph, String filePath, IProgressMonitor monitor)
			throws ExtensionException {

		FileOutputStream writer;
		StringBuffer placeSB = new StringBuffer();
		StringBuffer transitionSB = new StringBuffer();
		StringBuffer markingSB = new StringBuffer();

		// StringBuffers init
		placeSB.append("PLACE ");
		markingSB.append("MARKING ");
	
		// Filename checks
		if (filePath.equalsIgnoreCase("") || filePath == null) {
			throw new ExtensionException("The filename is not correct. Please provide a valid filename");
		}

		int totalWork = graph.getNodes().size() + graph.getArcs().size();
		monitor.beginTask("Export to LoLA", totalWork);

		try {
			// File creation
			writer = new FileOutputStream(new File(filePath));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writer));

			// Header
			bw.append("{ LoLA net format TPN built from Coloane model \"" + filePath + "\" }\n\n");

			for (INode node : graph.getNodes()) {
				if ("place".equals(node.getNodeFormalism().getName())) {
					exportPlace(node, placeSB);
					exportMarking(node, markingSB);
				} else if ("transition".equals(node.getNodeFormalism().getName())) {
					exportTransition(node, transitionSB);
				} else {
					Logger.getLogger("fr.lip6.move.coloane.core").warning("Unknown node of type "+node.getNodeFormalism().getName()+ " when expecting a P/T net.");
				}
			}

			// For display purposes
			commaCleaner(placeSB);
			commaCleaner(markingSB);

			placeSB.append(";\n");
			markingSB.append(";\n");

			bw.append(placeSB.toString() + "\n" + markingSB.toString() + "\n" + transitionSB.toString());

			// End of writing : clean & close
			bw.flush();
			writer.flush();
			bw.close();
			writer.close();
		} catch (FileNotFoundException fe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Failed creating the file : wrong filename ");
			throw new ExtensionException("Invalid filename !");
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Failed while writing into the file");
			throw new ExtensionException("Write error :" + ioe.getMessage());
		}
		monitor.done();
	}

	/**
	 * Gets a place's name
	 * @param place Place to look into
	 * @return Place's name
	 */
	private String placeId(INode place) {
		IAttribute name = place.getAttribute("name");
		if (name == null || name.getValue().equals("")) {
			return "xxT" + place.getId(); 
		}
		return name.getValue();
	}
	
	/**
	 * Gets a transition's label
	 * @param transition Transition to look into
	 * @return Transition's label
	 */
	private String transitionId(INode transition) {
		IAttribute name = transition.getAttribute("label");
		if (name == null) {
			name = transition.getAttribute("name");
		}
		if (name == null || name.getValue().equals("")) {
			return "xxT" + transition.getId(); 
		}
		return name.getValue();
	}
	
	/**
	 * Gets a marking's value
	 * @param place Place that is marked
	 * @return Marking's value
	 */
	private String markingValue(INode place) {
		IAttribute mark = place.getAttribute("marking");
		if (mark == null)
			return Integer.toString(0);
		return place.getAttribute("marking").getValue();
	}
	
	/**
	 * Exports a place node
	 * @param place Place to export
	 * @param pSB Place StringBuffer
	 * @throws IOException Something wrong happened
	 */
	private void exportPlace(INode place, StringBuffer pSB) throws IOException {
		pSB.append(placeId(place) + ", ");
	}
	
	/**
	 * Exports a place's marking
	 * @param place Place's marking to export
	 * @param mSB Marking StringBuffer
	 */
	private void exportMarking(INode place, StringBuffer mSB) {
		if (Integer.parseInt(markingValue(place)) != 0) {
			mSB.append(placeId(place) + ": " + markingValue(place) + ", ");
		}
	}


	/**
	 * Exports a transition node
	 * @param transition Transition to export
	 * @param tSB Transition StringBuffer
	 * @throws IOException Something wrong happened
	 */
	private void exportTransition(INode transition, StringBuffer tSB) throws IOException {
		IAttribute vis = transition.getAttribute("visibility");
		if (vis == null || "private".equals(vis.getValue())) {

			tSB.append("TRANSITION ");
			tSB.append(transitionId(transition));
			tSB.append("\n");

			tSB.append("CONSUME ");
			for (IArc arc : transition.getIncomingArcs()) {
				exportArc(arc, tSB);
			}
			commaCleaner(tSB);
			tSB.append(";\n");

			tSB.append("PRODUCE ");
			for (IArc arc : transition.getOutgoingArcs()) {
				exportArc(arc, tSB);
			}
			commaCleaner(tSB);
			tSB.append(";\n\n");
		}
	}

	/**
	 * Exports an arc
	 * @param arc Arc to export
	 * @param tSB Transition StringBuffer
	 * @throws IOException Something wrong happened
	 */
	private void exportArc(IArc arc, StringBuffer tSB) throws IOException {
		if (arc.getSource().getNodeFormalism().getName().equals("place")) {
			tSB.append(placeId(arc.getSource()));
		} else {
			tSB.append(placeId(arc.getTarget()));
		}

		tSB.append(": " + arc.getAttribute("valuation").getValue() + ", ");
	}
	
	/**
	 * delete the last occurrence of ',' and the space that follows
	 * @param sb StringBuffer to clean
	 * @throws IOException Something wrong happened
	 */
	private void commaCleaner(StringBuffer sb) throws IOException {
		int start = sb.lastIndexOf(",");
		int end = start + 2;

		if (start != -1) {
			sb.delete(start, end);
		}
	}

}
