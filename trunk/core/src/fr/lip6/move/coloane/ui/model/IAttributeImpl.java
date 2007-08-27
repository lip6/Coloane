package fr.lip6.move.coloane.ui.model;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Interface des attributs d'un element du modele (noeud ou arc). <br>
 * Ses attributs sont :
 * <ul>
 * 	<li>id : nom unique (dans le projet) de la propriete
 * 	<li>displayName : chaine de characteres a afficher dans le PropertyView d'Eclipse
 * 	<li>propType : type (boolean, integer, string, list)
 * 	<li>value : valeur de la propriete
 * 	<li>validation : ??
 * 	<li>validation message : ??
 * </ul>
 */

public interface IAttributeImpl {

	/** ID pour la propriete lorsqu'un changement de valeur */
	String VALUE_PROP = "Attribute.ValueUpdate";

	/** ID pour la propriete lorsqu'un changement de la position */
	String LOCATION_PROP = "Attribute.Location";

	/** ID pour la propriete lorsque l'attribut doit etre selectionne */
	String SELECT_LIGHT_PROP = "Attribute.SelectLightUpdate";

	/** ID pour la propriete lorsque l'attribut doit etre selectionne */
	String SELECT_HEAVY_PROP = "Attribute.SelectHeavyUpdate";

	/** ID pour la propriete lorsque l'attribut doit etre selectionne */
	String UNSELECT_LIGHT_PROP = "Attribute.UnSelecLighttUpdate";

	/** ID pour la propriete lorsque l'attribut doit etre selectionne */
	String UNSELECT_HEAVY_PROP = "Attribute.UnSelectHeavyUpdate";

	/**
	 * Retourne l'identifiant de l'attribut
	 * @return
	 */
	int getId();

	/**
	 * Retourne la chaine qui sera affichee sur la fenetre PropertiesView d'Eclipse
	 * @return String
	 */
	String getDisplayName();

	/**
	 * Retourne la valeur de l'attribut
	 * @return Object
	 */
	String getValue();

	/**
	 * Retourne le type d'affichage a considerer pour cet attrribut
	 * @return int Indicateur d'affichage
	 * @see fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo
	 */
	int getType();

	/**
	 * Inidique la nouvelle valeur pour l'attribut generique
	 * @param oldValue L'ancienne valeur de l'attribut
	 * @param newValue La nouvelle valeur de l'attribut
	 */
	void setValue(String oldValue, String newValue);

	/**
	 * Indicateur d'affichage
	 * @return true si l'attribut doit etre affiche
	 */
	boolean isDrawable();

	/**
	 * Indicateur d'attribut multiligne
	 * @return true si l'attribut est multiligne
	 */
	boolean isMultiline();

	/**
	 * Retourne l'objet contenant les informations graphiques concernant cet attribut
	 * @return IAttributeGraphicInfo
	 * @see IAttributeGraphicInfo
	 */
	IAttributeGraphicInfo getGraphicInfo();

	/**
	 * Retourne l'attribut generique attache a l'attribut augmente
	 * @return IAttribute L'atribut generique
	 */
	IAttribute getGenericAttribute();

	/**
	 * Retourne la valeur par defaut de l'attribut
	 * @return La valeur par defaut
	 */
	String getDefaultValue();

	/**
	 * TODO : A documenter
	 * @return
	 */
	boolean getValidation();

	/**
	 * TODO : A documenter
	 * @return Le message
	 */
	String getValidationMessage();

	/**
	 * Retourne la reference de l'element proprietaire de cet attribut.
	 * Un IElement peut etre caste en INodeImpl ou IArcImpl
	 * @return
	 */
	IElement getReference();

	/**
	 * Leve un evenement pour demander la mise en valeur de l'attribut.
	 * @param state Selection / Deselection
	 */
	void setSelect(boolean state);

	/**
	 * Leve un evenement pour demander la mise en valeur de l'attribut.
	 * Deux mises en valeur sont possible :
	 * <ul>
	 * 	<li>Une legere qui correspond a un survol de l'objet reference</li>
	 * 	<li>Une lourde qui correspond a un clic sur l'objet referent</li>
	 * </ul>
	 * @param light Type de mie en valeur
	 * @param state Selection / Deselection
	 */
	void setSelect(boolean light, boolean state);
}
