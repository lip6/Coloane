package fr.lip6.move.coloane.projects.its.expression;

/**
 * A multiplication
 * @author Yann
 *
 */
public final class Mult extends NaryExpression {

	/**
	 * {@inheritDoc}
	 */
	public int evaluate(IEvaluationContext context) {
		int total = 0;
		for (IntegerExpression expr : getChildren()) {
			total *= expr.evaluate(context);
		}
		return total;
	}

}
