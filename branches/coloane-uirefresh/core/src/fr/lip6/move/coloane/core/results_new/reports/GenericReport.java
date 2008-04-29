package fr.lip6.move.coloane.core.results_new.reports;

import fr.lip6.move.coloane.core.results_new.IResultTree;
import fr.lip6.move.coloane.core.results_new.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class GenericReport implements IReport {
	@Override
	public IResultTree build(IResultsCom result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
		for(int i=0;i<result.getSubResults().size();i++) {
			SubResultsCom sub = result.getSubResults().get(i);
			ResultTreeImpl node = new ResultTreeImpl(i+"");
			for(String s:sub.getCmdME())
				node.addChild(new ResultTreeImpl("ME",s));
			for(String s:sub.getCmdRO())
				node.addChild(new ResultTreeImpl("RO",s));
			for(String s:sub.getCmdRT())
				node.addChild(new ResultTreeImpl("RT",s));
			node.addChild(new ResultTreeImpl("",sub.getDetails()));
			root.addChild(node);
		}
		return root;
	}
}
