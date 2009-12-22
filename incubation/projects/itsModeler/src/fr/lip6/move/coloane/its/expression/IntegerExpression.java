package fr.lip6.move.coloane.its.expression;

import java.util.List;
import java.util.Set;

/**
 * A basic Composite + Interpret DP implem for integer arithmetic expressions involving variables.
 * @author Yann
 *
 */
public interface IntegerExpression {

	/**
	 * 
	 * @return children of this expression (can be empty)
	 */
	List<IntegerExpression> getChildren();

	/**
	 * Evaluate in the given context, which should provide values for every variable in the expression.
	 * @param context the context
	 * @return the integer value in this context
	 * throws NullPointerException bad stuff if the context is not OK: variables not set/not found
	 */
	int evaluate(IEvaluationContext context);

	/**
	 * Accessor to test if a context is OK.
	 * @return the set of variables in this expression.
	 */
	Set<IVariable> supportingVariables();
}
