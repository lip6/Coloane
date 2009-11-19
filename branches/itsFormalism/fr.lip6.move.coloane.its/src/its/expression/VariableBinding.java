package its.expression;

/**
 * A class implementing a variable binding built to work with the EvaluationContext implementation.
 * No data is stored, it delegates updates on the context.
 * @author Yann
 *
 */
public final class VariableBinding implements IVariableBinding {

	private EvaluationContext context;
	private IVariable var;

	/**
	 * Create a binding for var, get/set the value in the provided context.
	 * @param var the variable
	 * @param evaluationContext the home context
	 */
	public VariableBinding(IVariable var, EvaluationContext evaluationContext) {
		this.context = evaluationContext;
		this.var = var;
	}


	/**
	 * @return the variable name
	 */
	@Override
	public String getVariableName() {
		return var.getName();
	}

	/**
	 * @return the variable value or null if not set
	 */
	@Override
	public Integer getVariableValue() {
		return context.getVariableValue(var);
	}

	/**
	 * Update the context with new value
	 * @param value the new value or null to unset
	 */
	@Override
	public void setVariableValue(Integer value) {
		context.setVariableValue(var, value);
	}


}
