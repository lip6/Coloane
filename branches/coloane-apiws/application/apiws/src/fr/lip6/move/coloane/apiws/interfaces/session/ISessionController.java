package fr.lip6.move.coloane.apiws.interfaces.session;

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
	 */
	public boolean openSession(IApiSession s);

	/**
	 * Verifie si on peut suspendre la session.
	 * @param la session à suspendre.
	 * @return true, si on peut suspendre la session, false sinon.
	 */
	public boolean suspendSession(IApiSession s);

	/**
	 * Verifie si on peut reprendre la session.
	 * @param s la session a reprendre.
	 * @return true, si on peut reprendre la session, false sinon.
	 */
	public boolean resumeSession(IApiSession s);
	
	/**
	 * Verifie si on peut fermer la session
	 * @param s la session a fermer
	 * @return true, si on peut fermer la session, false sinon
	 */
	public boolean closeSession(IApiSession s);

	/**
	 * Verifie si on peut demander un service sur la session
	 * @param s la session sur qui on demande un service
	 * @return true, si on peut demander un service sur la session, false sinon
	 */
	public boolean askForService(IApiSession s);

	/**
	 * 
	 */
	public void notifyEndOpenSession();

	/**
	 * 
	 */
	public void notifyEndSuspendSession();

	/**
	 * 
	 * @param nameSession
	 */
	public void notifyEndResumeSession(String nameSession);

	/**
	 * 
	 */
	public void notifyEndCloseSession();

	
}
