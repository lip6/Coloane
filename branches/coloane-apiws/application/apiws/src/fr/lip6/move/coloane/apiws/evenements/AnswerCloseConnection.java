package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseConnection;
import fr.lip6.move.wrapper.ws.WrapperStub.Unauthentification;

public class AnswerCloseConnection implements IAnswerCloseConnection{
	
	private int[] id;
	
	public AnswerCloseConnection(Unauthentification u){
		this.id = u.getId();
	}
	
	public int[] getId(){
		return id;
	}
}
