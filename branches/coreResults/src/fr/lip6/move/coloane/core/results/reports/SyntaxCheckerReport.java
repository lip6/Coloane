package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

/**
 * Générateur de résultat pour le syntax check
 */
public class SyntaxCheckerReport implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResult result) {
		ResultTreeImpl root;

		// Si aucun message (pas d'erreur)... Alors on affiche un message standard
		if (result.getSubResults().size() == 0) {
			root = new ResultTreeImpl(result.getResultName(), Messages.SyntaxCheckerReport_0);
			root.setSessionManager(SessionManager.getInstance());
			return root;
		}

		// Sinon on affiche le nombre de messages dans la 2eme colonne
		int errors = result.getSubResults().size();
		if (errors == 1) {
			root = new ResultTreeImpl(result.getResultName(), Messages.SyntaxCheckerReport_1);
		} else {
			root = new ResultTreeImpl(result.getResultName(), errors + Messages.SyntaxCheckerReport_2);
		}
		root.setSessionManager(SessionManager.getInstance());

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
