package fr.lip6.move.coloane.apiws.wrapperCommunication;

import fr.lip6.move.coloane.apiws.evenements.ReceptMessage;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.coloane.apiws.objects.dialog.Dialog;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.wrapper.ws.GException;
import fr.lip6.move.wrapper.ws.WrapperStub;
import fr.lip6.move.wrapper.ws.WrapperStub.AsyncMessage;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.Ping;
import fr.lip6.move.wrapper.ws.WrapperStub.PingResponse;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Cette classe représent un écouteur pour les messages asynchrone.
 */
public class Listener extends Thread implements IListener {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private Authentification auth = null;
	private WrapperStub stub = null;
	private int durePing;
	private boolean stopThread = false;

	private Map<Integer, Object> listObservable = null;

	/**
	 * Constructeur
	 * @param auth l'objet Authentification pour identifier l'utilisateur
	 * @param stub le stub de communication
	 * @param listObservables la liste des observables à notifier
	 */
	public Listener(Authentification auth, WrapperStub stub, Map<Integer, Object> listObservables) {
		this.auth = auth;
		this.stub = stub;
		this.durePing = auth.getPeriodPing() * 1000;
		this.stopThread = false;
		this.listObservable = listObservables;

		LOGGER.finer("Création du Listener");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void run() {

		LOGGER.fine("Demmarage du Listener");

		boolean stop = false;

		while (!stop) {

			try {
				sleep(durePing);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			AsyncMessage message = null;

			try {
				if (stub == null) {
					throw new ApiException("Error of communcation : Stub is null");
				}

				Ping req = new Ping();
				req.setAuth(auth);

				PingResponse res = stub.ping(req);
				message = res.get_return();

				if (message.getTraces() != null) {
					for (int i = 0; i < message.getTraces().length; i++) {
						LOGGER.fine("Récéption d'un message");
						ReceptMessage m = new ReceptMessage(message.getTraces()[i].getNtype(), message.getTraces()[i].getMessage());
						((IReceptMessageObservable)  listObservable.get(IObservables.RECEPT_MESSAGE)).notifyObservers(m);
					}
				}

				if (message.getDbs() != null) {
					for (int i = 0; i < message.getDbs().length; i++) {
						LOGGER.fine("Récéption d'une boîte de dialogue");
						Dialog dialog = new Dialog(message.getDbs()[i]);
						((IReceptDialogObservable) listObservable.get(IObservables.RECEPT_DIALOG)).notifyObservers(dialog);
					}
				}

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized (this) {
				stop = this.stopThread;
			}

		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final synchronized void stopper() {
		this.stopThread = true;
		LOGGER.fine("Arrêt du Listener");
	}

}
