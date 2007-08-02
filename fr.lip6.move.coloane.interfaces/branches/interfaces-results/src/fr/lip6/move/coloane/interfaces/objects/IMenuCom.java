package fr.lip6.move.coloane.interfaces.objects;

/**
 * Interface fournie par l'API a Coloane pour la manipulation de sous-menus
 * 
 * Un menu est defini pour un menu de service ou d'administration<br>
 * Un menu se compose de plusieurs elements :
 * <ul>
 * 	<li>Un nom de service associe</li>
 * 	<li>Un nom de pere dans la hierarchie des menus</li>
 * 	<li>Un statut (actif, inactif)</li>
 * </ul> 
 */

/** test **/

public interface IMenuCom {
	
	/**
	 * Indique si le menu est actif ou pas
	 * @return Un booleen indiquant le status (true = actif)
	 */
	public boolean isEnabled();
	
	/**
	 * Change le statut du menu
	 * @param enabled Le nouveau status (true = actif)
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Retourne le nom du menu pere
	 * @return La chaine de caractere identifiant le menu pere
	 */
	public String getFatherName();
	
	/**
	 * Retourne le nom du service associe au menu
	 * @return Le nom du service associe au menu
	 */
	public String getServiceName();

}
