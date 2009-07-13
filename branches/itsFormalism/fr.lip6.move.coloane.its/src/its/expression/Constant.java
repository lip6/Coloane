package its.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Constant implements IntegerExpression {

	private int value;

	public Constant(int value) {
		this.value = value;
	}
	@Override
	public int evaluate(EvaluationContext context) {
		return value;
	}
	@Override
	public List<IntegerExpression> getChildren() {
		return new ArrayList<IntegerExpression>();
	}

	@Override
	public Set<IVariable> supportingVariables() {
		return new HashSet<IVariable>();
	}
}
