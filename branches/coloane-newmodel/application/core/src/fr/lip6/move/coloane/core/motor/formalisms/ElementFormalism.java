package fr.lip6.move.coloane.core.motor.formalisms;

import java.util.ArrayList;

/**
 * Cette classe represente un element du base du formalisme.<br>
 * Un element de base est le composant d'un formalisme.<br>
 * Un element de base contient toute les informations decrivant ce composant de formalisme :
 * <ul>
 * 	<li>Nom et Nom de palette</li>
 * 	<li>Largeur, Hauteur</li>
 * 	<li>Details graphiques : Remplissage, Icones etc...</li>
 * </ul>
 */

public class ElementFormalism {
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
	 * Tous les types de dessins sont d�finis dans INodeGraphicInfo et IArcGraphicInfo
	 * @see fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo
	 */
	private int numFigure;

	/** Adresse du fichier representant l'icone de 16 pixels sur 16. */
	private String addrIcone16 = null;

	/** Adresse du fichier representant l'icone de 24 pixels sur 24. */
	private String addrIcone24 = null;

	/** Tableau des differents attributs de l'element de base. */
	private ArrayList<AttributeFormalism> listOfAttributeElem = new ArrayList<AttributeFormalism>(0);

	/**
	 * Constructeur
	 * @param elementName Nom de l'element de base.
	 * @param elementPaletteName Le nom affiche dans la palette d'outils
	 * @param elementFormalism Le formalisme associe a l'element
	 * @param elementNumFigure Numero permettant de savoir quel figure dessiner pour cet element.
	 * @param elementWidth Largeur du dessin de l'element de base.
	 * @param elementHeight Hauteur du dessin de l'element de base.
	 * @param elementIsFilled Flag indiquant si la figure sera remplie.
	 */
	public ElementFormalism(String elementName, String elementPaletteName, Formalism elementFormalism, int elementNumFigure, int elementWidth, int elementHeight, boolean elementIsFilled) {
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
	public final void addAttributeFormalism(AttributeFormalism attrForm) { listOfAttributeElem.add(attrForm); }

	/**
	 * Retourne le nom de l'element de base
	 * @return String
	 */
	public final String getName() {	return name; }

	/**
	 * Retourne le nom de l'element de base<br>
	 * <b>Attention !</b> Cette methode retourne le nom prevu pour etre affiche sur la palette
	 */
	public final String getPaletteName() { return this.paletteName; }

	/**
	 * Retourne la liste des attributs d'un element.
	 * @return ArrayList<AttributeFormalism>
	 */
	public final ArrayList<AttributeFormalism> getListOfAttribute() { return listOfAttributeElem; }

	/**
	 * Retourne l'indication sur le dessin
	 * @return int
	 */
	public final int getNumFigure() { return numFigure; }

	/**
	 * Retourne la largeur de l'objet telle que definie par le formalisme
	 * @return int La largeur
	 */
	public final int getWidth() { return width; }

	/**
	 * Retourne la hauteur de l'objet telle que definie par le formalisme
	 * @return int La hauteur
	 */
	public final int getHeight() { return height; }

	/**
	 * Retourne la propriete de remplissage de l'objet
	 * @return boolean
	 */
	public final boolean getIsFilled() { return isFilled; }

	/**
	 * Retourne l'adresse du fichier donnant l'icone de la figure.
	 * @return String
	 */
	public final String getAddrIcone16() { return addrIcone16; }

	/**
	 * Retourne l'adresse du fichier de l'icone de 24 pixels
	 * @return String
	 */
	public final String getAddrIcone24() { return addrIcone24; }

	/**
	 * Retourne le formalisme auquel appartient l'element de base
	 * @return Formalism
	 */
	public final Formalism getFormalism() {	return formalism; }

	/**
	 * Indique l'adresse de l'image pour la palette (16x16)
	 * @param addrIcone16
	 */
	public final void setAddrIcone16(String elementAddrIcone16) { this.addrIcone16 = elementAddrIcone16; }

	/**
	 * Indique l'adresse de l'image pour la palette (24x24)
	 * @param addrIcone24
	 */
	public final void setAddrIcone24(String elementAddrIcone24) { this.addrIcone24 = elementAddrIcone24; }
}
