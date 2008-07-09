package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.reports.GenericReport;
import fr.lip6.move.coloane.core.results.reports.IReport;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Liste de ResultTree. Cette liste étend Observable.<br>
 * Cette classe est thread-safe.
 */
public class ResultTreeList extends Observable implements IResultTree, Observer {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Attributs du point d'extension 'exports'
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.reports"; //$NON-NLS-1$
	private static final String SERVICE_EXTENSION = "service_name"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$

	private final ConcurrentHashMap<String, IResultTree> map;
	private final ArrayList<Integer> highlights;
	private HashMap<String, IReport> services;
	private final IReport generic;

	public ResultTreeList() {
		map = new ConcurrentHashMap<String, IResultTree>();
		highlights = new ArrayList<Integer>();
		generic = new GenericReport();
	}

	private void buildServicesList() {
		services = new HashMap<String, IReport>();

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement element : reg.getConfigurationElementsFor(EXTENSION_POINT_ID)) {
			String service = element.getAttribute(SERVICE_EXTENSION);
			try {
				IReport report = (IReport) element.createExecutableExtension(CLASS_EXTENSION);
				services.put(service, report);
				LOGGER.fine("Ajout du service de resultat : " + service); //$NON-NLS-1$
			} catch (CoreException e) {
				LOGGER.warning("Probleme avec l'extension : " + service); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Prise en compte d'un objet resultat en provenance de la partie Com<br>
	 * L'objet est transformé en {@link IResultTree}
	 * @param serviceName Le nom du service pour lequel on recoit les resultats
	 * @param result L'objet (en provenance de Com) qui contient les resultats
	 */
	public final void add(String serviceName, IResultsCom result) {
		if (services == null) {
			this.buildServicesList();
		}

		ResultTreeImpl newResult = null;

		IReport report = services.get(serviceName);
		if (report != null) {
			newResult = report.build(result);
		}

		// Si aucun report specialise n'est disponible, on utilise le GenericReport
		if (newResult == null) {
			newResult = generic.build(result);
		}

		newResult.setParent(this);
		newResult.setServiceName(serviceName);
		map.put(serviceName, newResult);
		update(null, getWidth(newResult));
	}

	/**
	 * Retourne le nombre d'elements composant l'arbre de resultats (i.e. le nombre de colonnes)
	 * @param node L'arbre de resultat qu'on souhaite analyser
	 * @return Le nombre d'elements composant l'arbre (colonnes)
	 */
	private int getWidth(IResultTree node) {
		if (node == null) {
			return -1;
		}
		// Pour le premier niveau
		int max = node.getElement().size();

		// Parcours en profondeur
		for (IResultTree child : node.getChildren()) {
			max = Math.max(max, getWidth(child));
		}
		return max;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#addChild(fr.lip6.move.coloane.core.results.IResultTree)
	 */
	public final void addChild(IResultTree child) {
		String serviceName = ""; //$NON-NLS-1$
		if (child instanceof ResultTreeImpl) {
			serviceName = ((ResultTreeImpl) child).getServiceName();
		}
		map.put(serviceName, child);
		update(null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getChildren()
	 */
	public final List<IResultTree> getChildren() {
		return new ArrayList<IResultTree>(map.values());
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getElement()
	 */
	@SuppressWarnings("unchecked")
	public final List getElement() {
		return new ArrayList(map.values());
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getParent()
	 */
	public final IResultTree getParent() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#setParent(fr.lip6.move.coloane.core.results.IResultTree)
	 */
	public void setParent(IResultTree parent) {
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getId()
	 */
	public final int getId() {
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getHighlight()
	 */
	public final List<Integer> getHighlighted() {
		return this.highlights;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#setHighlighted(int[])
	 */
	public final void addHighlighted(int... toHighlight) {
		for (Integer id : toHighlight) {
			this.highlights.add(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#remove()
	 */
	public final void remove() { }

	/*
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public final void update(Observable o, Object arg) {
		setChanged();
		Integer width;
		if (arg != null) {
			width = (Integer) arg;
		} else {
			width = 0;
		}
		notifyObservers(width);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getSessionManager()
	 */
	public final ISessionManager getSessionManager() {
		return SessionManager.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#setSessionManager(fr.lip6.move.coloane.core.motor.session.ISessionManager)
	 */
	public final void setSessionManager(ISessionManager sessionManager) {
		return;
	}

	@Override
	public final String toString() {
		return map.toString();
	}

	/**
	 * Supprime le resultat serviceName
	 * @param serviceName
	 */
	public final void remove(String serviceName) {
		if (serviceName != null) {
			map.remove(serviceName);
			update(null, 0);
		}
	}

	/**
	 * Supprime tous les resultats de la liste
	 */
	public final void removeAll() {
		map.clear();
		update(null, 0);
	}
}
