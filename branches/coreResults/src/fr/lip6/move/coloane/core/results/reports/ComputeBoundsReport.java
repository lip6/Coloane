package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

/**
 * Générateur de résultat pour le calcul des bornes
 */
public class ComputeBoundsReport implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResult result) {
		ResultTreeImpl root;
		int nbSubResult = result.getSubResults().size();

		// Si aucun resultat... On retourne un root vide
		if (nbSubResult <= 0) {
			root = new ResultTreeImpl(result.getServiceName(), Messages.ComputeBoundsReport_0);
			root.setSessionManager(SessionManager.getInstance());
			return root;
		} else if (nbSubResult == 1) {
			root = new ResultTreeImpl(result.getServiceName(), Messages.ComputeBoundsReport_1);
			root.setSessionManager(SessionManager.getInstance());
		} else {
			root = new ResultTreeImpl(result.getServiceName(), nbSubResult + Messages.ComputeBoundsReport_2);
			root.setSessionManager(SessionManager.getInstance());
		}

		// Sinon on parcours les resultats
		for (ISubResult sub : result.getSubResults()) {
			String res = sub.getTextualResults().get(0);
			String firstPart = res.substring(0, res.indexOf(':')).trim();
			String secondPart = res.substring(res.indexOf(':') + 1).trim();
			ResultTreeImpl node = new ResultTreeImpl(firstPart, secondPart);

			// On utilise l'object designation pour mettre en valeur l'objet
			// dont on recoit les bornes
			for (int id : sub.getObjectsDesignation()) {
				node.addHighlighted(id);
			}
			// On ajoute ce noeud au resultat de service
			root.addChild(node);
		}
		return root;
	}
}
