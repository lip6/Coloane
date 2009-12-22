package fr.lip6.move.coloane.projects.its.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An integer literal constant.
 * @author Yann
 *
 */
public final class Constant implements IntegerExpression {

	private int value;

	/**
	 * Ctor.
	 * @param value the value
	 */
	public Constant(int value) {
		this.value = value;
	}
	/**
	 * {@inheritDoc}
	 */
	public int evaluate(IEvaluationContext context) {
		return value;
	}
	/**
	 * {@inheritDoc}
	 */
	public List<IntegerExpression> getChildren() {
		return new ArrayList<IntegerExpression>();
	}

	/**
	 * {@inheritDoc}
	 */
	public Set<IVariable> supportingVariables() {
		return new HashSet<IVariable>();
	}
}
