package fr.lip6.move.coloane.api.interfaces;

import fr.lip6.move.coloane.api.session.ApiSession;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

/**
 * Gestionnaire de sessions.<br>
 * Il existe un seul gestionnaire de sessions pour les {@link ApiConnection}
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
	 * Demande la fermeture de la session désignée
	 * @param apiSession La session qui doit être fermée
	 * @return <code>true</code> si la fermeture s'est bien passsée
	 */
	boolean closeSession(ApiSession apiSession);

	/**
	 * Indique la fin du parsing des menus et donc la fin de l'ouverture de session
	 */
	void notifyEndOpenSession();

	/**
	 * Indique la réception de l'acquittement de FK pour la suspension de session
	 */
	void notifyEndSuspendSession();

	/**
	 * Indique la réception de l'acquittement de FK pour la reprise de session
	 * @param nameSession Le nom de la session concernée
	 */
	void notifyEndResumeSession(String nameSession);

	/**
	 * Indique la réception de l'acquittement de FK pour la fermetue de session
	 */
	void notifyEndCloseSession();


	/**
	 * Demande d'une invocation de service sur une session
	 * @param apiSession La session concernée par l'invocation de service
	 * @return <code>true</code> Si l'invocation (et non le service) s'est bien déroulée
	 */
	boolean askForService(ApiSession apiSession);

	/**
	 * TODO : A documenter
	 */
	void notifyWaitingForResult();

	/**
	 * TODO : A documenter
	 */
	void notifyWaitingForModel();

	/**
	 * Indique la réception de l'acquittement de FK pour la réception des résultats
	 */
	void notifyEndResult();

	/**
	 * Transmet les informations relatives à la session pour les retourner au core
	 * @param sessionInfo Les informatiosne en question
	 */
	void notifyReceptSessionInfo(ISessionInfo sessionInfo);
}
