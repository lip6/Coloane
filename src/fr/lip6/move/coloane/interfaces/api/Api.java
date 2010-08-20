package fr.lip6.move.coloane.interfaces.api;

import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public abstract class Api implements IApi {

	/** The logger */
	protected static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** A list of observers to notify */
	private Map<Integer, List<IApiObserver>> observers = new HashMap<Integer, List<IApiObserver>>();
	
	/** A list of menu items that have to be displayed by Coloane */
	protected List<IItemMenu> menus = new ArrayList<IItemMenu>();

	/** Authentication status */
	private boolean authentication;
	
	/** Authentication data */
	private Map<String,String> credentials = new HashMap<String, String>();

	/**
	 * Constructor.<br>
	 * By default, the authentication status is set to <code>false</code>. 
	 */
	public Api() {
		this.authentication = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<IItemMenu> getInitialApiMenus() {
		return this.menus;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addObserver(IApiObserver observer, int observerType) {
		List<IApiObserver> typedObservers = this.observers.get(Integer.valueOf(observerType));
		// Create a new list of typedObserver if it does not already exist
		if (typedObservers == null) {
			typedObservers = new ArrayList<IApiObserver>();
			this.observers.put(Integer.valueOf(observerType), typedObservers);
		}
		typedObservers.add(observer);
	}
	
	/**
	 * Notify observers with corresponding type
	 * @param observerType The type of observers to notify
	 * @param newValue The new value to send to observers
	 */
	protected void notifyObservers(int observerType, Object newValue) {
		List<IApiObserver> typedObservers = this.observers.get(Integer.valueOf(observerType));
		for (IApiObserver observer : typedObservers) {
			observer.update(this, newValue);
		}
	}
	
	/**
	 * @return <code>true</code> if some credential information have been stored
	 */
	public boolean isAuthenticated() {
		return authentication;
	}
	
	/**
	 * Set the authentication status
	 * @param authentication <code>true</code> if the API is authenticated
	 */
	public void setAuthenticated(boolean authentication) {
		this.authentication = authentication;
	}
	
	/**
	 * Set authentication properties
	 * @param login The registered login
	 * @param password The registered password
	 */
	public void setAuthenticationCredential(String key, String value) {
		this.credentials.put(key, value);
	}
	
	/**
	 * @param key The asked credential
	 * @return The credential value if is exists or <code>null</code>.
	 */
	public String getAuthenticationCredential(String key) {
		return this.credentials.get(key);
	}
}
