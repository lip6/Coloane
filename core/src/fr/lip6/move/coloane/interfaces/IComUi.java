package fr.lip6.move.coloane.interfaces;

public interface IComUi {

	/** Demande d'un service a la plateforme FK */
	void askForService(String rootMenuName, String parentName, String serviceName);

	/** Transmet les reponses obtenues via une boite de dialogue */
	void getDialogAnswers(IDialogResult results);

}
