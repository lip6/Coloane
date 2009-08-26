package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import org.eclipse.swt.widgets.Composite;


/**
 * Usine a zones de texte.
 * Plusieurs zone de texte sont possible pour chaque boite de dialogue.
 * En fonction des parametres inputType et multiLine, la zone de texte differe
 *
 */
public final class TextAreaFactory {

	/**
	 * Classe statique
	 */
	private TextAreaFactory() { }

	/**
	 * Construction de la zone de texte
	 * @param parent La boite de dialogue en cours de construction
	 * @param inputType Le type de saisie
	 * @param multiLine Indicateur de saisie multiligne
	 * @param defaultVal Valeur par default de la zone texte
	 * @return TextArea Une zone de texte
	 */
	public static TextArea create(Composite parent, int inputType, int multiLine, String defaultVal) {

		if (inputType == IDialog.INPUT_FORBIDDEN && multiLine == IDialog.SINGLE_LINE) {
			return new DummyTextArea(parent);
		} else if (inputType == IDialog.INPUT_FORBIDDEN && multiLine != IDialog.SINGLE_LINE) {
			return new ListTextArea(parent,	multiLine, defaultVal);
		} else {
			return new EditableTextArea(parent, multiLine, defaultVal);
		}
	}
}
