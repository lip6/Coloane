package fr.lip6.move.coloane.api.objects;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.IResultsCom;

/**
 * Cette classe defini la listes des reslutats renvoyes par la plate-forme
 * a la suite d'un appel de service. Ces resultats doivent etre envoyes a Coloane
 * pour affichage.
 * 
 */

public class ResultsCom implements IResultsCom {

	/** La liste des descriptions de resultats */
	private Vector<String> description;
	/** La liste des resultats */
	private Vector<String> elements;

	/**
	 * Constructeur
	 */
	public ResultsCom() {
		this.description = new Vector<String>();
		this.elements = new Vector<String>();
	}

	/**
	 * Ajout d'un element de resultats
	 * @param element Une chaine de caractere indiquant le resultat
	 */
	public void addElement(String element) {
		this.elements.add(element);
	}

	/**
	 * Ajout d'une description d'un resultat
	 * @param description Une chaine de caractere decrivant un resultat
	 */
	public void addDescription(String description) {
		this.description.add(description);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IResultatsCom#getListOfElement()
	 */
	public Vector<String> getListOfElement() {
		return this.elements;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IResultatsCom#getListOfDescription()
	 */
	public Vector<String> getListOfDescription() {
		return this.description;
	}
	
	/**
	 * Retourne une sous-liste des resultats
	 * @param start Indice de depart des resultats desires
	 * @return La sous liste des resutlats depuis l'indice de debut jusqu'a la fin
	 */
	public Vector<String> getSublistOfDescription(int start) {
		Vector<String> tmp = new Vector<String>();
		for (int i=start; i < this.description.size(); i++) {
			tmp.add(this.description.elementAt(i));
		}
		return tmp;
	}
	
	/**
	 * Retourne la premiere description
	 * @return La chaine de caractere correspondant a la premiere description
	 */
	public String getHeadDescription() {
		return this.description.elementAt(0);
	}

}
