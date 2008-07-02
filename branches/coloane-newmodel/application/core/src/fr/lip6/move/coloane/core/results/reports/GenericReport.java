package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
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
				if (!("".equals(sub.getDetails()))) { //$NON-NLS-1$
					node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails(), sub.getCmdRT().get(0)); //$NON-NLS-1$
				} else {
					node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getCmdRT().get(0)); //$NON-NLS-1$
				}
			} else {
				node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails()); //$NON-NLS-1$
			}

			for (String s : sub.getCmdRO()) {
				int id = Integer.valueOf(s);

				String name = s;
				INode nodeModel = root.getSessionManager().getCurrentSession().getGraph().getNode(id);
				if (nodeModel != null) {
					IAttribute attr = nodeModel.getAttribute("name"); //$NON-NLS-1$
					if (attr != null) {
						String value = attr.getValue();
						if (!("".equals(value))) { //$NON-NLS-1$
							name = value;
						}
					}
				}
				node.addChild(new ResultTreeImpl(id, "Object", name)); //$NON-NLS-1$
				node.addHighlighted(id);
			}

			if (sub.getCmdRT().size() > 1) {
				for (String s : sub.getCmdRT()) {
					node.addChild(new ResultTreeImpl("Text", s)); //$NON-NLS-1$
				}
			}
			root.addChild(node);
		}
		return root;
	}
}
