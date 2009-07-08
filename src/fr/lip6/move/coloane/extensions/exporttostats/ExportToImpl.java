package fr.lip6.move.coloane.extensions.exporttostats;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
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

/**
 * Export models to STATS format
 *
 * @author Yamina Aziz
 */

public class ExportToImpl implements IExportTo {

	/**
	 * Default constructor
	 */
	public ExportToImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Export a graph to STATS formatted file
	 * @param graph The graph to export
	 * @param filePath The path of the destination file
	 * @param monitor A monitor to follow the export progression
	 * @throws ColoaneException Something wrong has happened.
	 */
	public void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ColoaneException {
		// TODO Auto-generated method stub
		
		FileOutputStream writer;
		BufferedWriter writerBuffer;
		
		// Filename checks
		if (filePath.equalsIgnoreCase("") || filePath == null) { //$NON-NLS-1$
			throw new ColoaneException("The filename is not correct. Please provide a valid filename");
		}
		
		int totalWork = graph.getNodes().size() + graph.getArcs().size();
		monitor.beginTask("Export to STATS", totalWork);
		
		try {
			// File creation
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
			writerBuffer = new BufferedWriter(new OutputStreamWriter(writer));

			// Statistics
			//System.out.println(graph.getFormalism().getName());
			Collection<String> stat = translateGraph(graph, monitor);
			for (String line : stat) {
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
			throw new ColoaneException("Invalid filename !");
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Erreur lors de l'écriture dans le fichier");
			throw new ColoaneException("Write error :" + ioe.getMessage());
		}
		monitor.done();		
	}
	
	/**
	 * Translate graph into STATS commands
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of STATS commands
	 */
	private Collection<String> translateGraph(IGraph graph, IProgressMonitor monitor) {
		List<String> toReturn = new ArrayList<String>();

		// Nodes
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			toReturn.addAll(this.translateNode(graph, node));
			monitor.worked(1);
		}

		// Arcs
		monitor.subTask("Export arcs");
		for (IArc arc : graph.getArcs()) {
			toReturn.addAll(this.translateArc(graph, arc));
			monitor.worked(1);
		}

		return toReturn;
	}
	
	/**
	 * Translate a node into STATS commands
	 * @param graph The graph of nodes
	 * @param node The node to convert
	 * @return A collection of STATS commands (describing the node)
	 */
	
	private Collection<String> translateNode(IGraph graph, INode node) {
		List<String> toReturn = new ArrayList<String>();
		toReturn.add(new String("Number of nodes: " + graph.getNodes().size()));
		toReturn.add(new String("which[" + node.getNodeFormalism().getName().length() + ":" + node.getNodeFormalism().getName() + "]"));
		
		return toReturn;
	}
	
	/**
	 * Translate an arc into STATS commands
	 * @param graph The graph of arcs
	 * @param arc The arc to convert
	 * @return A collection of STATS commands (describing the arc)
	 */
	
	private Collection<String> translateArc(IGraph graph, IArc arc) {
		List<String> toReturn = new ArrayList<String>();
		toReturn.add(new String("Number of arcs: " + graph.getArcs().size()));
		toReturn.add(new String("which[" + arc.getArcFormalism().getName().length() + ":" + arc.getArcFormalism().getName() + "]"));
		
		return toReturn;
	}



}
