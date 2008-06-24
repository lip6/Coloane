package fr.lip6.move.coloane.apiws.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptTraceMessage;
import fr.lip6.move.wrapper.ws.WrapperStub.AsyncMessage;

public class ReceptTraceMessage implements IReceptTraceMessage {
	
	private ArrayList<String> traceMessages;
	
	public ReceptTraceMessage(AsyncMessage m){
		this.traceMessages = new ArrayList<String>();
		for (int i=0;i<m.getTraces().length;i++){
			this.traceMessages.add(m.getTraces()[i]);
		}
	}
	
	public ArrayList<String> getTraceMessages(){
		return traceMessages;
	}

}
