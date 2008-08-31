package fr.lip6.move.coloane.extensions.exportToPGF;

import java.util.Collection;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * @author Alban Linard
 */
public class PGFTranslator {

	/**
	 * Creer un tableau de string correspondant au contenu du fichier pgf, pour
	 * le model passer en parametres.
	 * @param model Model du fichier à exporter. 
	 * @return Le contenu du fichier pgf correspondant au model.
	 */
	public final String translateModel(IGraph model) {
		StringBuffer r = new StringBuffer();
		
		// Debut du document
		r.append("\\begin{tikzpicture}\n");
		
		// La liste des styles :
		r.append("[");
		r.append("place/.style={circle,draw=black,thick,minimum size=.5cm},");
		r.append("queue/.style={circle,draw=black,thick,fill=blue!50,minimum size=.5cm},");
		r.append("transition/.style={rectangle,draw=black,thick,minimum size=.5cm},");
		r.append("immediate transition/.style={rectangle,draw=black,thick,fill=black,minimum size=.5cm},");
		r.append("arc/.style={-latex,draw=black},");
		r.append("inhibitor arc/.style={-o,draw=black}");
		r.append("]");

		// Traduction du modèle
		r.append(translate(model));
		
		// Fin du document
		r.append("\\end{tikzpicture}\n\n");
		
		return r.toString();
	}
	
	
	/**
	 * Traduction d'un modèle RdP en PGF
	 * @param model Le modèle à traduire
	 * @return Une chaine de caractères contenant les commandes PGF
	 */
	private final String translate(IGraph model){
		StringBuffer r = new StringBuffer();
		r.append(translateAttributes(model));
		
		// Tous les noeuds du modèle doivent être traduits
		r.append(translateNodes(model.getNodes()));
		// Tous les arcs doivent subir le même traitement
		r.append(translateArcs(model.getArcs()));
		
		return r.toString();
	}
	
	/**
	 * Traduction d'un groupe de noeuds du modèle en commandes PGF
	 * @param nodes L'ensemble de noeuds à traduire
	 * @return Une chaine de caractères contenant les commandes PGF
	 */
	private final String translateNodes(Collection<INode> nodes) {
		StringBuffer r = new StringBuffer();
		for (INode node : nodes) {
			r.append("\\node[" + node.getNodeFormalism().getName() + "]");
			r.append(" (" + node.getId() + ")");
			r.append(" at (" + node.getGraphicInfo().getLocation().x * 0.05 + ", " + node.getGraphicInfo().getLocation().y * 0.05 + ")");
			r.append(" ").append(this.translateAttributes(node));
			r.append(" {};\n");
		}
		return r.toString();
	}
	
	/**
	 * Traduction d'un groupe d'arcs du modèle en commandes PGF
	 * @param nodes L'ensemble des arcs à traduire
	 * @return Une chaine de caractères contenant les commandes PGF
	 */
	private final String translateArcs(Collection<IArc> arcs) {
		StringBuffer r = new StringBuffer();
		for (IArc arc : arcs){
			r.append("\\draw[" + arc.getArcFormalism().getName() + "]");
			r.append(" (" + arc.getSource().getId() + ")");
			r.append(" ").append(this.translateAttributes(arc));
			r.append(" (" + arc.getTarget().getId() + ");\n");
		}
		return r.toString();
	}
	
	/**
	 * Traduction des attributs d'un élement de modèle
	 * @param element L'élément de modèle dont les attributs doivent être traduits
	 * @return La chaine de caractères contenant les commandes PGF
	 */
	private final String translateAttributes(IElement element){
		StringBuffer r = new StringBuffer();
		for (IAttribute attribute : element.getAttributes()){
			r.append(translateAttribute(attribute.getName(), attribute.getValue())).append("\n");
		}
		return r.toString();
	}
	
	
	/**
	 * Traduit une valeur d'attribut selon son type (name)
	 * @param name Le type de l'attribut
	 * @param value La valeur de l'attribut
	 * @return La chaine de caractère correspondant à la traduction
	 */
	private final String translateAttribute(String name, String value) {		
		// Dans le cas d'un attribut de nom
		if ("name".equals(name)) {
			return " [label=below:" + name + "]";
		}
		
		// Dans le cas d'un attribut de marquage
		// On cherche à savoir si le marquage est entier (PN) ou pas (CPN)
		if ("marking".equals(name)) {
			try {
				Integer.valueOf(value);
				return " [tokens=" + value + "]";
			} catch (NumberFormatException e) {
				return " [label=above:" + value + "]";
			}
		}
		
		// Dans le cas d'une guarde
		if ("guard".equals(name)) {
			return "[label=above:" + value + "]";
		}
		
		// Dans le cas d'une valuation d'arc
		if ("valuation".equals(name)) {
			try {
				if (Integer.valueOf(value).intValue() == 1){ return "--"; } 
				else { return " to node { " + value + " }";	}
			} catch (NumberFormatException e) {
				return " to node { " + value + " }";
			}
		}
		return "";
	}
}