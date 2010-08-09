package fr.lip6.move.coloane.core.session;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.ApiDescription;
import fr.lip6.move.coloane.core.extensions.ApiExtension;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.ResultManager;
import fr.lip6.move.coloane.core.ui.checker.Checker;
import fr.lip6.move.coloane.core.ui.menus.ColoaneRootMenu;
import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.result.Tip;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * Session.<br>
 * 
 * A session holds :
 * <ul>
 * 	<li>a main graph</li>
 * 	<li>a list of results</li>
 * 	<li>a list of dedicated menus (services, actions, ...)</li>
 * 	<li>a list of tips</li>
 * </ul>
 * 
 * @see Tip
 * 
 * @author Jean-Baptiste Voron
 */
public class Session implements ISession {
	/** Logger */
	private static final Logger LOG = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

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

	/** A list of menu manager */
	private List<ColoaneRootMenu> rootMenus = new ArrayList<ColoaneRootMenu>();

	/** A list of tips. Each object may have several associated tips */
	private Map<Integer, List<ICoreTip>> tips = new HashMap<Integer, List<ICoreTip>>();

	/** Listener manager */
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Constructor<br>
	 * @param sessionId The session name
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

		// The graph associated with the session must not be null
		if (graph == null) {
			throw new ColoaneException("The session must be associated with a graph"); //$NON-NLS-1$
		}		
		this.graph = graph;
		
		// Set basic menus (for available APIs)
		List<ApiDescription> apis = ApiExtension.getApis(this.graph.getFormalism());
		for (ApiDescription api : apis) {
			LOG.finer("Building the " + api.getName() + " root-menu associated with the session"); //$NON-NLS-1$ //$NON-NLS-2$
			ColoaneRootMenu apiMenu = MenuManipulation.buildRootMenu(api.getName(), api.getDescription(), api.getIcon());
		
			// Build sub-menus
			LOG.finer("Fetching sub-menus"); //$NON-NLS-1$
			List<ISubMenu> submenus = api.getApiClass().initApi();
			for (ISubMenu submenu : submenus) {
				apiMenu.add(MenuManipulation.buildSubMenu(submenu));
			}
			rootMenus.add(apiMenu);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return this.sessionId;
	}

	/**
	 * Resume the session
	 */
	public final void resume() {
		if (console != null) {
			ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
		}
	}

	/**
	 * Destroy the session
	 */
	public final void destroy() {
		if (console != null) {
			ConsolePlugin.getDefault().getConsoleManager().removeConsoles(new IConsole[] {console});
		}
	}

	/** {@inheritDoc} */
	public final String getSessionId() {
		return this.sessionId;
	}

	/** {@inheritDoc} */
	public final IGraph getGraph() {
		return this.graph;
	}

	/** {@inheritDoc} */
	public final void setChecker(Checker checker) {
		this.checker = checker;
	}
	
	/** {@inheritDoc} */
	public Checker getChecker() {
		return checker;
	}

	/** {@inheritDoc} */
	public final List<ColoaneRootMenu> getRootServiceMenus() {
		return Collections.unmodifiableList(rootMenus);
	}

	/** {@inheritDoc} */
	public final ColoaneRootMenu getRootServiceMenu(String menuName) {
		for (ColoaneRootMenu rootMenu : this.rootMenus) {
			if (rootMenu.getMenuText().toLowerCase().equals(menuName.toLowerCase())) {
				return rootMenu;
			}
		}
		LOG.warning("No root menu named : " + menuName + " has been found...");  //$NON-NLS-1$//$NON-NLS-2$
		return null;
	}

	/** {@inheritDoc} */
	public final ResultManager getResultManager() {
		if (this.resultManager == null) {
			this.resultManager = new ResultManager();
		}
		return this.resultManager;
	}

	/** {@inheritDoc} */
	public final Collection<ICoreTip> getTips() {
		List<ICoreTip> list = new ArrayList<ICoreTip>();
		for (List<ICoreTip> values : tips.values()) {
			list.addAll(values);
		}
		return list;
	}

	/** {@inheritDoc} */
	public final Collection<ICoreTip> getTipForObject(int id) {
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
	public final void addAllTips(Collection<ICoreTip> tips) {
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
	public final void removeTips(Collection<ICoreTip> tips) {
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
	 * @return The console associated with this session
	 */
	private MessageConsole getConsole() {
		if (console == null) {
			console = new MessageConsole(sessionId, null); // TODO : Add an icon
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {console});
		}
		return console;
	}

	/**
	 * Print a message in the associated console
	 * @param message the message to print
	 * @param type The type of message to print
	 * @see MessageType
	 */
	public final void printConsoleMessage(String message, MessageType type) {
		MessageConsoleStream mcs = this.getConsole().newMessageStream();
		type.applyType(mcs);
		mcs.println(message);
		try {
			mcs.flush();
			mcs.close();
		} catch (IOException e) {
			// Hope that no such problem will ever occurs
			throw new AssertionError(e);
		}
	}
	
	/** {@inheritDoc} */
	public final synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs .addPropertyChangeListener(listener);
	}

	/** {@inheritDoc} */
	public final synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Send a notification to all listeners
	 * @param property The property that has been updated
	 * @param oldValue The old property value
	 * @param newValue The new property value
	 */
	protected final void firePropertyChange(final String property, final Object oldValue, final Object newValue) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (pcs.hasListeners(property)) {
					pcs.firePropertyChange(property, oldValue, newValue);
				}
			}
		});
	}
}
