package fr.lip6.move.coloane.api.interfaces;


/**
 * cette interface représente une modification d'un menu TQ(7 & 8)
 * @author KAHOO & UU
 *
 */
public interface IUpdateItem {

	/**
	 * Donne le nom du root name.
	 * @return string.
	 */
	public String getRootName();

	/**
	 * Donne le nom du service.
	 * @return string.
	 */
	public String getServiceName();

	/**
	 * Donne l'état de l'item (à activer ou pas).
	 * @return bool.
	 */
	public boolean getState();
}
