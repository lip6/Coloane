package fr.lip6.move.coloane.interfaces.api;

/**
 * Définition d'une API de communication.<br>
 * L'API de communication doit pertmettre la création d'une connexion via la méthode getApiConnection
 * @see IApiConnection
 */
public interface IApi {
	/**
	 * Cree un objet representant une connexion
	 * @return un objet representant une connexion
	 */
	IApiConnection createApiConnection();
}
