package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.api.log.LogsUtils;
import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.model.IModel;

/**
 * Classe implementant le comportement du haut-parleur
 */
public class FramekitThreadSpeaker extends Thread {
	
	/** Reference vers l'API */
	private Api api;
	
	/** Reference vers l'objet ComLowLevel */
	private ComLowLevel lowCom;
	
	/** Verrou */
	private Lock verrou;
	
	private LogsUtils logutils;
	/**
	 * Constructeur
	 * @param api point d'entre vers l'api
	 * @param lowCom point d'entree vers la couche bas niveau
	 * @param verrou
	 */
	public FramekitThreadSpeaker(Api api, ComLowLevel lowCom, Lock verrou) {
		this.api = api;
		this.lowCom = lowCom;
		this.verrou = verrou;
		logutils = new LogsUtils();
	}
	 

	/**
	 * Ouverture d'une session du cote de FrameKit
	 * @param sessionName Le nom de la session (utilise pour FK)
	 * @param date la date de derniere mise a jour du modele
	 * @param sessionFormalism Le formalisme du modele qu'on envoie
	 * @return booleen Reussite de l'operation d'ouverture de session
	 */
	public boolean openSession(String sessionName, int date, String sessionFormalism) {
		Object[] param = {sessionName, date, sessionFormalism};
		Api.apiLogger.entering("FrameKitThreadSpreaker", "openSession", param);
		Commande cmd = new Commande();

		// Compisition de la commande OS
		byte[] send = cmd.createCmdOS(sessionName, date, sessionFormalism);
		String serviceName;
		
		try {
			lowCom.writeCommande(send);
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThreadSpeaker", "openSession", e);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			Api.apiLogger.exiting("FrameKitThreadSpeaker", "openSession", false);
			return false;
		}

		// Mise en attente
		verrou.attendre(); //OS
		verrou.attendre(); //TD
		verrou.attendre(); //FA
		verrou.attendre(); //TL
		serviceName = verrou.attendreServiceName(); //VI
		verrou.attendre(); //FL
		
		try {
			lowCom.writeCommande(cmd.createCmdDI());
			lowCom.writeCommande(cmd.createCmdCI(serviceName, 1));
			lowCom.writeCommande(cmd.createCmdFI());
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThread", "openSession", e);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			Api.apiLogger.exiting("FrameKitThreadSpeaker", "openSession", false);
			return false;
		}
				
		verrou.attendre(); //QQ(3)
		Api.apiLogger.exiting("FrameKitThreadSpeaker", "openSession", true);
		return true;
	}
	
	/**
	 * Execute un service
	 * @param rootMenuName nom du menu pere
	 * @param menuName nom du menu
	 * @param serviceName nom du service
	 */
	public void execService(String rootMenuName, String menuName, String serviceName) {
		Object[] param = {rootMenuName, menuName, serviceName};
		Api.apiLogger.entering("FrameKitThreadSpeaker", "execService", param);
		Commande cmd = new Commande();
		try {
			
			// Si le modele a ete mis a jour depuis le dernier appel de service on doit envoyer un <MS>
			if (this.api.getDirtyState()) {
				byte[] commande = cmd.createCmdMS(this.api.getDateModel());
				lowCom.writeCommande(commande);
			}
			
			// En-tete du message
			byte[] dt = cmd.createCmdSimple("DT");
			lowCom.writeCommande(dt);
			
			byte[] pq;
	
			// Si le nom du pere est vide... Alors le service est directement sous la racine
			if (!menuName.equals(rootMenuName)) {
				pq = cmd.createCmdPQ(rootMenuName , menuName , 1);
				lowCom.writeCommande(pq);
			}
	
			pq = cmd.createCmdPQ(rootMenuName , serviceName , 1);
			lowCom.writeCommande(pq);
							
			// Pied du message
			byte[] ft = cmd.createCmdSimple("FT");
			lowCom.writeCommande(ft);

		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThreadSpeaker", "execService", e);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			
		}
		Api.apiLogger.exiting("FramekitthreadSpeaker", "execService");
	}	
		
	/**
	 * Envoyer un modele vers la plate-forme
	 */
	public void sendModel() {
		Api.apiLogger.entering("FrameKitThread", "sendModel");
		String[] modelCami;
		
		Commande cmd = new Commande();
		
		IModel model = this.api.getModel();
		modelCami = model.translate();
		
		try {
			byte[] commande = cmd.createCmdSimple("DB");
			lowCom.writeCommande(commande);
				
			for (int i = 0; i < modelCami.length; i++) {
				commande = cmd.convertToFramekit(modelCami[i]);
				Api.apiLogger.finer(modelCami[i]);
				//System.out.println(modelCami[i]);
				lowCom.writeCommande(commande);
			}
		
			commande = cmd.createCmdSimple("FB");
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThread", "sendModel", e);
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
		}
		Api.apiLogger.exiting("FrameKitThread", "sendModel");	

	}
	
	/**
	 * Changer la date du modele de la session courante
	 * @param newDate nouvelle date
	 * @return boolean
	 */
	public boolean sendNewDate(int newDate) {
		Api.apiLogger.entering("FrameKitThreadSpeaker", "sendNewDate", newDate);
		Commande cmd = new Commande();
		byte[] commande = cmd.createCmdSimple("QQ");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThread", "sendNewDate", e);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			Api.apiLogger.exiting("FrameKitThreadSpeaker", "sendNewDate", false);
			return false;
		}
		Api.apiLogger.exiting("FrameKitThreadSpeaker", "sendNewDate", true);
		return true;
	}

	/**
	 * Envoi a FrameKit une reponse a un dialogue
	 * @param results La reponse a envoyee
	 * @return boolean
	 */
	public boolean sendDialogueResponse(IDialogResult results) {
		Api.apiLogger.entering("FrameKitThreadSpeaker", "sendDialogueResponse", results);
		Commande cmd = new Commande();
				
		try {
			// Message DP
			byte[] commande = cmd.createCmdSimple("DP");
			lowCom.writeCommande(commande);
		
			// La reponse effective (Message RD)
			StringBuffer rd = new StringBuffer();
			rd.append("RD(");
			rd.append(results.getDialogId());
			rd.append(",");
			rd.append(results.getAnswerType());
			rd.append(",");
			//int tmp = results.hasBeenModified() == true?1:0;
			//rd.append(tmp);
			rd.append("1");
			rd.append(",");
			rd.append(")");
			String answer = rd.toString();			
			commande = cmd.convertToFramekit(answer);
			lowCom.writeCommande(commande);
			
			// Ensemble de resultats
			commande = cmd.createCmdSimple("DE");
			lowCom.writeCommande(commande);
			
			// Contenu de la boite de dialogue
			StringBuffer ds = new StringBuffer();
			ds.append("DS(");
			ds.append(results.getDialogId());
			ds.append(",");
			ds.append(results.getText().get(0).length());
			ds.append(":");
			ds.append(results.getText().get(0));
			ds.append(")");
			String value = ds.toString();			
			commande = cmd.convertToFramekit(value);
			lowCom.writeCommande(commande);
			
			commande = cmd.createCmdSimple("FE");
			lowCom.writeCommande(commande);
		
			// Message FP
			commande = cmd.createCmdSimple("FP");
			lowCom.writeCommande(commande);
			
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThreadSpeaker", "sendDialogueResponse", e);
			this.api.closeConnexion(1,"Connexion detruite par FrameKit",1);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			Api.apiLogger.exiting("FrameKitThreadSpeaker", "sendDialogResponse", false);
			return false;
		}
		Api.apiLogger.exiting("FrameKitThreadSpeaker", "sendDialogResponse", true);
		return true;
	}
	
	/**
	 * Suspendre la session courante
	 */
	public boolean sendSuspend() {
		Api.apiLogger.entering("FrameKitThreadSpeaker", "sendSuspend");
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdSimple("SS");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThreadSpeaker", "sendSuspend", e);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
		}
		
		// Attente du message SS
		verrou.attendre(); //SS
		Api.apiLogger.finer("Attente validee SS");
		//System.out.println("Attente validee SS");
		Api.apiLogger.exiting("FrameKitThreadSpeaker", "sendSuspend", true);
		return true;		
	}
	
	/**
	 * Reprendre une session
	 * @param sName le nom de la session
	 */
	public void sendResume(String sName) {
		Api.apiLogger.entering("FrameKitThreadSpeaker", "sendResume", sName);
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdRS(sName);
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThreadSpeaker", "sendResume", e);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);				
		}
		Api.apiLogger.exiting("FrameKitThreadSpeaker", "sendResume");
	}
	
	/**
	 * Fermer la session courante
	 */
	public boolean sendClose() {
		Api.apiLogger.entering("FrameKitThreadSpeaker", "sendResume");
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdFS(1);
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			Api.apiLogger.throwing("FrameKitThreadSpeaker", "sendClose", e);
			Api.apiLogger.warning(e.getMessage() + logutils.StackToString(e));
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
		}
		
		// Attente du message FS
		verrou.attendre(); //FS
		
		Api.apiLogger.finer("Attente validee FS");
		//System.out.println("Attente validee FS");
		Api.apiLogger.exiting("FrameKitThreadSpeaker", "sendSuspend", true);
		return true;
	}

	/**
	 * arreter un service
	 * @param root le menu 
	 * @param service le service
	 * @param option les option du service (vide)
	 * @return boolean
	 */
	public boolean stopServive(String root, String service, String option) {
		Object[] param = {root, service, option};
		Api.apiLogger.entering("FrameKitThreadSpeaker", "stopService", param);
		//Commande cmd = new Commande();
		//byte[] commande = cmd.createCmdTQ(root,service,4,option); //a construire
		//comm.writeCommande(commande);
		Api.apiLogger.exiting("FrameKitThreadSpeaker", "stopService", true);
		return true;
	}
}
