package fr.lip6.move.coloane.interfaces.api.session;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.util.List;

/**
 * Cette interface définie une session.
 */
public interface IApiSession { // TODO : plus de détails dans les commentaires des exceptions pour permettre au core de traiter les exceptions.

	/**
	 * @return le nom de la session
	 */
	String getName();

	/**
	 * @return le formalise de la session
	 */
	String getFormalism();

	/**
	 * @return la date de la session
	 */
	int getDate();

	/**
	 * Recupère l'identifiant d'une session.<br>
	 * Celui ci est calculé par les API en fonction de leur interlocuteur.<br>
	 * @return l'identifiant de la session
	 */
	String getId();

	/**
	 * Ouvre la session sur la plate-forme de services
	 * @param date Date de la session.
	 * @param formalism Formalisme de la session.
	 * @param name Nom de la session.
	 * @return les informations sur la session ouverte.
	 * @throws ApiException si l'ouverture d'une session échoue.
	 */
	ISessionInfo open(int date, String formalism, String name) throws ApiException;

	/**
	 * Suspend la session
	 * @throws ApiException si la suspention de la session échoue.
	 */
	@Deprecated
	void suspend() throws ApiException;

	/**
	 * Restaure la session
	 * @throws ApiException si la reprise de la session échoue.
	 */
	void resume() throws ApiException;

	/**
	 * Ferme la session.
	 * <b>Aucune garantie n'est donnée sur la session active après la fermeture !!</b><br>
	 * <b>Ce <code>close</code> doit donc être suivi d'un <code>resume</code></b>
	 * @throws ApiException si la fermeture de la session échoue.
	 */
	void close() throws ApiException;

	/**
	 * Demande un service sur la session courante
	 * @param service service à executer
	 * @param options la liste des options cochés
	 * @param objects les objets sur lesquels exécuter un service
	 * @param texts les texts sur lesquels exécuter un service
	 * @param inputModel Le modèle sur lequel il faut invoquer le service
	 * @param outputModel Le modèle qu'on est susceptible de recevoir en retour
	 * @throws ApiException si l'excution du service sur la session échoue.
	 */
	void askForService(IService service, List<IOptionMenu> options, List<IElement> objects, List<String> texts, IGraph inputModel, IGraph outputModel) throws ApiException;

	/**
	 * Envoie la boite de dialogue reponse
	 * @param dialogAnswer la boîte dialogue réponse à envoyer.
	 * @return true, si l'envoie a reussie.
	 * @throws ApiException si l'envoie la boite de dialogue réponse échoue.
	 */
	boolean sendDialogAnswer(IDialogAnswer dialogAnswer) throws ApiException;

	/**
	 * Invalide un model
	 * @throws ApiException en cas de problème réseau
	 */
	void invalidModel() throws ApiException;

	/**
	 * Arrête l'exécution d'un service
	 */
	void stopService();
}
