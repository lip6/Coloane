package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;

/**
 * Définition d'un attribut d'objet du modèle.<br>
 * Un attribut peut-être attaché à de nombreux types d'objets (arc, noeud, graphe ...)
 */
public interface IAttribute extends IAbstractPropertyChange {

	/** ID de la propriété lors d'un changement de valeur */
	String VALUE_PROP = "Attribute.ValueUpdate"; //$NON-NLS-1$

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
	 * @see IAttributeGraphicInfo
	 */
	IAttributeGraphicInfo getGraphicInfo();

	/**
	 * @return L'élément associé à cet attribut.
	 */
	IElement getReference();

	/**
	 * @return L'ElementFormalism associé.
	 */
	IAttributeFormalism getAttributeFormalism();
}
