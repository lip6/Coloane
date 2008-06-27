package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAskDialog;
import fr.lip6.move.wrapper.ws.WrapperStub.AsyncMessage;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;

public class AskDialog implements IAskDialog{
	
	private DialogBox dialogBox;
	
	public AskDialog(AsyncMessage d){
		this.dialogBox = d.getDbs()[0];
	}
	
	public DialogBox getDialog(){
		return dialogBox;
	}

}
