package fr.lip6.move.coloane.interfaces.api.connection;

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
	
	/**
	 * Cree un objet representant une connexion
	 * @return un objet representant une connexion
	 */
	public IApiConnection getApiConnection();
}