package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Cette classe définie une modification sur un menu.
 */
public interface IUpdateMenu {

	/**
	 * Recupere le nom du menu principal a modifier
	 * @return le nom du menu principal a modifier
	 */
	String getRootName();

	/**
	 * Recupere le nom de l'item a modifier
	 * @return le nom de l'item a modifier
	 */
	String getServiceName();

	/**
	 * Recupere l'etat de l'item
	 * @return true, si l'item est visible, false sinon
	 */
	boolean getState();
}
