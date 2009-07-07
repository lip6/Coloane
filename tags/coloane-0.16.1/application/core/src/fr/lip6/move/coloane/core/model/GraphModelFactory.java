package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Classe permettant de construire un nouveau IGraph.
 */
public class GraphModelFactory {

	/**
	 * @param formalismName nom du formalisme.
	 * @return nouvelle instance d'un IGraph.
	 */
	public final IGraph createGraph(String formalismName) {
		return new GraphModel(formalismName);
	}

}
