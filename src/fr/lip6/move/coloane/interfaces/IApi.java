package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.communications.objects.Dialogue;

public interface IApi {
	
	/**
	 * Permet de se connecter a une plate-forme
	 * @param login le login de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 * @param ip de la machine hebergeant la plateforme FrameKit
	 * @param port ou contacter FrameKit
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire 
	 * @throws  
	 */
	public boolean openConnexion(String login, String password, String FKIp, int FKPort);
	
	/**
	 * Permet de fermer la connexion entre l'interface utilisateur et la plateforme
	 *
	 */
	public void closeConnexion(); 
	
	/**
	 * Permet d'ouvrir une session (une session est associee a un modele)
	 * @param sessionName est le nom du modele
	 * @param date est la date de creation de la session
	 * @param sessionFormalism est le nom du formalism auquel est attacher le modele
	 * @return retourne TRUE si la session est ouverte et FALSE dans le cas contraire 
	 */
	public boolean openSession(String sessionName, int date, String sessionFormalism);
		
	/**
	 * Permet de suspendre la session courante
	 * @param sessionName est le nom de la session
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire 
	 */
	public boolean suspendCurrentSession(String sessionName);
	
	/**
	 * Permet de reprendre l'execution d'une session
	 * @param sessionName le nom de la session
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire 
	 */
	public boolean resumeSession(String sessionName);
	
	/**
	 * Permet de supprimer une session
	 * @param sessionName nom de la session
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	public boolean closeCurrentSession(String sessionName);
	
	/**
	 * Permet de notifier a la plate-forme que le modele a ete modifie
	 * @param date nouvelle date soumise
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	public boolean changeModeleDate(int date);
	
	/**
	 * Permet de faire une demande de service a la plate-forme
	 * @param rootMenuName nom racine de l'arbre des menus
	 * @param menuName premier noeud de l'arbre des menus
	 * @param serviceName nom du service
	 * @param checkMarkList liste des services actifs ou non
	 */
	public void askForService(String rootMenuName, String menuName, String serviceName, String [] checkMarkList);
	
	/**
	 * Permet de repondre a la plate-forme 
	 * @param response est le dialogue de reponse
	 * @return retourne TRUE si ca c'est bien passe et FALSE dans le cas contraire
	 */
	public boolean sendDialogueResponse(Dialogue response);

	/**
	 * Permet de demander l'arret d'un service
	 * @param serviceName le nom du service a arrete
	 * @return TRUE si l'arret du service est effectif
	 */
	public boolean stopService(String serviceName);

}
