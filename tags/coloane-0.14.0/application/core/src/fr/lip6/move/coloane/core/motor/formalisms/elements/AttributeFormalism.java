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

	/** Style d'affichage : Gras ? */
	private boolean bold = false;

	/** Style d'affichage : Italique ? */
	private boolean italic = false;

	/** Style d'affichage : Taille de la police ? */
	private int size = 10;

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
		this(name, drawable, multiline);
		this.defaultValue = defaultValue;
	}


	/** {@inheritDoc} */
	public final String getName() { return this.name; }

	/** {@inheritDoc} */
	public final boolean isDrawable() { return drawable; }

	/** {@inheritDoc} */
	public final boolean isMultiLine() { return multiline; }

	/** {@inheritDoc} */
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

	/** {@inheritDoc} */
	public final boolean isBold() {
		return bold;
	}

	/**
	 * Indique si l'attribut doit être affiché en gras
	 * @param bold <code>true</code> si l'attribut doit être affiché en gras. <code>false</code> sinon.
	 */
	public final void setBold(boolean bold) {
		this.bold = bold;
	}

	/** {@inheritDoc} */
	public final boolean isItalic() {
		return italic;
	}

	/**
	 * Indique si l'attribut doit être affiché en italique
	 * @param italic <code>true</code> si l'attribut doit être affiché en italique. <code>false</code> sinon.
	 */
	public final void setItalic(boolean italic) {
		this.italic = italic;
	}

	/** {@inheritDoc} */
	public final Integer getSize() {
		// TODO : Sortir la valeur dans un préférence : Taille par défaut des attributs
		return this.size;
	}

	/**
	 * Indique la taille de la police utilisée pour afficher l'attribut
	 * @param size La taille souhaitée de la police
	 */
	public final void setSize(String size) {
		this.size = Integer.valueOf(size);
	}
}
