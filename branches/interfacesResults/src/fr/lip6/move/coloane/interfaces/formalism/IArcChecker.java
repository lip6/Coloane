package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Define a method which check some conditions defined by the user on an {@link IArc}.
 * User <b>must only</b> test the arc and <b>must not</b> test anything else (included arc attributes).
 * An empty public constructor <b>must</b> be available.
 * For attributes, see {@link IAttributeChecker}.
 * @author Florian David
 */
public interface IArcChecker {
	/**
	 * Method implemented by the user who wants to check some conditions on an {@link IArc}.
	 * @param arc the arc to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	boolean check(IArc arc);
}
