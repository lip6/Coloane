package fr.lip6.move.coloane.apicami.interfaces;

import fr.lip6.move.coloane.apicami.session.ApiSession;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

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
	 * Indique la nouvelle session active<br>
	 * Si la session à activer n'est pas enregistrée... Il y a un problème
	 * @param session La nouvelle session active
	 * @return <code>true</code> si tout est OK; <code>false</code> si la session n'est pas enregistrée.
	 */
	boolean setActiveSession(ApiSession session);

	/**
	 * Ajoute une session à la liste des sessions contrôlées
	 * @param session La session à ajouter
	 */
	void addSession(ApiSession session);

	/**
	 * Supprime une session de la liste des sessions contrôlées<br>
	 * <b>Si la session supprimée est la session active, la session active sera <code>null</code> après la suppression</b>
	 * @param session La session à supprimer
	 */
	void removeSession(ApiSession session);

	/**
	 * Fermeture de toutes les session connectées
	 * @throws ApiException Si au moins une des session ne s'est pas correctement fermée
	 */
	void closeAllSessions() throws ApiException;

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
	 * Indique la réception de l'acquittement de FK pour la fermeture de session
	 */
	void notifyEndCloseSession();

	/**
	 * Indique a la session active qu'on est en attente de resultats de la plate-forme
	 */
	void notifyWaitingForResult();

	/**
	 * Indique que la plate-forme attend notre modele
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

	/**
	 * Indique a la session active la fin de l'invalidation de modèle
	 */
	void notifyEndInvalidModel();

	/**
	 * Indique à la session la définition d'une nouvelle boite de dialogue.<br>
	 * L'enregistrement des boites de dialogue est nécessaire pour permettre des réponses correctes
	 * @param dialog La définition de la boite de dialogue
	 */
	void notifyReceptDialog(IDialog dialog);
}
