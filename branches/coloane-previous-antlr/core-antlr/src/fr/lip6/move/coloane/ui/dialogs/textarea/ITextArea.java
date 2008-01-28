package fr.lip6.move.coloane.ui.dialogs.textarea;

import java.util.ArrayList;

public interface ITextArea {

	/**
	 * Ajout d'un choix dans la liste des items
	 * @param choice Le nouveau choix a ajouter
	 */
	void addChoice(String choice);

	/**
	 * Retourne le texte de la zone de texte
	 * @return Un ensemble de chaines de caracteres
	 */
	ArrayList<String> getText();

	/**
	 * Retourne la valeur par defaut de la zone de texte
	 * @return La valeur par defaut de la zone de texte
	 */
	String getDefaultValue();
}
