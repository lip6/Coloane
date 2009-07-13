package fr.lip6.move.coloane.extensions.importExportGSPN.exportToGSPN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.logging.Logger;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
//import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Export graphs to GSPN format
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
	 * Export a graph to GSPN formatted file
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
		
		int totalWork = graph.getNodes().size();
		monitor.beginTask("Export to GSPN", totalWork);
		
		try {
			// File creation
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
			writerBuffer = new BufferedWriter(new OutputStreamWriter(writer));

			// Translation
			Collection<String> gspn = translateGraph(graph, monitor);
			for (String line : gspn) {
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
	 * Translate a graph into GSPN commands
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	private Collection<String> translateGraph(IGraph graph, IProgressMonitor monitor) {
		List<String> toReturn = new ArrayList<String>();
		char lettre='\0';
		
		toReturn.add("|256");
		toReturn.add("%");
		toReturn.add("|");
		
		// Nodes
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			for(IAttribute attribute : node.getAttributes()){
				if((attribute.getName().equals("marking"))){
					lettre='m';
					if(attribute.getValue().equals("0")==false){
						toReturn.add("(" + attribute.getValue() + " " + lettre + " " + attribute.getGraphicInfo().getLocation().x + " " + attribute.getGraphicInfo().getLocation().y + " " + "(@" + lettre);
						toReturn.add("))");
					}
				}
			monitor.worked(1);
			}
		}
		
		// Attributes
		for (IAttribute attribute : graph.getAttributes()) {
			if(attribute.getName().equals("colorset")){
				lettre='c';
			}
			if(attribute.getName().equals("function")){
				lettre='f';
			}
			
			if(attribute.getValue().equals("")==false){
				toReturn.add("(" + attribute.getValue() + " " + lettre + " " + attribute.getGraphicInfo().getLocation().x + " " + attribute.getGraphicInfo().getLocation().y + " " + "(@" + lettre);
				toReturn.add("))");
			}
						
		}

		return toReturn;
	}


}
