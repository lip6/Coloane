package its.tpnui;

import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * A class providing a view of thhe transitions of a TPN in a table
 * @author Yann
 *
 */
public final class TransitionTableProvider implements IStructuredContentProvider {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		IGraph graph = (IGraph) inputElement;
		IGraphFormalism formalism = graph.getFormalism().getMasterGraph();
		IElementFormalism trans = formalism.getElementFormalism("transition");
		Collection<INode> nodes = graph.getNodes();

		List<INode> toret = new ArrayList<INode>();
		List<INode> privates = new ArrayList<INode>();
		for (INode node : nodes) {
			if (node.getNodeFormalism().equals(trans)) {
				if (node.getAttribute("visibility").equals("public")) {
					toret.add(node);
				} else {
					privates.add(node);
				}
			}
		}
		toret.addAll(privates);
		return toret.toArray();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
