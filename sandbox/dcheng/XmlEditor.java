package fr.lip6.move.coloane.ui;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.objects.IPosition;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.model.Model;
import fr.lip6.move.coloane.model.Node;
import fr.lip6.move.coloane.model.Arc;
import fr.lip6.move.coloane.model.Attribute;

/** Classe de gestion du modele au format xml */
public class XmlEditor extends DefaultHandler {

	/* Balise courante */
	private String ltag;

	/* id courant */
	private int refid;

	/* text courant */
	private String data = "";

	IModel model = new Model();

	/** METHODES POUR FICHIER XML* */

	/** Ecriture * */

	/* Renvoie une String contenant le modele au format XML */
	public String modelXML(IModel m) {

		String line = "<?xml version='1.0' encoding='ISO-8859-1'?>\n";

		try {
			URL dtd = Coloane.getDefault().getBundle().getEntry("ressources/coloane.dtd");
			URL path = FileLocator.toFileURL(dtd);
			line += "<!DOCTYPE model SYSTEM '" + path.getPath() + "'>\n";
		} catch (Exception e) {
			System.err.println("DTD introuvable");
		}

		// Ecriture des attributs relatifs au formalism et positions
		line += "<model formalism='" + m.getFormalism() + "' xposition='"
				+ m.getXPosition() + "' yposition='" + m.getYPosition()
				+ "'>\n";

		// Ecriture des attributs du modele
		if (!(m.getListOfAttrSize() == 0)) {
			line += attrXML(m);
		}

		// Creation des noeuds
		line += "<nodes>\n";
		line += nodeXML(m);
		line += "</nodes>\n";

		// Creation des arcs
		line += "<arcs>\n";
		line += arcXML(m);
		line += "</arcs>\n";

		line += "</model>";
		return line;
	}

	/* Renvoie une String contenant l'ensemble des noeuds au format XML */
	public String nodeXML(IModel m) {
		String line = "";

		// Pour chaque noeud,
		// ecriture des données relatifs au nodetype et aux positions
		for (int i = 0; i < m.getListOfNodeSize(); i++) {
			INode node = m.getNthNode(i);

			line += "<node nodetype='" + node.getNodeType() + "' id='"
					+ node.getId() + "' xposition='" + node.getXPosition()
					+ "' yposition='" + node.getYPosition() + "'>\n";

			// Ecriture des attributs de chaque noeud
			if (!(node.getListOfAttr() == null)) {
				line += attrXML(node);
			}
			line += "</node>\n";
		}

		return line;
	}

	/* Renvoie une String contenant l'ensemble des arcs au format XML */
	public String arcXML(IModel m) {
		String line = "";

		// Pour chaque arc,
		// ecriture des données relatifs à l'arctype et aux positions
		for (int i = 0; i < m.getListOfArcSize(); i++) {
			IArc arc = m.getNthArc(i);

			line += "<arc arctype='" + arc.getArcType() + "' id='"
					+ arc.getId() + "' startid='"
					+ arc.getStartingNode().getId() + "' endid='"
					+ arc.getEndingNode().getId() + "'>\n";

			//Ecriture des PI
			if (!(arc.getListOfPI().size() == 0)) {
				line += piXML(arc);
			}
			
			// Ecriture des attributs de chaque arc
			if (!(arc.getListOfAttrSize() == 0)) {
				line += attrXML(arc);
			}
			line += "</arc>\n";
		}

		return line;
	}

	/*
	 * Renvoie une String contenant les PI de l'arc passe en parametre, au
	 * format XML
	 */
	public String piXML(IArc arc) {
		String line = "";

		for (int i = 0; i < arc.getListOfPI().size(); i++) {

			IPosition pi = arc.getNthPI(i);
			line = "<pi xposition='" + pi.getXPosition() + "' yposition='"
					+ pi.getYPosition() + "'/>\n";
		}
		return line;
	}

	/*
	 * Renvoie une String contenant les attributs du modele, passe en parametre,
	 * au format XML
	 */
	public String attrXML(IModel m) {
		String line = "";
		for (int i = 0; i < m.getListOfAttrSize(); i++) {
			IAttribute attr = m.getNthAttr(i);

			if (!attr.getValue().equals("")) {
				if (attr.getName().equals("author(s)")) {
					line += "<authors" + " xposition='" + attr.getXPosition()
							+ "' yposition='" + attr.getYPosition() + "'>"
							+ attr.getValue() + "</authors>\n";
				} else {
					line += "<" + attr.getName() + " xposition='"
							+ attr.getXPosition() + "' yposition='"
							+ attr.getYPosition() + "'>"
							+ format(attr.getValue()) + "</" + attr.getName()
							+ ">\n";
				}
			}
		}

		return line;
	}

	/*
	 * Renvoie une String contenant les attributs du noeud, passe en parametre,
	 * au format XML
	 */
	public String attrXML(INode node) {
		String line = "";

		for (int i = 0; i < node.getListOfAttrSize(); i++) {

			IAttribute attr = node.getNthAttr(i);

			if (!attr.getValue().equals("")) {
				line += "<" + attr.getName() + " xposition='"
						+ attr.getXPosition() + "' yposition='"
						+ attr.getYPosition() + "'>" + format(attr.getValue())
						+ "</" + attr.getName() + ">\n";
			}
		}
		return line;
	}

	public String format(String txt) {
		txt = txt.replaceAll("<", "&lt;");
		txt = txt.replaceAll(">", "&gt;");
		return txt;
	}

	/*
	 * Renvoie une String contenant les attributs de l'arc, passe en parametre,
	 * au format XML
	 */
	public String attrXML(IArc arc) {
		String line = "";

		for (int i = 0; i < arc.getListOfAttrSize(); i++) {

			IAttribute attr = arc.getNthAttr(i);
			if (!attr.getValue().equals("")) {
				line += "<" + attr.getName() + " xposition='"
						+ attr.getXPosition() + "' yposition='"
						+ attr.getYPosition() + "'>" + format(attr.getValue())
						+ "</" + attr.getName() + ">\n";
			}
		}
		return line;
	}

	/* Lecture */

	// Permet de sauvegarder 1 attribut afin de lui affecter sa valeur
	Attribute att = null;

	/*
	 * Lit les balises ouvrantes et effectue les operations de creation de
	 * modele
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		// Dans la balise model
		if (qName.equals("model")) {

			int x, y;

			ltag = "model";
			refid = 1;

			// Recupération des positions
			x = Integer.parseInt(attributes.getValue("xposition"));
			y = Integer.parseInt(attributes.getValue("yposition"));

			// Creation du modèle
			model.setPosition(x, y);
			model.setFormalism(attributes.getValue("formalism"));

			// Dans une balise node
		} else if (qName.equals("node")) {

			int x, y, id;

			ltag = "node";

			// Recupération des positions et de l'identifiant
			x = Integer.parseInt(attributes.getValue("xposition"));
			y = Integer.parseInt(attributes.getValue("yposition"));
			id = Integer.parseInt(attributes.getValue("id"));

			refid = id;

			// Creation du noeud
			INode node = new Node(attributes.getValue("nodetype"), x, y, id);

			// Ajout du noeud au modèle
			try {
				model.addNode(node);
			} catch (SyntaxErrorException e) {
				System.out.println(e.toString());
			}

			// Dans une balise arc
		} else if (qName.equals("arc")) {

			int id, startid, endid;

			ltag = "arc";

			// Récuperation de l'identifiant de l'arc
			id = Integer.parseInt(attributes.getValue("id"));

			refid = id;

			// Récuperation de l'identifiant de ses noeuds
			startid = Integer.parseInt(attributes.getValue("startid"));
			endid = Integer.parseInt(attributes.getValue("endid"));

			// Creation de l'arc
			IArc arc = new Arc(attributes.getValue("arctype"), id);

			arc.setStartingNode(model.getANode(startid));
			arc.setEndingNode(model.getANode(endid));

			// Ajout de l'arc au modèle
			try {
				model.addArc(arc);
			} catch (SyntaxErrorException e) {
				System.out.println(e.toString());
			}

			// Dans une balise d'un attribut (l'objet)
		} else if (!(qName.equals("nodes") || qName.equals("arcs"))) {

			int x, y;

			x = Integer.parseInt(attributes.getValue("xposition"));
			y = Integer.parseInt(attributes.getValue("yposition"));

			// Si on lit une position intermediaire
			if (qName.equals("pi")) {
				if (ltag.equals("arc")) {
					try {
						model.getAnArc(refid).addPI(x, y);
					} catch (SyntaxErrorException e) {
						System.out.println(e.toString());
					}
				}
				// sinon on lit un attribut
			} else if (qName.equals("authors")) {
				att = new Attribute("author(s)", "", refid);
			} else {
				att = new Attribute(qName, "", refid);
			}

			att.setPosition(x, y);

		}
	}

	/* Gestion des données contenues dans les balises */
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		// Creation de la donnée (chaine de caractères)
		for (int i = 0; i < length; i++) {
			data += ch[start + i];
		}

		data = deformat(data);

	}

	public String deformat(String txt) {
		txt = txt.replaceAll("&lt;", "<");
		txt = txt.replaceAll("&gt;", ">");
		return txt;
	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		// La donnée doit etre du texte et pas un retour chariot ou un
		// tabulation
		if (!(data.equals("") || data.equals("\n") || data.equals("\r") || data.equals("	"))) {

			// Ajout de l'attribut au modele
			if (ltag.equals("model")) {

				att.setValue(data);
				model.addAttribute(att);

				// Ajout de l'attribut à un noeud
			} else if (ltag.equals("node")) {
				att.setValue(data);
				model.getANode(refid).addAttribute(att);

				// Ajout de l'attribut à un arc
			} else if (ltag.equals("arc")) {

				att.setValue(data);
				model.getAnArc(refid).addAttribute(att);

			}
		}
		data = "";
	}

	public void error(SAXParseException e) throws SAXParseException {
		throw e;
	}

	public void fatalError(SAXParseException e) throws SAXParseException {
		throw e;
	}

	/** FIN METHODES XML* */

	/* Retourne le modele créé par le parcours du fichier xml */
	public IModel getModel() {
		return model;
	}

}
