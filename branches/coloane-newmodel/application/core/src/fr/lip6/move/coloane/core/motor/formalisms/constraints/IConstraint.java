package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.interfaces.IElementFormalism;

/**
 * Interface définissant le principe de contrainte.<br>
 * Une contrainte est attachée à un formalisme. Elle concerne 1 ou plusieurs élément de ce formalisme.<br>
 * Chaque contrainte doit définir une méthode {@link #isSatisfied(ElementFormalism, ElementFormalism)}
 * qui retourne <code>true</code> si la contrainte est vérifiée (satisfaire) ou <code>false</code> dans le
 * cas inverse.
 * 
 * @author Jean-Baptiste Voron
 *
 */
public interface IConstraint {
	
	boolean isSatisfied(IElementFormalism source, IElementFormalism target); 

}
