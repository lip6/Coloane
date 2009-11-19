package fr.lip6.move.coloane.its.ui.forms;

import fr.lip6.move.coloane.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.its.Concept;
import fr.lip6.move.coloane.its.TypeDeclaration;
import fr.lip6.move.coloane.its.TypeList;
import fr.lip6.move.coloane.its.expression.IEvaluationContext;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * A provider for objects in the tree view: type declarations, parameters, concepts
 * @author Yann
 *
 */
public final class TypeListTreeProvider implements ITreeContentProvider {

	/**
	 * Root elements are the type declarations.
	 * @param inputElement a type list
	 * @return the types in the list
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		TypeList tl = (TypeList) inputElement;
		List<Object> al = new ArrayList<Object>();
		for (TypeDeclaration td : tl) {
			al.add(td);
		}
		return al.toArray();
	}

	/**
	 * Children of type = parameters
	 * + concepts for composite type
	 * + effective type for concept
	 * @param element a type declaration or other tree view object
	 * @return children of this tree node
	 */
	@Override
	public Object[] getChildren(Object element) {
		List<Object> children = new ArrayList<Object>();
		if (element instanceof CompositeTypeDeclaration) {
			CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) element;
			for (Concept concept : ctd.listConcepts()) {
				children.add(concept);
			}
		} else if (element instanceof Concept) {
			Concept concept = (Concept) element;
			if (concept.getEffective() != null) {
				children.add(concept.getEffective());
			}
		}
		if (element instanceof TypeDeclaration) {
			TypeDeclaration td = (TypeDeclaration) element;
			IEvaluationContext params = td.getParameters();
			children.addAll(params.getBindings());
		}

		return children.toArray();
	}

	/**
	 * Implemented where possible per the contract of TreeProvider
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		// NOP
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

}
