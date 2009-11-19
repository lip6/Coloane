package its.typesui;

import its.TypeList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
/**
 * A basic type list based provider for the table view
 * @author Yann
 *
 */
public final class TypeListProvider implements IStructuredContentProvider {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		TypeList tl = (TypeList) inputElement;
		return tl.toArray();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
