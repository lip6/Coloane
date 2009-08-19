package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.IGraphChecker;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class GraphChecker {

	private IGraphChecker checker;
	private Integer severity;
	private String message;
	
	GraphChecker(IGraphChecker checker, String message, Integer severity) {
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
	
	public boolean check(IGraph graph) {
		return checker.check(graph);
	}
}
