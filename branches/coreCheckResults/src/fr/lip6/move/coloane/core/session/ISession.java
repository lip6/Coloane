package fr.lip6.move.coloane.core.session;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.ResultManager;
import fr.lip6.move.coloane.core.results.Tip;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.MenuManager;

/**
 * Describe a session
 */
public interface ISession {

	/** Event raised when som tips are added/removed or updates */
	String PROP_TIPS = "Session.tips"; //$NON-NLS-1$

	/**
	 * @return The session name
	 */
	String getSessionId();

	/**
	 * @return IGraph The graph associated to the session
	 */
	IGraph getGraph();

	/**
	 * @return The list of menus (services) associated with this session
	 */
	List<MenuManager> getServicesMenu();

	/**
	 * Add a menu (service) to this session
	 * @param menu The menu to add to the session
	 */
	void addServicesMenu(MenuManager menu);

	/**
	 * Clear the menu list for this session
	 */
	void clearServicesMenu();

	/**
	 * @return The result manager associated with this session
	 */
	ResultManager getResultManager();

	/**
	 * Add a list of tips to the session.
	 * @param tips List of tips to add to the session
	 * @see Tip
	 */
	void addAllTips(Collection<ICoreTip> tips);

	/**
	 * Remove all tips from the sessions
	 * @param tips List of tips to remove from the session
	 */
	void removeTips(Collection<ICoreTip> tips);

	/**
	 * @return List of tips
	 */
	Collection<ICoreTip> getTips();

	/**
	 * Return the list of tips associated with an object of the current session graph
	 * @param id The ID of the object for which the list of tips should be retrieved
	 * @return A list of tips
	 */
	Collection<ICoreTip> getTipForObject(int id);
	
	/**
	 * Add a listener to be waware of session changes
	 * @param listener The listener to add to the session
	 * @see PropertyChangeSupport
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a listener from the list
	 * @param listener The listener to remove
	 * @see PropertyChangeSupport
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);


}
