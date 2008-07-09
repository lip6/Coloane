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
	private String size;

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

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism#isBold()
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * Indique si l'attribut doit être affiché en gras
	 * @param bold <code>true</code> si l'attribut doit être affiché en gras. <code>false</code> sinon.
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism#isItalic()
	 */
	public boolean isItalic() {
		return italic;
	}

	/**
	 * Indique si l'attribut doit être affiché en italique
	 * @param bold <code>true</code> si l'attribut doit être affiché en italique. <code>false</code> sinon.
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism#getSize()
	 */
	public Integer getSize() {
		// TODO: Sortir la valeur dans un préférence : Taille par défaut des attributs
		if (size == null) {
			return 10;
		}
		return Integer.valueOf(size);
	}

	/**
	 * Indique la taille de la police utilisée pour afficher l'attribut
	 * @param size La taille souhaitée de la police
	 */
	public void setSize(String size) {
		this.size = size;
	}
}
