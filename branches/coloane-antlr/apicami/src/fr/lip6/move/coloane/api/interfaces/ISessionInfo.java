package fr.lip6.move.coloane.api.interfaces;


/**
 * cette interface est donnée en paramétre a OpenSession
 * qui se trouve dans l'interface IAPIOpenSession.
 * @author KAHOO & UU
 *
 */

public interface ISessionInfo {

	/**
	 * Retourne le nom de la session.
	 * @return String
	 */
	String getSessionName();

	/**
	 * Retourne la date du modèle, lors de l'ouverture de la session.
	 * @return String
	 */
	String getSessionDate();

	/**
	 * Retourne le formalisme du modèle.
	 * @return String
	 */
    String getSessionFormalism();


}