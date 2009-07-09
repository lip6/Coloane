package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

/**
 * Cette classe reprÃ©sente les caracteristiques d'un attribut d'un Ã©lÃ©ment de formalisme.<br>
 * Un attribut est une caractÃ©ristique d'un Ã©lÃ©ment de base.<br>
 * Chaque Ã©lÃ©ment du formalisme maintient une liste de ses attributs.
 */
public interface IAttributeFormalism {

	/**
	 * @return Le nom de l'attribut
	 */
	String getName();

	/**
	 * @return L'indicateur qui permet de savoir si un attribut est affichable dans l'Ã©diteur grapique
	 */
	boolean isDrawable();

	/**
	 * @return L'indicateur qui permet de savoir si un attribut peut Ãªtre rÃ©parti sur plusieurs lignes
	 */
	boolean isMultiLine();

	/**
	 * @return L'indicateur qui dit si un attribut est limité à un jeu de valeurs énummérées.
	 * @see getEnumeration()
	 */
	boolean isEnumerated();
	
	/**
	 * 
	 * @return La liste des valeurs possible de cet attribut s'il est énuméré, 
	 * ou <code>null</code> sinon.
	 */
	List<String> getEnumeration(); 
	
	
	/**
	 * @return La valeur par dÃ©faut de l'attribut
	 */
	String getDefaultValue();

	/**
	 * @return <code>true</code> si l'attribut doit etre affichÃ© en gras. <code>false</code> sinon.
	 */
	boolean isBold();

	/**
	 * @return <code>true</code> si l'attribut doit etre affichÃ© en gras. <code>false</code> sinon.
	 */
	boolean isItalic();

	/**
	 * @return la taille de la police utilisÃ©e pour l'affichage de l'attribut
	 */
	Integer getSize();
}
