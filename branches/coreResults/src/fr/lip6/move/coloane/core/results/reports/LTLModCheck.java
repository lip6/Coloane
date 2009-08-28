package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

import java.util.List;

/**
 * Rapport pour le service LTL
 * @author jbvoron
 */
public class LTLModCheck implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResult result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getServiceName());
		root.setSessionManager(SessionManager.getInstance());

		// Parcours de tous les DE-FE
		for (ISubResult sub : result.getSubResults()) {
			ResultTreeImpl node = new ResultTreeImpl(sub.getSubResultName());
			int i = 0;
			for (List<String> descList : sub.getTextualResults()) {
				String desc = descList.get(0);
				node.addChild(new ResultTreeImpl(desc + " " + i)); //$NON-NLS-1$
			}
//			for (Vector<String> toHighlight : sub.getCmdXA()) {
//				node.addChild(new ResultTreeImpl(Integer.parseInt(toHighlight.get(0)), toHighlight.get(1) + ":" + toHighlight.get(2)));
//			}
			for (int highlight : sub.getObjectsDesignation()) {
				node.addHighlighted(highlight);
			}
			root.addChild(node);
		}
		return root;
	}
}
