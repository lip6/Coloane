package fr.lip6.move.coloane.motor.formalism;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe represente un element du base du formalisme.
 * Un element de base est le composant d'un formalisme.
 * Un element de base contient toute les informations decrivant ce composant de formalisme :
 * <ul>
 * 	<li>Nom et Nom de palette</li>
 * 	<li>Largeur, Hauteur</li>
 * 	<li>Details graphiques : Remplissage, Icones etc...</li>
 * </ul>
 */

public class ElementBase implements Serializable {
	
	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Nom associe a l'element de base. */
	private String name;
	
	/** Nom associe a l'element de base. */
	private String paletteName;

	/** Largeur du dessin representant l'element de base. */
	private int width = -1;

	/** Hauteur du dessin representant l'element de base. */
	private int height = -1;

	/** Booleen indiquant si le dessin de l'element de base est rempli ou non. */
	private boolean isFilled = false;

	/** Formalisme auquel appartient l'element de base. */
	private Formalism formalism = null;
	
	/** 
	 * Numero indiquant quel dessin faire.
	 * Tous les types de dessins sont définis dans INodeGraphicInfo et IArcGraphicInfo
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo
	 * @see fr.lip6.move.coloane.ui.model.INodeGraphicInfo 
	 */
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
	public ElementBase(String name, String paletteName, int numFigure, int width, int height, boolean isFilled) {
		this.name = name;
		this.paletteName = paletteName;
		this.numFigure = numFigure;
		this.width = width;
		this.height = height;
		this.isFilled = isFilled;
		this.numFigure = numFigure;
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
	 * Retourne le nom de l'element de base
	 * <b>Attention !</b> Cette methode retourne le nom prevu pour etre affiche sur la palette
	 */
	public String getPaletteName() {
		return this.paletteName;
	}
	
	/**
	 * Retourne la liste des attributs d'un element.
	 * @return ArrayList
	 */
	public ArrayList getListOfAttribute() {
		return listOfAttributeElem;
	}
	
	/**
	 * TODO : A verifier
	 * Retourne l'indication sur le dessin
	 * @return int
	 */
	public int getNumFigure() {
		return numFigure;
	}

	/**
	 * Retourne la largeur de l'objet telle que definie par le formalisme
	 * @return int La largeur
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Retourne la hauteur de l'objet telle que definie par le formalisme
	 * @return int La hauteur
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Retourne la propriete de remplissage de l'objet
	 * @return boolean
	 */
	public boolean getIsFilled() {
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
	 * Retourne le formalisme auquel appartient l'element de base
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
