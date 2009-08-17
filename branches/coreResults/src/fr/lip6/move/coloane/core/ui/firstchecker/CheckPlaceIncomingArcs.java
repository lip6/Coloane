package fr.lip6.move.coloane.core.ui.firstchecker;

import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.List;

import org.eclipse.core.resources.IMarker;

public class CheckPlaceIncomingArcs implements INodeChecker {

	public void nodeCheck(INode node) {
		List<IArc> arcList = node.getIncomingArcs();
		
		int nbArcs = 0;
		for (IArc arc : arcList) {
			if (arc.getArcFormalism().getName().equals("arc"))
				nbArcs++;
		}
		if (nbArcs > 0)
			ColoaneMarkerNavigationProvider.addMarker(ColoaneMarkerNavigationProvider.NODE_MARKER, "Il y a "+nbArcs+" arc(s) entrant(s) alors qu'aucun n'est autorisé.", node, IMarker.SEVERITY_ERROR);
	}
}
