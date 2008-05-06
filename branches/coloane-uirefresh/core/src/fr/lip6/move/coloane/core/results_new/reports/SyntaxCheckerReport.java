package fr.lip6.move.coloane.core.results_new.reports;

import java.util.List;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.results_new.IResultTree;
import fr.lip6.move.coloane.core.results_new.ResultTreeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class SyntaxCheckerReport implements IReport {

	@Override
	public IResultTree build(IResultsCom result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
//		IModelImpl model = Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel();
		
		for(SubResultsCom sub:result.getSubResults()) {
			ResultTreeImpl node = new ResultTreeImpl(sub.getCmdRT().get(0));
			for(String obj:sub.getCmdRO()) {
				int id = Integer.valueOf(obj);
				node.addChild(new ResultTreeImpl(id,obj));
			}
			root.addChild(node);
		}
		
		return root;
	}

	@Override
	public List<Integer> highlightNode(IResultsCom result) {
		return new GenericReport().highlightNode(result);
	}

}
