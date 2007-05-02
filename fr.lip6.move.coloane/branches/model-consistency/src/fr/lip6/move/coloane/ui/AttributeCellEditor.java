package fr.lip6.move.coloane.ui;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import fr.lip6.move.coloane.ui.dialogs.AttributeDialog;

public class AttributeCellEditor extends DialogCellEditor {
	private String title;
	private String help;

	protected AttributeCellEditor (Composite parent, String title, String help) {
		super(parent);
		this.title = title;
		this.help = help;
	}
	
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		
		AttributeDialog inputDialog = new AttributeDialog(title,help);
		
		inputDialog.open();
		
		return "3";

	}

}
