package fr.lip6.move.coloane.interfaces.translators;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.interfaces.utils.CamiParser;

import java.util.StringTokenizer;
import java.util.Vector;

public class CamiTranslator implements Translator {

	public final Vector<String> translateModel(IModel model) {
		Vector<String> toReturn = new Vector<String>();

		// Ajout du noeud du modele
		toReturn.add(new String("CN(3:net,1)"));

		// Ajout des attributs
		for (IAttribute attribute : model.getListOfAttributes()) { toReturn.addAll(this.translateAttribute(attribute));	}

		// Ajout des noeuds
		for (INode node : model.getListOfNodes()) {	toReturn.addAll(this.translateNode(node)); }

		// Ajout des arcs
		for (IArc arc : model.getListOfArcs()) { toReturn.addAll(this.translateArc(arc)); }

		return toReturn;
	}

	public final Vector<String> translateArc(IArc arc) {
		Vector<String> toReturn = new Vector<String>();

		// traduction de la partie principale
		StringBuffer buffer = new StringBuffer();
		buffer.append("CA(");
		buffer.append(arc.getArcType().length() + ":" + arc.getArcType() + ",");
		buffer.append(arc.getId() + ",");
		buffer.append(arc.getStartingNode().getId() + "," + arc.getEndingNode().getId());
		buffer.append(")");
		toReturn.add(buffer.toString());

		// Traduction des points intermediaires
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			toReturn.add(new String("PI(-1," + arc.getId() + "," + arc.getNthPI(i).getXPosition() + "," + arc.getNthPI(i).getYPosition() + ",-1)"));
		}

		// Traduction des attributs
		for (IAttribute att : arc.getListOfAttr()) { toReturn.addAll(this.translateAttribute(att));	}

		return toReturn;
	}

	public final Vector<String> translateAttribute(IAttribute attribute) {
		Vector<String> toReturn = new Vector<String>();
		String attributeValue = attribute.getValue();

		// Si la valeur de l'attribut est vide... on retourne
		if (attributeValue.equals("")) {
			return toReturn;
		}

		// Decoupage de la chaine de charactere suivant un pattern
		String[] valueTable = attributeValue.split("(\n\r)|(\r\n)|(\n)|(\r)");

		// Si la tableau obtenu est de taille 1 et que la ligne est de taille < a 255, on a un attribut d'une ligne
		if (valueTable.length == 1 && valueTable[0].length() > 0 && valueTable[0].length() <= MAXLENGTH) {

				StringBuffer buffer = new StringBuffer();
				buffer.append("CT(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getRefId() + ",");
				buffer.append(valueTable[0].length() + ":" + valueTable[0]);
				buffer.append(")");
				toReturn.add(buffer.toString());

		// Sinon, on a un attribut multiligne
		} else {
			int lineCounter = 1; // compteur ligne utile

			for (int i = 0; i < valueTable.length; i++) {

				// Pour chaque ligne, on teste si on doit la decouper car trop longue
				if (valueTable[i].length() < MAXLENGTH) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getRefId() + ",");
					buffer.append(lineCounter++ + ",");
					buffer.append(1 + ","); // Archaisme de Framekit
					buffer.append(valueTable[i].length() + ":" + valueTable[i]);
					buffer.append(")");
					toReturn.addElement(buffer.toString());
				} else {
					int start = 0;
					int end = MAXLENGTH;

					// Traduction des n*255 premiers caracteres
					while (end < valueTable[i].length()) {
						String sub = valueTable[i].substring(start, end);
						StringBuffer buffer = new StringBuffer();
						buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getRefId() + ",");
						buffer.append(lineCounter++ + ",");
						buffer.append(1 + ","); // archaisme de Framekit
						buffer.append(sub.length() + ":" + sub);
						buffer.append(")");
						toReturn.addElement(buffer.toString());

						start += MAXLENGTH;
						end += MAXLENGTH;
					}

					// Traduction des caracteres restants
					String sub = valueTable[i].substring(start, valueTable[i].length());
					StringBuffer buffer = new StringBuffer();
					buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getRefId() + ",");
					buffer.append(lineCounter++ + ",");
					buffer.append(1 + ","); // archaisme de Framekit
					buffer.append(sub.length() + ":" + sub);
					buffer.append(")");
					toReturn.addElement(buffer.toString());
				}
			}

		}

		//Traduit la position de l'attribut
		if (attribute.getXPosition() != 0 || attribute.getYPosition() != 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("PT(" + attribute.getRefId() + "," + attribute.getName().length() + ":" + attribute.getName() + ",");
			buffer.append(attribute.getXPosition() + "," + attribute.getYPosition());
			buffer.append(")");
			toReturn.add(buffer.toString());
		}

		return toReturn;
	}

	public final Vector<String> translateNode(INode node) {
		Vector<String> toReturn = new Vector<String>();
		toReturn.add(new String("CN(" + node.getNodeType().length() + ":" + node.getNodeType() + "," + node.getId() + ")"));
		toReturn.add(new String("PO(" + node.getId() + "," + node.getXPosition() + "," + node.getYPosition() + ")"));

		// Traduction des attributs
		for (IAttribute att : node.getListOfAttr()) { toReturn.addAll(this.translateAttribute(att));	}

		return toReturn;
	}

	/**
	 * Construit un noeud a partir des donnees CAMI
	 * @param model Le modele auquel rattacher le noeud
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction du noeud
	 */
	private IModel loadNode(IModel model, CamiParser parser) throws SyntaxErrorException {
		String nodeType = parser.parseString(",");
		String nodeId = parser.parseInt(")");

		// Si le noeud en cours n'est pas le noeud principal du modele
		if (Integer.parseInt(nodeId) != 1) {
			try {
				model.addNode(new Node(nodeType, 0, 0, Integer.parseInt(nodeId)));
			} catch (ModelException e) {
				throw new SyntaxErrorException("Impossible d'ajouter le noeud au modele : " + e.getMessage());
			}
		}

		return model;
	}

	/**
	 * Construit un arc a partir des donnees CAMI
	 * @param model Le modele auquel rattacher l'arc
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction du noeud
	 * @throws SyntaxErrorException
	 */
	private IModel loadArc(IModel model, CamiParser parser) throws SyntaxErrorException {
		String arcType = parser.parseString(",");
		String arcId = parser.parseInt(",");
		String from = parser.parseInt(",");
		String to = parser.parseInt(")");

		// Recherche de la source et de la cible
		INode nodeBegin = model.getANode(Integer.parseInt(from));
		INode nodeEnd = model.getANode(Integer.parseInt(to));

		if (nodeBegin == null || nodeEnd == null) {
			throw new SyntaxErrorException("Impossible de connecter l'arc : Source / Cible manquante");
		}

		// Creation de l'arc
		IArc arc = new Arc(arcType, Integer.parseInt(arcId));
		arc.setStartingNode(nodeBegin);
		arc.setEndingNode(nodeEnd);
		nodeBegin.addOutputArc(arc);
		nodeEnd.addInputArc(arc);
		try {
			model.addArc(arc);
		} catch (ModelException e) {
			throw new SyntaxErrorException("Impossible d'ajouter l'arc au modele : " + e.getMessage());
		}

		return model;
	}

	/**
	 * Construit un attribut a partir des donnees CAMI
	 * @param model Le modele auquel rattacher l'attribut
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction de l'attribut
	 * @throws SyntaxErrorException
	 */
	private IModel loadAttribute(IModel model, CamiParser parser) throws SyntaxErrorException {
		String value = new String();
		String name = parser.parseString(",");
		String ref = parser.parseInt(",");
		value = parser.parseString(")");

		// Creation effective de l'attribut
		IAttribute attr = new Attribute(name, value, Integer.parseInt(ref));

		// L'attribut peut directement etre attache au modele
		if (Integer.parseInt(ref) == 1) {
			model.addAttribute(attr);
			return model;
		}

		// Est-ce que l'attribut s'attache a un arc ?
		IArc arc = model.getAnArc(Integer.parseInt(ref));
		if (arc != null) {
			arc.addAttribute(attr);
			return model;
		}

		// Est-ce que l'attribut s'attache a un noeud ?
		INode node = model.getANode(Integer.parseInt(ref));
		if (node != null) {
			node.addAttribute(attr);
			return model;
		}

		// Sinon on retourne une erreur
		throw new SyntaxErrorException("Impossible d'attacher l'attribut a un objet");
	}

	/**
	 * Construit un attribut <b>multiligne</b> a partir des donnees CAMI
	 * @param model Le modele auquel rattacher l'attribut
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction de l'attribut
	 * @throws SyntaxErrorException
	 */
	private IModel loadMAttribute(IModel model, CamiParser parser) throws SyntaxErrorException {
		String value = new String();
		boolean found = false;

		String name = parser.parseString(",");
		String ref = parser.parseInt(",");
		parser.parseInt(",");
		parser.parseInt(",");
		value = parser.parseString(")");

		// Si le referent a comme identifiant 1, alors il faut attacher l'attribut au modele
		if (Integer.parseInt(ref) == 1) {
			// On recherche l'attribut a modifier
			for (IAttribute attribute : model.getListOfAttributes()) {
				if (name.equals(attribute.getName())) {
					attribute.setValue(attribute.getValue() + "\r" + value);
					found = true;
					break; // On sort de cette boucle de recherche
				}
			}

			// Si aucun attribut n'a ete trouve... On en cree un!
			if (!found) {
				IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
				model.addAttribute(att);
			}
			return model;
		}

		// On cherche a savoir si l'attribut s'attache a un arc ?
		IArc arc = model.getAnArc(Integer.parseInt(ref));
		if (arc != null) {
			for (IAttribute attribute : arc.getListOfAttr()) {
				if (name.equals(attribute.getName())) {
					attribute.setValue(attribute.getValue() + "\r" + value);
					found = true;
					break;
				}
			}

			// Si aucun attribut de l'arc ne correspond on en cree un
			if (!found) {
				IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
				arc.addAttribute(att);
			}
			return model;
		}

		// On cherche a savoir si l'attribut s'attache a un noeud ?
		INode node = model.getANode(Integer.parseInt(ref));
		if (node != null) {
			for (IAttribute attribute : node.getListOfAttr()) {
				if (name.equals(attribute.getName())) {
					attribute.setValue(attribute.getValue() + "\r" + value);
					found = true;
					break;
				}
			}

			// Si aucun attribut du noeud ne correspond... On en cree un
			if (!found) {
				IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
				node.addAttribute(att);
			}
			return model;
		}

		// Sinon on retourne une erreur
		throw new SyntaxErrorException("Impossible d'attacher l'attribut a un objet");
	}

	/**
	 * Positionne un objet a partir des donnees CAMI
	 * @param model Le modele auquel s'applique le positionnement
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres le repositionnement de l'objet
	 * @throws SyntaxErrorException
	 */
	private IModel loadPosition(IModel model, CamiParser parser) throws SyntaxErrorException {
		String ref = parser.parseInt(",");
		String x = "";
		String y = "";

		// !! Attention bidouille pour prendre en compte le PO de 3e type
		if (Integer.parseInt(ref) == -1) {
			ref = parser.parseInt(",");
			x = parser.parseInt(",");
			y = parser.parseInt(",");
		} else {
			x = parser.parseInt(",");
			y = parser.parseInt(")");
		}

		if (Integer.parseInt(ref) == 1) {
			model.setPosition(Integer.parseInt(x), Integer.parseInt(y));
			return model;
		}

		INode node = model.getANode(Integer.parseInt(ref));
		if (node != null) {
			node.setPosition(Integer.parseInt(x), Integer.parseInt(y));
			return model;
		}

		throw new SyntaxErrorException("Impossible de trouver l'objet concerne par cette position");
	}

	/**
	 * Construit un point d'inflexion a partir des donnees CAMI
	 * @param model Le modele auquel rattacher le point d'inflexion
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction du point d'inflexion
	 * @throws SyntaxErrorException
	 */
	private IModel loadInflexPoint(IModel model, CamiParser parser) throws SyntaxErrorException {
		String ref = parser.parseInt(",");
		String x = "";
		String y = "";

		// !! Attention bidouille pour prendre en compte le PI avec ref == -1
		if (Integer.parseInt(ref) == -1) {
			ref = parser.parseInt(",");
			x = parser.parseInt(",");
			y = parser.parseInt(",");
			// x = ps.parseInt(",");
		} else {
			x = parser.parseInt(",");
			y = parser.parseInt(")");
		}

		// Dernier arc rencontre
		IArc arc = model.getAnArc(Integer.parseInt(ref));
		if (arc != null) {
			try {
				arc.addPI(Integer.parseInt(x), Integer.parseInt(y));
			} catch (Exception e) {
				throw new SyntaxErrorException("Impossible d'ajouter un point d'inflexion a l'arc : " + e.getMessage());
			}
			return model;
		} else {
			throw new SyntaxErrorException("Impossible de rattacher le point d'inflexion a un arc");
		}
	}

	/**
	 * Positionne un attribut a partir des donnees CAMI
	 * @param model Le modele auquel s'applique le repositionement
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres le repositionement de l'attribut
	 * @throws SyntaxErrorException
	 */
	private IModel loadTextPosition(IModel model, CamiParser parser) throws SyntaxErrorException {
		String ref = parser.parseInt(",");
		String name = parser.parseString(",");
		String x = parser.parseInt(",");
		String y = parser.parseInt(")");

		if (Integer.parseInt(ref) == 1) {
			for (IAttribute attribute : model.getListOfAttributes()) {
				if (attribute.getName().equals(name)) {
					attribute.setPosition(Integer.parseInt(x), Integer.parseInt(y));
					return model;
				}
			}
			throw new SyntaxErrorException("Impossible de trouver l'attribut a positionner");
		}

		IArc arc = model.getAnArc(Integer.parseInt(ref));
		if (arc != null) {
			for (IAttribute attribute : arc.getListOfAttr()) {
				if (attribute.getName().equals(name)) {
					attribute.setPosition(Integer.parseInt(x), Integer.parseInt(y));
					return model;
				}
			}
			throw new SyntaxErrorException("Impossible de trouver l'attribut a positionner");
		}

		INode node = model.getANode(Integer.parseInt(ref));
		if (node != null) {
			for (IAttribute attribute : node.getListOfAttr()) {
				if (attribute.getName().equals(name)) {
					attribute.setPosition(Integer.parseInt(x), Integer.parseInt(y));
					return model;
				}
			}
			throw new SyntaxErrorException("Impossible de trouver l'attribut a positionner");
		}

		throw new SyntaxErrorException("Impossible de trouver l'objet referent de l'attribut"); //$NON-NLS-1$
	}



	public final IModel loadModel(Vector<String> commands) throws SyntaxErrorException {
		IModel model = new Model(this);

		StringTokenizer tokenizer;
		CamiParser parser;

		for (String line : commands) {
			tokenizer = new StringTokenizer(line);
			parser = new CamiParser(line);

			// Type de la commande
			String type = tokenizer.nextToken("(");

			// Decouverte d'un noeud
			if (type.equals("CN")) {
				model = this.loadNode(model, parser);
				continue; // Prochaine commande
			}

			// Decouverte d'un arc
			if (type.equals("CA")) {
				model = this.loadArc(model, parser);
				continue; // Prochaine commande
			}

			// Decouverte d'attribut sur une ligne
			if (type.equals("CT")) {
				model = this.loadAttribute(model, parser);
				continue;
			}

			// Creation d'une ligne dans un attribut multi-ligne
			if (type.equals("CM")) {
				model = this.loadMAttribute(model, parser);
				continue;
			}

			// Decouverte d'une position de noeud
			if (type.equals("PO") || type.equals("pO")) {
				model = this.loadPosition(model, parser);
				continue;
			}

			// Decouverte d'une position intermediaire
			if (type.equals("PI")) {
				model = this.loadInflexPoint(model, parser);
				continue;
			}

			// Decouverte d'une position de texte
			if (type.equals("PT")) {
				model = this.loadTextPosition(model, parser);
				continue;
			}

			// Decouverte d'une position de texte
			if (type.equals("SU")) {
				int toDel = Integer.parseInt(parser.parseInt(")"));
				try {
					IArc a = model.getIdArc(toDel);
					if (a != null) {
						model.delArc(a);
					}
					INode n = model.getIdNode(toDel);
					if (n != null) {
						model.delNode(n);
					}
				} catch (ModelException e) {
					throw new SyntaxErrorException("Impossible de supprimer l'element : " + toDel);
				}
				continue;
			}

			throw new SyntaxErrorException("Commande non reconnue : " + type);
		}

		return model;
	}
}
