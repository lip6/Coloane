package fr.lip6.move.coloane.core.ui.views;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * TODO: A documenter
 * @author jbvoron
 *
 */
public final class TreeContentProvider implements ITreeContentProvider {

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parentElement) {
		System.err.println(" >> getChildren (" + parentElement + ")");
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(Object element) {
		System.err.println(" >> getParent (" + element + ")");
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasChildren(Object element) {
		// Pour le moment aucun element n'a de fils
		System.err.println(" >> hasChildren (" + element.getClass() + ")");
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getElements(Object inputElement) {
		System.err.println(" >> getElements (" + inputElement + ")");
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		System.err.println(" >> dispose ()");
	}

	/**
	 * {@inheritDoc}
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		System.err.println(" >> inputChanged (" + viewer + ", " + oldInput + "," + newInput + ")");
	}

}
