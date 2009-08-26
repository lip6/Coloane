package its.expression;

public class Add extends NaryExpression implements IntegerExpression {

	@Override
	public int evaluate(EvaluationContext context) {
		int total = 0;
		for (IntegerExpression expr : getChildren()) {
			total += expr.evaluate(context);
		}
		return total;
	}

}
