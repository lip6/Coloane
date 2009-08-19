package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

public class AttributeChecker {

	private IAttributeChecker checker;
	private Integer severity;
	private String message;
	
	AttributeChecker(IAttributeChecker checker, String message, Integer severity) {
		this.severity = severity;
		this.checker = checker;
		this.message = message;
	}
	
	public Integer getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}	
	
	public boolean check(String value) {
		return checker.check(value);
	}
}
