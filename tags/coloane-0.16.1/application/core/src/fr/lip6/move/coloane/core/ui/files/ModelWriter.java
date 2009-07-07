package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.swt.graphics.Color;

/**
 * Classe regroupant les outils pour ecrire un modele sous forme XML
 */
public final class ModelWriter {
	/**
	 * Classe ne contenant que des méthodes statique.
	 */
	private ModelWriter() {	}

	/**
	 * Retourne une chaine contenant tout le modele en XML
	 * @param graph Le model sous forme d'objet JAVA
	 * @return String
	 */
	public static String translateToXML(IGraph graph) {

		// L'entete XML
		StringBuilder line = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>\n"); //$NON-NLS-1$

		// Ecriture des attributs relatifs au formalisme et positions
		line.append("<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$
		line.append(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/model.xsd'"); //$NON-NLS-1$
		line.append(" formalism='").append(graph.getFormalism()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
		line.append(" xposition='0' yposition='0'>\n"); //$NON-NLS-1$

		// Ecriture des attributs du modele
		line.append(translateAttributesToXML(graph));

		// Creation des noeuds
		line.append("<nodes>\n"); //$NON-NLS-1$
		line.append(translateNodesToXML(graph));
		line.append("</nodes>\n"); //$NON-NLS-1$

		// Creation des arcs
		line.append("<arcs>\n"); //$NON-NLS-1$
		line.append(translateArcsToXML(graph));
		line.append("</arcs>\n"); //$NON-NLS-1$

		// Création des noeuds
		line.append("<stickys>\n"); //$NON-NLS-1$
		line.append(translateStickyNotesToXML(graph));
		line.append("</stickys>\n"); //$NON-NLS-1$

		line.append("</model>"); //$NON-NLS-1$
		return line.toString();
	}

	/**
	 * Convert a Color {@link Color} into a String of type "#RGB"
	 * @param color The SWT Color object
	 * @return A string that can be dump into a XML reprensentation
	 */
	private static String color2String(Color color) {
		String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());
		if (red.length() == 1) {
			red = "0" + red; //$NON-NLS-1$
		}
		if (green.length() == 1) {
			green = "0" + green; //$NON-NLS-1$
		}
		if (blue.length() == 1) {
			blue = "0" + blue; //$NON-NLS-1$
		}
		return "#" + red + green + blue; //$NON-NLS-1$
	}

	/**
	 * Traduction des noeuds du modele en format XML
	 * @param graph Le modele
	 * @return Une chaine de caracteres decrivant en XML les noeuds du modele
	 */
	private static String translateNodesToXML(IGraph graph) {
		StringBuilder sb = new StringBuilder();

		// Pour chaque noeud...
		for (INode node : graph.getNodes()) {

			// Debut du noeud
			sb.append("<node nodetype='").append(node.getNodeFormalism().getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" id='").append(node.getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" xposition='").append(node.getGraphicInfo().getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" yposition='").append(node.getGraphicInfo().getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" scale='").append(node.getGraphicInfo().getScale()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" alt='").append(node.getGraphicInfo().getGdIndex()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" foreground='").append(color2String(node.getGraphicInfo().getForeground())).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" background='").append(color2String(node.getGraphicInfo().getBackground())).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$

			// Ecriture des attributs de chaque noeud
			sb.append(translateAttributesToXML(node));

			// Fin du noeud
			sb.append("</node>\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	/**
	 * @param graph Le modèle
	 * @return Une chaine de caractères décrivant en XML les notes.
	 */
	private static String translateStickyNotesToXML(IGraph graph) {
		StringBuilder sb = new StringBuilder();

		// Pour chaque note...
		for (IStickyNote note : ((GraphModel) graph).getStickyNotes()) {

			// Début de la note
			sb.append("<sticky"); //$NON-NLS-1$
			sb.append(" xposition='").append(note.getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" yposition='").append(note.getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" width='").append(note.getSize().width).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" height='").append(note.getSize().height).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$

			// Ecriture de la valeur de la note
			sb.append("<value>").append(format(note.getLabelContents())).append("</value>\n");  //$NON-NLS-1$//$NON-NLS-2$

			// Ajout des liens
			for (ILink link : note.getLinks()) {
				if (link.getElement() instanceof IElement) {
					int linkId = ((IElement) link.getElement()).getId();
					sb.append("<link linkId='").append(linkId).append("' />\n"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}

			// Fin de la note
			sb.append("</sticky>\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	/**
	 * Traduction des arcs du modele en format XML
	 * @param graph Le modele en objet JAVA contenant des arcs
	 * @return Une chaine de caracteres decrivant en XML les arcs du modele
	 */
	private static String translateArcsToXML(IGraph graph) {
		StringBuilder sb = new StringBuilder();

		// Pour chaque arc...
		for (IArc arc : graph.getArcs()) {

			// Debut de l'arc
			sb.append("<arc arctype='").append(arc.getArcFormalism().getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" id='").append(arc.getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" startid='").append(arc.getSource().getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" endid='").append(arc.getTarget().getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" color='").append(color2String(arc.getGraphicInfo().getColor())).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" curved='").append(arc.getGraphicInfo().getCurve()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(">\n"); //$NON-NLS-1$

			// Ecriture des PI
			sb.append(translateInflexToXML(arc));

			// Ecriture des attributs de chaque arc
			sb.append(translateAttributesToXML(arc));

			// Fin de l'arc
			sb.append("</arc>\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	/**
	 * Traduction des points d'inflexion des arcs du modele en format XML
	 * @param arc L'arc en objet JAVA contenant des points d'inflexion
	 * @return Une chaine de caracteres decrivant en XML les points d'inflexion des arcs du modele
	 */
	private static String translateInflexToXML(IArc arc) {
		StringBuilder sb = new StringBuilder();

		// Pour chaque point d'inflexion...
		for (Bendpoint inflex : arc.getInflexPoints()) {
			sb.append("<pi"); //$NON-NLS-1$
			sb.append(" xposition='").append(inflex.getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" yposition='").append(inflex.getLocation().y).append("'/>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return sb.toString();
	}

	/**
	 * Traduction des attributs d'un élement du modèle en format XML
	 * @param elt IElement
	 * @return Une chaine de caracteres decrivant en XML les attributs de l'élement passé en parametre
	 */
	private static String translateAttributesToXML(IElement elt) {
		StringBuilder sb = new StringBuilder();

		sb.append("<attributes>\n"); //$NON-NLS-1$

		// Pour chaque attribut...
		for (IAttribute att : elt.getAttributes()) {

			// On ne traite pas le cas des attributs qui sont vides
			if (!att.getValue().equals("")) { //$NON-NLS-1$
				String balise = att.getName();
				sb.append("<attribute name='").append(balise).append("'");  //$NON-NLS-1$//$NON-NLS-2$
				sb.append(" xposition='").append(att.getGraphicInfo().getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
				sb.append(" yposition='").append(att.getGraphicInfo().getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
				sb.append(">"); //$NON-NLS-1$

				sb.append(format(att.getValue()));

				sb.append("</attribute>\n"); //$NON-NLS-1$
			}
		}

		sb.append("</attributes>\n"); //$NON-NLS-1$

		return sb.toString();
	}

	/**
	 * Gestion des caracteres speciaux (protection)
	 * @param txt Le texte a proteger
	 * @return Le texte transforme et protege
	 */
	private static String format(String txt) {
		String protectedTxt = txt;
		protectedTxt = protectedTxt.replaceAll("&", "&amp;"); //$NON-NLS-1$ //$NON-NLS-2$
		protectedTxt = protectedTxt.replaceAll("<", "&lt;"); //$NON-NLS-1$ //$NON-NLS-2$
		protectedTxt = protectedTxt.replaceAll(">", "&gt;"); //$NON-NLS-1$ //$NON-NLS-2$
		return protectedTxt;
	}


	/**
	 * Création d'une chaine XML représentant un modèle vide
	 * @param formalismName Nom du formalisme
	 * @return représentation XML
	 */
	public static String createDefault(String formalismName) {
		// L'entete XML
		StringBuilder line = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>\n"); //$NON-NLS-1$

		// Ecriture des attributs relatifs au formalisme et positions
		line.append("<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$
		line.append(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/model.xsd'"); //$NON-NLS-1$
		line.append(" formalism='").append(formalismName).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
		line.append(">\n"); //$NON-NLS-1$

		// Creation des noeuds
		line.append("<nodes>\n"); //$NON-NLS-1$
		line.append("</nodes>\n"); //$NON-NLS-1$

		// Creation des arcs
		line.append("<arcs>\n"); //$NON-NLS-1$
		line.append("</arcs>\n"); //$NON-NLS-1$

		line.append("</model>"); //$NON-NLS-1$

		return line.toString();
	}
}
