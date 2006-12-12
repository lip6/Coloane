package fr.lip6.move.coloane.motor.session;

import java.util.ArrayList;
import java.util.Iterator;

import fr.lip6.move.coloane.communications.models.*;
import fr.lip6.move.coloane.communications.objects.*;

/**
 * Classe du Gestionnaire de Service
 * 
 * @author Jean-David HAGEGE
 * @author Selvaratnam SENTHURAN
 */
public class SessionManager {

    /**
     * Pour le prototype il n'ya qu'une seule session dans le gestionnaire
     */
    private Session currentSession;

    /**
     * Liste des sessions.
     */
    private static ArrayList listOfSessions = new ArrayList();

    /**
     * Constructeur de la classe SessionManager: Creation d'un gestionnaire de sessions
     * Sessions
     * 
     */
    public SessionManager() {
    	System.out.println("Lancement du gestionnaire de sessions");
    	this.currentSession = null;
    }

    /***************************************************************************
     * Setters/Getters
     **************************************************************************/

    /**
     * Retourne la session courante
     * 
     * @return la session courante
     */
    public Session getSession() {
        return currentSession;
    }

    /**
     * Positionne la session courante
     * 
     * @param s la session
     */
    public void setSession(Session s) {
        //quand on ajoute une session elle devient par defaut la session courante
        if(this.currentSession==null){
            this.currentSession = s;
        }
        SessionManager.listOfSessions.add(s);
    }

    /**
     * Retourne la session dont le nom est donnÈ
     * 
     * @param sessionName nom de la session
     * @return Sesson la session courante
     * 
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
     * Suspension d'une session
     * 
     * @param sessionName nom de la session
     * 
     */
    public void suspendSession(String sessionName) {
        Session theSession;
        theSession = this.getSession(sessionName);
        theSession.workSuspend();
    }

    /**
     * Reprendre, rendre active une session
     * 
     * @param sessionName nom de la session
     * 
     */
    public void resumeSession(String sessionName) {
        Session theSession;
        theSession = this.getSession(sessionName);
        theSession.workResume();
    }

    /**
     * Deconnexion du modèle de la session désignée
     * 
     * @param sessionName nom de la session
     * 
     */
    public void modelDeconnexion(String sessionName) {
        this.currentSession.closeConnexion();
        SessionManager.listOfSessions.remove(this.currentSession);
        this.currentSession=null;
    }

    /**
     * Deconnexion brutale de tous les modËles
     * 
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
     * Arrête le service dÈsignÈ de la session dÈsignÈ
     * 
     * @param sessionName nom de la session
     * @param serviceName nom du service
     * @return boolean
     */
    public boolean stopService(String sessionName, String serviceName) {
        Session theSession;
        theSession = this.getSession(sessionName);
        return theSession.stopService(serviceName);
    }

    /**
     * Fournir une mise à jour des services
     * 
     * @author Selvaratnam SENTHURAN
     * @param service : liste de services
     */
    public void updateServices(Menu menuAPI) {
        currentSession.updateServices(menuAPI);
    }
    
    /**
     * Signal que l'on peu reactiver les services pour la session
     *  
     * @param res boolean indiquant si l'on active ou desactive les services associes a la session courante.
     */
    public void activateServices(boolean res) {
        //debloque le model pour l'edition
        currentSession.sessionModel.setLocked(res);
        //active les services de la session
        currentSession.activateServices(res);
    }

    /**
     * DÈbut d'envoi d'un rÈsultat
     * 
     * @author Selvaratnam SENTHURAN
     * @param res
     *            rÈsultat ‡ envoyer
     * 
     */
    public void beginAnswerRes(Object[] res) {
        currentSession.beginAnswerRes(res);
    }

    /**
     * Retour de résultat d'un service
     * 
     * @param res Liste de résultats
     * 
     */
    public void sendResults(Object[] res) {
        currentSession.sendResults(res);
    }

    /**
     * Envoi du Model a framekit 
     * 
     * @return Model Aretourner a framekit, ce model est celui de la session courante
     * 
     */
    public Model askForModel(){
        return this.currentSession.sessionModel.getModel();
    }
    
    
    /**
     * 
     * Reception d'un nouveau model pour la session courante
     * 
     * @param modele nouveau modèle à attacher à la session courante
     */
    public void setModel(Model model){
        try {
			this.currentSession.sessionModel.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
