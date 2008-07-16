package fr.lip6.move.coloane.interfaces.objects.menu;

public interface IUpdateMenu {
	
	/**
	 * Recupere le nom du menu principal a modifier
	 * @return le nom du menu principal a modifier
	 */
	public String getRootName();
	
	/**
	 * Recupere le nom du service a modifier
	 * @return le nom du service a modifier
	 */
	public String getServiceName();
	
	/**
	 * Recupere l'etat de l'item
	 * @return true, si le service est active, false sinon
	 */
	public boolean getState();
}