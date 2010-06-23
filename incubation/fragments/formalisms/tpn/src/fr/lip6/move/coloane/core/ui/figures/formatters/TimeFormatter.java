package fr.lip6.move.coloane.core.ui.figures.formatters;

import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.text.DecimalFormatSymbols;

public class TimeFormatter implements IAttributeFormatter {

	public String applyFormat(String value, IElement parentElement) {
		String earlyValue = parentElement.getAttribute("earliestFiringTime").getValue();
		String latestValue = parentElement.getAttribute("latestFiringTime").getValue();
		
		if (latestValue.equalsIgnoreCase("inf")) {
			latestValue = new DecimalFormatSymbols().getInfinity();
		}
		
		if (!earlyValue.equals("") && !latestValue.equals("")) {
			return "[" + earlyValue + "," + latestValue + "]"; 
		}
		return null;
	}

}
