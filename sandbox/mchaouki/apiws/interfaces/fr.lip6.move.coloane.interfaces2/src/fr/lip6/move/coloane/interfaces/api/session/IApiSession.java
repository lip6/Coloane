package fr.lip6.move.coloane.interfaces.api.session;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.api.objects.menu.IOption;
import fr.lip6.move.coloane.interfaces.api.objects.model.IModel;

public interface IApiSession {
	
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
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @param interlocutor l'interlocuteur (l'outil).
	 * @param mode le mode (interactif ou batch).
	 * @return true, si la session est bien ouvert, false sinon
	 */
	public boolean openSession(String sessionDate, String sessionFormalism,String sessionName,String interlocutor,int mode);
	
	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @return true, si la session est bien ouvert, false sinon
	 */
	public boolean openSession(String sessionDate, String sessionFormalism,String sessionName);

	/**
	 * Suspend la session courrante
	 * @return true, si la session est bien suspendu, false sinon
	 */
	public boolean suspendSession();
	
	/**
	 * Restaure la session passer en parametre
	 * @param sessionName la session a restaurer
	 * @return true, si la session est bien restaure, false sinon
	 */
	public boolean resumeSession(String sessionName);

	/**
	 * Ferme la session courrante.
	 * @return true, si la session est bien fermee, false sinon
	 */
	public boolean closeSession();

	/**
	 * Demande un service sur la session courrante
	 * @param rootName 
	 * @param menuName
	 * @param serviceName
	 * @param options 
	 * @return true, si la demande de service a reussie, false sinon
	 */
	public boolean askForService(String rootName,String menuName, String serviceName, ArrayList<IOption> options,IModel model);

	/**
	 * Demande un service sur la session courrante
	 * @param rootName
	 * @param menuName
	 * @param serviceName
	 * @param options 
	 * @param date
	 * @return true, si la demande de service a reussie, false sinon
	 */
	public boolean askForService(String rootName,String menuName, String serviceName, ArrayList<IOption> options,IModel model, String date);

	/**
	 * Envoie la boite de dialog reponse
	 * @param dialogAnswer la boite de dialogue reponse
	 * @return true, si l'envoie a reussie, false sinon
	 */
	public boolean sendDialogAnswer(IDialogAnswer dialogAnswer);
	
	/**
	 * 
	 * @param model
	 */
	public void sendModel(IModel model);
	
	/**
	 * 
	 */
	public void invalidModel();
}
