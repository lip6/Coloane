package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.wrapper.ws.CException;
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
	public Authentification openConnection(String login, String pass) throws CException;
	
	/**
	 * Demander une ouverture de session au wrapper
	 */
	public Session openSession(String nameFormalism) throws CException;
	
	/**
	 * Demander un changement de session au wrapper
	 */
	public Session changeSession(String idSession) throws CException;
	
	/**
	 * Demander une fermeture de session au wrapper
	 */
	public Session closeSession(String idSession) throws CException;
	
	/**
	 * Demander une fermeture de connexion
	 */
	public Unauthentification closeConnection() throws CException;
	
	/**
	 * Enovyer la reponse a une boite de dialogue au wrapper
	 */
	public String answerToDialogBox(DialogBox answer) throws CException;
	
	/**
	 * Demander l'execution d'un service au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void executService();
	
	
}
