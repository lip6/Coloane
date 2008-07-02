package fr.lip6.move.coloane.api.main;

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.api.log.ColoaneHandler;

import fr.lip6.move.coloane.api.utils.ComLowLevel;
import fr.lip6.move.coloane.api.utils.FKCommand;
import fr.lip6.move.coloane.api.utils.FramekitThreadListener;
import fr.lip6.move.coloane.api.utils.FramekitThreadSpeaker;

import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;
import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.impl.IModel;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogFormatter;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * API de communication entre Coloane et FrameKit
 *
 * Cette partie doit pouvoir etre changee en fonction du protocole de
 * communication entre les deux entites : Coloane et FrameKit. Tous les
 * dialogues entre ces deux entites sont effectues en CAMI
 */

public final class Api implements IApi {

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

	/** Module de communications */
	private IComApi com;

	/** Journalisation de la classe */
	private static Logger apiLog;

	/** Instance de la classe privee pour singleton */
	private static Api instance;

	/**
	 * Retourne l'instance d'API qui convient.<br>
	 * Evite les doublons !
	 * @param moduleCom Le module Com en association avec l'API
	 * @return L'API
	 */
	public static synchronized Api getInstance(IComApi moduleCom) {
		if (instance == null) { instance = new Api(moduleCom); }
		return instance;
	}

	/**
	 * Constructeur privee pour singleton<br>
	 * Mise en place de la classe de communications bas niveau<br>
	 * Ce constructeur se charge aussi de la mise en place du module de log
	 * @param moduleCom le module de communication
	 */
	private Api(IComApi moduleCom) {
		this.com = moduleCom;

		// Les services rendus par la couche basse
		this.comLowServices = new ComLowLevel();

		// Liste des threads ouverts pour la communication
		this.listeThread = new HashMap<String, FramekitThreadSpeaker>();

		// Liste d'indicateurs
		this.connexionOpened = false; // Est-ce que je suis authentifie aupres de la plate-forme
		this.sessionOpened = false;	  // Existe-t-il une session ouverte
		this.currentSessionName = ""; // Le nom de la session courante;

		// Initialisation du logger
		this.initializeLogger();

		apiLog.config("Initialisation de l'API de communication CAMI");
		apiLog.config("Initialisation du module de Log");
	}

	/**
	 * Ouvre une connexion sur la plateforme FrameKit.
	 * @param login le login de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 * @param ip ip de la machine hebergeant la plateforme FrameKit
	 * @param port port pour contacter la plateforme FrameKit
	 * @param apiName Le nom de l'API
	 * @param apiVersion La version de l'API
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	public boolean openConnection(String login, String password, String ip, int port, String apiName, String apiVersion) {

		// Si une connexion est deja ouverte, on refuse une nouvelle connexion
		if (connexionOpened) {
			apiLog.warning("Une session est deja ouverte");
			return false;
		}

		try {
			apiLog.finer("Debut connexion vers " + ip + ":" + port);

			// Creation de la socket de communications
			comLowServices.createCom(ip, port);

			apiLog.finer("Dans la suite :");
			apiLog.finer("--> Vers FrameKit");
			apiLog.finer("<-- Vers Coloane");

			// Ecriture de la commande de demande de connexion et attente de la reponse
			boolean rep = this.camiCmdConnection(login, password, apiName, apiVersion);
			return rep;

		} catch (CommunicationCloseException e) {
			apiLog.throwing("Api", "openConnection", e);
			return false;
		} catch (IOException e) {
			apiLog.throwing("Api", "openConnection", e);
			return false;
		}
	}

	/**
	 * Creation et envoi de la commande de connexion a Framekit (SC) compatible Framekit CPN-AMI 3.0
	 * @param login Le login de l'utilisateur
	 * @param password Le mot de passe de l'utilisateur
	 * @param apiName Le nom de l'API
	 * @param apiVersion La version de l'API
	 * @throws erreurs de connexion qui sont catchees par openConnexion
	 */
	private boolean camiCmdConnection(String login, String password, String apiName, String apiVersion) throws CommunicationCloseException {
		try {
			FKCommand cmd = new FKCommand();

			/* Premiere partie : Le login et le password */
			// Construction de la commande CAMI sans toucher aux 4 premiers octets
			byte[] send = cmd.createCmdSC(login, password);

			// Envoi de la commande vers FK
			comLowServices.writeCommande(send);

			// Lecture de la reponse en provenance de FK
			Vector<String> commandeRecue = comLowServices.readCommande();
			Vector<String> reponse = FKCommand.getArgs((String) commandeRecue.elementAt(0));

			/* Si la reponse de FK differe de SC */
			if (!(reponse.firstElement().equals("SC"))) {
				apiLog.warning("Balise non attendue (attendue SC)" + (String) reponse.firstElement());
				return false;
			}

			/* Deuxieme partie les informations sur l'API */
			send = cmd.createCmdOC(apiName, apiVersion, login);
			comLowServices.writeCommande(send);
			commandeRecue = comLowServices.readCommande();
			reponse = FKCommand.getArgs((String) commandeRecue.elementAt(0));

			if (!(reponse.firstElement().equals("OC"))) {
				apiLog.warning("Balise non attendue (attendue OC)" + (String) reponse.firstElement());
				return false;
			} else {
				this.connexionOpened = true;
				return true;
			}
		} catch (CommunicationCloseException e) {
			apiLog.throwing("Api", "camiCmdConnection", e);
			throw e;
		} catch (SyntaxErrorException e) {
			apiLog.throwing("Api", "camiCmdConnection", e);
			throw new CommunicationCloseException("Erreur lors de l'envoi et/ou de la reception des commandes");
		}
	}

	/**
	 * Ouverture d'une session (une session est associee a un modele)
	 * @param sessionName est le nom du modele
	 * @param date est la date de creation de la session
	 * @param sessionFormalism est le nom du formalisme auquel est attache le modele
	 * @return retourne TRUE si la session est ouverte et FALSE dans le cas contraire
	 */
	public  boolean openSession(String sessionName, int date, String sessionFormalism) {
		// Si aucune connexion n'est ouverte, l'ouverture de session doit etre impossible
		if (!this.connexionOpened) {
			apiLog.warning("Tentative d'ouverture de session sans ouverture de connexion");
			return false;
		}

		// On lance le thread charge d'ecouter les commandes CAMI en provenance de FrameKit
		if (listener == null) {
			listener = new FramekitThreadListener(this, comLowServices);
			listener.start();
		}

		// Creation du thread associe a la session qui enverra les commandes CAMI
		FramekitThreadSpeaker speak = new FramekitThreadSpeaker(this, comLowServices);
		speak.start();

		// On detecte si une session est deja ouverte
		if (sessionOpened) {
			if (!this.suspendCurrentSession()) {
				this.closeConnexion();
			}
		}

		// Si la demande d'ouverture de session recoit une reponse positive :
		// - On ajoute le thread speaker dans une table
		// - La session est marquee comme ouverte
		if (speak.openSession(sessionName, date, sessionFormalism)) {
			listeThread.put(sessionName, speak);

			// Mise a jour des indicateurs
			this.sessionOpened = true;
			this.currentSessionName = sessionName;
			return true;

		// Sinon
		} else {
			// Suppression du thread speaker utilise
			speak = null;
			return false;
		}
	}

	/**
	 * Indique la fin d'une ouverture de session
	 * @return
	 */
	public void setEndOpenSession() {
		apiLog.fine("Fin de l'ouverture de session");
		com.setEndOpenSession();
	}

	/**
	 * Indique la fin de la fermeture de session (deconnexion)
	 */
	public void setEndCloseCurrentSession() {
		apiLog.fine("Fin de la fermeture de session");
		com.setEndCloseSession();
	}

	/**
	 * Indique la fin de service
	 */
	public void setEndService() {
		apiLog.fine("Fin du service");
		com.setEndService();
	}

	/**
	 * Permet de fermer la connexion entre l'interface utilisateur et la plate-forme.<br>
	 * La fermeture de la connexion implique la fermeture prealable de toutes les sessions<br>
	 * ATTENTION : La fermeture des sessions doit etre faite avant. (C'est un prerequis !)
	 */
	@SuppressWarnings("unused")
	public void closeConnexion() {
		// Une connexion doit etre disponible
		if (this.connexionOpened) {

			// Fabrication de la commande FC
			FKCommand cmd = new FKCommand();
			byte[] send = cmd.createCmdFC();

			try {
				comLowServices.writeCommande(send);
			} catch (CommunicationCloseException e) {
				apiLog.warning("Aucune connexion detectee : Deconnexion impossible");
			}

			// Fermeture des threads restants
			for (FramekitThreadSpeaker speaker : listeThread.values()) {
				speaker = null;
			}

			listeThread.clear();

			// Mise a jour des indicateurs
			apiLog.finer("Mise a jour des indicateurs : AUTH=NO  CONNECT=NO");
			this.connexionOpened = false;
			this.sessionOpened = false;

			// Fermeture des sockets de bas niveau
			apiLog.fine("Fermeture des socket bas-niveau");
			this.comLowServices.closeCom();

			// Fermeture de la thread listener
			listener = null;

			this.currentSessionName = null;
		} else {
			apiLog.warning("Aucune connexion detectee : Deconnexion impossible");
		}
	}

	/**
	 * Fermture de la connexion demandee par la plateforme.<br>
	 * Cette fermeture a lieu lors de la reception d'un FC ou d'un KO
	 * @param type Raison de la fermeture de la connexion
	 * @param message Message a afficher (transmis par la plate-forme)
	 * @param severity Gravite de l'incident (transmis par la plate-forme)
	 */
	@SuppressWarnings("unused")
	public void closeConnexion(int type, String message, int severity) {
		// Fermeture des threads restants
		apiLog.fine("Demande de deconnexion brutale de la plateforme (initiee par la plateforme");
		for (FramekitThreadSpeaker speaker : listeThread.values()) { speaker = null; }
		listeThread.clear();

		// Mise a jour de toutes les sessions
		this.com.closeAllSessions();

		// Fermeture de la thread listener
		listener = null;

		// Mise a jour des indicateurs
		this.sessionOpened = false;
		this.currentSessionName = null;

		// Fermeture des sockets bas-niveau
		if (severity > 1) {
			this.connexionOpened = false;
			this.comLowServices.closeCom();
		}
	}

	/**
	 * Suspension de la session courante
	 * @return resultat de l'operation
	 */
	public boolean suspendCurrentSession() {
		apiLog.finer("Suspension de la session");
		if (this.sessionOpened && this.currentSessionName != null) {

			// Suppression de la session courante
			this.currentSessionName = null;

			// On demande a FrameKit de suspendre la session
			boolean result = this.getCurrentSpeaker().sendSuspend();
			apiLog.finer("Session courante suspendue");
			return result;
		} else {
			apiLog.warning("Aucune session ouverte");
			return false;
		}

	}

	/**
	 * Reprend l'execution d'une session
	 * @param sessionName le nom de la session
	 * @return le resultat de l'operation
	 */
	public boolean resumeSession(String sessionName) {
		return false;
	}

	/**
	 * Ferme la session courante (fermeture de session)
	 * @return Le resultat de l'operation booleen
	 */
	public boolean closeCurrentSession() {
		apiLog.fine("Fermeture de la session courante");
		if (this.sessionOpened && this.currentSessionName != null) {

			// Si FrameKit accorde la fermeture de session
			if (this.getCurrentSpeaker().sendClose()) {
				apiLog.fine("FrameKit accorde la fermeture de session");
				listeThread.remove(this.currentSessionName);
				this.currentSessionName = null;
				if (listeThread.isEmpty()) {
					this.sessionOpened = false;
				}
				return true;
			} else {
				return false;
			}
		} else {
			apiLog.warning("Aucune session ouverte");
			return false;
		}
	}

	/**
	 * Permet de notifier a la plate-forme que le modele a ete modifie
	 * @param date Nouvelle date soumise
	 * @return TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	public boolean changeModeleDate(int date) {
		if (this.sessionOpened && this.currentSessionName != null) {
			FramekitThreadSpeaker speaker = this.getCurrentSpeaker();
			boolean result = speaker.sendNewDate(date);
			return result;
		} else {
			apiLog.warning("Aucune session ouverte");
			return false;
		}
	}

	/**
	 * Recupere l'etat du modele
	 * @return l'etat du modele (booleen)
	 */
	public boolean getDirtyState() {
		boolean status = this.com.getDirtyState();
		apiLog.finer("Demande de l'etat du modele => " + status);
		return status;
	}

	/**
	 * Recupere la date de derniere modification du modele
	 * @return la date de derniere modification
	 */
	public int getDateModel() {
		int date = this.com.getDateModel();
		apiLog.finer("Demande la date du modele => " + date);
		return date;
	}

	/**
	 * Permet de faire une demande de service a la plate-forme
	 *
	 * @param rootMenuName nom racine de l'arbre des menus
	 * @param parentName premier noeud de l'arbre des menus
	 * @param serviceName nom du service
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		// On sauvegarde le nom de service pour les resultats
		this.currentService = serviceName;

		if (this.sessionOpened && this.currentSessionName != null) {
			FramekitThreadSpeaker speaker = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			speaker.execService(rootMenuName, parentName, serviceName);
			apiLog.finer("Demande de service OK");
		} else {
			apiLog.warning("Demande de service KO");
		}
	}


	public void setTaskDescription(String service, String description) {
		apiLog.fine("Demande d'affichage de description pour la tache (" + service + " : " + description);
		com.setTaskDescription(service, description);
	}

	/**
	 * Envoie d'une reponse a la plate-forme
	 * @param results Ensemble des reponses de la boite de dialogue
	 * @return resultat de l'operation
	 * @see IDialogResult
	 */
	public boolean getDialogAnswers(IDialogResult results) {
		apiLog.fine("Transmission des resultats de la boite de dialogue a la plateforme");
		if (!this.sessionOpened || this.currentSessionName == null) {
			apiLog.warning("La session n'existe pas ou n'est pas connectee");
			return false;
		} else {
			FramekitThreadSpeaker speak;
			speak = (FramekitThreadSpeaker) listeThread.get(currentSessionName);
			if (!speak.sendDialogueResponse(results)) {
				apiLog.warning("Echec de la transmission des resultats");
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
	 * Affichage d'un message dans la console historique
	 * @param message Le message a afficher dans la vue History
	 */
	public void printHistory(String message) {
		apiLog.finer("Affichage dans l'historique (API)" + message);
		this.com.printHistoryMessage(message);
	}

	/**
	 * Affichage des menus
	 * @param menu Le menu a afficher
	 * @see IRootMenuCom
	 */
	public void drawMenu(IRootMenuCom menu) {
		apiLog.finer("Affichage des menus de services");
		this.com.drawMenu(menu);
	}

	/**
	 * Demande de mise a jour des menus
	 * @param updates L'ensemble des mises a jour a effectuer sur les menus
	 */
	public void updateMenu(Vector<IUpdateMenuCom> updates) {
		apiLog.finer("Mise a jour des menus de services");
		this.com.updateMenu(updates);
	}

	/**
	 * Demande l'affichage d'une boite de dialogue<br>
	 * Reminder : Les boites de dialogue sont construite sous l'autorite de la plate-forme
	 * @param dialog La boite de dialogue entierement definie
	 * @see IDialogCom
	 */
	public void drawDialog(IDialogCom dialog) {
		apiLog.finer("Affichage d'une boite de dialogue " + dialog.getId());
		this.com.drawDialog(dialog);
	}

	/**
	 * Cacher une boite de dialogue
	 * @param numDialog L'identite de la boite a masquer
	 */
	public void hideDialogUI(int numDialog) {
		apiLog.warning("hideDialogUI : Not available yet");
	}

	/**
	 * Supprimer une boite de dialogue
	 * @param numDialog L'identite de la boite a detruire
	 */
	public void destroyDialogUI(int numDialog) {
		apiLog.warning("destroyDialogUI : Not available yet");
	}

	/**
	 * Transmet un nouveau modele a creer<br>
	 * Dans certains cas, la plate-forme renvoie des modeles a afficher dans l'IHM.<br>
	 * La construction se fait donc du cote API et l'affichage du cote Coloane
	 * @param model Modele a creer du cote Coloane
	 * @see IModel
	 */
	public void setNewModel(IModel model) {
		if (model == null) { return; }
		apiLog.finer("Transmission d'un nouveau modele");
		this.com.setNewModel(model);
	}

	/**
	 * Traite les resultats d'un appel de services.<br>
	 * Un appel de service peut provoquer un certain dnombre de resultats a afficher.
	 * @param resultList  La liste des resultats renvoyes par la plate-forme
	 * @see ResultsCom
	 */
	public void setResults(IResultsCom result) {
		apiLog.finer("Transmission et affichage des resultats de service");
		this.com.setResults(this.currentService, result);
		this.com.printResults();
	}

	/**
	 * Demande du modele cote Coloane.<br>
	 * L'API peut avoir besoin de recuperer le modele en cours d'edition du cote Coloane.<br>
	 * Cette methode permet de transferer le modele depuis Coloane vers l'API de communication
	 * @return le modele courant
	 * @see IModel
	 */
	public IModel getModel() {
		apiLog.finer("Recuperation du modele edite par Coloane");
		return this.com.sendModel();
	}

	/**
	 * Retourne la thread qui permet d'envoyer des commandes pour la session courrante.<br>
	 * Chaque session est associee a un thread de communication.
	 * @return la thread liee a la session courrante
	 */
	public FramekitThreadSpeaker getCurrentSpeaker() {
		return (FramekitThreadSpeaker) listeThread.get(currentSessionName);
	}

	/**
	 * Initialisation du logger d'evenements
	 */
	private void initializeLogger() {
		apiLog = Logger.getLogger("fr.lip6.move.coloane.api");
		apiLog.setLevel(Level.FINEST); // On loggue tout !
		apiLog.addHandler(new ColoaneHandler());
		try {
			ColoaneLogHandler handler = ColoaneLogHandler.getInstance();
			ColoaneLogFormatter format = new ColoaneLogFormatter();
			format.setVersion("Api");
			handler.setFormatter(format);
			apiLog.addHandler(handler);
		} catch (IOException e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier");
		}
	}

	/**
	 * Retourne le gestionnaire de logs
	 * @return Le logger
	 */
	public static Logger getLogger() {
		return apiLog;
	}

	/**
	 * Modifie le niveau de verbosite du log de l'Api
	 * @param niveau le nouveau niveau du log
	 * */

	public static void setVerbosity(Level niveau) {
		apiLog.setLevel(niveau);
	}
}
