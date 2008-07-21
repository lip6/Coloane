package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class LTLModCheck implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResultsCom result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
		root.setSessionManager(SessionManager.getInstance());

		// Parcours de tous les DE-FE
		for (SubResultsCom sub : result.getSubResults()) {
			ResultTreeImpl node = new ResultTreeImpl(sub.getDetails());
			int i = 0;
			for (String desc : sub.getCmdRT()) {
				node.addChild(new ResultTreeImpl(desc + " " + i)); //$NON-NLS-1$
			}
//			for (Vector<String> toHighlight : sub.getCmdXA()) {
//				node.addChild(new ResultTreeImpl(Integer.parseInt(toHighlight.get(0)), toHighlight.get(1) + ":" + toHighlight.get(2)));
//			}
			for (String highlight : sub.getCmdME()) {
				node.addHighlighted(Integer.parseInt(highlight));
			}
			root.addChild(node);
		}
		return root;
	}
}
