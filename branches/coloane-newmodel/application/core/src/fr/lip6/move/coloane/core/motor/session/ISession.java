package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.menus.RootMenu;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public interface ISession {

	/** Les indicateurs de statuts */
	int ERROR = -1;
	int CLOSED = 0;
	int CONNECTED = 1;
	int SUSPENDED = 2;

	/**
	 * Suspension de la session
	 */
	void suspend();

	/**
	 * Reprise de la session
	 */
	void resume();

	/**
	 * Destruction de la session :
	 * <ul>
	 * 	<li>Suppression des menus admin / services</li>
	 *  <li>Etat de la session positionne a CLOSED</li>
	 * </ul>
	 */
	void destroy();

	/**
	 * Retoune le nom de la session
	 * @return name
	 */
	String getName();

	/**
	 * Retoune le modele
	 * @return IModelImpl Le modele de la session
	 */
	IGraph getGraph();

	/**
	 * Positionne le modele
	 * @param model nouveau modele
	 */
	void setModel(IGraph graph);

	/**
	 * Retourne le menu d'administration
	 * @return la racine du menu d'administration
	 */
	RootMenu getAdminMenu();

	/**
	 * Indique le menu d'administration attache a la session
	 * @param adminMenu La racinde du menu d'administration
	 */
	void setAdminMenu(RootMenu admin);

	/**
	 * Retourne le menu de service de la session
	 * @return la racine du menu de services
	 */
	RootMenu getServicesMenu();

	/**
	 * Indique le menu de services attache a la session
	 * @param sessionMenu la racine du menu de services
	 */
	void setServicesMenu(RootMenu root);

	/**
	 * Retourne la liste de resultats associee a la session
	 * @return La liste de resultats a afficher dans la vue adequate
	 */
	ResultTreeList getServiceResults();

	/**
	 * Retourne le status courant de la session
	 * @return le status courant de la session
	 */
	int getStatus();

	/**
	 * Modifie le status courant de la session
	 * @param sessionStatus Le status courant de la session
	 */
	void setStatus(int status);
}
