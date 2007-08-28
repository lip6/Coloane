package fr.lip6.move.coloane.ui;

import fr.lip6.move.coloane.ui.dialogs.AttributeDialog;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Description de l'editeur de proprietes
 */
public class AttributeCellEditor extends DialogCellEditor {
	/** Le titre de la boite de dialogue */
	private String title;

	/** Le message d'aide */
	private String help;

	/** La valeur par defaut */
	private String byDefault;

	/**
	 * Constructeur
	 * @param parent Composite
	 * @param editorTitle Le titre de la boite de dialogue
	 * @param editorHelp Le message d'aide associe a l'editeur
	 * @param editorDefault Le texte par defaut a faire figurer dans la boite de texte
	 */
	protected AttributeCellEditor(Composite parent, String editorTitle, String editorHelp, String editorDefault) {
		super(parent);
		this.title = editorTitle;
		this.help = editorHelp;
		this.byDefault = editorDefault;
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
