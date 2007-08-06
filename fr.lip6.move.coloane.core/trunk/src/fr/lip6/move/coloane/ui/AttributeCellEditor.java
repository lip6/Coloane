package fr.lip6.move.coloane.ui;

import fr.lip6.move.coloane.ui.dialogs.AttributeDialog;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class AttributeCellEditor extends DialogCellEditor {
	private String title;
	private String help;
	private String byDefault;

	protected AttributeCellEditor(Composite p, String t, String h, String d) {
		super(p);
		this.title = t;
		this.help = h;
		this.byDefault = d;
	}

	@Override
	protected final Object openDialogBox(Control cellEditorWindow) {

		/* Creation de la boite de dialogue en memoire */
		AttributeDialog inputDialog = new AttributeDialog(title, help, byDefault);

		/* Ouverture de la boite de dialogue */
		inputDialog.open();

		/* Recuperation des resultats */
		return inputDialog.getResult();
	}

}
