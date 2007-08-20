package fr.lip6.move.coloane.api.model;

import fr.lip6.move.coloane.api.main.Api;

import java.util.Arrays;
import java.util.Vector;

/**
 * Enrichissement de la definition d'un noeud generique par sa traduction en CAMI
 * @see fr.lip6.move.coloane.interfaces.model.Node
 */
public class Node extends fr.lip6.move.coloane.interfaces.model.Node {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.Node#Node(String,int,int,int)
	 */
	public Node(String nodeType, int x, int y, int id) {
		super(nodeType, x, y, id);
	}

	/**
	 * Traduction du noeud noeud en chaines de commandes CAMI
	 * @return String[]
	 */
	public final String[] translate() {
		Api.apiLogger.entering("Node", "translate");
		String[] stringToReturn;
		StringBuffer s;
		Vector<String> vectorStringToReturn = new Vector<String>(0);

		s = new StringBuffer();
		s.append("CN(");
		s.append(getNodeType().length() + ":" + this.getNodeType());
		s.append(",");
		s.append(getId());
		s.append(")");
		vectorStringToReturn.addElement(s.toString());

		s = new StringBuffer();
		s.append("PO(");
		s.append(getId());
		s.append(",");
		s.append(getXPosition());
		s.append(",");
		s.append(getYPosition());
		s.append(")");
		vectorStringToReturn.addElement(s.toString());

		for (int i = 0; i < this.getListOfAttrSize(); i++) {
			vectorStringToReturn.addAll(Arrays.asList(this.getNthAttr(i).translate()));
		}

		stringToReturn = new String[vectorStringToReturn.size()];
		vectorStringToReturn.toArray(stringToReturn);
		Api.apiLogger.exiting("Node", "translate", stringToReturn);
		return stringToReturn;
	}
}

