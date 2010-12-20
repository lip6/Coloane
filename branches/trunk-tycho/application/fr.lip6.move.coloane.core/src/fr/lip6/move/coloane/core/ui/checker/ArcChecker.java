package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Wrapper defining a complete arc checker.
 * It contains :<ul>
 * <li>an {@link IArcChecker} : condition to create a marker</li>
 * <li>the marker message</li>
 * <li>the marker severity</li>
 * </ul>
 * 
 * The {@link ArcChecker#check(IArc)} method check the given {@link IArc}.
 * If the method return true, a marker has to be created and the marker message is obtained by {@link ArcChecker#getMessage()} and it severity by {@link ArcChecker#getSeverity()}.
 * 
 * @author Florian David
 */
public class ArcChecker {
	/** @see IArcChecker */
	private IArcChecker checker;
	/** The marker severity */
	private Integer severity;
	/** The marker severity */
	private String message;
	
	/**
	 * Construct a arc checker.
	 * @param checker the {@link IArcChecker} condition to create the marker.
	 * @param message the marker message.
	 * @param severity the marker severity.
	 */
	ArcChecker(IArcChecker checker, String message, Integer severity) {
		this.severity = severity;
		this.checker = checker;
		this.message = message;
	}

	/**
	 * Getter on marker severity.
	 * @return the marker severity.
	 */
	public final Integer getSeverity() {
		return severity;
	}

	/**
	 * Getter on marker message.
	 * @return the marker message.
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * Call the {@link IArcChecker#performCheck(IArc)} method of the checker on an {@link IArc}.
	 * @param arc the {@link IArc} to check.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public final boolean check(IArc arc) {
		return checker.performCheck(arc);
	}
}
