package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.reports.GenericReport;
import fr.lip6.move.coloane.core.results.reports.IReport;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
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

	private final Map<String, IResultTree> map;
	private final List<Integer> highlights;
	private Map<String, IReport> services;
	private final IReport generic;

	/**
	 * Constructeur
	 */
	public ResultTreeList() {
		map = new LinkedHashMap<String, IResultTree>();
		highlights = new ArrayList<Integer>();
		generic = new GenericReport();
	}

	/**
	 * Ajouter tous les services disponible par le point d'extension SERVICE_EXTENSION
	 */
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
	public final void add(String serviceName, IResult result) {
		if (services == null) {
			this.buildServicesList();
		}

		ResultTreeImpl newResult = null;

		IReport report = services.get(serviceName.trim());
		if (report != null) {
			newResult = report.build(result);
		}

		// Si aucun report specialise n'est disponible, on utilise le GenericReport
		if (newResult == null) {
			newResult = generic.build(result);
		}

		// Si un résultat pour ce service existait déjà ou le supprime
		if (map.containsKey(serviceName)) {
			map.remove(serviceName);
		}

		newResult.setParent(this);
		newResult.setServiceName(serviceName);
		List<ICoreTip> coreTips = new ArrayList<ICoreTip>(result.getTipsList().size());
		for (ITip tip : result.getTipsList()) {
			coreTips.add(new CoreTipModel(tip));
		}
		newResult.setTips(coreTips);
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

	/** {@inheritDoc} */
	public final void addChild(IResultTree child) {
		String serviceName = ""; //$NON-NLS-1$
		if (child instanceof ResultTreeImpl) {
			serviceName = ((ResultTreeImpl) child).getServiceName();
		}
		map.put(serviceName, child);
		update(null, null);
	}

	/** {@inheritDoc} */
	public final List<IResultTree> getChildren() {
		return new ArrayList<IResultTree>(map.values());
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public final List getElement() {
		return new ArrayList(map.values());
	}

	/** {@inheritDoc} */
	public final IResultTree getParent() {
		return null;
	}

	/** {@inheritDoc} */
	public void setParent(IResultTree parent) {
	}

	/** {@inheritDoc} */
	public final int getId() {
		return -1;
	}

	/** {@inheritDoc} */
	public final List<Integer> getHighlighted() {
		return this.highlights;
	}

	/** {@inheritDoc} */
	public final void addHighlighted(int... toHighlight) {
		for (Integer id : toHighlight) {
			this.highlights.add(id);
		}
	}

	/** {@inheritDoc} */
	public final void remove() { }

	/** {@inheritDoc} */
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

	/** {@inheritDoc} */
	public final ISessionManager getSessionManager() {
		return SessionManager.getInstance();
	}

	/** {@inheritDoc} */
	public final void setSessionManager(ISessionManager sessionManager) {
		return;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return map.toString();
	}

	/**
	 * Supprime le resultat serviceName
	 * @param serviceName nom du service
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

	/** {@inheritDoc} */
	public final List<ICoreTip> getTips() {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	public final List<ICoreTip> getTips(List<Integer> haveTips) {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	public final void setTips(List<ICoreTip> tips) {
		throw new UnsupportedOperationException();
	}
}
