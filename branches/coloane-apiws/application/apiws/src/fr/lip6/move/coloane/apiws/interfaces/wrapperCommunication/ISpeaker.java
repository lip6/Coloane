package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.Unauthentification;

public interface ISpeaker {

	/**
	 * Demander une ouverture de connexion au wrapper
	 */
	public Authentification openConnection(String login, String pass);
	
	/**
	 * Demander une ouverture de session au wrapper
	 */
	public Session openSession(String nameFormalism);
	
	/**
	 * Demander un changement de session au wrapper
	 */
	public Session changeSession(String nameSession);
	
	/**
	 * Demander une fermeture de session au wrapper
	 */
	public Session closeSession(String idSession);
	
	/**
	 * Demander une fermeture de connexion
	 */
	public Unauthentification closeConnection();
	
	/**
	 * Demander l'execution d'un service au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void executService();
	
	
}
