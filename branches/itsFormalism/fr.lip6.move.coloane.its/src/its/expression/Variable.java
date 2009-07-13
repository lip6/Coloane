package its.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Variable implements IntegerExpression, IVariable {

	private String name;

	public Variable(String name) {
		this.name = name;
	}
	
	@Override
	public int evaluate(EvaluationContext context) {
		return context.getVariableValue(this);
	}

	@Override
	public List<IntegerExpression> getChildren() {
		return new ArrayList<IntegerExpression>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Set<IVariable> supportingVariables() {
		Set<IVariable> set= new HashSet<IVariable>();
		set.add(this);
		return set;
	}

}
