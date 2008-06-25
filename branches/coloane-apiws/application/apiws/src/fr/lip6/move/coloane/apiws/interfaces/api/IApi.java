package fr.lip6.move.coloane.apiws.interfaces.api;

public interface IApi {
	
	/**
	 * Creer une 'ApiConnection' 
	 * @return une 'ApiConnection'
	 */
	public IApiConnection getApiConnection();
	
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
