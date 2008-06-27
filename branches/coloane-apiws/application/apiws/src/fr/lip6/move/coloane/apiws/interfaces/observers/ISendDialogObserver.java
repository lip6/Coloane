package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSendDialog;

public interface ISendDialogObserver {
	
	/**
	 * Mettre a jour l'observateur d'evenement : envoie d'une de boite de dialogue vers le wrapper
	 * @param d la reponse du wrapper a la boite de dialogue a envoyer aux observateurs de l'evenement
	 */
	public void update(IAnswerSendDialog d);
}
