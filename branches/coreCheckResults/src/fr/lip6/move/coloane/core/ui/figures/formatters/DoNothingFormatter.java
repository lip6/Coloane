package fr.lip6.move.coloane.core.ui.figures.formatters;

import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;
import fr.lip6.move.coloane.interfaces.model.IElement;

public class DoNothingFormatter implements IAttributeFormatter {
	
	/**
	 * {@inheritDoc}
	 */
	public String applyFormat(String value, IElement parentElement) {
		return value;
	}

}
