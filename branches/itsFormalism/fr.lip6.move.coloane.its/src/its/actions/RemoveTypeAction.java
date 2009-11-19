package its.actions;

import its.TypeDeclaration;
import its.TypeList;

import org.eclipse.jface.action.Action;

/**
 * The action of removing a type.
 * @author Yann
 *
 */
public final class RemoveTypeAction extends Action {

	private TypeList types;
	private TypeDeclaration td;

	/**
	 * Ctor
	 * @param types the owning type list
	 * @param td the type to remove
	 */
	public RemoveTypeAction(TypeList types, TypeDeclaration td) {
		this.types = types;
		this.td = td;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		types.removeTypeDeclaration(td);
	}
}
