package fr.lip6.move.coloane.core.results_new.reports;

import java.util.List;

import fr.lip6.move.coloane.core.results_new.IResultTree;
import fr.lip6.move.coloane.core.results_new.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

public class SyntaxCheckerReport implements IReport {

	@Override
	public IResultTree build(IResultsCom result) {
		return new ResultTreeImpl("Test SyntaxCheckerReport");
	}

	@Override
	public List<Integer> highlightNode(IResultsCom result) {
		// TODO Auto-generated method stub
		return null;
	}

}
