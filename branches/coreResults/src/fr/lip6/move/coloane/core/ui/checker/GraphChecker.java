package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.IGraphChecker;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Wrapper defining a complete graph checker.
 * It contains :<ul>
 * <li>an {@link IGraphChecker} : condition to create a marker</li>
 * <li>the marker message</li>
 * <li>the marker severity</li>
 * </ul>
 * The {@link GraphChecker#check(IGraph)} method check the given {@link IGraph}.
 * If the method return true, a marker has to be created and the marker message is obtained by {@link GraphChecker#getMessage()} and it severity by {@link GraphChecker#getSeverity()}.
 * @author Florian David
 */
public class GraphChecker {
	/** @see IGraphChecker */
	private IGraphChecker checker;
	/** The marker severity. */
	private Integer severity;
	/** The marker severity. */
	private String message;

	/**
	 * Construct a graph checker.
	 * @param checker the {@link IGraphChecker} condition to create the marker.
	 * @param message the marker message.
	 * @param severity the marker severity.
	 */
	GraphChecker(IGraphChecker checker, String message, Integer severity) {
		this.severity = severity;
		this.checker = checker;
		this.message = message;
	}

	/**
	 * Getter on marker severity.
	 * @return the marker severity.
	 */
	public Integer getSeverity() {
		return severity;
	}

	/**
	 * Getter on marker message.
	 * @return the marker message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Call the {@link IGraphChecker#check(IGraph)} method of the checker on an {@link IGraph}.
	 * @param graph the {@link IGraph} to check.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public boolean check(IGraph graph) {
		return checker.check(graph);
	}
}
