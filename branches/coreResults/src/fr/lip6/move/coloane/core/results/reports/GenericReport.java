package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.List;
import java.util.Map;

/**
 * This class build a generic result tree.
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public class GenericReport implements IReport {

	/**
	 * Build a result tree from an {@link IResult}. It will be displayed in the result view.
	 * @param result the {@link IResult} containing all data needed to build the tree (Comming from Com).
	 * @return the result builded tree.
	 */
	public final ResultTreeImpl build(IResult result) {
		// Build the root of the result tree.
		ResultTreeImpl root;
		if (!"".equals(result.getResultName())) { //$NON-NLS-1$
			root = new ResultTreeImpl(result.getResultName());
		} else {
			root = new ResultTreeImpl(Messages.GenericReport_0);
		}

		// Attach the session Manager to the root.
		root.setSessionManager(SessionManager.getInstance());


		// Call the GenericReport#addResultTreeImpl(List<ISubResult>, ResultTreeImpl) method to build the first level of the result tree.
		addResultTreeImpl(result.getSubResults(), root);


		// Adding textualResults to the root.
		for (List<String> tabStr : result.getTextualResults()) {
			root.addChild(new ResultTreeImpl(tabStr.toArray(new String[tabStr.size()])));
		}

		// Creation of tips
		Map<Integer, List<ITip>> tipsMap = result.getTips();
		root.setTips(tipsMap, tipsMap.keySet().toArray(new Integer[tipsMap.size()]));

		return root;
	}
	
	/**
	 * Build a level of the result tree. Call itself recursively to build the whole tree.
	 * @param subResults the {@link ISubResult} list used to build the level.
	 * @param root the current level of the tree.
	 */
	private void addResultTreeImpl(List<ISubResult> subResults, ResultTreeImpl root) {
		for (int i = 0; i < subResults.size(); i++) {
			// For each subResult,
			ISubResult sub = subResults.get(i);

			// we create a node which will contain all data from the subResults list.
			ResultTreeImpl node;
			if (!("".equals(sub.getSubResultName()))) { //$NON-NLS-1$
				// Create a node with a specified name and all objects designation.
				node = new ResultTreeImpl(sub.getObjectsDesignation(), sub.getSubResultName(), sub.getInformation());
			} else {
				// Create a node with a generic name and all objects designation.
				node = new ResultTreeImpl(sub.getObjectsDesignation(), Messages.GenericReport_1 + (i + 1));
			}
			// Adding the created node to the root and call recursively the GenericReport#addResultTreeImpl(List<ISubResult>, ResultTreeImpl) method
			// in order to build the inferiors levels.
			root.addChild(node);
			addResultTreeImpl(sub.getSubResults(), node);


			// Getting the attributes map and the tips map from the current subResult.
			Map<Integer, List<String>> attributesMap = sub.getAttributesOutline();
			Map<Integer, List<ITip>> tipsMap = sub.getTips();
			// For each object id,
			for (int id : sub.getObjectsOutline()) {
				String name = "id : " + String.valueOf(id); //$NON-NLS-1$
				// we get the corresponding element in the graph.
				IElement element = root.getSessionManager().getCurrentSession().getGraph().getObject(id);
				// If the element is null, it doesn't exist so we do nothing.
				if (element != null) {
					// Otherwise we add it to the result
					String formalismName = Messages.GenericReport_2;
					// We retrieve it formalism name ...
					if (element instanceof INode) {
						IAttribute attribute = element.getAttribute("name"); //$NON-NLS-1$
						if (attribute != null) {
							String value = attribute.getValue();
							if (!("".equals(value))) { //$NON-NLS-1$
								name = value;
							}
						}
						formalismName = ((INode) element).getNodeFormalism().getName();
					}
					if (element instanceof IArc) {
						IAttribute attribute = element.getAttribute("name"); //$NON-NLS-1$
						if (attribute != null) {
							String value = attribute.getValue();
							if (!("".equals(value))) { //$NON-NLS-1$
								name = value;
							}
						}
						formalismName = ((IArc) element).getArcFormalism().getName();
					}
					if (element instanceof IGraph) {
						formalismName = ((IGraph) element).getFormalism().getName();
					}
					// and we create a child for the current node.
					ResultTreeImpl child = new ResultTreeImpl(id, formalismName, name);
					// We add the attributes to highlight which belong to the element to the child.
					child.addAttributesOutline(attributesMap, id);
					attributesMap.remove(id);
					// We also add tips.
					child.setTips(tipsMap, id);
					tipsMap.remove(id);
					// And finally add the child to the current node.
					node.addChild(child);
				}
			}

			// After adding all the attributes to corresponding elements, we add remaining attributes to the current node.
			node.addAttributesOutline(attributesMap, attributesMap.keySet().toArray(new Integer[attributesMap.size()]));

			// Create tips
			node.setTips(tipsMap, tipsMap.keySet().toArray(new Integer[tipsMap.size()]));

			// Finally adding textuals results to the current node.
			for (List<String> tabStr : sub.getTextualResults()) {
				root.addChild(new ResultTreeImpl(tabStr.toArray(new String[tabStr.size()])));
			}
		}
	}
}
