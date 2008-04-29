package fr.lip6.move.coloane.core.ui.panels;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import fr.lip6.move.coloane.core.results_new.IResultTree;

public class ResultContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		return ((IResultTree)parentElement).getChildren().toArray();
	}

	public Object getParent(Object element) {
		return ((IResultTree)element).getParent();
	}

	public boolean hasChildren(Object element) {
		return ((IResultTree)element).getChildren().size()>0;
	}

	public Object[] getElements(Object inputElement) {
		return ((IResultTree)inputElement).getElement().toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
