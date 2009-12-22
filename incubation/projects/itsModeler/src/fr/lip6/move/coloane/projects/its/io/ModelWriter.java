package fr.lip6.move.coloane.projects.its.io;

import fr.lip6.move.coloane.projects.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.projects.its.Concept;
import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.TypeList;
import fr.lip6.move.coloane.projects.its.expression.IEvaluationContext;
import fr.lip6.move.coloane.projects.its.expression.IVariableBinding;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to save .xmlits format files.
 */
public final class ModelWriter {
	private static Map<Object, Integer> ids = null;
	private static int nextID;

	/**
	 * No instance of utility class.
	 */
	private ModelWriter() { }
	
	/** Compute an xml string representing the TypeList for serialization purposes.
	 * @param types the types to export
	 * @return a string containing the file
	 */
	public static String translateToXML(TypeList types) {
		// Initialize ids for this file
		ids = new HashMap<Object, Integer>();
		nextID = 7000;
		// XML header
		StringBuilder line = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>\n"); //$NON-NLS-1$

		// Ecriture des attributs relatifs au formalisme et positions
		line.append("<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$
		line.append(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/fr.lip6.move.coloane.its.xsd'"); //$NON-NLS-1$
		line.append(">\n"); //$NON-NLS-1$

		// Ecriture des attributs du modele
		// line.append(translateAttributesToXML(graph));

		// Creation des types
		line.append("<types>\n"); //$NON-NLS-1$
		line.append(translateTypesToXML(types));
		line.append("</types>\n"); //$NON-NLS-1$

		// Creation des concepts
		line.append("<concepts>\n"); //$NON-NLS-1$
		line.append(translateConceptsToXML(types));
		line.append("</concepts>\n"); //$NON-NLS-1$

		// Creation of parameters
		line.append("<parameters>\n"); //$NON-NLS-1$
		line.append(translateParametersToXML(types));
		line.append("</parameters>\n"); //$NON-NLS-1$

		line.append("</model>"); //$NON-NLS-1$

		// explicit free of memory + clear
		ids = null;

		return line.toString();
	}



	/**
	 * Traduction des noeuds du modele en format XML
	 * @param types Le modele
	 * @return Une chaine de caracteres decrivant en XML les noeuds du modele
	 */
	private static String translateTypesToXML(TypeList types) {
		StringBuilder sb = new StringBuilder();
		// Pour chaque noeud...
		for (TypeDeclaration type : types) {
			int myID = nextID++;
			ids.put(type, myID);
			// Debut du noeud
			sb.append("<type name='").append(type.getTypeName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" id='").append(Integer.toString(myID)).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" formalism='").append(type.getTypeType()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" path='").append(type.getTypePath()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			// Fin du noeud
			sb.append("/>\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}
	/**
	 * Traduction des noeuds du modele en format XML
	 * @param types Le modele
	 * @return Une chaine de caracteres decrivant en XML les noeuds du modele
	 */
	private static String translateConceptsToXML(TypeList types) {
		StringBuilder sb = new StringBuilder();
		// Pour chaque noeud...
		for (TypeDeclaration type : types) {
			if (type instanceof CompositeTypeDeclaration) {
				CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) type;
				for (Concept concept : ctd.listConcepts()) {
					if (concept.getEffective() != null) {
						int myID = nextID++;
						// Debut du noeud
						sb.append("<concept name='").append(concept.getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						sb.append(" id='").append(Integer.toString(myID)).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						sb.append(" effective='").append(ids.get(concept.getEffective())).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						sb.append(" parent='").append(ids.get(ctd)).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						// Fin du noeud
						sb.append("/>\n");

					}
				}
			}
		}
		return sb.toString();
	}
	/**
	 * Traduction des noeuds du modele en format XML
	 * @param types Le modele
	 * @return Une chaine de caracteres decrivant en XML les noeuds du modele
	 */
	private static String translateParametersToXML(TypeList types) {
		StringBuilder sb = new StringBuilder();
		// Pour chaque noeud...
		for (TypeDeclaration type : types) {
			IEvaluationContext params = type.getParameters();
			if (!params.getVariables().isEmpty()) {
				for (IVariableBinding vb : params.getBindings()) {
					if (vb.getVariableValue() != null) {
						int myID = nextID++;
						// Debut du noeud
						sb.append("<parameter name='").append(vb.getVariableName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						sb.append(" id='").append(Integer.toString(myID)).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						sb.append(" parent='").append(ids.get(type)).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						sb.append(" value='").append(vb.getVariableValue()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
						// Fin du noeud
						sb.append("/>\n");
					}
				}
			}
		}
		return sb.toString();
	}

}


