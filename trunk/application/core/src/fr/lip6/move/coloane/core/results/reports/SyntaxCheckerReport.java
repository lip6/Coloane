package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class SyntaxCheckerReport implements IReport {


	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResultsCom result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
		root.setSessionManager(SessionManager.getInstance());

		if (result.getSubResults().size() == 0) {
			root.addChild(new ResultTreeImpl(Messages.SyntaxCheckerReport_0));
		}

		for (SubResultsCom sub : result.getSubResults()) {
			ResultTreeImpl node = new ResultTreeImpl(sub.getCmdRT().get(0));
			for (String obj : sub.getCmdRO()) {
				int id = Integer.valueOf(obj);
				node.addChild(new ResultTreeImpl(id, obj));
				node.addHighlighted(id);
			}
			root.addChild(node);
		}
		return root;
	}
}
