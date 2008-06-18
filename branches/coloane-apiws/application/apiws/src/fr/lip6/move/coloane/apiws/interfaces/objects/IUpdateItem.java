package fr.lip6.move.coloane.apiws.interfaces.objects;

public interface IUpdateItem {
	
	/**
	 * Recupere le nom de la racine qui contient la question
	 * @return le nom de la racine qui contient la question
	 */
	public String getRootName();
	
	/**
	 * Recupere le nom de la question a changer
	 * @return le nom de la question a changer
	 */
	public String getServiceName();
	
	/**
	 * Recupere l'etat de la question
	 * @return l'etat de la question
	 */
	public int getState();
	
	/**
	 * Recupere l'information d'etat de la question
	 * @return 'information d'etat de la question
	 */
	public String getMessage();
}
