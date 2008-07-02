package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;

/**
 * Cette classe représente les caracteristiques d'un attribut d'un élément de formalisme.<br>
 * Un attribut est une caractéristique d'un élément de base.<br>
 * Chaque élément du formalisme maintient une liste de ses attributs.
 */
public class AttributeFormalism implements IAttributeFormalism {
	/** Nom de l'attribut. */
	private String name;

	/** Attribut est-il multilignes. */
	private boolean multiline;

	/** L'attribut est-il affichable. */
	private boolean drawable;

	/** Valeur par défaut de l'attribut. */
	private String defaultValue = null;

	/**
	 * Construit un nouvel attribut
	 * @param name Le nom de l'attribut.
	 * @param drawable L'information est elle affichable a l'ecran ?
	 * @param multiline L'attribut est il multi-lignes ?
	 */
	public AttributeFormalism(String name, boolean drawable, boolean multiline) {
		this.name = name;
		this.drawable = drawable;
		this.multiline = multiline;
	}

	/**
	 * Construit un nouvel attribut (avec une valeur par defaut)
	 * @param name Le nom de l'attribut.
	 * @param drawable L'information est elle affichable a l'ecran ?
	 * @param multiline L'attribut est il multi-lignes ?
	 * @param defaultValue La valeur par defaut de l'attribut.
	 */
	public AttributeFormalism(String name, boolean drawable, boolean multiline, String defaultValue) {
		this(name,drawable,multiline);
		this.defaultValue = defaultValue;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IAttributeFormalism#getName()
	 */
	public final String getName() { return this.name; }

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IAttributeFormalism#isDrawable()
	 */
	public final boolean isDrawable() { return drawable; }

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IAttributeFormalism#isMultiLine()
	 */
	public final boolean isMultiLine() { return multiline; }

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IAttributeFormalism#getDefaultValue()
	 */
	public final String getDefaultValue() {
		if (defaultValue != null) { return defaultValue; }
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * Positionne la valeur par defaut de l'attribut
	 * @param defaultValue La valeur par défaut de l'attribut
	 */
	public final void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
