package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

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
	 * Repond a la boite de dialog
	 * @param dialogAnswer la boite de dialog reponse
	 * @return Un message decrivant la bonne reception de la boite de dialog reponse
	 * @throws ApiException
	 */
	public String answerToDialogBox(IDialogAnswer dialogAnswer) throws ApiException;
}
