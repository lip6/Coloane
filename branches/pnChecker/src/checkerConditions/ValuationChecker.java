package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

public class ValuationChecker implements IAttributeChecker {

	public ValuationChecker() {
		// TODO Auto-generated constructor stub
	}

	public boolean check(String value) {
		Integer valuation = Integer.parseInt(value);
		if ((valuation >= 2) && (valuation <= 5)) {
			// La valuation de l'arc est bien comprise entre 2 et 5 : pas de problème, on renvoie vrai.
			return true;
		}
		// Sinon la valuatin n'est pas correcte : on renvoie false pour créer un marker.
		return false;
	}

}
