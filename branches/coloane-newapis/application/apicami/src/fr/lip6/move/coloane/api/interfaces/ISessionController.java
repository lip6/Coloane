package fr.lip6.move.coloane.api.interfaces;

import fr.lip6.move.coloane.api.session.ApiSession;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

import java.io.IOException;

/**
 * Gestionnaire de sessions
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public interface ISessionController {

	/**
	 * @return la session active.
	 */
	IApiSession getActiveSession();

	/**
	 * Ajoute une session dans ma liste de sessions.
	 * @param s La session à rajouter.
	 * @return <code>true</code> si l'ajoute se passe bien
	 */
	boolean addSession(IApiSession s);

	/**
	 * Supprime une session de la liste des sessions
	 * @param session lasession a supprimer.
	 * @return <code>true</code> si la suppression est effective
	 */
	boolean removeSession(IApiSession session);

	/**
	 * Indique si la session est active ou pas
	 * @param session Session concernée par cette vérification
	 * @return <code>true</code>, si vraiment c'est la session active, <code>false</code> sinon.
	 */
	boolean isSessionActive(IApiSession session);

    /**
     * Suspend la session désignée
     * @param session La session à suspendre.
     * @return true, si suspendue, false sinon.
     */
	boolean suspendSession(IApiSession session);

	/**
	 * Reprise de la session désignée
	 * @param session La session a reprendre.
	 * @return true, si la session a été reprise , false sinon.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	boolean resumeSession(IApiSession session) throws InterruptedException, IOException;

	/**
	 * session demande a session controller s'il a le droit d'ouvrir une session ou pas.
	 * si il y'a pas de session active => c bon, sinon je demande a la
	 * session active de se suspendre , j'attend que le parser me reveille quand
	 * il recoit la reponse du SS, puis c bon,
	 * @param s la session qu'on veut ouvrir.
	 * @return vraie si c'est ok, false sinon.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	boolean openSession(IApiSession s) throws InterruptedException, IOException;

	/**
	 * nous signale la fin, des AQ et TQ
	 * fait par le parseur.
	 */
	public void notifyEndOpenSession();

	/**
	 * nous signale la reception du SS
	 * appelé par le parseur.
	 */
	public void notifyEndSuspendSession();

	public void notifyEndResumeSession(String nameSession);

	public void notifyEndCloseSession();

	public boolean closeSession(ApiSession apiSession);

	public boolean askForService(ApiSession apiSession);

	public void notifyWaitingForResult();

	public void notifyWaitingForModel() throws IOException;

	public void notifyEndResult();

	public void notifyReceptSessionInfo(ISessionInfo fkInfo);
}
