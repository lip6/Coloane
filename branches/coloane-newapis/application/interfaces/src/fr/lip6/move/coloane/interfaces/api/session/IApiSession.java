package fr.lip6.move.coloane.interfaces.api.session;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.model.IModel;

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
	 * Recupere l'identifiant d'une session.
	 * Cette methode est utiliser par l'apiws, pour identifier une session 
	 * car le nom d'un session n'est pas forcement unique chez le wrapper.
	 * Dans le cas de l'api-antlr idSession peut-etre considerer comme egale a nameSession.
	 * @return l'identifiant d'une session.
	 */
	public String getIdSession();
	
	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @param interlocutor l'interlocuteur (l'outil).
	 * @param mode le mode (interactif ou batch).
	 * @return les informations sur la session ouverte
	 */
	public ISessionInfo openSession(String sessionDate, String sessionFormalism,String sessionName,String interlocutor,int mode) throws ApiException;
	
	/**
	 * Ouvre une session
	 * @param sessionDate date de la session.
	 * @param sessionFormalism formalisme de la session.
	 * @param sessionName nom de la session.
	 * @return les informations sur la session ouverte
	 */
	public ISessionInfo openSession(String sessionDate, String sessionFormalism,String sessionName) throws ApiException;

	/**
	 * Suspend la session courrante
	 * @return true, si la session est bien suspendu, false sinon
	 */
	public boolean suspendSession() throws ApiException;
	
	/**
	 * Restaure la session courrante
	 * @return true, si la session est bien restaure, false sinon
	 */
	public boolean resumeSession() throws ApiException;

	/**
	 * Ferme la session courrante.
	 * @return true, si la session est bien fermee, false sinon
	 */
	public boolean closeSession() throws ApiException;

	/**
	 * Demande un service sur la session courrante
	 * @param rootName 
	 * @param menuName
	 * @param serviceName
	 * @param options 
	 * @param model
	 * @return true, si la demande de service a reussie, false sinon
	 */
	public boolean askForService(String rootName,String menuName, String serviceName, ArrayList<String> options,IModel model) throws ApiException;

	/**
	 * Demande un service sur la session courrante
	 * @param rootName
	 * @param menuName
	 * @param serviceName
	 * @param options 
	 * @param model
	 * @param date
	 * @return true, si la demande de service a reussie, false sinon
	 */
	public boolean askForService(String rootName,String menuName, String serviceName, ArrayList<String> options,IModel model, String date) throws ApiException;

	/**
	 * Envoie la boite de dialogue reponse
	 * @param idDialog l'identifiant de la boite de dialog
	 * @param buttonAnswer le type de la reponse (OK/CANCEL)
	 * @param modified si la boite de dialogue a subi une modification
	 * @param value ????
	 * @param lines ????
	 * @param objects ???
	 * @return true, si l'envoie a reussie, false sinon
	 */
	public boolean sendDialogAnswer(int idDialog, int buttonAnswer, boolean modified, String value, ArrayList<String> lines, ArrayList<Integer> objects) throws ApiException;
	
	/**
	 * 
	 * @param model
	 */
	public void sendModel(IModel model) throws ApiException;
	
	/**
	 * 
	 */
	public void invalidModel() throws ApiException;
}