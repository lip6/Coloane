package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

/**
 * A place/transition should have a name
 * 
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public class NameChecker implements IAttributeChecker {

	/**
	 * {@inheritDoc}
	 */
	public boolean performCheck(String value) {
		return !value.isEmpty();
	}
}
