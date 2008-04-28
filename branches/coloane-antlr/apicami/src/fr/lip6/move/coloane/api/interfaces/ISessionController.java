package fr.lip6.move.coloane.api.interfaces;

public interface ISessionController {

	public IApiSession getActiveSession();

	public boolean addSession(IApiSession s);

	public boolean removeSession(IApiSession s );

	public boolean ifActivateSession(IApiSession s );

}
