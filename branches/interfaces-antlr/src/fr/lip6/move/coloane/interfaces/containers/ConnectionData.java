package fr.lip6.move.coloane.interfaces.containers;

import java.net.InetSocketAddress;

/**
 * Objet utilise pour encapsuler les donnees necessaires a <b>l'authentification</b> sur la plateforme.<br>
 * Doivent y figurer :
 * <ul>
 * 	<li>L'adresse de la plateforme</li>
 * 	<li>Le login de connexion</li>
 * 	<li>Le mot de passe</li>
 * 	<li>Le nom du client (coloane)</li>
 * 	<li>La version du client</li>
 * </ul>
 */
public final class ConnectionData {
	
	/** Les donnees relatives a la plateforme */
	private InetSocketAddress platform;
	
	/** Le login */
	private String login;
	
	/** Le mot de passe */
	private String pass;
	
	/** Le nom du client */
	private String name;
	
	/** La version du client */
	private String version;
	
	/* ------- */

	public ConnectionData(InetSocketAddress platform, String login, String pass, String name, String version) {
		this.login = login;
		this.pass = pass;
		this.login = login;
		this.name = name;
		this.version = version;
	}

	/* ------- */

	public InetSocketAddress getPlatform() {
		return platform;
	}

	public void setPlatform(InetSocketAddress platform) {
		this.platform = platform;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}	
}
