package fr.lip6.move.coloane.api.interfaces;


/**
 * cette interface coordonne les sessions
 * @author KAHOO & UU
 *
 */

public interface ISessionManager {

	/**
	 * nous rend la session en cours..
	 * @return la session en cours(APISession).
	 */
	IApiSession getActiveSession();


	/**
	 * Appel de la methode ouvrir session.
	 * @param l'interface ISessionInfo qui porte des informations
	 * relatives à la session.
	 */
	void openSession( String SessionDate,String SessionFormalism,String SessionName);


	/**
	 * Appel de la methode ferme session.
	 */
	void closeSession();


	/**
	 *  Appel de la methode suspendre session.
	 * @return vraie si la suspension de la session reussie, faux sinon.
	 */

	boolean suspendSession();

	/**
	 * Appel de la methode reprendre session.
	 * @return vraie si la reprise de la session reussie, faux sinon.
	 */
	boolean resumeSession();


}
