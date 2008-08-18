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
		ResultTreeImpl root = new ResultTreeImpl(result.getServiceName());
		root.setSessionManager(SessionManager.getInstance());

		if (result.getSubResults().size() == 0) {
			root.addChild(new ResultTreeImpl(Messages.SyntaxCheckerReport_0));
		}

		for (ISubResult sub : result.getSubResults()) {
			ResultTreeImpl node = new ResultTreeImpl(sub.getTextualResults().get(0));
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
