package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

public interface IReport {
	/**
	 * Construction de l'arbre des résultats qui seront affiches dans la fenetre "resultats"
	 * @param result Objet contenant les données brutes en provenance de la Com
	 * @return Arbre des résultats
	 */
	IResultTree build(IResultsCom result);
}
