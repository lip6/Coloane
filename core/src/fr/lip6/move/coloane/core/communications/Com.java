package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.core.interfaces.IComMotor;
import fr.lip6.move.coloane.core.interfaces.IComUi;
import fr.lip6.move.coloane.core.interfaces.IMotorCom;
import fr.lip6.move.coloane.core.interfaces.IUiCom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.menus.RootMenu;
import fr.lip6.move.coloane.core.ui.dialogs.DrawDialog;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;
import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;

public final class Com implements IComUi, IComApi, IComMotor {

	/** Une poignee sur l'API de communication avec la plateforme */
	private IApi api = null;

	/** Une poignee sur le moteur */
	private IMotorCom motor = null;

	/** Une poignee sur l'interface utilisateur */
	private IUiCom ui = null;

	/** Le menu en cours de construction */
	private RootMenu root = null;

	/** L'ensemble de modifications qui doivent etre faites sur les menus */
	private Vector<IUpdateMenuCom> updates = null;

	/** Conteneur graphique de haut niveau */
	private Composite parent;

	/** L'instance du singleton : Com */
	private static Com instance;

	/** Indicateur d'authentification */
	private static boolean isAuthenticated = false;

	/**
	 * Le constructeur en private
	 * Le module de communications doit creer un lien avec l'API de communications
	 * Pour eviter les doublonson utilise le pattern <b>Singleton</b>
	 * @see #getInstance()
	 */
	private Com() {
		this.api = Api.getInstance(this);
		this.parent = (Composite) Coloane.getParent();
	}

	/**
	 * Retourne le module de communications
	 * @return Com Le module de communications
	 */
	public static synchronized Com getInstance() {
		if (instance == null) { instance = new Com(); }
		return instance;
	}

	/**
	 * Permet de rattacher le moteur au module de communications
	 * @param theMotor Le module moteur
	 */
	public void setMotor(IMotorCom theMotor) {
		this.motor = theMotor;
	}

	/**
	 * Permet d'attacher l'interface utilisateur au module de communications
	 * @param theUi L'interface utilisateur
	 */
	public void setUi(IUiCom theUi) {
		this.ui = theUi;
	}

	/**
	 * Authentification de l'utilisateur
	 * @param login login
	 * @param pass mot de passe
	 * @param ip IP ou est la plateforme FrameKit
	 * @param port Port ou joindre la plateforme FrameKit
	 * @return boolean indiquant si l'authentification c'est bien passee
	 */
	public boolean authentication(String login, String pass, String ip, int port) {
		Coloane.getLogger().fine("Demande d'authentification");

		// Connexion a la plateforme
		boolean retour = this.api.openConnection(login, pass, ip, port, Coloane.getParam("API_NAME"), Coloane.getParam("API_VERSION")); //$NON-NLS-1$ //$NON-NLS-2$
		// Log du resultat
		if (retour) { Coloane.getLogger().fine("Authentification OK"); } else { Coloane.getLogger().warning("Authentification KO"); } //$NON-NLS-1$ //$NON-NLS-2$
		isAuthenticated = retour;
		return retour;
	}

	/**
	 * Connecte un modele a la plate-forme
	 * @param model Le modelea connecter a la plateforme
	 * @return boolean Selon le resultat de la connexion
	 * @throws Exception
	 */
	public boolean openSession(IModelImpl model) {
		Coloane.getLogger().fine("Connexion d'un modele"); //$NON-NLS-1$

		// Si le modele est nul on ne peut rien faire
		if (model == null) {
			Coloane.getLogger().warning("Aucun modele a connecter"); //$NON-NLS-1$
			return false;
		}
		// Recuperation du nom de la session courante
		String sessionName = motor.getSessionManager().getCurrentSessionName();
		// Recuperation du nom du formalime de la session courante
		String formalismName = model.getFormalism().getName();

		// Demande de l'ouverture de session a l'API
		Boolean retour = api.openSession(sessionName, model.getDate(), formalismName);
		// Log du resultat
		if (retour) { Coloane.getLogger().fine("Connexion reussie !"); } else { Coloane.getLogger().warning("Echec de la connexion"); } //$NON-NLS-1$ //$NON-NLS-2$

		// Au commencement, un modele est toujours propre
		model.setDirty(false);

		return retour;
	}

	/**
	 * Deconnecte un modele (Demande en provenance de Coloane)
	 * @param modele Le modele a deconnecter
	 * @return boolean Si la deconnexion c'est bien passee
	 * @throws Exception exception
	 */
	public boolean closeSession() {
		// On verifie qu'il y a bien une sesssion courante
		if (motor.getSessionManager().getCurrentSession() == null) { return false; }

		// On sauvegarde les noms des menus a supprimer
		RootMenu menuServiceName = motor.getSessionManager().getCurrentSession().getServicesMenu();
		RootMenu menuAdminName = motor.getSessionManager().getCurrentSession().getAdminMenu();

		// Si la deconnexion du cote API se passe bien
		if (api.closeCurrentSession()) {

			// Suppression du menu de services
			if (menuServiceName != null) { this.ui.removeMenu(menuServiceName.getName()); }
			// Suppression du menu d'administration
			if (menuAdminName != null) { this.ui.removeMenu(menuAdminName.getName()); }

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Deconnexion brutale de tous les modeles (demande de FrameKit)<br>
	 * Cette deconnexion est provoquee par un KO ou un FC
	 */
	public void closeAllSessions() {
		motor.getSessionManager().destroyAllSessions();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IComUi#askForService(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		Coloane.getLogger().fine("Demande de service : " + serviceName); //$NON-NLS-1$
		// Grisage du menu de services
		this.ui.changeMenuStatus(rootMenuName, false);
		// Requete a l'API
		this.api.askForService(rootMenuName, parentName, serviceName);
		// Au retour d'un service, le modele est toujours propre
		this.motor.getSessionManager().getCurrentSessionModel().setDirty(false);
	}

	/**
	 * Affichage d'un message dans l'interface utilisateur (Vue History)
	 * @param message Message a afficher dans la console
	 */
	public void printHistoryMessage(String message) {
		Coloane.getLogger().finest("Affichage dans l'historique : " + message); //$NON-NLS-1$
		this.ui.printHistoryMessage(message);
	}

	/**
	 * Affichage des menus construit a partir des commandes CAMI
	 * @param menu La racine du menu a afficher
	 */
	public void drawMenu(IRootMenuCom rootMenuCom) {
		Coloane.getLogger().fine("Affichage des menus"); //$NON-NLS-1$

		try {
			// Transformation des menus
			root = new RootMenu(rootMenuCom.getRootMenuName());
			for (IMenuCom menuCom : rootMenuCom.getListMenu()) {
				root.addMenu(menuCom.getServiceName(), menuCom.getFatherName(), menuCom.isEnabled());
			}

			// Demande d'affichage a l'UI
			parent.getDisplay().asyncExec(new Runnable() {
				public void run() { ui.drawMenu(root); }
			});
		} catch (Exception e) {
			Coloane.getLogger().warning("Unable to build the model"); //$NON-NLS-1$
		}

	}

	/**
	 * Affichage des menus construit a partir des commandes CAMI
	 * @param updates La racine du menu a afficher
	 */
	public void updateMenu(Vector<IUpdateMenuCom> updatesMenu) {
		Coloane.getLogger().fine("Mise a jour des menus");
		this.updates = updatesMenu;
		parent.getDisplay().asyncExec(new Runnable() {
			public void run() { ui.updateMenu(updates); }
		});
	}

	/**
	 * Affichage d'une boite de dialogue
	 * @param dialogCom La boite de dialogue entierement definie
	 */
	public void drawDialog(IDialogCom dialog) {
		/* Affichage de la boite de dialogue dans une thread dediee */
		parent.getDisplay().asyncExec(new DrawDialog(dialog));
	}

	/**
	 * Recupere les informations de la boite de dialogue
	 * @results Les resultats sous forme d'objets
	 */
	public void sendDialogAnswers(IDialogResult results) {
		this.api.getDialogAnswers((IDialogResult) results);
	}

	/**
	 * Affichage des resultats d'un service
	 * @param serviceName Le nom du service auquel sont rattaches les resultats
	 * @param result L'objet contenant tous les resultats
	 */
	public void setResults(String serviceName, IResultsCom resultsCom) {
		Coloane.getLogger().fine("Preparation des resultats pour le service : " + serviceName);
		if ((serviceName != "") && (resultsCom != null)) { //$NON-NLS-1$
			this.ui.setResults(serviceName, resultsCom);
		} else {
			this.ui.setResults(serviceName, null);
			this.ui.printResults();
		}
	}

	/**
	 * Affichage des resultats transmis par l'API
	 * Cette methode doit etre appelee apres la methode setResults
	 */
	public void printResults() {
		Coloane.getLogger().fine("Affichage des resultats");
		this.ui.printResults();
	}

	/**
	 * Afichage d'un message de FrameKit
	 * @param type Le type de message
	 * @param text Le texte du message
	 * @param specialType Un indicateur
	 */
	public void setUiMessage(int type, String text, int specialType) {
		Coloane.showWarningMsg(text);
	}


	/**
	 * Recupere le modele pour le transmettre a l'API
	 * @return IModel Le modele en cours
	 * @see IModel
	 */
	public IModel sendModel() {
		Coloane.getLogger().fine("Transmission d'un modele a la plateforme");
		return this.motor.getSessionManager().getCurrentSessionModel().getGenericModel();
	}


	/**
	 * Demande la creation d'un nouveau modele a partir des inputs de FK<br>
	 * En general, l'API est responsable de cet appel !
	 * @param Le modele construit par l'api de communication
	 */
	public void setNewModel(IModel model) {
		Coloane.getLogger().fine("Reception d'un nouveau modele");
		this.motor.setNewModel(model);
	}


	/**
	 * Informe FK que le modele a ete mis a jour
	 * @param dateUpdate La date de derniere mise a jour du modele
	 */
	public void toUpdate(int dateUpdate) {
		Coloane.getLogger().fine("Le modele doit etre mis a jour du cote de la plateforme");
		this.api.changeModeleDate(dateUpdate);
	}

	/**
	 * Retourne l'etat de fraicheur actuel du modele
	 * @return boolean Indicateur de fraicheur
	 */
	public boolean getDirtyState() {
		boolean state = this.motor.getSessionManager().getCurrentSessionModel().isDirty();
		if (state) { Coloane.getLogger().fine("Le modele est actuellement SALE"); } else { Coloane.getLogger().fine("Le modele est actuellement PROPRE"); }
		return this.motor.getSessionManager().getCurrentSessionModel().isDirty();
	}

	/**
	 * Retourne la date de derniere modification du modele
	 * @return int Date
	 */
	public int getDateModel() {
		return this.motor.getSessionManager().getCurrentSessionModel().getDate();
	}

	/**
	 * Donne l'etat d'authentification
	 * @return un booleen
	 */
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
}
