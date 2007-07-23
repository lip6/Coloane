package fr.lip6.move.coloane.api.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import fr.lip6.move.coloane.api.log.*;
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
import fr.lip6.move.coloane.api.exceptions.TraceLevelException;
import fr.lip6.move.coloane.api.exceptions.WrongArgumentValueException;
import java.util.logging.*;

/**
 * API de communication entre Coloane et FrameKit
 * 
 * Cette partie doit pouvoir etre changee en fonction du protocole de
 * communication entre les deux entites : Coloane et FrameKit. Tous les
 * dialogues entre ces deux entites sont effectues en CAMI
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

	/**
	 * Thread listener permettant d'ecouter tous les messages provenant de
	 * Framekit
	 */
	private FramekitThreadListener listener;

	/** Verrou de la threadListener */
	private Lock verrou;

	/** Module de communications */
	private IComApi com;

	/** Journalisation de la classe */
	public static Logger apiLogger;

	/** Fichier d'enregistrements des logs de l'api au format simple */
	private FileHandler f;

	/** Ce champ fournissant les outils pour formater les messages d'affichage du logs*/
	private LogsUtils logsutils;

	

	/**
	 * Constructeur
	 * @param moduleCom le module de communication
	 * @param level le niveau de trace
	 * */
	
	public Api(IComApi moduleCom, int level){
		//Le module de communication
		this.com = moduleCom;

		// Les services rendus par la couche basse
		this.comLowServices = new ComLowLevel();

		// Liste des threads ouverts pour la communication
		this.listeThread = new HashMap<String, FramekitThreadSpeaker>();
		this.verrou = new Lock();

		// Liste d'indicateurs
		this.connexionOpened = false; // Est-ce que je suis authentifie aupres de la plate-forme
		this.sessionOpened = false;	  // Existe-t-il une session ouverte
		this.currentSessionName = ""; // Le nom de la session courante;
		
		//Lancement du logger
		apiLogger = Logger.getLogger("fr.lip6.move.coloane.api.main.Api");
		logsutils = new LogsUtils();
		switch (level) {
		case DEBUG:
			apiLogger.setLevel(Level.FINEST);
			break;
		

		case BETA:
			apiLogger.setLevel(Level.FINE);
			break;

		case NORMAL:
			apiLogger.setLevel(Level.INFO);
			break;

		default:
			TraceLevelException tle = new TraceLevelException("Niveau de trace non defini");
			apiLogger.throwing("Api", "Api", tle);
			apiLogger.warning(tle.getMessage());
			System.err.println(tle.getMessage());
		}
		try {
			f = new FileHandler("coloane_apicami.log");
			f.setFormatter(new ApiFormatter());
			apiLogger.addHandler(f);
		} catch (IOException e) {
			apiLogger.throwing("Api", "Api", e);
			apiLogger.warning("Erreur d'ouverture du fichier" + logsutils.StackToString(e));
		}
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
		Object[] param = { login, password, ip, port, apiName, apiVersion };
		apiLogger.entering("Api", "openConnexion", param);
		
		// Si une connexion est deja ouverte, on refuse une nouvelle connexion
		if (connexionOpened) {
			apiLogger.exiting("Api", "openConnexion", false);
			return false;
		}

		try {
			apiLogger.info("Debut connexion vers " + ip + ":" + port);
			// System.out.println("Debut connexion vers " + ip + ":" + port);
			comLowServices.createCom(ip, port);

			apiLogger.info("Dans la suite :");
			apiLogger.info("--> Vers FrameKit");
			apiLogger.info("<-- Vers Coloane");
			// System.out.println("Dans la suite :");
			// System.out.println("--> Vers FrameKit");
			// System.out.println("<-- Vers Coloane");
			boolean rep = this.camiCmdConnection(login, password, apiName, apiVersion);
			apiLogger.exiting("Api", "openConnexion", rep);
			return rep;

		} catch (CommunicationCloseException e) {
			apiLogger.throwing("Api", "openConnexion", e);
			apiLogger.warning(e.getMessage() + logsutils.StackToString(e));
			this.closeConnexion(1, "Connexion detruite par Framekit", 1);
			apiLogger.exiting("Api", "openConnexion", false);
			return false;
		} catch (WrongArgumentValueException e) {
			apiLogger.throwing("Api", "openConnexion", e);
			apiLogger.warning(e.getMessage() + logsutils.StackToString(e));
			apiLogger.exiting("Api", "openConnexion", false);
			return false;
		} catch (Exception e) {
			apiLogger.throwing("Api", "openConnexion", e);
			// e.printStackTrace();
			apiLogger.warning(e.getMessage() + logsutils.StackToString(e));
			apiLogger.exiting("Api", "openConnexion", false);
			return false;
		}
	}

	/**
	 * Creation et envoi de la commande de connexion a Framekit (SC) compatible Framekit CPN-AMI 3.0
	 * 
	 * @param login Le login de l'utilisateur
	 * @param password Le mot de passe de l'utilisateur
	 * @param apiName Le nom de l'API
	 * @param apiVersion La version de l'API
	 * @throws erreurs de connexion qui sont catchees par openConnexion
	 */
	private boolean camiCmdConnection(String login, String password, String apiName, String apiVersion) throws Exception {
		Vector reponse;
		Vector commandeRecue;
		Object[] param = { login, password, apiName, apiVersion };
		apiLogger.entering("Api", "camiCmdConnection", param);
		try {
			Commande cmd = new Commande();
			apiLogger.info("Construction de la commande CAMI");
			// System.out.println("Construction de la commande CAMI...");

			/* Premiere partie : Le login et le password */
			// Construction de la commande CAMI sans toucher aux 4 premiers
			// octets
			byte[] send = cmd.createCmdSC(login, password);
			comLowServices.writeCommande(send);
			commandeRecue = comLowServices.readCommande();
			reponse = cmd.getArgs((String) commandeRecue.elementAt(0));

			/* Si la reponse de FK differe de SC */
			if (!(reponse.firstElement().equals("SC"))) {
				apiLogger.warning("Balise non attendue (attendue SC)"
						+ (String) reponse.firstElement());
				// System.err.println("Balise non attendue (attendue SC) :" +
				// (String) reponse.firstElement());
				apiLogger.exiting("Api", "camiCmdConnection", false);
				return false;
			}

			/* Deuxieme partie les informations sur l'API */
			send = cmd.createCmdOC(apiName, apiVersion, login);
			comLowServices.writeCommande(send);
			commandeRecue = comLowServices.readCommande();
			reponse = cmd.getArgs((String) commandeRecue.elementAt(0));

			if (!(reponse.firstElement().equals("OC"))) {
				apiLogger.warning("Balise non attendue (attendue OC)"
						+ (String) reponse.firstElement());
				// System.err.println("Balise non attendue (attendue OC) :"+
				// (String) reponse.firstElement());
				apiLogger.exiting("Api", "camiCmdConnection", false);
				return false;
			} else {
				// TODO : Affichage d'un message dans la console de
				// l'utilisateur
				this.connexionOpened = true;
				apiLogger.exiting("Api", "camiCmdConnection", true);
				return true;
			}
		} catch (Exception e) {
			apiLogger.throwing("Api", "camiCmdConnection", e);
			apiLogger.warning("Erreur dans la connexion a Framekit:"
					+ e.getMessage() + logsutils.StackToString(e));
			// System.err.println("Erreur dans la connexion a FrameKit: " +
			// e.getMessage());
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
	public boolean openSession(String sessionName, int date,
			String sessionFormalism) {
		boolean result;
		Object[] param = { sessionName, date, sessionFormalism };
		apiLogger.entering("Api", "openSession", param);
		// Si aucune connexion n'est ouverte, l'ouverture de session doit etre
		// impossible
		if (!this.connexionOpened) {
			apiLogger
					.warning("Tentative d'ouverture de session sans ouverture de connexion");
			// System.err.println("Tentative d'ouverture de session sans
			// ouverture de connexion");
			apiLogger.exiting("Api", "openSession", false);
			return false;
		}

		// On lance le thread charge d'ecouter les commandes CAMI en provenance
		// de FrameKit
		if (listener == null) {
			listener = new FramekitThreadListener(this, comLowServices,
					this.verrou);
			listener.start();
		}

		// Creation du thread associe a la session qui enverra les commandes
		// CAMI
		FramekitThreadSpeaker speak = new FramekitThreadSpeaker(this,
				comLowServices, verrou);
		speak.start();

		// On detecte si une session est deja ouverte
		if (sessionOpened) {
			if (!this.suspendCurrentSession()) {
				this.closeConnexion();
			}
		}

		// On envoie les commandes necessaires pour l'ouverture de session du
		// cote FK
		result = speak.openSession(sessionName, date, sessionFormalism);

		// Si le resultat est OK on ajoute le thread speaker dans une table
		// La session est ouverte
		if (result) {
			listeThread.put(sessionName, speak);

			// Mise a jour des indicateurs
			this.sessionOpened = true;
			this.currentSessionName = sessionName;
			apiLogger.exiting("Api", "openSession", true);
			return true;

			// La session n'est pas ouverte
		} else {
			// Suppression du thread speaker utilise
			speak = null;
			apiLogger.exiting("Api", "openSession", false);
			return false;
		}
	}

	/**
	 * Permet de fermer la connexion entre l'interface utilisateur et la
	 * plate-forme La fermeture de la connexion implique la fermeture prealable
	 * de toutes les sessions ATTENTION : La fermeture des sessions doit etre
	 * faite avant. (C'est un prerequis
	 */
	public void closeConnexion() {
		apiLogger.entering("Api", "closeConnexion");
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
						FramekitThreadSpeaker speaker = (FramekitThreadSpeaker) it
								.next();
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
				listener = null;

				System.out.flush();
				System.err.flush();

				this.currentSessionName = null;
			} catch (Exception e) {
				apiLogger.throwing("Api", "closeConnexion", e);
				apiLogger.warning(e.getMessage());
				// e.printStackTrace();
			}
		} else {
			apiLogger
					.warning("Aucune connexion detectee : Detection impossible");
			// System.err.println("Aucune connexion detectee : Deconnexion
			// impossible");
		}
		apiLogger.exiting("Api", "closeConnexion");
	}

	/**
	 * Fermture de la connexion demandee par la plateforme Cette fermeture a
	 * lieu lors de la reception d'un FC ou d'un KO
	 * 
	 * @param type Raison de la fermeture de la connexion
	 * @param message Message a afficher (transmis par la plate-forme)
	 * @param severity Gravite de l'incident (transmis par la plate-forme)
	 */
	public void closeConnexion(int type, String message, int severity) {
		Object[] param = { type, message, severity };
		apiLogger.entering("Api", "closeConnexion", param);
		// On doit fermer tous les threads speaker ouvert (un par session)
		// On doit aussi prevenir les sessions pour qu'elles se mettent a jour
		if (!this.listeThread.isEmpty()) {
			Iterator it = listeThread.keySet().iterator();

			// Fermeture des threads
			while (it.hasNext()) {
				String sessionName = (String) it.next();
				@SuppressWarnings("unused")
				FramekitThreadSpeaker threadSpeaker = (FramekitThreadSpeaker) listeThread
						.get(sessionName);
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
			apiLogger.throwing("Api", "closeConnexion", e);
			apiLogger
					.warning("Erreur lors de la fermeture des services de bas niveau"
							+ logsutils.StackToString(e));
			// System.err.println("Erreur lors de la fermeture des services de
			// bas niveau");
		}
		apiLogger.exiting("Api", "closeConnexion");
	}

	/**
	 * Suspend la session courante
	 * 
	 * @return resultat de l'operation
	 */
	public boolean suspendCurrentSession() {
		apiLogger.entering("Api", "suspendConnexion");
		if (this.sessionOpened && this.currentSessionName != null) {

			// Suppression de la session courante
			this.currentSessionName = null;

			// On demande a FrameKit de suspendre la session
			apiLogger.exiting("Api", "suspendCurrentSession", this
					.getCurrentSpeaker().sendSuspend());
			return (this.getCurrentSpeaker().sendSuspend());
		} else {
			apiLogger.exiting("Api", "suspendCurrentSession", false);
			return false;
		}

	}

	/**
	 * Reprend l'execution d'une session
	 * 
	 * @param sessionName
	 *            le nom de la session
	 * @return le resultat de l'operation
	 */
	public boolean resumeSession(String sessionName) {
		/* Pas encore implementee */
		return false;
	}

	/**
	 * Supprime une session (fermeture de session)
	 * 
	 * @return Le resultat de l'operation booleen
	 */
	public boolean closeCurrentSession() {
		apiLogger.entering("Api", "closeCurrentSession");
		if (!this.sessionOpened || this.currentSessionName == null) {
			apiLogger.exiting("Api", "closeCurrentSession", false);
			return false;
		} else {
			if (this.getCurrentSpeaker().sendClose()) {
				listeThread.remove(this.currentSessionName);
				this.currentSessionName = null;
				if (listeThread.isEmpty()) {
					this.sessionOpened = false;
				}
				apiLogger.exiting("Api", "closeCurrentSession", true);
				return true;
			}
			apiLogger.exiting("Api", "closeCurrentSession", false);
			return false;
		}
	}

	/**
	 * Permet de notifier a la plate-forme que le modele a ete modifie
	 * 
	 * @param date
	 *            Nouvelle date soumise
	 * @return TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	public boolean changeModeleDate(int date) {
		apiLogger.entering("Api", "changeModelDate", date);
		if (!this.sessionOpened || this.currentSessionName == null) {
			apiLogger.exiting("Api", "changeModelDate", false);
			return false;
		} else {
			FramekitThreadSpeaker idThread = this.getCurrentSpeaker();
			apiLogger.exiting("Api", "changeModelDate", idThread
					.sendNewDate(date));
			return idThread.sendNewDate(date);
		}
	}

	/**
	 * Recupere l'etat du modele
	 * 
	 * @return l'etat du modele (booleen)
	 */
	public boolean getDirtyState() {
		apiLogger.entering("Api", "getDirtyState");
		apiLogger.exiting("Api", "getDirtyState", this.com.getDirtyState());
		return this.com.getDirtyState();
	}

	/**
	 * Recupere la date de derniere modification du modele
	 * 
	 * @return la date de derniere modification
	 */
	public int getDateModel() {
		apiLogger.entering("Api", "getDateModel");
		apiLogger.exiting("Api", "getDateModel", this.com.getDateModel());
		return this.com.getDateModel();
	}

	/**
	 * Permet de faire une demande de service a la plate-forme
	 * 
	 * @param rootMenuName
	 *            nom racine de l'arbre des menus
	 * @param parentName
	 *            premier noeud de l'arbre des menus
	 * @param serviceName
	 *            nom du service
	 */
	public void askForService(String rootMenuName, String parentName,
			String serviceName) {
		Object[] param = { rootMenuName, parentName, serviceName };
		apiLogger.entering("Api", "askForService", param);
		// On sauvegarde le nom de service pour les resultats
		this.currentService = serviceName;

		if (this.sessionOpened && this.currentSessionName != null) {
			FramekitThreadSpeaker speak;
			speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			speak.execService(rootMenuName, parentName, serviceName);
			apiLogger.info("Demande de service OK");
			// System.out.println("Demande de service OK");
		} else {
			apiLogger.warning("Demande de service KO");
			// System.err.println("Demande de service KO");
		}
		apiLogger.exiting("Api", "askForService");
	}

	/**
	 * Envoie d'une reponse a la plate-forme
	 * 
	 * @param results
	 *            Ensemble des reponses de la boite de dialogue
	 * @return resultat de l'operation
	 * @see IDialogResult
	 */
	public boolean getDialogAnswers(IDialogResult results) {
		apiLogger.entering("Api", "getDialogAnswers", results);
		if (!this.sessionOpened || this.currentSessionName == null) {
			apiLogger.exiting("Api", "getDialogAnswers", false);
			return false;
		} else {
			FramekitThreadSpeaker speak;
			speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			if (!speak.sendDialogueResponse(results)) {
				apiLogger.exiting("Api", "getDialogAnswers", false);
				return false;
			}
			apiLogger.exiting("Api", "getDialogAnswers", true);
			return true;
		}
	}

	/**
	 * Demande l'arret du service
	 * 
	 * @param serviceName
	 *            le service que l'on veut arreter
	 * @return TRUE si l'arret du service est effectif
	 */
	public boolean stopService(String serviceName) {
		apiLogger.entering("Api", "stopService", serviceName);
		apiLogger.exiting("Api", "stopService", true);
		return true;
	}

	/**
	 * Faire suivre les messages de FrameKit vers l'IHM (Coloane)
	 * 
	 * @param type
	 *            Type du message
	 * @param text
	 *            Texte a ecrire
	 * @param specialType
	 *            Type du message warning
	 * @throws Exception
	 *             Si non respect des types
	 */
	public void sendMessageUI(int type, String text, int specialType) {
		Object[] param = { type, text, specialType };
		apiLogger.entering("Api", "sendMessageUI", param);
		this.com.setUiMessage(type, text, specialType);
		apiLogger.exiting("Api", "sendMessageUI");
	}

	/**
	 * Affichage d'un message dans la console historique
	 * 
	 * @param message
	 *            Le message a afficher dans la vue History
	 */
	public void printHistory(String message) {
		apiLogger.entering("Api", "printHistory", message);
		apiLogger.info("Affichage historique (API)" + message);
		// System.out.println("Affichage historique (API)"+message);
		this.com.printHistoryMessage(message);
		apiLogger.exiting("Api", "printHistory");
	}

	/**
	 * Affichage des menus
	 * 
	 * @param menu
	 *            Le menu a afficher
	 * @see IRootMenuCom
	 */
	public void drawMenu(IRootMenuCom menu) {
		apiLogger.entering("Api", "drawMenu", menu);
		this.com.drawMenu(menu);
		apiLogger.exiting("Api", "drawMenu");
	}

	/**
	 * Demande de mise a jour des menus
	 * 
	 * @param updates
	 *            L'ensemble des mises a jour a effectuer sur les menus
	 */
	public void updateMenu(Vector<IUpdateMenuCom> updates) {
		apiLogger.entering("Api", "updateMenu", updates);
		this.com.updateMenu(updates);
		apiLogger.exiting("Api", "updateMenu");
	}

	/**
	 * Indique a Coloane que le modele a change d'etat
	 * 
	 * @param state
	 *            Le nouvel etat
	 */
	public void setModelDirty(boolean state) {
		apiLogger.entering("Api", "setModelDirty", state);
		this.com.setModelDirty(state);
		apiLogger.exiting("Api", "setModelDirty");
	}

	/**
	 * Demande l'affichage d'une boite de dialogue Reminder : Les boites de
	 * dialogue sont construite sous l'autorite de la plate-forme
	 * 
	 * @param dialog
	 *            La boite de dialogue entierement definie
	 * @see IDialogCom
	 */
	public void drawDialog(IDialogCom dialog) {
		apiLogger.entering("Api", "drawDialog", dialog);
		this.com.drawDialog(dialog);
		apiLogger.exiting("Api", "drawDialog");
	}

	/**
	 * Cacher une boite de dialogue
	 * 
	 * @param numDialog
	 *            L'identite de la boite a masquer
	 */
	public void hideDialogUI(int numDialog) {
		apiLogger.entering("Api", "hideDialogUI", numDialog);
		// TODO: Cacher une boite de dialogue
		apiLogger.warning("Not available yet");
		// System.err.println("Not available yet...");
		apiLogger.exiting("Api", "hideDialogUI");
	}

	/**
	 * Supprimer une boite de dialogue
	 * 
	 * @param numDialog L'identite de la boite a detruire
	 * 
	 */
	public void destroyDialogUI(int numDialog) {
		apiLogger.entering("Api", "destroyDialogUI", numDialog);
		apiLogger.warning("Not available yet");
		// System.err.println("Not available yet...");
		apiLogger.exiting("Api", "hideDialogUI");
	}

	/**
	 * Transmet un nouveau modele a creer<br>
	 * Dans certains cas, la plate-forme renvoie des modeles a afficher dans
	 * l'IHM.<br>
	 * La construction se fait donc du cote API et l'affichage du cote Coloane
	 * 
	 * @param model
	 *            Modele a creer du cote Coloane
	 * @see IModel
	 */
	public void setNewModel(IModel model) {
		apiLogger.entering("Api", "destroyDialogUI", model);
		this.com.setNewModel(model);
		apiLogger.exiting("Api", "setNewModel");
	}

	/**
	 * Traite les resultats d'un appel de services.<br>
	 * Un appel de service peut provoquer un certain dnombre de resultats a
	 * afficher.
	 * 
	 * @param resultList
	 *            La liste des resultats renvoyes par la plate-forme
	 * @see ResultsCom
	 */
	public void setResults(Vector<IResultsCom> resultList) {
		apiLogger.entering("Api", "setResults", resultList);
		if (!resultList.isEmpty()) {
			Iterator i = resultList.iterator();

			// On envoie tous les services a la com qui est chargee de les
			// afficher
			while (i.hasNext()) {
				IResultsCom r = (IResultsCom) i.next();
				this.com.setResults(this.currentService, r);
			}

			this.com.printResults();
		} else {
			this.com.setResults("", null);
		}
		apiLogger.exiting("Api", "setResults");
	}

	/**
	 * Demande du modele cote Coloane.<br>
	 * L'API peut avoir besoin de recuperer le modele en cours d'edition du cote
	 * Coloane.<br>
	 * Cette methode permet de transferer le modele depuis Coloane vers l'API de
	 * communication
	 * 
	 * @return le modele courant
	 * @see IModel
	 */
	public IModel getModel() {
		apiLogger.entering("Api", "getModel");
		apiLogger.exiting("Api", "getModel", this.com.getModel());
		return this.com.getModel();
	}

	/**
	 * Retourne la thread qui permet d'envoyer des commandes pour la session
	 * courrante.<br>
	 * Chaque session est associee a un thread de communication.
	 * 
	 * @return la thread liee a la session courrante
	 */
	public FramekitThreadSpeaker getCurrentSpeaker() {
		apiLogger.entering("Api", "getCurrentSpeaker");
		FramekitThreadSpeaker speak;
		speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
		apiLogger.exiting("Api", "getCurrentSpeaker", speak);
		return speak;
	}
}
