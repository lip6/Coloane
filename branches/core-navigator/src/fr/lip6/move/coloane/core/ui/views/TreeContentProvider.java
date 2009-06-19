package fr.lip6.move.coloane.core.ui.views;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for {@link Tree}
 * 
 * @author Clément Démoulins
 */
public class TreeContentProvider implements ITreeContentProvider {

	/** {@inheritDoc} */
	public final Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Tree) {
			return ((Tree) parentElement).getChildren().toArray();
		}
		return null;
	}

	/** {@inheritDoc} */
	public final Object getParent(Object element) {
		if (element instanceof Tree) {
			return ((Tree) element).getParent();
		}
		return null;
	}

	/** {@inheritDoc} */
	public final boolean hasChildren(Object element) {
		Object[] childs = getChildren(element);
		return childs != null && childs.length > 0;
	}

	/** {@inheritDoc} */
	public final Object[] getElements(Object inputElement) {
		if (inputElement instanceof Tree) {
			return new Object[] {((Tree) inputElement).getElement()};
		}
		return null;
	}

	/** {@inheritDoc} */
	public final void dispose() { }

	/** {@inheritDoc} */
	public final void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }

}
