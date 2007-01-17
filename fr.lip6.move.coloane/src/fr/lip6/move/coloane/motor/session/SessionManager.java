package fr.lip6.move.coloane.motor.session;

import java.util.ArrayList;
import java.util.Iterator;

import fr.lip6.move.coloane.communications.models.*;

/**
 * Gestionnaire de Sessions
 */
public class SessionManager {

    /** Pour le prototype il n'ya qu'une seule session dans le gestionnaire */
    private Session currentSession;

    /** Liste des sessions. */
    private static ArrayList<Session> listOfSessions = new ArrayList<Session>();

    /** 
     * Constructeur  
     */
    public SessionManager() {
    	this.currentSession = null;
    }

    /**
     * Retourne la session courante
     * @return la session courante
     */
    public Session getCurrentSession() {
        return currentSession;
    }

    /**
     * Retourne la session dont le nom est donne
     * @param sessionName nom de la session
     * @return Session la session courante
     */
    public Session getSession(String sessionName) {
        Iterator it;
        Session session = null;
        for (it = listOfSessions.iterator(); it.hasNext();) {
            session = (Session) it.next();
            if (sessionName.equals(session.getName())) {
                return session;
            }
        }
        return null;
    }
    
    /**
     * Positionne la session courante
     * @param s la session a positionner comme courante
     */
    public void setSession(Session s) {
        if(this.currentSession == null) {
            this.currentSession = s;
        }
        SessionManager.listOfSessions.add(s);
    }


    /**
     * Suspension d'une session
     * @param sessionName nom de la session
     */
    public void suspendSession(String sessionName) {
        this.getSession(sessionName).workSuspend();
    }

    /**
     * Reprendre, rendre active une session
     * @param sessionName nom de la session
     */
    public void resumeSession(String sessionName) {
    	this.getSession(sessionName).workResume();
    }

    /**
     * Deconnexion du modele de la session désignée
     * @param sessionName nom de la session
     */
    public void modelDeconnexion(String sessionName) {
        this.currentSession.closeConnexion();
        SessionManager.listOfSessions.remove(this.currentSession);
        this.currentSession = null;
    }

    /**
     * Deconnexion brutale de tous les modeles
     */
    public void disconnectAllModels() {
        Iterator it;
        Session theSession = null;
        for (it = listOfSessions.iterator(); it.hasNext();) {
            theSession = (Session) it.next();
            theSession.closeConnexionPanic();
           
        }
        SessionManager.listOfSessions.remove(theSession);
        this.currentSession=null;
    }

    /**
     * Arrête le service designe de la session designee
     * @param sessionName nom de la session
     * @param serviceName nom du service
     * @return boolean
     */
    public boolean stopService(String sessionName, String serviceName) {
        return this.getSession(sessionName).stopService(serviceName);
    }

    
    /**
     * Envoi du Model a framekit 
     * @return Model Aretourner a framekit, ce model est celui de la session courante
     */
    public Model askForModel(){
        return this.currentSession.sessionModel.getModel();
    }
    
    
    /**
     * Reception d'un nouveau model pour la session courante
     * @param modele nouveau modèle à attacher à la session courante
     */
    public void setModel(Model model){
        try {
			this.currentSession.sessionModel.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
