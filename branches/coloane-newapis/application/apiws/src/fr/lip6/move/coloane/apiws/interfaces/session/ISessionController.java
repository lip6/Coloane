package fr.lip6.move.coloane.apiws.interfaces.session;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;

/**
 * Cette interface represent le gestionnaire des sessions.
 */
public interface ISessionController {

	/**
	 * Recupere la session active.
	 * @return la session active.
	 */
	IApiSession getActiveSession();

	/**
	 * Ajouter une session dans la liste des sessions.
	 * @param s la session à rajouter.
	 * @return true si c'est bien ajouter, false sinon.
	 */
	boolean addSession(IApiSession s);

	/**
	 * Supprimer une session de la liste des sessions.
	 * @param s la session a supprimer.
	 * @return true si c'est bien supprimer, false sinon.
	 */
	boolean removeSession(IApiSession s);

	/**
	 * Verifie si la session est active ou pas.
	 * @param s la session à verifier.
	 * @return true, si la session est active, false sinon.
	 */
	boolean isActivateSession(IApiSession s);

	/**
	 * Verifie si on peut ouvrir une session.
	 * @param s la session qu'on veut ouvrir.
	 * @return true si on peut ouvrir une session.
	 * @throws ApiException si l'ouverture de la session est impossible.
	 */
	boolean openSession(IApiSession s) throws ApiException;

	/**
	 * Verifie si on peut suspendre la session.
	 * @param s la session à suspendre.
	 * @return true, si on peut suspendre la session.
	 * @throws ApiException si la susspension de la session est impossible.
	 */
	boolean suspendSession(IApiSession s) throws ApiException;

	/**
	 * Verifie si on peut reprendre la session.
	 * @param s la session a reprendre.
	 * @return true, si on peut reprendre la session.
	 * @throws ApiException si la restauration de la session est impossible.
	 */
	boolean resumeSession(IApiSession s) throws ApiException;

	/**
	 * Verifie si on peut fermer la session
	 * @param s la session a fermer
	 * @return true, si on peut fermer la session.
	 * @throws ApiException si la fermeture de la session est impossible.
	 */
	boolean closeSession(IApiSession s) throws ApiException;

	/**
	 * Verifie si on peut demander un service sur la session
	 * @param s la session sur qui on demande un service
	 * @return true, si on peut demander un service sur la session.
	 * @throws ApiException si la demande de service est impossible.
	 */
	boolean askForService(IApiSession s) throws ApiException;

	/**
	 * Notifier l'ouverture d'une session.
	 * @param opened la session ouvert.
	 */
	void notifyEndOpenSession(IApiSession opened, MMenu menu) throws ApiException;

	/**
	 * Notifier la suspention d'une session
	 * @param suspended la session suspendue
	 */
	void notifyEndSuspendSession(IApiSession suspended) throws ApiException;

	/**
	 * Notifier la restauration d'une session
	 * @param resumed la session restaurer
	 */
	void notifyEndResumeSession(IApiSession resumed) throws ApiException;

	/**
	 * Notifier la fermeture d'une session
	 * @param closed la session fermer
	 */
	void notifyEndCloseSession(IApiSession closed) throws ApiException;

	/**
	 * 
	 */
	void notifyWaitingForModel();


	/**
	 * 
	 */
	void notifyWaitingForResult();

	/**
	 * 
	 */
	void notifyEndResult();
}
