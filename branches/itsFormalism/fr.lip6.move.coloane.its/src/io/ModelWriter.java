package io;

import its.CompositeTypeDeclaration;
import its.Concept;
import its.TypeDeclaration;
import its.TypeList;

import java.util.HashMap;

/**
 * Classe regroupant les outils pour ecrire un modele sous forme XML
 */
public final class ModelWriter {
	private static HashMap<Object, Integer> ids=null;
	private static int nextID;


	/** Compute an xml string representing the TypeList for serialization purposes.
	 */
	public static String translateToXML(TypeList types) {
		// Initialize ids for this file
		ids = new HashMap<Object,Integer>();
		nextID = 7000;
		// L'entete XML
		StringBuilder line = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>\n"); //$NON-NLS-1$

		// Ecriture des attributs relatifs au formalisme et positions
		line.append("<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$
		line.append(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/its.xsd'"); //$NON-NLS-1$
		line.append(">\n"); //$NON-NLS-1$

		// Ecriture des attributs du modele
		// line.append(translateAttributesToXML(graph));

		// Creation des types
		line.append("<types>\n"); //$NON-NLS-1$
		line.append(translateTypesToXML(types));
		line.append("</types>\n"); //$NON-NLS-1$

		// Creation des cocepts
		line.append("<concepts>\n"); //$NON-NLS-1$
		line.append(translateConceptsToXML(types));
		line.append("</concepts>\n"); //$NON-NLS-1$

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
			ids.put(type,myID);
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
}
