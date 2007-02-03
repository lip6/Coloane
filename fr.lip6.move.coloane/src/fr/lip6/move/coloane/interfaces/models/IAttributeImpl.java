package fr.lip6.move.coloane.interfaces.models;

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
	public static final String UPDATE_ATTRIBUTE_VALUE = "Attribute.ValueUpdate";

	

	/**
	 * Retourne la chaine de caracteres a afficher dans la fenetre de proprietes
	 * @return String
	 */
	public String getDisplayName();

	/**
	 * Retourne la valeur de la propriete
	 * @return Object
	 */
	public Object getValue();

	/**
	 * Retourner True s'il faut un validateur
	 * @return boolean
	 */
	public boolean getValidation();

	/**
	 * Message de validation. Par exemple "Not a number"
	 * @return String
	 */
	public String getValidationMessage();

	/**
	 * True si la valeur est affichee a cote la figure du noeud
	 * @return boolean
	 */
	public boolean isDrawable();
    
}
