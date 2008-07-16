package fr.lip6.move.coloane.apiws.wrapperCommunication;

import java.rmi.RemoteException;
import java.util.HashMap;

import fr.lip6.move.coloane.apiws.evenements.ReceptMessage;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.coloane.apiws.objects.dialog.Dialog;
import fr.lip6.move.coloane.apiws.objects.dialog.DialogAnswerForWrapper;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.wrapper.ws.GExceptionException0;
import fr.lip6.move.wrapper.ws.WrapperStub;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDb;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDbResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.AsyncMessage;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.Ping;
import fr.lip6.move.wrapper.ws.WrapperStub.PingResponse;

public class Listener extends Thread implements IListener {

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
					throw new ApiException("Error of communcation : Stub is null");
				Ping req = new Ping();
				req.setAuth(auth);
				PingResponse res=stub.ping(req);
				message=res.get_return();
				
				if (message.getTraces() != null){
					for (int i=0;i<message.getTraces().length;i++){
						// TODO Passer en parametre plus tard le type du message
						ReceptMessage m = new ReceptMessage(0/* message.getType()*/,message.getTraces()[i].getMessage());
						((IReceptMessageObservable)  listObservable.get(IObservables.RECEPT_MESSAGE)).notifyObservers(m);
					}
				}

				if (message.getDbs() != null){
					for (int i=0; i<message.getDbs().length;i++){
						Dialog dialog = new Dialog(message.getDbs()[i]);
						((IReceptDialogObservable) listObservable.get(IObservables.RECEPT_DIALOG)).notifyObservers(dialog);
					}
				}
				
			}catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GExceptionException0 e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized(this) {
				stop = this.stopThread;
			}

		}
	}

	public synchronized void stopper() {
		this.stopThread = true;
	}


	public String answerToDialogBox(IDialogAnswer dialogAnswer) throws ApiException{
		String toReturn = null;
		
		DialogBox answer = new DialogAnswerForWrapper(dialogAnswer).getDialogAnswer();

		try {
			if(stub==null)
				throw new ApiException("Error of communcation : Stub is null");
			AnswerDb req = new AnswerDb();
			req.setAuth(auth);
			req.setDialog(answer);
			AnswerDbResponse res=stub.answerDb(req);
			toReturn=res.get_return();
			
		}catch (RemoteException e) {
			ApiException ee = new ApiException(e.getMessage());
			// TODO Auto-generated catch block
			throw ee;
		} catch (GExceptionException0 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return toReturn;    
	}

}