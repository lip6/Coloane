package its.expression;

import its.obs.SimpleObservable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationContext extends SimpleObservable implements IEvaluationContext {

	private Map<IVariable,Integer> values;
	private Set<IVariable> variables;
	List<IVariableBinding> vbs;

	public EvaluationContext() {
		variables = new HashSet<IVariable>();
		values = new HashMap<IVariable, Integer>();
	}
	@Override
	public Integer getVariableValue(IVariable var) {
		return values.get(var);
	}

	@Override
	public void setVariableValue(IVariable var, Integer value) {
		if (!variables.contains(var)) {
			declareVariable(var);
		}
		if (values.get(var) != value) {
			values.put(var,value);
			notifyObservers();
		}
	}
	@Override
	public void declareVariable(IVariable var) {
		variables.add(var);
		vbs = null;
	}
	@Override
	public Set<IVariable> getVariables() {
		return variables;
	}
	@Override
	public Collection<IVariableBinding> getBindings() {
		if (vbs == null) {
			vbs = new ArrayList<IVariableBinding>();
		
			for (IVariable var : getVariables()) {
				vbs.add(new VariableBinding(var,this));
			}
		}
		return vbs;
	}

}
