package fr.lip6.move.coloane.apiws.interfaces.session;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;

public interface ISessionController {

	/**
	 * Recupere la session active.
	 * @return la session active.
	 */
	public IApiSession getActiveSession();

	/**
	 * Ajouter une session dans la liste des sessions.
	 * @param la session à rajouter.
	 * @return true si c'est bien ajouter, false sinon.
	 */
	public boolean addSession(IApiSession s);

	/**
	 * Supprimer une session de la liste des sessions.
	 * @param la session a supprimer.
	 * @return true si c'est bien supprimer, false sinon.
	 */
	public boolean removeSession(IApiSession s);

	/**
	 * Verifie si la session est active ou pas.
	 * @param la session à verifier.
	 * @return true, si la session est active, false sinon.
	 */
	public boolean isActivateSession(IApiSession s);
	

	/**
	 * Verifie si on peut ouvrir une session.
	 * @param s la session qu'on veut ouvrir.
	 * @return true si on peut ouvrir une session, false sinon.
	 * @throws ApiException
	 */
	public boolean openSession(IApiSession s) throws ApiException;

	/**
	 * Verifie si on peut suspendre la session.
	 * @param la session à suspendre.
	 * @return true, si on peut suspendre la session, false sinon.
	 * @throws ApiException
	 */
	public boolean suspendSession(IApiSession s) throws ApiException;

	/**
	 * Verifie si on peut reprendre la session.
	 * @param s la session a reprendre.
	 * @return true, si on peut reprendre la session, false sinon.
	 * @throws ApiException
	 */
	public boolean resumeSession(IApiSession s) throws ApiException;
	
	/**
	 * Verifie si on peut fermer la session
	 * @param s la session a fermer
	 * @return true, si on peut fermer la session, false sinon
	 * @throws ApiException
	 */
	public boolean closeSession(IApiSession s) throws ApiException;

	/**
	 * Verifie si on peut demander un service sur la session
	 * @param s la session sur qui on demande un service
	 * @return true, si on peut demander un service sur la session, false sinon
	 * @throws ApiException
	 */
	public boolean askForService(IApiSession s) throws ApiException;

	/**
	 * Notifier l'ouverture d'une session
	 * @param opened la session ouvert
	 */
	public void notifyEndOpenSession(IApiSession opened, MMenu menu) throws ApiException;

	/**
	 * Notifier la suspention d'une session
	 * @param suspended la session suspendue
	 */
	public void notifyEndSuspendSession(IApiSession suspended) throws ApiException;

	/**
	 * Notifier la restauration d'une session
	 * @param resumed la session restaurer
	 */
	public void notifyEndResumeSession(IApiSession resumed) throws ApiException;

	/**
	 * Notifier la fermeture d'une session
	 * @param closed la session fermer
	 * @param resumed la session qui sera restaure
	 */
	public void notifyEndCloseSession(IApiSession closed) throws ApiException;

	/**
	 * 
	 */
	public void notifyWaitingForModel();


	/**
	 * 
	 */
	public void notifyWaitingForResult();
	
	/**
	 * 
	 */
	public void notifyEndResult();
}
