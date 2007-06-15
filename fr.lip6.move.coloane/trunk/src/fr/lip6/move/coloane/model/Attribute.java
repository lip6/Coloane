package fr.lip6.move.coloane.model;

import java.util.Vector;
import java.io.Serializable;

/**
 * Classe representant un attribut d'un modele. Un attribut se structure de la
 * maniere suivante :
 * <ul>
 * <li> Nom de l'attribut sous forme de chaine de caracteres
 * <li> La valeur de l'attribut (ensemble de chaine de caracteres java
 * quelconque representant les differents lignes)
 * <li> Abscisse et ordonnee de l'attribut
 * <li> Identifiant de l'element d'un modele dont il est affecte
 * </ul>
 * 
 */
public class Attribute extends fr.lip6.move.coloane.interfaces.model.Attribute
		implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructeur */
	public Attribute(String name, String value, int refId) {
		super(name, value, refId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Traduit un objet Attribute en la chaine de caracteres CAMI
	 * correspondante.
	 * 
	 * @return String[]
	 */
	public String[] translate() {
		StringBuffer s;
		String[] stringToReturn = null;
		String[] tab_val = null;
		String val = this.getValue();
		Vector<String> vectorStringToReturn = new Vector<String>();

		// Decoupage de la chaine de charactere suivant un pattern
		tab_val = val.split("(\n\r)|(\r\n)|(\n)|(\r)");

		// if (val.equals("")){vectorStringToReturn.addElement(new String(""));}

		// Si la tableau obtenu est de taille 1 et que la ligne est de taille <
		// à 255, on a un attribut d'une ligne
		if (tab_val.length == 1 && tab_val[0].length() <= 255) {

			if (!val.equals("")) {
				s = new StringBuffer();
				s.append("CT(");
				s.append(this.name.length() + ":" + this.name);
				s.append(",");
				s.append(this.refId);
				s.append(",");
				s.append(tab_val[0].length() + ":" + tab_val[0]);
				s.append(")");
				vectorStringToReturn.addElement(s.toString());
			}

			// Sinon, on a un attribut multiligne
		} else {
			int cpt_lig = 1; // compteur ligne utile

			for (int i = 0; i < tab_val.length; i++) {

				// Pour chaque ligne, on teste si on doit la decouper car trop
				// longue
				if (tab_val[i].length() < 255) {
					s = new StringBuffer();
					s.append("CM(");
					s.append(this.name.length() + ":" + this.name);
					s.append(",");
					s.append(this.refId);
					s.append(",");
					s.append(cpt_lig++);
					s.append(",");
					s.append(1); // archaisme de Framekit
					s.append(",");
					s.append(tab_val[i].length() + ":" + tab_val[i]);
					s.append(")");
					vectorStringToReturn.addElement(s.toString());

				} else {
					int start = 0;
					int end = 255;

					// Traduction des n*255 premiers caracteres
					while (end < tab_val[i].length()) {

						String sub = tab_val[i].substring(start, end);

						s = new StringBuffer();
						s.append("CM(");
						s.append(this.name.length() + ":" + this.name);
						s.append(",");
						s.append(this.refId);
						s.append(",");
						s.append(cpt_lig++);
						s.append(",");
						s.append(1); // archaisme de Framekit
						s.append(",");
						s.append(sub.length() + ":" + sub);
						s.append(")");
						vectorStringToReturn.addElement(s.toString());

						start += 255;
						end += 255;
					}
					// Traduction des caracteres restants
					String sub = tab_val[i].substring(start, tab_val[i]
							.length());
					s = new StringBuffer();
					s.append("CM(");
					s.append(this.name.length() + ":" + this.name);
					s.append(",");
					s.append(this.refId);
					s.append(",");
					s.append(cpt_lig++);
					s.append(",");
					s.append(1); // archaisme de Framekit
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
		return stringToReturn;
	}
}
