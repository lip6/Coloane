package its.conceptsui;

import its.CompositeTypeDeclaration;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ConceptProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) inputElement;
		return ctd.listConcepts().toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

}
