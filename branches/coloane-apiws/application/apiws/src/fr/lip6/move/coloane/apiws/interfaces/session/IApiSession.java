package fr.lip6.move.coloane.apiws.interfaces.session;

import fr.lip6.move.coloane.apiws.interfaces.objects.IModel;
import fr.lip6.move.wrapper.ws.CException;

public interface IApiSession {

	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @param interlocutor l'interlocuteur (l'outil).
	 * @param mode le mode (interactif ou batch).
	 */
	public void openSession(String sessionDate, String sessionFormalism,String sessionName,String interlocutor,int mode) throws CException;
	
	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 */
	public void openSession(String sessionDate, String sessionFormalism,String sessionName) throws CException;

	/**
	 * Changer de session
	 * @param s la session a charger
	 */
	public void changeSession(IApiSession s) throws CException;


	/**
	 * Fermer la session courrante.
	 */
	public void closeSession() throws CException;

	/**
	 * Demander un service sur la session courrante
	 * @param rootName 
	 * @param menuName
	 * @param serviceName
	 */
	public void askForService(String rootName,String menuName, String serviceName);


	/**
	 * Demander un service sur la session courrante
	 * @param rootName
	 * @param menuName
	 * @param serviceName
	 * @param date
	 */
	public void askForService(String rootName,String menuName, String serviceName, String date);

	
	/**
	 * 
	 * @param model
	 */
	public void sendModel(IModel model);
	
	
	/**
	 * 
	 */
	public void invalidModel();
	
	
	/**
	 * Recupere l'automate de la session courrante
	 * @return l'automate de la session courrante
	 */
	public ISessionStateMachine getSessionStateMachine();
	
	/**
	 * Recupere le nom de la session courrante
	 * @return le nom de la session
	 */
	public String getSessionName();
	
	/**
	 * Recupere le formalise de la session courrante
	 * @return le formalise de la session courrante
	 */
	public String getSessionFormalism();
	
	/**
	 * Recupere la date de la session courrante
	 * @return la date de la session courrante
	 */
	public String getSessionDate();
	
	/**
	 * Recupere l'interlocuteur (l'outil) de la session courrante
	 * @return l'interlocuteur de la session courrante
	 */
	public String getInterlocutor();
	
	/**
	 * Recupere le mode de la session courrante
	 * @return le mode de la session courrante
	 */
	public int getMode();
	
	/**
	 * Recupere l'identifiant de la session courrante
	 * @return l'identifiant de la session courrante
	 */
	public String getIdSession();
	
	/**
	 * Recupere le gestionnaire de session
	 * @return le gestionnaire de session
	 */
	public ISessionController getSessionController();
	
	/**
	 * Notifier l'ouverture d'une session
	 */
	public void notifyEndOpenSession();

	/**
	 * Notifier la suspension d'une session
	 */
	public void notifyEndSuspendSession();
	
	/**
	 * Notifier la restauration d'une session
	 */
	public void notifyEndResumeSession();
	
	
	/**
	 * Notifier le changement de session
	 */
	public void notifyEndChangeSession();
	

	/**
	 * Notifier la fermeture d'une session
	 */
	public void notifyEndCloseSession();
	
	
	/**
	 * 
	 */
	public void notifyWaitingForModel();

	
	/**
	 * 
	 */
	public void notifyWaitingForResult();
	
	
	/**
	 * 
	 */
	public void notifyEndResult();

}
