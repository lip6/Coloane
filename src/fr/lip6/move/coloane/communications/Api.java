package fr.lip6.move.coloane.communications;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;


import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.communications.objects.Dialogue;
import fr.lip6.move.coloane.communications.objects.FramekitMessage;
import fr.lip6.move.coloane.communications.objects.Menu;
import fr.lip6.move.coloane.communications.utils.ComLowLevel;
import fr.lip6.move.coloane.communications.utils.Commande;
import fr.lip6.move.coloane.communications.utils.FramekitThreadListener;
import fr.lip6.move.coloane.communications.utils.FramekitThreadSpeaker;
import fr.lip6.move.coloane.communications.utils.Lock;
import fr.lip6.move.coloane.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.exceptions.WrongArgumentValueException;
import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.main.Coloane;

/**
 * 
 * Interface avec l'API (services offerts par l'API ˆ Coloane)
 * 
 * @author M2-SAR 2005-2006 Equipe 2
 * 
 */

public class Api implements IApi {

	/**
	 * Indique si la connexion avec Framekit est ouverte ou non
	 */
	private boolean connexionOpened;
	
	/**
	 * Indique si une session est ouverte ou non
	 */
	private boolean sessionOpened;
	
	/**
	 * Socket de communication avec Framekit
	 */
	private ComLowLevel comLowServices;
	
	/**
	 * Liste des threads speakers associes aux sessions ouvertes
	 */
	private HashMap listeThread;
	
	/**
	 * Nom de la session courrante
	 */
	private String currentSessionName;
	
	/**
	 * Indique si la suspension de la session courante a ete effectuee
	 */
	private boolean suspendCurrentSession;
	
	/**
	 * Indique si la reprise d'une session a ete effectuee
	 */
	private boolean resumedSession;
	
	/**
	 * Indique si la fermeture de la session courante a ete effectuee
	 */
	private boolean closedSession;
	
	/**
	 * Thread listener permettant d'ecouter tous les messages provenant de Framekit
	 */
	private FramekitThreadListener listener;
	
	/**
	 * Verrou de la threadListener
	 */
	private Lock verrou; 
	
	
	public Api() {
		
		// Les services rendus par la couche basse
		this.comLowServices = new ComLowLevel();
		
		this.listeThread = new HashMap();
		this.verrou = new Lock();
		this.connexionOpened = false;
		this.sessionOpened = false;
		this.currentSessionName = null;
		this.suspendCurrentSession = false;
		this.resumedSession = false;
		this.closedSession = false; 
	}
	
	
	/**
	 * Ouvre une connexion sur la plateforme FrameKit.
	 * 
	 * @param login le login de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 * @param FKIp ip de la machine hebergeant la plateforme FrameKit
	 * @param FKPort port pour contacter la plateforme FrameKit
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas
	 *         contraire
	 * @throws
	 */
	public boolean openConnexion(String login, String password, String ip, int port) {
		try {
			System.out.println("Debut connexion vers " + ip + ":" + port);
			comLowServices.createCom(ip, port);
			
			System.out.println("Dans la suite :");
			System.out.println("--> Vers FrameKit");
			System.out.println("<-- Vers Coloane");
			
			return this.camiCmdConnection(login, password);
			
		} catch (CommunicationCloseException e) {
			cnxClosed(1,"Connexion dŽtruite par Framekit",1);
			return false;
		} catch (WrongArgumentValueException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * CrŽŽ et envoie la commande de connexion ˆ Framekit (SC) compatible Framekit CPN-AMI 3.0
	 * 
	 * @param login    le login de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 *            
	 * @throws erreurs de connexion qui sont catchŽes par openConnexion
	 */
	private boolean camiCmdConnection(String login, String password) throws Exception {
		
		Vector reponse;
		Vector commandeRecue;
		
		try {
			Commande cmd = new Commande();
			System.out.println("Construction de la commande CAMI...");

			
			/* Premire partie : Le login et le password */	
			// Construction de la commande CAMI sans toucher aux 4 premiers octets
			byte[] send = cmd.createCmdSC(login, password);
			comLowServices.writeCommande(send);
			commandeRecue = comLowServices.readCommande();
			reponse = cmd.getComdRecuAndArg((String) commandeRecue.elementAt(0));
		
			/* Si la rŽponse de FK diffre de SC */
			if (!(reponse.firstElement().equals("SC"))) {
				System.err.println("Balise non attendue (attendue SC) :" + (String) reponse.firstElement());
				return false;
			} else {
				// TODO : Affichage d'un message dans la console
			}
		
			/* Deuxime partie les informations sur l'API */

			send = cmd.createCmdOC(Coloane.getParam("API_NAME"), Coloane.getParam("API_VERSION"), login);
			comLowServices.writeCommande(send);
			commandeRecue = comLowServices.readCommande();
			reponse = cmd.getComdRecuAndArg((String)commandeRecue.elementAt(0));

			if (!(reponse.firstElement().equals("OC"))) {
				System.err.println("Balise non attendue (attendue OC) :"+ (String) reponse.firstElement());
				return false;
			} else {
				// TODO : Affichage d'un message dans la console de l'utilisateur
				this.connexionOpened = true;
				return true;
			}
		} catch (Exception e) {
            System.err.println("Erreur dans la connexion ˆ FrameKit: " + e.getMessage());
            throw e;
        }
	}
	
	
	/**
	 * Permet de fermer la connexion entre l'interface utilisateur et la plateforme
	 * 
	 */
	public void closeConnexion() {

		if (this.connexionOpened) {
			Commande cmd = new Commande();
			byte[] send = cmd.createCmdFC();
			
			try {
				comLowServices.writeCommande(send);
				if (!this.listeThread.isEmpty()) {
					
					System.out.println("Fermeture des threads restants...");
					Iterator it = listeThread.keySet().iterator();

					// On ferme toutes les threads liees aux modeles ouverts
					while (it.hasNext()) {
						String sessionName = (String) it.next();
						System.out.println("Nom de la session :" + sessionName);
					}
					listeThread.clear();
				}
				this.connexionOpened = false;
				this.sessionOpened = false;
				
				this.comLowServices.closeCom();
				System.out.flush();
				System.err.flush();
				
				this.currentSessionName = null;
				this.suspendCurrentSession = false;
				this.resumedSession = false;
				this.closedSession = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Aucune connexion dŽtectŽe : Deconnexion impossible");		
		}
	}
	
	/**
	 * Ouverture d'une session (une session est associee a un modele)
	 * 
	 * @param sessionName est le nom du modele
	 * @param date est la date de creation de la session
	 * @param sessionFormalism est le nom du formalisme auquel est attache le modele
	 * @return retourne TRUE si la session est ouverte et FALSE dans le cas contraire
	 */
	public boolean openSession(String sessionName, int date,String sessionFormalism) {
		boolean result;
		
		if (!this.connexionOpened && !this.currentSessionName.equals(null)) {
			System.err.println("Tentative d'ouverture de session sans ouverture de connexion");
			return false;
		} 
		
		if (listener == null) {
			System.out.println("Lancement du thread listener");
			listener = new FramekitThreadListener(this, comLowServices, this.verrou);
			listener.start();
		}	
		
		// On crŽŽ la thread associe a la session cree (donc le modele) qui enverra les commandes CAMI
		FramekitThreadSpeaker speak = new FramekitThreadSpeaker(this, comLowServices,sessionName, date, sessionFormalism, verrou);
		speak.start();
		
		// On envoie les commandes nŽcessaires pour l'ouverture de session du cotŽ FK
		result = speak.openSession(sessionName, date, sessionFormalism);
		
		if (result) {			
			System.out.println("Session ouverte.");
			listeThread.put(sessionName, speak);
			this.sessionOpened = true;
			this.currentSessionName = sessionName;
			return true;
		} else {
			speak = null;
			return false;
		}
	}
	
	/**
	 * Suspend la session courante
	 * 
	 * @param sessionName est le nom de la session
	 * @return resultat de l'operation
	 */
	public boolean suspendCurrentSession(String sessionName) {
		if (this.sessionOpened && this.currentSessionName != null) {
			this.getCurrentSpeaker().sendSuspend();
			while (!this.getSuspendCurrentSession()) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					System.out.println("Exception Thread sleep ");
				}
			}
			this.setSuspendCurrentSession(false);
			this.currentSessionName = null;
			
			return true;
			
		} else {
			return false;
		}
		
	}
	
	/**
	 * Reprend l'execution d'une session
	 * 
	 * @param sessionName le nom de la session
	 * @return le resultat de l'operation
	 */
	public boolean resumeSession(String sessionName) {
		if (this.sessionOpened && (this.currentSessionName == null)) {
			if (!this.listeThread.isEmpty()) {
				Iterator it = listeThread.keySet().iterator();
				
				while (it.hasNext()) {
					FramekitThreadSpeaker idThread;
					System.out.println("Parcours de la liste des sessions");
					if (sessionName == (String) it.next()) {
						idThread = (FramekitThreadSpeaker) listeThread.get(sessionName);
						idThread.sendResume(sessionName);
						while (!this.getResumedSession()) {
							try {
								Thread.sleep(100);
							} catch (Exception e) {
								System.out.println("Exception Thread sleep ");
							}
						}
						this.setResumedSession(false);
						this.currentSessionName = sessionName;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Supprime une session
	 * 
	 * @param sessionName le nom de la session
	 * @return Le resultat de l'operation
	 */
	public boolean closeCurrentSession(String sessionName) {
		if (!this.sessionOpened || this.currentSessionName == null) {
			return false;
		} else {
			FramekitThreadSpeaker idThread = this.getCurrentSpeaker();
			idThread.sendClose();
			while (!this.getClosedSession()) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					System.out.println("Exception Thread.sleep ");
				}
			}
			listeThread.remove(this.currentSessionName);
			this.currentSessionName = null;
			this.setClosedSession(false);
			if (listeThread.isEmpty()) {
				this.sessionOpened = false;
			}
			return true;
		}
		
	}
	
	/**
	 * permet de notifier a la plate-forme que le modele a ete modifie
	 * 
	 * @param date
	 *            c'est la nouvelle date soumise
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas
	 *         contraire
	 */
	public boolean changeModeleDate(int date) {
		if (!this.sessionOpened || this.currentSessionName == null) {
			return false;
		} else {
			FramekitThreadSpeaker idThread = this.getCurrentSpeaker();
			return idThread.sendNewDate(date);
		}
	}
	
	/**
	 * permet de faire une demande de service a la plate-forme
	 * 
	 * @param rootMenuName nom racine de l'arbre des menus
	 * @param menuName premier noeud de l'arbre des menus
	 * @param serviceName nom du service
	 * @param checkMarkList liste des services actifs ou non
	 */
	public void askForService(String rootMenuName, String menuName,String serviceName, String[] checkMarkList) {
		
		
		System.out.println("Demande de service a FrameKit : ");
		System.out.println("Menu parent : " + rootMenuName);
		System.out.println("Nom du menu : " + menuName);
		System.out.println("Nom du service : " + serviceName);
		for(int j = 0; j<checkMarkList.length ; j++){
			System.out.println("option choisie : " + checkMarkList[j]);
		}
		System.out.println("-----------------------------------------------");
		
		if (this.sessionOpened && this.currentSessionName != null) {
			FramekitThreadSpeaker speak;
			speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			speak.execService(rootMenuName, menuName, serviceName,checkMarkList);
			System.out.println("Demande de service OK");
		} else {
			System.err.println("Demande de service KO");
		}
		
	}
	
	/**
	 * Reponse a la plate-forme
	 * 
	 * @param response dialogue de reponse
	 * @return resultat de l'operation
	 */
	public boolean sendDialogueResponse(Dialogue response) {
		System.out.println("sendDialogueResponse ...");
		if (!this.sessionOpened || this.currentSessionName == null) {
			return false;
		} else {
			FramekitThreadSpeaker speak;
			speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			if (speak.sendDialogueResponse(response)) {
				System.out.println("DialogueResponse envoye");
			} else {
				System.out.println("DialogueResponse pas envoye !!!");
			}
			return true;
		}
	}
	
	/**
	 * 
	 * @param serviceName
	 *            le service que l'on veut arreter
	 * @return TRUE si l'arret du service est effectif
	 */
	public boolean stopService(String serviceName) {
		return true;
	}
	
	/**
	 * fonction permettant de faire suivre les messages de framekit vers l'ihm
	 * 
	 * @param typ
	 *            type du message
	 * @param txt
	 *            texte a ecrire
	 * @param speType
	 *            type du message warning
	 * @throws Exception
	 *             si non respect des types
	 */
	public void sendMessageUI(int typ, String txt, int speType)
	throws Exception {
		try {
			//this.ihm.setMessage(new FramekitMessage(typ, txt, speType));
		} catch (Exception e) {
			throw e;
		}
	}

    /**
     * envoyr un dialogue a l'Interface Utilisateur
     * @param dialog le dialogue
     */
    public void setDialogUI(Dialogue dialog) {
        
        //this.ihm.setDialogue(dialog);
        
    }
    
    
    /**
	 * afficher une boite de dialogue
	 * 
	 * @param numDialog
	 *            identifiant du dialogue
	 */
	public void displayDialogUI(int numDialog) {
		
		//this.ihm.displayDialog(numDialog);
		
	}
    
	/**
	 * cacher une boite de dialogue
	 * 
	 * @param numDialog
	 *            identifiant du dialogue
	 */
	public void hideDialogUI(int numDialog) {
		
		//this.ihm.hideDialog(numDialog);
		
	}
	
	/**
	 * supprimer une boite de dialogue
	 * 
	 * @param numDialog
	 *            identifiant du dialogue
	 */
	public void destroyDialogUI(int numDialog) {
		
		this.coloaneServices.destroyDialog(numDialog);
		
	}
	
	/**
	 * permet de transmettre a l'Interface Utilisateur la fermeture de connexion
	 * 
	 * @param type
	 *            1
	 * @param message
	 *            message a afficher
	 * @param severity
	 *            severite de l'incident
	 */
	public synchronized void cnxClosed(int type, String message, int severity) {
		
		System.out.println("ON FERME LA CONNEXION");
				
		this.coloaneServices.connexionClosed(type, message, severity);
		if (!this.listeThread.isEmpty()) {
			Iterator it = listeThread.keySet().iterator();
			
			// on ferme toutes les threads liees aux modeles ouverts
			while (it.hasNext()) {
				FramekitThreadSpeaker idThread;
				String sessionName = (String) it.next();
				idThread = (FramekitThreadSpeaker) listeThread.get(sessionName);
				
				// gerer l'arret de la thread
				System.out.println("Arret " + idThread.getName());
//				idThread.stop();
				idThread = null;
			}
			listeThread.clear();
		}
		// gerer l'arret de la thread listener -- deprecated
//		listener.stop();
		listener = null;
		
		this.connexionOpened = false;
		this.sessionOpened = false;
		this.currentSessionName = null;
		this.suspendCurrentSession = false;
		this.resumedSession = false;
		this.closedSession = false;
		try {
		this.comLowServices.closeCom();
		} catch (Exception e) {
			System.out.println("UserFramekitAPI : cnxClosed");
		}
		System.out.flush();
		
		System.out.println("FERMETURE DE TOUTES LES THREADS");
	}
	
	
	/**
	 * Retourne le modele courrant
	 * 
	 * @return le modele courant
	 */
	public Model getModel() {
		return this.coloaneServices.getCurrentModel();
	}
	
	/**
	 * Retransmet le menu a l'UI
	 * @param menu le menu
	 */
	public void setMenu(Menu menu) {
		this.coloaneServices.setMenu(menu);
	}
	
	/**
	 * Retourne la thread qui permet d'envoyer des commandes pour la session
	 * courrante
	 * 
	 * @return la thread liee a la session courrante
	 */
	public FramekitThreadSpeaker getCurrentSpeaker() {
		FramekitThreadSpeaker speak;
		speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
		return speak;
	}
	
	/**
	 * accesseur de la variable suspendCurrentSession
	 * 
	 * @param b
	 *            true ou false
	 */
	public void setSuspendCurrentSession(boolean b) {
		this.suspendCurrentSession = b;
	}
	
	/**
	 * permet de verifier l'etat de la variable de suspendCurrentSession
	 * 
	 * @return si la session est suspendu ou pas
	 */
	public boolean getSuspendCurrentSession() {
		return this.suspendCurrentSession;
	}
	
	/**
	 * permet de verifier l'etat de la variable de resumedSession
	 * 
	 * @return false ou true
	 */
	public boolean getResumedSession() {
		return this.resumedSession;
	}
	
	/**
	 * accesseur de la variable resumedSession
	 * 
	 * @param b
	 *            indique si la session a repris ou non
	 */
	public void setResumedSession(boolean b) {
		this.resumedSession = b;
	}
	
	/**
	 * permet de verifier l'etat de la variable de closedSession
	 * 
	 * @return false ou true
	 */
	public boolean getClosedSession() {
		return this.closedSession;
	}
	
	/**
	 * accesseur de la variable closedSession
	 * 
	 * @param b
	 *            indique si la session est fermee ou non
	 */
	public void setClosedSession(boolean b) {
		this.closedSession = b;
	}
	
		
	/**
	 * transmet le model creer pas l'outil a l'utilisateur
	 * @param aModel model cree
	 */
	public void setModel(Model aModel) {
		this.coloaneServices.giveAModel(aModel);
	}
	
	/**
	 * On signal la fin du service a l'ihm
	 *
	 */
	public void endService() {
		this.coloaneServices.endService();
	}
    
    
    /**
     * Getter du listener
     * @return le listener
     */
	
	public FramekitThreadListener getListener() {
		return this.listener;
	}
    
    
    /**
     * Lit le fichier de configuration
     * @param configFilePath le fichier de configuration
     * @return l'adresse de framekit
     */
    
    public static String readConfigFile(String configFilePath) {
        String defaultframekitserveur = "localhost";
        File configFile;
        StringTokenizer st;
        BufferedReader br;
        String line;
        String optionName;

        try {
            configFile = new File(configFilePath);
            br = new BufferedReader(new FileReader(configFile));

            while (br.ready()) {
                line = br.readLine();
                st = new StringTokenizer(line);
                try {
                    optionName = st.nextToken("=");
                    if (optionName.trim().toLowerCase().equals(
                            "framekitserveur")) {
                        return line.substring(optionName.length() + 1).trim();
                    }
                } catch (NoSuchElementException e) {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            return defaultframekitserveur;
        } catch (IOException e) {
            e.printStackTrace();
            return defaultframekitserveur;
        }
        return defaultframekitserveur;
    }

    
    
}             
