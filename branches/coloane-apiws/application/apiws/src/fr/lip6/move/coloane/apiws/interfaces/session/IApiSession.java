package fr.lip6.move.coloane.apiws.interfaces.session;

import fr.lip6.move.coloane.apiws.exceptions.ApiSessionException;
import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.model.IModel;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public interface IApiSession {

	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @param interlocutor l'interlocuteur (l'outil).
	 * @param mode le mode (interactif ou batch).
	 */
	public void openSession(String sessionDate, String sessionFormalism,String sessionName,String interlocutor,int mode) throws WrapperException, ApiSessionException;
	
	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 */
	public void openSession(String sessionDate, String sessionFormalism,String sessionName) throws WrapperException, ApiSessionException;

	/**
	 * Changer de session
	 * @param s la session a charger
	 */
	public void changeSession(IApiSession s) throws WrapperException, ApiSessionException;

	/**
	 * Fermer la session courrante.
	 */
	public void closeSession() throws WrapperException, ApiSessionException;

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
	 * Recupere les menus de la session
	 * @return les menus de la session
	 */
	public IMMenu getMenus();
	
	/**
	 * Met a jours la session
	 * @param s la session qui contient les nouvelles informations
	 */
	public void updateSession(Session s);
	
}
