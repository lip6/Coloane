package its.expression;

import java.util.Collection;
import java.util.Set;

public interface IEvaluationContext {

	void setVariableValue (IVariable var, Integer value) ;
	
	Integer getVariableValue (IVariable var);
	
	Set<IVariable> getVariables ();

	void declareVariable(IVariable var);
	
	Collection<IVariableBinding> getBindings();

	boolean containsVariable(IVariable var); 
}
