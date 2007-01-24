package fr.lip6.move.coloane.communications.utils;

import fr.lip6.move.coloane.communications.Api;
import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.communications.objects.Dialogue;
import fr.lip6.move.coloane.exceptions.CommunicationCloseException;

/**
 * Classe implementant le comportement du haut-parleur
 */
public class FramekitThreadSpeaker extends Thread {
	
	/** Reference vers l'API */
	private Api api;
	
	/** Reference vers l'objet ComLowLevel */
	private ComLowLevel lowCom;
	
	/** Date du modele */
	private int date;

	/** Verrou */
	private Lock verrou;
	
	/**
	 * Constructeur
	 * @param apiFK point d'entre vers l'api
	 * @param lowCom point d'entree vers la couche bas niveau
	 * @param sName nom de session
	 * @param dat date
	 * @param sessionFormalism formalism
	 * @param verrou
	 */
	public FramekitThreadSpeaker(Api apiFK, ComLowLevel lowCom, String name, int date, String sessionFormalism, Lock verrou) {
		this.api = apiFK;
		this.lowCom = lowCom;
		this.date = date;
		this.verrou = verrou;
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
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
		}
	}	
		
	/**
	 * Envoyer un modele vers la plate-forme
	 */
	public void sendModel() {
		String[] modelCami;
		
		Commande cmd = new Commande();
		
		Model model = this.api.getModel();
		modelCami = model.translateToCAMI();
		
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
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
			e.printStackTrace();
		}

	}
	
	/**
	 * Changer la date du modele de la session courante
	 * @param newDate nouvelle date
	 * @return boolean
	 */
	public boolean sendNewDate(int newDate) {
		this.date = newDate;
		Commande cmd = new Commande();
		byte[] commande = cmd.createCmdSimple("QQ");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
			return false;
		}
		return true;
	}

	/**
	 * Envoi a FrameKit une reponse a un dialogue
	 * @param response La reponse a envoyee
	 * @return boolean
	 */
	public boolean sendDialogueResponse(Dialogue response) {
		String[] laReponse = response.translateToCAMI();
		Commande cmd = new Commande();
		
		for (int i = 0; i < laReponse.length; i++) {
			byte[] commande;
			commande = cmd.convertToFramekit(laReponse[i]);
			
			try {
				lowCom.writeCommande(commande);
			} catch (CommunicationCloseException e) {
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Suspendre la session courante
	 */
	public void sendSuspend() {
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdSimple("SS");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
		}
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
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);				
		}
	}
	
	/**
	 * Fermer la session courante
	 */
	public void sendClose() {
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdFS(1);
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
		}
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
		
		
		
	/**
	 * 
	 * @param sessionName
	 * @param date
	 * @param sessionFormalism
	 * @return
	 */
	public boolean openSession(String sessionName, int date, String sessionFormalism) {
		
		Commande cmd = new Commande();
		byte[] send = cmd.createCmdOS(sessionName, date, sessionFormalism);
		String serviceName;
		
		System.out.println("--> OS(" + sessionName + "," + date + "," + sessionFormalism + ")");
		
		try {
			lowCom.writeCommande(send);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
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
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
			return false;
		}
				
		verrou.attendre(); //QQ(3)
		return true;
	}
		

		/**
		 * mise a jour du menu
		 * @param majMenu les mise a jour du menu
		 */
		/*public void setMenuMAJ(Vector majMenu){
			
		}*/
		

		/**
		 * mise a jour des services du menu
		 * @param liste Vecteur de vecteur de commande TQ
		 */
		/*public void majService(Vector liste) {
			try {
				this.globalMenu = this.traducteur.updateMenu(liste, this.globalMenu);
			} catch (UnexpectedCAMICommand e) {
				e.printStackTrace();
			}
			this.api.setMenu(this.globalMenu);
			System.out.println("SPEAKER : MAJ DES SERVICES : " + liste.size());
		}*/
		

}
