package fr.lip6.move.coloane.fragments.formalisms.snb.checker;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

public class PlaceEmptyName implements IAttributeChecker {

	public PlaceEmptyName() {
	}

	public boolean performCheck(String value) {
		return !value.equals("");
	}

}
