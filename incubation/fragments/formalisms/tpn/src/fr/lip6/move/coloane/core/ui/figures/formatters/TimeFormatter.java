package fr.lip6.move.coloane.core.ui.figures.formatters;

import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.text.DecimalFormatSymbols;

public class TimeFormatter implements IAttributeFormatter {

	public String applyFormat(String value, IElement parentElement) {
		String earlyValue = parentElement.getAttribute("earliestFiringTime").getValue();
		String latestValue = parentElement.getAttribute("latestFiringTime").getValue();
		String paren = "]";
		if (latestValue.equalsIgnoreCase("inf")) {
			latestValue = new DecimalFormatSymbols().getInfinity();
			paren = "[";
		}
		
		if (!earlyValue.equals("") && !latestValue.equals("")) {
			return "[" + earlyValue + "," + latestValue + paren; 
		}
		return null;
	}

}
