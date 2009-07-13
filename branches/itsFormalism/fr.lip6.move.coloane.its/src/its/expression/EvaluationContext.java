package its.expression;

public interface EvaluationContext {

	void setVariableValue (IVariable var, int value) ;
	
	Integer getVariableValue (IVariable var);
}
