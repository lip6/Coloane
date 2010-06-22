package fr.lip6.move.coloane.core.ui.figures.formatters;

import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

public class MarkingFormatter implements IAttributeFormatter {
	
	/**
	 * {@inheritDoc}
	 */
	public String applyFormat(String value, IElement parentElement) {
		INode parentNode = (INode) parentElement;
		String currentValue = parentNode.getAttribute("marking").getValue(); //$NON-NLS-1$
		if (!currentValue.equals("")) { //$NON-NLS-1$
			return "["+currentValue+"]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return ""; //$NON-NLS-1$
	}

}
