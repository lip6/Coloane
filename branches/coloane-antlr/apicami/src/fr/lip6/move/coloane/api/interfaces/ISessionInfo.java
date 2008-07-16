package fr.lip6.move.coloane.api.interfaces;

/**
 * cette interface est retournée en asynchrone par OpenSession
 * qui se trouve dans IAPIOpenSession.
 * @author KAHOO & UU
 *
 */

public interface ISessionInfo {


	/**
	 * Retourne le nom du service.
	 * @return String
	 */
	public String getNameService();


	/**
	 * Retourne des informations relatives au service.
	 * @return String
	 */
	public String getAboutService();


	/**
	 * Retourne l'incrmental.
	 * @return String
	 */
	public String getIncremental();


	/**
	 * Retourne le resultat calculé .
	 *  (1) si fait précédemment
	 *  (2) non
	 * @return String
	 */
	String getResultatCalcule();

}
