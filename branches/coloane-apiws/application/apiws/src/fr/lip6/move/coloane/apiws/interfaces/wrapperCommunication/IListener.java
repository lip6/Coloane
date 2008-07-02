package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.objects.dialog.IDialogBox;

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
	public String answerToDialogBox(IDialogBox dialog) throws WrapperException;
	
}
