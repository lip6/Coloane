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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
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

	/** Minimum number of transition (Marking Dependent)'s definition **/
	private static int MINMKD = 1;
	
	private char lettre;
	private int index=0, index2=0, index3=1, nb_places=0, nb_transitions=0, id_source, id_target, nb_input_arcs, nb_output_arcs, nb_inhibitor_arcs;
	private String label, description, tag, marking, rate, definition, weight, priority, color, multiplicity, arc_color, value;
	private double abs_node, ord_node, abs_tag, ord_tag, abs_rate, ord_rate, abs_weight, ord_weight, abs_def, ord_def, abs_color, ord_color;
	//private double abs_arc_color, ord_arc_color;
	private Integer index_marking, index_place, index_priority, address;
	private Map<String, Integer> hmMarking = new HashMap<String, Integer>();
	private Map<Integer, Integer> hmPlace = new HashMap<Integer, Integer>();
	private Map<String, Integer> tmGroup = new TreeMap<String, Integer>();
	
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
		
		// Graph check
		if(graph==null) {
			throw new ColoaneException("The graph cannot be null");
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
	public Collection<String> translateGraph(IGraph graph, IProgressMonitor monitor) {
		List<String> toReturn = new ArrayList<String>();
		
		toReturn.addAll(this.definitionMD(graph, monitor));
		toReturn.addAll(this.headerDef());
		toReturn.addAll(this.markingOfPlaces(graph, monitor));
		toReturn.addAll(this.colorDef(graph, monitor));
		toReturn.add("\n\n\n");
		toReturn.addAll(this.headerNet());
		toReturn.addAll(this.noobjs(graph, monitor));
		toReturn.addAll(this.places(graph, monitor));
		toReturn.addAll(this.groups(graph, monitor));
		toReturn.addAll(this.transitionsAndArcs(graph, monitor));
		
		return toReturn;
	}
	
	
	/**
	 * Manages definition of transition (Marking Dependent) for .def
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	public Collection<String> definitionMD(IGraph graph, IProgressMonitor monitor){
		List<String> toReturn = new ArrayList<String>();
		
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			for(IAttribute attribute : node.getAttributes()){
				if(attribute.getName().equalsIgnoreCase("definition")){
					toReturn.add("|"+ MINMKD);
					toReturn.add(attribute.getValue());
					MINMKD++;
				}
			}
			monitor.worked(1);
		}
		
		return toReturn;
	}
	

	/**
	 * Manages the .def header
	 * @return A collection of GSPN commands
	 */
	public Collection<String> headerDef(){
		List<String> toReturn = new ArrayList<String>();
		
		toReturn.add("|256");
		toReturn.add("%");
		toReturn.add("|");
		
		return toReturn;
	}
	
	
	/**
	 * Manages the attribute "marking" of places for .def
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	public Collection<String> markingOfPlaces(IGraph graph, IProgressMonitor monitor){
		List<String> toReturn = new ArrayList<String>();
		
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			for(IAttribute attribute : node.getAttributes()){
				if((attribute.getName().equalsIgnoreCase("marking"))){
					lettre='m';
					if(attribute.getValue().equalsIgnoreCase(attribute.getAttributeFormalism().getDefaultValue())==false){
						toReturn.add("(" + lettre + node.getId() + " " + lettre + " " + getAttributeXCoordinate(attribute) + " " + getAttributeYCoordinate(attribute) + " " + "(@" + lettre);
						toReturn.add(attribute.getValue());
						toReturn.add("))");
						index++;
						hmMarking.put(attribute.getValue(), index);
					}
				}
			}
			monitor.worked(1);
		}
		
		return toReturn;
	}
	
	
	/**
	 * Manages the attributes "color classes" and "static subclasses" of graph for .def
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	public Collection<String> colorDef(IGraph graph, IProgressMonitor monitor){
		List<String> toReturn = new ArrayList<String>();
		
		for (IAttribute attribute : graph.getAttributes()) {
			lettre='c';
			if((attribute.getValue().equalsIgnoreCase("")==false)){
				String classes=attribute.getValue();
				String[] color=classes.split("\n");
				for(int i=0;i<color.length;i++){
					if(color[i].contains("=")){
						String[] separator=color[i].split("=");
						for(int j=0;j<separator.length;j++){
							if(j%2==0)
								label=separator[j];
							else
								description=separator[j];
						}
						toReturn.add("(" + label + " " + lettre + " " + getAttributeXCoordinate(attribute) + " " + getAttributeYCoordinate(attribute) + " (@" + lettre);
						toReturn.add(description);
						toReturn.add("))");
					}
				}
			}
		}
		
		return toReturn;
	}
	
	
	/**
	 * Translate a graph into GSPN commands
	 * This method manages the .net header
	 * @return A collection of GSPN commands
	 */
	public Collection<String> headerNet(){
		List<String> toReturn = new ArrayList<String>();
		
		toReturn.add("|0|");
		toReturn.add("|");
		
		return toReturn;
	}
	
	
	/**
	 * Manages the objects' number of graph for .net
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	public Collection<String> noobjs(IGraph graph, IProgressMonitor monitor){
		List<String> toReturn = new ArrayList<String>();
		
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			if(node.getNodeFormalism().getName().equalsIgnoreCase("place"))
				nb_places++;
			if((node.getNodeFormalism().getName().equalsIgnoreCase("immediate transition"))||(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Infinite)"))||(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Marking Dependent)"))||(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Server)")))
				nb_transitions++;
			if(node.getNodeFormalism().getName().equalsIgnoreCase("immediate transition")){
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equalsIgnoreCase("priority")){
						priority=attribute.getValue();
						address = tmGroup.get(priority);
						if(address==null){
							tmGroup.put(priority, index3);
							index3++;
						}
					}
				}
			}
			monitor.worked(1);
		}
		toReturn.add("f   0   " + nb_places + "   0   " + nb_transitions + "   " + tmGroup.size() + "   0   0");
		
		return toReturn;
	}
	
	
	/**
	 * Manages places of graph for .net
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	public Collection<String> places(IGraph graph, IProgressMonitor monitor){
		List<String> toReturn = new ArrayList<String>();
		
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()){
			if(node.getNodeFormalism().getName().equalsIgnoreCase("place")){
				abs_node=getNodeXCoordinate(node);
				ord_node=getNodeYCoordinate(node);
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equalsIgnoreCase("tag")){
						abs_tag=getAttributeXCoordinate(attribute);
						ord_tag=getAttributeYCoordinate(attribute);
						tag=attribute.getValue();
					}
					if(attribute.getName().equalsIgnoreCase("marking")){
						if((attribute.getValue().equalsIgnoreCase(attribute.getAttributeFormalism().getDefaultValue()))==false){
							value=attribute.getValue();
							index_marking=hmMarking.get(value);
							marking="-1000" + index_marking;
						}
						else
							marking="0";
					}
					if(attribute.getName().equalsIgnoreCase("color label")){
						if(attribute.getValue().equalsIgnoreCase("")==false){
							abs_color=getAttributeXCoordinate(attribute);
							ord_color=getAttributeYCoordinate(attribute);
							color=attribute.getValue();
						}
					}
				}
				if((abs_color!=0) && (ord_color!=0) && (color !=null)){
				toReturn.add(tag + "    " + marking + " " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " 0 " + abs_color + " " + ord_color + " " + color);
				}
				else{
				toReturn.add(tag + "    " + marking + " " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " 0");
				}
				index2++;
				hmPlace.put(node.getId(), index2);
			}
			monitor.worked(1);
		}
		
		return toReturn;
	}
	
	
	/**
	 * Manages groups of graph for .net
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	public Collection<String> groups(IGraph graph, IProgressMonitor monitor){
		List<String> toReturn = new ArrayList<String>();
				
		for(String s: tmGroup.keySet()){
			if(s.equalsIgnoreCase("1")){
				monitor.subTask("Export nodes");
				for(INode node: graph.getNodes()){
					if(node.getNodeFormalism().getName().equalsIgnoreCase("immediate transition")){
						abs_node=getNodeXCoordinate(node);
						ord_node=getNodeYCoordinate(node);
					}
					monitor.worked(1);
				}
				toReturn.add("G" + tmGroup.get(s) + " " + abs_node + " " + ord_node + " " + s);
			}
			else
				toReturn.add("G" + tmGroup.get(s) + " " + "0" + " " + "0" + " " + s);
		}
		
		return toReturn;
	}
	
	
	/**
	 * Manages transitions and arcs of graph for .net
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	public Collection<String> transitionsAndArcs(IGraph graph, IProgressMonitor monitor){
		List<String> toReturn = new ArrayList<String>();
		
		// Management of transitions
		monitor.subTask("Export nodes");
		for(INode node : graph.getNodes()){
			if((node.getNodeFormalism().getName().equalsIgnoreCase("transition (Infinite)"))||(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Server)"))||(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Marking Dependent)"))||(node.getNodeFormalism().getName().equalsIgnoreCase("immediate transition"))){
				nb_input_arcs=0;
				nb_output_arcs=0;
				nb_inhibitor_arcs=0;
				// Number of a transition's input arcs
				for(IArc arc : node.getIncomingArcs()){
					if((arc.getArcFormalism().getName().equalsIgnoreCase("arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("colored arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored arc")))
						nb_input_arcs++;
				}
				// Number of a transition's output arcs
				for(IArc arc : node.getOutgoingArcs()){
					if((arc.getArcFormalism().getName().equalsIgnoreCase("arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("colored arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored arc")))
						nb_output_arcs++;
				}
				// Number of a transition's inhibitors arcs
				for(IArc arc : node.getIncomingArcs()){
					if((arc.getArcFormalism().getName().equalsIgnoreCase("inhibitor arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken inhibitor arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("colored inhibitor arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored inhibitor arc")))
						nb_inhibitor_arcs++;
				}
			
				abs_node=getNodeXCoordinate(node);
				ord_node=getNodeYCoordinate(node);
			
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equalsIgnoreCase("tag")){
						abs_tag=getAttributeXCoordinate(attribute);
						ord_tag=getAttributeYCoordinate(attribute);
						tag=attribute.getValue();
					}
					if(attribute.getName().equalsIgnoreCase("rate")){
						abs_rate=getAttributeXCoordinate(attribute);
						ord_rate=getAttributeYCoordinate(attribute);
						rate=attribute.getValue();
					}
					if(attribute.getName().equalsIgnoreCase("weight")){
						abs_weight=getAttributeXCoordinate(attribute);
						ord_weight=getAttributeYCoordinate(attribute);
						weight=attribute.getValue();
					}
					if(attribute.getName().equalsIgnoreCase("priority")){
						priority=attribute.getValue();
						index_priority=tmGroup.get(priority);
					}
					if(attribute.getName().equalsIgnoreCase("definition")){
						definition="-510";
						abs_def=getAttributeXCoordinate(attribute);
						ord_def=getAttributeYCoordinate(attribute);
					}
					if(attribute.getName().equalsIgnoreCase("color label")){
						abs_color=getAttributeXCoordinate(attribute);
						ord_color=getAttributeYCoordinate(attribute);
						color=attribute.getValue();
						
						// Transition (Infinite)
						if(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Infinite)")){
							if(attribute.getValue().equalsIgnoreCase("")==false)
								toReturn.add(tag + "  " + rate + "  0  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + rate + "  0  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0");
						}
											
						// Transition (Server)
						if(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Server)")){
							if(attribute.getValue().equalsIgnoreCase("")==false)
								toReturn.add(tag + "  " + rate + "  " + priority + "  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + rate + "  " + priority + "  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0");
						}
						
						// Transition (Marking Dependent)
						if(node.getNodeFormalism().getName().equalsIgnoreCase("transition (Marking Dependent)")){
							if(attribute.getValue().equalsIgnoreCase("")==false)
								toReturn.add(tag + "  " + definition + "  1  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_def + " " + ord_def + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + definition + "  1  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_def + " " + ord_def + " 0");
						}
					
						// Immediate transition
						if(node.getNodeFormalism().getName().equalsIgnoreCase("immediate transition")){
							if(attribute.getValue().equalsIgnoreCase("")==false)
								toReturn.add(tag + "  " + weight + "  1  " + index_priority + "   " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_weight + " " + ord_weight + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + weight + "  1  " + index_priority + "   " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_weight + " " + ord_weight + " 0");
						}
					
						
						// Management of arcs
						monitor.subTask("Export arcs");
						for(IArc arc : node.getIncomingArcs()){
							id_source=arc.getSource().getId();
							index_place=hmPlace.get(id_source);
							if((arc.getArcFormalism().getName().equalsIgnoreCase("arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("colored arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored arc"))){
								for(IAttribute attribute2 : arc.getAttributes()){
									if(attribute2.getName().equalsIgnoreCase("multiplicity")){
										multiplicity=attribute2.getValue();
										if(arc.getArcFormalism().getName().equalsIgnoreCase("broken arc"))
											toReturn.add("  -" + multiplicity + " " + index_place + " 0 0");
										else											
											toReturn.add("  " + multiplicity + " " + index_place + " 0 0");
									}
									else{
										//abs_arc_color=getAttributeXCoordinate(attribute2);
										//ord_arc_color=getAttributeYCoordinate(attribute2);
										arc_color=attribute2.getValue();
										if(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored arc")){
											if(attribute2.getValue().equalsIgnoreCase("")==false)
												toReturn.add("  -1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  -1 " + index_place + " 0 0");
										}
										else{
											if(attribute2.getValue().equalsIgnoreCase("")==false)
												toReturn.add("  1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  1 " + index_place + " 0 0");
										}
									}
								}
							}
							monitor.worked(1);
						}
						toReturn.add("   " + nb_output_arcs);
						monitor.subTask("Export arcs");
						for(IArc arc : node.getOutgoingArcs()){
							id_target=arc.getTarget().getId();
							index_place=hmPlace.get(id_target);
							if((arc.getArcFormalism().getName().equalsIgnoreCase("arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("colored arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored arc"))){
								for(IAttribute attribute2 : arc.getAttributes()){
									if(attribute2.getName().equalsIgnoreCase("multiplicity")){
										multiplicity=attribute2.getValue();
										if(arc.getArcFormalism().getName().equalsIgnoreCase("broken arc"))
											toReturn.add("  -" + multiplicity + " " + index_place + " 0 0");
										else
											toReturn.add("  " + multiplicity + " " + index_place + " 0 0");
									}
									else{
										//abs_arc_color=getAttributeXCoordinate(attribute2);
										//ord_arc_color=getAttributeYCoordinate(attribute2);
										arc_color=attribute2.getValue();
										if(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored arc")){
											if(attribute2.getValue().equalsIgnoreCase("")==false)
												toReturn.add("  -1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  -1 " + index_place + " 0 0");
										}
										else{
											if(attribute2.getValue().equalsIgnoreCase("")==false)
												toReturn.add("  1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  1 " + index_place + " 0 0");
										}
									}
								}
							}
							monitor.worked(1);
						}
						toReturn.add("   " + nb_inhibitor_arcs);
						monitor.subTask("Export arcs");
						for(IArc arc : node.getIncomingArcs()){
							id_source=arc.getSource().getId();
							index_place=hmPlace.get(id_source);
							if((arc.getArcFormalism().getName().equalsIgnoreCase("inhibitor arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken inhibitor arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("colored inhibitor arc"))||(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored inhibitor arc"))){
								for(IAttribute attribute2 : arc.getAttributes()){
									if(attribute2.getName().equalsIgnoreCase("multiplicity")){
										multiplicity=attribute2.getValue();
										if(arc.getArcFormalism().getName().equalsIgnoreCase("broken inhibitor arc"))
											toReturn.add("  -" + multiplicity + " " + index_place + " 0 0");
										else
											toReturn.add("  " + multiplicity + " " + index_place + " 0 0");
									}
									else{
										//abs_arc_color=getAttributeXCoordinate(attribute2);
										//ord_arc_color=getAttributeYCoordinate(attribute2);
										arc_color=attribute2.getValue();
										if(arc.getArcFormalism().getName().equalsIgnoreCase("broken colored inhibitor arc")){
											if(attribute2.getValue().equalsIgnoreCase("")==false)
												toReturn.add("  -1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  -1 " + index_place + " 0 0");
										}
										else{
											if(attribute2.getValue().equalsIgnoreCase("")==false)
												toReturn.add("  1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  1 " + index_place + " 0 0");
										}
									}
								}
							}
							monitor.worked(1);
						}
					}
				}
			}
			monitor.worked(1);
		}
		
		return toReturn;
	}
		
		
	/**
	 * Manages X coordinate of nodes
	 * @param node The node to manage
	 * @return X coordinate of nodes
	 */
	public double getNodeXCoordinate (INode node){
		return 0.01*node.getGraphicInfo().getLocation().x;
	}

	/**
	 * Manages Y coordinate of nodes
	 * @param node The node to manage
	 * @return Y coordinate of nodes
	 */
	public double getNodeYCoordinate (INode node){
		return 0.01*node.getGraphicInfo().getLocation().y;
	}
	
	/**
	 * Manages X coordinate of attributes
	 * @param attribute The attribute to manage
	 * @return X coordinate of attributes
	 */
	public double getAttributeXCoordinate (IAttribute attribute){
		return 0.01*attribute.getGraphicInfo().getLocation().x;
	}
	
	/**
	 * Manages Y coordinate of attributes
	 * @param attribute The attribute to manage
	 * @return Y coordinate of attributes
	 */
	public double getAttributeYCoordinate (IAttribute attribute){
		return 0.01*attribute.getGraphicInfo().getLocation().y;
	}
	
}
