package fr.lip6.move.coloane.core.motor.formalism;

import fr.lip6.move.coloane.core.motor.formalism.*;

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
	 * Retoune l'dentifiant de l'element en entrée 
	 * @return id
	 */
	int getIdIn();

	/**
	 * Retoune l'dentifiant de l'element en entrée 
	 * @return id
	 */
	int getIdOut();
	
}


