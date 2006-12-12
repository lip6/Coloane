package fr.lip6.move.coloane.motor.session;

import org.eclipse.jface.action.MenuManager;

import fr.lip6.move.coloane.communications.objects.Menu;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;

/**
 * Classe du Gestionnaire de Services
 * 
 * @author Jean-David HAGEGE
 * @author Abdelhamid ABDI
 * @author Selvaratnam SENTHURAN
 */
public class Session {
    
    /**
     * Compteur de sessions
     */
    public static int cntSession=1;
    
    /**
     * Les differents menus
     */
    public static final String ADMINMENUNAME = "Administration";
    public static final String FORMALISMMENUNAME = "AMI-Net";
    
    /**
     * Le modèle associé
     */
    public ModelImplAdapter sessionModel;

    /**
     * Nom de la session
     * 
     */
    private String sessionName;

    /**
     * Identifiant de la session
     * 
     */
    private int sessionNumber;

    /**
     * Arborescence du menu et des services de la session
     */
    private Menu sessionMenu;

    /**
     * Arborescence du menu administration
     */
    private Menu adminMenu;
    
    
    
    /**
     * menu Administration
     */
    private MenuManager adminManager;
    
    /**
     * Menu du formalisme (PetriNet, Accessibility Graph,..)
     */
    private MenuManager formalismManager;
    
    
    /**
     * Constructeur de la classe Session
     * 
     * @param name Nom de la session
     * @param num numero de la session
     */
    public Session(String name, int num) {
        sessionName = name;
        sessionNumber = num;
        adminManager = null;
        formalismManager = null;
        sessionModel= null;
    }

    /**
     * Retoune le nom de la session
     * 
     * @return name
     */
    public String getName() {
        return sessionName;
    }

    /**
     * Positionne le nom de la session
     * 
     * @param name
     *            nom de session
     */
    public void setName(String name) {
        this.sessionName = name;
    }

    /**
     * Retoune le numero de session
     * 
     * @return number
     */
    public int getNumber() {
        return this.sessionNumber;
    }

    /**
     * Positionne le nom de la session
     * 
     * @param number
     *            numero de session
     */
    public void setNumber(int number) {
        this.sessionNumber = number;
    }

    /**
     * Retoune le modele
     * 
     * @return services
     */
    public ModelImplAdapter getSessionModel() {
        return this.sessionModel;
    }

    /**
     * Positionne le modele
     * 
     * @param model
     *            nouveau modele
     */
    public void setSessionModel(ModelImplAdapter model) {
        this.sessionModel = model;
    }


    /***************************************************************************
     * Gestion Session
     **************************************************************************/

    /**
     * Suspendre l'exÈcution d'un service
     * 
     * @author Selvaratnam SENTHURAN
     */
    public void workSuspend() {
        // Pas implementé car monosession
    }

    /**
     * Reprendre l'exÈcution d'un service
     * 
     * @author Selvaratnam SENTHURAN
     */
    public void workResume() {
        // Pas implementé car monosession
    }

    /**
     * Fermeture de la connexion du modele
     * 
     * @author Selvaratnam SENTHURAN
     * 
     */
    public void closeConnexion() {
        this.activateServices(false);
    }

    /**
     * Fermeture brutale de la connexion
     * 
     */
    public void closeConnexionPanic() {
        this.activateServices(false);
    }

    /**
     * Fermeture de la session
     * 
     */
    public void stopSession() {

    }

    /***************************************************************************
     * Gestion Services
     **************************************************************************/

    /**
     * Fournir une mise à jour des services
     * 
     * @param menu Liste de services
     * 
     */
    public void updateServices(Menu menuAPI) {
    	System.out.println("Mise a jour des services :"+menuAPI.getName());
        if(menuAPI.getName().equals(Session.ADMINMENUNAME)){
        	System.out.println("OK pour admin");
            this.setAdminMenu(menuAPI);
        }else if(menuAPI.getName().equals(Session.FORMALISMMENUNAME)){
        	System.out.println("OK pour formalism");
            this.setSessionMenu(menuAPI);
        }else {
        	System.err.println("Echec lors de la reconnaissance de menu");
        }
        // traitements éventuels, interne au moteur
    }

    /**
     * Arrête le service
     * 
     * @param serviceName Nom du service
     * @return boolean
     */
    public boolean stopService(String serviceName) { 
        this.getSessionModel().setLocked(false);
        return true;
    }

    /**
     * active ou desactive l'ensemble des services de la session
     * 
     * @author Selvaratnam SENTHURAN,ABDI ABDELHAMID
     * @see org.dqs.ui.motor.interfaces.IMotCom#activateServices(boolean)
     * @param res Boolean indiquant a true que l'on active tout les services associe a la session
     */
    public void activateServices(boolean res) {
      //TODO activer les services
    }

    /**
     * DÈbut de rÈponse d'une demande de service
     * 
     * @author Selvaratnam SENTHURAN
     * @param res
     *            liste de rÈsultats
     */
    public void beginAnswerRes(Object[] res) {
        // TODO Auto-generated method stub
    }

    /**
     * Retour de rÈsultat d'un service
     * 
     * @author Selvaratnam SENTHURAN
     * @param res
     *            liste de rÈsultats
     */
    public void sendResults(Object[] res) {
        // TODO Auto-generated method stub
    }

    
    
    
    
    
    
    
    /**
     * @return Returns the menuAdministration.
     */
    public MenuManager getAdminManager() {
        return adminManager;
    }

    /**
     * @param adminManager The menuAdministration to set.
     */
    public void setAdminManager(MenuManager adminManager) {
        this.adminManager = adminManager;
    }

    /**
     * @return Returns the menuFormalisme.
     */
    public MenuManager getFormalimManager() {
        return formalismManager;
    }

    /**
     * @param formalismManager The menuFormalisme to set.
     */
    public void setFormalismManager(MenuManager formalismManager) {
        this.formalismManager = formalismManager;
    }

    /**
     * @return Returns the adminMenu.
     */
    public Menu getAdminMenu() {
        return adminMenu;
    }
    
    /**
     * @param adminMenu The adminMenu to set.
     */
    public void setAdminMenu(Menu adminMenu) {
        this.adminMenu = adminMenu;
    }
    
    /**
     * @return Retourne le sessionMenu.
     */
    public Menu getSessionMenu() {
        return sessionMenu;
    }

    /**
     * @param menu
     *            Positionne le menu de la session.
     */
    public void setSessionMenu(Menu menu) {
        this.sessionMenu = menu;
    }

}
