package fr.lip6.move.coloane.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import fr.lip6.move.coloane.api.objects.ResultsCom;
import fr.lip6.move.coloane.api.utils.ComLowLevel;
import fr.lip6.move.coloane.api.utils.Commande;
import fr.lip6.move.coloane.api.utils.FramekitThreadListener;
import fr.lip6.move.coloane.api.utils.FramekitThreadSpeaker;
import fr.lip6.move.coloane.api.utils.Lock;

import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.IComApi;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.api.exceptions.WrongArgumentValueException;

/**
 * API de communication entre Coloane et FrameKit
 * 
 * Cette partie doit pouvoir etre changee en fonction du protocole de communication entre
 * les deux entites : Coloane et FrameKit. Tous les dialogues entre ces deux entites sont
 * effectues en CAMI
 */

public class Api implements IApi {

	/** Indique si la connexion avec Framekit est ouverte ou non */
	private boolean connexionOpened;
	
	/** Indique si une session est ouverte ou non */
	private boolean sessionOpened;
	
	/** Nom de la session courante */
	private String currentSessionName;
	
	/** Nom de la session courante */
	private String currentService;
	
	/** Socket de communication avec Framekit */
	private ComLowLevel comLowServices;
	
	/** Liste des threads speakers associes aux sessions ouvertes */
	private HashMap<String, FramekitThreadSpeaker> listeThread;
	
	/** Thread listener permettant d'ecouter tous les messages provenant de Framekit */
	private FramekitThreadListener listener;
	
	/** Verrou de la threadListener */
	private Lock verrou;
	
	/** Module de communications */
	private IComApi com;
	
	/**
	 * Constructeur
	 * @param moduleCom Le module de communication
	 */
	public Api(IComApi moduleCom) {
		
		// Le module de communication
		this.com = moduleCom;
		
		// Les services rendus par la couche basse
		this.comLowServices = new ComLowLevel();
		
		// Liste des threads ouverts pour la communication
		this.listeThread = new HashMap<String, FramekitThreadSpeaker>();
		this.verrou = new Lock();
		
		// Liste d'indicateurs
		this.connexionOpened = false;		// Est-ce que je suis authentifie auprs de la plate-forme
		this.sessionOpened = false;			// Existe-t-il une session ouverte
		this.currentSessionName = "";		// Le nom de la session courante;
	}
	
	
	/**
	 * Ouvre une connexion sur la plateforme FrameKit.
	 * 
	 * @param login le login de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 * @param ip ip de la machine hebergeant la plateforme FrameKit
	 * @param port port pour contacter la plateforme FrameKit
	 * @param apiName Le nom de l'API
	 * @param apiVersion La version de l'API
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	public boolean openConnexion(String login, String password, String ip, int port, String apiName, String apiVersion) {
		
		// Si une connexion est deja ouverte, on refuse une nouvelle connexion
		if (connexionOpened) {
			return false;
		}
		
		try {
			System.out.println("Debut connexion vers " + ip + ":" + port);
			comLowServices.createCom(ip, port);
			
			System.out.println("Dans la suite :");
			System.out.println("--> Vers FrameKit");
			System.out.println("<-- Vers Coloane");
			
			return this.camiCmdConnection(login, password, apiName, apiVersion);
			
		} catch (CommunicationCloseException e) {
			this.closeConnexion(1,"Connexion detruite par Framekit",1);
			return false;
		} catch (WrongArgumentValueException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Creation et envoi de la commande de connexion a Framekit (SC) compatible Framekit CPN-AMI 3.0
	 * 
	 * @param login    Le login de l'utilisateur
	 * @param password Le mot de passe de l'utilisateur
	 * @param apiName  Le nom de l'API
	 * @param apiVersion La version de l'API
	 * @throws erreurs de connexion qui sont catchees par openConnexion
	 */
	private boolean camiCmdConnection(String login, String password, String apiName, String apiVersion) throws Exception {
		Vector reponse;
		Vector commandeRecue;
		
		try {
			Commande cmd = new Commande();
			System.out.println("Construction de la commande CAMI...");

			/* Premiere partie : Le login et le password */	
			// Construction de la commande CAMI sans toucher aux 4 premiers octets
			byte[] send = cmd.createCmdSC(login, password);
			comLowServices.writeCommande(send);
			commandeRecue = comLowServices.readCommande();
			reponse = cmd.getArgs((String) commandeRecue.elementAt(0));
		
			/* Si la reponse de FK differe de SC */
			if (!(reponse.firstElement().equals("SC"))) {
				System.err.println("Balise non attendue (attendue SC) :" + (String) reponse.firstElement());
				return false;
			} 
		
			/* Deuxieme partie les informations sur l'API */
			send = cmd.createCmdOC(apiName, apiVersion, login);
			comLowServices.writeCommande(send);
			commandeRecue = comLowServices.readCommande();
			reponse = cmd.getArgs((String)commandeRecue.elementAt(0));

			if (!(reponse.firstElement().equals("OC"))) {
				System.err.println("Balise non attendue (attendue OC) :"+ (String) reponse.firstElement());
				return false;
			} else {
				// TODO : Affichage d'un message dans la console de l'utilisateur
				this.connexionOpened = true;
				return true;
			}
		} catch (Exception e) {
            System.err.println("Erreur dans la connexion a FrameKit: " + e.getMessage());
            throw e;
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
	public boolean openSession(String sessionName, int date, String sessionFormalism) {
		boolean result;
		
		// Si aucune connexion n'est ouverte, l'ouverture de session doit etre impossible
		if (!this.connexionOpened) {
			System.err.println("Tentative d'ouverture de session sans ouverture de connexion");
			return false;
		} 
		
		// On lance le thread charge d'ecouter les commandes CAMI en provenance de FrameKit
		if (listener == null) {
			listener = new FramekitThreadListener(this, comLowServices, this.verrou);
			listener.start();
		}	
		
		// Creation du thread associe a la session qui enverra les commandes CAMI
		FramekitThreadSpeaker speak = new FramekitThreadSpeaker(this, comLowServices, verrou);
		speak.start();
		
		// On detecte si une session est deja ouverte
		if (sessionOpened) {
			if (!this.suspendCurrentSession()) {
				this.closeConnexion();
			}
		}
		
		// On envoie les commandes necessaires pour l'ouverture de session du cote FK
		result = speak.openSession(sessionName, date, sessionFormalism);
		
		// Si le resultat est OK on ajoute le thread speaker dans une table
		// La session est ouverte
		if (result) {			
			listeThread.put(sessionName, speak);
			
			// Mise a jour des indicateurs
			this.sessionOpened = true;
			this.currentSessionName = sessionName;
			
			return true;
		
		// La session n'est pas ouverte
		} else {			
			// Suppression du thread speaker utilise
			speak = null;
			return false;
		}
	}
	
	
	/**
	 * Permet de fermer la connexion entre l'interface utilisateur et la plate-forme
	 * La fermeture de la connexion implique la fermeture prealable de toutes les sessions
	 * ATTENTION : La fermeture des sessions doit etre faite avant. (C'est un prerequis
	 */
	public void closeConnexion() {

		// Une connexion doit etre disponible
		if (this.connexionOpened) {
			
			try {
				// Fabrication de la commande FC
				Commande cmd = new Commande();
				byte[] send = cmd.createCmdFC();
				comLowServices.writeCommande(send);
				
				// Fermeture des threads restants
				if (!this.listeThread.isEmpty()) {
					
					Iterator it = listeThread.values().iterator();

					// On ferme toutes les threads liees aux modeles ouverts
					while (it.hasNext()) {
						@SuppressWarnings("unused")
						FramekitThreadSpeaker speaker = (FramekitThreadSpeaker) it.next();
						speaker = null;
					}
					listeThread.clear();
				}
				
				// Mise a jour des indicateur
				this.connexionOpened = false;
				this.sessionOpened = false;
				
				// Fermeture des socket de bas niveau
				this.comLowServices.closeCom();
				
				// Fermeture de la thread listener
				listener  = null;
				
				System.out.flush();
				System.err.flush();
				
				this.currentSessionName = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Aucune connexion detectee : Deconnexion impossible");		
		}
	}
	
	
	/**
	 * Fermture de la connexion demandee par la plateforme
	 * Cette fermeture a lieu lors de la reception d'un FC ou d'un KO
	 * @param type Raison de la fermeture de la connexion
	 * @param message Message a afficher (transmis par la plate-forme)
	 * @param severity Gravite de l'incident (transmis par la plate-forme)
	 */
	public void closeConnexion(int type, String message, int severity) {
		
		// On doit fermer tous les threads speaker ouvert (un par session)
		// On doit aussi prevenir les sessions pour qu'elles se mettent a jour
		if (!this.listeThread.isEmpty()) {
			Iterator it = listeThread.keySet().iterator();
			
			// Fermeture des threads
			while (it.hasNext()) {
				String sessionName = (String) it.next();
				@SuppressWarnings("unused")
				FramekitThreadSpeaker threadSpeaker = (FramekitThreadSpeaker) listeThread.get(sessionName);
				threadSpeaker = null;
			}
			listeThread.clear();
		}

		// Mise a jour de toutes les sessions
		this.com.closeAllSessions();
		
		// Fermeture de la thread listener
		listener = null;

		// Mise a jour des indicateurs
		this.connexionOpened = false;
		this.sessionOpened = false;
		this.currentSessionName = null;

		// Fermeture des sockets bas-niveau
		try {
			this.comLowServices.closeCom();
		} catch (Exception e) {
			System.err.println("Erreur lors de la fermeture des services de bas niveau");
		}
	}
	

	/**
	 * Suspend la session courante
	 * @return resultat de l'operation
	 */
	public boolean suspendCurrentSession() {
		
		if (this.sessionOpened && this.currentSessionName != null) {
			
			// Suppression de la session courante
			this.currentSessionName = null;
			
			// On demande a FrameKit de suspendre la session
			 return (this.getCurrentSpeaker().sendSuspend());
		} else {
			return false;
		}
		
	}
	
	
	/**
	 * Reprend l'execution d'une session
	 * @param sessionName le nom de la session
	 * @return le resultat de l'operation
	 */
	public boolean resumeSession(String sessionName) {
		/* Pas encore implementee */
		return false;
	}
	
	
	/**
	 * Supprime une session (fermeture de session)
	 * @return Le resultat de l'operation booleen
	 */
	public boolean closeCurrentSession() {
		if (!this.sessionOpened || this.currentSessionName == null) {
			return false;
		} else {
			if(this.getCurrentSpeaker().sendClose()) {
				listeThread.remove(this.currentSessionName);
				this.currentSessionName = null;
				if (listeThread.isEmpty()) {
					this.sessionOpened = false;
				}
				return true;
			}
			return false;
		}
	}
	
	
	/**
	 * Permet de notifier a la plate-forme que le modele a ete modifie
	 * @param date Nouvelle date soumise
	 * @return TRUE si ca c'est bien passe et FALSE dans le cas contraire
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
	 * Recupere l'etat du modele
	 * @return l'etat du modele (booleen)
	 */
	public boolean getDirtyState() {
		return this.com.getDirtyState();
	}
	
	/**
	 * Recupere la date de derniere modification du modele
	 * @return la date de derniere modification
	 */
	public int getDateModel() {
		return this.com.getDateModel();
	}
	
	
	/**
	 * Permet de faire une demande de service a la plate-forme
	 * @param rootMenuName nom racine de l'arbre des menus
	 * @param parentName premier noeud de l'arbre des menus
	 * @param serviceName nom du service
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		
		// On sauvegarde le nom de service pour les resultats
		this.currentService = serviceName;

		if (this.sessionOpened && this.currentSessionName != null) {
			FramekitThreadSpeaker speak;
			speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			speak.execService(rootMenuName, parentName, serviceName);
			System.out.println("Demande de service OK");
		} else {
			System.err.println("Demande de service KO");
		}
	}
	
	
	/**
	 * Envoie d'une reponse a la plate-forme
	 * @param results Ensemble des reponses de la boite de dialogue
	 * @return resultat de l'operation
	 * @see IDialogResult
	 */
	public boolean getDialogAnswers(IDialogResult results) {
		if (!this.sessionOpened || this.currentSessionName == null) {
			return false;
		} else {
			FramekitThreadSpeaker speak;
			speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			if (!speak.sendDialogueResponse(this.translateDialogResult(results))) {
				return false;
			}
			return true;
		}
	}
	
	
	/**
	 * Demande l'arret du service
	 * @param serviceName le service que l'on veut arreter
	 * @return TRUE si l'arret du service est effectif
	 */
	public boolean stopService(String serviceName) {
		return true;
	}
	
	
	/**
	 * Faire suivre les messages de FrameKit vers l'IHM (Coloane)
	 * @param type Type du message
	 * @param text Texte a ecrire
	 * @param specialType Type du message warning
	 * @throws Exception Si non respect des types
	 */
	public void sendMessageUI(int type, String text, int specialType) {
		this.com.setUiMessage(type,text,specialType);
	}
	
	
	/**
	 * Affichage d'un message dans la console historique
	 * @param message Le message a afficher dans la vue History
	 */
	public void printHistory (String message) {
		System.out.println("Affichage historique (API)"+message);
		this.com.printHistoryMessage(message);
	}
	
	/** 
	 * Affichage des menus
	 * @param menu Le menu a afficher
	 * @see IRootMenuCom
	 */
	public void drawMenu (IRootMenuCom menu) {
		this.com.drawMenu(menu);
	}
	
	/** 
	 * Demande de mise a jour des menus
	 * @param updates L'ensemble des mises a jour a effectuer sur les menus
	 */
	public void updateMenu(Vector<IUpdateMenuCom> updates) {
		this.com.updateMenu(updates);
	}
	
	/**
	 * Indique a Coloane que le modele a change d'etat
	 * @param state Le nouvel etat
	 */
	public void setModelDirty(boolean state) {
		this.com.setModelDirty(state);
	}

   
	/**
	 * Demande l'affichage d'une boite de dialogue
	 * Reminder : Les boites de dialogue sont construite sous l'autorite de la plate-forme
	 * @param dialog La boite de dialogue entierement definie
	 * @see IDialogCom
	 */
	public void drawDialog(IDialogCom dialog) {
    	this.com.drawDialog(dialog);
    }
    
   	/**
	 * Cacher une boite de dialogue
	 * @param numDialog L'identite de la boite a masquer
	 */
	public void hideDialogUI(int numDialog) {
		// TODO: Cacher une boite de dialogue
		System.err.println("Not available yet...");
	}
	
	/**
	 * Supprimer une boite de dialogue
	 * @param numDialog L'identite de la boite a detruire 
	 */
	public void destroyDialogUI(int numDialog) {
		System.err.println("Not available yet...");		
	}
	
	/**
	 * Transmet un nouveau modele a creer<br>
	 * Dans certains cas, la plate-forme renvoie des modeles a afficher dans l'IHM.<br>
	 * La construction se fait donc du cote API et l'affichage du cote Coloane
	 * @param model Modele a creer du cote Coloane
	 * @see IModel
	 */
	public void setNewModel(IModel model) {
		this.com.setNewModel(model);
	}
	
	/**
	 * Traite les resultats d'un appel de services.<br>
	 * Un appel de service peut provoquer un certain dnombre de resultats a afficher.
	 * @param resultList La liste des resultats renvoyes par la plate-forme
	 * @see ResultsCom
	 */
	public void setResults(Vector<IResultsCom> resultList) {
		if(!resultList.isEmpty()) {
			Iterator i = resultList.iterator();

			// On envoie tous les services a la com qui est chargee de les afficher
			while (i.hasNext()) {
				IResultsCom r = (IResultsCom) i.next();
				this.com.setResults(this.currentService,r);
			}
			
			this.com.printResults();
		}
	}
	
	/**
	 * Demande du modele cote Coloane.<br>
	 * L'API peut avoir besoin de recuperer le modele en cours d'edition du cote Coloane.<br>
	 * Cette methode permet de transferer le modele depuis Coloane vers l'API de communication
	 * @return le modele courant
	 * @see IModel
	 */
	public IModel getModel() {
		return this.com.getModel();
	}
	
	/**
	 * Retourne la thread qui permet d'envoyer des commandes pour la session courrante.<br>
	 * Chaque session est associee a un thread de communication.
	 * @return la thread liee a la session courrante
	 */
	public FramekitThreadSpeaker getCurrentSpeaker() {
		FramekitThreadSpeaker speak;
		speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
		return speak;
	}
	
	/*** Translate To Cami **/
	
	
	/**
	 * Traduction d'un resultat de fenetre de dialogue en CAMI.
	 * Cet objet en provenance de Coloane doit etre traduit en CAMI
	 * @param dialogResult L'objet contenant les resultats de la fenetre de dialogue
	 * @return La chaine CAMI a transmettre 
	 */
	public String translateDialogResult(IDialogResult dialogResult) {
		StringBuffer s;
        
        String returnValue = dialogResult.getText().toString();
        
        s = new StringBuffer();
        s.append("RD(");
        s.append(dialogResult.getDialogId());
        s.append(",");
        s.append(dialogResult.getAnswerType());
        s.append(",");
        s.append(dialogResult.hasBeenModified());
        s.append(",");
        s.append(returnValue.length());
        s.append(":");
        s.append(returnValue);
        s.append(")");

        return s.toString();
	}
}             
