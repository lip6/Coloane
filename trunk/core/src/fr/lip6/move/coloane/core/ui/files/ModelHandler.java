package fr.lip6.move.coloane.core.ui.files;

import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

public class ModelHandler extends DefaultHandler {

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

		// Remise a zero du champs data
		data = ""; //$NON-NLS-1$

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
	

	/* Retourne le modele cree par le parcours du fichier xml */
	public final IModel getModel() {
		return model;
	}
}
