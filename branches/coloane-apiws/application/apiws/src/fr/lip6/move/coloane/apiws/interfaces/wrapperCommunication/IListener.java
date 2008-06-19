package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

public interface IListener {

	/**
	 * Fonction de polling.
	 * Permet de demander periodiquement au wrapper s'il y a des messages ou boites de dialog a traiter
	 * 
	 * ATTENTION: SE METTRE D'ACCORD AVEC SILIEN POUR LA SIGNIATURE
	 */
	public void ping();
	
}
