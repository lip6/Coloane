package fr.lip6.move.coloane.core.formalisms.constraints;

/**
 * Constraint.<br>
 * There exist two kind of constraints:
 * <ul>
 * 	<li>{@link IConstraintLink}</li>
 * 	<li>{@link IConstraintNode}</li>
 * </ul>
 * 
 * @author Jean-Baptiste Voron
 */
public interface IConstraint {

	String PARAMETER_ID = "parameter"; //$NON-NLS-1$
	String PARAMETER_NAME = "name"; //$NON-NLS-1$
	String PARAMETER_VALUE = "value"; //$NON-NLS-1$

}
