package fr.lip6.move.coloane.motor.formalism;

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

public class ElementBase {

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
	public ElementBase(String elementName, String elementPaletteName, Formalism elementFormalism, int elementNumFigure, int elementWidth, int elementHeight, boolean elementIsFilled) {
		this.name = elementName;
		this.paletteName = elementPaletteName;
		this.formalism = elementFormalism;
		this.numFigure = elementNumFigure;
		this.width = elementWidth;
		this.height = elementHeight;
		this.isFilled = elementIsFilled;
		this.numFigure = elementNumFigure;
	}

	/**
	 * Ajoute un attribut a l'element de base
	 * @param attrForm Attribut a ajouter a la liste des attributs
	 */
	public final void addAttributeFormalism(AttributeFormalism attrForm) {
		listOfAttributeElem.add(attrForm);
	}

	/**
	 * Retourne le nom de l'element de base
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Retourne le nom de l'element de base
	 * <b>Attention !</b> Cette methode retourne le nom prevu pour etre affiche sur la palette
	 */
	public final String getPaletteName() {
		return this.paletteName;
	}

	/**
	 * Retourne la liste des attributs d'un element.
	 * @return ArrayList
	 */
	public final ArrayList getListOfAttribute() {
		return listOfAttributeElem;
	}

	/**
	 * Retourne l'indication sur le dessin
	 * @return int
	 */
	public final int getNumFigure() {
		return numFigure;
	}

	/**
	 * Retourne la largeur de l'objet telle que definie par le formalisme
	 * @return int La largeur
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * Retourne la hauteur de l'objet telle que definie par le formalisme
	 * @return int La hauteur
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * Retourne la propriete de remplissage de l'objet
	 * @return boolean
	 */
	public final boolean getIsFilled() {
		return isFilled;
	}

	/**
	 * Retourne l'adresse du fichier donnant l'icone de la figure.
	 * @return String
	 */
	public final String getAddrIcone16() {
		return addrIcone16;
	}

	/**
	 * Retourne l'adresse du fichier de l'icone de 24 pixels
	 * @return String
	 */
	public final String getAddrIcone24() {
		return addrIcone24;
	}

	/**
	 * Retourne le formalisme auquel appartient l'element de base
	 * @return Formalism
	 */
	public final Formalism getFormalism() {
		return formalism;
	}

	/**
	 * Indique l'adresse de l'image pour la palette (16x16)
	 * @param addrIcone16
	 */
	public final void setAddrIcone16(String elementAddrIcone16) {
		this.addrIcone16 = elementAddrIcone16;
	}

	/**
	 * Indique l'adresse de l'image pour la palette (24x24)
	 * @param addrIcone24
	 */
	public final void setAddrIcone24(String elementAddrIcone24) {
		this.addrIcone24 = elementAddrIcone24;
	}
}
