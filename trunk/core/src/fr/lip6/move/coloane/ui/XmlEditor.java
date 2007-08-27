package fr.lip6.move.coloane.ui;

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
import fr.lip6.move.coloane.main.Coloane;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/** Classe de gestion du modele au format xml */
public class XmlEditor extends DefaultHandler {

	/* --------------- Ecriture --------------- */

	/**
	 * Retourne une chaine contenant tout le modele en XML
	 * @param model Le model sous forme d'objet JAVA
	 * @return String
	 */
	public static String translateToXML(IModel model) {

		// L'entete XML
		String line = "<?xml version='1.0' encoding='ISO-8859-1'?>\n";

		// On tente de recuperer la DTD pour pouvoir inclure don adresse en debut de fichier
		try {
			URL dtd = Coloane.class.getResource("/resources/coloane.dtd");
			URL	path = FileLocator.toFileURL(dtd);
			line += "<!DOCTYPE model SYSTEM '" + path.getPath() + "'>\n";
		} catch (Exception e) {
			System.err.println("DTD introuvable");
		}

		// Ecriture des attributs relatifs au formalisme et positions
		line += "<model formalism='" + model.getFormalism() + "' xposition='" + model.getXPosition() + "' yposition='" + model.getYPosition() + "'>\n";

		// Ecriture des attributs du modele
		if (!(model.getListOfAttrSize() == 0)) {
			line += translateAttributesToXML(model);
		}

		// Creation des noeuds
		line += "<nodes>\n";
		line += translateNodesToXML(model);
		line += "</nodes>\n";

		// Creation des arcs
		line += "<arcs>\n";
		line += translateArcsToXML(model);
		line += "</arcs>\n";

		line += "</model>";
		return line;
	}

	/**
	 * Traduction des noeuds du modele en format XML
	 * @param model Le modele en objet JAVA contenant des noeuds
	 * @return Une chaine de caracteres decrivant en XML les noeuds du modele
	 */
	private static String translateNodesToXML(IModel model) {
		String line = "";

		// Pour chaque noeud...
		for (int i = 0; i < model.getListOfNodeSize(); i++) {
			INode node = model.getNthNode(i);

			// Debut du noeud
			line += "<node nodetype='" + node.getNodeType() + "' id='" + node.getId() + "' xposition='" + node.getXPosition() + "' yposition='" + node.getYPosition() + "'>\n";

			// Ecriture des attributs de chaque noeud
			if (!(node.getListOfAttr() == null)) {
				line += translateNodesAttributesToXML(node);
			}

			// Fin du noeud
			line += "</node>\n";
		}
		return line;
	}

	/**
	 * Traduction des arcs du modele en format XML
	 * @param model Le modele en objet JAVA contenant des arcs
	 * @return Une chaine de caracteres decrivant en XML les arcs du modele
	 */
	private static String translateArcsToXML(IModel model) {
		String line = "";

		// Pour chaque arc...
		for (int i = 0; i < model.getListOfArcSize(); i++) {
			IArc arc = model.getNthArc(i);

			// Debut de l'arc
			line += "<arc arctype='" + arc.getArcType() + "' id='" + arc.getId() + "' startid='" + arc.getStartingNode().getId() + "' endid='" + arc.getEndingNode().getId() + "'>\n";

			// Ecriture des PI
			if (!(arc.getListOfPI().size() == 0)) {
				line += translateInflexToXML(arc);
			}

			// Ecriture des attributs de chaque arc
			if (!(arc.getListOfAttrSize() == 0)) {
				line += translateArcsAttributesToXML(arc);
			}

			// Fin de l'arc
			line += "</arc>\n";
		}
		return line;
	}

	/**
	 * Traduction des points d'inflexion des arcs du modele en format XML
	 * @param arc L'arc en objet JAVA contenant des points d'inflexion
	 * @return Une chaine de caracteres decrivant en XML les points d'inflexion des arcs du modele
	 */
	private static String translateInflexToXML(IArc arc) {
		String line = "";

		// Pour chaque point d'inflexion...
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			IInflexPoint pi = arc.getNthPI(i);
			line += "<pi xposition='" + pi.getXPosition() + "' yposition='" + pi.getYPosition() + "'/>\n";
		}
		return line;
	}

	/**
	 * Traduction des attributs des objets du modele en format XML
	 * @param model Le modele en objet JAVA contenant des attributs d'objet
	 * @return Une chaine de caracteres decrivant en XML les attributs du modele
	 */
	private static String translateAttributesToXML(IModel model) {
		String line = "";

		// Pour chaque attribut...
		for (int i = 0; i < model.getListOfAttrSize(); i++) {
			IAttribute attr = model.getNthAttr(i);

			// On ne traite pas le cas des attributs qui sont vides
			if (!attr.getValue().equals("")) {
				// Traitement special pour l'attribut AUTHOR
				if (attr.getName().equals("author(s)")) {
					line += "<authors" + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + attr.getValue() + "</authors>\n";
				} else {
					line += "<" + attr.getName() + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + format(attr.getValue())	+ "</" + attr.getName() + ">\n";
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
		String line = "";

		// Pour chaque attribut...
		for (int i = 0; i < arc.getListOfAttrSize(); i++) {
			IAttribute attr = arc.getNthAttr(i);
			// On ne traite pas les attributs vides
			if (!attr.getValue().equals("")) {
				line += "<" + attr.getName() + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + format(attr.getValue()) + "</" + attr.getName() + ">\n";
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
		String line = "";

		// Pour chaque attribut...
		for (int i = 0; i < node.getListOfAttrSize(); i++) {
			IAttribute attr = node.getNthAttr(i);
			// On ne traite pas le cas des attributs vides
			if (!attr.getValue().equals("")) {
				line += "<" + attr.getName() + " xposition='" + attr.getXPosition() + "' yposition='" + attr.getYPosition() + "'>" + format(attr.getValue()) + "</"	+ attr.getName() + ">\n";
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
		txt = txt.replaceAll("<", "&lt;");
		txt = txt.replaceAll(">", "&gt;");
		return txt;
	}

	/**
	 * Gestion des caracteres speciaux (deprotection)
	 * @param txt Le texte a deproteger
	 * @return Le texte transforme et protege
	 */
	private String deformat(String txt) {
		txt = txt.replaceAll("&lt;", "<");
		txt = txt.replaceAll("&gt;", ">");
		return txt;
	}




	/* --------------- Lecture --------------- */

	private IModel model = new Model(new CamiTranslator());
	private IAttribute currentAttribute = null;
	private String currentObject = "";

	private int refId = 0; // Le dernier ID lu

	/**
	 * Lecture des balises ouvrantes du modele
	 */
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {

		// Balise MODEL
		if (baliseName.equals("model")) {
			// Recuperation des positions
			int x = Integer.parseInt(attributes.getValue("xposition"));
			int y = Integer.parseInt(attributes.getValue("yposition"));
			this.refId = 1;
			this.currentObject = "model";

			// Creation du modele
			this.model.setPosition(x, y);
			this.model.setFormalism(attributes.getValue("formalism"));

		// Balise NODE
		} else if (baliseName.equals("node")) {

			// Recuperation des positions et de l'identifiant
			int x = Integer.parseInt(attributes.getValue("xposition"));
			int y = Integer.parseInt(attributes.getValue("yposition"));
			this.refId = Integer.parseInt(attributes.getValue("id"));
			this.currentObject = "node";

			// Creation du noeud
			INode node = new Node(attributes.getValue("nodetype"), x, y, this.refId);

			// Ajout du noeud au modele
			try {
				this.model.addNode(node);
			} catch (ModelException e) {
				System.err.println(e.toString());
			}

		// Balise ARC
		} else if (baliseName.equals("arc")) {

			// Recuperation de l'identifiant de l'arc
			this.refId = Integer.parseInt(attributes.getValue("id"));

			// Recuperation de l'identifiant de ses noeuds
			int startid = Integer.parseInt(attributes.getValue("startid"));
			int endid = Integer.parseInt(attributes.getValue("endid"));
			this.currentObject = "arc";

			// Creation de l'arc
			IArc arc = new Arc(attributes.getValue("arctype"), this.refId);
			arc.setStartingNode(this.model.getANode(startid));
			arc.setEndingNode(this.model.getANode(endid));

			// Ajout de l'arc au modele
			try {
				this.model.addArc(arc);
			} catch (ModelException e) {
				System.err.println(e.toString());
			}

		// Balise ATTRIBUT & PI
		} else if (!(baliseName.equals("nodes") || baliseName.equals("arcs"))) {

			int x = Integer.parseInt(attributes.getValue("xposition"));
			int y = Integer.parseInt(attributes.getValue("yposition"));

			// Si on lit une position intermediaire
			if (baliseName.equals("pi")) {
				try {
					this.model.getAnArc(this.refId).addPI(x, y);
				} catch (ModelException e) {
					System.err.println(e.toString());
				}

			//Si on lit un attribut
			} else {

				// On distingue l'attribut AUTHOR
				if (baliseName.equals("authors")) {
					currentAttribute = new Attribute("author(s)", "", this.refId);
				} else {
					currentAttribute = new Attribute(baliseName, "", this.refId);
				}

				currentAttribute.setPosition(x, y);
			}
		}
	}

	// Donnees contenues dans les balises
	private String data = "";

	/**
	 * Gestion des donnees contenues dans les balises
	 */
	public final void characters(char[] ch, int start, int length) throws SAXException {

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
		if (!(data.equals("") || data.equals("\n") || data.equals("\r") || data.equals("\t"))) {

			// Ajout de l'attribut au modele
			if (this.currentObject.equals("model")) {
				this.currentAttribute.setValue(data);
				this.model.addAttribute(this.currentAttribute);

			// Ajout de l'attribut a un noeud
			} else if (this.currentObject.equals("node")) {
				this.currentAttribute.setValue(data);
				this.model.getANode(this.refId).addAttribute(this.currentAttribute);

			// Ajout de l'attribut a un arc
			} else if (this.currentObject.equals("arc")) {
				this.currentAttribute.setValue(data);
				model.getAnArc(this.refId).addAttribute(this.currentAttribute);
			}
		}
		// Remise a zero des donnees lues
		data = "";
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
