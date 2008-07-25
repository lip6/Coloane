package fr.lip6.move.coloane.apiws.test;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

/**
 * Thread qui repond à la boîte de dialogue.
 */
public class DialogThread extends Thread {

	private IApiSession session;

	private IDialogAnswer dialogAnswer;

	/**
	 * Constructeur
	 * @param dialogAnswer la boite de dialogue réponse
	 */
	public DialogThread(IDialogAnswer dialogAnswer) {
		this.dialogAnswer = dialogAnswer;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void run() {
		try {
			session.sendDialogAnswer(dialogAnswer);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
