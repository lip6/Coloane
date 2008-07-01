package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptTraceMessage;
import fr.lip6.move.coloane.apiws.interfaces.observers.ITraceMessageObserver;

public class TraceMessageObserver implements ITraceMessageObserver {

	public void update(IReceptTraceMessage m) {
		for(String s : m.getTraceMessages()){
			System.out.println("TRACE MESSAGE : "+s);
		}
	}

}
