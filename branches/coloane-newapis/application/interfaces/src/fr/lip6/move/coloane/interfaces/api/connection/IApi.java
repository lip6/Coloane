package fr.lip6.move.coloane.interfaces.api.connection;

/**
 * Définition d'une API de communication.<br>
 * L'API de communication doit pertmettre la création d'une connexion via la méthode getApiConnection
 * @see IApiConnection
 */
public interface IApi {
	/**
	 * Recupere le nom de l'API
	 * @return le nom de l'API
	 */
	String getUiName();

	/**
	 * Recupere la version de l'API
	 * @return la version de l'API
	 */
	String getUiVersion();

	/**
	 * Cree un objet representant une connexion
	 * @return un objet representant une connexion
	 */
	IApiConnection getApiConnection();
}
