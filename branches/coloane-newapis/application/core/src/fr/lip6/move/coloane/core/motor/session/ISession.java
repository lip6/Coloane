package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.MenuManager;

/**
 * Une session est attaché à chaque éditeur et gère l'ApiSession qui communique avec Framekit.
 */
/**
 * @author clement
 *
 */
public interface ISession {

	/** Les indicateurs de statuts */
	int ERROR = -1;
	int CLOSED = 0;
	int CONNECTED = 1;
	int SUSPENDED = 2;

	/**
	 * Connecte la session à framekit.
	 * @return <code>true</code> si la connexion est ouverte
	 */
	boolean connect();

	/**
	 * Déconnecte la session de framekit.
	 * @return <code>true</code> si la connexion est fermé
	 */
	boolean disconnect();

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
	 * @return nom de la session
	 */
	String getName();

	/**
	 * Retoune le modele
	 * @return IGraph Le modele de la session
	 */
	IGraph getGraph();

	/**
	 * Positionne le modele
	 * @param graph nouveau modele
	 */
	void setModel(IGraph graph);

	/**
	 * Retourne le menu d'administration
	 * @return la racine du menu d'administration
	 */
	MenuManager getAdminMenu();

	/**
	 * Indique le menu d'administration attache a la session
	 * @param admin La racine du menu d'administration
	 */
	void setAdminMenu(MenuManager admin);

	/**
	 * Retourne le menu de service de la session
	 * @return la racine du menu de services
	 */
	List<MenuManager> getServicesMenu();

	/**
	 * Permet d'ajouter un menu à la liste des menus associés à cette session
	 * @param menu racine du menu à ajouter
	 */
	void addServicesMenu(MenuManager menu);

	/**
	 * Vide le menu pour cette session.
	 */
	void clearServicesMenu();

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
	 * @param status Le status courant de la session
	 */
	void setStatus(int status);

	/**
	 * Ajout de tous les services passé en paramètre
	 * @param services Collection de services
	 */
	void addAllServices(Collection<IService> services);

	/**
	 * @return liste des services disponibles
	 */
	Collection<IService> getServices();

	/**
	 * @param id id du service
	 * @return IService correspondant ou <code>null</code>
	 */
	IService getService(String id);

	/**
	 * @param option nom de l'option
	 * @param state état de l'option
	 */
	void setOption(String option, boolean state);

	/**
	 * @return la liste des options actives
	 */
	List<String> getActiveOptions();

	/**
	 * @param service service à executer
	 */
	void askForService(IService service);

	/**
	 * Previens l'api d'un changement majeur du modèle
	 */
	void invalidModel();
}
