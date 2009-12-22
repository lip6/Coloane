package fr.lip6.move.coloane.its.actions;

import fr.lip6.move.coloane.its.TypeList;
import fr.lip6.move.coloane.its.dialogs.AddTypeDialog;
import fr.lip6.move.coloane.its.plugin.editors.MultiPageEditor;

import org.eclipse.jface.action.Action;


/**
 * An action to add a type to a type list
 * @author Yann
 *
 */
public final class AddTypeAction extends Action {

	private MultiPageEditor editor;
	private AddTypeDialog atd;

	/**
	 * basic ctor
	 * @param editor the parent editor (contains the type list)
	 */
	public AddTypeAction(MultiPageEditor editor) {
		setEditor(editor);
	}

	/**
	 * update current editor
	 * @param editor parent editor
	 */
	public void setEditor(MultiPageEditor editor) {
		this.editor = editor;
		atd = new AddTypeDialog(editor.getSite().getShell(), editor.getTypes());
	}

	/**
	 * Runs this action
	 */
	@Override
	public void run() {
		TypeList types = editor.getTypes();
		atd.open();
		if (atd.getType() != null) {
			types.addTypeDeclaration(atd.getType());
		}
	}

	/** Used to position a hint = a specific file in the dialog.
	 * 
	 * @param hint a full file path to the candidate for insertion.
	 */
	public void setHint(String hint) {
		atd.setFileField(hint);
	}
}
