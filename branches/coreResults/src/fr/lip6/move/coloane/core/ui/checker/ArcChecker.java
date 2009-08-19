package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;

public class ArcChecker {

	private IArcChecker checker;
	private Integer severity;
	private String message;
	
	ArcChecker(IArcChecker checker, String message, Integer severity) {
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
	
	public boolean check(IArc arc) {
		return checker.check(arc);
	}
}
