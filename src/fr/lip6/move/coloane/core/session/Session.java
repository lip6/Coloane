package fr.lip6.move.coloane.core.session;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * Definition d'une session
 */
public class Session implements ISession {
	/** Le logger pour la classe */
	private static final Logger LOG = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private static final List<ICoreTip> EMPTY_TIP_LIST = Collections.unmodifiableList(new ArrayList<ICoreTip>(0));

	/** Identifiant de la session */
	private String sessionId;

	/** Le graph associe */
	private IGraph graph;

	/** Le ResultTreeList associé */
	private ResultTreeList results;

	/** Console pour cette session, elle est créée à la première utilisation */
	private MessageConsole console;

	/** Arborescence du menu et des services de la session */
	private List<MenuManager> menus = new ArrayList<MenuManager>();

	/**	Liste des services disponibles */
	private Map<String, IService> services = new HashMap<String, IService>();

	/** Liste des options active */
	private Set<IOptionMenu> options = new HashSet<IOptionMenu>();

	/** Status de la session */
	private int status = ISession.CLOSED;

	/** Objet session de l'api */
	private IApiSession apiSession;

	/** Liste des tips rangés par id des éléments sur lesquels ils pointent */
	private Map<Integer, List<ICoreTip>> tips = new HashMap<Integer, List<ICoreTip>>();

	/** La gestion des listeners est déléguée a cette classe */
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);


	/**
	 * Constructeur<br>
	 * Tous les champs sont initialisés.<br>
	 * Le nom ne doit pas être nul.
	 * @param sessionId Nom de la session
	 */
	public Session(String sessionId) {
		if (sessionId == null) {
			throw new NullPointerException("name cannot be null"); //$NON-NLS-1$
		}
		this.sessionId = sessionId;
	}

	/**
	 * Reprise de la session
	 */
	public final void resume() {
		LOG.finer("Reprise de la session " + sessionId); //$NON-NLS-1$
		if (status == ISession.CONNECTED) {
			try {
				apiSession.resume();
			} catch (ApiException e) {
				e.printStackTrace();
				LOG.finer("Impossible de reprendre la session " + sessionId); //$NON-NLS-1$
				// TODO : désactiver les services du menu
			}
		}
		if (console != null) {
			ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
		}
	}

	/**
	 * Destruction de la session :
	 * <ul>
	 * 	<li>Déconnexion de la session</li>
	 * </ul>
	 * @throws ApiException En cas d'echec
	 */
	public final void destroy() throws ApiException {
		if (console != null) {
			ConsolePlugin.getDefault().getConsoleManager().removeConsoles(new IConsole[] {console});
		}
		if (apiSession != null) {
			disconnect(true);
		}
	}

	/** {@inheritDoc} */
	public final String getSessionId() {
		return sessionId;
	}

	/** {@inheritDoc} */
	public final IGraph getGraph() {
		return this.graph;
	}

	/** {@inheritDoc} */
	public final void setModel(IGraph model) {
		this.graph = model;
	}

	/** {@inheritDoc} */
	public final List<MenuManager> getServicesMenu() {
		return Collections.unmodifiableList(menus);
	}

	/** {@inheritDoc} */
	public final void addServicesMenu(MenuManager menu) {
		LOG.finer("Ajout d'un rootMenu : " + menu.getMenuText()); //$NON-NLS-1$
		this.menus.add(menu);
	}

	/** {@inheritDoc} */
	public final void clearServicesMenu() {
		LOG.finer("Suppression du menu"); //$NON-NLS-1$
		menus.clear();
	}

	/** {@inheritDoc} */
	public final int getStatus() {
		return status;
	}

	/** {@inheritDoc} */
	public final void setStatus(int status) {
		int oldStatus = this.status;
		this.status = status;
		firePropertyChange(ISession.PROP_CONNECTION, oldStatus, status);
	}

	/** {@inheritDoc} */
	public final ResultTreeList getServiceResults() {
		if (results == null) {
			this.results = new ResultTreeList();
		}
		return results;
	}

	/**
	 * Connecte la session à framekit.
	 * @param monitor moniteur pour la progressbar
	 * @return <code>true</code> si la connexion est ouverte
	 * @throws ApiException en cas d'échec lors de la connexion
	 */
	public final ISessionInfo connect(IProgressMonitor monitor) throws ApiException {
		monitor.subTask(Messages.Session_0);
		apiSession = Com.getInstance().createApiSession();
		monitor.worked(1);
		monitor.subTask(Messages.Session_1);
		ISessionInfo info = apiSession.open(graph.getDate(), graph.getFormalism().getFKName(), sessionId);
		monitor.worked(1);
		setStatus(ISession.CONNECTED);
		LOG.finer("Connexion de la session " + sessionId); //$NON-NLS-1$
		return info;
	}

	/**
	 * Déconnecte la session de framekit.
	 * @throws ApiException En cas d'erreur de l'api
	 */
	public final void disconnect(boolean safeMode) throws ApiException {
		disconnect(safeMode, new IProgressMonitor() {
			public void beginTask(String name, int totalWork) { }
			public void done() { }
			public void internalWorked(double work) { }
			public boolean isCanceled() { return false; }
			public void setCanceled(boolean value) { }
			public void setTaskName(String name) { }
			public void subTask(String name) { }
			public void worked(int work) { }
		});
	}

	/**
	 * Déconnecte la session de framekit.
	 * @param monitor moniteur pour la boite de progression
	 * @throws ApiException En cas d'erreur de l'api
	 */
	public final void disconnect(boolean safeMode, IProgressMonitor monitor) throws ApiException {
		LOG.finest("Demande de déconnexion de " + sessionId); //$NON-NLS-1$
		monitor.subTask(Messages.Session_2);
		menus.clear();
		monitor.worked(1);
		monitor.subTask(Messages.Session_3);
		if (apiSession != null) {
			if (safeMode) {
				apiSession.close();
			}
			apiSession = null;
		}
		monitor.worked(1);
		setStatus(ISession.CLOSED);
		LOG.finer("Déconnexion de la session " + sessionId); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return sessionId;
	}

	/** {@inheritDoc} */
	public final void addAllServices(Collection<IService> services) {
		for (IService service : services) {
			this.services.put(service.getId(), service);
		}
	}

	/** {@inheritDoc} */
	public final IService getService(String id) {
		return services.get(id);
	}

	/** {@inheritDoc} */
	public final Collection<IService> getServices() {
		return services.values();
	}

	/** {@inheritDoc} */
	public final List<IOptionMenu> getActiveOptions(String path) {
		List<IOptionMenu> pathOptions = new ArrayList<IOptionMenu>();
		for (IOptionMenu option : options) {
			if (option.getPath().startsWith(path)) {
				pathOptions.add(option);
			}
		}
		return pathOptions;
	}

	/** {@inheritDoc} */
	public final void setOption(IOptionMenu option, boolean state) {
		if (state) {
			options.add(option);
		} else {
			options.remove(option);
		}
	}

	/**
	 * @param service service à executer
	 * @param path Chemin
	 * @throws ApiException Si l'invocation du service a échoué
	 */
	public final void askForService(IService service, String path) throws ApiException {
		if (status != ISession.CONNECTED) {
			LOG.warning("Invocation du service impossible, la session n'est pas connecté"); //$NON-NLS-1$
			return;
		}
		LOG.fine("Invocation du service : " + service + " " + getActiveOptions(path)); //$NON-NLS-1$//$NON-NLS-2$
		IGraph emptyGraph = null;
		if (service.getOutputFormalism() != null) {
			IFormalism form = FormalismManager.getInstance().getFormalismByFkName(service.getOutputFormalism());
			emptyGraph = new GraphModel(form.getName());
		}
		apiSession.askForService(service, getActiveOptions(path), null, null, graph, emptyGraph);
	}

	/**
	 * Préviens l'api d'un changement majeur du modèle
	 * @throws ApiException En cas d'erreur de l'api
	 */
	public final void invalidModel() throws ApiException {
		if (apiSession == null) {
			return;
		}
		apiSession.invalidModel();
	}

	/**
	 * Envoi la réponse a une boite de dialogue
	 * @param dialogAnswer réponse à la boite de dialogue
	 * @throws ApiException En cas d'erreur de l'api
	 */
	public final void sendDialogAnswer(IDialogAnswer dialogAnswer) throws ApiException {
		if (apiSession == null) {
			return;
		}
		apiSession.sendDialogAnswer(dialogAnswer);
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
	 * Envoie une notification de modification de propriété
	 * @param property La propriété
	 * @param oldValue L'ancienne valeur de la propriété
	 * @param newValue La nouvelle valeur
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

	/**
	 * Interruption du service en cours.
	 */
	public final void interruptService() {
		if (status == ISession.CONNECTED) {
			apiSession.stopService();
		}
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
	public final Collection<ICoreTip> getTip(int id) {
		List<ICoreTip> list = tips.get(id);
		if (list == null) {
			list = EMPTY_TIP_LIST;
		}
		return list;
	}

	/**
	 * Mise à jours des tips
	 * @param tips listes des tips concernée par la mise à jours
	 */
	private void updateTips(Collection<ICoreTip> tips) {
		firePropertyChange(ISession.PROP_TIPS, null, tips);
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
	public final void removeAllTips(Collection<ICoreTip> tips) {
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
	 * @return la console
	 */
	private MessageConsole getConsole() {
		if (console == null) {
			console = new MessageConsole(sessionId, null); // TODO : ajouter une icône
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {console});
		}
		return console;
	}

	/**
	 * @param message message à afficher dans la console
	 * @param type type du message (des constantes sont définies dans la classe MessageType)
	 */
	public final void printConsoleMessage(String message, MessageType type) {
		MessageConsoleStream mcs = getConsole().newMessageStream();
		type.applyType(mcs);
		mcs.println(message);
		try {
			mcs.flush();
			mcs.close();
		} catch (IOException e) {
			// Si il y a un problème à ce niveau, c'est qu'il y a vraiment un problème.
			throw new AssertionError(e);
		}
	}
}
