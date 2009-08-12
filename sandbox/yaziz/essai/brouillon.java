import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

		monitor.subTask("Export nodes");
		for(INode node : graph.getNodes()){
			int nb_arcs=0;
			for(IArc arc : node.getIncomingArcs()){
				if((arc.getArcFormalism().getName().equals("arc"))||(arc.getArcFormalism().getName().equals("broken arc"))||(arc.getArcFormalism().getName().equals("colored arc"))||(arc.getArcFormalism().getName().equals("broken colored arc")))
					nb_arcs++;
			}
			// Management of transitions
			if(node.getNodeFormalism().getName().equals("transition (Infinite)")){
				//abs_node=node.getGraphicInfo().getLocation().x;
				//ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("rate"))
						rate=attribute.getValue();
					//abs_att=attribute.getGraphicInfo().getLocation().x;
					//ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							//abs_color=attribute.getGraphicInfo().getLocation().x;
							//ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + " " + rate + "  0  0 " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0 " + "abs_color" + " " + "ord_color" + " " + color);
						}
						else
							toReturn.add(tag + " " + rate + "  0  0 " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0 ");
					}
					
				}
				
				//for(IArc arc : graph.getArcs()){
					//for(IAttribute attribute : arc.getAttributes()){
						
					//}
				//}
			}
				
			
			if(node.getNodeFormalism().getName().equals("transition (Server)")){
				//abs_node=node.getGraphicInfo().getLocation().x;
				//ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("rate"))
						rate=attribute.getValue();
					if(attribute.getName().equals("priority"))
						priority=attribute.getValue();
					//abs_att=attribute.getGraphicInfo().getLocation().x;
					//ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							//abs_color=attribute.getGraphicInfo().getLocation().x;
							//ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + " " + rate + "  " + priority + "  0 " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0 " + "abs_color" + " " + "ord_color" + " " + color);
						}
						else
							toReturn.add(tag + " " + rate + "  " + priority + "  0 " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0");
							
					}
				}
				
				//for(IArc arc : graph.getArcs()){
					//for(IAttribute attribute : arc.getAttributes()){
						
					//}
				//}
				
			}
			
			if(node.getNodeFormalism().getName().equals("transition (Marking Dependent)")){
				//abs_node=node.getGraphicInfo().getLocation().x;
				//ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("definition"))
						definition="-510";
					//abs_att=attribute.getGraphicInfo().getLocation().x;
					//ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							//abs_color=attribute.getGraphicInfo().getLocation().x;
							//ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + " " + definition + "  1  0 " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0 " + "abs_color" + " " + "ord_color" + " " + color);
						}
						else
							toReturn.add(tag + " " + definition + "  1  0 " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0");
							
					}
				}
				
				//for(IArc arc : graph.getArcs()){
					//for(IAttribute attribute : arc.getAttributes()){
						
					//}
				//}
				
			}
			
			if(node.getNodeFormalism().getName().equals("immediate transition")){
				num_imm++;
				//abs_node=node.getGraphicInfo().getLocation().x;
				//ord_node=node.getGraphicInfo().getLocation().y;
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag"))
						tag=attribute.getValue();
					if(attribute.getName().equals("weight"))
						weight=attribute.getValue();
					//abs_att=attribute.getGraphicInfo().getLocation().x;
					//ord_att=attribute.getGraphicInfo().getLocation().y;
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							//abs_color=attribute.getGraphicInfo().getLocation().x;
							//ord_color=attribute.getGraphicInfo().getLocation().y;
							color=attribute.getValue();
							toReturn.add(tag + " " + weight + " 1  " + num_imm + " " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0 " + "abs_color" + " " + "ord_color" + " " + color);
						}
						else
							toReturn.add(tag + " " + weight + " 1  " + num_imm + " " + nb_arcs + " 0 " + "abs_node" + " " + "ord_node" + " " + "abs_att" + " " + "ord_att" + " " + "pos1 pos2 0");
					}
				}
				//for(IArc arc : graph.getArcs()){
					//for(IAttribute attribute : arc.getAttributes()){
						
					//}
				//}
				
			}
			monitor.worked(1);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		// Multiplicity of arcs
		for(IArc arc : graph.getArcs()){
			for(IAttribute attribute2 : arc.getAttributes()){
				if(attribute2.getName().equals("multiplicity")){
					multiplicity=attribute2.getValue();
					if((arc.getArcFormalism().getName().equals("broken arc"))||(arc.getArcFormalism().getName().equals("broken colored arc"))||(arc.getArcFormalism().getName().equals("broken inhibitor arc"))||(arc.getArcFormalism().getName().equals("broken colored inhibitor arc")))
						multiplicity="-"+multiplicity;
				}
			}
		}			
		
		
		
		
		
		
		
		// Management of arcs
		monitor.subTask("Export arcs");
		for(IArc arc : node.getIncomingArcs()){
			id_source=arc.getSource().getId();
			index_place=hmPlace.get(id_source);
			if((arc.getArcFormalism().getName().equals("arc"))||(arc.getArcFormalism().getName().equals("broken arc"))||(arc.getArcFormalism().getName().equals("colored arc"))||(arc.getArcFormalism().getName().equals("broken colored arc"))){
				for(IAttribute attribute2 : arc.getAttributes()){
					if(attribute2.getName().equals("multiplicity")){
						multiplicity=attribute2.getValue();
						if(arc.getArcFormalism().getName().equals("broken arc"))
							toReturn.add("  -" + multiplicity + " " + index_place + " 0 0");
						else											
							toReturn.add("  " + multiplicity + " " + index_place + " 0 0");
					}
					else{
						//abs_arc_color=attribute2.getGraphicInfo().getLocation().x;
						//ord_arc_color=attribute2.getGraphicInfo().getLocation().y;
						arc_color=attribute2.getValue();
						if(arc.getArcFormalism().getName().equals("broken colored arc")){
							if(attribute2.getValue().equals("")==false)
								toReturn.add("  -1 " + index_place + " 0 0 " + "abs_arc_color" + " " + "ord_arc_color" + " " + arc_color);
							else
								toReturn.add("  -1 " + index_place + " 0 0");
						}
						else{
							if(attribute2.getValue().equals("")==false)
								toReturn.add("  1 " + index_place + " 0 0 " + "abs_arc_color" + " " + "ord_arc_color" + " " + arc_color);
							else
								toReturn.add("  1 " + index_place + " 0 0");
						}
					}
				}
			}
			monitor.worked(1);
		}
		toReturn.add("    " + nb_output_arcs);
		monitor.subTask("Export arcs");
		for(IArc arc : node.getOutgoingArcs()){
			id_target=arc.getTarget().getId();
			index_place=hmPlace.get(id_target);
			if((arc.getArcFormalism().getName().equals("arc"))||(arc.getArcFormalism().getName().equals("broken arc"))||(arc.getArcFormalism().getName().equals("colored arc"))||(arc.getArcFormalism().getName().equals("broken colored arc"))){
				for(IAttribute attribute2 : arc.getAttributes()){
					if(attribute2.getName().equals("multiplicity")){
						multiplicity=attribute2.getValue();
						if(arc.getArcFormalism().getName().equals("broken arc"))
							toReturn.add("  -" + multiplicity + " " + index_place + " 0 0");
						else
							toReturn.add("  " + multiplicity + " " + index_place + " 0 0");
					}
					else{
						//abs_arc_color=attribute2.getGraphicInfo().getLocation().x;
						//ord_arc_color=attribute2.getGraphicInfo().getLocation().y;
						arc_color=attribute2.getValue();
						if(arc.getArcFormalism().getName().equals("broken colored arc")){
							if(attribute2.getValue().equals("")==false)
								toReturn.add("  -1 " + index_place + " 0 0 " + "abs_arc_color" + " " + "ord_arc_color" + " " + arc_color);
							else
								toReturn.add("  -1 " + index_place + " 0 0");
						}
						else{
							if(attribute2.getValue().equals("")==false)
								toReturn.add("  1 " + index_place + " 0 0 " + "abs_arc_color" + " " + "ord_arc_color" + " " + arc_color);
							else
								toReturn.add("  1 " + index_place + " 0 0");
						}
					}
				}
			}
			monitor.worked(1);
		}
		toReturn.add("    " + nb_inhibitor_arcs);
		monitor.subTask("Export arcs");
		for(IArc arc : node.getIncomingArcs()){
			id_source=arc.getSource().getId();
			index_place=hmPlace.get(id_source);
			if((arc.getArcFormalism().getName().equals("inhibitor arc"))||(arc.getArcFormalism().getName().equals("broken inhibitor arc"))||(arc.getArcFormalism().getName().equals("colored inhibitor arc"))||(arc.getArcFormalism().getName().equals("broken colored inhibitor arc"))){
				for(IAttribute attribute2 : arc.getAttributes()){
					if(attribute2.getName().equals("multiplicity")){
						multiplicity=attribute2.getValue();
						if(arc.getArcFormalism().getName().equals("broken inhibitor arc"))
							toReturn.add("  -" + multiplicity + " " + index_place + " 0 0");
						else
							toReturn.add("  " + multiplicity + " " + index_place + " 0 0");
					}
					else{
						//abs_arc_color=attribute2.getGraphicInfo().getLocation().x;
						//ord_arc_color=attribute2.getGraphicInfo().getLocation().y;
						arc_color=attribute2.getValue();
						if(arc.getArcFormalism().getName().equals("broken colored inhibitor arc")){
							if(attribute2.getValue().equals("")==false)
								toReturn.add("  -1 " + index_place + " 0 0 " + "abs_arc_color" + " " + "ord_arc_color" + " " + arc_color);
							else
								toReturn.add("  -1 " + index_place + " 0 0");
						}
						else{
							if(attribute2.getValue().equals("")==false)
								toReturn.add("  1 " + index_place + " 0 0 " + "abs_arc_color" + " " + "ord_arc_color" + " " + arc_color);
							else
								toReturn.add("  1 " + index_place + " 0 0");
						}
					}
				}
			}
			monitor.worked(1);
		}
		
		
		
		
		
		
		// Number of a transition's input arcs
		for(IArc arc : node.getIncomingArcs()){
			if((arc.getArcFormalism().getName().equals("arc"))||(arc.getArcFormalism().getName().equals("broken arc"))||(arc.getArcFormalism().getName().equals("colored arc"))||(arc.getArcFormalism().getName().equals("broken colored arc")))
				nb_input_arcs++;
		}
		// Number of a transition's output arcs
		for(IArc arc : node.getOutgoingArcs()){
			if((arc.getArcFormalism().getName().equals("arc"))||(arc.getArcFormalism().getName().equals("broken arc"))||(arc.getArcFormalism().getName().equals("colored arc"))||(arc.getArcFormalism().getName().equals("broken colored arc")))
				nb_output_arcs++;
		}
		// Number of inhibitors arcs
		for(IArc arc : node.getIncomingArcs()){
			if((arc.getArcFormalism().getName().equals("inhibitor arc"))||(arc.getArcFormalism().getName().equals("broken inhibitor arc"))||(arc.getArcFormalism().getName().equals("colored inhibitor arc"))||(arc.getArcFormalism().getName().equals("broken colored inhibitor arc")))
				nb_inhibitor_arcs++;
		}
		
		
		
		
		
		
		
		
		for(IArc arc : graph.getArcs()){
			if(arc.getSource().equals("place")){
				id_source=arc.getSource().getId();
				index_place=hmPlace.get(id_source);
			}
			if(arc.getTarget().equals("place")){
				id_target=arc.getTarget().getId();
				index_place=hmPlace.get(id_target);
			}
		}
		
		
		
		
		
		
		for(IArc arc : graph.getArcs()){
			if(arc.getSource().equals("place")){
				id_source=arc.getSource().getId();
				for(Integer i: hmPlace.keySet())
					index_place=hmPlace.get(i);
			}
			if(arc.getTarget().equals("place")){
				id_target=arc.getTarget().getId();
				for(Integer i: hmPlace.keySet())
					index_place=hmPlace.get(i);
			}
		}
		
		
		
		
		
		
		
		
		
		// Color classes and static subclasses
		for (IAttribute attribute : graph.getAttributes()) {
			lettre='c';
			if((attribute.getValue().equals("")==false)){
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
							/*
							Map<String, String> hmColor = new HashMap<String, String>();
							hmColor.put(label, description);
							for(String s: hmColor.keySet()){
								toReturn.add("(" + s + " " + lettre + " " + attribute.getGraphicInfo().getLocation().x + " " + attribute.getGraphicInfo().getLocation().y + " (@" + lettre);
								toReturn.add(hmColor.get(s));
								toReturn.add("))");
								}
							*/
						}
						toReturn.add("(" + label + " " + lettre + " " + attribute.getGraphicInfo().getLocation().x + " " + attribute.getGraphicInfo().getLocation().y + " (@" + lettre);
						toReturn.add(description);
						toReturn.add("))");
					}
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		private Collection<String> noobjs(IGraph graph, IProgressMonitor monitor){
			List<String> toReturn = new ArrayList<String>();
			Map<String, Integer> hmNbGrp = new HashMap<String, Integer>();
			
			monitor.subTask("Export nodes");
			for (INode node : graph.getNodes()) {
				if(node.getNodeFormalism().getName().equals("place"))
					nb_places++;
				if((node.getNodeFormalism().getName().equals("immediate transition"))||(node.getNodeFormalism().getName().equals("transition (Infinite)"))||(node.getNodeFormalism().getName().equals("transition (Marking Dependent)"))||(node.getNodeFormalism().getName().equals("transition (Server)")))
					nb_transitions++;
				if(node.getNodeFormalism().getName().equals("immediate transition")){
					for(IAttribute attribute : node.getAttributes()){
						if(attribute.getName().equals("priority")){
							priority=attribute.getValue();
							Integer i = hmNbGrp.get(priority);
							if(i == null)
								hmNbGrp.put(priority, 1);
							else
								hmNbGrp.put(priority, i+1);
						}
					}
				}
				monitor.worked(1);
			}
			for(String s: hmNbGrp.keySet()){
				nb_grp+=hmNbGrp.get(s);
				}
			toReturn.add("f   0   " + nb_places + "   0   " + nb_transitions + "   " + nb_grp + "   0   0");
			
			return toReturn;
		}
		
		
		
		
		
		
		
		private Collection<String> groups(IGraph graph, IProgressMonitor monitor){
			List<String> toReturn = new ArrayList<String>();
			Map<String, String> hmGroup = new HashMap<String, String>();
			
			monitor.subTask("Export nodes");
			for(INode node : graph.getNodes()){
				if(node.getNodeFormalism().getName().equals("immediate transition")){
					for(IAttribute attribute : node.getAttributes()){
						if(attribute.getName().equals("priority")){
							priority=attribute.getValue();
							hmGroup.put(priority, "G" + MINGRP);
							MINGRP++;
							
							if(attribute.getValue().equals(attribute.getAttributeFormalism().getDefaultValue())){
								abs_node=getNodeXCoordinate(node);
								ord_node=getNodeYCoordinate(node);
							}
							else{
								abs_node=0;
								ord_node=0;
							}	
											
						}
					}
				}
				monitor.worked(1);
			}
			
			for(String s: hmGroup.keySet())
				toReturn.add(hmGroup.get(s) + " " + abs_node + " " + ord_node + " " + s);
			
			return toReturn;
		}
		
		
		
		
		
		
		
		