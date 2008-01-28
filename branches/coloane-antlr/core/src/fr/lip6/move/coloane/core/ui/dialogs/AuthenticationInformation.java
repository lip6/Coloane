package fr.lip6.move.coloane.core.ui.dialogs;

public class AuthenticationInformation {

	private String login;
	private String pass;
	private String ip;
	private int port;

	public AuthenticationInformation(String loginI, String passI, String ipI, int portI) {
		this.login = loginI;
		this.pass = passI;
		this.ip = ipI;
		this.port = portI;
	}

	public final String getLogin() {
		return login;
	}
	public final String getPass() {
		return pass;
	}
	public final String getIp() {
		return ip;
	}
	public final int getPort() {
		return port;
	}
}
