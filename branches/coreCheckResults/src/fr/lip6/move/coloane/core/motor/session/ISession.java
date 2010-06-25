package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.MenuManager;

/**
 * Une session est attaché à chaque éditeur et gère l'ApiSession qui communique avec Framekit.
 */
public interface ISession {

	/** Propriété pour les changements d'état de la connection */
	String PROP_CONNECTION = "Session.connection"; //$NON-NLS-1$

	/** Propriété pour l'ajout ou la suppression de tips */
	String PROP_TIPS = "Session.tips"; //$NON-NLS-1$

	/** Les indicateurs de statuts */
	int CLOSED = 0;
	int CONNECTED = 1;

	/**
	 * @return nom de la session
	 */
	String getSessionId();

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
	 * @param option option
	 * @param state état de l'option
	 */
	void setOption(IOptionMenu option, boolean state);

	/**
	 * @param path restreint aux options ce trouvant dans la descendance de ce chemin
	 * @return la liste des options actives
	 */
	List<IOptionMenu> getActiveOptions(String path);

	/**
	 * @param listener listener à ajouter
	 * @see PropertyChangeSupport
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @param listener listener à enlever
	 * @see PropertyChangeSupport
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @param tips liste de tips à afficher
	 */
	void addAllTips(Collection<ICoreTip> tips);

	/**
	 * @param tips liste de tips à enlever
	 */
	void removeAllTips(Collection<ICoreTip> tips);

	/**
	 * @return liste des tips qui doivent être affiché
	 */
	Collection<ICoreTip> getTips();

	/**
	 * @param id id d'un IElement
	 * @return la liste des tips correspondant à cette id
	 */
	Collection<ICoreTip> getTip(int id);
}
