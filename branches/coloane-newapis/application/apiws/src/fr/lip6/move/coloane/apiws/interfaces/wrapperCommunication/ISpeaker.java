package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.wrapper.ws.WrapperStub;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.Unauthentification;

public interface ISpeaker {
	
	/**
	 * Recupere l'authentification de l'utilisateur
	 * @return l'authentification de l'utilisateur
	 */
	public Authentification getAuthentification();
	
	/**
	 * Recupere le stub de communication
	 * @return le stub de communication
	 */
	public WrapperStub getStub();

	/**
	 * Demander une ouverture de connexion au wrapper
	 */
	public Authentification openConnection(String login, String pass) throws ApiException;
	
	/**
	 * Demander une ouverture de session au wrapper
	 */
	public Session openSession(String nameFormalism) throws ApiException;
	
	/**
	 * Demander un changement de session au wrapper
	 */
	public Session changeSession(String idSession) throws ApiException;
	
	/**
	 * Demander une fermeture de session au wrapper
	 */
	public Session closeSession(String idSession) throws ApiException;
	
	/**
	 * Demander une fermeture de connexion
	 */
	public Unauthentification closeConnection() throws ApiException;
	
	/**
	 * Repond a la boite de dialog
	 * @param answer la boite de dialog a la quelle on repond
	 * @return Un message decrivant la bonne reception de la boite de dialog reponse
	 * @throws ApiException
	 */
	public String answerToDialogBox(DialogBox answer) throws ApiException;
	
	/**
	 * Demander l'execution d'un service au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void executService() throws ApiException;
	
	

}