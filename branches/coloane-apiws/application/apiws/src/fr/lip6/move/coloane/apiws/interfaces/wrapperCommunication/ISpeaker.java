package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

public interface ISpeaker {

	/**
	 * Demander une ouverture de connexion au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void openConnection();
	
	/**
	 * Demander une ouverture de session au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void openSession();
	
	/**
	 * Demander un changement de session au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void changeSession();
	
	/**
	 * Demander une fermeture de session au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void closeSession();
	
	/**
	 * Demander une fermeture de connexion
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void closeConnection();
	
	/**
	 * Demander l'execution d'un service au wrapper
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void executService();
	
	
}
