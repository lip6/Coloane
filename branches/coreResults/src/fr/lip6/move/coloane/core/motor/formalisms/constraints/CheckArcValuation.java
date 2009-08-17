package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.core.ui.checker.ColoaneMarkerNavigationProvider;
import fr.lip6.move.coloane.interfaces.formalism.IArcAttributeChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.core.resources.IMarker;

public class CheckArcValuation implements IArcAttributeChecker {
	private Integer valMin;
	
	private Integer valMax;

	private String name;
	
	public CheckArcValuation(Integer valMin, Integer valMax) {
		this.valMin = valMin;
		this.valMax = valMax;
	}


	public void arcAttributeCheck(IArc arc, IAttribute attribute) {
		if (attribute.getName().equals("valuation")) {
			Integer valuation = Integer.parseInt(attribute.getValue());
			if (valuation < valMin || valuation > valMax)
				ColoaneMarkerNavigationProvider.addMarker(ColoaneMarkerNavigationProvider.ARC_ATTRIBUTE_MARKER, "La valuation doit être située entre "+valMin+" et "+valMax+".", arc, IMarker.SEVERITY_WARNING);
		}
	}
}