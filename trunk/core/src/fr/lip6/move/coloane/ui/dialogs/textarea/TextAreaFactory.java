package fr.lip6.move.coloane.ui.dialogs.textarea;

import org.eclipse.swt.widgets.Composite;


/**
 * Usine a zones de texte.
 * Plusieurs zone de texte sont possible pour chaque boite de dialogue.
 * En fonction des parametres inputType et multiLine, la zone de texte differe
 *
 */
public class TextAreaFactory {

	protected TextAreaFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construction de la zone de texte
	 * @param parent La boite de dialogue en cours de construction
	 * @param inputType Le type de saisie
	 * @param multiLine Indicateur de saisie multiligne
	 * @param defaultVal Valeur par default de la zone texte
	 * @return TextArea Une zone de texte
	 */
	public static TextArea create(Composite parent, int inputType, int multiLine, String defaultVal) {

		if (inputType == TextArea.INPUT_FORBIDDEN && multiLine == TextArea.SINGLE_LINE) {
			return new DummyTextArea(parent);
		} else if (inputType == TextArea.INPUT_FORBIDDEN && multiLine != TextArea.SINGLE_LINE) {
			return new ListTextArea(parent,	multiLine, defaultVal);
		} else {
			return new EditableTextArea(parent, multiLine, defaultVal);
		}
	}
}
