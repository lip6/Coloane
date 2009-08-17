package fr.lip6.move.coloane.core.ui.firstchecker;

import fr.lip6.move.coloane.interfaces.model.IElement;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;

public class Marking {
	public static Map<Integer,IMarker> map =  new HashMap<Integer,IMarker>();
	
	public void checkName(IElement ele) {
		/*
		NodeModel element = (NodeModel) ele;

		if ("".equals(element.getAttribute("name").getValue())) { //$NON-NLS-1$ //$NON-NLS-2$
			try {
				Path path = new Path(SessionManager.getInstance().getCurrentSession().getName());
				IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);

				//System.out.println(SessionManager.getInstance().getCurrentSession().getName());
				//System.out.println(path);


				IMarker marker = resource.createMarker("fr.lip6.move.coloane.core.problems.marker"); //$NON-NLS-1$

				marker.setAttribute("id", ele.getId()); //$NON-NLS-1$
				marker.setAttribute(IMarker.LOCATION, "Not available"); //$NON-NLS-1$
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				marker.setAttribute(IMarker.MESSAGE, "Pas de nom."); //$NON-NLS-1$
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
	}	
}