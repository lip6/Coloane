package fr.lip6.move.coloane.interfaces.objects.dialog;

import java.util.List;

/**
 * Interface fournie par Coloane a l'API.
 * Elle contient toutes les informations pour manipuler les resultats.
 * envoye par Coloane a la suite d'un affichage d'une boite de dialogue.
 */
public interface IDialogAnswer {

	/** Pour les boites de dialogue simples */
	int BUTTON_OK = 1;
	int BUTTON_CANCEL = 2;

	/** Pour les boites de dialogues interactives */
	int BUTTON_ABORT = 1;
	int BUTTON_QUIT = 2;

	/**
	 * Récupére l'identifiant de la boîte de dialogue à la quel on répond.
	 * @return L'identifiant de la boîte de dialogue à la quel on répond.
	 */
	int getIdDialog();

	/**
	 * Récupére le type du button réponse choisi.
	 * @return Le type du button réponse choisi.
	 */
	int getButtonType();

	/**
	 * Détermine si la boîte de dialogue est modifiée.
	 * @return <code>true</code> si la boîte de dialogue est modifiée, <code>false</code> sinon.
	 */
	boolean hasBeenModified();

	/**
	 * Récupére la ou les valeur(s) saisie(s) dans la boîte de dialogue.
	 * @return La ou les valeur(s) saisie(s) dans la boîte de dialogue.
	 */
	List<String> getAllValue();

	/**
	 * Récupere la liste d'objets faisant parti de la réponse.
	 * @return La liste d'objets faisant parti de la réponse.
	 * @deprecated
	 */
	List<Integer> getObjects();

}
