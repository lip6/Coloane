package its.typesui;

import java.util.ArrayList;

import its.CompositeTypeDeclaration;
import its.Concept;
import its.TypeDeclaration;
import its.TypeList;
import its.expression.IEvaluationContext;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TypeListTreeProvider implements ITreeContentProvider {


	@Override
	public Object[] getElements(Object inputElement) {
		TypeList tl = (TypeList) inputElement;
		ArrayList<Object> al = new ArrayList<Object>();
		for (TypeDeclaration td : tl) {
			al.add(td);
		}
		return al.toArray();
	}

	public Object[] getChildren(Object element) {
		ArrayList<Object> children = new ArrayList<Object>();
		if (element instanceof CompositeTypeDeclaration) {
			CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) element;
			for (Concept concept : ctd.listConcepts()) {
				children.add(concept);
			}
		} else if (element instanceof Concept) {
			Concept concept = (Concept) element;
			if (concept.getEffective()!=null) {
				children.add( concept.getEffective() );
			}
		}
		if (element instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) element;
			IEvaluationContext params = td.getParameters();
			children.addAll(params.getBindings());
		}

		return children.toArray();
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
