package fr.lip6.move.coloane.motor.session;

import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.ui.model.IModelImpl;

/**
 * Definition d'une session
 */
public class Session {
    
    /** Compteur de sessions */
    public static int cntSession=1;
    
   /** Le modele associe */
    public IModelImpl sessionModel;

    /** Nom de la session */
    private String sessionName;

    /** Identifiant de la session */
    private int sessionId;

    /** Arborescence du menu administration */
    private RootMenu adminMenu;
    
    /** Arborescence du menu et des services de la session */
    private RootMenu sessionMenu;
    
	/** Status de la session */
    private int sessionStatus;
    
    
    /**
     * Constructeur de la classe Session
     * @param name Nom de la session
     * @param num numero de la session
     */
    public Session(String name, int id) {
        this.sessionName = name;
        this.sessionId = id;
        this.sessionModel= null;
        
        this.sessionStatus = 0;	 	
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
    public int getId() {
        return this.sessionId;
    }

    /**
     * Positionne le nom de la session
     * @param number Le numero de la session
     */
    public void setId(int id) {
        this.sessionId = id;
    }

    /**
     * Retoune le modele
     * @return ModelImplAdapter Le modele de la session
     */
    public IModelImpl getModel() {
        return this.sessionModel;
    }

    /**
     * Positionne le modele
     * @param model nouveau modele
     */
    public void setModel(IModelImpl model) {
        this.sessionModel = model;
    }
    
    /**
     * Retourne le menu d'administration
     * @return la racine du menu d'administration
     */
 	public RootMenu getAdminMenu() {
		return adminMenu;
	}

 	/**
 	 * Indique le menu d'administration attache ˆ la session
 	 * @param adminMenu La racinde du menu d'administration
 	 */
	public void setAdminMenu(RootMenu adminMenu) {
		this.adminMenu = adminMenu;
	}

	/**
	 * Retourne le menu de service de la session
	 * @return la racine du menu de services
	 */
	public RootMenu getServicesMenu() {
		return sessionMenu;
	}

	/**
	 * Indique le menu de services attache a la session
	 * @param sessionMenu la racine du menu de services
	 */
	public void setServicesMenu(RootMenu sessionMenu) {
		this.sessionMenu = sessionMenu;
	}

	/**
	 * Retourne le status courant de la session
	 * @return le status courant de la session
	 */
	public int getStatus() {
		return sessionStatus;
	}

	/**
	 * Modifie le status courant de la session
	 * @param sessionStatus Le status courant de la session
	 */
	public void setStatus(int sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
}