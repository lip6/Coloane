package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.interfaces.objects.IInflexPoint;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Logger;

import org.eclipse.core.runtime.FileLocator;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/** Classe de gestion du modele au format xml */
public class XmlEditor extends DefaultHandler {

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#resolveEntity(java.lang.String, java.lang.String)
	 */
	public final InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		//Choisit le Xschemas en fonction du modele
		if (systemId != null) {
			if (systemId.endsWith("petriXschemas.xsd") || (systemId.endsWith("coloane.dtd"))) { //$NON-NLS-1$ //$NON-NLS-2$
				URL dtd = Coloane.getDefault().getBundle().getEntry("/resources/petriXschemas.xsd"); //$NON-NLS-1$
				Coloane.getLogger().finer("Recherche de la DTD (ressource) : " + dtd.getPath()); //$NON-NLS-1$
				URL	path = FileLocator.toFileURL(dtd);
				Coloane.getLogger().finer("Recherche de la DTD : " + path.getPath()); //$NON-NLS-1$
				InputStream in = path.openStream();
				return new InputSource(in);
			} else if (systemId.endsWith("prefixXschemas.xsd")) { //$NON-NLS-1$
				URL dtd = Coloane.getDefault().getBundle().getEntry("/resources/prefixXschemas.xsd"); //$NON-NLS-1$
				Coloane.getLogger().finer("Recherche de la DTD (ressource) : " + dtd.getPath()); //$NON-NLS-1$
				URL	path = FileLocator.toFileURL(dtd);
				Coloane.getLogger().finer("Recherche de la DTD : " + path.getPath()); //$NON-NLS-1$
				InputStream in = path.openStream();
				return new InputSource(in);
			} else if (systemId.endsWith("ReachibilityXschemas.xsd")) { //$NON-NLS-1$
				URL dtd = Coloane.getDefault().getBundle().getEntry("/resources/ReachibilityXschemas.xsd"); //$NON-NLS-1$
				Coloane.getLogger().finer("Recherche de la DTD (ressource) : " + dtd.getPath()); //$NON-NLS-1$
				URL	path = FileLocator.toFileURL(dtd);
				Coloane.getLogger().finer("Recherche de la DTD : " + path.getPath()); //$NON-NLS-1$
				InputStream in = path.openStream();
				return new InputSource(in);
			}
		}
		Coloane.getLogger().fine("Impossible de trouver une DTD valide (demande de " + systemId + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		return null;
	}

	/* --------------- Ecriture --------------- */

	/**
	 * Retourne une chaine contenant tout le modele en XML
	 * @param model Le model sous forme d'objet JAVA
	 * @return String
	 */
	public static String translateToXML(IModel model) {

		// L'entete XML
		String line = "<?xml version='1.0' encoding='UTF-8'?>\n"; //$NON-NLS-1$
		String schemas = ""; //$NON-NLS-1$
		// On tente de recuperer la DTD pour pouvoir inclure don adresse en debut de fichier
		try {
			if (model.getFormalism().equals("AMI-NET")) { //$NON-NLS-1$
				schemas = "petriXschemas.xsd"; //$NON-NLS-1$
			} else if (model.getFormalism().equals("ReachabilityGraph")) { //$NON-NLS-1$
				schemas = "ReachabilityXschemas.xsd"; //$NON-NLS-1$
			} else if (model.getFormalism().equals("Branching-Process")) { //$NON-NLS-1$
				schemas = "prefixXschemas.xsd"; //$NON-NLS-1$
			}
		} catch (Exception e) {
			Coloane.getLogger().warning("DTD introuvable"); //$NON-NLS-1$
		}

		// Ecriture des attributs relatifs au formalisme et positions
		line += "<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='" + schemas + "' formalism='" + model.getFormalism() + "' xposition='" + model.getXPosition() + "' yposition='" + model.getYPosition() + "'>\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

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


	/* --------------- Lecture --------------- */

	private IModel model = new Model(new CamiTranslator());
	private IAttribute currentAttribute = null;
	private String currentObject = ""; //$NON-NLS-1$

	private int refId = 0; // Le dernier ID lu
	private Logger logger = Coloane.getLogger();

	/**
	 * Lecture des balises ouvrantes du modele
	 */
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {

		logger.finest("Balise lue : " + baliseName); //$NON-NLS-1$

		// Balise MODEL
		if (baliseName.equals("model")) { //$NON-NLS-1$
			// Recuperation des positions
			int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
			int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
			this.refId = 1;
			this.currentObject = "model"; //$NON-NLS-1$

			// Creation du modele
			this.model.setPosition(x, y);
			this.model.setFormalism(attributes.getValue("formalism")); //$NON-NLS-1$

		// Balise NODE
		} else if (baliseName.equals("node")) { //$NON-NLS-1$

			// Recuperation des positions et de l'identifiant
			int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
			int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$
			this.refId = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$
			this.currentObject = "node"; //$NON-NLS-1$

			// Creation du noeud
			INode node = new Node(attributes.getValue("nodetype"), x, y, this.refId); //$NON-NLS-1$

			// Ajout du noeud au modele
			try {
				this.model.addNode(node);
			} catch (ModelException e) {
				Coloane.getLogger().warning("Impossible d'ajouter le noeud au modele : " + e.toString()); //$NON-NLS-1$
			}

		// Balise ARC
		} else if (baliseName.equals("arc")) { //$NON-NLS-1$

			// Recuperation de l'identifiant de l'arc
			this.refId = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$

			// Recuperation de l'identifiant de ses noeuds
			int startid = Integer.parseInt(attributes.getValue("startid")); //$NON-NLS-1$
			int endid = Integer.parseInt(attributes.getValue("endid")); //$NON-NLS-1$
			this.currentObject = "arc"; //$NON-NLS-1$

			// Creation de l'arc
			IArc arc = new Arc(attributes.getValue("arctype"), this.refId); //$NON-NLS-1$
			arc.setStartingNode(this.model.getANode(startid));
			arc.setEndingNode(this.model.getANode(endid));

			// Ajout de l'arc au modele
			try {
				this.model.addArc(arc);
			} catch (ModelException e) {
				Coloane.getLogger().warning("Impossible d'ajouter l'arc au modele : " + e.toString()); //$NON-NLS-1$
			}

		// Balise ATTRIBUT & PI
		} else if (!(baliseName.equals("nodes") || baliseName.equals("arcs"))) { //$NON-NLS-1$ //$NON-NLS-2$

			int x = Integer.parseInt(attributes.getValue("xposition")); //$NON-NLS-1$
			int y = Integer.parseInt(attributes.getValue("yposition")); //$NON-NLS-1$

			// Si on lit une position intermediaire
			if (baliseName.equals("pi")) { //$NON-NLS-1$
				try {
					this.model.getAnArc(this.refId).addPI(x, y);
				} catch (ModelException e) {
					Coloane.getLogger().warning("Impossible de trouver l'arc referent" + e.toString()); //$NON-NLS-1$
				}

			//Si on lit un attribut
			} else {

				// On distingue l'attribut AUTHOR
				if (baliseName.equals("authors")) { //$NON-NLS-1$
					currentAttribute = new Attribute("author(s)", "", this.refId); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					currentAttribute = new Attribute(baliseName, "", this.refId); //$NON-NLS-1$
				}

				currentAttribute.setPosition(x, y);
			}
		}
	}

	// Donnees contenues dans les balises
	private String data = ""; //$NON-NLS-1$

	/**
	 * Gestion des donnees contenues dans les balises
	 */
	public final void characters(char[] ch, int start, int length) throws SAXException {
		data = ""; //$NON-NLS-1$

		// Creation de la donnees (chaine de caracteres)
		for (int i = 0; i < length; i++) {
			data += ch[start + i];
		}
		data = this.deformat(data);
	}


	/**
	 * Lecture des balises fermantes du modele
	 */
	public final void endElement(String namespaceURI, String localName, String qName) throws SAXException {

		// La donnee doit etre du texte et pas un retour chariot ou une tabulation
		if (!(data.equals("") || data.equals("\n") || data.equals("\r") || data.equals("\t"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

			// Ajout de l'attribut au modele
			if (this.currentObject.equals("model")) { //$NON-NLS-1$
				this.currentAttribute.setValue(data);
				this.model.addAttribute(this.currentAttribute);

			// Ajout de l'attribut a un noeud
			} else if (this.currentObject.equals("node")) { //$NON-NLS-1$
				this.currentAttribute.setValue(data);
				this.model.getANode(this.refId).addAttribute(this.currentAttribute);

			// Ajout de l'attribut a un arc
			} else if (this.currentObject.equals("arc")) { //$NON-NLS-1$
				this.currentAttribute.setValue(data);
				model.getAnArc(this.refId).addAttribute(this.currentAttribute);
			}
		}
		// Remise a zero des donnees lues
		data = ""; //$NON-NLS-1$
	}

	public void startDocument() throws SAXException { };
	public void endDocument() throws SAXException { };
	public void processingInstruction(String target, String d) throws SAXException { };
	public void startPrefixMapping(String prefix, String uri) throws SAXException { };
	public void endPrefixMapping(String prefix) throws SAXException { };
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException { };
	public void skippedEntity(String name) throws SAXException { };
	public void setDocumentLocator(Locator loc) { };


	/* Retourne le modele cree par le parcours du fichier xml */
	public final IModel getModel() {
		return model;
	}
}
