package fr.lip6.move.coloane.apiws.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAskDialog;
import fr.lip6.move.coloane.apiws.interfaces.objects.dialog.IDialogBox;
import fr.lip6.move.coloane.apiws.objects.dialog.DialogBoxImpl;
import fr.lip6.move.wrapper.ws.WrapperStub.AsyncMessage;

public class AskDialog implements IAskDialog{
	
	private ArrayList<IDialogBox> dialogBoxs;
	
	public AskDialog(AsyncMessage d){
		this.dialogBoxs = new ArrayList<IDialogBox>();
		for (int i=0;i<d.getDbs().length;i++){
			this.dialogBoxs.add(new DialogBoxImpl(d.getDbs()[i]));
		}
	}
	
	public ArrayList<IDialogBox> getDialogs(){
		return dialogBoxs;
	}

}
