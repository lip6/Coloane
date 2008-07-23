package fr.lip6.move.coloane.interfaces.objects.dialog;

import java.util.List;

/**
 * Interface fournie par Coloane a l'API.
 * Elle contient toutes les informations pour manipuler les resultats.
 * envoye par Coloane a la suite d'un affichage d'une boite de dialogue.
 */
public interface IDialogAnswer {

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
	boolean isModified();

	/**
	 * Récupére la valeur saisie dans la boîte de dialogue.
	 * @return La valeur saisie dans la boîte de dialogue.
	 */
	String getValue();

	/**
	 * Récupere la liste d'objets faisant parti de la réponse.
	 * @return La liste d'objets faisant parti de la réponse.
	 */
	List<Integer> getObjects();

}
