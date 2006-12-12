package fr.lip6.move.coloane.motor;


import fr.lip6.move.coloane.interfaces.IComMotor;
import fr.lip6.move.coloane.interfaces.IMotorCom;
import fr.lip6.move.coloane.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.motor.session.SessionManager;

public class Motor implements IMotorCom {
	private static FormalismManager formalismManager;
	private static SessionManager sessionManager;
	
	/* Le module de communications */
	private IComMotor com = null;

	/**
	 * Constructeur du module moteur
	 *
	 */
	public Motor() {
		Motor.formalismManager = new FormalismManager();
		Motor.sessionManager = new SessionManager();  
	}
	
	/**
	 * Recupere une poignee sur le moteur
	 * @param com le module de communication
	 */
	public void setCom(IComMotor com) {
		this.com = com;
	}
	
	/**
	 * Ouvre une connexion pour un modele
	 * 
	 * @param model 
	 * @param sessionName
	 * @return le resultat de l'operation
	 * @throws Exception
	 */
	public boolean openConnexion(ModelImplAdapter model, String sessionName) throws Exception {

		try {
			// Creation d'une nouvelle session
			Session session = new Session(sessionName, Session.cntSession++);
			if (session == null) {
				throw new Exception("Echec lors de la creation de la session");
			}
			
			// On associe le modele à la session
			session.setSessionModel(model);
			// On ajoute la session au gestionnaire de session
			Motor.sessionManager.setSession(session);
			
			
			if (com == null) {
				throw new Exception("Module de communication non instancié");
			}
			
			boolean result = com.connexion(model);
			return result;
		
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Donne la main sur le SessionManager
	 */
	public SessionManager getSessionManager() {
		return Motor.sessionManager;
	}
	
	/**
	 * Donne la main sur le FormalismManager
	 */
	public FormalismManager getFormalismManager() {
		return Motor.formalismManager;
	}
}
