package fr.lip6.move.coloane.extensions.importExportGSPN.exportToGSPN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
		
		int totalWork = graph.getNodes().size() + graph.getArcs().size();
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
		int i=1;
		String label=new String(), description=new String();;
		
		
		/* .DEF */
		// Management of transitions (Marking Dependent)
		for (INode node : graph.getNodes()) {
			for(IAttribute attribute : node.getAttributes()){
				if(attribute.getName().equals("definition")){
					toReturn.add("|"+i);
					toReturn.add(attribute.getValue());
					i++;
				}
			}
		}
		
		toReturn.add("|256");
		toReturn.add("%");
		toReturn.add("|");
		
		// Marking of places
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			for(IAttribute attribute : node.getAttributes()){
				if((attribute.getName().equals("marking"))){
					lettre='m';
					if(attribute.getValue().equals(attribute.getAttributeFormalism().getDefaultValue())==false){
						toReturn.add("(" + lettre + node.getId() + " " + lettre + " " + attribute.getGraphicInfo().getLocation().x + " " + attribute.getGraphicInfo().getLocation().y + " " + "(@" + lettre);
						toReturn.add(attribute.getValue());
						toReturn.add("))");
					}
				}
			//monitor.worked(1);
			}
		}
		
		// Color classes and static subclasses
		for (IAttribute attribute : graph.getAttributes()) {
			lettre='c';
			if((attribute.getValue().equals("")==false)){
				String classes=attribute.getValue();
				String[] color=classes.split("\n");
				for(int j=0;j<color.length;j++){
					if(color[j].contains("=")){
						String[] separator=color[j].split("=");
						for(int k=0;k<separator.length;k++){
							if(k%2==0)
								label=separator[k];
							else
								description=separator[k];
							Map<String, String> hmColor = new HashMap<String, String>();
							hmColor.put(label, description);
						}
						toReturn.add("(" + label + " " + lettre + " " + attribute.getGraphicInfo().getLocation().x + " " + attribute.getGraphicInfo().getLocation().y + " (@" + lettre);
						toReturn.add(description);
						toReturn.add("))");
					}
				}
			}
		}
							
		toReturn.add("\n\n\n");
		
		
		
		/* .NET */
		
		
		
		int nb_places=0, nb_transitions=0, nb_imm=0;
		String tag=new String(), color, rate=new String(), definition=new String(), priority=new String(), marking=new String();
		int abs_node, ord_node, abs_att, ord_att, abs_color, ord_color;
				
		
		toReturn.add("|0|");
		toReturn.add("|");
		
		// Nodes
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			if(node.getNodeFormalism().getName().equals("place"))
				nb_places++;
			if((node.getNodeFormalism().getName().equals("immediate transition"))||(node.getNodeFormalism().getName().equals("transition (Infinite)"))||(node.getNodeFormalism().getName().equals("transition (Marking Dependent)"))||(node.getNodeFormalism().getName().equals("transition (Server)")))
				nb_transitions++;
			if(node.getNodeFormalism().getName().equals("immediate transition"))
				nb_imm++;
		}
		toReturn.add("f  0  " + nb_places + "  0  " + nb_transitions + "  " + nb_imm + "  0  0");
		
		
		for (INode node : graph.getNodes()){
			// Management of places
			if(node.getNodeFormalism().getName().equals("place")){
				abs_node=node.getGraphicInfo().getLocation().x;
				ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("marking")){
						if((attribute.getValue().equals(attribute.getAttributeFormalism().getDefaultValue()))==false){
							marking="-1000" + node.getId();
							//i++;
						}
						else
							marking="0";
					}
					//abs_att=attribute.getGraphicInfo().getLocation().x;
					//ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							abs_color=attribute.getGraphicInfo().getLocation().x;
							ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + "  " + marking + " " + abs_node + " " + ord_node + " " + "abs_att" + " " + "ord_att" + " 0 " + abs_color + " " + ord_color + " " + color);
						}
						else
							toReturn.add(tag + "  " + marking + " " + abs_node + " " + ord_node + " " + "abs_att" + " " + "ord_att" + " 0 ");
					}
					
				}
			}
		}
		
		
		for(INode node : graph.getNodes()){
			// Management of groups
			if(node.getNodeFormalism().getName().equals("immediate transition")){
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("priority")){
						priority=attribute.getValue();
						toReturn.add("Gi  0 0 " + priority);
					}
				}
			}
				
		}
		
				
		for(INode node : graph.getNodes()){
			// Management of transitions
			if(node.getNodeFormalism().getName().equals("transition (Infinite)")){
				abs_node=node.getGraphicInfo().getLocation().x;
				ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("rate"))
						rate=attribute.getValue();
					abs_att=attribute.getGraphicInfo().getLocation().x;
					ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							abs_color=attribute.getGraphicInfo().getLocation().x;
							ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + " " + rate + "  0  0  nb_arcs 0 " + abs_node + " " + ord_node + " " + abs_att + " " + ord_att + " " + "pos1 pos2 0 " + abs_color + " " + ord_color + " " + color);
						}
						else
							toReturn.add(tag + " " + rate + "  0  0  nb_arcs 0 " + abs_node + " " + ord_node + " " + abs_att + " " + ord_att + " " + "pos1 pos2 0");
							
					}
				}
				
				//for(IArc arc : graph.getArcs()){
					//for(IAttribute attribute : arc.getAttributes()){
						
					//}
				//}
				
			}
			
			if(node.getNodeFormalism().getName().equals("transition (Server)")){
				abs_node=node.getGraphicInfo().getLocation().x;
				ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("rate"))
						rate=attribute.getValue();
					if(attribute.getName().equals("priority"))
						priority=attribute.getValue();
					abs_att=attribute.getGraphicInfo().getLocation().x;
					ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							abs_color=attribute.getGraphicInfo().getLocation().x;
							ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + " " + rate + "  " + priority + "  0  nb_arcs 0 " + abs_node + " " + ord_node + " " + abs_att + " " + ord_att + " " + "pos1 pos2 0 " + abs_color + " " + ord_color + " " + color);
						}
						else
							toReturn.add(tag + " " + rate + "  " + priority + "  0  nb_arcs 0 " + abs_node + " " + ord_node + " " + abs_att + " " + ord_att + " " + "pos1 pos2 0");
							
					}
				}
				
				//for(IArc arc : graph.getArcs()){
					//for(IAttribute attribute : arc.getAttributes()){
						
					//}
				//}
				
			}
			
			if(node.getNodeFormalism().getName().equals("transition (Marking Dependent)")){
				abs_node=node.getGraphicInfo().getLocation().x;
				ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("definition"))
						definition=attribute.getValue();
					abs_att=attribute.getGraphicInfo().getLocation().x;
					ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							abs_color=attribute.getGraphicInfo().getLocation().x;
							ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + " " + definition + "  1  0  nb_arcs 0 " + abs_node + " " + ord_node + " " + abs_att + " " + ord_att + " " + "pos1 pos2 0 " + abs_color + " " + ord_color + " " + color);
						}
						else
							toReturn.add(tag + " " + definition + "  1  0  nb_arcs 0 " + abs_node + " " + ord_node + " " + abs_att + " " + ord_att + " " + "pos1 pos2 0");
							
					}
				}
				
				//for(IArc arc : graph.getArcs()){
					//for(IAttribute attribute : arc.getAttributes()){
						
					//}
				//}
				
			}
			
			// immediate transition
			// [...]
			
			monitor.worked(1);
		}
		
		

		return toReturn;
	}


}
