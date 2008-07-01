package fr.lip6.move.coloane.apiws.wrapperCommunication;

import java.rmi.RemoteException;
import java.util.HashMap;

import fr.lip6.move.coloane.apiws.evenements.AnswerSendDialog;
import fr.lip6.move.coloane.apiws.evenements.AskDialog;
import fr.lip6.move.coloane.apiws.evenements.ReceptTraceMessage;
import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.observables.IAskDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.ISendDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.ITraceMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.wrapper.ws.GExceptionException0;
import fr.lip6.move.wrapper.ws.WrapperStub;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDb;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDbResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.AsyncMessage;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.Ping;
import fr.lip6.move.wrapper.ws.WrapperStub.PingResponse;

public class Listener extends Thread implements IListener{

	private Authentification auth = null;

	private WrapperStub stub = null;
	
	private int durePing = 3*1000;

	private boolean stopThread = false;

	private HashMap<Integer, Object> listObservable= null;

	public Listener(Authentification auth, WrapperStub stub, HashMap<Integer, Object> listObservables){
		this.auth = auth;
		this.stub = stub;
		this.stopThread = false;
		this.listObservable = listObservables;
	}

	public void run() {

		boolean stop = false;

		while (!stop){ 
			try {
				sleep(durePing);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			AsyncMessage message = null;        

			try {
				if(stub==null)
					throw new WrapperException("Error of communcation : Stub is null",WrapperException.COMM_ERROR);
				Ping req = new Ping();
				req.setAuth(auth);
				PingResponse res=stub.ping(req);
				message=res.get_return();
			}catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GExceptionException0 e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WrapperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (message.getTraces() != null){
				ReceptTraceMessage traceMessage = new ReceptTraceMessage(message);
				((ITraceMessageObservable) listObservable.get(IObservables.TRACE_MESSAGE)).notifyObservers(traceMessage);
			}

			if (message.getDbs() != null){
				AskDialog dialog = new AskDialog(message);
				((IAskDialogObservable ) listObservable.get(IObservables.ASK_DIALOG)).notifyObservers(dialog,this);
			}

			synchronized(this) {
				stop = this.stopThread;
			}

		}
	}

	public synchronized void stopper() {
		this.stopThread = true;
	}


	public String answerToDialogBox(DialogBox answer) throws WrapperException{
		String toReturn = null;        

		try {
			if(stub==null)
				throw new WrapperException("Error of communcation : Stub is null",WrapperException.COMM_ERROR);
			AnswerDb req = new AnswerDb();
			req.setAuth(auth);
			req.setDialog(answer);
			AnswerDbResponse res=stub.answerDb(req);
			toReturn=res.get_return();
			
			AnswerSendDialog answerSendDialog = new AnswerSendDialog(toReturn); 
			((ISendDialogObservable) listObservable.get(IObservables.SEND_DIALOG)).notifyObservers(answerSendDialog); 
			
		}catch (RemoteException e) {
			WrapperException ee = new WrapperException();
			ee.initialize(e.getMessage());
			// TODO Auto-generated catch block
			throw ee;
		} catch (GExceptionException0 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return toReturn;    
	}


}