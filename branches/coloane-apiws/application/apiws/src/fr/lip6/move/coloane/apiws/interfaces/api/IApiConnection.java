package fr.lip6.move.coloane.apiws.interfaces.api;

public interface IApiConnection {

	/**
	 * Initialiser l'adresse IP du serveur
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setIpServer();

	/**
	 * Initialiser le port du serveur
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setPortServer();

	/**
	 * Initialiser le login
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setLogin();

	/**
	 * Initialiser le password
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setPassword();
}
