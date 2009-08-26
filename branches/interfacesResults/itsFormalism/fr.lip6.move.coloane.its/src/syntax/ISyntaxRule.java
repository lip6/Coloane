package syntax;

import syntax.tpn.Result;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.model.IElement;

public interface ISyntaxRule {

	/** Check this rule on the provided element.
	 * 
	 * @param elt the elt to check
	 * @param result the result object to add any syntax errors to.
	 * @return
	 */
	boolean check (IElement elt, Result result);
	
	/** The name of this rule.
	 * e.g. "name uniqueness"
	 * 
	 * @return the name of this rule.
	 */
	String getName ();
	
	/** The element type this rule applies to.
	 * e.g. "inhibitor Arc"
	 */
	Iterable<IElementFormalism> getRuleTypes();

}
