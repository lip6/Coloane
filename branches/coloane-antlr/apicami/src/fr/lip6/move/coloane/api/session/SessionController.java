package fr.lip6.move.coloane.api.session;

import java.io.IOException;
import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IApiSession;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
import java.lang.IllegalStateException;

/**
 * cette classe implemente l'interface ISessionController. elle gere les
 * sessions.
 *
 * @author kahoo && UU
 *
 */
public class SessionController implements ISessionController {

	/** l'ensemble de nos session */
	private ArrayList<IApiSession> liste;

	/** la session active */
	private IApiSession activeSession;

	/**
	 * le constructeur de notre classe; par default.
	 */

	public SessionController() {
		this.activeSession = null;
		this.liste = new ArrayList<IApiSession>();
	}

	/**
	 * nous retourne la session active.
	 *
	 * @return la session active.
	 */
	public IApiSession getActiveSession() {
		return this.activeSession;
	}

	/**
	 * nous rajoute une session dans ma liste de sessions.
	 *
	 * @param la
	 *            session à rajouter.
	 * @return true si c'est fait, false sinon.
	 */
	public boolean addSession(IApiSession s) {
		//System.out.println("je rajoute....." + s.getSessionName());

		this.liste.add(s);
	//	for(IApiSession session : liste ) {
	//		System.out.println("dans la liste ya addd" +session.getSessionName());
	//	}
		return true;
	}

	/**
	 * nous supprime une session dans ma liste de sessions.
	 *
	 * @param la
	 *            session a supprimer.
	 * @return true si c'est fait, false sinon.
	 */
	public boolean removeSession(IApiSession s) {

		return (this.liste.remove(s));

	}

	/**
	 * nous dit si notre session est active ou pas.
	 *
	 * @param la
	 *            session à verifier.
	 * @return true, si vraiment c'est la session active, false sinon.
	 */
	public boolean ifActivateSession(IApiSession s) {
		return this.activeSession.equals(s);
	}

	/**
	 * nous permet de suspendre la session.
	 *
	 * @param la
	 *            session à suspendre.
	 * @return true, si suspendue, false sinon.
	 */
	public boolean suspendSession(IApiSession s) {
		if (this.activeSession.equals(s)) {
			return true;
		}
		return false;
	}

	/**
	 * nous permet de reprendre la session.
	 *
	 * @param la
	 *            session a reprendre.
	 * @return true, si la session a été reprise , false sinon.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean resumeSession(IApiSession s) throws InterruptedException, IOException {

			if (this.activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
  //  System.out.println("je suspennnd " + this.activeSession.getSessionName());
			this.activeSession.suspendSession();
			this.activeSession = s;

			return true;
		}
			else{

				throw new IllegalStateException("je peux pas etre suspenduuu!!!");
			}

	}

	/**
	 * session demande a session controller s'il a le droit d'ouvrir une session
	 * ou pas. si il y'a pas de session active => c bon, sinon je demande a la
	 * session active de se suspendre , j'attend que le parser me reveille quand
	 * il recoit la reponse du SS, puis c bon,
	 *
	 * @param s
	 *            la session qu'on veut ouvrir.
	 * @return vraie si c'est ok, false sinon.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public boolean openSession(IApiSession s) throws InterruptedException, IOException {


		//System.out.println("dans openSession........");
		if (this.activeSession == null) {
			this.activeSession = s;
			addSession(s);
			return true;
		} else {
			if (this.activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
			this.activeSession.suspendSession();
			this.activeSession = s;
			addSession(s);
			return true;
		}
			else{
				///lever lexception attendre la notification de endOpenSession
				throw new IllegalStateException("une autre session est en cours d'ouverture!!!");
			}
		}

	}

	public void notifyEndOpenSession() {
	this.activeSession.notifyEndOpenSession();
	}


	public void notifyEndSuspendSession() {

    this.activeSession.notifyEndSuspendSession();


  }


	public void notifyEndResumeSession(String nameSession) {
	//	for(IApiSession session : liste ) {
	//		System.out.println("dans la liste ya" +session.getSessionName());
	//	}
	//	System.out.println(this.activeSession.getSessionName() + this.activeSession.getSessionStateMachine().getState() );
    for(IApiSession session : liste ) {

    	if (session.getSessionName().equals(nameSession)){
    		session.notifyEndResumeSession(nameSession);
    		this.activeSession = session;
    		break;
    	}


    }
   // System.out.println(this.activeSession.getSessionName() + this.activeSession.getSessionStateMachine().getState() );
	}



}