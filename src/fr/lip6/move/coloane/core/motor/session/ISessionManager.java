package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.beans.PropertyChangeListener;
import java.util.Collection;

/**
 * Gestionnaire de session, gère également l'état de la connection avec l'api.
 */
public interface ISessionManager {

	/** Propriété pour le changement de la session courrante */
	String PROP_CURRENT_SESSION = "SessionManager.currentSession"; //$NON-NLS-1$

	/** Propriété pour le changement d'état de l'authentification */
	String PROP_AUTHENTICATION = "SessionManager.authentication"; //$NON-NLS-1$

	/**
	 * Ajoute une session au manager<br>
	 * Si aucune session est courante... Celle la devient la session courante.</br>
	 * Le nom de la session ne doit pas etre null ou vide
	 * @param name Le nom de la nouvelle session
	 * @return La nouvelle session ou NULL si le nom est incorrect ou la session si elle existe deja
	 */
	ISession newSession(String name);

	/**
	 * Retourne la session courante ou NULL si aucune session n'est active
	 * @return La session courante ou NULL
	 */
	ISession getCurrentSession();

	/**
	 * Retourne la session dont le nom est indique en parametre
	 * @param sessionName nom de la session
	 * @return la session designe ou <code>null</code> si on ne trouve pas la session
	 */
	ISession getSession(String sessionName);

	/**
	 * @param graph IGraph
	 * @return la session contenant ce graphe ou <code>null</code> si aucune session ne correspond.
	 */
	ISession getSession(IGraph graph);

	/**
	 * @return Liste de toutes les sessions
	 */
	Collection<ISession> getSessions();

	/**
	 * Retourne le status d'authentification du client<br>
	 * L'authentification est valable pour tous les modeles
	 * @return Un booleen
	 */
	boolean isAuthenticated();

	/**
	 * Positionne le status d'authentification du client
	 * @param authStatus Le nouveau status du client
	 */
	void setAuthenticated(boolean authStatus);

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
}
