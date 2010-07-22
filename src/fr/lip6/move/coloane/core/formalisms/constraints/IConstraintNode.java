package fr.lip6.move.coloane.core.formalisms.constraints;

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Interface définissant le principe de contrainte pour un noeud.<br>
 * Chaque contrainte de noeud doit définir une méthode {@link #isSatisfied(INode)}
 * qui retourne <code>true</code> si la contrainte est vérifiée (satisfaite) ou <code>false</code> dans le
 * cas inverse.
 *
 * @author Jean-Baptiste Voron
 *
 */
public interface IConstraintNode {

	/**
	 * Est-ce que la contrainte est satisfaite ?
	 * @param node Le noeud qui doit être testé et vérifié
	 * @return <code>true</code> si l'action est possible. <code>false</code> sinon.
	 */
	boolean isSatisfied(INode node);
}
