package fr.lip6.move.coloane.api.interfaces;

/**
 * cette interface est fournise a Coloane pour diverses methodes
 * en relation avec la session.
 * @author KAHOO & UU
 *
 */

public interface IApiSession {

	/*/**
	 * Donne le nom à la session.
	 * @param représente le nom a la session.

	void setSessionName(String name);

	/**
	 *  Donne la date de la session.
	 * @param représente la date de la session.

	void setSessionDate(String date);

	/**
	 * Retourne le nom du formalisme de la session.

	void setSessionFormalism();
*/

	/**
	 * l'apelle de la methode openSession sur la session.
	 * @param sessionDate: date de la session.
	 * @param sessionFormalism: son formalisme.
	 * @param sessionName: son nom.
	 * @param interlocutor: son interlocuteur (l'outil).
	 * @param mode: son mode (interactif ou batch).
	 */

	public void openSession(String sessionDate, String sessionFormalism,
			String sessionName,String interlocutor,int mode);


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

	/**
	 * Appel de la methode demande de service.
	 * @param le nom de la racine demandé.
	 * @param le nom du service demandé.
	 */
	void askForService(String rootName, String serviceName);


	/**
	 * Appel de la methode demande de service.
	 * @param le nom de la racine demandé.
	 * @param le nom du service demandé.
	 * @param la date .
	 */
	void askForService(String rootName, String serviceName, String Date);


}
