package fr.lip6.move.coloane.projects.its.expression;

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A basic implem of an {@link IEvaluationContext}. Also implements {@link SimpleObservable}.
 * @author Yann
 *
 */
public final class EvaluationContext extends SimpleObservable implements IEvaluationContext {

	private Map<IVariable, Integer> values;
	private Set<IVariable> variables;
	private List<IVariableBinding> vbs;

	/**
	 * Ctor.
	 */
	public EvaluationContext() {
		variables = new HashSet<IVariable>();
		values = new HashMap<IVariable, Integer>();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getVariableValue(IVariable var) {
		return values.get(var);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVariableValue(IVariable var, Integer value) {
		if (!variables.contains(var)) {
			declareVariable(var);
		}
		if (values.get(var) != value) {
			values.put(var, value);
			notifyObservers();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void declareVariable(IVariable var) {
		variables.add(var);
		vbs = null;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IVariable> getVariables() {
		return variables;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<IVariableBinding> getBindings() {
		if (vbs == null) {
			vbs = new ArrayList<IVariableBinding>();

			for (IVariable var : getVariables()) {
				vbs.add(new VariableBinding(var, this));
			}
		}
		return vbs;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsVariable(IVariable var) {
		return variables.contains(var);
	}

	public void clear() {
		values.clear();
		vbs.clear();
		variables.clear();
	}

}
