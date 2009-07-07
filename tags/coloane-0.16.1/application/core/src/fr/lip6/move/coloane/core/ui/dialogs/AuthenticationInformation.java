package fr.lip6.move.coloane.core.ui.dialogs;


/**
 * Définition des informations d'authentification auprès de la palte-forme
 */
public class AuthenticationInformation {

	private String login;
	private String pass;
	private String ip;
	private String apiType;
	private int port;

	/**
	 * Constructeur
	 * @param login Le login de connexion
	 * @param pass Le mot de passe pour ce compte
	 * @param ip L'ip de la plate-forme
	 * @param port Le port de la plate-forme
	 * @param apiType Le type de la plateforme (cami ou webservices)
	 */
	public AuthenticationInformation(String login, String pass, String ip, int port, String apiType) {
		this.login = login;
		this.pass = pass;
		this.ip = ip;
		this.port = port;
		this.apiType = apiType;
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
		return apiType;
	}
}
