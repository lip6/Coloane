package fr.lip6.move.coloane.core.session;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.ApiDescription;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.List;

/**
 * Session Manager
 */
public interface ISessionManager {

	/** Event raised when the current session has changed */
	String PROP_CURRENT_SESSION = "SessionManager.currentSession"; //$NON-NLS-1$

	/**
	 * Create a new session and add it to the manager.<br>
	 * If no session is currently active, this new one becomes the <i>current session</i>.
	 * The session name must not be <code>null</code> or be empty.
	 * @param sessionId The name of the new session
	 * @param graph The graph associated with the new session
	 * @return The new session
	 * @throws ColoaneException If something went wrong during the creation process
	 */
	ISession createSession(String sessionId, IGraph graph) throws ColoaneException;
	
	/**
	 * Resume the designated session.<br>
	 * This session is new the current one.
	 * @param sessionId The name of the session to resume
	 * @return The session that has been resumed or <code>null</code> if something went wring during the process
	 */
	ISession resumeSession(String sessionId);
	
	/**
	 * Remove session from the session manager.<br>
	 * Usually, this operation is called when the user close an editor.
	 * @param sessionId The name of the session to remove from the session manager
	 * @return The session that has been removed from the session manager or <code>null</code> is something went wrong during the process 
	 */
	ISession destroySession(String sessionId);

	/**
	 * @return The current session or <code>null</code> is no session is the current one
	 */
	ISession getCurrentSession();

	/**
	 * Return a session registered in the session manager according to its <b>name</b>
	 * @param sessionName The session name
	 * @return The desired session or <code>null</code> if no session has been found
	 */
	ISession getSession(String sessionName);

	/**
	 * Return a session registered in the session manager according to the <b>graph</b> it owns
	 * @param graph IGraph
	 * @return The desired session or <code>null</code> if no session has been found
	 */
	ISession getSession(IGraph graph);

	/**
	 * @return The list of all registered session
	 */
	Collection<ISession> getSessions();
	
	/**
	 * @return The list of available <b>global</b> APIs
	 */
	List<ApiDescription> getAvailableGlobalApis();

	/**
	 * Add a listener about session changes.
	 * @param listener The listener to add to the list
	 * @see PropertyChangeSupport
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a listener
	 * @param listener The listener to remove
	 * @see PropertyChangeSupport
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);
}
