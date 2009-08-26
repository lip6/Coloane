package fr.lip6.move.coloane.core.ui.firstchecker;


import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

public class MarkerResolutionGenerator implements IMarkerResolutionGenerator {

	public IMarkerResolution[] getResolutions(IMarker marker) {
		 try {
	            Object problem = marker.getAttribute("WhatsUp"); //$NON-NLS-1$
	            
	            return new IMarkerResolution[] {
	               new QuickFix("Fix #1 for "+problem), //$NON-NLS-1$
	               new QuickFix("Fix #2 for "+problem), //$NON-NLS-1$
	            };
	         }
	         catch (CoreException e) {
	            return new IMarkerResolution[0];
	         }

	}

}
