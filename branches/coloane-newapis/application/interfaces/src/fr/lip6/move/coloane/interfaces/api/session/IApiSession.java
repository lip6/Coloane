package fr.lip6.move.coloane.interfaces.api.session;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

import java.util.List;

/**
 * Cette interface définie une session.
 */
public interface IApiSession { // TODO : plus de détails dans les commentaires des exceptions pour permettre au core de traiter les exceptions.

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
	int getSessionDate();

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
	 * @param serviceName nom du service
	 * @param options la liste des options cochés
	 * @param model le model sur lequel invoquer le service
	 * @return true, si la demande de service a reussie.
	 * @throws ApiException si l'excution du service sur la session échoue.
	 */
	boolean askForService(String rootName, String serviceName, List<String> options, IGraph model) throws ApiException;

	/**
	 * Envoie la boite de dialogue reponse
	 * @param dialogAnswer la boîte dialogue réponse à envoyer.
	 * @return true, si l'envoie a reussie.
	 * @throws ApiException si l'envoie la boite de dialogue réponse échoue.
	 */
	boolean sendDialogAnswer(IDialogAnswer dialogAnswer) throws ApiException;

	/**
	 * Envoie un model
	 * @param model le model a envoyer
	 * @throws ApiException si l'envoie du model échoue
	 */
	void sendModel(IGraph model) throws ApiException;

	/**
	 * Invalide un model
	 * @throws ApiException si l'invalidation du model échoue
	 */
	void invalidModel() throws ApiException;
}
