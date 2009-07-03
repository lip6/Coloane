package io;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import its.CompositeTypeDeclaration;
import its.Concept;
import its.TypeDeclaration;
import its.TypeList;


/** This class handles all that is necessary to convert a given **resolved** ITS type
 * to the input format used by SDD-ITS (the C++ model-checking component).
 * Essentially responsibility is:
 * * find all dependent types + sort them to have an easy declaration order.
 * * Replace any concepts or parameters of the type with their resolved values
 * * then export each type in a separate file
 * * create the ITSModel file (=> a list of types)
 * * add the "main instance" line
 * 
 * @author Yann
 *
 */
public class ITSModelWriter {

	
	public void exportITSModel (TypeList types, TypeDeclaration type, String directory) {
		// the types which need to be declared.
		
		List<TypeDeclaration> toProcess = new ArrayList<TypeDeclaration>();
		getDependentTypes(types, type, toProcess);
		
		for (TypeDeclaration td : toProcess) {
			// composite case
			if (td instanceof CompositeTypeDeclaration) {
				CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) td;
				assert ctd.isSatisfied();
				IGraph satGraph = new GraphModelFactory().copyGraph(ctd.getGraph()); 

				// replace the instance types by their corresponding concept name
				for (INode node : satGraph.getNodes()) { 
					if ("instance".equals(node.getNodeFormalism().getName())) {
						IAttribute instType = node.getAttribute("type");					
						instType.setValue(ctd.getConcept(instType.getValue()).getEffective().getTypeName());
					}
				}
			} else {
				// Basic case
				// currently no parameters to instantiate
				
			}
		}
	}

	/**
	 * Construct the list of types that need to be exported, in a correct order.
	 * @param types
	 * @param type
	 * @param todo
	 */
	private void getDependentTypes(TypeList types, TypeDeclaration type, List<TypeDeclaration> todo) {
		
		if (type instanceof CompositeTypeDeclaration) {
			CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) type;
			for (Concept c : ctd.listConcepts()) {
				if (! todo.contains(c.getEffective())) {
					getDependentTypes(types, c.getEffective(), todo);
				}
			}			
		}
		todo.add(type);
	}
	
}
