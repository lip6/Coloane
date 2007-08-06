package fr.lip6.move.coloane.api.model;

import fr.lip6.move.coloane.api.main.Api;

import java.util.Vector;

/**
 * Enrichissement de la definition d'un attribut generique par sa traduction en CAMI
 * @see fr.lip6.move.coloane.interfaces.model.Attribute
 */
public class Attribute extends fr.lip6.move.coloane.interfaces.model.Attribute {

	private static final long serialVersionUID = 1L;

	/** Constructeur */
	public Attribute(String name, String value, int refId) {
		super(name, value, refId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Traduit un objet Attribute en la chaine de caracteres CAMI correspondante.
	 * @return String[]
	 */
	public final String[] translate() {
		Api.apiLogger.entering("Attribute",	"translate");
		StringBuffer s;
		String[] stringToReturn = null;
		String [] valuesTable = null;
		String val = this.getValue();
		Vector<String> vectorStringToReturn = new Vector<String>();


		valuesTable = val.split("(\n\r)|(\r\n)|(\n)|(\r)");

		//  if (val.equals("")){vectorStringToReturn.addElement(new String(""));}

		if ((valuesTable.length == 1) && (valuesTable[0].length() <= 255)) {

			if (!val.equals("")) {
				s = new StringBuffer();
				s.append("CT(");
				s.append(this.name.length() + ":" + this.name);
				s.append(",");
				s.append(this.refId);
				s.append(",");
				s.append(valuesTable[0].length() + ":" + valuesTable[0]);
				s.append(")");
				vectorStringToReturn.addElement(s.toString());
			}


		} else {
			int lineCounter = 1; //compteur ligne utile

			for (int i = 0; i < valuesTable.length; i++) {

				if (valuesTable[i].length() < 255) {
					s = new StringBuffer();
					s.append("CM(");
					s.append(this.name.length() + ":" + this.name);
					s.append(",");
					s.append(this.refId);
					s.append(",");
					s.append(lineCounter++);
					s.append(",");
					s.append(1); //archaisme de Framekit
					s.append(",");
					s.append(valuesTable[i].length() + ":" + valuesTable[i]);
					s.append(")");
					vectorStringToReturn.addElement(s.toString());
				} else {
					int start = 0;
					int end = 255;


					while (end < valuesTable[i].length()) {

						String sub = valuesTable[i].substring(start, end);

						s = new StringBuffer();
						s.append("CM(");
						s.append(this.name.length() + ":" + this.name);
						s.append(",");
						s.append(this.refId);
						s.append(",");
						s.append(lineCounter++);
						s.append(",");
						s.append(1); //archaisme de Framekit
						s.append(",");
						s.append(sub.length() + ":" + sub);
						s.append(")");
						vectorStringToReturn.addElement(s.toString());

						start += 255; 
						end += 255;
					}

					String sub = valuesTable[i].substring(start, valuesTable[i].length());
					s = new StringBuffer();
					s.append("CM(");
					s.append(this.name.length() + ":" + this.name);
					s.append(",");
					s.append(this.refId);
					s.append(",");
					s.append(lineCounter++);
					s.append(",");
					s.append(1); //archaisme de Framekit
					s.append(",");
					s.append(sub.length() + ":" + sub);
					s.append(")");
					vectorStringToReturn.addElement(s.toString());
				}
			}



			if (this.xPosition != 0 || this.yPosition != 0) {
				s = new StringBuffer();
				s.append("PT(");
				s.append(this.refId);
				s.append(",");
				s.append(this.name.length() + ":" + this.name);
				s.append(",");
				s.append(this.xPosition);
				s.append(",");
				s.append(this.yPosition);
				s.append(")");
				vectorStringToReturn.addElement(s.toString());
			}
		}
		stringToReturn = new String[vectorStringToReturn.size()];
		vectorStringToReturn.toArray(stringToReturn);
		Api.apiLogger.exiting("Attribute", "translate", stringToReturn);
		return stringToReturn;
	}
}
