package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IElement;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.core.ui.model.NodeImplAdapter;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

import java.util.LinkedList;
import java.util.List;
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

	private Stack<IElement> stack = new Stack<IElement>();
	private List<Runnable> tasks = new LinkedList<Runnable>();

	private Formalism formalisme;
	private IModelImpl modelAdapter;

	// Donnees contenues dans les balises
	private String data = ""; //$NON-NLS-1$


	/**
	 * Lecture des balises ouvrantes du modele
	 */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
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
	 */
	@Override
	public final void characters(char[] ch, int start, int length) throws SAXException {
		data = this.deformat(new String(ch, start, length));
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
		IModel model = new Model(new CamiTranslator());

		// Recuperation des positions
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$

		// Parametrage du modele bas niveau
		model.setPosition(x, y);
		model.setFormalism(attributes.getValue("formalism")); //$NON-NLS-1$

		// Construction du modeleAdapter
		try {
			IModelImpl modelAdapter = new ModelImplAdapter(model);
			this.formalisme = modelAdapter.getFormalism();
			stack.push(modelAdapter);
		} catch (BuildException e) {
			logger.warning("Impossible de construire le modèle : " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * @param attributes
	 */
	private void startNode(Attributes attributes) {
		IModelImpl modelAdapter = (IModelImpl) stack.peek();

		// Recuperation des positions et de l'identifiant
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		String nodeType = attributes.getValue("nodetype"); //$NON-NLS-1$
		int id = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$

		// Creation du noeud
		INode node = new Node(nodeType, x, y, id);
		INodeImpl nodeAdapter = new NodeImplAdapter(node, this.formalisme.getNodeFormalism(nodeType));
		nodeAdapter.setModelAdapter(modelAdapter);

		// Taille du noeud
		try {
			int scale = Integer.parseInt(attributes.getValue("scale")); //$NON-NLS-1$
			nodeAdapter.getGraphicInfo().setScale(scale);
		} catch (NumberFormatException e) {
			logger.fine("attribut scale absent ou incorrecte"); //$NON-NLS-1$
		}

		// Couleur du noeud
		try {
			Color foreground = parseColor(attributes.getValue("foreground")); //$NON-NLS-1$
			nodeAdapter.getGraphicInfo().setForeground(foreground);
		} catch (NumberFormatException e) {
			logger.fine("attribut foreground absent ou incorrecte"); //$NON-NLS-1$
		}

		// Couleur de fond du noeud
		try {
			Color background = parseColor(attributes.getValue("background")); //$NON-NLS-1$
			nodeAdapter.getGraphicInfo().setBackground(background);
		} catch (NumberFormatException e) {
			logger.fine("attribut background absent ou incorrecte"); //$NON-NLS-1$
		}
		stack.push(nodeAdapter);
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
		IModelImpl modelAdapter = (IModelImpl) stack.peek();

		// Recuperation de l'identifiant de ses noeuds
		int startid = Integer.parseInt(attributes.getValue("startid")); //$NON-NLS-1$
		int endid = Integer.parseInt(attributes.getValue("endid")); //$NON-NLS-1$

		// Creation de l'arc
//		try {
			IArcImpl arcAdapter = new ArcImplAdapter(
					modelAdapter.getNode(startid),
					modelAdapter.getNode(endid),
					modelAdapter.getFormalism().getArcFormalism(attributes.getValue("arctype"))); //$NON-NLS-1$
			arcAdapter.setModelAdapter(modelAdapter);

			// Couleur de l'arc
			try {
				Color color = parseColor(attributes.getValue("color")); //$NON-NLS-1$
				arcAdapter.getGraphicInfo().setColor(color);
			} catch (NumberFormatException e) {
				logger.fine("attribut color absent ou incorrecte"); //$NON-NLS-1$
			}

			stack.push(arcAdapter);
//		} catch (BuildException e) {
//			logger.warning("Impossible de créer l'arc : " + e.getMessage()); //$NON-NLS-1$
//		}
	}

	/**
	 * La pile doit contenir un IArcImpl
	 * @param attributes
	 */
	private void startInflexPoint(Attributes attributes) {
		IArcImpl arcAdapter = (IArcImpl) stack.peek();
		int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
		int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
		arcAdapter.addInflexPoint(new Point(x, y));
	}

	/**
	 * La pile doit contenir l'IElement sur lequel doit être placé cet attribut
	 * @param attributes
	 */
	private void startAttribute(String name, Attributes attributes) {
		IElement element = stack.peek();
		for (IAttributeImpl attr : element.getProperties().values()) {
			if (attr.getDisplayName().equals(name)) {
				int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
				int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
				attr.getGraphicInfo().setLocation(x, y);
				stack.push(attr);
				return;
			}
		}
	}

	private void endModel() {
		this.modelAdapter = (IModelImpl) stack.pop();
		for (Runnable task : tasks) {
			task.run();
		}
	}

	/**
	 * La pile doit contenir le noeud ajouté par startNode et le model.<br>
	 * Le noeud est dépilé.
	 */
	private void endNode() {
		INodeImpl nodeAdapter = (INodeImpl) stack.pop();
		IModelImpl modelAdapter = (IModelImpl) stack.peek();
		try {
			modelAdapter.addNode(nodeAdapter);
		} catch (BuildException e) {
			logger.warning("Impossible d'ajouter le noeud : " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * La pile doit contenir l'arc ajouté par startArc et le model.<br>
	 * L'arc est dépilé.
	 */
	private void endArc() {
		IArcImpl arcAdapter = (IArcImpl) stack.pop();
		IModelImpl modelAdapter = (IModelImpl) stack.peek();
		try {
			modelAdapter.addArc(arcAdapter);
		} catch (BuildException e) {
			logger.warning("Impossible d'ajouter l'arc : " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Ne fait rien.
	 */
	private void endInflexPoint() {
		// rien à faire.
	}

	/**
	 * La pile doit contenir un IElement sur lequel doit être ajouté l'attribut.<br>
	 * L'attribut est dépilé.
	 */
	private void endAttribute() {
		final IAttributeImpl attributeAdapter = (IAttributeImpl) stack.pop();
		final String value = data;
		tasks.add(new Runnable() {
			public void run() {
				attributeAdapter.setValue(attributeAdapter.getDefaultValue(), value);
			}
		});
	}

	/**
	 * Retourne le modele cree par le parcours du fichier xml
	 */
	public final IModelImpl getModel() {
		return modelAdapter;
	}
}
