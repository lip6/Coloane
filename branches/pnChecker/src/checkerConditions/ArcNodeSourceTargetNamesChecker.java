package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;

public class ArcNodeSourceTargetNamesChecker implements IArcChecker {

	public boolean check(IArc arc) {
		if (arc.getSource().getAttribute("name").getValue().equals(arc.getTarget().getAttribute("name").getValue())) {
			// Si le nom des noeuds source et target de l'arc sont les mêmes, on renvoie false pour signaler une erreur.
			return false;
		}
		// Sinon aucun problème, on renvoie true.
		return true;
	}

}
