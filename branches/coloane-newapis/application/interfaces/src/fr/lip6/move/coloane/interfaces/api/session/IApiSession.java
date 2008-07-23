package fr.lip6.move.coloane.interfaces.api.session;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.model.IModel;

import java.util.List;

/**
 * Cette interface définie une session.
 */
public interface IApiSession {

	/**
	 * Recupere le nom de la session courrante
	 * @return le nom de la session
	 */
	String getSessionName();

	/**
	 * Recupere le formalise de la session courrante
	 * @return le formalise de la session courrante
	 */
	String getSessionFormalism();

	/**
	 * Recupere la date de la session courrante
	 * @return la date de la session courrante
	 */
	String getSessionDate();

	/**
	 * Recupere l'interlocuteur (l'outil) de la session courrante
	 * @return l'interlocuteur de la session courrante
	 */
	String getInterlocutor();

	/**
	 * Recupere le mode de la session courrante
	 * @return le mode de la session courrante
	 */
	int getMode();

	/**
	 * Recupere l'identifiant d'une session.
	 * Cette methode est utiliser par l'apiws, pour identifier une session
	 * car le nom d'un session n'est pas forcement unique chez le wrapper.
	 * Dans le cas de l'api-antlr idSession peut-etre considerer comme egale a nameSession.
	 * @return l'identifiant d'une session.
	 */
	String getIdSession();

	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @param interlocutor l'interlocuteur (l'outil).
	 * @param mode le mode (interactif ou batch).
	 * @return les informations sur la session ouverte.
	 * @throws ApiException si l'ouverture d'une session échoue.
	 */
	@Deprecated
	ISessionInfo openSession(int sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode) throws ApiException;

	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @return les informations sur la session ouverte.
	 * @throws ApiException si l'ouverture d'une session échoue.
	 */
	ISessionInfo openSession(int sessionDate, String sessionFormalism, String sessionName) throws ApiException;

	/**
	 * Suspend la session courrante
	 * @return true, si la session est bien suspendu.
	 * @throws ApiException si la suspention de la session échoue.
	 */
	boolean suspendSession() throws ApiException;

	/**
	 * Restaure la session courrante
	 * @return true, si la session est bien restaure.
	 * @throws ApiException si la restauration de la session échoue.
	 */
	boolean resumeSession() throws ApiException;

	/**
	 * Ferme la session courrante.
	 * @return true, si la session est bien fermee.
	 * @throws ApiException si la fermeture de la session échoue.
	 */
	boolean closeSession() throws ApiException;

	/**
	 * Demande un service sur la session courrante
	 * @param rootName nom du menu principal du service
	 * @param menuName nom du sous-menu du menu principal du service
	 * @param serviceName nom du service
	 * @param options la liste des options cochés
	 * @param model le model sur lequel invoquer le service
	 * @return true, si la demande de service a reussie.
	 * @throws ApiException si l'excution du service sur la session échoue.
	 */
	boolean askForService(String rootName, String menuName, String serviceName, List<String> options, IModel model) throws ApiException;

	/**
	 * Demande un service sur la session courrante
	 * @param rootName nom du menu principal du service
	 * @param menuName nom du sous-menu du menu principal du service
	 * @param serviceName nom du service
	 * @param options la liste des options cochés
	 * @param model le model sur lequel invoquer le service
	 * @param date la date du model
	 * @return true, si la demande de service a reussie.
	 * @throws ApiException si l'excution du service sur la session échoue.
	 */
	boolean askForService(String rootName, String menuName, String serviceName, List<String> options, IModel model, String date) throws ApiException;

	/**
	 * Envoie la boite de dialogue reponse
	 * @param idDialog l'identifiant de la boite de dialog
	 * @param buttonAnswer le type de la reponse (OK/CANCEL)
	 * @param modified si la boite de dialogue a subi une modification
	 * @param value la valeur à envoyer
	 * @param lines ????
	 * @param objects ????
	 * @return true, si l'envoie a reussie.
	 * @throws ApiException si l'envoie la boite de dialogue réponse échoue.
	 */
	boolean sendDialogAnswer(int idDialog, int buttonAnswer, boolean modified, String value, List<String> lines, List<Integer> objects) throws ApiException;

	/**
	 * Envoie un model
	 * @param model le model a envoyer
	 * @throws ApiException si l'envoie du model échoue
	 */
	void sendModel(IModel model) throws ApiException;

	/**
	 * Invalide un model
	 * @throws ApiException si l'invalidation du model échoue
	 */
	void invalidModel() throws ApiException;
}
