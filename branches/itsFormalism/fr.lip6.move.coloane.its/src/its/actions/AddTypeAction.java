package its.actions;

import its.TypeList;
import its.dialogs.AddTypeDialog;

import org.eclipse.jface.action.Action;

import testits.editors.MultiPageEditor;

public class AddTypeAction extends Action {
	
	private MultiPageEditor editor;

	public AddTypeAction(MultiPageEditor editor) {
		this.editor = editor;
	}
	
	public void setEditor(MultiPageEditor editor) {
		this.editor = editor;
	}
	
	@Override
	public void run() {
		TypeList types = editor.getTypes();
		AddTypeDialog atd = new AddTypeDialog(editor.getSite().getShell(),types);
		atd.open();
		if (atd.getType() != null) {
			types.addTypeDeclaration(atd.getType());
		}
	}
}
