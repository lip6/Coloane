package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

/**
 * Definition d'une session
 */
public class Session implements ISession {
	/** Le logger pour la classe */
	private static final Logger LOG = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Nom de la session */
	private String name;

	/** Le graph associe */
	private IGraph graph;

	/** Le ResultTreeList associé */
	private ResultTreeList results;

	/** Arborescence du menu administration */
	private MenuManager adminMenu;

	/** Arborescence du menu et des services de la session */
	private List<MenuManager> menus;

	/**	Liste des services disponibles */
	private Map<String, IService> services;

	/** Liste des options active */
	private Set<String> options;

	/** Status de la session */
	private int status;

	/** Objet session de l'api */
	private IApiSession apiSession;

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);


	/**
	 * Constructeur<br>
	 * Tous les champs sont initialisés.<br>
	 * Le nom ne doit pas etre nul.
	 * @param name Nom de la session
	 */
	public Session(String name) {
		if (name == null) {
			throw new NullPointerException("name cannot be null"); //$NON-NLS-1$
		}
		this.name = name;
		this.graph = null;
		this.status = ISession.CLOSED;
		this.results = null;
		this.adminMenu = null;
		this.menus = new ArrayList<MenuManager>();
		this.services = new HashMap<String, IService>();
		this.options = new HashSet<String>();
	}

	/**
	 * Reprise de la session
	 */
	public final void resume() {
		LOG.finer("Reprise de la session " + name); //$NON-NLS-1$
		if (status == ISession.CONNECTED) {
			try {
				apiSession.resume();
			} catch (ApiException e) {
				e.printStackTrace();
				LOG.finer("Impossible de reprendre la session " + name); //$NON-NLS-1$
				// TODO : désactiver les services du menu
			}
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
		if (apiSession != null) {
			disconnect();
		}
	}

	/** {@inheritDoc} */
	public final String getName() {
		return name;
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
	public final MenuManager getAdminMenu() {
		return adminMenu;
	}

	/** {@inheritDoc} */
	public final void setAdminMenu(MenuManager admin) {
		this.adminMenu = admin;
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
		ISessionInfo info = apiSession.open(graph.getDate(), graph.getFormalism().getFKName(), name);
		monitor.worked(1);
		setStatus(ISession.CONNECTED);
		LOG.finer("Connexion de la session " + name); //$NON-NLS-1$
		return info;
	}

	/**
	 * Déconnecte la session de framekit.
	 * @throws ApiException En cas d'erreur de l'api
	 */
	public final void disconnect() throws ApiException {
		disconnect(new IProgressMonitor() {
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
	public final void disconnect(IProgressMonitor monitor) throws ApiException {
		LOG.finest("Demande de déconnexion de " + name); //$NON-NLS-1$
		monitor.subTask(Messages.Session_2);
		menus.clear();
		adminMenu = null;
		monitor.worked(1);
		monitor.subTask(Messages.Session_3);
		if (apiSession != null) {
			apiSession.close();
			apiSession = null;
		}
		monitor.worked(1);
		setStatus(ISession.CLOSED);
		LOG.finer("Déconnexion de la session " + name); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return name;
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
	public final List<String> getActiveOptions() {
		return Collections.unmodifiableList(new ArrayList<String>(options));
	}

	/** {@inheritDoc} */
	public final void setOption(String option, boolean state) {
		if (state) {
			options.add(option);
		} else {
			options.remove(option);
		}
	}

	/**
	 * @param service service à executer
	 * @throws ApiException Si l'invocation du service a échoué
	 */
	public final void askForService(IService service) throws ApiException {
		if (status != ISession.CONNECTED) {
			LOG.warning("Invocation du service impossible, la session n'est pas connecté"); //$NON-NLS-1$
			return;
		}
		LOG.fine("Invocation du service : " + service + " " + getActiveOptions()); //$NON-NLS-1$//$NON-NLS-2$
		IGraph emptyGraph = null;
		if (service.getOutputFormalism() != null) {
			IFormalism form = FormalismManager.getInstance().getFormalismByFkName(service.getOutputFormalism());
			emptyGraph = new GraphModel(form.getName());
		}
		apiSession.askForService(service, getActiveOptions(), null, null, graph, emptyGraph);
	}

	/**
	 * Previens l'api d'un changement majeur du modèle
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
		apiSession.stopService();
	}
}
