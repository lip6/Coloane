package its.expression;

import java.util.List;
import java.util.Set;

public interface IntegerExpression {

	List<IntegerExpression> getChildren();
	
	int evaluate (EvaluationContext context);
	
	Set<IVariable> supportingVariables ();
}
