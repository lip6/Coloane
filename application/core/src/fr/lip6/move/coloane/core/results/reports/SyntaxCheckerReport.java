package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

import java.util.List;

public class SyntaxCheckerReport implements IReport {

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.reports.IReport#build(fr.lip6.move.coloane.interfaces.objects.IResultsCom)
	 */
	public final IResultTree build(IResultsCom result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());

		for (SubResultsCom sub : result.getSubResults()) {
			ResultTreeImpl node = new ResultTreeImpl(sub.getCmdRT().get(0));
			for (String obj : sub.getCmdRO()) {
				int id = Integer.valueOf(obj);
				node.addChild(new ResultTreeImpl(id, obj));
			}
			root.addChild(node);
		}
		return root;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.reports.IReport#highlightNode(fr.lip6.move.coloane.interfaces.objects.IResultsCom)
	 */
	public final List<Integer> highlightNode(IResultsCom result) {
		return new GenericReport().highlightNode(result);
	}

}
