package fr.lip6.move.coloane.interfaces;

import java.util.ArrayList;

/**
 * Interface fournie par Coloane a l'API
 * Elle contient toutes les informations pour manipuler les resultats
 * envoye par Coloane a la suite d'un affichage d'une boite de dialogue
 */
public interface IDialogResult {

	/**
	 * Retourne le texte qui a ete saisi dans la boite de dialogue
	 * @return Un tableau de lignes saisies
	 */
	ArrayList<String> getAnswer();

	/**
	 * Retourne le type de reponse de Coloane
	 * @return un type de reponse
	 * @see IDialogCom
	 */
	int getAnswerType();

	/**
	 * Indique l'identifiant de la boite de dialogue consideree
	 * @return Un identifiant
	 */
	int getDialogId();

	/**
	 * Indique si le contenu de la boite de dialogue a ete modifiee
	 * @return Un booleen
	 */
	boolean hasBeenModified();

}
