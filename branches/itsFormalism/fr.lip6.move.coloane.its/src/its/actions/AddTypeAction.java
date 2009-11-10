package its.actions;

import its.TypeList;
import its.dialogs.AddTypeDialog;

import org.eclipse.jface.action.Action;

import testits.editors.MultiPageEditor;

public class AddTypeAction extends Action {
	
	private MultiPageEditor editor;
	private AddTypeDialog atd;

	public AddTypeAction(MultiPageEditor editor) {
		setEditor(editor);
	}
	
	public void setEditor(MultiPageEditor editor) {
		this.editor = editor;
		atd = new AddTypeDialog(editor.getSite().getShell(),editor.getTypes());				
	}
	
	@Override
	public void run() {
		TypeList types = editor.getTypes();
		atd.open();
		if (atd.getType() != null) {
			types.addTypeDeclaration(atd.getType());
		}
	}

	public void setHint(String hint) {
		atd.setFileField(hint);
	}
}
