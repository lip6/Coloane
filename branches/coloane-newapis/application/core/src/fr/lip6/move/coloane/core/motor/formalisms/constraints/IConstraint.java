package fr.lip6.move.coloane.core.motor.formalisms.constraints;

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

	/**
	 * @return Le nom de la contrainte (pour être affichée)
	 */
	String getName();
}
