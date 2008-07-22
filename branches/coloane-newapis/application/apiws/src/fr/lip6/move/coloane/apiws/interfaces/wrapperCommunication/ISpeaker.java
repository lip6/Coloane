package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.wrapper.ws.WrapperStub;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.Unauthentification;

/**
 * Cette interface représent un speaker pour la communication avec le wrapper.
 */
public interface ISpeaker {

	/**
	 * Recupere l'authentification de l'utilisateur
	 * @return l'authentification de l'utilisateur
	 */
	Authentification getAuthentification();

	/**
	 * Recupere le stub de communication
	 * @return le stub de communication
	 */
	WrapperStub getStub();

	/**
	 * Demander une ouverture de connexion au wrapper
	 * @param login le login de l'utilisateur
	 * @param password le password de l'utilisateur
	 * @return un objet représentant la connexion
	 * @throws ApiException si la connection échoue
	 */
	Authentification openConnection(String login, String password) throws ApiException;

	/**
	 * Demander une ouverture de session au wrapper
	 * @param nameFormalism le nom du formalisme pour l'ouverture de la session
	 * @return l'objet représentant la session ouverte
	 * @throws ApiException si l'ouverture de la session échoue
	 */
	Session openSession(String nameFormalism) throws ApiException;

	/**
	 * Demander un changement de session au wrapper
	 * @param idSession l'identifiant de la session a réstauré
	 * @return l'objet représentant la session réstaurer
	 * @throws ApiException si la réstauration de la session échoue
	 */
	Session changeSession(String idSession) throws ApiException;

	/**
	 * Demander une fermeture de session au wrapper
	 * @param idSession l'identifiant de la session a réstauré
	 * @return l'objet représentant la session réstaurer
	 * @throws ApiException si la fermeture de la session échoue
	 */
	Session closeSession(String idSession) throws ApiException;

	/**
	 * Demander une fermeture de connexion
	 * @return l'objet représentant la déconnexion
	 * @throws ApiException si la déconnexion échoue
	 */
	Unauthentification closeConnection() throws ApiException;

	/**
	 * Repond a la boite de dialog
	 * @param answer la boite de dialog a la quelle on repond
	 * @return Un message decrivant la bonne reception de la boite de dialog reponse
	 * @throws ApiException si la réponse à la boîte de dialogue échoue
	 */
	String answerToDialogBox(DialogBox answer) throws ApiException;

	/**
	 * Demander l'execution d'un service au wrapper
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 * @throws ApiException si l'excution d'un service échoue
	 */
	void executService() throws ApiException;

}
