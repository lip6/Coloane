package its.expression;

public class Div extends NaryExpression implements IntegerExpression {

	@Override
	public int evaluate(IEvaluationContext context) {
		int total = 0;
		boolean first = true;
		for (IntegerExpression expr : getChildren()) {
			if (first) {
				total = expr.evaluate(context);
				first = false;
			} else {
				total /= expr.evaluate(context);
			}
		}
		return total;
	}

}
