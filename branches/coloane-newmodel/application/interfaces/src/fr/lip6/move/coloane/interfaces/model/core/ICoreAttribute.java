package fr.lip6.move.coloane.interfaces.model.core;

import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;

public interface ICoreAttribute extends ICoreAbstractPropertyChange, IAttribute {

	/** ID de la propriété lors d'un changement de valeur */
	String VALUE_PROP = "Attribute.ValueUpdate"; //$NON-NLS-1$

	/** ID de la propriété lors d'un changement de la position */
	String LOCATION_PROP = "Attribute.Location"; //$NON-NLS-1$

	/** ID de la propriété lorsque l'attribut doit être sélectionne */
	String SELECT_LIGHT_PROP = "Attribute.SelectLightUpdate"; //$NON-NLS-1$

	/** ID de la propriété lorsque l'attribut doit être sélectionne */
	String SELECT_HEAVY_PROP = "Attribute.SelectHeavyUpdate"; //$NON-NLS-1$

	/** ID de la propriété lorsque l'attribut doit être désélectionne */
	String UNSELECT_LIGHT_PROP = "Attribute.UnSelecLighttUpdate"; //$NON-NLS-1$

	/** ID de la propriété lorsque l'attribut doit être désélectionne */
	String UNSELECT_HEAVY_PROP = "Attribute.UnSelectHeavyUpdate"; //$NON-NLS-1$

	/**
	 * @return La valeur de cette attribut.
	 */
	String getValue();

	/**
	 * @param value nouvelle valeur pour cette attribut.
	 */
	void setValue(String value);

	/**
	 * @return Le nom de cette attribut.
	 */
	String getName();

	/**
	 * Retourne l'objet contenant les informations graphiques concernant cet attribut
	 * @return IAttributeGraphicInfo
	 * @see ICoreAttributeGraphicInfo
	 */
	IAttributeGraphicInfo getGraphicInfo();

	/**
	 * @return L'élément associé à cet attribut.
	 */
	IElement getReference();

	/**
	 * @return La taille de police à utiliser pour l'affichage de l'attribut
	 */
	int getSize();
	
	/**
	 * @return <code>true</code> si l'attribut doit être affiché en gras. <code>false</code> sinon.
	 */
	boolean isBold();
	
	/**
	 * @return <code>true</code> si l'attribut doit être affiché en italique. <code>false</code> sinon.
	 */
	boolean isItalic();

	/**
	 * @return True si l'attribut doit être dessiné.
	 */
	boolean isDrawable();

	/**
	 * @return True si l'attribut est multi-ligne.
	 */
	boolean isMultiLine();
}
