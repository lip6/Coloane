package fr.lip6.move.coloane.interfaces.objects;

public interface IUpdateMenuCom {

	/**
	 * Retourne le nom du menu racine auquel est attache le service
	 * @return Le nom du menu racine
	 */
	public String getRoot();

	/**
	 * Retourne le nom du service qui doit etre mis a jour
	 * @return Le nom du service
	 */
	public String getService();

	/**
	 * Retourne le nouvel etat
	 * @return Le nouvel etat
	 */
	public boolean getState();

}