package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import java.util.List;

/**
 * Interface définissant une zone de texte pour une boite de dialogue
 */
public interface ITextArea {

	/**
	 * Retourne le texte de la zone de texte
	 * @return Un ensemble de chaines de caracteres
	 */
	List<String> getText();

	/**
	 * Retourne la valeur par defaut de la zone de texte
	 * @return La valeur par defaut de la zone de texte
	 */
	String getDefaultValue();
}
