package fr.lip6.move.coloane.projects.its.expression;

/**
 * An addition.
 * @author Yann
 *
 */
public final class Add extends NaryExpression implements IntegerExpression {

	/**
	 * {@inheritDoc}
	 */
	public int evaluate(IEvaluationContext context) {
		int total = 0;
		for (IntegerExpression expr : getChildren()) {
			total += expr.evaluate(context);
		}
		return total;
	}

}
