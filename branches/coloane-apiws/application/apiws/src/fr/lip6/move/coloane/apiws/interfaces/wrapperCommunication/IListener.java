package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.wrapper.ws.CException;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;

public interface IListener {

	/**
	 * Lancer le listener
	 */
	public void start();
	
	/**
	 * Stopper le listener
	 */
	public void stopper();
	
	/**
	 * Enovyer la reponse a une boite de dialogue au wrapper
	 */
	public String answerToDialogBox(DialogBox answer) throws CException;
	
}
