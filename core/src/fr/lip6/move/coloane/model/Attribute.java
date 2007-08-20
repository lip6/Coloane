package fr.lip6.move.coloane.model;

import java.util.Vector;

/**
 * Classe representant un attribut d'un modele. Un attribut se structure de la maniere suivante :
 * <ul>
 *   <li> Nom de l'attribut sous forme de chaine de caracteres
 *   <li> La valeur de l'attribut (ensemble de chaine de caracteres java quelconque representant les differents lignes)
 *   <li> Abscisse et ordonnee de l'attribut
 *   <li> Identifiant de l'element d'un modele dont il est affecte
 * </ul>
 */
public class Attribute extends fr.lip6.move.coloane.interfaces.model.Attribute {

	private static final int MAXLENGTH = 255;

	/** Constructeur */
	public Attribute(String name, String value, int refId) {
		super(name, value, refId);
	}

	/**
	 * Traduit un objet Attribute en la chaine de caracteres CAMI
	 * correspondante.
	 *
	 * @return String[]
	 */
	public final String[] translate() {
		StringBuffer s;
		String[] valueTable = null;
		String val = this.getValue();
		Vector<String> toReturn = new Vector<String>();

		// Decoupage de la chaine de charactere suivant un pattern
		valueTable = val.split("(\n\r)|(\r\n)|(\n)|(\r)");

		// Si la tableau obtenu est de taille 1 et que la ligne est de taille < a 255, on a un attribut d'une ligne
		if (valueTable.length == 1 && valueTable[0].length() <= MAXLENGTH) {

			if (!val.equals("")) {
				s = new StringBuffer();
				s.append("CT(");
				s.append(getName().length() + ":" + getName() + ",");
				s.append(getRefId() + ",");
				s.append(valueTable[0].length() + ":" + valueTable[0]);
				s.append(")");
				toReturn.addElement(s.toString());
			}

		// Sinon, on a un attribut multiligne
		} else {
			int lineCounter = 1; // compteur ligne utile

			for (int i = 0; i < valueTable.length; i++) {

				// Pour chaque ligne, on teste si on doit la decouper car trop longue
				if (valueTable[i].length() < MAXLENGTH) {
					s = new StringBuffer();
					s.append("CM(");
					s.append(getName().length() + ":" + getName() + ",");
					s.append(getRefId() + ",");
					s.append(lineCounter++ + ",");
					s.append(1 + ","); // archaisme de Framekit
					s.append(valueTable[i].length() + ":" + valueTable[i]);
					s.append(")");
					toReturn.addElement(s.toString());
				} else {
					int start = 0;
					int end = MAXLENGTH;

					// Traduction des n*255 premiers caracteres
					while (end < valueTable[i].length()) {
						String sub = valueTable[i].substring(start, end);
						s = new StringBuffer();
						s.append("CM(");
						s.append(getName().length() + ":" + getName() + ",");
						s.append(getRefId() + ",");
						s.append(lineCounter++ + ",");
						s.append(1); // archaisme de Framekit
						s.append(",");
						s.append(sub.length() + ":" + sub);
						s.append(")");
						toReturn.addElement(s.toString());

						start += MAXLENGTH;
						end += MAXLENGTH;
					}

					// Traduction des caracteres restants
					String sub = valueTable[i].substring(start, valueTable[i].length());
					s = new StringBuffer();
					s.append("CM(");
					s.append(getName().length() + ":" + getName() + ",");
					s.append(getRefId() + ",");
					s.append(lineCounter++ + ",");
					s.append(1 + ","); // archaisme de Framekit
					s.append(sub.length() + ":" + sub);
					s.append(")");
					toReturn.addElement(s.toString());
				}
			}

		}

		//Traduit la position de l'attribut
		if (getXPosition() != 0 || getYPosition() != 0) {
			s = new StringBuffer();
			s.append("PT(");
			s.append(getRefId() + ",");
			s.append(getName().length() + ":" + getName() + ",");
			s.append(getXPosition() + ",");
			s.append(getYPosition());
			s.append(")");
			toReturn.addElement(s.toString());
		}

		String[] result = new String[toReturn.size()];
		toReturn.toArray(result);
		return result;
	}
}
