package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

/**
 * Générateur de résultat pour les P-invariants
 */
public class PInvariantsReport implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResult result) {
		ResultTreeImpl root;
		int nbSubResult = result.getSubResults().size();

		// Si aucun resultat... On retourne un root vide
		if (nbSubResult <= 0) {
			root = new ResultTreeImpl(result.getResultName(), Messages.PInvariantsReport_0);
			root.setSessionManager(SessionManager.getInstance());
			return root;
		} else if (nbSubResult == 1) {
			root = new ResultTreeImpl(result.getResultName(), Messages.PInvariantsReport_1);
			root.setSessionManager(SessionManager.getInstance());
		} else {
			root = new ResultTreeImpl(result.getResultName(), nbSubResult + Messages.PInvariantsReport_2);
			root.setSessionManager(SessionManager.getInstance());
		}

		for (ISubResult sub : result.getSubResults()) {
			ResultTreeImpl node = new ResultTreeImpl(sub.getTextualResults().get(0).get(0));
			System.err.println(sub.getObjectsDesignation());
			System.err.println(sub.getObjectsOutline());
			for (int id : sub.getObjectsDesignation()) {
				node.addChild(new ResultTreeImpl(id, String.valueOf(id)));
				node.addHighlighted(id);
			}
			root.addChild(node);
		}
		return root;
	}
}
