package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.results.IResultTree;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Modèle (au sens du MVC) des résultats pour le CheckBoxTreeViewer.
 * Le modèle se base sur les données contenues dans unIResultTree.
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
