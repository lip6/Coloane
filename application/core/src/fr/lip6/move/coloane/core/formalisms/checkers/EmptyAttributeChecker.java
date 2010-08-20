package fr.lip6.move.coloane.core.formalisms.checkers;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

/**
 * Use this checker to check if an attribute has a non empty value.
 * 
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public class EmptyAttributeChecker implements IAttributeChecker {

	/**
	 * {@inheritDoc}
	 */
	public boolean performCheck(String value) {
		return !value.isEmpty();
	}
}
