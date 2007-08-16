package fr.lip6.move.coloane.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.utils.CamiParser;
import fr.lip6.move.coloane.main.Coloane;

import java.io.File;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Cette classe represente un modele generique.<br>
 * Elle permet la manipulation des elements du modele et le parcours de ceux-ci.<br>
 * Un modele se compose de :
 * <ul>
 * <li> Une liste de noeuds</li>
 * <li> Une liste d'attributs</li>
 * <li> Une liste d'arcs</li>
 * </ul>
 * A noter qu'un modele a toujours un identifiant egal a 1<br>
 * Le modele generique porte l'information sur son formalisme en tant que chaine
 * de caracteres.<br>
 *
 * @see fr.lip6.move.coloane.interfaces.model.Model
 */
public class Model extends fr.lip6.move.coloane.interfaces.model.Model {

	/**
	 * Constructeur
	 */
	public Model() {
		super();
	}

	/**
	 * Constructeur
	 * @param commands La suite de commandes CAMI pour construire le modele
	 * @throws SyntaxErrorException
	 */
	public Model(Vector<String> commands) throws SyntaxErrorException, ModelException {
		super(commands);
	}

	/**
	 * Constructeur
	 * @param commandsFile Le fichier contenant les commandes CAMI pour construire le modele
	 * @throws SyntaxErrorException
	 */
	public Model(File commandsFile) throws SyntaxErrorException, ModelException {
		super(commandsFile);
	}

	/**
	 * Construction du modele a partir d'un vecteur de commandes<br>
	 * Cette method est a implementee par les concepteurs d'API a partir du protocole qu'ils utilisent
	 * @param commands Un vecteur de commandes pour construire le modele
	 * @throws SyntaxErrorException
	 */
	@Override
	protected final void buildModel(Vector camiCommande) throws SyntaxErrorException, ModelException {
		String line = null;
		String type = null;

		StringTokenizer tokenizer;
		CamiParser parser;

		for (int k = 0; k < camiCommande.size(); k++) {
			line = (String) camiCommande.get(k);
			tokenizer = new StringTokenizer(line);
			parser = new CamiParser(line.substring(3));

			type = tokenizer.nextToken("(");

			// Decouverte d'un noeud
			if (type.equals("CN")) {
				String nodeType = parser.parseString(",");
				String nodeId = parser.parseInt(")");

				// Si le noeud en cours n'est pas le noeud principal du modele
				if (Integer.parseInt(nodeId) != 1) {
					INode node = new Node(nodeType, 0, 0, Integer.parseInt(nodeId));
					this.addNode(node);
				} else {
					this.setFormalism(nodeType);
				}
				continue; // Prochaine commande
			}

			// Decouverte d'un arc
			if (type.equals("CA")) {
				String arcType = parser.parseString(",");
				String arcId = parser.parseInt(",");
				String from = parser.parseInt(",");
				String to = parser.parseInt(")");

				// Recherche de la source et de la cible
				INode nodeBegin = getANode(Integer.parseInt(from));
				INode nodeEnd = getANode(Integer.parseInt(to));

				if (nodeBegin == null || nodeEnd == null) {
					throw new SyntaxErrorException(Coloane.getTranslate().getString("model.Model.9")); //$NON-NLS-1$
				}

				// Creation de l'arc
				IArc arc = new Arc(arcType, Integer.parseInt(arcId));
				arc.setStartingNode(nodeBegin);
				arc.setEndingNode(nodeEnd);
				nodeBegin.addOutputArc(arc);
				nodeEnd.addInputArc(arc);
				this.addArc(arc);
				continue; // Prochaine commande
			}

			// Decouverte d'attribut sur une ligne
			if (type.equals("CT")) {
				String value = new String();
				String name = parser.parseString(",");
				String ref = parser.parseInt(",");
				value = parser.parseString(")");

				// Creation effective de l'attribut
				IAttribute attr = new Attribute(name, value, Integer.parseInt(ref));

				// L'attribut peut directement etre attache au modele
				if (Integer.parseInt(ref) == 1) {
					this.addAttribute(attr);
					continue;
				}

				// Est-ce que l'attribut s'attache a un arc ?
				IArc arc = getAnArc(Integer.parseInt(ref));
				if (arc != null) {
					arc.addAttribute(attr);
					continue;
				}

				// Est-ce que l'attribut s'attache a un noeud ?
				INode node = getANode(Integer.parseInt(ref));
				if (node != null) {
					node.addAttribute(attr);
					continue;
				}

				// Sinon on retourne une erreur
				throw new SyntaxErrorException(Coloane.getTranslate().getString("model.Model.14")); //$NON-NLS-1$
			}

			// Creation d'une ligne dans un attribut multi-ligne
			if (type.equals("CM")) {
				String value = new String();
				boolean found = false;

				String name = parser.parseString(",");
				String ref = parser.parseInt(",");
				parser.parseInt(",");
				parser.parseInt(",");
				value = parser.parseString(")");
				tokenizer = new StringTokenizer(line);

				// Si le referent a comme identifiant 1, alors il faut
				// attacher l'attribut au modele
				if (Integer.parseInt(ref) == 1) {

					// On recherche l'attribut a modifier
					for (int i = 0; i < this.getListOfAttrSize(); i++) {
						IAttribute a = this.getNthAttr(i);
						if (name.equals(a.getName())) {
							a.setValue(a.getValue() + "\r" + value);
							found = true;
							break; // On sort de cette boucle de recherche
						}
					}

					// Si aucun attribut n'a ete trouve... On en cree un!
					if (!found) {
						IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
						this.addAttribute(att);
					}
					continue;
				}

				// On cherche a savoir si l'attribut s'attache a un arc ?
				IArc arc = getAnArc(Integer.parseInt(ref));
				if (arc != null) {
					for (int i = 0; i < arc.getListOfAttrSize(); i++) {
						IAttribute att = arc.getNthAttr(i);
						if (name.equals(att.getName())) {
							att.setValue(att.getValue() + "\r" + value);
							found = true;
							break;
						}
					}

					// Si aucun attribut de l'arc ne correspond on en cree un
					if (!found) {
						// On cree un nouvel attribut
						IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
						arc.addAttribute(att);
					}
					continue;
				}

				// On cherche a savoir si l'attribut s'attache a un noeud ?
				INode node = getANode(Integer.parseInt(ref));
				if (node != null) {
					for (int i = 0; i < node.getListOfAttrSize(); i++) {
						IAttribute att = node.getNthAttr(i);
						if (name.equals(att.getName())) {
							att.setValue(att.getValue() + "\r" + value);
							found = true;
							break;
						}
					}

					// Si aucun attribut du noeud ne correspond... On en cree un
					if (!found) {
						IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
						node.addAttribute(att);
					}
				}
			}

			// Decouverte d'une position de noeud
			if (type.equals("PO") || type.equals("pO")) {
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
					this.setPosition(Integer.parseInt(x), Integer.parseInt(y));
				}

				if (Integer.parseInt(ref) != 1) {
					INode node = getANode(Integer.parseInt(ref));
					if (node != null) {
						node.setPosition(Integer.parseInt(x), Integer.parseInt(y));
					} else {
						throw new SyntaxErrorException(Coloane.getTranslate().getString("model.Model.42"));
					}
				}
			}

			// Decouverte d'une position intermediaire
			if (type.equals("PI")) {
				String ref = parser.parseInt(",");
				String x = "";
				String y = "";

				// !! Attention bidouille pour prendre en compte le PI avec ref == -
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
				IArc arc = this.getAnArc(Integer.parseInt(ref));

				if (arc != null) {
					arc.addPI(Integer.parseInt(x), Integer.parseInt(y));
				} else {
					throw new SyntaxErrorException("La position est attachee a un element introuvable ou incorrect");
				}
			}

			// Decouverte d'une position de texte
			if (type.equals("PT")) {
				String ref = parser.parseInt(",");
				String name = parser.parseString(",");
				String x = parser.parseInt(",");
				String y = parser.parseInt(")");

				if (Integer.parseInt(ref) == 1) {
					for (int i = 0; i < this.getListOfAttrSize(); i++) {
						IAttribute attr = this.getNthAttr(i);
						if (attr.getName().equals(name)) {
							attr.setPosition(Integer.parseInt(x), Integer.parseInt(y));
							break;
						}
					}
					continue;
				}

				IArc arc = getAnArc(Integer.parseInt(ref));
				if (arc != null) {
					for (int i = 0; i < arc.getListOfAttrSize(); i++) {
						IAttribute attr = arc.getNthAttr(i);
						if (attr.getName().equals(name)) {
							attr.setPosition(Integer.parseInt(x), Integer.parseInt(y));
							break;
						}
					}
					continue;
				}

				INode node = getANode(Integer.parseInt(ref));
				if (node != null) {
					for (int i = 0; i < node.getListOfAttrSize(); i++) {
						IAttribute attr = node.getNthAttr(i);
						if (attr.getName().equals(name)) {
							attr.setPosition(Integer.parseInt(x), Integer.parseInt(y));
							break;
						}
					}
					continue;
				}

				throw new SyntaxErrorException(Coloane.getTranslate().getString("model.Model.41")); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Traduit un modele en chaines de caracteres CAMI correspondantes.
	 * @return String[] la chaine de caracteres CAMI correspondante a l'objet Model
	 */
	public final String[] translate() {
		Vector<String> vec = new Vector<String>();
		String[] nodes;
		String[] arcs;
		StringBuffer s;

		// Ajout du modele
		s = new StringBuffer();
		s.append("CN(");
		s.append(this.getFormalism().length() + ":" + this.getFormalism() + ",");
		s.append("1");
		s.append(")");
		vec.addElement(s.toString());

		// Ajout des attributs
		for (int i = 0; i < this.getListOfAttrSize(); i++) {
			vec.addAll(Arrays.asList(this.getNthAttr(i).translate()));
		}

		// Ajout des noeuds
		for (int i = 0; i < this.getListOfNodeSize(); i++) {
			nodes = (this.getNthNode(i)).translate();

			for (int j = 0; j < nodes.length; j++) {
				if (!nodes[j].equals("")) {
					vec.add(nodes[j]);
				}
			}
		}

		// Ajout des arcs
		for (int i = 0; i < this.getListOfArcSize(); i++) {
			arcs = (this.getNthArc(i)).translate();

			for (int j = 0; j < arcs.length; j++) {
				if (!arcs[j].equals("")) {
					vec.add(arcs[j]);
				}
			}

		}
		String[] cami = new String[vec.size()];
		vec.toArray(cami);
		return cami;
	}
}
