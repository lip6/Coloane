package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.interfaces.objects.IDialogCom;

/**
 * L'interface IAPI represente l'ensemble des methodes qui doivent etre
 * offertes par une API au module de communication de Coloane.
 * Toutes les methodes (sans exceptions) doivent etre correctement implementees.
 *
 * D'autres interfaces sont disponibles dans le packages interfaces
 */
public interface IApi {

	/** Definition des niveaux de trace pour le lancement de l'API */

	/** Niveau utilisateur */
	int NORMAL = 2;

	/** *Niveau pour les developpeurs */
	int BETA   = 1;

	/** Niveau pour le deboguage */
	int DEBUG  = 0;

	/**
	 * Permet de se connecter a une plate-forme (FrameKit par exemple)
	 * Toutes les informations sont fournies par Coloane.
	 * Y compris pour le nom et la version de l'API qui correspondent a des donnees propre a Coloane
	 *
	 * @param login le login de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 * @param ip de la machine hebergeant la plateforme FrameKit
	 * @param port ou contacter FrameKit
	 * @param apiName Le nom de l'API
	 * @param apiVersion La version de l'API
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	boolean openConnexion(String login, String password, String ip, int port, String apiName, String apiVersion);

	/**
	 * Permet de fermer la connexion entre l'interface utilisateur et la plateforme.
	 * Cette methode est appelee par le module de communication lors d'un probleme grave.
	 * Elle doit garantir que tous les environnements de communication (y compris bas niveau sont fermes)
	 * En general, cette methode est suivi de la fermeture de l'application.
	 */
	void closeConnexion();

	/**
	 * Permet d'ouvrir une session (une session est associee a un modele)
	 *
	 * @param sessionName est le nom du modele
	 * @param date est la date de creation de la session
	 * @param sessionFormalism est le nom du formalism auquel est attacher le modele
	 * @return retourne TRUE si la session est ouverte et FALSE dans le cas contraire
	 */
	boolean openSession(String sessionName, int date, String sessionFormalism);

	/**
	 * Permet de suspendre la session courante.
	 * Lors d'un changement de modele du cote de Coloane, un changement de session est
	 * necessaire du cote de la plate-forme.
	 *
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	boolean suspendCurrentSession();

	/**
	 * Permet de reprendre l'execution d'une session (precedemment suspendue)
	 *
	 * @param sessionName le nom de la session
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	boolean resumeSession(String sessionName);

	/**
	 * Permet de supprimer la session courante.
	 * La plate-forme doit etre avertie de la fermeture de la session courante
	 *
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	boolean closeCurrentSession();

	/**
	 * Permet de notifier a la plate-forme que le modele a ete modifie.
	 * La palteforme maintient les modeles a jour dans son repository.
	 * Lors d'une modification sur un modele ouvert et connecte, une modification
	 * implique que la plate-forme doit etre tenue au courant pour demander le nouveau
	 * modele. (C'est toujours la plate-forme qui demande la modele)
	 *
	 * @param date nouvelle date soumise
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	boolean changeModeleDate(int date);

	/**
	 * Permet de faire une demande de service a la plate-forme.
	 * Une demande de service ne renvoie rien de particulier.
	 * Les resultats sont transmis sous d'autres forme a Coloane.
	 *
	 * @param rootMenuName nom racine de l'arbre des menus
	 * @param menuName premier noeud de l'arbre des menus
	 * @param serviceName nom du service
	 */
	void askForService(String rootMenuName, String menuName, String serviceName);

	/**
	 * Permet de demander l'arret d'un service. (precedemment demande)
	 *
	 * @param serviceName le nom du service a arrete
	 * @return TRUE si l'arret du service est effectif
	 */
	boolean stopService(String serviceName);

	/**
	 * Retourne la date de derniere modification du modele.
	 * Cette date peut etre stockee dans l'API ou demande au module de communication
	 *
	 * @return la date sous la forme d'un entier
	 */
	int getDateModel();

	/**
	 * Demande l'affichage d'une boite de dialogue.
	 * C'est la plate-forme qui construit et demande l'affichage des boites de dialogue.
	 *
	 * @param dialog Objet contenant les informations pour afficher une boite de dialogue
	 * @see IDialogCom
	 */
	void drawDialog(IDialogCom dialog);

	/**
	 * Demande la transmission de l'objet resultat de boite de dialogue.
	 * Les resultats d'une boite de dialogue sont fournis sous la forme d'un objet self-content.
	 *
	 * @param results L'bojet contenant tous les resultats
	 * @return TRUE si la reponse a bien ue lieue, FALSE en cas d'echec
	 */
	boolean getDialogAnswers(IDialogResult results);
}
