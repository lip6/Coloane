package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class GenericReport implements IReport {

	public final IResultTree build(IResultsCom result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
		root.setSessionManager(SessionManager.getInstance());

		// Un sous-arbre pour chaque sous-resultats
		for (int i = 0; i < result.getSubResults().size(); i++) {
			SubResultsCom sub = result.getSubResults().get(i);

			ResultTreeImpl node;
			if (sub.getCmdRT().size() == 1) {
				node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails(), sub.getCmdRT().get(0));
			} else {
				node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails());
			}

			for (String s : sub.getCmdRO()) {
				int id = Integer.valueOf(s);
				String name = s;
				node.addChild(new ResultTreeImpl(id, "Object", name));
			}
			if (sub.getCmdRT().size() > 1) {
				for (String s : sub.getCmdRT()) {
					node.addChild(new ResultTreeImpl("Text", s));
				}
			}
			root.addChild(node);
		}
		return root;
	}
}
