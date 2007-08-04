package fr.lip6.move.coloane.motor.formalism;

import java.io.Serializable;

/**
 * Cette classe represente les caracteristiques d'un attribut.
 * Un attribut est une caracteristique d'un element de Base.
 * 
 * @author Thomas d'Erceville
 */
public class AttributeFormalism implements Serializable {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;

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
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo
	 */
	private int type = 0;

	/**
	 * Construit d'un nouvel attribut
	 * @param nameA Le nom de l'attribut.
	 * @param isDrawableA L'information est elle affichable a l'ecran.
	 * @param isMultiLinesA L'attribut est il multi-lignes.
	 */
	public AttributeFormalism(int order, String name, boolean isDrawable, boolean isMultiLines) {
		this.name         = name;
		this.isDrawable   = isDrawable;
		this.isMultiLines = isMultiLines;
		this.order = order;
	}

	/**
	 * Construit d'un nouvel attribut (avec indication d'affichage)
	 * @param nameA Le nom de l'attribut.
	 * @param isDrawableA L'information est elle affichable a l'ecran.
	 * @param isMultiLinesA L'attribut est il multi-lignes.
	 * @param type Le type d'affichage
	 */
	public AttributeFormalism(int order, String name, int type, boolean isDrawable, boolean isMultiLines) {
		this.name         = name;
		this.isDrawable   = isDrawable;
		this.isMultiLines = isMultiLines;
		this.order = order;
		this.type = type;
	}

	/**
	 * Construit un nouvel attribut (avec une valeur par defaut)
	 * @param nameA Le nom de l'attribut.
	 * @param isDrawableA L'information est elle affichable a l'ecran.
	 * @param isMultiLinesA L'attribut est il multi-lignes.
	 * @param defaultValueA La valeur par defaut de l'attribut.
	 */
	public AttributeFormalism(int order, String name, boolean isDrawable, boolean isMultiLines, String defaultValue) {
		this.name         = name;
		this.isDrawable   = isDrawable;
		this.isMultiLines = isMultiLines;
		this.defaultValue = defaultValue;
		this.order = order;
	}


	/**
	 * Construit un nouvel attribut (avec une valeur par defaut)
	 * @param nameA Le nom de l'attribut.
	 * @param isDrawableA L'information est elle affichable a l'ecran.
	 * @param isMultiLinesA L'attribut est il multi-lignes.
	 * @param defaultValueA La valeur par defaut de l'attribut.
	 * @param type Le type d'affichage
	 */
	public AttributeFormalism(int order, String name, int type, boolean isDrawable, boolean isMultiLines, String defaultValue) {
		this.name         = name;
		this.isDrawable   = isDrawable;
		this.isMultiLines = isMultiLines;
		this.defaultValue = defaultValue;
		this.order = order;
		this.type = type;
	}


	/**
	 * Retourne le nom de l'attribut.
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Retourne le booleen indiquant si l'attribut est affichable.
	 * @return boolean
	 */
	public boolean isDrawable() {
		return isDrawable;
	}

	/**
	 * Retourne le booleen indiquant si l'attribut est multilignes.
	 * @return boolean
	 */
	public boolean isMultiLines() {
		return isMultiLines;
	}

	/**
	 * Retourne la valeur par defaut de l'attribut
	 * @return String
	 */
	public String getDefaultValue() {
		if (defaultValue != null) {
			return defaultValue;
		}
		return "";
	}

	/**
	 * Retourne une chaine de carateres representant l'attribut
	 * @return String
	 */
	public String toString() {
		String str = "Attribut:\n     Name : " + name + "\n     Multilignes : " + isMultiLines;
		if (defaultValue != null) {
			str.concat("\n     Valeur par defaut : " + defaultValue);
		}
		return str;
	}

	/**
	 * Retourne le numero d'index pour l'affichage
	 * @return le numero (int) d'affichage
	 */
	public int getOrder() {
		return this.order;
	}

	/**
	 * Retourne le type d'affichage
	 * @return l'indicateur du type d'affichage
	 */
	public int getType() {
		return this.type;
	}
}