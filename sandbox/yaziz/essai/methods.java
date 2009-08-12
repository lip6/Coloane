	/**
	 * Translate a graph into GSPN commands
	 * @param graph The graph to translate
	 * @param monitor The monitor to follow the progression
	 * @return A collection of GSPN commands
	 */
	private Collection<String> translateGraph(IGraph graph, IProgressMonitor monitor) {
		List<String> toReturn = new ArrayList<String>();
		char lettre='\0';
		String label="", description="";
		Map<String, Integer> hmMarking = new HashMap<String, Integer>();
		int index2=0;
		
		
		/** .DEF **/
		
		// Management of transition (Marking Dependent)'s definition
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			for(IAttribute attribute : node.getAttributes()){
				if(attribute.getName().equals("definition")){
					toReturn.add("|"+ MINMKD);
					toReturn.add(attribute.getValue());
					MINMKD++;
				}
			}
			monitor.worked(1);
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
						toReturn.add("(" + lettre + node.getId() + " " + lettre + " " + getAttributeXCoordinate(attribute) + " " + getAttributeYCoordinate(attribute) + " " + "(@" + lettre);
						toReturn.add(attribute.getValue());
						toReturn.add("))");
						index2++;
						hmMarking.put(attribute.getValue(), index2);
					}
				}
			}
			monitor.worked(1);
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
						}
						toReturn.add("(" + label + " " + lettre + " " + getAttributeXCoordinate(attribute) + " " + getAttributeYCoordinate(attribute) + " (@" + lettre);
						toReturn.add(description);
						toReturn.add("))");
					}
				}
			}
		}
							
		toReturn.add("\n\n\n");
		
		
		
		
		
		
		/** .NET **/
		
		int nb_places=0, nb_transitions=0, nb_imm=0, num_imm=0;
		String tag="", color="", rate="", definition="", priority="", marking="", weight="", multiplicity="", arc_color="";
		double abs_node, ord_node, abs_tag=0, ord_tag=0, abs_rate=0, ord_rate=0, abs_weight=0, ord_weight=0, abs_def=0, ord_def=0, abs_color, ord_color;
		//int abs_arc_color, ord_arc_color;
		int index=0;
		Map<Integer, Integer> hmPlace = new HashMap<Integer, Integer>();
		String value="";
		Integer index_marking;
				
		// Introduction
		toReturn.add("|0|");
		toReturn.add("|");
		
		// Noobjs
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()) {
			if(node.getNodeFormalism().getName().equals("place"))
				nb_places++;
			if((node.getNodeFormalism().getName().equals("immediate transition"))||(node.getNodeFormalism().getName().equals("transition (Infinite)"))||(node.getNodeFormalism().getName().equals("transition (Marking Dependent)"))||(node.getNodeFormalism().getName().equals("transition (Server)")))
				nb_transitions++;
			if(node.getNodeFormalism().getName().equals("immediate transition"))
				nb_imm++;
			monitor.worked(1);
		}
		toReturn.add("f   0   " + nb_places + "   0   " + nb_transitions + "   " + nb_imm + "   0   0");
		
		
		
		// Management of places
		monitor.subTask("Export nodes");
		for (INode node : graph.getNodes()){
			if(node.getNodeFormalism().getName().equals("place")){
				abs_node=getNodeXCoordinate(node);
				ord_node=getNodeYCoordinate(node);
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag")){
						abs_tag=getAttributeXCoordinate(attribute);
						ord_tag=getAttributeYCoordinate(attribute);
						tag=attribute.getValue();
					}
					if(attribute.getName().equals("marking")){
						if((attribute.getValue().equals(attribute.getAttributeFormalism().getDefaultValue()))==false){
							value=attribute.getValue();
							index_marking=hmMarking.get(value);
							marking="-1000" + index_marking;
						}
						else
							marking="0";
					}
					if(attribute.getName().equals("color label")){
						if(attribute.getValue().equals("")==false){
							abs_color=getAttributeXCoordinate(attribute);
							ord_color=getAttributeYCoordinate(attribute);
							color=attribute.getValue();
							toReturn.add(tag + "    " + marking + " " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " 0 " + abs_color + " " + ord_color + " " + color);
						}
						else
							toReturn.add(tag + "    " + marking + " " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " 0 ");	
					}
				}
				index++;
				hmPlace.put(node.getId(), index);
			}
			monitor.worked(1);
		}
		
		
		
		
		// Management of groups
		monitor.subTask("Export nodes");
		for(INode node : graph.getNodes()){
			// Management of groups
			if(node.getNodeFormalism().getName().equals("immediate transition")){
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("priority")){
						priority=attribute.getValue();
						toReturn.add("G" + MINGRP + "  0 0 " + priority);
						MINGRP++;
					}
				}
			}
			monitor.worked(1);
		}
		
		
		
		// Management of transitions
		int id_source, id_target;
		Integer index_place;
		
		monitor.subTask("Export nodes");
		for(INode node : graph.getNodes()){
			if((node.getNodeFormalism().getName().equals("transition (Infinite)"))||(node.getNodeFormalism().getName().equals("transition (Server)"))||(node.getNodeFormalism().getName().equals("transition (Marking Dependent)"))||(node.getNodeFormalism().getName().equals("immediate transition"))){
				int nb_input_arcs=0, nb_output_arcs=0, nb_inhibitor_arcs=0;
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
				// Number of a transition's inhibitors arcs
				for(IArc arc : node.getIncomingArcs()){
					if((arc.getArcFormalism().getName().equals("inhibitor arc"))||(arc.getArcFormalism().getName().equals("broken inhibitor arc"))||(arc.getArcFormalism().getName().equals("colored inhibitor arc"))||(arc.getArcFormalism().getName().equals("broken colored inhibitor arc")))
						nb_inhibitor_arcs++;
				}
			
				abs_node=getNodeXCoordinate(node);
				ord_node=getNodeYCoordinate(node);
			
				for(IAttribute attribute : node.getAttributes()){
					if(attribute.getName().equals("tag")){
						abs_tag=getAttributeXCoordinate(attribute);
						ord_tag=getAttributeYCoordinate(attribute);
						tag=attribute.getValue();
					}
					if(attribute.getName().equals("rate")){
						abs_rate=getAttributeXCoordinate(attribute);
						ord_rate=getAttributeYCoordinate(attribute);
						rate=attribute.getValue();
					}
					if(attribute.getName().equals("weight")){
						abs_weight=getAttributeXCoordinate(attribute);
						ord_weight=getAttributeYCoordinate(attribute);
						weight=attribute.getValue();
					}
					if(attribute.getName().equals("priority"))
						priority=attribute.getValue();
					if(attribute.getName().equals("definition")){
						definition="-510";
						abs_def=getAttributeXCoordinate(attribute);
						ord_def=getAttributeYCoordinate(attribute);
					}
					if(attribute.getName().equals("color label")){
						abs_color=getAttributeXCoordinate(attribute);
						ord_color=getAttributeYCoordinate(attribute);
						color=attribute.getValue();
						
						// Transition (Infinite)
						if(node.getNodeFormalism().getName().equals("transition (Infinite)")){
							if(attribute.getValue().equals("")==false)
								toReturn.add(tag + "  " + rate + "  0  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + rate + "  0  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0 ");						

						}
											
						// Transition (Server)
						if(node.getNodeFormalism().getName().equals("transition (Server)")){
							if(attribute.getValue().equals("")==false)
								toReturn.add(tag + "  " + rate + "  " + priority + "  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + rate + "  " + priority + "  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_rate + " " + ord_rate + " 0 ");
						}
						
						// Transition (Marking Dependent)
						if(node.getNodeFormalism().getName().equals("transition (Marking Dependent)")){
							if(attribute.getValue().equals("")==false)
								toReturn.add(tag + "  " + definition + "  1  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_def + " " + ord_def + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + definition + "  1  0  " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_def + " " + ord_def + " 0 ");
						}
					
						// Immediate transition
						if(node.getNodeFormalism().getName().equals("immediate transition")){
							num_imm++;
							if(attribute.getValue().equals("")==false)
								toReturn.add(tag + "  " + weight + "  1  " + num_imm + "   " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_weight + " " + ord_weight + " 0 " + abs_color + " " + ord_color + " " + color);
							else
								toReturn.add(tag + "  " + weight + "  1  " + num_imm + "   " + nb_input_arcs + " 0 " + abs_node + " " + ord_node + " " + abs_tag + " " + ord_tag + " " + abs_weight + " " + ord_weight + " 0 ");
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
										//abs_arc_color=getAttributeXCoordinate(attribute2);
										//ord_arc_color=getAttributeYCoordinate(attribute2);
										arc_color=attribute2.getValue();
										if(arc.getArcFormalism().getName().equals("broken colored arc")){
											if(attribute2.getValue().equals("")==false)
												toReturn.add("  -1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  -1 " + index_place + " 0 0");
										}
										else{
											if(attribute2.getValue().equals("")==false)
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
										//abs_arc_color=getAttributeXCoordinate(attribute2);
										//ord_arc_color=getAttributeYCoordinate(attribute2);
										arc_color=attribute2.getValue();
										if(arc.getArcFormalism().getName().equals("broken colored arc")){
											if(attribute2.getValue().equals("")==false)
												toReturn.add("  -1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  -1 " + index_place + " 0 0");
										}
										else{
											if(attribute2.getValue().equals("")==false)
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
										//abs_arc_color=getAttributeXCoordinate(attribute2);
										//ord_arc_color=getAttributeYCoordinate(attribute2);
										arc_color=attribute2.getValue();
										if(arc.getArcFormalism().getName().equals("broken colored inhibitor arc")){
											if(attribute2.getValue().equals("")==false)
												toReturn.add("  -1 " + index_place + " 0 0 " + "0" + " " + "0" + " " + arc_color);
											else
												toReturn.add("  -1 " + index_place + " 0 0");
										}
										else{
											if(attribute2.getValue().equals("")==false)
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