package its.expression;

import java.util.List;
import java.util.Set;

public interface IntegerExpression {

	List<IntegerExpression> getChildren();
	
	int evaluate (IEvaluationContext context);
	
	Set<IVariable> supportingVariables ();
}
