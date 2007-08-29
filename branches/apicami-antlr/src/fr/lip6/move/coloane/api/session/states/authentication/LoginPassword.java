package fr.lip6.move.coloane.api.session.states.authentication;

public final class LoginPassword implements IAuthenticationMessage {

	private String login;
	private String password;
	private String apiName; 
	private String apiVersion;
	

	public LoginPassword(String login, String password, String apiName, String apiVersion) {
		super();
		this.login = login;
		this.password = password;
		this.apiName = apiName;
		this.apiVersion = apiVersion;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	
	
}
