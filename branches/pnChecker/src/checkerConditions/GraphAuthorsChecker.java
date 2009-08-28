package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;

public class GraphAuthorsChecker implements IAttributeChecker {

	public boolean check(String value) {
		if ("".equals(value)) {
			// Si le nom des auteurs n'est pas renseigné, on renvoie vrai pour créer un marker et signaler le problème.
			return false;
		}
		// Sinon les noms ont été renseignés, pas de problème : on renvoie true.
		return true;
	}

}
