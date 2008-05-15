package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationInformation;

/**
 * Toutes les methodes qui doivent etre implementees par le MOTOR pour l'UI
 */
public interface IMotorUi {

	/**
	 * Retourne le gestionnaire de session
	 * @return Le gestionnaire de session
	 */
	SessionManager getSessionManager();

	/**
	 * Procedure d'authentification (demande a la plateforme)
	 * @param authInformation La structure contenant les informations necessaires a l'authentification
	 */
	void authentication(final AuthenticationInformation authInformation);

	/**
	 * Demande d'un service a la plateforme
	 * @param rootMenuName Le menu racine
	 * @param referenceName Le menu de reference (qui n'est pas forcement le parent)
	 * @param serviceName Le service a executer
	 */
	void askForService(String rootMenuName, String referenceName, String serviceName);

}
