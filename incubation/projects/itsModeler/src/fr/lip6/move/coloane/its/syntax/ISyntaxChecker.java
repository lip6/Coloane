package fr.lip6.move.coloane.its.syntax;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;


/** Defines an interface for a formalism specific syntax checker. */
public interface ISyntaxChecker {

	/** The main entry point for syntax checking.
	 * 
	 * @param graph the graph to check
	 * @return an IResult to feed the results view
	 */
	IResult check(IGraph graph);

	/** Return the formalism this syntax checker applies to.
	 * 
	 * @return the formalism this syntax checker is designed to check
	 */
	String getFormalism();

	/** Add a syntax check rule to this Syntax checker
	 * 
	 * @param rule the rule to add.
	 */
	void addRule(ISyntaxRule rule);

}
