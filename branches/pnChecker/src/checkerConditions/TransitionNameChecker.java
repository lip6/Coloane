package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

public class TransitionNameChecker implements IAttributeChecker {

	public boolean check(String value) {
		try {
			if (value.substring(0, 10).equals("Transition")) {
				// Si le nom commence par "Place " alors c'est correct, on renvoie true.
				return true;
			}
			// Sinon le nom ne respecte pas la condition : on crée un marker.
			return false;
		} catch (IndexOutOfBoundsException e) {
			// Si value est une chaîne de moins de 6 caractères, une exception est levée
			return false;
		}
	}

}
