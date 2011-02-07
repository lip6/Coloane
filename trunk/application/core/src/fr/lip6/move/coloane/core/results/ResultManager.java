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
package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.reports.GenericReport;
import fr.lip6.move.coloane.core.results.reports.IReport;
import fr.lip6.move.coloane.core.session.ISessionManager;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.panels.IResultTree;
import fr.lip6.move.coloane.core.ui.panels.ResultTreeImpl;
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
 * Manager of results.<br>
 * This class handles results {@link IResult} coming from services (<i>local tools</i> or <i>platform tools</i>).<br>
 * Thanks to report classes {@link IReport}, the result is formatted and displayed correctly.<br>
 * <br>
 * Reports are used to deal with strange results coming from <b>old tools</b> or tools that are not aware of the results architecture used by Coloane.<br>
 * This class is thread-safe.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Demoulins
 * @author Florian David
 */
public class ResultManager extends Observable implements IResultTree, Observer {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Attributes for the extension point 'reports'.<br>
	 * Those attributes will be used to fill up the list with all available reports
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.reports"; //$NON-NLS-1$
	private static final String SERVICE_EXTENSION = "service_name"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$

	/** The map that associates a service name with its result tree */
	private final Map<String, IResultTree> map;
	/** Some object id to highlight */
	private final List<Integer> highlights;
	/** Association between service name and its delegate able to parse and handle results */
	private Map<String, IReport> services;
	/** A generic report able to deal with common results */
	private final IReport generic;

	/**
	 * Constructor
	 */
	public ResultManager() {
		map = new LinkedHashMap<String, IResultTree>();
		highlights = new ArrayList<Integer>();
		generic = new GenericReport();
	}

	/**
	 * Take into account a new {@link IResult} coming from a service.<br>
	 * The object is transformed into a {@link IResultTree}
	 * @param serviceName Service name
	 * @param result Object that contains all the result for this service
	 */
	public final void add(String serviceName, IResult result) {
		// If the current result should not be displayed (as specified), do not take care of it
		if (!result.shouldBeDisplayed()) {
			return;
		}

		// Lazy build of the services list (only if it is necessary).
		if (services == null) {
			this.buildServicesList();
		}

		ResultTreeImpl newResult = null;

		// We look for an existing report that is able to deal with this kind of result
		IReport report = services.get(serviceName.trim());
		if (report != null) {
			newResult = report.build(result);
		// If no dedicated report is available, use the generic one
		} else {
			newResult = generic.build(result);
		}

		// Some basic considerations (top tree position and master name)
		newResult.setParent(this);
		newResult.setServiceName(serviceName);

		// If there was already a result object for this service, we replace it by the new one
		if (map.containsKey(serviceName)) {
			map.remove(serviceName);
		}
		map.put(serviceName, newResult);

		// Ask to update the result view (through observers)
		update(null, getWidth(newResult));
	}

	/**
	 * Build the list of available reports {@link IReport}.<br>
	 * Those are contributed thanks to the extension point SERVICE_EXTENSION
	 */
	private void buildServicesList() {
		services = new HashMap<String, IReport>();

		// Fill the list
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement element : reg.getConfigurationElementsFor(EXTENSION_POINT_ID)) {
			String service = element.getAttribute(SERVICE_EXTENSION);
			try {
				IReport report = (IReport) element.createExecutableExtension(CLASS_EXTENSION);
				services.put(service, report);
				LOGGER.config("Add report/result handler: " + service); //$NON-NLS-1$
			} catch (CoreException e) {
				// Ooops something went wrong...
				LOGGER.warning("Problem with the report/result extension: " + service); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Return the number of elements for each sub-tree.
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

	/**
	 * {@inheritDoc}
	 */
	public final Map<Integer, List<String>> getAttributesOutline() {
		return null;
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
	public final void setTips(Map<Integer, List<ITip>> map, Integer... objectIds) {
		throw new UnsupportedOperationException();
	}
}
