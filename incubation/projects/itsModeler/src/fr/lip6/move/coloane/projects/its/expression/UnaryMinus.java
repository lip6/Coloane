package fr.lip6.move.coloane.projects.its.expression;

/**
 * unary minus of an expression as in -(3)
 * @author Yann
 *
 */
public final class UnaryMinus extends NaryExpression {

	/**
	 * Ctor.
	 * @param expr child expression
	 */
	public UnaryMinus(IntegerExpression expr) {
		getChildren().add(expr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int evaluate(IEvaluationContext context) {
		assert getChildren().size() == 1;
		return -getChildren().iterator().next().evaluate(context);
	}

}
