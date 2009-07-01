package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
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

	// Correspondance entre les id du document xml et celle des nouveaux objets créés.
	private Map<Integer, Integer> ids = new HashMap<Integer, Integer>();

	private IGraph graph;

	// Donnees contenues dans les balises
	private StringBuilder data = new StringBuilder();


	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		data.setLength(0);

		// Balise MODEL
		if ("model".equals(baliseName)) { //$NON-NLS-1$
			startModel(attributes);

		// Balise NODE
		} else if ("node".equals(baliseName)) { //$NON-NLS-1$
			try {
				startNode(attributes);
			} catch (ModelException e) {
				logger.warning(e.getMessage());
				throw new IllegalArgumentException(e);
			}

		// Balise ARC
		} else if ("arc".equals(baliseName)) { //$NON-NLS-1$
			try {
				startArc(attributes);
			} catch (ModelException e) {
				logger.warning(e.getMessage());
				throw new IllegalArgumentException(e);
			}

		// Balise NOTE
		} else if ("sticky".equals(baliseName)) { //$NON-NLS-1$
			startStickyNote(attributes);

		// Balise PI
		} else if ("pi".equals(baliseName)) {  //$NON-NLS-1$
			startInflexPoint(attributes);

		// Balise ATTRIBUT
		} else if ("attribute".equals(baliseName)) { //$NON-NLS-1$
			startAttribute(attributes.getValue("name"), attributes);  //$NON-NLS-1$
		} else if ("link".equals(baliseName)) { //$NON-NLS-1$
			startLink(attributes);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void characters(char[] ch, int start, int length) throws SAXException {
		data.append(this.deformat(new String(ch, start, length)));
	}


	/** {@inheritDoc} */
	@Override
	public final void endElement(String uri, String localName, String baliseName) throws SAXException {
		if ("model".equals(baliseName)) { //$NON-NLS-1$
			endModel();
		} else if ("node".equals(baliseName)) { //$NON-NLS-1$
			endNode();
		} else if ("sticky".equals(baliseName)) { //$NON-NLS-1$
			endStickyNote();
		} else if ("arc".equals(baliseName)) { //$NON-NLS-1$
			endArc();
		} else if ("pi".equals(baliseName)) { //$NON-NLS-1$
			endInflexPoint();
		} else if ("attribute".equals(baliseName)) { //$NON-NLS-1$
			endAttribute();
		} else if ("value".equals(baliseName)) { //$NON-NLS-1$
			endValue();
		}
	}

	/**
	 * Gestion des caracteres speciaux (deprotection)
	 * @param protectedTxt Le texte a deproteger
	 * @return Le texte transforme et deprotege
	 */
	private String deformat(String protectedTxt) {
		String txt = protectedTxt;
		txt = txt.replaceAll("&amp;", "&"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&lt;", "<"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&gt;", ">"); //$NON-NLS-1$ //$NON-NLS-2$
		return txt;
	}

	/**
	 * La pile doit être vide (à priori)
	 * @param attributes Les attributs attachée à la balise
	 * @throws SAXException Wrap an IllegalArgumentException throws by {@link GraphModel}.
	 */
	private void startModel(Attributes attributes) throws SAXException {
		// Récupération du nom du formalisme
		String formalismName = attributes.getValue("formalism"); //$NON-NLS-1$

		// Création du graph
		try {
			IGraph graph = new GraphModel(formalismName);
			stack.push(graph);
		} catch (IllegalArgumentException e) {
			throw new SAXException(e);
		}
	}

	/**
	 * Analye d'un noeud du graphe
	 * @param attributes Les attributs attachée à la balise
	 * @throws ModelException Si la création du noeud pose problème.
	 */
	private void startNode(Attributes attributes) throws ModelException {
		IGraph graph = (IGraph) stack.peek();

		// Recuperation des infos concernant le noeud.
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		String nodeFormalismName = attributes.getValue("nodetype"); //$NON-NLS-1$
		int id = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$

		// Creation du noeud
		INode node = graph.createNode(nodeFormalismName);
		ids.put(id, node.getId());
		node.getGraphicInfo().setLocation(new Point(x, y));

		// Taille du noeud
		try {
			int scale = Integer.parseInt(attributes.getValue("scale")); //$NON-NLS-1$
			node.getGraphicInfo().setScale(scale);
		} catch (NumberFormatException e) {
			logger.fine("attribut scale absent ou incorrect"); //$NON-NLS-1$
		}

		// Taille du noeud
		try {
			int alt = Integer.parseInt(attributes.getValue("alt")); //$NON-NLS-1$
			node.getGraphicInfo().switchGraphicalDescription(alt);
		} catch (NumberFormatException e) {
			logger.fine("attribut alt absent ou incorrect"); //$NON-NLS-1$
			node.getGraphicInfo().switchGraphicalDescription(0);
		}

		// Couleur du noeud
		try {
			Color foreground = parseColor(attributes.getValue("foreground")); //$NON-NLS-1$
			node.getGraphicInfo().setForeground(foreground);
		} catch (NumberFormatException e) {
			logger.fine("attribut foreground absent ou incorrect"); //$NON-NLS-1$
		}

		// Couleur de fond du noeud
		try {
			Color background = parseColor(attributes.getValue("background")); //$NON-NLS-1$
			node.getGraphicInfo().setBackground(background);
		} catch (NumberFormatException e) {
			logger.fine("attribut background absent ou incorrect"); //$NON-NLS-1$
		}

		// Is interface
		try {
			boolean state = Boolean.valueOf(attributes.getValue("interface")); //$NON-NLS-1$
			node.setInterface(state);
		} catch (NumberFormatException e) {
			logger.fine("attribut interface absent ou incorrect"); //$NON-NLS-1$
		}

		// Node link
		try {
			String nodeLink = attributes.getValue("link"); //$NON-NLS-1$
			node.setNodeLink(nodeLink);
		} catch (NumberFormatException e) {
			logger.fine("attribut link absent ou incorrect"); //$NON-NLS-1$
		}

		stack.push(node);
	}

	/**
	 * Analye d'une note associée au graphe
	 * @param attributes Les attributs attachée à la balise
	 */
	private void startStickyNote(Attributes attributes) {
		ICoreGraph graph = (ICoreGraph) stack.peek();

		// Recuperation des infos concernant le noeud.
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		int width = Integer.parseInt(attributes.getValue("width")); //$NON-NLS-1$
		int height = Integer.parseInt(attributes.getValue("height")); //$NON-NLS-1$

		// Creation de la note
		IStickyNote note = graph.createStickyNote();
		note.setLocation(new Point(x, y));
		note.setSize(new Dimension(width, height));
		stack.push(note);
	}

	/**
	 * Création d'un lien pour la note se trouvant au sommet de la pile vers l'élément linkId.
	 * @param attributes Les attributs attachée à la balise
	 */
	private void startLink(Attributes attributes) {
		IStickyNote note = (IStickyNote) stack.pop();
		ICoreGraph graph = (ICoreGraph) stack.peek();
		int linkId = Integer.parseInt(attributes.getValue("linkId")); //$NON-NLS-1$
		IElement element = graph.getObject(ids.get(linkId));

		// Création du lien
		graph.createLink(note, (ILinkableElement) element);

		stack.push(note);
	}

	/**
	 * Transforme une chaine de caractères en couleur
	 * @param value couleur à parser du type '#XXXXXX' avec X un chiffre en hexadécimal
	 * @return objet couleur correspondante
	 * @throws NumberFormatException si la chaine n'est pas correctement formaté
	 */
	private Color parseColor(String value) throws NumberFormatException {
		if (value == null || !value.matches("#\\p{XDigit}{6}")) { //$NON-NLS-1$
			throw new NumberFormatException("This value : " + value + " is not a valid color.");  //$NON-NLS-1$//$NON-NLS-2$
		}
		Color color = JFaceResources.getColorRegistry().get(value);
		if (color == null) {
			RGB rgb = new RGB(
				Integer.parseInt(value.substring(1, 3), 16),
				Integer.parseInt(value.substring(3, 5), 16),
				Integer.parseInt(value.substring(5, 7), 16));
			JFaceResources.getColorRegistry().put(value, rgb);
			color = JFaceResources.getColorRegistry().get(value);
		}
		return color;
	}

	/**
	 * Analyse d'un arc du graphe<br>
	 * @param attributes Les attributs attachée à la balise
	 * @throws ModelException Si la création de l'arc pose problème.
	 */
	private void startArc(Attributes attributes) throws ModelException {
		IGraph graph = (IGraph) stack.peek();

		// Recuperation des infos concernant l'arc
		int id = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$
		int startid = Integer.parseInt(attributes.getValue("startid")); //$NON-NLS-1$
		int endid = Integer.parseInt(attributes.getValue("endid")); //$NON-NLS-1$
		boolean curved = Boolean.parseBoolean(attributes.getValue("curved")); //$NON-NLS-1$
		String arcFormalismName = attributes.getValue("arctype"); //$NON-NLS-1$

		// Creation de l'arc
		IArc arc = graph.createArc(arcFormalismName,
				graph.getNode(ids.get(startid)),
				graph.getNode(ids.get(endid)));
		ids.put(id, arc.getId());

		// Couleur de l'arc
		try {
			Color color = parseColor(attributes.getValue("color")); //$NON-NLS-1$
			arc.getGraphicInfo().setColor(color);
		} catch (NumberFormatException e) {
			logger.fine("attribut color absent ou incorrecte"); //$NON-NLS-1$
		}

		// Courbure
		arc.getGraphicInfo().setCurve(curved);

		stack.push(arc);
	}

	/**
	 * La pile doit contenir un IArcImpl
	 * @param attributes Les attributs attachée à la balise
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
	 * @param attributes Les attributs attachée à la balise
	 */
	private void startAttribute(String name, Attributes attributes) {
		IElement element = (IElement) stack.peek();
		IAttribute attribute = element.getAttribute(name);
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		Point location = new Point(x, y);

		stack.push(attribute);
		stack.push(location);
	}

	/**
	 * Le graphe est dépilé et this.graph est affecté
	 */
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
	 * La note est dépilée et on défini sa valeur.
	 */
	private void endStickyNote() {
		stack.pop();
	}

	/**
	 * L'attribut est dépilé et on défini sa valeur.
	 */
	private void endAttribute() {
		Point location = (Point) stack.pop();
		IAttribute attribute = (IAttribute) stack.pop();
		String value = data.toString();
		attribute.setValue(value);
		attribute.getGraphicInfo().setLocation(location);
	}

	/**
	 * Initialise la valeur de la note situé au sommet de la pile
	 */
	private void endValue() {
		IStickyNote note = (IStickyNote) stack.peek();
		String value = data.toString();
		note.setLabelContents(value);
	}

	/**
	 * @return le modele crée par le parcours du fichier xml
	 */
	public final IGraph getGraph() {
		return graph;
	}
}
