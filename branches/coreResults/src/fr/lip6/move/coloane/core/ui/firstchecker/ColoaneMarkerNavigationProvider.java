package fr.lip6.move.coloane.core.ui.firstchecker;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.common.ui.services.marker.AbstractMarkerNavigationProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class ColoaneMarkerNavigationProvider extends AbstractMarkerNavigationProvider {

	//public static String ROOT_MARKER = "fr.lip6.move.coloane.core.ui.marking.rootMarker"; //$NON-NLS-1$
	public static String NODE_MARKER = "fr.lip6.move.coloane.core.ui.marking.nodeMarker"; //$NON-NLS-1$
	public static String NODE_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.marking.nodeAttributeMarker"; //$NON-NLS-1$
	public static String ARC_MARKER = "fr.lip6.move.coloane.core.ui.marking.arcMarker"; //$NON-NLS-1$
	public static String ARC_ATTRIBUTE_MARKER = "fr.lip6.move.coloane.core.ui.marking.arcAttributeMarker"; //$NON-NLS-1$
	public static String ID = "id"; //$NON-NLS-1$

	public static void addMarker(String type, String message, IElement element, int severity) {
		IPath path = new Path(SessionManager.getInstance().getCurrentSession().getName());
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
		
		if (element == null) {
			System.out.println("Element NULL");
			return;
		}
		
		if (severity != IMarker.SEVERITY_ERROR && severity != IMarker.SEVERITY_WARNING && severity != IMarker.SEVERITY_INFO) {
			System.out.println("Severity incorrecte");
			return;
		}
		
		try {
			IMarker marker = resource.createMarker(type);

			Map<String,Object> markerMap = new HashMap<String, Object>(4);
			markerMap.put(ID, element.getId());
			markerMap.put(IMarker.LOCATION, "Not available"); //$NON-NLS-1$
			markerMap.put(IMarker.SEVERITY, severity);
			markerMap.put(IMarker.MESSAGE, message);
			marker.setAttributes(markerMap);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void deleteElementMarkers(String type, IElement element) {
		IPath path = new Path(SessionManager.getInstance().getCurrentSession().getName());
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
		
		if (element == null) {
			System.out.println("Element NULL");
			return;
		}
		
		try {
			IMarker[] markers = resource.findMarkers(type, false, IResource.DEPTH_ZERO);
			
			for (IMarker marker : markers) {
				Integer id = (Integer) marker.getAttribute(ID,-1);
				if (id == element.getId())
					marker.delete();	
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGotoMarker(IMarker marker) {
		// We get the IElement id associated to the marker
		Integer objectId = -1;
		try {
			objectId = (Integer) marker.getAttribute(ID); //$NON-NLS-1$
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// If no id, there is no element to highlight in the IGraph
		if (objectId == -1)
			return;
		
		// Then we get the IElement
		IGraph currentGraph = SessionManager.getInstance().getCurrentSession().getGraph();
		IElement element = currentGraph.getArc(objectId);
		if (element == null)
			element = currentGraph.getNode(objectId);

		// If the IElement isn't null, it will be highlight in the IGraph
		if (element != null) {
			try {
				IEditorPart editor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) marker.getResource());
				GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
				viewer.deselectAll();
				viewer.appendSelection((EditPart) viewer.getEditPartRegistry().get(element));
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
