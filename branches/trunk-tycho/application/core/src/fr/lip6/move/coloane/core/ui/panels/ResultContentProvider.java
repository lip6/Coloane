package fr.lip6.move.coloane.core.ui.panels;


import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provide contents for the CheckBoxTreeViewer.<br>
 * Results are extracted from a IResultTree
 */
public class ResultContentProvider implements ITreeContentProvider {

	/** {@inheritDoc} */
	public final Object[] getChildren(Object parentElement) {
		return ((IResultTree) parentElement).getChildren().toArray();
	}

	/** {@inheritDoc} */
	public final Object getParent(Object element) {
		return ((IResultTree) element).getParent();
	}

	/** {@inheritDoc} */
	public  final boolean hasChildren(Object element) {
		return ((IResultTree) element).getChildren().size() > 0;
	}

	/** {@inheritDoc} */
	public final Object[] getElements(Object inputElement) {
		return ((IResultTree) inputElement).getElement().toArray();
	}

	/** {@inheritDoc} */
	public void dispose() {
	}

	/** {@inheritDoc} */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
