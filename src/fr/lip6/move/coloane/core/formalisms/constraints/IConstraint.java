package fr.lip6.move.coloane.core.formalisms.constraints;

/**
 * Une contrainte est attachée à un formalisme. <br>
 * Elle concerne 1 ou plusieurs élément de ce formalisme.<br>
 *
 * Elle peut être de type : <b>lien</b> ou <b>noeud</b>.
 * Elle implémente donc :
 * <ul>
 * 	<li>{@link IConstraintLink}</li>
 * 	<li>{@link IConstraintNode}</li>
 * </ul>
 */
public interface IConstraint {

	String PARAMETER_ID = "parameter"; //$NON-NLS-1$
	String PARAMETER_NAME = "name"; //$NON-NLS-1$
	String PARAMETER_VALUE = "value"; //$NON-NLS-1$

	/**
	 * @return Le nom de la contrainte (pour être affichée)
	 */
	String getName();
}
