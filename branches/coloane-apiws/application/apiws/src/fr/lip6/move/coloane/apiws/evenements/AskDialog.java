package fr.lip6.move.coloane.apiws.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAskDialog;
import fr.lip6.move.wrapper.ws.WrapperStub.AsyncMessage;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;

public class AskDialog implements IAskDialog{
	
	private ArrayList<DialogBox> dialogBox;
	
	public AskDialog(AsyncMessage d){
		this.dialogBox = new ArrayList<DialogBox>();
		for (int i=0;i<d.getDbs().length;i++){
			this.dialogBox.add(d.getDbs()[i]);
		}
	}
	
	public ArrayList<DialogBox> getDialogs(){
		return dialogBox;
	}

}
