package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.results.IResultTree;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ResultContentProvider implements ITreeContentProvider {

	public final Object[] getChildren(Object parentElement) {
		return ((IResultTree) parentElement).getChildren().toArray();
	}

	public final Object getParent(Object element) {
		return ((IResultTree) element).getParent();
	}

	public  final boolean hasChildren(Object element) {
		return ((IResultTree) element).getChildren().size() > 0;
	}

	public final Object[] getElements(Object inputElement) {
		return ((IResultTree) inputElement).getElement().toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
