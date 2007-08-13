package fr.lip6.move.coloane.model;

import java.util.Arrays;
import java.util.Vector;

/**
 * Classe representant un arc d'un modele dans les differents formalismes
 * implantes. Un arc possede obligatoire un noeud en entree et en sortie.
 * Un liste d'attribut peut etre en plus attache a un arc.
 */
public class Arc extends fr.lip6.move.coloane.interfaces.model.Arc {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param arcType
	 * @param id
	 */
	public Arc(String arcType, int id) {
		super(arcType, id);
	}

	/**
	 * Constructeur
	 * @param arcType
	 */
	public Arc(String arcType) {
		super(arcType);
	}

	/**
	 * <b>Objet > CAMI</b>
	 * Traduit un objet Arc en chaine de caracteres CAMI correspondante
	 * @return String[]
	 */
	public final String[] translate() {
		if (getStartingNode() == null || getEndingNode() == null) {
			return null;
		} else {
			StringBuffer s;
			Vector<String> toReturn = new Vector<String>(0);

			s = new StringBuffer();
			s.append("CA(");
			s.append(getArcType().length() + ":" + getArcType() + ",");
			s.append(getId() + ",");
			s.append(getStartingNode().getId() + ",");
			s.append(getEndingNode().getId() + ")");
			toReturn.addElement(s.toString());

			// Traduction des points intermediaires
			for (int i = 0; i < this.getListOfPI().size(); i++) {
				s = new StringBuffer();
				s.append("PI(");
				s.append("-1,");
				s.append(this.getNthPI(i).getXPosition() + ",");
				s.append(this.getNthPI(i).getYPosition() + ",");
				s.append("0,");
				s.append("-1)");
				toReturn.addElement(s.toString());
			}

			// Traduction des attributs
			for (int i = 0; i < this.getListOfAttrSize(); i++) {
				toReturn.addAll(Arrays.asList(this.getNthAttr(i).translate()));
			}

			String[] result = new String[toReturn.size()];
			toReturn.toArray(result);
			return result;
		}
	}
}

