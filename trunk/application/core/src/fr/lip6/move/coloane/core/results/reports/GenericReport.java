package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

import java.util.ArrayList;
import java.util.List;

public class GenericReport implements IReport {

	public final IResultTree build(IResultsCom result) {
		ResultTreeImpl root = new ResultTreeImpl(result.getQuestion());
		IModelImpl model = Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel();

		// Un sous-arbre pour chaque sous-resultats
		for (int i = 0; i < result.getSubResults().size(); i++) {
			SubResultsCom sub = result.getSubResults().get(i);

			ResultTreeImpl node;
			if (sub.getCmdRT().size() == 1) {
				node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails(), sub.getCmdRT().get(0));
			} else {
				node = new ResultTreeImpl("Ensemble " + (i + 1), sub.getDetails());
			}

			for (String s : sub.getCmdRO()) {
				int id = Integer.valueOf(s);
				String name = s;
				INodeImpl nodeImpl = model.getNode(id);
				if (nodeImpl != null) {
					String attribut = nodeImpl.getNodeAttributeValue("name");
					if (!attribut.equals("")) {
						name = attribut;
					}
				}
				node.addChild(new ResultTreeImpl(id, "Object", name));
			}
			if (sub.getCmdRT().size() > 1) {
				for (String s : sub.getCmdRT()) {
					node.addChild(new ResultTreeImpl("Text", s));
				}
			}
			root.addChild(node);
		}
		return root;
	}

	public final List<Integer> highlightNode(IResultsCom result) {
		List<Integer> list = new ArrayList<Integer>();
		for (SubResultsCom sub : result.getSubResults()) {
			for (String me : sub.getCmdME()) {
				Integer id = new Integer(me);
				list.add(id);
			}
		}
		return list;
	}
}
