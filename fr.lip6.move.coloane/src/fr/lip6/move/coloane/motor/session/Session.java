package fr.lip6.move.coloane.motor.session;

import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;

/**
 * Session
 */
public class Session {
    
    /** Compteur de sessions */
    public static int cntSession=1;
    
   /** Le modele associe */
    public ModelImplAdapter sessionModel;

    /** Nom de la session */
    private String sessionName;

    /** Identifiant de la session */
    private int sessionNumber;

    /** Arborescence du menu administration */
    private RootMenu adminMenu;
    
    /** Arborescence du menu et des services de la session */
    private RootMenu sessionMenu;

    /**
     * Constructeur de la classe Session
     * @param name Nom de la session
     * @param num numero de la session
     */
    public Session(String name, int num) {
        sessionName = name;
        sessionNumber = num;
        sessionModel= null;
    }

    /**
     * Retoune le nom de la session
     * @return name
     */
    public String getName() {
        return sessionName;
    }

    /**
     * Positionne le nom de la session
     * @param name nom de session
     */
    public void setName(String name) {
        this.sessionName = name;
    }

    /**
     * Retourne le numero de la session
     * @return int Le numero de la session
     */
    public int getNumber() {
        return this.sessionNumber;
    }

    /**
     * Positionne le nom de la session
     * @param number Le numero de la session
     */
    public void setNumber(int num) {
        this.sessionNumber = num;
    }

    /**
     * Retoune le modele
     * @return ModelImplAdapter Le modele de la session
     */
    public ModelImplAdapter getSessionModel() {
        return this.sessionModel;
    }

    /**
     * Positionne le modele
     * @param model nouveau modele
     */
    public void setSessionModel(ModelImplAdapter model) {
        this.sessionModel = model;
    }


    /**
     * Suspension d'un service sur la session
     */
    public void workSuspend() {
    	// TODO : A Implementer
    }

    /**
     * Reprendre l'exÈcution d'un service
     */
    public void workResume() {
        // TODO : A Implementer
    }

    /**
     * Fermeture de la connexion du modele
     */
    public void closeConnexion() {
        this.activateServices(false);
    }

    /**
     * Fermeture brutale de la connexion
     */
    public void closeConnexionPanic() {
        this.activateServices(false);
    }

    /**
     * Fermeture de la session
     */
    public void stopSession() {
    	// TODO : A Implementer
    }

     /**
     * Arrête le service
     * @param serviceName Nom du service
     * @return boolean
     */
    public boolean stopService(String serviceName) { 
        this.getSessionModel().setLocked(false);
        return true;
    }

    /**
     * Active ou desactive l'ensemble des services de la session
     */
    public void activateServices(boolean res) {
      // TODO : Activer les services
    }

	public RootMenu getAdminMenu() {
		return adminMenu;
	}

	public void setAdminMenu(RootMenu adminMenu) {
		this.adminMenu = adminMenu;
	}

	public RootMenu getSessionMenu() {
		return sessionMenu;
	}

	public void setSessionMenu(RootMenu sessionMenu) {
		this.sessionMenu = sessionMenu;
	}
}