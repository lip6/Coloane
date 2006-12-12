package fr.lip6.move.coloane.communications;


import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;
import fr.lip6.move.coloane.interfaces.IComIhm;
import fr.lip6.move.coloane.interfaces.IComMotor;
import fr.lip6.move.coloane.interfaces.IMotorCom;
import fr.lip6.move.coloane.interfaces.models.IModel;
import fr.lip6.move.coloane.motor.Motor;

public class Com implements IComIhm, IComApi, IComMotor {
	
	/** Une poignee sur l'API de communication avec la plateforme */
	private IApi api = null;
	
	/** Une poignee sur le moteur */
	private IMotorCom motor = null;
	
	/**
	 * Le constructeur
	 */
	public Com() {
		this.api = new Api();
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
			System.out.println("Demande de connexion du modèle");

			// TODO : La date est 0... Pourquoi ?
			String sessionName = motor.getSessionManager().getSession().getName();
			System.out.println("Nom de la session : "+sessionName);
			
			String formalismName = modele.getFormalism().getName();
			System.out.println("Nom du formalisme : "+formalismName);
			
			Boolean retour = api.openSession(sessionName, 0, formalismName);
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

			String sessionName = motor.getSessionManager().getSession().getName();
			if (api.closeCurrentSession(sessionName)) {
				motor.modelDeconnexion(sessionName);
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
	
	
	
	
	
	
}
