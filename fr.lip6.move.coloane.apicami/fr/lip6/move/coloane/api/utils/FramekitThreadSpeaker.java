package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.api.Api;
import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
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
	}
	 

	/**
	 * Ouverture d'une session du cote de FrameKit
	 * @param sessionName LE nom de la session (utilse pour FK)
	 * @param date la date de derniere mise a jour du modele
	 * @param sessionFormalism Le formalisme du modele qu'on envoie
	 * @return booleen Reussite de l'operation d'ouverture de session
	 */
	public boolean openSession(String sessionName, int date, String sessionFormalism) {
		
		Commande cmd = new Commande();

		// Compisition de la commande OS
		byte[] send = cmd.createCmdOS(sessionName, date, sessionFormalism);
		String serviceName;
		
		try {
			lowCom.writeCommande(send);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
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
			e.printStackTrace();
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			return false;
		}
				
		verrou.attendre(); //QQ(3)
		return true;
	}
	
	/**
	 * Execute un service
	 * @param rootMenuName nom du menu pere
	 * @param menuName nom du menu
	 * @param serviceName nom du service
	 */
	public void execService(String rootMenuName, String menuName, String serviceName) {
	
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
			e.printStackTrace();
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
		}
	}	
		
	/**
	 * Envoyer un modele vers la plate-forme
	 */
	public void sendModel() {
		String[] modelCami;
		
		Commande cmd = new Commande();
		
		IModel model = this.api.getModel();
		modelCami = model.translate();
		
		try {
			byte[] commande = cmd.createCmdSimple("DB");
			lowCom.writeCommande(commande);
				
			for (int i = 0; i < modelCami.length; i++) {
				commande = cmd.convertToFramekit(modelCami[i]);
				System.out.println(modelCami[i]);
				lowCom.writeCommande(commande);
			}
		
			commande = cmd.createCmdSimple("FB");
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			e.printStackTrace();
		}

	}
	
	/**
	 * Changer la date du modele de la session courante
	 * @param newDate nouvelle date
	 * @return boolean
	 */
	public boolean sendNewDate(int newDate) {
		Commande cmd = new Commande();
		byte[] commande = cmd.createCmdSimple("QQ");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
			return false;
		}
		return true;
	}

	/**
	 * Envoi a FrameKit une reponse a un dialogue
	 * @param results La reponse a envoyee
	 * @return boolean
	 */
	public boolean sendDialogueResponse(String results) {
		Commande cmd = new Commande();
				
		try {
			// Message DP
			byte[] commande = cmd.createCmdSimple("DP");
			lowCom.writeCommande(commande);
		
			// La reponse effective (Message RD)
			String answer = results;
			commande = cmd.convertToFramekit(answer);
			lowCom.writeCommande(commande);
		
			// Message FP
			commande = cmd.createCmdSimple("DP");
			lowCom.writeCommande(commande);
			
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1,"Connexion detruite par FrameKit",1);
			e.printStackTrace();
			return false;
		}
	return true;
	}
	
	/**
	 * Suspendre la session courante
	 */
	public boolean sendSuspend() {
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdSimple("SS");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
		}
		
		// Attente du message SS
		verrou.attendre(); //SS
		
		System.out.println("Attente validee SS");
		return true;		
	}
	
	/**
	 * Reprendre une session
	 * @param sName le nom de la session
	 */
	public void sendResume(String sName) {
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdRS(sName);
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);				
		}
	}
	
	/**
	 * Fermer la session courante
	 */
	public boolean sendClose() {
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdFS(1);
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.closeConnexion(1,"Connexion detruite par Framekit",1);
		}
		
		// Attente du message FS
		verrou.attendre(); //FS
		
		System.out.println("Attente validee FS");
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
		//Commande cmd = new Commande();
		//byte[] commande = cmd.createCmdTQ(root,service,4,option); //a construire
		//comm.writeCommande(commande);
		return true;
	}
}
