package fr.lip6.move.coloane.apiws.interfaces.session;

import fr.lip6.move.coloane.apiws.interfaces.objects.IModel;

public interface IApiSession {

	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism son formalisme.
	 * @param sessionName son nom.
	 * @param interlocutor son interlocuteur (l'outil).
	 * @param mode: son mode (interactif ou batch).
	 */
	public void openSession(String sessionDate, String sessionFormalism,String sessionName,String interlocutor,int mode);
	
	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism son formalisme.
	 * @param sessionName son nom.
	 */
	public void openSession(String sessionDate, String sessionFormalism,String sessionName);

	/**
	 * Suspendre la session courrante
	 * @return vraie si la suspension de la session reussie, faux sinon.
	 */
	public boolean suspendSession();

	/**
	 * Reprendre la session courrante.
	 * @return vraie si la reprise de la session reussie, faux sinon.
	 */
	public boolean resumeSession();

	/**
	 * Fermer la session courrante.
	 */
	public void closeSession();


	/**
	 * Demander un service sur la session courrante
	 * Le model sera toujours envoyer au wrapper pour tous service
	 * @param rootName 
	 * @param menuName
	 * @param serviceName
	 * @param model
	 */
	public void askForService(String rootName,String menuName, String serviceName,IModel model);


	/**
	 * Demander un service sur la session courrante
	 * Le model sera toujours envoyer au wrapper pour tous service
	 * @param rootName
	 * @param menuName
	 * @param serviceName
	 * @param date
	 * @param model
	 */
	public void askForService(String rootName,String menuName, String serviceName, String date,IModel model);

	
	/*
	Inutile d'avoir cette fonction: le model sera envoyer par a chaque demande de service
	public void sendModel(IModel model);
	*/
	
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
	 * Recupere le gestionnaire de session
	 * @return le gestionnaire de session
	 */
	public ISessionController getSessionController();
	
	
	/**
	 * 
	 */
	public void invalidModel();
	
	/**
	 * 
	 */
	public void notifyEndOpenSession();

	/**
	 * 
	 */
	public void notifyEndSuspendSession();
	
	/**
	 * 
	 * @param nameSession
	 */
	public void notifyEndResumeSession(String nameSession);

	/**
	 * 
	 */
	public void notifyEndCloseSession();

}
