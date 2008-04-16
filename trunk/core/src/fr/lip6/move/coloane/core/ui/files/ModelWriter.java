package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.IInflexPoint;

/**
 * Classe regroupant les outils pour ecrire un modele sous forme XML
 */
public class ModelWriter {

	/**
	 * Retourne une chaine contenant tout le modele en XML
	 * @param model Le model sous forme d'objet JAVA
	 * @return String
	 */
	public static String translateToXML(IModelImpl modelimpl) {
		IModel model = modelimpl.getGenericModel();

		// L'entete XML
		String line = "<?xml version='1.0' encoding='UTF-8'?>\n"; //$NON-NLS-1$
		String schema = modelimpl.getFormalism().getSchema();
		
		// Ecriture des attributs relatifs au formalisme et positions
		line += "<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/" + schema + "' formalism='" + model.getFormalism() + "' xposition='" + model.getXPosition() + "' yposition='" + model.getYPosition() + "'>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		// Ecriture des attributs du modele
		if (!(model.getListOfAttrSize() == 0)) {
			line += translateAttributesToXML(model);
		}

		// Creation des noeuds
		line += "<nodes>\n"; //$NON-NLS-1$
		line += translateNodesToXML(model);
		line += "</nodes>\n"; //$NON-NLS-1$

		// Creation des arcs
		line += "<arcs>\n"; //$NON-NLS-1$
		line += translateArcsToXML(model);
		line += "</arcs>\n"; //$NON-NLS-1$

		line += "</model>"; //$NON-NLS-1$
		return line;
	}

	/**
	 * Traduction des noeuds du modele en format XML
	 * @param model Le modele en objet JAVA contenant des noeuds
	 * @return Une chaine de caracteres decrivant en XML les noeuds du modele
	 */
	private static String translateNodesToXML(IModel model) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque noeud...
		for (int i = 0; i < model.getListOfNodeSize(); i++) {
			INode node = model.getNthNode(i);

			// Debut du noeud
			line += "<node nodetype='" + node.getNodeType() + "' id='" + node.getId() + "' xposition='" + node.getXPosition() + "' yposition='" + node.getYPosition() + "'>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

			// Ecriture des attributs de chaque noeud
			if (!(node.getListOfAttr() == null)) {
				line += translateNodesAttributesToXML(node);
			}

			// Fin du noeud
			line += "</node>\n"; //$NON-NLS-1$
		}
		return line;
	}

	/**
	 * Traduction des arcs du modele en format XML
	 * @param model Le modele en objet JAVA contenant des arcs
	 * @return Une chaine de caracteres decrivant en XML les arcs du modele
	 */
	private static String translateArcsToXML(IModel model) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque arc...
		for (int i = 0; i < model.getListOfArcSize(); i++) {
			IArc arc = model.getNthArc(i);

			// Debut de l'arc
			line += "<arc arctype='" + arc.getArcType() + "' id='" + arc.getId() + "' startid='" + arc.getStartingNode().getId() + "' endid='" + arc.getEndingNode().getId() + "'>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

			// Ecriture des PI
			if (!(arc.getListOfPI().size() == 0)) {
				line += translateInflexToXML(arc);
			}

			// Ecriture des attributs de chaque arc
			if (!(arc.getListOfAttrSize() == 0)) {
				line += translateArcsAttributesToXML(arc);
			}

			// Fin de l'arc
			line += "</arc>\n"; //$NON-NLS-1$
		}
		return line;
	}

	/**
	 * Traduction des points d'inflexion des arcs du modele en format XML
	 * @param arc L'arc en objet JAVA contenant des points d'inflexion
	 * @return Une chaine de caracteres decrivant en XML les points d'inflexion des arcs du modele
	 */
	private static String translateInflexToXML(IArc arc) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque point d'inflexion...
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			IInflexPoint pi = arc.getNthPI(i);
			line += "<pi xposition='" + pi.getXPosition() + "' yposition='" + pi.getYPosition() + "'/>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return line;
	}

	/**
	 * Traduction des attributs des objets du modele en format XML
	 * @param model Le modele en objet JAVA contenant des attributs d'objet
	 * @return Une chaine de caracteres decrivant en XML les attributs du modele
	 */
	private static String translateAttributesToXML(IModel model) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque attribut...
		for (int i = 0; i < model.getListOfAttrSize(); i++) {
			IAttribute attr = model.getNthAttr(i);

			// On ne traite pas le cas des attributs qui sont vides
			if (!attr.getValue().equals("")) { //$NON-NLS-1$
				// Traitement special pour l'attribut AUTHOR
				if (attr.getName().equals("author(s)")) { //$NON-NLS-1$
					line += "<authors" + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + attr.getValue() + "</authors>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				} else {
					line += "<" + attr.getName() + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + format(attr.getValue())	+ "</" + attr.getName() + ">\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				}
			}
		}
		return line;
	}

	/**
	 * Traduction des attributs des arcs du modele en format XML
	 * @param arc L'arc en objet JAVA contenant des attributs
	 * @return Une chaine de caracteres decrivant en XML les attributs de l'arc
	 */
	private static String translateArcsAttributesToXML(IArc arc) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque attribut...
		for (int i = 0; i < arc.getListOfAttrSize(); i++) {
			IAttribute attr = arc.getNthAttr(i);
			// On ne traite pas les attributs vides
			if (!attr.getValue().equals("")) { //$NON-NLS-1$
				line += "<" + attr.getName() + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + format(attr.getValue()) + "</" + attr.getName() + ">\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			}
		}
		return line;
	}

	/**
	 * Traduction des attributs des arcs du modele en format XML
	 * @param arc L'arc en objet JAVA contenant des attributs
	 * @return Une chaine de caracteres decrivant en XML les attributs de l'arc
	 */
	private static String translateNodesAttributesToXML(INode node) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque attribut...
		for (int i = 0; i < node.getListOfAttrSize(); i++) {
			IAttribute attr = node.getNthAttr(i);
			// On ne traite pas le cas des attributs vides
			if (!attr.getValue().equals("")) { //$NON-NLS-1$
				line += "<" + attr.getName() + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + format(attr.getValue()) + "</"	+ attr.getName() + ">\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			}
		}
		return line;
	}

	/**
	 * Gestion des caracteres speciaux (protection)
	 * @param txt Le texte a proteger
	 * @return Le texte transforme et protege
	 */
	private static String format(String txt) {
		txt = txt.replaceAll("<", "&lt;"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll(">", "&gt;"); //$NON-NLS-1$ //$NON-NLS-2$
		return txt;
	}
	
	
	public static String createDefault(String formalismName) {
		// L'entete XML
		String line = "<?xml version='1.0' encoding='UTF-8'?>\n"; //$NON-NLS-1$
		String schema = FormalismManager.getFormalismByName(formalismName).getSchema();
		
		Coloane.getLogger().finer("Choix du schema de validation : "+schema); //$NON-NLS-1$
		
		// Ecriture des attributs relatifs au formalisme et positions
		line += "<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='" + schema + "' formalism='" + formalismName + "' xposition='0' yposition='0'>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		// Creation des noeuds
		line += "<nodes>\n"; //$NON-NLS-1$
		line += "</nodes>\n"; //$NON-NLS-1$

		// Creation des arcs
		line += "<arcs>\n"; //$NON-NLS-1$
		line += "</arcs>\n"; //$NON-NLS-1$

		line += "</model>"; //$NON-NLS-1$

		return line;
	}
}
