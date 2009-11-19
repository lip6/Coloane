package its.conceptsui;

import its.CompositeTypeDeclaration;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provide the concepts of a compsite type declaration
 * @author Yann
 *
 */
public final class ConceptProvider implements IStructuredContentProvider {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) inputElement;
		return ctd.listConcepts().toArray();
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
