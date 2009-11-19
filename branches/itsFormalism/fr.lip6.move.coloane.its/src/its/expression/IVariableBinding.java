package its.expression;

/**
 * Represents a pair variable/value, possibly unset as reflected by null values.
 * @author Yann
 *
 */
public interface IVariableBinding {
	/**
	 * The variable name
	 * @return the name
	 */
	String getVariableName();
	/**
	 * The integer value of the variable or null if not set.
	 * @return the value
	 */
	Integer getVariableValue();
	/**
	 * Update variable value (and possibly notify listeners)
	 * @param value new value or null to unset
	 */
	void setVariableValue(Integer value);
}
