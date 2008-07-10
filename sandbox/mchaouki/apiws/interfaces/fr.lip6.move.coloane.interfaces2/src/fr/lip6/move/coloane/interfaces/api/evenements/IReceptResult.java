package fr.lip6.move.coloane.interfaces.api.evenements;

import fr.lip6.move.coloane.interfaces.api.objects.result.IResult;

public interface IReceptResult {
	
	/**
	 * Recupere le resultat de l'execution d'un service
	 * @return le resultat de l'execution d'un service
	 */
	public IResult getResult();
}
