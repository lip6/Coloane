package fr.lip6.move.coloane.core.results_new.reports;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.results_new.IResultTree;
import fr.lip6.move.coloane.core.results_new.ResultTreeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class GenericReport implements IReport {
	@Override
	public IResultTree build(IResultsCom result) {
//		printModel();
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
		IModelImpl model = Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel();

		// Un sous-arbre pour chaque sous-resultats
		for(int i=0;i<result.getSubResults().size();i++) {
			SubResultsCom sub = result.getSubResults().get(i);

			ResultTreeImpl node;
			if(sub.getCmdRT().size()==1)
				node = new ResultTreeImpl("Ensemble "+(i+1), sub.getDetails(), sub.getCmdRT().get(0));
			else
				node = new ResultTreeImpl("Ensemble "+(i+1), sub.getDetails());

			for(String s:sub.getCmdRO()) {
				int id = Integer.valueOf(s);
				String name = s;
				INodeImpl nodeImpl = model.getNode(id);
				if(nodeImpl != null) {
					String attribut = nodeImpl.getNodeAttributeValue("name");
					if(!attribut.equals(""))
						name = attribut;
				}
				node.addChild(new ResultTreeImpl(id,"Object",name));
			}
			if(sub.getCmdRT().size()>1) {
				for(String s:sub.getCmdRT())
					node.addChild(new ResultTreeImpl("Text",s));
			}
			root.addChild(node);
		}
		return root;
	}

	@SuppressWarnings("unused")
	private static void printModel() {
		IModelImpl model = Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel();
		System.err.println("Affichage du model");
		for(INodeImpl node:model.getNodes()) {
			System.err.println(node.getId()+" "+node.getAttributes());
		}
	}

	@Override
	public List<Integer> highlightNode(IResultsCom result) {
		List<Integer> list = new ArrayList<Integer>();
		for(SubResultsCom sub:result.getSubResults()) {
			for(String me:sub.getCmdME()) {
				Integer id = new Integer(me);
				list.add(id);
			}
		}
		return list;
	}
}
