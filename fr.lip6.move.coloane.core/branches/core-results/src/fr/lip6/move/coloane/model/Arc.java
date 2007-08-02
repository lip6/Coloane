package fr.lip6.move.coloane.model;

import java.util.Arrays;
import java.util.Vector;
import java.io.Serializable;


/**
 * Classe representant un arc d'un modele dans les differents formalismes
 * implantes. Un arc possede obligatoire un noeud en entree et en sortie.
 * Un liste d'attribut peut etre en plus attache a un arc.
 * 
 * @author Olivier Rouquette, Christophe Janton
 */
public class Arc extends fr.lip6.move.coloane.interfaces.model.Arc implements Serializable { 

	private static final long serialVersionUID = 1L;

	/** Constructeur */
	public Arc(String arcType, int id) {
		super(arcType, id);
	}

	public Arc(String arcType) {
		super(arcType);
	}


	/**
	 * Traduit un objet Arc en chaine de caracteres CAMI correspondante
	 * @return String[]
	 */
	public String[] translate() {
		if (this.startingNode == null || this.endingNode == null) {
			return null;
		} else {
			String[] stringToReturn;
			StringBuffer s;
			Vector<String> vectorStringToReturn = new Vector<String>(0);

			s = new StringBuffer();
			s.append("CA(");
			s.append(this.arcType.length() + ":" + this.arcType);
			s.append(",");
			s.append(this.id);
			s.append(",");
			s.append(this.startingNode.getId());
			s.append(",");
			s.append(this.endingNode.getId());
			s.append(")");
			vectorStringToReturn.addElement(s.toString());

			//Traduction des points intermediaires
			for (int i = 0; i < this.getListOfPI().size(); i++) {
				s = new StringBuffer();
				s.append("PI(");
				s.append("-1");
				s.append(",");
				s.append(this.getNthPI(i).getXPosition());
				s.append(",");
				s.append(this.getNthPI(i).getYPosition());
				s.append(",0,");
				s.append("-1");
				s.append(")");
				vectorStringToReturn.addElement(s.toString()); 
			}

			for (int i = 0; i < this.getListOfAttrSize(); i++) {
				vectorStringToReturn.addAll(Arrays.asList(this.getNthAttr(i).translate()));
			}

			stringToReturn = new String[vectorStringToReturn.size()];
			vectorStringToReturn.toArray(stringToReturn);
			return stringToReturn;
		}
	}
}

