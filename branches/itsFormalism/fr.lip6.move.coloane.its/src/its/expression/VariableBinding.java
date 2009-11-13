package its.expression;

public class VariableBinding implements IVariableBinding {

	private EvaluationContext context;
	private IVariable var;

	public VariableBinding(IVariable var, EvaluationContext evaluationContext) {
		this.context = evaluationContext;
		this.var = var;
	}

	@Override
	public String getVariableName() {
		return var.getName();
	}

	@Override
	public Integer getVariableValue() {
		return context.getVariableValue(var);
	}

	@Override
	public void setVariableValue(Integer value) {
		context.setVariableValue(var, value);
	}
	

}
