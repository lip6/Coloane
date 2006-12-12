package fr.lip6.move.coloane.communications.utils;

import java.util.Vector;


import fr.lip6.move.coloane.communications.Api;
import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.communications.objects.Dialogue;
import fr.lip6.move.coloane.exceptions.CommunicationCloseException;

/**
 * 
 * @author DQS equipe 2 (Styx)
 *
 */
public class FramekitThreadSpeaker extends Thread {
	
	/**
	 * RŽference vers l'API
	 */
	private Api api;
	
	/**
	 * Reference vers l'objet ComLowLevel
	 */
	private ComLowLevel comm;
	
	/**
	 * Formalisme
	 */
	private String formalism;
	
	/**
	 * Date du modele
	 */
	private int date;
	
	/**
	 * Est-ce que la date a ete mise a jour
	 */
	private boolean dateUpdated;
	
	/**
	 * Nom de la session
	 */
	private String sessionName;
	
	/**
	 * Menu
	 */
	private Vector globalMenu;


	/**
	 * Verrou
	 */
	private Lock verrou;
	
	/**
	 * Permet la mise a jour du menu
	 */
	private CamiTranslator traducteur;
	
	/**
	 * Constructeur
	 * @param apiFK point d'entre vers l'api
	 * @param com point d'entree vers la com
	 * @param sName nom de session
	 * @param dat date
	 * @param sessionFormalism formalism
	 * @param verrou
	 */
	public FramekitThreadSpeaker(Api apiFK, ComLowLevel com, String sName, int dat,String sessionFormalism, Lock verrou) {
		this.api = apiFK;
		this.comm = com;
		this.date = dat;
		this.formalism = sessionFormalism;
		this.sessionName = sName;
		this.verrou = verrou;
		this.traducteur = new CamiTranslator();
		this.dateUpdated = false;
	}
	 

	/**
	 * Execute un service
	 * @param rootMenuName nom du menu pere
	 * @param menuName nom du menu
	 * @param serviceName nom du service
	 * @param checkMarkList les options d'executions
	 */
	public void execService(String rootMenuName, String menuName,String serviceName, String[] checkMarkList) {
	
		Commande cmd = new Commande();
		
		if (this.dateUpdated) {
			byte[] commande = cmd.createCmdMS(this.date);
			try {
				comm.writeCommande(commande);
			} catch (CommunicationCloseException e) {
				e.printStackTrace();
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				this.api.getListener().stop();
				this.stop();
			}
			this.dateUpdated = false;
		}
		
		byte[] dt = cmd.createCmdSimple("DT");
		byte[] pq;
		byte[] ft = cmd.createCmdSimple("FT");
		String s1 = new String(dt, 4, dt.length - 4);
		System.out.println("envoi de : " + s1);
		
		try {
			comm.writeCommande(dt);
			
			System.out.println("MENU NAME : " + menuName);
			System.out.println("SERVICE NAME : " + serviceName);
			System.out.println("CHECKMARK : " + checkMarkList[0]);
			
			
			if (!menuName.equals("")) {
				pq = cmd.createCmdPQ(rootMenuName , menuName , 1);
				comm.writeCommande(pq);
			}
					
			
			pq = cmd.createCmdPQ(rootMenuName , serviceName , 1);
			comm.writeCommande(pq);
						
			for (int i = 0; i < checkMarkList.length; i++) {
				pq = cmd.createCmdPQ(rootMenuName , checkMarkList[i] , 1);
				comm.writeCommande(pq);
			}
			comm.writeCommande(ft);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
	/**
	 * 
	 *
	 */
	public void sendModel() {
		Model m;
		String[] leModel;
		byte[] commande;
		Commande cmd = new Commande();
		
		m = this.api.getModel();
		leModel = m.translateToCAMI();
		
		commande = cmd.createCmdSimple("DB");
		try {
			comm.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
			this.api.getListener().stop();
			this.stop();
		}
				
		for (int i = 0; i < leModel.length; i++) {
			commande = cmd.convertToFramekit(leModel[i]);
			System.out.println(leModel[i]);
			
			try {
				comm.writeCommande(commande);
			} catch (CommunicationCloseException e) {
				e.printStackTrace();
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				this.api.getListener().stop();
				this.stop();
			}
		}
		
		commande = cmd.createCmdSimple("FB");
		try {
			comm.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
			this.api.getListener().stop();
			this.stop();
		}
				
		System.out.println("Model envoyer (" + formalism + ", " + date + ", " + sessionName + ")");
	}

	/**
	 * Envoi a FrameKit une reponse a un dialogue
	 * @param response : la reponse a envoyee
	 * @return boolean
	 */
	public boolean sendDialogueResponse(Dialogue response) {
		String[] laReponse = response.translateToCAMI();
		byte[] commande;
		Commande cmd = new Commande();
		
		for (int i = 0; i < laReponse.length; i++) {
			commande = cmd.convertToFramekit(laReponse[i]);
			try {
				comm.writeCommande(commande);
			} catch (CommunicationCloseException e) {
				e.printStackTrace();
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				this.api.getListener().stop();
				return false;
			}
		}
		return true;
		
	}
	
	/**
	 * Suspendre la session courante
	 *
	 */
	public void sendSuspend() {
		byte[] commande;
		Commande cmd = new Commande();
		commande = cmd.createCmdSimple("SS");
		try {
			comm.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
			this.api.getListener().stop();
			this.stop();
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
			comm.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			e.printStackTrace();
			this.api.cnxClosed(1,"Connexion detruite par Framekit",1);				
			this.api.getListener().stop();
			this.stop();
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
				comm.writeCommande(commande);
			} catch (CommunicationCloseException e) {
				e.printStackTrace();
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				this.api.getListener().stop();
				this.stop();
			}
		}

		/**
		 * Changer la date du modele de la session courante
		 * @param newDate nouvelle date
		 * @return boolean
		 */
		public boolean sendNewDate(int newDate) {
			this.date = newDate;
			this.dateUpdated = true;
			Commande cmd = new Commande();
			byte[] commande = cmd.createCmdSimple("QQ");
			try {
				comm.writeCommande(commande);
			} catch (CommunicationCloseException e) {
				e.printStackTrace();
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				this.api.getListener().stop();
				return false;
			}
					
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
				comm.writeCommande(send);
			} catch (CommunicationCloseException e) {
				e.printStackTrace();
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				this.api.getListener().stop();
				return false;
			}
			
			verrou.attendre(); //OS
			verrou.attendre(); //TD
			verrou.attendre(); //FA
			verrou.attendre(); //TL
			serviceName = verrou.attendreServiceName(); //VI
			verrou.attendre(); //FL
			
			try {
				comm.writeCommande(cmd.createCmdDI());
				comm.writeCommande(cmd.createCmdCI(serviceName, 1));
				comm.writeCommande(cmd.createCmdFI());
			} catch (CommunicationCloseException e) {
				e.printStackTrace();
				this.api.cnxClosed(1,"Connexion detruite par Framekit",1);
				this.api.getListener().stop();
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
