package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo;

/**
 * Cette classe represente les caracteristiques d'un attribut.<br>
 * Un attribut est une caracteristique d'un element de Base.
 */
public class AttributeFormalism {
	/** Nom de l'attribut. */
	private String name;

	/** Attribut est-il multilignes. */
	private boolean isMultiLines;

	/** L'attribut est-il affichable. */
	private boolean isDrawable;

	/** Valeur par defaut de l'attribut. */
	private String defaultValue = null;

	/** Ordre d'affichage dans la fenetre des proprietes */
	private int order;

	/**
	 * Type d'affichage
	 * @see fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo
	 */
	private int type = IAttributeGraphicInfo.NOR;

	/**
	 * Constructeur
	 * @param attributeOrder L'ordre d'affichage dans la fenetre de proprietes
	 * @param attributeName Le nom de l'attribut.
	 * @param attributeIsDrawable L'information est elle affichable a l'ecran.
	 * @param attributeIsMultiLines L'attribut est il multi-lignes.
	 */
	public AttributeFormalism(int attributeOrder, String attributeName, boolean attributeIsDrawable, boolean attributeIsMultiLines) {
		this.name = attributeName;
		this.isDrawable = attributeIsDrawable;
		this.isMultiLines = attributeIsMultiLines;
		this.order = attributeOrder;
	}

	/**
	 * Constructeur (avec indication d'affichage)
	 * @param attributeOrder L'ordre d'affichage dans la fenetre de proprietes
	 * @param attributeName Le nom de l'attribut.
	 * @param attributeType Le type d'affichage
	 * @param attributeIsDrawable L'information est elle affichable a l'ecran.
	 * @param attributeIsMultiLines L'attribut est il multi-lignes.
	 */
	public AttributeFormalism(int attributeOrder, String attributeName, int attributeType, boolean attributeIsDrawable, boolean attributeIsMultiLines) {
		this.name = attributeName;
		this.isDrawable = attributeIsDrawable;
		this.isMultiLines = attributeIsMultiLines;
		this.order = attributeOrder;
		this.type = attributeType;
	}

	/**
	 * Construit un nouvel attribut (avec une valeur par defaut)
	 * @param attributeOrder L'ordre d'affichage dans la fenetre des proprietes
	 * @param attributeName Le nom de l'attribut.
	 * @param attributeIsDrawable L'information est elle affichable a l'ecran.
	 * @param attributeIsMultiLines L'attribut est il multi-lignes.
	 * @param attributeDefaultValue La valeur par defaut de l'attribut.
	 */
	public AttributeFormalism(int attributeOrder, String attributeName, boolean attributeIsDrawable, boolean attributeIsMultiLines, String attributeDefaultValue) {
		this.name         = attributeName;
		this.isDrawable   = attributeIsDrawable;
		this.isMultiLines = attributeIsMultiLines;
		this.defaultValue = attributeDefaultValue;
		this.order = attributeOrder;
	}


	/**
	 * Construit un nouvel attribut (avec une valeur par defaut)
	 * @param attributeOrder L'ordre d'affichage dans la fenetre de proprietes
	 * @param attributeName Le nom de l'attribut.
	 * @param attributeType Le type d'affichage
	 * @param attributeIsDrawable L'information est elle affichable a l'ecran.
	 * @param attributeIsMultiLines L'attribut est il multi-lignes.
	 * @param attributeDefaultValue La valeur par defaut de l'attribut.
	 */
	public AttributeFormalism(int attributeOrder, String attributeName, int attributeType, boolean attributeIsDrawable, boolean attributeIsMultiLines, String attributeDefaultValue) {
		this.name = attributeName;
		this.isDrawable = attributeIsDrawable;
		this.isMultiLines = attributeIsMultiLines;
		this.defaultValue = attributeDefaultValue;
		this.order = attributeOrder;
		this.type = attributeType;
	}


	/**
	 * Retourne le nom de l'attribut.
	 * @return String
	 */
	public final String getName() { return this.name; }

	/**
	 * Retourne le booleen indiquant si l'attribut est affichable.
	 * @return boolean
	 */
	public final boolean isDrawable() { return isDrawable; }

	/**
	 * Retourne le booleen indiquant si l'attribut est multilignes.
	 * @return boolean
	 */
	public final boolean isMultiLines() { return isMultiLines; }

	/**
	 * Retourne la valeur par defaut de l'attribut
	 * @return String
	 */
	public final String getDefaultValue() {
		if (defaultValue != null) { return defaultValue; }
		return ""; //$NON-NLS-1$
	}

	/**
	 * Retourne le numero d'index pour l'affichage
	 * @return le numero (int) d'affichage
	 */
	public final int getOrder() { return this.order; }

	/**
	 * Retourne le type d'affichage
	 * @return l'indicateur du type d'affichage
	 */
	public final int getType() { return this.type; }
}
