package fr.lip6.move.coloane.apiws.objects.dialog;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;

public class DialogAnswerForWrapper {
	
	private DialogBox dialogAnswer;
	
	public DialogAnswerForWrapper(IDialogAnswer dialogAnswer){
		// TODO Creer le constructeur pour la boite de dialog a fournir au wrapper
	}
	
	public DialogBox getDialogAnswer(){
		return dialogAnswer;
	}
}