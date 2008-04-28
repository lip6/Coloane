package fr.lip6.move.coloane.api.session;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IApiSession;
import fr.lip6.move.coloane.api.interfaces.ISessionController;

/**
 * cette classe implemente l'interface ISessionController.
 * elle gere les sessions.
 * @author kahoo && UU
 *
 */
public class SessionController implements ISessionController {

	/** l'ensemble de nos session*/
	private ArrayList<IApiSession> liste;

	/** la session active*/
	private IApiSession activeSession;



   /**
	 * le constructeur de notre classe; par default.
     */

  public SessionController(){
	   this.activeSession=null;
	   this.liste = new ArrayList<IApiSession>();
  }
	/**
	 * nous retourne la session active.
	 * @return la session active.
	 */
	public IApiSession getActiveSession(){
		return this.activeSession;
	}

	/**
	 * nous rajoute une session dans ma liste de sessions.
	 * @param la session à rajouter.
	 * @return true si c'est fait, false sinon.
	 */
	public boolean addSession(IApiSession s){
		this.liste.add(s);
		this.activeSession = s;
		return true;
	}

	/**
	 * nous supprime une session dans ma liste de sessions.
	 * @param la session a supprimer.
	 * @return true si c'est fait, false sinon.
	 */
	public boolean removeSession(IApiSession s ){
		this.activeSession = null;
		return (this.liste.remove(s));

	}

	/**
	 * nous dit si notre session est active ou pas.
	 * @param la session à verifier.
	 * @return true, si vraiment c'est la session active, false sinon.
	 */
	public boolean ifActivateSession(IApiSession s){
      return  this.activeSession.equals(s);
		}

	 /**
     * nous permet de suspendre la session.
     * @param la session à suspendre.
     * @return true, si suspendue, false sinon.
     */
	public boolean suspendSession(IApiSession s){
		if (this.activeSession.equals(s)){
			this.activeSession= null;
			return true;
		}
		return false;
	}

	/**
	 * nous permet de reprendre la session.
	 * @param la session a reprendre.
	 * @return true, si la session a été reprise , false sinon.
	 */
	public boolean resumeSession(IApiSession s){
		if (this.activeSession.equals(null)){
			this.activeSession= s;
			return true;
		}
		return false;

	}


}