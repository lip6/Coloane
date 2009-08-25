package fr.lip6.move.coloane.core.ui.checkerCondition;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

public class CheckerNodeAttribute implements IAttributeChecker{

	public boolean check(String value) {
		if ("".equals(value)) //$NON-NLS-1$
			return false;
		return true;
	}

}
