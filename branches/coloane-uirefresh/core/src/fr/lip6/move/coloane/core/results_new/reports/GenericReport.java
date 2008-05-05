package fr.lip6.move.coloane.core.results_new.reports;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.results_new.IResultTree;
import fr.lip6.move.coloane.core.results_new.ResultTreeImpl;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IElement;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class GenericReport implements IReport {
	@Override
	public IResultTree build(IResultsCom result) {
		printModel();
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
		IModelImpl model = Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel();

		// Un sous-arbre pour chaque sous-resultats
		for(int i=0;i<result.getSubResults().size();i++) {
			SubResultsCom sub = result.getSubResults().get(i);
			ResultTreeImpl node = new ResultTreeImpl("Ensemble "+(i+1), sub.getDetails());

			// Mise en valeur des objets ME
			for(String s:sub.getCmdME()) {
				int id = Integer.valueOf(s);
//				for(IElement n:model.getChildren()) {
//					if(n instanceof IModelImpl) {
//						INodeImpl ni = (INodeImpl)n;
//						if(ni.getId() == id)
//							ni.setAttributesSelected(true, true);
//					}
//				}
			}
			// 
			for(String s:sub.getCmdRO()) {
				int id = Integer.valueOf(s);
				String name = s;
//				for(IElement n:model.getAttributes()) {
//					IAttribute attribute = (IAttribute)n;
//					if(attribute.getRefId() == id)
//						name = attribute.getName();
//				}
				node.addChild(new ResultTreeImpl("Objet",name));
			}
			for(String s:sub.getCmdRT())
				node.addChild(new ResultTreeImpl("Text",s));
			root.addChild(node);
		}
		return root;
	}
	
	private static void printModel() {
//		IModel model = Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel().getGenericModel();
//		System.err.println("Affichage du model "+Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel().getChildren().size());
//		for(IElement e:Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel().getChildren())
//			System.err.println(e);
//		for(INode node:model.getListOfNodes()) {
//			System.err.println("Node "+node.getId());
//		}
//		for(IArc arc:model.getListOfArcs()) {
//			System.err.println("Arc "+arc.getId()+" - "+arc.getStartingNode().getId()+">"+arc.getEndingNode().getId());
//		}
	}
}
