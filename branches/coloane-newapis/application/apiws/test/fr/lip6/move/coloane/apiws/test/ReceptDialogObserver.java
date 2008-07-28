package fr.lip6.move.coloane.apiws.test;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

/**
 * Observeur des boîtes de dialogues.
 */
public class ReceptDialogObserver implements IReceptDialogObserver {

	private int idDialog;

	private IApiSession session;

	/**
	 * {@inheritDoc}
	 */
	public final void update(IDialog dialog) {
		// TODO Auto-generated method stub
		System.out.println("DIALOG");
		this.idDialog = dialog.getId();
		try {
			session.sendDialogAnswer(new DialogAnswer(getIdDialog(), IDialog.DLG_NO_BUTTON, false, null, null));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @return l'identifiant de la boîte de dialogue.
	 */
	public final int getIdDialog() {
		return idDialog;
	}

	/**
	 * @param s la session a qui appartient la boîte de dialogue
	 */
	public final void setSession(IApiSession s) {
		this.session = s;
	}

}
