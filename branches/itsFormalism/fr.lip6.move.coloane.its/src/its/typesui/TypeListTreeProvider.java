package its.typesui;

import java.util.ArrayList;

import its.CompositeTypeDeclaration;
import its.Concept;
import its.TypeDeclaration;
import its.TypeList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TypeListTreeProvider implements ITreeContentProvider {


	@Override
	public Object[] getElements(Object inputElement) {
		TypeList tl = (TypeList) inputElement;
		ArrayList<Object> al = new ArrayList<Object>();
		for (TypeDeclaration td : tl) {
			al.add(td);
//			if (td instanceof CompositeTypeDeclaration) {
//				CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) td;
//				//				for (Concept concept : ctd.listConcepts()) {
//				//					al.add(concept);
//				//				}
//			}

		}
		return al.toArray();
	}

	public Object[] getChildren(Object element) {
		if (element instanceof CompositeTypeDeclaration) {
			CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) element;
			ArrayList<Concept> al = new ArrayList<Concept>();
			for (Concept concept : ctd.listConcepts()) {
				al.add(concept);
			}
			return al.toArray();
		}

		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Concept) {
			Concept concept = (Concept) element;
			return concept.getParent();
		} else if (element instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) element;
			return td.getTypeList();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	@Override
	public void dispose() {
		// NOP
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

}
