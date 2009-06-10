package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Interface définissant le principe de contrainte pour un lien.<br>
 * Chaque contrainte de lien doit définir une méthode {@link #isSatisfied(INode, INode)}
 * qui retourne <code>true</code> si la contrainte est vérifiée (satisfaite) ou <code>false</code> dans le
 * cas inverse.
 *
 * @author Jean-Baptiste Voron
 *
 */
public interface IConstraintLink {

	/**
	 * Est-ce que la contrainte est satisfaite ?
	 * @param source Le noeud <b>source</b> de l'arc a tester
	 * @param target Le noeud <b>cible</b> de l'arc a tester
	 * @return <code>true</code> si la connexion est possible. <code>false</code> sinon.
	 */
	boolean isSatisfied(INode source, INode target);

}
