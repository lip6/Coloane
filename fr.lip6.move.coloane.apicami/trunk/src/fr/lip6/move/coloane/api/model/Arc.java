package fr.lip6.move.coloane.api.model;

import fr.lip6.move.coloane.api.main.Api;

import java.util.Arrays;
import java.util.Vector;

/**
 * Enrichissement de la definition d'un arc generique par sa traduction en CAMI
 * @see fr.lip6.move.coloane.interfaces.model.Arc
 */
public class Arc extends fr.lip6.move.coloane.interfaces.model.Arc {

	private static final long serialVersionUID = 1L;
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.Arc#Arc(String, int)
	 */
	public Arc(String arcType, int id) {
		super(arcType, id);
	}


	/**
	 * Traduit un objet Arc en chaines de caracteres CAMI correspondantes
	 * @return String[]
	 */
	public final String[] translate() {
		Api.apiLogger.entering("Arc", "translate");
		if (this.startingNode == null || this.endingNode == null) {
			Api.apiLogger.exiting("Arc", "translate", null);
			return null;
		} else {
			String[] stringToReturn;
			StringBuffer s;
			Vector<String> vectorStringToReturn = new Vector<String>(0);

			s = new StringBuffer();
			s.append("CA(");
			s.append(this.type.length() + ":" + this.type);
			s.append(",");
			s.append(this.id);
			s.append(",");
			s.append(this.startingNode.getId());
			s.append(",");
			s.append(this.endingNode.getId());
			s.append(")");
			vectorStringToReturn.addElement(s.toString());

			for (int i = 0; i < this.getListOfAttrSize(); i++) {
				vectorStringToReturn.addAll(Arrays.asList(this.getNthAttr(i).translate()));
			}

			stringToReturn = new String[vectorStringToReturn.size()];
			vectorStringToReturn.toArray(stringToReturn);
			Api.apiLogger.exiting("Arc", "translate", stringToReturn);
			return stringToReturn;
		}
	}
}

