/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.session;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.ApiDescription;
import fr.lip6.move.coloane.core.extensions.ApiExtension;
import fr.lip6.move.coloane.core.ui.checker.CheckerManager;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;

/**
 * Session Manager.<br>
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public final class SessionManager implements ISessionManager {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Session manager instance */
	private static SessionManager instance = null;

	/** Current session */
	private ISession currentSession;

	/** Global session */
	private ISession globalSession;

	/** List of all sessions currently opened */
	private Map<String, ISession> sessions = new HashMap<String, ISession>();

	/** Listeners handler */
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/** List of available (connected) <b>global</b> APIs */
	private List<ApiDescription> apis = null;

	/** Automatic session ids. */
	private BigInteger nextSessionId = BigInteger.ZERO;
	
	/**
	 * Constructor (private)
	 * @throws ColoaneException The global session cannot be created
	 */
	private SessionManager() throws ColoaneException {
		this.currentSession = null;
		this.globalSession = new Session("global", null); //$NON-NLS-1$
	}

	/**
	 * @return THE (unique) session manager
	 */
	public static synchronized ISessionManager getInstance() {
		if (instance == null) {
			try {
				instance = new SessionManager();
			} catch (ColoaneException e) {
				LOGGER.severe(e.getMessage());
				e.printStackTrace();
			}
		}
		return instance;
	}

	/** {@inheritDoc} */
	@Override
	public ISession getCurrentSession() {
		return currentSession;
	}

	/** {@inheritDoc} */
	@Override
	public ISession getGlobalSession() {
		return globalSession;
	}

	/** {@inheritDoc} */
	@Override
	public ISession getSession(String sessionName) {
		return sessions.get(sessionName);
	}

	/** {@inheritDoc} */
	@Override
	public ISession getSession(IGraph graph) {
		for (ISession session : sessions.values()) {
			if (session.getGraph().equals(graph)) {
				return session;
			}
		}
		LOGGER.warning("No session has been found"); //$NON-NLS-1$
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Collection<ISession> getSessions() {
		return sessions.values();
	}

	/** {@inheritDoc} */
	@Override
	public ISession createSession(IGraph graph) throws ColoaneException {
		String sessionId = "coloane-session-" + nextSessionId.toString(); //$NON-NLS-1$
		nextSessionId = nextSessionId.add(BigInteger.ONE);
		// If a session already exists with the same name
		if (sessions.containsKey(sessionId)) {
			throw new ColoaneException("A session with the same name (" + sessionId + ") already exists..."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// Otherwise, a new session is created and added to the session list
		ISession newSession = new Session(sessionId, graph);
		sessions.put(sessionId, newSession);
		//		setCurrentSession(newSession); // Set the current session if no session is active yet

		// Before returning the new session, we add it an appropriate checker
		CheckerManager.getInstance().associateCheckerToSession(newSession);

		return newSession;
	}

	/** {@inheritDoc} */
	@Deprecated
	@Override
	public ISession createSession(String sessionId, IGraph graph) throws ColoaneException {
		// If a session already exists with the same name
		if (sessions.containsKey(sessionId)) {
			throw new ColoaneException("A session with the same name (" + sessionId + ") already exists..."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// Otherwise, a new session is created and added to the session list
		ISession newSession = new Session(sessionId, graph);
		sessions.put(sessionId, newSession);
		//		setCurrentSession(newSession); // Set the current session if no session is active yet

		// Before returning the new session, we add it an appropriate checker
		CheckerManager.getInstance().associateCheckerToSession(newSession);

		return newSession;
	}

	/** {@inheritDoc} */
	@Override
	public ISession resumeSession(String sessionId) {
		if (sessionId == null) { return null; }
		ISession toResume = getSession(sessionId);

		if (toResume != null) {
			LOGGER.finer("Resuming the session : " + sessionId); //$NON-NLS-1$
			// Tells the session that it will be resumed
			((Session) toResume).resume();
			setCurrentSession(toResume);

			// Refreshing menu list
			return toResume;
		}
		LOGGER.warning("The session " + sessionId + " is not registered in the session manager"); //$NON-NLS-1$ //$NON-NLS-2$
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public ISession destroySession(String sessionId) {
		if (sessionId != null) {
			LOGGER.fine("Destroying the session " + sessionId); //$NON-NLS-1$

			ISession toDestroy = sessions.remove(sessionId);
			if (toDestroy != null) {
				((Session) toDestroy).destroy();
				// If the destroyed session is the current one...
				if (toDestroy.equals(currentSession)) {
					setCurrentSession(null);
				}
				return toDestroy;
			}
			LOGGER.warning("The session " + sessionId + " is not registered in the session manager"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return null;
	}

	/**
	 * Change the current session
	 * @param currentSession The new current session
	 * @return The new current session. Be careful, the new current session can be <code>null</code>.
	 */
	private ISession setCurrentSession(ISession currentSession) {
		ISession previousSession = this.currentSession;
		this.currentSession = currentSession;
		LOGGER.fine("The session " + currentSession + " is now the current one"); //$NON-NLS-1$ //$NON-NLS-2$

		// Refresh views
		firePropertyChange(PROP_CURRENT_SESSION, previousSession, currentSession);

		return this.currentSession;
	}

	/** {@inheritDoc} */
	@Override
	public List<ApiDescription> getAvailableGlobalApis() {
		apis = ApiExtension.getAvailableApis(null, true);
		return apis;
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Send a notification to all registered listeners
	 * @param property The property that has changed
	 * @param oldValue The old property value
	 * @param newValue The new property value
	 */
	protected void firePropertyChange(final String property, final Object oldValue, final Object newValue) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (pcs.hasListeners(property)) {
					pcs.firePropertyChange(property, oldValue, newValue);
				}
			}
		});
	}
}
