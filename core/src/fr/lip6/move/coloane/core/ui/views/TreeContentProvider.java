package fr.lip6.move.coloane.core.ui.views;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeContentProvider implements ITreeContentProvider {
	
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		// Pour le moment aucun element n'a de fils
		return false;
	}

	public Object[] getElements(Object inputElement) {
		return null;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
