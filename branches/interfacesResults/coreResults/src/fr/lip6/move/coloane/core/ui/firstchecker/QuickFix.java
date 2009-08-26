package fr.lip6.move.coloane.core.ui.firstchecker;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.ui.IMarkerResolution;

public class QuickFix implements IMarkerResolution {
	 String label;
	 
     public QuickFix(String label) {
        this.label = label;
     }
     
     public String getLabel() {
        return label;
     }
     
     public void run(IMarker marker) {

    	try {
			Integer id = (Integer) marker.getAttribute("id"); //$NON-NLS-1$
			String session = (String) marker.getAttribute("session"); //$NON-NLS-1$

	        INode node = SessionManager.getInstance().getSession(session).getGraph().getNode(id);
	        IAttribute attribut = node.getAttribute("name"); //$NON-NLS-1$
	        attribut.setValue(node.getNodeFormalism().getName()+" "+id); //$NON-NLS-1$
	        
	        marker.delete();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
     
}
