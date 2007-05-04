package fr.lip6.move.coloane.ui;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import fr.lip6.move.coloane.ui.dialogs.AttributeDialog;

public class AttributeCellEditor extends DialogCellEditor {
	private String title;
	private String help;
	private String byDefault;

	protected AttributeCellEditor (Composite parent, String title, String help, String byDefault) {
		super(parent);
		this.title = title;
		this.help = help;
		this.byDefault = byDefault;
	}
	
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		
		/* Creation de la boite de dialogue en memoire */
		AttributeDialog inputDialog = new AttributeDialog(title,help,byDefault);
		
		/* Ouverture de la boite de dialogue */
		inputDialog.open();
		
		/* Recuperation des resultats */
		return inputDialog.getResult();
	}

}
