package fr.lip6.move.coloane.projects.its.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Base abstract class for N-ary expressions
 * @author Yann
 *
 */
public abstract class NaryExpression implements IntegerExpression {

	private List<IntegerExpression> children = new ArrayList<IntegerExpression>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IntegerExpression> getChildren() {
		return children;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<IVariable> supportingVariables() {
		Set<IVariable> set = new HashSet<IVariable>();
		for (IntegerExpression expr : getChildren()) {
			set.addAll(expr.supportingVariables());
		}
		return set;
	}
}
