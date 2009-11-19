package fr.lip6.move.coloane.its.labelsui;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * a very basic provider hiding a list of strings
 * @author Yann
 *
 */
public final class LabelsProvider implements IStructuredContentProvider {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		List<String> list =  (List<String>) inputElement;
		return list.toArray();
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
