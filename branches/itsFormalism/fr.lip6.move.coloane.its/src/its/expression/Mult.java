package its.expression;

public class Mult extends NaryExpression {

	@Override
	public int evaluate(EvaluationContext context) {
		int total = 0;
		for (IntegerExpression expr : getChildren()) {
			total *= expr.evaluate(context);
		}
		return total;
	}

}
