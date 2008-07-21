package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class GenericReport implements IReport {

	/** {@inheritDoc} */
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
					node = new ResultTreeImpl(Messages.GenericReport_0 + (i + 1), sub.getDetails(), sub.getCmdRT().get(0));
				} else {
					node = new ResultTreeImpl(Messages.GenericReport_1 + (i + 1), sub.getCmdRT().get(0));
				}
			} else {
				node = new ResultTreeImpl(Messages.GenericReport_2 + (i + 1), sub.getDetails());
			}

			for (String s : sub.getCmdRO()) {
				int id = Integer.valueOf(s);

				String name = String.valueOf(id);
				IElement element = root.getSessionManager().getCurrentSession().getGraph().getObject(id);
				if ((element != null) && (element instanceof INode)) {
					String value = element.getAttribute(Messages.GenericReport_3).getValue();
					if (!("".equals(value))) { //$NON-NLS-1$
						name = value;
					}
				}
				node.addChild(new ResultTreeImpl(id, Messages.GenericReport_4, name));
				node.addHighlighted(id);
			}

			if (sub.getCmdRT().size() > 1) {
				for (String s : sub.getCmdRT()) {
					node.addChild(new ResultTreeImpl(Messages.GenericReport_5, s));
				}
			}
			root.addChild(node);
		}
		return root;
	}
}
