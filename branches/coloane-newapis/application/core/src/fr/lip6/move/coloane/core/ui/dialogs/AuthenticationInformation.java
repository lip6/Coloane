package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.core.main.Coloane;

/**
 * Définition des informations d'authentification auprès de la palte-forme
 */
public class AuthenticationInformation {

	private String login;
	private String pass;
	private String ip;
	private int port;

	/**
	 * Constructeur
	 * @param login Le login de connexion
	 * @param pass Le mot de passe pour ce compte
	 * @param ip L'ip de la plate-forme
	 * @param port Le port de la plate-forme
	 */
	public AuthenticationInformation(String login, String pass, String ip, int port) {
		this.login = login;
		this.pass = pass;
		this.ip = ip;
		this.port = port;
	}

	/**
	 * @return le login de connexion
	 */
	public final String getLogin() {
		return login;
	}

	/**
	 * @return le mot de passe
	 */
	public final String getPass() {
		return pass;
	}

	/**
	 * @return l'ip de la plate-forme
	 */
	public final String getIp() {
		return ip;
	}

	/**
	 * @return le port de la plate-forme
	 */
	public final int getPort() {
		return port;
	}

	/**
	 * @return le type de la plate-forme
	 */
	public final String getApiType() {
		return Coloane.getParam("API_TYPE"); //$NON-NLS-1$
	}
}
