package fr.lip6.move.coloane.interfaces.api.objects;

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
	public int getResultatCalcule();
}