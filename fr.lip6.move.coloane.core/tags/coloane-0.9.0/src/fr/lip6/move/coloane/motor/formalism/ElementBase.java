package fr.lip6.move.coloane.motor.formalism;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Cette classe represente un element du base du formalisme.
 * 
 * @author Thomas d'Erceville
 */

public class ElementBase implements Serializable {
	
	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Nom associe a l'element de base. */
	private String name;

	/** Largeur du dessin representant l'element de base. */
	private int width = -1;

	/** Hauteur du dessin representant l'element de base. */
	private int height = -1;

	/** Booleen indiquant si le dessin de l'element de base est rempli ou non. */
	private boolean isFilled = false;

	/** Formalisme auquel appartient l'element de base. */
	private Formalism formalism = null;
	
	/** Numero indiquant quel dessin faire. */
	private int numFigure;

	/** Adresse du fichier representant l'icone de 16 pixels sur 16. */
	private String addrIcone16 = null;

	/** Adresse du fichier representant l'icone de 24 pixels sur 24. */
	private String addrIcone24 = null;

	/** Tableau des differents attributs de l'element de base. */
	private ArrayList<AttributeFormalism> listOfAttributeElem = new ArrayList<AttributeFormalism>(0);

	/**
	 * Constructeur de la classe
	 * 
	 * @param name Nom de l'element de base.
	 * @param width Largeur du dessin de l'element de base.
	 * @param height Hauteur du dessin de l'element de base.
	 * @param isFilled Flag indiquant si la figure sera remplie.
	 * @param numFigure Numero permettant de savoir quel figure dessiner pour cet element.
	 */
	public ElementBase(String name, int numFigure, int width, int height, boolean isFilled) {
		this.name = name;
		this.numFigure = numFigure;
		this.width = width;
		this.height = height;
		this.isFilled = isFilled;
		this.numFigure = numFigure;
	}

	/**
	 * Retourne l'attribut designe par son index
	 * 
	 * @param index Index de l'attribut a recuperer 
	 * @return AttributeFormalism
	 */
	public AttributeFormalism getNthAttr(int index) {
		AttributeFormalism attr;
		try {
			attr = (AttributeFormalism) this.listOfAttributeElem.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return attr;
	}

	/**
	 * Ajoute un attribut a l'element de base
	 * @param attrForm Attribut a ajouter a la liste des attributs
	 */
	public void addAttributeFormalism(AttributeFormalism attrForm) {
		listOfAttributeElem.add(attrForm);
	}

	/**
	 * Retourne le nom de l'element de base
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retourne l'indication sur le dessin
	 * @return int
	 */
	public int getNumFigure() {
		return numFigure;
	}


	/**
	 * Retourne la largeur de l'objet
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Retourne la hauteur de l'objet
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Retourne la propriete de remplissage de l'objet
	 * @return boolean
	 */
	public boolean getIsFillede() {
		return isFilled;
	}


	/**
	 * Retourne l'adresse du fichier donnant l'icone de la figure.
	 * @return String
	 */
	public String getAddrIcone16() {
		return addrIcone16;
	}

	/**
	 * Positionne la valeur de l'adresse de l'icone
	 * @param addrIcone L'adresse de l'icone
	 */
	public void setAddrIcone16(String addrIcone) {
		this.addrIcone16 = addrIcone;
	}

	/**
	 * Retourne la liste des attributs d'un element.
	 * @return ArrayList
	 */
	public ArrayList getListOfAttribute() {
		return listOfAttributeElem;
	}

	/**
	 * Retourne l'adresse du fichier de l'icone de 24 pixels
	 * @return String
	 */
	public String getAddrIcone24() {
		return addrIcone24;
	}
	
	/**
	 * Definit l'adresse de l'icone de 24 pixels
	 * @param addrIcone24 L'adresse de l'icone de 24 pixels
	 */
	public void setAddrIcone24(String addrIcone24) {
		this.addrIcone24 = addrIcone24;
	}

	/**
	 * @return Retourne une chaine de caracteres.
	 */
	public String toString() {
		Iterator it;
		AttributeFormalism attr = null;
		String str = "Element de Base :";
		str.concat("\n  Name" + name);
		for (it = listOfAttributeElem.iterator(); it.hasNext();) {
			str.concat(attr.toString());
		}
		return str;
	}
	
	/**
	 * Retourne le formalisme de l'element de base
	 * @return Formalism
	 */
	public Formalism getFormalism() {
		return formalism;
	}

	/**
	 * Definit le formalisme auquel appartient l'element de base.
	 * @param formalism Le formalisme
	 */
	public void setFormalism(Formalism formalism) {
		this.formalism = formalism;
	}
}
