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
import java.util.Map;

/**
 * Cette classe va générer un arbre de résultat générique
 */
public class GenericReport {

	/**
	 * Construction de l'arbre des résultats qui seront affiches dans la fenetre "resultats"
	 * @param result Objet contenant les données brutes en provenance de la Com
	 * @return Arbre des résultats
	 */
	public final ResultTreeImpl build(IResult result) {
		// 1. Build the root of the result tree
		ResultTreeImpl root;
		if (!"".equals(result.getResultName())) { //$NON-NLS-1$
			root = new ResultTreeImpl(result.getResultName());
		} else {
			root = new ResultTreeImpl(Messages.GenericReport_0);
		}
		
		// 2. Attach the session Manager to the root
		root.setSessionManager(SessionManager.getInstance());

		addResultTreeImpl(result.getSubResults(),root);

		// Creation of textualResults
		for (List<String> tabStr : result.getTextualResults()) { 
			root.addChild(new ResultTreeImpl(tabStr.toArray(new String[tabStr.size()]))); 
		}
		return root; 
	}
	
	// TODO : Expliquer le code
	private void addResultTreeImpl(List<ISubResult> subresults, ResultTreeImpl root) {
		// For each group of subResults
		for (int i = 0; i < subresults.size(); i++) {
			ISubResult sub = subresults.get(i);

			// Create a node result
			ResultTreeImpl node;
			if (!("".equals(sub.getSubResultName()))) { //$NON-NLS-1$
				if (sub.getObjectsDesignation().size() > 0) {
					node = new ResultTreeImpl(sub.getObjectsDesignation(), sub.getSubResultName(), sub.getInformation());
				} else {						
					node = new ResultTreeImpl(sub.getSubResultName(), sub.getInformation());
				}
			} else {
				node = new ResultTreeImpl(Messages.GenericReport_4 + (i + 1));
			}

			root.addChild(node);
			addResultTreeImpl(sub.getSubResults(),node);
			
			
			Map<Integer, List<String>> attributesMap = sub.getAttributesOutline();
			// Create objectsOutline
			for (int id : sub.getObjectsOutline()) {
				String name = "id : " + String.valueOf(id); //$NON-NLS-1$
				IElement element = root.getSessionManager().getCurrentSession().getGraph().getObject(id);
				if (element != null) {
					String formalismName = Messages.GenericReport_2;
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
					ResultTreeImpl child = new ResultTreeImpl(id, formalismName, name);
					child.addAttributesOutline(attributesMap,id);
					attributesMap.remove(id);
					node.addChild(child);
				}
			}


			// Create textualResults
			for (List<String> tabStr : sub.getTextualResults()) { 
				root.addChild(new ResultTreeImpl(tabStr.toArray(new String[tabStr.size()]))); 
			}

			// Create 
			node.addAttributesOutline(attributesMap,attributesMap.keySet().toArray(new Integer[attributesMap.size()]));
		}
	}
}
