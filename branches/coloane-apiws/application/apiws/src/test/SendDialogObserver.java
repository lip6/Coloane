package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSendDialog;
import fr.lip6.move.coloane.apiws.interfaces.observers.ISendDialogObserver;

public class SendDialogObserver implements ISendDialogObserver{

	public void update(IAnswerSendDialog d) {
		System.out.println("SEND DIALOG");
		System.out.println("retour -> "+d.getReturnMsg());
		System.out.println("");
	}

}
