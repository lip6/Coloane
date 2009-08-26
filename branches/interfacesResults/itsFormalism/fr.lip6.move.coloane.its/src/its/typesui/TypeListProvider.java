package its.typesui;

import its.TypeList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TypeListProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		TypeList tl = (TypeList) inputElement;
		return tl.toArray();
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
