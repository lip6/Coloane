package fr.lip6.move.coloane.apiws.wrapperCommunication;

import fr.lip6.move.coloane.apiws.ApiConnection;
import fr.lip6.move.coloane.apiws.evenements.ReceptMessage;
import fr.lip6.move.coloane.apiws.evenements.ReceptServiceState;
import fr.lip6.move.coloane.apiws.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptServiceStateObservable;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.coloane.apiws.objects.dialog.Dialog;
import fr.lip6.move.coloane.apiws.stubs.GExceptionException0;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.AsyncMessage;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Authentification;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Ping;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.PingResponse;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Cette classe représent un écouteur pour les messages asynchrone.
 *
 * @author Monir CHAOUKI
 */
public class Listener extends Thread implements IListener {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private Authentification auth = null;
	private WrapperStub stub = null;
	private int durePing;
	private boolean stopThread = false;

	private Map<Integer, Object> listObservable = null;

	private ApiConnection connection = null;

	/**
	 * Constructeur
	 * @param auth l'objet Authentification pour identifier l'utilisateur
	 * @param stub le stub de communication
	 * @param listObservables la liste des observables à notifier
	 * @param connection la connexion
	 */
	public Listener(Authentification auth, WrapperStub stub, Map<Integer, Object> listObservables, ApiConnection connection) {
		this.auth = auth;
		this.stub = stub;
		this.durePing = auth.getPeriodPing() * 1000;
		this.stopThread = false;
		this.listObservable = listObservables;
		this.connection = connection;

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

				// Pause durant un certains temps pour ne pas se faire tuer par le wrapper
				sleep(durePing);

				AsyncMessage message = null;

				if (stub == null) {
					throw new ApiException("Error of communcation : Stub is null");
				}

				// Construction d'une requête pour demander s'il y a quelque-chose (messages asynchrones) à lire
				Ping req = new Ping();
				req.setAuth(auth);

				// Envoie de la requête pour demander s'il y a quelque-chose (messages asynchrones) à lire
				PingResponse res = stub.ping(req);
				message = res.get_return();

				// Test s'il y a des messages de traces à lire
				if (message.getTraces() != null) {
					// Parcours tous les messages et notifie l'observateur adéquat
					for (int i = 0; i < message.getTraces().length; i++) {
						LOGGER.fine("Récéption d'un message : " + message.getTraces()[i].getType() + " : " + message.getTraces()[i].getMessage());
						LOGGER.finest("Demande la notification de la récéption d'un message");
						ReceptMessage m = new ReceptMessage(getMyType(message.getTraces()[i].getNtype()), message.getTraces()[i].getMessage());
						((IReceptMessageObservable)  listObservable.get(IObservables.RECEPT_MESSAGE)).notifyObservers(m);

						// Si un message est une erreur, on notifie l'observateur adéquat et arrête on arrête le Listener
						if (getMyType(message.getTraces()[i].getNtype()) == IReceptMessage.ERROR_MESSAGE) {
							// Test si l'erreur grave est déjà traiter i.e. la connexion est déjà fermée
							if (connection.isConnectionClosedByError()) {
								LOGGER.fine("Récéption d'une erreur via le listener qui est déjà traité: " + message.getTraces()[i].getType() + " : " + message.getTraces()[i].getMessage());
							} else { // Sinon c'est une erreur grave "asynchronne" du wrapper
								LOGGER.fine("Récéption d'une erreur via le listener qui est non traité" + message.getTraces()[i].getType() + " : " + message.getTraces()[i].getMessage());
								// En force la fermeture de la connexion
								LOGGER.fine("Demande de deconnexion forcée suite a une erreur grave");
								connection.closeConnectionError();
								// Notifie les observateurs d'événement: récéption d'une erreur.
								LOGGER.finest("Demande la notification de la récéption d'un erreur grave");
								((IBrutalInterruptObservable) listObservable.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(message.getTraces()[i].getMessage());
								// On stoppe le Listener
								stopper();
							}
						}
					}
				}

				// Test s'il y a des boîtes de dialogues à afficher
				if (message.getDbs() != null) {
					// Parcours tous les boîtes de dialogue et notifie l'observateur adéquat
					for (int i = 0; i < message.getDbs().length; i++) {
						LOGGER.fine("Récéption d'une boîte de dialogue: TYPE = " + message.getDbs()[i].getType() + " TITRE = " + message.getDbs()[i].getTitle() + " MESSAGE = " + message.getDbs()[i].getMessage());
						LOGGER.finest("Demande la notification de la récéption d'une boîte de dialogue");
						Dialog dialog = new Dialog(message.getDbs()[i]);
						((IReceptDialogObservable) listObservable.get(IObservables.RECEPT_DIALOG)).notifyObservers(dialog);
					}
				}

				// Test s'il y a des informations sur l'exécution d'un service à lire
				if (message.getQts() != null) {
					// Parcours tous les informations sur l'exécution d'un service et notifie l'observateur adéquat
					for (int i = 0; i < message.getQts().length; i++) {
						LOGGER.fine("Récéption d'une information sur un service: " + message.getQts()[i].getNameQuestion() + " : " + message.getQts()[i].getMessage());
						LOGGER.finest("Demande la notification de la récéption d'une information sur l'exécution d'un service");
						ReceptServiceState serviceState = new ReceptServiceState(message.getQts()[i]);
						((IReceptServiceStateObservable) listObservable.get(IObservables.RECEPT_SERVICE_STATE)).notifyObservers(serviceState);
					}
				}

			} catch (RemoteException e) {
				LOGGER.warning("Erreur lors du ping: " + e.getMessage());
				// En force la fermeture de la connexion
				LOGGER.fine("Demande de deconnexion forcée suite a une erreur grave");
				connection.closeConnectionError();
				// Notifie les observateurs d'événement: récéption d'une erreur.
				LOGGER.finest("Demande la notification de la récéption d'un erreur grave");
				((IBrutalInterruptObservable) listObservable.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
				// On stoppe le Listener
				stopper();
				e.printStackTrace();
			} catch (GExceptionException0 e) {
				LOGGER.warning("Erreur lors du ping: " + e.getMessage());
				// En force la fermeture de la connexion
				LOGGER.fine("Demande de deconnexion forcée suite a une erreur grave");
				connection.closeConnectionError();
				// Notifie les observateurs d'événement: récéption d'une erreur.
				LOGGER.finest("Demande la notification de la récéption d'un erreur grave");
				((IBrutalInterruptObservable) listObservable.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
				// On stoppe le Listener
				stopper();
				e.printStackTrace();
			} catch (ApiException e) {
				LOGGER.warning("Erreur lors du ping: " + e.getMessage());
				// En force la fermeture de la connexion
				LOGGER.fine("Demande de deconnexion forcée suite a une erreur grave");
				connection.closeConnectionError();
				// Notifie les observateurs d'événement: récéption d'une erreur.
				LOGGER.finest("Demande la notification de la récéption d'un erreur grave");
				((IBrutalInterruptObservable) listObservable.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
				// On stoppe le Listener
				stopper();
				e.printStackTrace();
			} catch (InterruptedException e) {
				LOGGER.warning("Erreur lors du ping: " + e.getMessage());
				// En force la fermeture de la connexion
				LOGGER.fine("Demande de deconnexion forcée suite a une erreur grave");
				connection.closeConnectionError();
				// Notifie les observateurs d'événement: récéption d'une erreur.
				LOGGER.finest("Demande la notification de la récéption d'un erreur grave");
				((IBrutalInterruptObservable) listObservable.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
				// On stoppe le Listener
				stopper();
				e.printStackTrace();
			}

			// Réinitialise à chaque tour de boucle le boolean 'stop' pour savoir s'il faut arrêter le Listener
			synchronized (this) {
				stop = this.stopThread;
			}

		}
		LOGGER.fine("Arrêt du Listener");
	}

	/**
	 * {@inheritDoc}
	 */
	public final synchronized void stopper() {
		this.stopThread = true;
	}

	/**
	 * Cette methode permet de faire la correspendance entre les types de messages reçu de la part du wrapper
	 * avec nos propres types de messages.
	 * @param type le type de message reçu de la part du wrapper
	 * @return un de nos types de messages
	 */
	private synchronized int getMyType(int type) {
		switch (type) {
			case 0 : return IReceptMessage.ADMINISTRATOR_MESSAGE;
			case 1 : return IReceptMessage.COPYRIGHT_MESSAGE;
			case 2 : return IReceptMessage.TRACE_MESSAGE;
			case 3 : return IReceptMessage.WARNING_MESSAGE;
			case 4 : return IReceptMessage.ERROR_MESSAGE;
			default : return -1;
		}
	}

}
