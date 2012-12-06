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
package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * Marker Manager.<br>
 * It manages all markers operations :
 * <ul>
 * 	<li>Create markers</li>
 * 	<li>Delete markers</li>
 *  <li>Provide navigation from the marker to the correct graph element</li>
 * </ul>
 *
 * @author Florian David
 */
public final class MarkerManager {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The id of the eclipse property view (to force focus on gotoMarker) */
	private static final String PROPERTY_VIEW_ID = "org.eclipse.ui.views.PropertySheet"; //$NON-NLS-1$

	/** ID of the root marker. */
	private static final String ROOT_MARKER = "fr.lip6.move.coloane.core.ui.checker.rootMarker"; //$NON-NLS-1$
	/** ID of the node marker. */
	private static final String NODE_MARKER = "fr.lip6.move.coloane.core.ui.checker.nodeMarker"; //$NON-NLS-1$
	/** ID of the node attribute marker. */
	private static final String NODE_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.checker.nodeAttributeMarker"; //$NON-NLS-1$
	/** ID of the arc marker. */
	private static final String ARC_MARKER = "fr.lip6.move.coloane.core.ui.checker.arcMarker"; //$NON-NLS-1$
	/** ID of the arc attribute marker. */
	private static final String ARC_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.checker.arcAttributeMarker"; //$NON-NLS-1$
	/** ID of the graph marker. */
	private static final String GRAPH_MARKER = "fr.lip6.move.coloane.core.ui.checker.graphMarker"; //$NON-NLS-1$
	/** ID of the graph attribute marker. */
	private static final String GRAPH_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.checker.graphAttributeMarker"; //$NON-NLS-1$

	/** ID the marker attribute "ID". Used to keep the IElement id with which the marker is linked. */
	private static final String ID = "id"; //$NON-NLS-1$

	/** ATTRIBUTE the marker attribute "ID". Used to keep the IAttribute name with which the marker is linked.(only for attribute markers) */
	private static final String ATTRIBUTE = "attribute"; //$NON-NLS-1$

	/** The instance */
	private static MarkerManager instance;

	/** Constructor */
	private MarkerManager() { }

	/** @return The CheckerManager instance */
	public static synchronized MarkerManager getInstance() {
		if (instance == null) { instance = new MarkerManager(); }
		return instance;
	}

	/**
	 * Create one marker.
	 * @param resource the resource file where markers are created.
	 * @param type kind of marker to create.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 * @return the newly built marker
	 */
	private static IMarker createMarker(IResource resource, String type, String message, IElement element, Integer severity) {
		try {
			IMarker marker = resource.createMarker(type);

			Map<String, Object> markerMap = new HashMap<String, Object>(4);
			markerMap.put(ID, element.getId());
			markerMap.put(IMarker.LOCATION, "Not available"); //$NON-NLS-1$
			markerMap.put(IMarker.SEVERITY, severity);
			markerMap.put(IMarker.MESSAGE, message);
			marker.setAttributes(markerMap);
			return marker;
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Create one node marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public void createNodeMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, NODE_MARKER, message, element, severity);
	}

	/**
	 * Create one node attribute marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 * @param attribute the attribute name
	 */
	public void createNodeAttributeMarker(IResource resource, String message, IElement element, Integer severity, String attribute) {
		IMarker marker = createMarker(resource, NODE_ATTRIBUTE_MARKER, message, element, severity);
		try {
			marker.setAttribute(ATTRIBUTE, attribute);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create one arc marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public void createArcMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, ARC_MARKER, message, element, severity);
	}

	/**
	 * Create one arc attribute marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 * @param attribute the attribute that has a problem
	 */
	public void createArcAttributeMarker(IResource resource, String message, IElement element, Integer severity, String attribute) {
		IMarker marker = createMarker(resource, ARC_ATTRIBUTE_MARKER, message, element, severity);
		try {
			marker.setAttribute(ATTRIBUTE, attribute);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create one graph marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public void createGraphMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, GRAPH_MARKER, message, element, severity);
	}

	/**
	 * Create one graph attribute marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public void createGraphAttributeMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, GRAPH_ATTRIBUTE_MARKER, message, element, severity);
	}

	/**
	 * Delete all markers from a file.
	 * @param resource file from where markers are deleted.
	 */
	public void deleteAllMarkers(IResource resource) {
		try {
			resource.deleteMarkers(ROOT_MARKER, true, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete all markers associated to a specific resource.<br>
	 * This method is especially useful to clean the problem view when the user closes a model
	 * @param resource The resource for which all resources must be deleted
	 */
	public static void deleteMarkers(IResource resource) {
		try {
			resource.deleteMarkers(null, false, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			LOGGER.warning("The resource " + resource.getName() + "does not exist anymore or has a problem...");  //$NON-NLS-1$//$NON-NLS-2$
		}

	}

	/**
	 * Delete markers of a specific type associated to a specific element.
	 * @param resource the resource file from where markers are deleted.
	 * @param type kind of marker that are deleted.
	 * @param element marker associated to this element will be deleted.
	 */
	private static void deleteMarkers(IResource resource, String type, IElement element) {
		if (element != null) {
			try {
				IMarker[] markers = resource.findMarkers(type, false, IResource.DEPTH_ZERO);

				for (IMarker marker : markers) {
					Integer id = (Integer) marker.getAttribute(ID, -1);
					if (id == element.getId()) {
						marker.delete();
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		if (type.equals(GRAPH_MARKER) || type.equals(GRAPH_ATTRIBUTE_MARKER)) {
			try {
				resource.deleteMarkers(type, false, IResource.DEPTH_ZERO);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * Delete IElement attribute markers associated to the IElement.
	 * @param resource the resource file from where markers are deleted.
	 * @param element marker associated to this element's attributes will be deleted.
	 */
	public void deleteElementAttributeMarkers(IResource resource, IElement element) {
		if (element instanceof INode) {
			deleteMarkers(resource, NODE_ATTRIBUTE_MARKER, element);
		} else if (element instanceof IArc) {
			deleteMarkers(resource, ARC_ATTRIBUTE_MARKER, element);
		} else if (element instanceof IGraph) {
			deleteMarkers(resource, GRAPH_ATTRIBUTE_MARKER, element);
		}
	}

	/**
	 * Delete markers associated to the IElement.
	 * @param resource the resource file from where markers are deleted.
	 * @param element marker associated to this element will be deleted.
	 */
	public void deleteElementMarkers(IResource resource, IElement element) {
		if (element instanceof INode) {
			deleteMarkers(resource, NODE_MARKER, element);
		} else if (element instanceof IArc) {
			deleteMarkers(resource, ARC_MARKER, element);
		} else if (element instanceof IGraph) {
			deleteMarkers(resource, GRAPH_MARKER, null);
		}
	}

	/**
	 * Sets the cursor and selection state for an editor to reveal the position of the given marker.
	 * @param marker the marker.
	 */
	public void doGotoMarker(IMarker marker) {
		// We get the IElement id associated to the marker
		Integer objectId = -1;
		try {
			objectId = (Integer) marker.getAttribute(ID);
		} catch (CoreException e) {
			// this may happen if markers are not refreshed correctly.
			// not a problem, no marker to goto anymore !
			return;
		}

		// If the id is still equals to -1, there is no element to highlight in the IGraph
		if (objectId == -1) {
			return;
		}

		// Then we get the IElement
		IGraph currentGraph = SessionManager.getInstance().getCurrentSession().getGraph();
		IElement element = currentGraph.getArc(objectId);
		if (element == null) {
			element = currentGraph.getNode(objectId);
		}
		// If the IElement isn't null, it will be highlight in the IGraph
		if (element != null) {
			try {
				IEditorPart editor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) marker.getResource());
				GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
				viewer.deselectAll();

				// See if we can refine to attribute selection
				String attribute = marker.getAttribute(ATTRIBUTE, null);
				EditPart toFocus = null;
				if (attribute != null) {
					IAttribute attView = element.getAttribute(attribute);
					// Focus on attribute
					// Could return null if this attribute is not visible as an EditPArt
					toFocus = (EditPart) viewer.getEditPartRegistry().get(attView);
				}

				if (toFocus == null) {
					// not resolved as an attribute, resolve to IModelElement
					// default to focus on the Node, Arc or Graph part
					// Should never be null, all nodes have a corresponding EditPart.
					toFocus = (EditPart) viewer.getEditPartRegistry().get(element);
				}
				viewer.appendSelection(toFocus);

				// Force display of Property page, unfortunately, not sure how to focus on a field of it.
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PROPERTY_VIEW_ID);

			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
}
