package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.core.ui.model.IElement;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class GenericReport implements IReport {

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.reports.IReport#build(fr.lip6.move.coloane.interfaces.objects.IResultsCom)
	 */
	public final ResultTreeImpl build(IResultsCom result) {

		// 1. Build the root of the resultat tree
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());

		// 2. Attach the session Manager to the root
		root.setSessionManager(SessionManager.getInstance());

		// For each subgroup of results
		for (int i = 0; i < result.getSubResults().size(); i++) {
			SubResultsCom sub = result.getSubResults().get(i);

			// Create a node result
			ResultTreeImpl node;
			if (sub.getCmdRT().size() == 1) {
				if (!("".equals(sub.getDetails()))) {
					node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails(), sub.getCmdRT().get(0));
				} else {
					node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getCmdRT().get(0));
				}
			} else {
				node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails());
			}

			for (String s : sub.getCmdRO()) {
				int id = Integer.valueOf(s);

				String name = String.valueOf(id);
				IElement element = root.getSessionManager().getCurrentSession().getGraph().getModelObject(id);
				if ((element != null) && (element instanceof INodeImpl)) {
					String value = element.getAttributeValue("name");
					if (!("".equals(value))) {
						name = value;
					}
				}
				node.addChild(new ResultTreeImpl(id, "Object", name));
				node.addHighlighted(id);
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
