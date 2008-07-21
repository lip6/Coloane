package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.interfaces.IDialogResult;

import java.util.ArrayList;

/**
 * Classe regroupant tous les resultats d'une boite de dialogue
 */
public class DialogResult implements IDialogResult {

	/** L'identifiant de la boite de dialogue */
	private int dialogId;

	/** La reponse de la boite de dialogue : Le bouton clique par l'utilisateur */
	private int answerType;

	/** La valeur de la zone de texte a-t-elle ete modifiee */
	private boolean hasBeenModified;

	/** Le resultat */
	private ArrayList<String> answer;

	/** Le resultat provient-il d'une saisie multiligne ? */
	private boolean multiLine;

	/**
	 * Constructeur de la structure de resultats
	 * @param id L'identifiant de la boite de dialogue
	 * @param returnType Le type de reponse (@see {@link IDialog})
	 * @param modified Le resultat a-t-il ete modifie depuis l'affichage de la boite de dialogue
	 * @param returnedAnswer Le resutlat de la boite de dialogue
	 */
	public DialogResult(int id, int returnType, boolean modified, ArrayList<String> returnedAnswer, boolean multiLineAnswer) {
		this.dialogId = id;
		this.answerType = returnType;
		this.hasBeenModified = modified;
		this.answer = returnedAnswer;
		this.multiLine = multiLineAnswer;
	}

	/** {@inheritDoc} */
	public final ArrayList<String> getAnswer() {
		return answer;
	}

	/** {@inheritDoc} */
	public final int getAnswerType() {
		return answerType;
	}

	/** {@inheritDoc} */
	public final int getDialogId() {
		return dialogId;
	}

	/** {@inheritDoc} */
	public final boolean hasBeenModified() {
		return hasBeenModified;
	}

	/** {@inheritDoc} */
	public final boolean isMultiLineAnswer() {
		return multiLine;
	}
}
