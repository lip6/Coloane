package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSendDialog;
import fr.lip6.move.coloane.apiws.interfaces.observers.ISendDialogObserver;

public interface ISendDialogObservable {

	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : envoie d'une boite de dialogue
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(ISendDialogObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : envoie d'une boite de dialogue
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(ISendDialogObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : envoie d'une boite de dialogue
	 */
	public void notifyObservers(IAnswerSendDialog e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
