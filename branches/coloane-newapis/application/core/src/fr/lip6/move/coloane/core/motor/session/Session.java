package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

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

	/** {@inheritDoc} */
	public final void suspend() {
		LOG.finer("Suspension de la session " + name); //$NON-NLS-1$
		if (status == ISession.CONNECTED) {
			status = ISession.SUSPENDED;
		}
	}

	/** {@inheritDoc} */
	public final void resume() {
		LOG.finer("Reprise de la session " + name); //$NON-NLS-1$
		if (status == ISession.SUSPENDED) {
			status = ISession.CONNECTED;
			try {
				apiSession.resume();
			} catch (ApiException e) {
				e.printStackTrace();
				LOG.finer("Impossible de reprendre la session " + name); //$NON-NLS-1$
				// TODO : désactiver les services du menu
			}
		}
	}

	/** {@inheritDoc} */
	public final void destroy() {
		if (apiSession != null && !disconnect()) {
			LOG.warning("La session " + name + " n'a pas pu être fermé"); //$NON-NLS-1$//$NON-NLS-2$
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
		this.status = status;
	}

	/** {@inheritDoc} */
	public final ResultTreeList getServiceResults() {
		if (results == null) {
			this.results = new ResultTreeList();
		}
		return results;
	}

	/** {@inheritDoc} */
	public final boolean connect(IProgressMonitor monitor) {
		try {
			monitor.subTask(Messages.Session_0);
			apiSession = Com.getInstance().createApiSession();
			monitor.worked(1);
			monitor.subTask(Messages.Session_1);
			apiSession.open(graph.getDate(), graph.getFormalism().getFKName(), name);
			monitor.worked(1);
		} catch (ApiException e) {
			LOG.warning("Problème lors de la connection de la session : " + e); //$NON-NLS-1$
			e.printStackTrace();
			return false;
		}
		setStatus(ISession.CONNECTED);
		LOG.finer("Connexion de la session " + name); //$NON-NLS-1$
		return true;
	}

	/** {@inheritDoc} */
	public final boolean disconnect() {
		LOG.finest("Demande de déconnexion de " + name); //$NON-NLS-1$
		menus.clear();
		adminMenu = null;
		try {
			if (apiSession != null) {
				apiSession.close();
				apiSession = null;
			}
		} catch (ApiException e) {
			LOG.warning("Problème lors de la déconnexion de la session : " + e); //$NON-NLS-1$
			return false;
		}
		setStatus(ISession.CLOSED);
		LOG.finer("Déconnexion de la session " + name); //$NON-NLS-1$
		return true;
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

	/** {@inheritDoc} */
	public final void askForService(IService service) {
		if (status != ISession.CONNECTED) {
			LOG.warning("Invocation du service impossible, la session n'est pas connecté"); //$NON-NLS-1$
			return;
		}
		try {
			LOG.fine("Invocation du service : " + service + " " + getActiveOptions()); //$NON-NLS-1$//$NON-NLS-2$
			apiSession.askForService(service, getActiveOptions(), graph);
		} catch (ApiException e) {
			LOG.warning("L'invocation du service a échoué : " + e); //$NON-NLS-1$
		}
	}

	/** {@inheritDoc} */
	public final void invalidModel() {
		if (status != ISession.CONNECTED) {
			return;
		}
		apiSession.invalidModel();
	}
}
