package fr.lip6.move.coloane.projects.its.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * the +infinity constant.
 * @author Yann
 *
 */
public final class Infinity implements IntegerExpression {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int evaluate(IEvaluationContext context) {
		return Integer.MAX_VALUE;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IntegerExpression> getChildren() {
		return new ArrayList<IntegerExpression>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IVariable> supportingVariables() {
		return new HashSet<IVariable>();
	}
}
