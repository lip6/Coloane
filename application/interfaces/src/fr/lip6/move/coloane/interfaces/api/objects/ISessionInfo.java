package fr.lip6.move.coloane.interfaces.api.objects;

/**
 * Cette interface définie les informations sur une session.
 * <ul>
 * 	<li> Le nom du service </li>
 * 	<li> Les informations relatives au service </li>
 * 	<li> L'incrmental </li>
 * 	<li> Le resultat déjà calculé </li>
 * </ul>
 */
public interface ISessionInfo {

	/**
	 * Retourne le nom du service.
	 * @return String
	 */
	String getNameService();


	/**
	 * Retourne des informations relatives au service.
	 * @return String
	 */
	String getAboutService();


	/**
	 * Retourne l'incrmental.
	 * @return String
	 */
	String getIncremental();


	/**
	 * Retourne le resultat calculé .
	 *  (1) si fait précédemment
	 *  (2) non
	 * @return String
	 */
	int getResultatCalcule();
}
