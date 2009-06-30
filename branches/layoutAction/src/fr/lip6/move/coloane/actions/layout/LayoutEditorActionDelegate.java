package fr.lip6.move.coloane.actions.layout;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.commands.ModificationResultCommand;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.graphviz.coloane.GraphLayout;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;


/**
 * The action of laying out a graph.
 *
 */
public class LayoutEditorActionDelegate implements IEditorActionDelegate {

	private IGraph graph = null;
	private CommandStack stack;

	/**
	 * {@inheritDoc}
	 */
	public final void setActiveEditor(IAction action, IEditorPart targetEditor) {
		graph = ((ColoaneEditor) targetEditor).getGraph();
		ColoaneEditor ce = (ColoaneEditor) targetEditor;
		GraphicalViewer viewer = (GraphicalViewer) ce.getAdapter(GraphicalViewer.class);
		stack = viewer.getEditDomain().getCommandStack();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void run(IAction action)  {
		if (graph != null) {
			ModificationResultCommand  c = new ModificationResultCommand(graph, GraphLayout.layout(graph));
			stack.execute(c);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// NOP
	}

}
