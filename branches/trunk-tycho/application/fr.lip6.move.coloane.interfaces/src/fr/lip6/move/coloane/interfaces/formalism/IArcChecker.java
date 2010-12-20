package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Define a method which checks some conditions defined by the user about an {@link IArc}.<br>
 * User <b>must only</b> tests the arc and <b>must not</b> tests anything else (included arc attributes).<br>
 * For attributes checking concerns, see {@link IAttributeChecker}.
 *
 * @author Florian David
 */
public interface IArcChecker {
	/**
	 * Method that has to be implemented by the user who wants to check some conditions on an {@link IArc}.
	 * @param arc the arc to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	boolean performCheck(IArc arc);
}
