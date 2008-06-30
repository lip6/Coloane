package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.ui.model.GraphModel;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IElement;
import fr.lip6.move.coloane.core.ui.model.interfaces.IGraph;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Classe destinee a la lecture d'un fichier XML. La lecture du fichier permet la construction d'un modele
 * @author jbvoron
 *
 */
public class ModelHandler extends DefaultHandler {
	private final Logger logger = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private Stack<Object> stack = new Stack<Object>();
	private Map<Integer, Integer> nodesId = new HashMap<Integer, Integer>();

	private IGraph graph;

	// Donnees contenues dans les balises
	private StringBuilder data;


	/**
	 * Lecture des balises ouvrantes du modele
	 */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		data = new StringBuilder();

		// Balise MODEL
		if ("model".equals(baliseName)) { //$NON-NLS-1$
			startModel(attributes);

		// Balise NODE
		} else if ("node".equals(baliseName)) { //$NON-NLS-1$
			startNode(attributes);

		// Balise ARC
		} else if ("arc".equals(baliseName)) { //$NON-NLS-1$
			startArc(attributes);

		// Balise PI
		} else if ("pi".equals(baliseName)) {  //$NON-NLS-1$
			startInflexPoint(attributes);

		// Balise ATTRIBUT
		} else if (!("nodes".equals(baliseName) || "arcs".equals(baliseName))) { //$NON-NLS-1$ //$NON-NLS-2$
			startAttribute(baliseName.replaceAll("authors", "author(s)"), attributes);  //$NON-NLS-1$//$NON-NLS-2$
		}
	}

	/**
	 * Gestion des donnees contenues dans les balises
	 * TODO : Utiliser un string builder ?
	 */
	@Override
	public final void characters(char[] ch, int start, int length) throws SAXException {
		data.append(this.deformat(new String(ch, start, length)));
	}


	/**
	 * Lecture des balises fermantes du modele
	 */
	@Override
	public final void endElement(String uri, String localName, String baliseName) throws SAXException {
		if ("model".equals(baliseName)) { //$NON-NLS-1$
			endModel();
		} else if ("node".equals(baliseName)) { //$NON-NLS-1$
			endNode();
		} else if ("arc".equals(baliseName)) { //$NON-NLS-1$
			endArc();
		} else if ("pi".equals(baliseName)) { //$NON-NLS-1$
			endInflexPoint();
		} else if (!("nodes".equals(baliseName) || "arcs".equals(baliseName))) { //$NON-NLS-1$ //$NON-NLS-2$
			endAttribute();
		}
	}

	/**
	 * Gestion des caracteres speciaux (deprotection)
	 * @param txt Le texte a deproteger
	 * @return Le texte transforme et protege
	 */
	private String deformat(String txt) {
		txt = txt.replaceAll("&lt;", "<"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&gt;", ">"); //$NON-NLS-1$ //$NON-NLS-2$
		return txt;
	}

	/**
	 * La pile doit être vide (à priori)
	 * @param attributes
	 */
	private void startModel(Attributes attributes) {
		// Recuperation des positions
//		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
//		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$

		// Récupération du nom du formalisme
		String formalismName = attributes.getValue("formalism"); //$NON-NLS-1$

		// Création du graph
		IGraph graph = new GraphModel(formalismName);

		stack.push(graph);
	}

	/**
	 * @param attributes
	 */
	private void startNode(Attributes attributes) {
		IGraph graph = (IGraph) stack.peek();

		// Recuperation des infos concernant le noeud.
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		String nodeFormalismName = attributes.getValue("nodetype"); //$NON-NLS-1$
		int id = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$

		// Creation du noeud
		INode node = graph.createNode(nodeFormalismName);
		nodesId.put(id, node.getId());
		node.getGraphicInfo().setLocation(x, y);

		// Taille du noeud
		try {
			int scale = Integer.parseInt(attributes.getValue("scale")); //$NON-NLS-1$
			node.getGraphicInfo().setScale(scale);
		} catch (NumberFormatException e) {
			logger.fine("attribut scale absent ou incorrecte"); //$NON-NLS-1$
		}

		// Couleur du noeud
		try {
			Color foreground = parseColor(attributes.getValue("foreground")); //$NON-NLS-1$
			node.getGraphicInfo().setForeground(foreground);
		} catch (NumberFormatException e) {
			logger.fine("attribut foreground absent ou incorrecte"); //$NON-NLS-1$
		}

		// Couleur de fond du noeud
		try {
			Color background = parseColor(attributes.getValue("background")); //$NON-NLS-1$
			node.getGraphicInfo().setBackground(background);
		} catch (NumberFormatException e) {
			logger.fine("attribut background absent ou incorrecte"); //$NON-NLS-1$
		}
		stack.push(node);
	}

	/**
	 * @param value couleur à parser
	 * @return objet couleur correspondant
	 * @throws NumberFormatException si la chaine n'est pas correctement formaté
	 */
	private Color parseColor(String value) {
		if (value == null || !value.matches("#\\p{XDigit}{6}")) { //$NON-NLS-1$
			throw new NumberFormatException("This value : " + value + " is not a valid color.");  //$NON-NLS-1$//$NON-NLS-2$
		}

		return new Color(null,
				Integer.parseInt(value.substring(1, 3), 16),
				Integer.parseInt(value.substring(3, 5), 16),
				Integer.parseInt(value.substring(5, 7), 16));
	}

	/**
	 * La pile doit contenir un IModelImpl
	 * @param attributes
	 */
	private void startArc(Attributes attributes) {
		IGraph graph = (IGraph) stack.peek();

		// Recuperation des infos concernant l'arc
		int startid = Integer.parseInt(attributes.getValue("startid")); //$NON-NLS-1$
		int endid = Integer.parseInt(attributes.getValue("endid")); //$NON-NLS-1$
		String arcFormalismName = attributes.getValue("arctype"); //$NON-NLS-1$

		// Creation de l'arc
		IArc arc = graph.createArc(arcFormalismName,
				graph.getNode(nodesId.get(startid)),
				graph.getNode(nodesId.get(endid)));

		// Couleur de l'arc
		try {
			Color color = parseColor(attributes.getValue("color")); //$NON-NLS-1$
			arc.getGraphicInfo().setColor(color);
		} catch (NumberFormatException e) {
			logger.fine("attribut color absent ou incorrecte"); //$NON-NLS-1$
		}
		stack.push(arc);
	}

	/**
	 * La pile doit contenir un IArcImpl
	 * @param attributes
	 */
	private void startInflexPoint(Attributes attributes) {
		IArc arc = (IArc) stack.peek();
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		arc.addInflexPoint(new Point(x, y));
	}

	/**
	 * La pile doit contenir l'IElement sur lequel doit être placé cet attribut
	 * @param name nom de l'attribut
	 * @param attributes
	 */
	private void startAttribute(String name, Attributes attributes) {
		IElement element = (IElement) stack.peek();
		IAttribute attribute = element.getAttribute(name);
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		attribute.getGraphicInfo().setLocation(x, y);

		stack.push(attribute);
	}

	private void endModel() {
		this.graph = (IGraph) stack.pop();
	}

	/**
	 * Le noeud est dépilé.
	 */
	private void endNode() {
		/*INode node = (INode)*/ stack.pop();
	}

	/**
	 * L'arc est dépilé.
	 */
	private void endArc() {
		/*IArc arc = (IArc)*/ stack.pop();
	}

	/**
	 * Ne fait rien.
	 */
	private void endInflexPoint() {
		// rien à faire.
	}

	/**
	 * L'attribut est dépilé et on défini ça valeur.
	 */
	private void endAttribute() {
		IAttribute attribute = (IAttribute) stack.pop();
		String value = data.toString();
		attribute.setValue(value);
	}

	/**
	 * Retourne le modele cree par le parcours du fichier xml
	 */
	public final IGraph getGraph() {
		return graph;
	}
}
