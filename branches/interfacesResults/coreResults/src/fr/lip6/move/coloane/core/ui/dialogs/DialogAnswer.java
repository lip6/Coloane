package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe regroupant tous les resultats d'une boite de dialogue
 */
public class DialogAnswer implements IDialogAnswer {

	/** L'identifiant de la boite de dialogue */
	private int dialogId;

	/** La reponse de la boite de dialogue : Le bouton clique par l'utilisateur */
	private int buttonType;

	/** La valeur de la zone de texte a-t-elle ete modifiee */
	private boolean modified;

	/** Le resultat */
	private List<String> answer;

	/**
	 * Constructeur de la structure de resultats
	 * @param id L'identifiant de la boite de dialogue
	 * @param buttonType Le type de reponse (@see {@link IDialogUI})
	 * @param modified Le resultat a-t-il ete modifie depuis l'affichage de la boite de dialogue
	 * @param returnedAnswer Le resutlat de la boite de dialogue
	 */
	public DialogAnswer(int id, int buttonType, boolean modified, List<String> returnedAnswer) {
		this.dialogId = id;
		this.buttonType = buttonType;
		this.modified = modified;
		this.answer = returnedAnswer;
	}

	/** {@inheritDoc} */
	public final List<String> getAllValue() {
		return answer;
	}

	/** {@inheritDoc} */
	public final int getButtonType() {
		return buttonType;
	}

	/** {@inheritDoc} */
	public final int getIdDialog() {
		return dialogId;
	}

	/** {@inheritDoc} */
	public final List<Integer> getObjects() {
		return new ArrayList<Integer>(0);
	}

	/** {@inheritDoc} */
	public final boolean hasBeenModified() {
		return modified;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder("Answer :\n"); //$NON-NLS-1$
		sb.append(" * id : ").append(dialogId).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(" * button type : ").append(buttonType).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(" * modified : ").append(modified).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(" * message : "); //$NON-NLS-1$
		for (String line : answer) {
			sb.append("\n").append(line); //$NON-NLS-1$
		}
		return sb.toString();
	}
}
