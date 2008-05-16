package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.results.reports.GenericReport;
import fr.lip6.move.coloane.core.results.reports.IReport;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Liste de ResultTree. Cette liste étend Observable.<br>
 * Cette classe est thread-safe.
 */
public class ResultTreeList extends Observable implements IResultTree, Observer {
	/**
	 * Attributs du point d'extension 'exports'
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.reports"; //$NON-NLS-1$
	private static final String SERVICE_EXTENSION = "service_name"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$

	private final CopyOnWriteArrayList<IResultTree> list;
	private final ArrayList<List<Integer>> highlights;
	private final HashMap<String, IReport> services;
	private final IReport generic;

	public ResultTreeList() {
		list = new CopyOnWriteArrayList<IResultTree>();
		highlights = new ArrayList<List<Integer>>();
		services = new HashMap<String, IReport>();
		generic = new GenericReport();

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement element : reg.getConfigurationElementsFor(EXTENSION_POINT_ID)) {
			String service = element.getAttribute(SERVICE_EXTENSION);
			try {
				IReport report = (IReport) element.createExecutableExtension(CLASS_EXTENSION);
				services.put(service, report);
				Coloane.getLogger().fine("Ajout du service de résultat : " + service);
			} catch (CoreException e) {
				Coloane.getLogger().warning("Problème avec l'extension : " + service);
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
		IResultTree newResult = null;
		List<Integer> newHighlight = null;

		IReport report = services.get(serviceName);
		if (report != null) {
			newResult = report.build(result);
			newHighlight = report.highlightNode(result);
		}

		if (newResult == null) {
			newResult = generic.build(result);
		}

		if (newHighlight == null) {
			newHighlight = new ArrayList<Integer>();
		}

		list.add(newResult);
		highlights.add(newHighlight);
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
		list.add(child);
		update(null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getChildren()
	 */
	public final List<IResultTree> getChildren() {
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getElement()
	 */
	@SuppressWarnings("unchecked")
	public final List getElement() {
		return list;
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

	/**
	 *
	 * @return Une liste d'identifiants d'elements
	 */
	public final List<Integer> getHighlight() {
		return highlights.get(highlights.size() - 1);
	}

	/**
	 *
	 * @param index
	 * @return
	 */
	public final List<Integer> getHighlight(int index) {
		return highlights.get(index);
	}

	/**
	 *
	 * @param node
	 * @return
	 */
	public final List<Integer> getHighlight(IResultTree node) {
		return getHighlight(list.indexOf(node));
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

	@Override
	public final String toString() {
		return list.toString();
	}

	/**
	 *
	 */
	public final void removeAll() {
		list.clear();
		update(null, 0);
	}
}
