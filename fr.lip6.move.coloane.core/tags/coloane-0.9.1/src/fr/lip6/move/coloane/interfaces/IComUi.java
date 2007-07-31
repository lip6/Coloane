package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.ui.dialogs.DialogResult;

public interface IComUi {
	
	/** Demande d'un service a la plateforme FK */
	public void askForService(String rootMenuName, String parentName, String serviceName);
	
	/** Transmet les reponses obtenues via une boite de dialogue */
	public void getDialogAnswers(DialogResult results);

}
