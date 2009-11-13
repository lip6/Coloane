package its.expression;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;

import java.util.Collection;
import java.util.Set;

public interface IEvaluationContext {

	void setVariableValue (IVariable var, Integer value) ;
	
	Integer getVariableValue (IVariable var);
	
	Set<IVariable> getVariables () throws ColoaneException;

	void declareVariable(IVariable var);
	
	Collection<IVariableBinding> getBindings(); 
}
