package fr.lip6.move.coloane.graphviz;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.graphviz.coloane.GraphLayout;

public class LayoutEditorActionDelegate implements IEditorActionDelegate {

	private IGraph graph = null;

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		graph = ((ColoaneEditor)targetEditor).getGraph();
	}

	@Override
	public void run(IAction action) {
		if ( graph != null )
			GraphLayout.Layout(graph);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// NOP
	}

}
