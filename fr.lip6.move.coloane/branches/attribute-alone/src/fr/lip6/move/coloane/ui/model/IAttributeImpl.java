package fr.lip6.move.coloane.ui.model;

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
	 * Retourne l'identifiant de l'attribut
	 * @return
	 */
	public String getId();
	
	/**
	 * Retourne la chaine qui sera affichee sur la fenetre PropertiesView d'Eclipse
	 * @return String
	 */
	public String getDisplayName();
	
	/**
	 * Retourne la valeur de l'attribut
	 * @return Object
	 */
	public String getValue();
	
	/**
	 * Inidique la nouvelle valeur pour l'attribut generique
	 * @param value La nouvelle valeur
	 */
	public void setValue(String value);
	
	/**
	 * Indicateur d'affichage
	 * @return true si l'attribut doit etre affiche
	 */
	public boolean isDrawable();
	
	/**
	 * Indicateur d'attribut multiligne
	 * @return true si l'attribut est multiligne
	 */
	public boolean isMultiline();
	
	public boolean getValidation();
	
	public String getValidationMessage();
}
