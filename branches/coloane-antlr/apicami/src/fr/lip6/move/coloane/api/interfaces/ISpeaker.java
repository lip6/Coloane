package fr.lip6.move.coloane.api.interfaces;


/**
 * cette interface représente notre speaker.
 * @author KAHOO & UU
 *
 */

public interface ISpeaker {

	/**
	 *  demande a ISpeaker d'envoyer a FK l'ouverture de la connection.
	 *  @param l'adresse IP de FK.
	 *  @param le port.
	 *  @param le login.
	 *  @param le password.
	 */
void OpenConnection(String IPAdress, int port, String login ,String password);

/**
 *  demande a ISpeaker d'envoyer a FK la fermeture de la connection.
 */
void CloseConnection();

/**
 *   demande a ISpeaker d'envoyer a FK l'ouverture d'une session.
 *   @param le nom de la session.
 *   @param le nom du formalisme.
 */
void OpenConnection(String sessionName, int date, String SessionFormalism);

/**
 * demande a ISpeaker d'envoyer a FK la fermeture d'une session.
 *   @param le nom de la session.
 */
void CloseSession(String SessionName);

/**
 * demande a ISpeaker d'envoyer a FK la suspension d'une session.
 *   @param le nom de la session.
 */
void SuspendSession(String SessionName);

/**
  * demande a ISpeaker d'envoyer a FK la reprise d'une session.
 *   @param le nom de la session.
 */
void ResumeSession(String SessionName);

/**
 *  demande a ISpeaker d'envoyer a FK la demande de service.
 *   @param le nom du chemin.
 *   @param le nom du service.
 */
void AskForService(String rootName, String ServiceName);

/**
  *  demande a ISpeaker d'envoyer a FK la demande de service.
 *   @param le nom du chemin.
 *   @param le nom du service.
 *    @param la date.
 */
void AskForService(String rootName, String RootName, String date);

/**
  *  demande a ISpeaker d'envoyer a FK un model.
 *   @param le model.
 */
void SendModel(IModel m);

/**
 *  demande a ISpeaker d'envoyer a FK une boite de dialogue.
 *   @param le dialogue.
 */
void SendDialogResponse(IDialog d);

}
