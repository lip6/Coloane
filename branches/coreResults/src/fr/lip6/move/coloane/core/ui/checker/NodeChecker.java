package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

public class NodeChecker {

	private INodeChecker checker;
	private Integer severity;
	private String message;
	
	NodeChecker(INodeChecker checker, String message, Integer severity) {
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
	
	public boolean check(INode node) {
		return checker.check(node);
	}
}
