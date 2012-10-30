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
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.ResultManager;
import fr.lip6.move.coloane.core.ui.checker.Checker;
import fr.lip6.move.coloane.core.ui.panels.ConsoleResultHandler;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;

/**
 * Session.<br>
 *
 * A session holds :
 * <ul>
 * 	<li>a main graph</li>
 * 	<li>a list of results</li>
 * 	<li>a list of tips</li>
 * </ul>
 *
 * @see Tip
 *
 * @author Jean-Baptiste Voron
 */
public final class Session implements ISession {
	/** Session identifier */
	private String sessionId;

	/** The graph */
	private IGraph graph;

	/** The checker associated with the graph */
	private Checker checker;

	/** The result manager */
	private ResultManager resultManager;

	/** The console (lazy loading) */
	private MessageConsole console;

	/** List of available (connected) <b>local</b> APIs */
	private List<ApiDescription> apis = null;

	/** A list of tips. Each object may have several associated tips */
	private Map<Integer, List<ICoreTip>> tips = new HashMap<Integer, List<ICoreTip>>();

	/** Listener manager */
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Constructor.
	 * @param sessionId The session name, non null and non empty
	 * @param graph The graph, non null.
	 * @throws ColoaneException if arguments are null or empty
	 */
	Session(String sessionId, IGraph graph) throws ColoaneException {
		// The name must not be null
		if (sessionId == null) {
			throw new ColoaneException("The session name must not be null"); //$NON-NLS-1$
		}
		// The session name must not be empty
		if (sessionId.isEmpty()) {
			throw new ColoaneException("The session name must not be empty"); //$NON-NLS-1$
		}
		this.sessionId = sessionId;
		this.graph = graph;

		new ConsoleResultHandler().init(this);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return this.sessionId;
	}

	/**
	 * Resume the session
	 */
	public void resume() {
		if (console != null) {
			ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
		}
	}

	/**
	 * Destroy the session
	 */
	public void destroy() {
		if (console != null) {
			ConsolePlugin.getDefault().getConsoleManager().removeConsoles(new IConsole[] {console});
		}
	}

	/** {@inheritDoc} */
	@Override
	public String getSessionId() {
		return this.sessionId;
	}

	/** {@inheritDoc} */
	@Override
	public IGraph getGraph() {
		return this.graph;
	}

	/** {@inheritDoc} */
	@Override
	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	/** {@inheritDoc} */
	@Override
	public Checker getChecker() {
		return checker;
	}

	/** {@inheritDoc} */
	@Override
	public List<ApiDescription> getAvailableApis() {
		if (apis == null) {
			apis = ApiExtension.getAvailableApis(this, false);
		}
		return apis;
	}

	/** {@inheritDoc} */
	@Override
	public ResultManager getResultManager() {
		if (this.resultManager == null) {
			this.resultManager = new ResultManager();
		}
		return this.resultManager;
	}

	/** {@inheritDoc} */
	@Override
	public Collection<ICoreTip> getTips() {
		List<ICoreTip> list = new ArrayList<ICoreTip>();
		for (List<ICoreTip> values : tips.values()) {
			list.addAll(values);
		}
		return list;
	}

	/** {@inheritDoc} */
	@Override
	public Collection<ICoreTip> getTipForObject(int id) {
		List<ICoreTip> list = tips.get(id);
		if (list == null) {
			// Return an empty list
			list = Collections.unmodifiableList(new ArrayList<ICoreTip>(0));
		}
		return list;
	}

	/**
	 * Update tips
	 * @param tips The list of tips that are concerned by this update
	 */
	private void updateTips(Collection<ICoreTip> tips) {
		for (ICoreTip tip : tips) {
			INode node = graph.getNode(tip.getIdObject());
			if (node != null) {
				node.updateTips();
				continue;
			}
			IArc arc = graph.getArc(tip.getIdObject());
			if (arc != null) {
				arc.updateTips();
			}
		}
		firePropertyChange(ISession.PROP_TIPS, null, tips);
	}

	/** {@inheritDoc} */
	@Override
	public void addAllTips(Collection<ICoreTip> tips) {
		for (ICoreTip tip : tips) {
			List<ICoreTip> values = this.tips.get(tip.getIdObject());
			if (values == null) {
				values = new ArrayList<ICoreTip>();
				this.tips.put(tip.getIdObject(), values);
			}
			values.add(tip);
		}
		updateTips(tips);
	}

	/** {@inheritDoc} */
	@Override
	public void removeTips(Collection<ICoreTip> tips) {
		for (ICoreTip tip : tips) {
			List<ICoreTip> values = this.tips.get(tip.getIdObject());

			// On supprime le tip sauf si il n'existe plus.
			if (values != null) {
				values.remove(tip);
				if (values.isEmpty()) {
					this.tips.remove(tip.getIdObject());
				}
			}
		}
		updateTips(tips);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageConsole getConsole() {
		if (this.console == null) {
			this.console = new MessageConsole(sessionId, null); // TODO : Add an icon
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {this.console});
		}
		return console;
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs .addPropertyChangeListener(listener);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Send a notification to all listeners
	 * @param property The property that has been updated
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
