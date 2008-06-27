package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSendDialog;

public class AnswerSendDialog implements IAnswerSendDialog{
	
	private String returnMsg;
	
	public AnswerSendDialog(String m){
		this.returnMsg = m;
	}
	
	public String getReturnMsg(){
		return returnMsg;
	}
}
