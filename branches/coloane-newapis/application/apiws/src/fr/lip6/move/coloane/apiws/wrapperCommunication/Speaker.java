package fr.lip6.move.coloane.apiws.wrapperCommunication;

import fr.lip6.move.coloane.apiws.ApiConnection;
import fr.lip6.move.coloane.apiws.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.stubs.GExceptionException0;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.AnswerDb;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.AnswerDbResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Authentification;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ChangeSession;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ChangeSessionResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.CloseSession;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.CloseSessionResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Connect;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ConnectResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.CreateSession;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.CreateSessionResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.DialogBox;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Disconnect;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.DisconnectResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteService;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithObjects;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithObjectsResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithOneObject;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithOneObjectResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithOneText;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithOneTextResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithTexts;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithTextsResponse;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Model;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Option;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Question;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.RService;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Service;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithObjects;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithOneObject;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithOneText;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithTexts;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Session;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Unauthentification;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.axis2.AxisFault;

/**
 * Cette classe représent un speaker pour communiquer avec le wrapper.
 *
 * @author Monir CHAOUKI
 */
public class Speaker implements ISpeaker {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private Authentification auth = null;

	private WrapperStub stub = null;

	private Map<Integer, Object> listObservables = null;

	private ApiConnection connection;

	/**
	 * Constructeur
	 * @param ipServer adresse IP du serveur où se trouve le wrapper
	 * @param portServer port du serveur où se trouve le wrapper
	 * @param cheminServer le chemin où se trouve le wrapper
	 * @param listObservables les observables
	 * @param connection la connexion
	 */
	public Speaker(String ipServer, int portServer, String cheminServer, Map<Integer, Object> listObservables, ApiConnection connection) {
		try {
			// Initialistion du stub de communication
			stub = new WrapperStub("http://" + ipServer + ":" + portServer + cheminServer);

			// Initialisation du timeout à 240 secondes
			org.apache.axis2.client.Options op = stub._getServiceClient().getOptions();
			op.setTimeOutInMilliSeconds(240000);
			stub._getServiceClient().setOptions(op);

			this.listObservables = listObservables;
			this.connection = connection;

			LOGGER.finer("Création du Speaker");

		} catch (AxisFault e) {
			LOGGER.warning("Echec de la création du Speaker: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final Authentification getAuthentification() {
		return auth;
	}

	/**
	 * {@inheritDoc}
	 */
	public final WrapperStub getStub() {
		return stub;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Authentification openConnection(String login, String pass) throws ApiException {
		try {

			if (stub == null) {
				throw new ApiException("Error of communcation : Stub is null");
			}

			// Construction d'une requête pour l'ouverture de la connexion
            LOGGER.finest("Construction de la requête pour l'ouverture de la connexion");
			Connect req = new Connect();
			req.setLogin(login);
			req.setMdp(pass);

			LOGGER.finest("Envoie de la requête au wrapper pour l'ouverture de la connexion");
			ConnectResponse res = stub.connect(req);
			auth = res.get_return();
			LOGGER.finest("Fin de l'envoie de la requête au wrapper pour l'ouverture de la connexion");

		} catch (RemoteException e) {
			LOGGER.warning("Erreur de l'ouverture de la connexion: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible d'ouvrir une connexion: " + e.getMessage());
		} catch (GExceptionException0 e) {
			LOGGER.warning("Erreur de l'ouverture de la connexion: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible d'ouvrir une connexion: " + e.getMessage());
		}
		return auth;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Session openSession(String nameFormalism) throws ApiException {
		Session session = null;

		try {
            if (stub == null) {
				throw new ApiException("Error of communcation : Stub is null");
            }

            // Construction d'une requête pour l'ouverture d'une session
            LOGGER.finest("Construction de la requête pour l'ouverture d'une session");
            CreateSession req = new CreateSession();
            req.setNameFormalism(nameFormalism);
            req.setUid(auth);

            LOGGER.finest("Envoie de la requête au wrapper pour l'ouverture d'une session");
            CreateSessionResponse res = stub.createSession(req);
            session = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour l'ouverture d'une session");

        } catch (RemoteException e) {
			LOGGER.warning("Erreur de l'ouverture d'une session: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible d'ouvrir une session: " + e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur de l'ouverture d'une session: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible d'ouvrir une session: " + e.getMessage());
        }

		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Session changeSession(String idSession) throws ApiException {
		Session session = null;

		try {
			if (stub == null) {
				throw new ApiException("Error of communcation : Stub is null");
			}

			// Construction d'une requête pour le changement de session i.e. restauration d'une session
			LOGGER.finest("Construction de la requête pour le changement de session");
			ChangeSession req = new ChangeSession();
			req.setUid(auth);
			req.setIdSession(idSession);

			LOGGER.finest("Envoie de la requête au wrapper pour le changement de session");
			ChangeSessionResponse res = stub.changeSession(req);
			session = res.get_return();
			LOGGER.finest("Fin de l'envoie de la requête au wrapper pour le changement de session");

		} catch (RemoteException e) {
			LOGGER.warning("Erreur lors du changement de session: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible de changer de session: " + e.getMessage());
		} catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors du changement de session: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible de changer de session: " + e.getMessage());
		}

		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Session closeSession(String idSession) throws ApiException {
		Session session = null;

        try {
            if (stub == null) {
				throw new ApiException("Error of communcation : Stub is null");
            }

            // Construction d'une requête pour la fermeture d'une session
            LOGGER.finest("Construction de la requête pour la fermeture d'une session");
            CloseSession req = new CloseSession();
            req.setUid(auth);
            req.setIdSession(idSession);

            LOGGER.finest("Envoie de la requête au wrapper pour la fermeture d'une session");
            CloseSessionResponse res = stub.closeSession(req);
            session = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour la fermeture d'une session");

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de la fermeture d'une session: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible de fermer une session: " + e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de la fermeture d'une session: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible de fermer une session: " + e.getMessage());
        }

        return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Unauthentification closeConnection() throws ApiException {
		Unauthentification unauth = null;

		try {

			if (stub == null) {
				throw new ApiException("Error of communcation : Stub is null");
			}

			// Construction d'une requête pour la fermeture de la connexion
			LOGGER.finest("Construction de la requête pour la fermeture d'une conexion");
            Disconnect req = new Disconnect();
            req.setId(auth);

            LOGGER.finest("Envoie de la requête au wrapper pour la fermeture d'une conexion");
            DisconnectResponse res = stub.disconnect(req);
            unauth = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour la fermeture d'une conexion");

		} catch (RemoteException e) {
			LOGGER.warning("Erreur lors de la fermeture de la connexion: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible de fermer la connexion: " + e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de la fermeture de la connexion: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException("Imposible de fermer la connexion: " + e.getMessage());
        }

        return unauth;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String answerToDialogBox(DialogBox answer) throws ApiException {
		String toReturn = null;

		try {
			if (stub == null) {
				throw new ApiException("Error of communcation : Stub is null");
			}

			// Construction d'une requête pour la réponse à une boîte de dialogue
			LOGGER.finest("Construction de la requête pour l'envoie d'une boîte de dialogue");
			AnswerDb req = new AnswerDb();
			req.setAuth(auth);
			req.setDialog(answer);

            LOGGER.finest("Envoie de la requête au wrapper pour la réponse à une boîte de dialogue");
			AnswerDbResponse res = stub.answerDb(req);
			toReturn = res.get_return();
			LOGGER.finest("Fin de l'envoie de la requête au wrapper pour la réponse à une boîte de dialogue");

		} catch (RemoteException e) {
			LOGGER.warning("Erreur lors de la reponse à une boîte de dialogue: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
		} catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de la reponse à une boîte de dialogue: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
		}

		return toReturn;
	}

	/**
	 * {@inheritDoc}
	 */
	public final RService executService(String idSession, Question root, Service question, List<Option> options, Model theModel) throws ApiException {

		RService toReturn = null;

		try {
            if (stub == null) {
                throw new ApiException("Error of communcation : Stub is null");
            }

            // Construction d'un requête pour l'exécution d'un service simple
            LOGGER.finest("Construction de la requête pour l'exécution d'un service simple");
            ExecuteService req = new ExecuteService();
            req.setUid(auth);
            req.setIdSession(idSession);
            req.setTheModel(theModel);
            req.setQuestion(question);
            req.setRoot(root);
            Option[] ops;
            if (options != null && options.size() > 0) {
                ops = new  Option[options.size()];
                int i = 0;
                for (Option op : options) {
                    ops[i++] = op;
                }
            } else {
                ops = new  Option[1];
                ops[0] = null;
            }
            req.setOptions(ops);

            LOGGER.finest("Envoie de la requête au wrapper pour l'exécution d'un service simple");
            ExecuteServiceResponse res = stub.executeService(req);
            toReturn = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour l'exécution d'un service simple");

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        }

        return toReturn;

	}

	/**
	 * {@inheritDoc}
	 */
	public final RService executeServiceWithObjects(String idSession, Question root, ServiceWithObjects question, List<Option> options, Model theModel) throws ApiException {

		RService toReturn = null;

		try {
            if (stub == null) {
                throw new ApiException("Error of communcation : Stub is null");
            }

            // Construction d'un requête pour l'exécution d'un service sur des objects
            LOGGER.finest("Construction de la requête pour l'exécution d'un service sur des objects");
            ExecuteServiceWithObjects req = new ExecuteServiceWithObjects();
            req.setUid(auth);
            req.setIdSession(idSession);
            req.setTheModel(theModel);
            req.setQuestion(question);
            req.setRoot(root);
            Option[] ops;
            if (options != null && options.size() > 0) {
                ops = new  Option[options.size()];
                int i = 0;
                for (Option op : options) {
                    ops[i++] = op;
                }
            } else {
                ops = new  Option[1];
                ops[0] = null;
            }
            req.setOptions(ops);

            LOGGER.finest("Envoie de la requête au wrapper pour l'exécution d'un service sur des objects");
            ExecuteServiceWithObjectsResponse res = stub.executeServiceWithObjects(req);
            toReturn = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour l'exécution d'un service sur des objects");

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        }

        return toReturn;

	}

	/**
	 * {@inheritDoc}
	 */
	public final RService executeServiceWithOneObject(String idSession,	Question root, ServiceWithOneObject question, List<Option> options,	Model theModel) throws ApiException {

		RService toReturn = null;

		try {
            if (stub == null) {
                throw new ApiException("Error of communcation : Stub is null");
            }

            // Construction d'un requête pour l'exécution d'un service sur un objet
            LOGGER.finest("Construction de la requête pour l'exécution d'un service sur un objet");
            ExecuteServiceWithOneObject req = new ExecuteServiceWithOneObject();
            req.setUid(auth);
            req.setIdSession(idSession);
            req.setTheModel(theModel);
            req.setQuestion(question);
            req.setRoot(root);
            Option[] ops;
            if (options != null && options.size() > 0) {
                ops = new  Option[options.size()];
                int i = 0;
                for (Option op : options) {
                    ops[i++] = op;
                }
            } else {
                ops = new  Option[1];
                ops[0] = null;
            }
            req.setOptions(ops);

            LOGGER.finest("Envoie de la requête au wrapper pour l'exécution d'un service sur un objet");
            ExecuteServiceWithOneObjectResponse res = stub.executeServiceWithOneObject(req);
            toReturn = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour l'exécution d'un service sur un objet");

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        }

        return toReturn;

	}

	/**
	 * {@inheritDoc}
	 */
	public final RService executeServiceWithOneText(String idSession, Question root, ServiceWithOneText question, List<Option> options, Model theModel) throws ApiException {

		RService toReturn = null;

		try {
            if (stub == null) {
                throw new ApiException("Error of communcation : Stub is null");
            }

            // Construction d'un requête pour l'exécution d'un service sur un texte
            LOGGER.finest("Construction de la requête pour l'exécution d'un service sur un texte");
            ExecuteServiceWithOneText req = new ExecuteServiceWithOneText();
            req.setUid(auth);
            req.setIdSession(idSession);
            req.setTheModel(theModel);
            req.setQuestion(question);
            req.setRoot(root);
            Option[] ops;
            if (options != null && options.size() > 0) {
                ops = new  Option[options.size()];
                int i = 0;
                for (Option op : options) {
                    ops[i++] = op;
                }
            } else {
                ops = new  Option[1];
                ops[0] = null;
            }
            req.setOptions(ops);

            LOGGER.finest("Envoie de la requête au wrapper pour l'exécution d'un service sur un texte");
            ExecuteServiceWithOneTextResponse res = stub.executeServiceWithOneText(req);
            toReturn = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour l'exécution d'un service sur un texte");

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        }

        return toReturn;

	}

	/**
	 * {@inheritDoc}
	 */
	public final RService executeServiceWithTexts(String idSession, Question root, ServiceWithTexts question, List<Option> options, Model theModel) throws ApiException {

		RService toReturn = null;

		try {
            if (stub == null) {
                throw new ApiException("Error of communcation : Stub is null");
            }

            // Construction d'un requête pour l'exécution d'un service sur du texte
            LOGGER.finest("Construction de la requête pour l'exécution d'un service sur du texte");
            ExecuteServiceWithTexts req = new ExecuteServiceWithTexts();
            req.setUid(auth);
            req.setIdSession(idSession);
            req.setTheModel(theModel);
            req.setQuestion(question);
            req.setRoot(root);
            Option[] ops;
            if (options != null && options.size() > 0) {
                ops = new  Option[options.size()];
                int i = 0;
                for (Option op : options) {
                    ops[i++] = op;
                }
            } else {
                ops = new  Option[1];
                ops[0] = null;
            }
            req.setOptions(ops);

            LOGGER.finest("Envoie de la requête au wrapper pour l'exécution d'un service sur du texte");
            ExecuteServiceWithTextsResponse res = stub.executeServiceWithTexts(req);
            toReturn = res.get_return();
            LOGGER.finest("Fin de l'envoie de la requête au wrapper pour l'exécution d'un service sur du texte");

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GExceptionException0 e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
			// Si c'est une erreur grave alors en force la fermeture de la connexion et
			// on notifie l'observateur de l'événement: BrutalInterupt
			if (getLevelException(e.getMessage()) >= 1) {
				connection.closeConnectionError();
				((IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT)).notifyObservers(e.getMessage());
			}
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        }

        return toReturn;
	}

	/**
	 * Récupére le niveau de l'exception à partir du message d'erreur
	 * @param msgException le message d'erreur
	 * @return le niveau de l'exception
	 */
	private int getLevelException(String msgException) {
        //String type;
        //String mess;
        //int error;
        int level;
        if (msgException.indexOf("FKException") != -1 || msgException.indexOf("WException") != -1) {
            int nb1 = 0;
            int nb2 = msgException.indexOf(":", nb1);
            //type = msgException.substring(nb1, nb2);
            nb1 = nb2 + 1;
            nb2 = msgException.indexOf(":", nb1);
            //error = new Integer(msgException.substring(nb1, nb2)).intValue();
            nb1 = nb2 + 1;
            nb2 = msgException.indexOf(":", nb1);
            level = new Integer(msgException.substring(nb1, nb2)).intValue();
            nb1 = nb2 + 1;
            nb2 = msgException.length();
            //mess =msgException.substring(nb1, nb2);
            return level;
        }
        return 99;
    }

}
