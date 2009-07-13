package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

import java.util.List;

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

		addResultTreeImpl(result.getSubResults(),root);
		
		if (result.getTextualResults().size() > 0) {
			for (String str : result.getTextualResults()) {
				root.addChild(new ResultTreeImpl(str));
			}
		}
		return root; 
	}
	
	private void addResultTreeImpl(List<ISubResult> subresults, ResultTreeImpl root) {
		// For each subgroup of results
		for (int i = 0; i < subresults.size(); i++) {
			ISubResult sub = subresults.get(i);

			// Create a node result
			ResultTreeImpl node;
			if (!("".equals(sub.getName()))) { //$NON-NLS-1$
				if (sub.getObjectsDesignation().size() > 0) {
					node = new ResultTreeImpl(sub.getObjectsDesignation(), sub.getName(), sub.getInformation());
				} else {						
					node = new ResultTreeImpl(sub.getName(), sub.getInformation());
				}
			} else {
				node = new ResultTreeImpl(Messages.GenericReport_0 + (i + 1));
			}

			root.addChild(node);
			addResultTreeImpl(sub.getChildren(),node);
			
			for (int id : sub.getObjectsOutline()) {
				String name = "id : " + String.valueOf(id); //$NON-NLS-1$
				IElement element = root.getSessionManager().getCurrentSession().getGraph().getObject(id);
				if (element != null) {
					String formalismName = "Le formalisme n'a pas de nom pour cet objet"; //$NON-NLS-1$
					if (element instanceof INode) {
						String value = element.getAttribute(Messages.GenericReport_3).getValue();
						if (!("".equals(value))) { //$NON-NLS-1$
							name = value;
						}
						formalismName = ((INode)element).getNodeFormalism().getName();
					}
					if (element instanceof IArc) {
						formalismName = ((IArc)element).getArcFormalism().getName();
					}
					if (element instanceof IGraph) {
						formalismName = ((IGraph)element).getFormalism().getName();
					}
					node.addChild(new ResultTreeImpl(id, formalismName, name));
				}			
			}			

			if (sub.getTextualResults().size() > 0) {
				for (String str : sub.getTextualResults()) {
					node.addChild(new ResultTreeImpl(str));
				}
			}
			
		}
	}
}
