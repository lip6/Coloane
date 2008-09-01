package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

/**
 * Cette interface doit être implémentée par les classes qui gèrent
 * des résultats de services en provenance de FrameKit. Une seule méthode
 * doit être définie : {@link #build(IResultsCom)}
 *
 * @author Clement Demoulins
 */
public interface IReport {
	/**
	 * Construction de l'arbre des résultats qui seront affiches dans la fenetre "resultats"
	 * @param result Objet contenant les données brutes en provenance de la Com
	 * @return Arbre des résultats
	 */
	ResultTreeImpl build(IResult result);
}
