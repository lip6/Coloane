package fr.lip6.move.coloane.its.checks.ui;

import fr.lip6.move.coloane.its.checks.CheckList;
import fr.lip6.move.coloane.its.checks.CheckService;
import fr.lip6.move.coloane.its.ui.forms.TypeListTreeProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;

public class CheckListTreeProvider extends TypeListTreeProvider implements
		IContentProvider {

	
	/**
	 * Root elements are the type declarations.
	 * @param inputElement a type list
	 * @return the types in the list
	 * 
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		CheckList cl = (CheckList) inputElement;
		List<Object> al = new ArrayList<Object>();
		al.add(cl.getType());
		for (CheckService cs : cl) {
			al.add(cs);
		}
		return al.toArray();
	}

	protected void addChildren(Object element, List<Object> children) {
		// TODO
		super.addChildren(element, children);
	}
	
	/**
	 * Implemented where possible per the contract of TreeProvider
	 * {@inheritDoc}
	 */
	@Override
	public Object getParent(Object element) {
		// TODO
//		if (element instanceof Concept) {
//			Concept concept = (Concept) element;
//			return concept.getParent();
//		} else if (element instanceof TypeDeclaration) {
//			TypeDeclaration td = (TypeDeclaration) element;
//			return td.getTypeList();
//		}
		return super.getParent(element);
	}
	
	

}
