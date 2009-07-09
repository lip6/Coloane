package fr.lip6.move.coloane.extensions.exporttostats;

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

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
//import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.draw2d.AbsoluteBendpoint;

import java.util.HashMap;
import java.util.Map;


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
		Map<String, Integer> hmNode = new HashMap<String, Integer>();
		Map<String, Integer> hmArc = new HashMap<String, Integer>();
		
		// Nodes
		monitor.subTask("Export nodes");
		toReturn.add(new String("Number of nodes: " + graph.getNodes().size()));
		for (INode node : graph.getNodes()) {
			Integer i = hmNode.get(node.getNodeFormalism().getName());
			if(i == null)
				hmNode.put(node.getNodeFormalism().getName(), 1);
			else
				hmNode.put(node.getNodeFormalism().getName(), i+1);
			monitor.worked(1);
		}
		
		for(String s: hmNode.keySet()){
			toReturn.add("\t"+ s + ":" + hmNode.get(s));
			}
		toReturn.add("\n");
		
		// Arcs
		monitor.subTask("Export arcs");
		toReturn.add(new String("Number of arcs: " + graph.getArcs().size()));
		for (IArc arc : graph.getArcs()) {
			Integer j = hmArc.get(arc.getArcFormalism().getName());
			if(j == null)
				hmArc.put(arc.getArcFormalism().getName(), 1);
			else
				hmArc.put(arc.getArcFormalism().getName(), j+1);
			
			monitor.worked(1);
		}
		
		for(String str: hmArc.keySet()){
			toReturn.add("\t"+ str + ":" + hmArc.get(str));
		}
				
		return toReturn;
	}

}
	
	