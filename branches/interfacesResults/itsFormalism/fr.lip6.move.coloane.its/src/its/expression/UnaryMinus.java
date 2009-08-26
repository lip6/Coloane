package its.expression;

public class UnaryMinus extends NaryExpression {

	public UnaryMinus(IntegerExpression expr) {
		getChildren().add(expr);
	}
	
	@Override
	public int evaluate(EvaluationContext context) {
		assert getChildren().size() == 1;
		return - getChildren().iterator().next().evaluate(context);
	}

}
