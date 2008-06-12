package fr.lip6.move.coloane.core.motor.formalism.permissionRule;

import fr.lip6.move.coloane.core.motor.formalism.*;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

public interface IRule {
		
	/**
	 * Retour de l'element en entrée
	 */
	ElementFormalism getElementIn();

	/**
	 * Retour de l'element en entrée
	 */
	ElementFormalism getElementOut();

	/**	
	 * Methode qui verifie la possibilité de relier deux elements
	 * @param eltIn : est l'element en entrée
	 * @param eltOut: est l'element en sortie
	*/
	
	boolean canConnect(INodeImpl eltIn,INodeImpl eltOut);

}


