package its.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class NaryExpression implements IntegerExpression {

	List<IntegerExpression> children = new ArrayList<IntegerExpression>();
	
	public List<IntegerExpression> getChildren() {
		return children;
	}

	@Override
	public Set<IVariable> supportingVariables() {
		Set<IVariable> set = new HashSet<IVariable>();		
		for (IntegerExpression expr : getChildren()) {
			set.addAll(expr.supportingVariables());
		}
		return set;
	}
}
