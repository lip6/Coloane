package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IElement;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.swt.graphics.Color;

/**
 * Classe regroupant les outils pour ecrire un modele sous forme XML
 */
public final class ModelWriter {

	private ModelWriter() {	}

	/**
	 * Retourne une chaine contenant tout le modele en XML
	 * @param model Le model sous forme d'objet JAVA
	 * @return String
	 */
	public static String translateToXML(IModelImpl model) {

		// L'entete XML
		String line = "<?xml version='1.0' encoding='UTF-8'?>\n"; //$NON-NLS-1$
		String schema = model.getFormalism().getSchema();

		// Ecriture des attributs relatifs au formalisme et positions
		line += "<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/" + schema + "' formalism='" + model.getFormalism() + "' xposition='0' yposition='0'>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		// Ecriture des attributs du modele
		if (model.getAttributes().size() > 0) {
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
	 * Convert a Color {@link Color} into a String of type "(R,G,B)"
	 * @param color The SWT Color object
	 * @return A string that can be dump into a XML reprensentation
	 */
	private static String color2String(Color color) {
		return "(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
	}

	/**
	 * Traduction des noeuds du modele en format XML
	 * @param model Le modele
	 * @return Une chaine de caracteres decrivant en XML les noeuds du modele
	 */
	private static String translateNodesToXML(IModelImpl model) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque noeud...
		for (INodeImpl node : model.getNodes()) {

			// Debut du noeud
			line += "<node nodetype='" + node.getGenericNode().getNodeType() + "'";	//$NON-NLS-1$ //$NON-NLS-2$
			line += " id='" + node.getId() + "'";									//$NON-NLS-1$ //$NON-NLS-2$
			line += " xposition='" + node.getGraphicInfo().getLocation().x + "'";	//$NON-NLS-1$ //$NON-NLS-2$
			line +=	" yposition='" + node.getGraphicInfo().getLocation().x + "'";	//$NON-NLS-1$ //$NON-NLS-2$
			line += " scale='" + node.getGraphicInfo().getScale() + "'";		//$NON-NLS-1$ //$NON-NLS-2$
			line += " foregroundcolor='" + color2String(node.getGraphicInfo().getForeground()) + "'";		//$NON-NLS-1$ //$NON-NLS-2$
			line += " backgroundcolor='" + color2String(node.getGraphicInfo().getBackground()) + "'>\n";	//$NON-NLS-1$ //$NON-NLS-2$

			// Ecriture des attributs de chaque noeud
			if (node.getAttributes().size() > 0) {
				line += translateAttributesToXML(node);
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
	private static String translateArcsToXML(IModelImpl model) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque arc...
		for (IArcImpl arc : model.getArcs()) {

			// Debut de l'arc
			line += "<arc arctype='" + arc.getGenericArc().getArcType() + "'";		//$NON-NLS-1$ //$NON-NLS-2$
			line += " id='" + arc.getId() + "'";									//$NON-NLS-1$ //$NON-NLS-2$
			line += " startid='" + arc.getSource().getId() + "'";					//$NON-NLS-1$ //$NON-NLS-2$
			line += " endid='" + arc.getTarget().getId() + "'";						//$NON-NLS-1$ //$NON-NLS-2$
			line += " foreground='" + arc.getGraphicInfo().getColor() + "'";		//$NON-NLS-1$ //$NON-NLS-2$
			line += ">\n"; 															//$NON-NLS-1$ //$NON-NLS-2$

			// Ecriture des PI
			if (arc.getInflexPoints().size() > 0) {
				line += translateInflexToXML(arc);
			}

			// Ecriture des attributs de chaque arc
			if (arc.getAttributes().size() > 0) {
				line += translateAttributesToXML(arc);
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
	private static String translateInflexToXML(IArcImpl arc) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque point d'inflexion...
		for (Bendpoint inflex : arc.getInflexPoints()) {
			line += "<pi xposition='" + inflex.getLocation().x + "' yposition='" + inflex.getLocation().y + "'/>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return line;
	}

	/**
	 * Traduction des attributs des objets du modele en format XML
	 * @param model Le modele en objet JAVA contenant des attributs d'objet
	 * @return Une chaine de caracteres decrivant en XML les attributs du modele
	 */
	private static String translateAttributesToXML(IElement elt) {
		String line = ""; //$NON-NLS-1$

		// Pour chaque attribut...
		for (IAttributeImpl att : elt.getAttributes()) {

			// On ne traite pas le cas des attributs qui sont vides
			if (att.getValue().equals("")) { //$NON-NLS-1$
				// Traitement special pour l'attribut AUTHOR
				if (att.getDisplayName().equals("author(s)")) { //$NON-NLS-1$
					line += "<authors" + " xposition='" + att.getGraphicInfo().getLocation().x + "' yposition='" + att.getGraphicInfo().getLocation().y + "'>" + att.getValue() + "</authors>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				} else {
					line += "<" + att.getDisplayName() + " xposition='" + att.getGraphicInfo().getLocation().x + "' yposition='" + att.getGraphicInfo().getLocation().y + "'>" + format(att.getValue())	+ "</" + att.getDisplayName() + ">\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				}
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
		String schema = Coloane.getDefault().getMotor().getFormalismManager().getFormalismByName(formalismName).getSchema();

		Coloane.getLogger().finer("Choix du schema de validation : " + schema); //$NON-NLS-1$

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
