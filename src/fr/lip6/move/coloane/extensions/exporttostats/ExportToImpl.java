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
		int a=0, b=0, c=0, d=0, e=0, f=0, g=0, h=0, i=0, j=0, k=0, l=0, m=0;

		// Nodes
		monitor.subTask("Export nodes");
		toReturn.add(new String("Number of nodes: " + graph.getNodes().size()));
		for (INode node : graph.getNodes()) {
						
			if(node.getNodeFormalism().getName().equals("place"))
				a++;
			if(node.getNodeFormalism().getName().equals("immediate transition"))
				b++;
			if(node.getNodeFormalism().getName().equals("transition (Infinite)"))
				c++;
			if(node.getNodeFormalism().getName().equals("transition (Marking Dependent)"))
				d++;
			if(node.getNodeFormalism().getName().equals("transition (1-Server)"))
				e++;
			
			monitor.worked(1);
		}
		
		toReturn.add(new String("\t" +a+ " places"));
		toReturn.add(new String("\t" +b+ " immediate transition"));
		toReturn.add(new String("\t" +c+ " transition (Infinite)"));
		toReturn.add(new String("\t" +d+ " transition (Marking Dependent)"));
		toReturn.add(new String("\t" +e+ " transition (1-Server)"));

		// Arcs
		monitor.subTask("Export arcs");
		toReturn.add(new String("Number of arcs: " + graph.getArcs().size()));
		for (IArc arc : graph.getArcs()) {
						
			if(arc.getArcFormalism().getName().equals("arc"))
				f++;
			if(arc.getArcFormalism().getName().equals("broken arc"))
				g++;
			if(arc.getArcFormalism().getName().equals("colored arc"))
				h++;
			if(arc.getArcFormalism().getName().equals("broken colored arc"))
				i++;
			if(arc.getArcFormalism().getName().equals("inhibitor arc"))
				j++;
			if(arc.getArcFormalism().getName().equals("broken inhibitor arc"))
				k++;
			if(arc.getArcFormalism().getName().equals("colored inhibitor arc"))
				l++;
			if(arc.getArcFormalism().getName().equals("broken colored inhibitor arc"))
				m++;
			
			monitor.worked(1);
		}
		
		toReturn.add(new String("\t" +f+ " arc"));
		toReturn.add(new String("\t" +g+ " broken arc"));
		toReturn.add(new String("\t" +h+ " colored arc"));
		toReturn.add(new String("\t" +i+ " broken colored arc"));
		toReturn.add(new String("\t" +j+ " inhibitor arc"));
		toReturn.add(new String("\t" +k+ " broken inhibitor arc"));
		toReturn.add(new String("\t" +l+ " colored inhibitor arc"));
		toReturn.add(new String("\t" +m+ " broken colored inhibitor arc"));
		

		return toReturn;
	}
}
	
	