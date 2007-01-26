package fr.lip6.move.coloane.communications;


import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;
import fr.lip6.move.coloane.interfaces.IComUi;
import fr.lip6.move.coloane.interfaces.IComMotor;
import fr.lip6.move.coloane.interfaces.IMotorCom;
import fr.lip6.move.coloane.interfaces.IUiCom;
import fr.lip6.move.coloane.interfaces.models.IModel;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.ui.dialogs.Dialog;
import fr.lip6.move.coloane.ui.dialogs.DialogResult;

public class Com implements IComUi, IComApi, IComMotor {
	
	/** Une poignee sur l'API de communication avec la plateforme */
	private IApi api = null;
	
	/** Une poignee sur le moteur */
	private IMotorCom motor = null;
	
	/** Une poignee sur l'interface utilisateur */
	private IUiCom ui = null;
	
	/**
	 * Le constructeur
	 * Le module de communications doit creer un lien avec l'API de communications
	 */
	public Com() {
		this.api = new Api(this);
	}
	
	/**
	 * Permet de rattacher le moteur au module de communications
	 * @param motor
	 */
	public void setMotor(IMotorCom motor) {
		this.motor = motor;
	}
	
	/**
	 * Authentification de l'utilisateur
	 * @param login login
	 * @param pass mot de passe
	 * @param fkIp IP ou est la plateforme FrameKit
	 * @param fkPort Port ou joindre la plateforme FrameKit
	 * @return Booléen indiquant si l'authentification c'est bien passee
	 * @throws Exception exception
	 */
	public boolean authentication(String login, String pass, String ip, int port)  throws Exception {
		try {
			System.out.println("Demande d'authentification : ");
			System.out.println("  Login-> "+login);
			System.out.println("  Pass-> "+pass);
			System.out.println("  IP-> "+ip);
			System.out.println("  Port-> " +port);
			
			/* Connexion à la plateforme */
			boolean retour = api.openConnexion(login, pass, ip, port);
			if (retour) {
				System.out.println("Retour authentification OK");
			} else {
				System.out.println("Retour authentification KO");
			}
			return retour;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Connecte un modèle
	 * @param modele : IModelCol
	 * @return Booléen, si la connexion c'est bien passee
	 * @throws Exception
	 */
	public boolean connexion(IModel modele) throws Exception {
		if (modele == null) {
            throw new NullPointerException();
        }
		try {
			System.out.println("Demande de connexion du modele");

			String sessionName = motor.getSessionManager().getCurrentSession().getName();
			System.out.println("Nom de la session : "+sessionName);
			
			String formalismName = modele.getFormalism().getName();
			System.out.println("Nom du formalisme : "+formalismName);
			
			// TODO : La date est 0... Pourquoi ?
			Boolean retour = api.openSession(sessionName, motor.getSessionManager().getCurrentSession().getSessionModel().getDate(), formalismName);
			if (retour) {
				System.out.println("Connexion réussie !");
				return true;
			} else {
				System.err.println("Echec de la connexion !");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * Déconnecte un modèle
	 * @param modele IModelCol le modele a deconnecter
	 * @return boolean si la deconnexion c'est bien passee
     * @throws Exception exception
	 */
	public boolean deconnexion(IModel modele) throws Exception {
		if (modele == null) {
            throw new NullPointerException();
        }        
		try {
			System.out.println("Demande de déconnexion du modèle");

			String sessionName = motor.getSessionManager().getCurrentSession().getName();
			if (api.closeCurrentSession(sessionName)) {
				motor.getSessionManager().modelDeconnexion(sessionName);
                System.out.println("Deconnexion reussie!");
				return true;
			} else {
                System.err.println("Echec de la deconnexion!");
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Demande de service a la plateforme FrameKit
	 * @param rootMenuName Nom de la racine du menu
	 * @param parentName Nom du pere du service demande
	 * @param serviceName Nom du service demande (nom de la feuille cliquee)
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		this.api.askForService(rootMenuName, parentName, serviceName);
	}
	
	/**
	 * Affichage d'un message dans l'interface utilisateur
	 * @param message Message a afficher dans la console 
	 */
	public void printHistoryMessage(String message) {
		this.ui.printHistoryMessage(message);
	}
	
	/**
	 * Affichage d'un message dans l'interface utilisateur
	 * @param message Message a afficher dans la console 
	 */
	public void printStateMessage(String message) {
		this.ui.printStateMessage(message);
	}
	
	/** 
	 * Affichage des menus construit a partir des commandes CAMI 
	 * @param menu La racine du menu a afficher
	 */
	public void drawMenu(RootMenu menu) {
		this.ui.drawMenu(menu);
	}
	
	/** 
	 * Affichage des menus construit a partir des commandes CAMI 
	 * @param menu La racine du menu a afficher
	 */
	public void updateMenu() {
		this.ui.updateMenu();
	}
	
	public void drawDialog(Dialog dialog) {
		this.ui.drawDialog(dialog);
	}
	
	public void getDialogAnswers(DialogResult results) {
		if (!this.api.getDialogAnswers(results)) {
			System.err.println("La transmission des reponses de la boite de dialogue a echouee");
		}
	}
	
	/**
	 * Creer une attache avec l'interface utilisateur
	 * @param IUiCom L'interface de l'interface utilisateur pour le module de communications
	 */
	public void setUi (IUiCom ui) {
		this.ui = ui;
	}
	
	/**
	 * Informe FK que le modele a ete mis a jour
	 * @param dateUpdate La date de derniere mise a jour du modele
	 */
	public void toUpdate (int dateUpdate) {
		this.api.changeModeleDate(dateUpdate);
	}
	
	/**
	 * Recupere le modele
	 * @return Le modele en cours
	 */
	public Model getModel() {
		return this.motor.getSessionManager().getCurrentSession().getSessionModel().getModel();
	}
	
	/**
	 * Permet de noter le modele comme sale ou propre apres communication avec la plate-forme
	 * @param boolean true indique que le modele a ete sali et doit donc etre mis a jour
	 */
	public void setModelDirty(boolean state) {
		this.motor.getSessionManager().getCurrentSession().getSessionModel().setDirty(state);
	}
	
	/**
	 * Retourne l'etat de fraicheur actuel du modele
	 * @return boolean Indicateur de fraicheur
	 */
	public boolean getDirtyState() {
		return this.motor.getSessionManager().getCurrentSession().getSessionModel().isDirty();
	}
	
	/** Retourne la date de derniere modification du modele
	 * @return int Date
	 */
	public int getDateModel() {
		return this.motor.getSessionManager().getCurrentSession().getSessionModel().getDate();
	}
}
