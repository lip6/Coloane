package its.actions;

import its.TypeDeclaration;
import its.TypeList;

import org.eclipse.jface.action.Action;

public class RemoveTypeAction extends Action {
	
	private TypeList types;
	private TypeDeclaration td;

	public RemoveTypeAction(TypeList types, TypeDeclaration td) {
		this.types = types;
		this.td = td;
	}
		
	@Override
	public void run() {
		types.removeTypeDeclaration(td);
	}
}
