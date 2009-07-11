package its.actions;

import its.TypeList;
import its.dialogs.AddTypeDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

import testits.editors.MultiPageEditor;

public class AddType extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MultiPageEditor editor = (MultiPageEditor) HandlerUtil.getActiveEditor(event);		
		TypeList types = editor.getTypes();
		AddTypeDialog atd = new AddTypeDialog(editor.getSite().getShell(),types);
		atd.open();
		if (atd.getType() != null) {
			types.addTypeDeclaration(atd.getType());
			// update display
			editor.getTableviewer().refresh();
		}
		
		return null;
	}
}
