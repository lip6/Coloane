package fr.lip6.move.coloane.interfaces.formalism;

/**
 * Cette classe représente les caracteristiques d'un attribut d'un élément de formalisme.<br>
 * Un attribut est une caractéristique d'un élément de base.<br>
 * Chaque élément du formalisme maintient une liste de ses attributs.
 */
public interface IAttributeFormalism {

	/**
	 * @return Le nom de l'attribut
	 */
	public abstract String getName();

	/**
	 * @return L'indicateur qui permet de savoir si un attribut est affichable dans l'éditeur grapique
	 */
	public abstract boolean isDrawable();

	/**
	 * @return L'indicateur qui permet de savoir si un attribut peut être réparti sur plusieurs lignes
	 */
	public abstract boolean isMultiLine();

	/**
	 * @return La valeur par défaut de l'attribut
	 */
	public abstract String getDefaultValue();

	/**
	 * @return <code>true</code> si l'attribut doit etre affiché en gras. <code>false</code> sinon.
	 */
	public boolean isBold();
	
	/**
	 * @return <code>true</code> si l'attribut doit etre affiché en gras. <code>false</code> sinon.
	 */
	public boolean isItalic();
	
	/**
	 * @return la taille de la police utilisée pour l'affichage de l'attribut
	 */
	public Integer getSize();
}