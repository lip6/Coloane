package fr.lip6.move.coloane.api.interfaces;

import java.io.IOException;

import fr.lip6.move.coloane.api.ApiConnection;
import fr.lip6.move.coloane.api.session.ApiSession;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

/**
 * Gestionnaire de sessions.<br>
 * Il existe un gestionnaire de sessions pour toute {@link ApiConnection}
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public interface ISessionController {

	/**
	 * @return la session active.
	 */
	ApiSession getActiveSession();

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
	 * @throws ApiException si on ne peut pas reprendre cette session
	 */
	boolean resumeSession(ApiSession session) throws ApiException;

	/**
	 * La session demande au contrôleur de sessions si elle a le droit de s'ouvrir ou pas.<br>
	 * Si aucune session n'est active : la demande est accordée<br>
	 * Sinon le session contrôleur essaye de suspendre la session active pour la remplacer par celle là<br>
	 * @param s La session qu'on veut ouvrir.
	 * @return <code>true</code> si c'est l'ouverture est autorisée, <code>false</code> sinon.
	 * @throws ApiException si la session active n'est pas suspendable
	 */
	boolean openSession(ApiSession s) throws ApiException;

	/**
	 * nous signale la fin, des AQ et TQ
	 * fait par le parseur.
	 */
	void notifyEndOpenSession();

	/**
	 * nous signale la reception du SS
	 * appelé par le parseur.
	 */
	 void notifyEndSuspendSession();

	 /**
	  *
	  * @param nameSession
	  */
	 void notifyEndResumeSession(String nameSession);

	 /**
	  *
	  */
	 void notifyEndCloseSession();

	 /**
	  *
	  * @param apiSession
	  * @return
	  */
	 boolean closeSession(ApiSession apiSession);

	 /**
	  * 
	  * @param apiSession
	  * @return
	  */
	 boolean askForService(ApiSession apiSession);

	 /**
	  * 
	  */
	 void notifyWaitingForResult();

	 /**
	  * 
	  * @throws IOException
	  */
	 void notifyWaitingForModel() throws IOException;

	 /**
	  * 
	  */
	 void notifyEndResult();

	 /**
	  * Transmet les informations relatives à la session pour les retourner au core
	  * @param sessionInfo Les informatiosne en question
	  */
	 void notifyReceptSessionInfo(ISessionInfo sessionInfo);
}
