package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IModel;

public interface IMotorCom {

	/**
	 * Retourne le gestionnaire de session
	 * @return Le gestionnaire de session
	 */
	SessionManager getSessionManager();

	/**
	 * Construction d'un nouveau modele en provenance de la plateforme
	 * @param model Le modele generique envoye par la plateforme
	 */
	void setNewModel(IModel model);

	/**
	 * Indication de la fin du service en cours
	 */
	void endService();

	/**
	 * Affichage des informations concernant la tache en cours
	 * @param service Le service en cours d'execution
	 * @param description La description a afficher du cote client
	 */
	void setTaskDescription(String service, String description);

}
