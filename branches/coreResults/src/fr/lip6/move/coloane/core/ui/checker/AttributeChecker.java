package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

/**
 * Wrapper defining a complete attribute checker.
 * It contains :<ul>
 * <li>an {@link IAttributeChecker} : condition to create a marker</li>
 * <li>the marker message</li>
 * <li>the marker severity</li>
 * </ul>
 * The {@link AttributeChecker#check(String)} method check the given value of the {@link IAttribute} defined in the extension point.
 * If the method return true, a marker has to be created and the marker message is obtained by {@link AttributeChecker#getMessage()} and it severity by {@link AttributeChecker#getSeverity()}.
 * @author Florian David
 */
public class AttributeChecker {
	/** @see IAttributeChecker */
	private IAttributeChecker checker;
	/** The marker severity. */
	private Integer severity;
	/** The marker severity. */
	private String message;
	
	/**
	 * Construct an attribute checker.
	 * @param checker the {@link IAttributeChecker} condition to create the marker.
	 * @param message the marker message.
	 * @param severity the marker severity.
	 */
	AttributeChecker(IAttributeChecker checker, String message, Integer severity) {
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
	 * Call the {@link IAttributeChecker#check(String)} method of the checker on a given value.
	 * @param value the value to check. It can be parsed but beware of {@link NumberFormatException}.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public final boolean check(String value) {
		return checker.check(value);
	}
}
