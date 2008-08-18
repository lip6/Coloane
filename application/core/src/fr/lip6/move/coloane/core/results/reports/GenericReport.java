package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

/**
 * Cette classe va générer un arbre de résultat générique
 */
public class GenericReport implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResult result) {

		// 1. Build the root of the resultat tree
		ResultTreeImpl root = new ResultTreeImpl(result.getServiceName());

		// 2. Attach the session Manager to the root
		root.setSessionManager(SessionManager.getInstance());

		// For each subgroup of results
		for (int i = 0; i < result.getSubResults().size(); i++) {
			ISubResult sub = result.getSubResults().get(i);

			// Create a node result
			ResultTreeImpl node;
			if (sub.getTextualResults().size() == 1) {
				if (!("".equals(sub.getName()))) { //$NON-NLS-1$
					node = new ResultTreeImpl(Messages.GenericReport_0 + (i + 1), sub.getName(), sub.getTextualResults().get(0));
				} else {
					node = new ResultTreeImpl(Messages.GenericReport_1 + (i + 1), sub.getTextualResults().get(0));
				}
			} else {
				node = new ResultTreeImpl(Messages.GenericReport_2 + (i + 1), sub.getName());
			}

			for (int id : sub.getObjectsOutline()) {
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

			if (sub.getTextualResults().size() > 1) {
				for (String s : sub.getTextualResults()) {
					node.addChild(new ResultTreeImpl(Messages.GenericReport_5, s));
				}
			}
			root.addChild(node);
		}
		return root;
	}
}
