package its.typesui;

import its.TypeDeclaration;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TypeLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// do stuff if you want nice icons in the table
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		TypeDeclaration type = (TypeDeclaration) element;
		switch (columnIndex) {
		case 0 :
			return type.getTypeName();
		case 1 :
			return type.getTypeType();
		case 2 :
			return type.getTypePath();
		case 3 :
			return Boolean.toString(type.isSatisfied());
		default :
			throw new RuntimeException("Column index out of bounds in TypeLabelProvider.");
		}
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}

	@Override
	public void dispose() {
		// nothing to do

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// default to true to ensure update on any change 
		return true;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
