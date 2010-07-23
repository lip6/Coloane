package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.panels.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

/**
 * Générateur de résultat pour le calcul des bornes
 */
public class MinimalSyphonTrapReport implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResult result) {
		ResultTreeImpl root;
		int nbSubResult = result.getSubResults().size();

		// Si aucun resultat... On retourne un root vide
		if (nbSubResult <= 0) {
			root = new ResultTreeImpl(result.getResultName(), Messages.MinimalSyphonTrapReport_0);
			root.setSessionManager(SessionManager.getInstance());
			return root;
		} else if (nbSubResult == 1) {
			root = new ResultTreeImpl(result.getResultName(), Messages.MinimalSyphonTrapReport_1);
			root.setSessionManager(SessionManager.getInstance());
		} else {
			root = new ResultTreeImpl(result.getResultName(), nbSubResult + Messages.MinimalSyphonTrapReport_2);
			root.setSessionManager(SessionManager.getInstance());
		}

		// Sinon on parcours les resultats
		for (ISubResult sub : result.getSubResults()) {
			String res = sub.getTextualResults().get(0).get(0);
			ResultTreeImpl node = new ResultTreeImpl(res);

			// On utilise l'object designation pour mettre en valeur l'objet dont on recoit les bornes
			for (int id : sub.getObjectsDesignation()) {
				node.addHighlighted(id);
			}
			// On ajoute ce noeud au resultat de service
			root.addChild(node);
		}
		return root;
	}
}
