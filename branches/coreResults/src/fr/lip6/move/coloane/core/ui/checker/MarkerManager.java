package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.common.ui.services.marker.AbstractMarkerNavigationProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * Marker manager.
 * 
 * @author Florian David
 */
public class MarkerManager extends AbstractMarkerNavigationProvider {
	/** ID of the root marker. */
	private static String ROOT_MARKER = "fr.lip6.move.coloane.core.ui.checker.rootMarker"; //$NON-NLS-1$
	/** ID of the node marker. */
	private static String NODE_MARKER = "fr.lip6.move.coloane.core.ui.checker.nodeMarker"; //$NON-NLS-1$
	/** ID of the node attribute marker. */
	private static String NODE_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.checker.nodeAttributeMarker"; //$NON-NLS-1$
	/** ID of the arc marker. */
	private static String ARC_MARKER = "fr.lip6.move.coloane.core.ui.checker.arcMarker"; //$NON-NLS-1$
	/** ID of the arc attribute marker. */
	private static String ARC_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.checker.arcAttributeMarker"; //$NON-NLS-1$
	/** ID of the graph marker. */
	private static String GRAPH_MARKER = "fr.lip6.move.coloane.core.ui.checker.graphMarker"; //$NON-NLS-1$
	/** ID of the graph attribute marker. */
	private static String GRAPH_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.checker.graphAttributeMarker"; //$NON-NLS-1$
	/** ID the marker attribute "ID". Used to keep the IElement id with which the marker is linked. */
	private static String ID = "id"; //$NON-NLS-1$

	/** Constructor */
	public MarkerManager() { }

	/** 
	 * Create one marker.
	 * @param resource the resource file where markers are created.
	 * @param type kind of marker to create.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	private static void createMarker(IResource resource, String type, String message, IElement element, Integer severity) {
		try {
			IMarker marker = resource.createMarker(type);

			Map<String, Object> markerMap = new HashMap<String, Object>(4);
			markerMap.put(ID, element.getId());
			markerMap.put(IMarker.LOCATION, "Not available"); //$NON-NLS-1$
			markerMap.put(IMarker.SEVERITY, severity);
			markerMap.put(IMarker.MESSAGE, message);
			marker.setAttributes(markerMap);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * Create one node marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public static void createNodeMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, NODE_MARKER, message, element, severity);
	}

	/** 
	 * Create one node attribute marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public static void createNodeAttributeMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, NODE_ATTRIBUTE_MARKER, message, element, severity);
	}

	/** 
	 * Create one arc marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public static void createArcMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, ARC_MARKER, message, element, severity);
	}

	/** 
	 * Create one arc attribute marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public static void createArcAttributeMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, ARC_ATTRIBUTE_MARKER, message, element, severity);
	}

	/** 
	 * Create one graph marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public static void createGraphMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, GRAPH_MARKER, message, element, severity);
	}

	/** 
	 * Create one graph attribute marker.
	 * @param resource the resource file where markers are created.
	 * @param message marker message.
	 * @param element the marker will be associated to this graph element.
	 * @param severity marker severity.
	 */
	public static void createGraphAttributeMarker(IResource resource, String message, IElement element, Integer severity) {
		createMarker(resource, GRAPH_ATTRIBUTE_MARKER, message, element, severity);
	}
	
	/**
	 * Delete all markers from a file.
	 * @param resource file from where markers are deleted.
	 */
	public static void deleteAllMarkers(IResource resource) {
		try {
			resource.deleteMarkers(ROOT_MARKER, true, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			e.printStackTrace();
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
	}
	
	/**
	 * Delete node markers associated to the node.
	 * @param resource the resource file from where markers are deleted.
	 * @param element marker associated to this element will be deleted.
	 */
	public static void deleteNodeMarkers(IResource resource, IElement element) {
		deleteMarkers(resource, NODE_MARKER, element);
	}

	/**
	 * Delete node attribute markers associated to the node.
	 * @param resource the resource file from where markers are deleted.
	 * @param element marker associated to this element will be deleted.
	 */
	public static void deleteNodeAttributeMarkers(IResource resource, IElement element) {
		deleteMarkers(resource, NODE_ATTRIBUTE_MARKER, element);
	}

	/**
	 * Delete arc markers associated to the arc.
	 * @param resource the resource file from where markers are deleted.
	 * @param element marker associated to this element will be deleted.
	 */
	public static void deleteArcMarkers(IResource resource, IElement element) {
		deleteMarkers(resource, ARC_MARKER, element);
	}
	
	/**
	 * Delete arc attribute markers associated to the arc.
	 * @param resource the resource file from where markers are deleted.
	 * @param element marker associated to this element will be deleted.
	 */
	public static void deleteArcAttributeMarkers(IResource resource, IElement element) {
		deleteMarkers(resource, ARC_ATTRIBUTE_MARKER, element);
	}
	
	/**
	 * Delete graph markers associated to the graph.
	 * @param resource the resource file from where markers are deleted.
	 */
	public static void deleteGraphMarkers(IResource resource) {
		deleteMarkers(resource, GRAPH_MARKER, null);
	}
	
	/**
	 * Delete graph attribute markers.
	 * @param resource the resource file from where markers are deleted.
	 */
	public static void deleteGraphAttributeMarkers(IResource resource) {
		deleteMarkers(resource, GRAPH_ATTRIBUTE_MARKER, null);
	}
	
	/** {@inheritDoc} */
	@Override
	protected final void doGotoMarker(IMarker marker) {
		// We get the IElement id associated to the marker
		Integer objectId = -1;
		try {
			objectId = (Integer) marker.getAttribute(ID);
		} catch (CoreException e) {
			e.printStackTrace();
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
				viewer.appendSelection((EditPart) viewer.getEditPartRegistry().get(element));
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
}
