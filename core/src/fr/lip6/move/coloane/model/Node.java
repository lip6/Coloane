package fr.lip6.move.coloane.model;

import java.util.Arrays;
import java.util.Vector;

/**
 * Le noeud d'un modele
 */
public class Node extends fr.lip6.move.coloane.interfaces.model.Node {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param nodeType Type de noeud
	 * @param x Position en X
	 * @param y Position en Y
	 * @param id ID du noeud
	 */
	public Node(String nodeType, int x, int y, int id) {
		super(nodeType, x, y, id);
	}

	/**
	 * Constructeur
	 * @param nodeType Type du noeud
	 * @param x Position en X
	 * @param y Position en Y
	 */
	public Node(String nodeType, int x, int y) {
		super(nodeType, x, y);
	}

	/**
	 * Constructeur
	 * @param nodeType Type du noeud
	 */
	public Node(String nodeType) {
		super(nodeType);
	}

	/**
	 * Traduction du noeud noeud en chaine de commandes CAMI
	 * @return String[]
	 */
	public final String[] translate() {
		StringBuffer s;
		Vector<String> toReturn = new Vector<String>(0);

		s = new StringBuffer();
		s.append("CN(");
		s.append(getNodeType() + ":" + getNodeType() + ",");
		s.append(getId());
		s.append(")");
		toReturn.addElement(s.toString());

		s = new StringBuffer();
		s.append("PO(");
		s.append(getId() + ",");
		s.append(getXPosition() + ",");
		s.append(getYPosition());
		s.append(")");
		toReturn.addElement(s.toString());

		for (int i = 0; i < this.getListOfAttrSize(); i++) {
			toReturn.addAll(Arrays.asList(this.getNthAttr(i).translate()));
		}

		String[] result = new String[toReturn.size()];
		toReturn.toArray(result);
		return result;
	}
}

