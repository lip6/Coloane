package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.IGraphChecker;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class GraphCheckerSuperior4Nodes implements IGraphChecker {

	public boolean check(IGraph graph) {
		if (graph.getNodes().size() > 4) {
			// Si le nombre de nodes est supérieur à 4, alors on renvoie false pour signer
			// que c'est un problème.
			return false;
		}
		// Sinon on renvoie vrai : il n'y a pas de problèmes.
		return true;
		
	}

}
