package its.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Infinity implements IntegerExpression {

	@Override
	public int evaluate(IEvaluationContext context) {
		return Integer.MAX_VALUE;
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
