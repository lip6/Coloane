package fr.lip6.move.coloane.projects.its.expression;

import java.util.Collections;
import java.util.List;

public class ExpressionParseResult {

	private final IntegerExpression expression;
	private final List<String> errors;
	public IntegerExpression getExpression() {
		return expression;
	}
	@SuppressWarnings("unchecked")
	public List<String> getErrors() {
		if (errors == null)
			return Collections.EMPTY_LIST;
		return errors;
	}
	public ExpressionParseResult(IntegerExpression expression,
			List<String> errors) {
		this.expression = expression;
		this.errors = errors;
	}
	public int getErrorCount() {
		if (errors == null)
			return 0;
		return errors.size();
	}
	
	
}
