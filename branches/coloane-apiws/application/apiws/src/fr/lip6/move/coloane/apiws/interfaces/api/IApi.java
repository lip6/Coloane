package fr.lip6.move.coloane.apiws.interfaces.api;

public interface IApi {
	
	/**
	 * Recupere le nom de l'API
	 * @return le nom de l'API
	 */
	public String getUiName();
	
	/**
	 * Recupere la version de l'API
	 * @return la version de l'API
	 */
	public String getUiVersion();
}
