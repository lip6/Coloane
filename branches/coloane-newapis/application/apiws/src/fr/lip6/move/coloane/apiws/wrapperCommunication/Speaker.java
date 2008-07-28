package fr.lip6.move.coloane.apiws.wrapperCommunication;

import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.wrapper.ws.GException;
import fr.lip6.move.wrapper.ws.WrapperStub;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDb;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDbResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.ChangeSession;
import fr.lip6.move.wrapper.ws.WrapperStub.ChangeSessionResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.CloseSession;
import fr.lip6.move.wrapper.ws.WrapperStub.CloseSessionResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.Connect;
import fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.CreateSession;
import fr.lip6.move.wrapper.ws.WrapperStub.CreateSessionResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.Disconnect;
import fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.ExecuteService;
import fr.lip6.move.wrapper.ws.WrapperStub.ExecuteServiceResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.Model;
import fr.lip6.move.wrapper.ws.WrapperStub.Option;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.Unauthentification;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.axis2.AxisFault;

/**
 * Cette classe représent un speaker pour communiquer avec le wrapper.
 */
public class Speaker implements ISpeaker {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private Authentification auth = null;

	private WrapperStub stub = null;

	/**
	 * Constructeur
	 * @param ipServer adresse IP du serveur où se trouve le wrapper
	 * @param portServer port du serveur où se trouve le wrapper
	 * @param cheminServer le chemin où se trouve le wrapper
	 */
	public Speaker(String ipServer, int portServer, String cheminServer) {
		try {
			//System.out.println("Adresse: " + "http://" + ipServer + ":" + portServer + cheminServer);
			//stub = new WrapperStub("http://" + ipServer + ":" + portServer + cheminServer);
			stub = new WrapperStub("http://izanami.rsr.lip6.fr:8081/axis2/services/Wrapper");
			//Options op = stub._getServiceClient().getOptions();
			//op.setTimeOutInMilliSeconds(120000);
			//stub._getServiceClient().setOptions(op);

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
			Connect req = new Connect();
			req.setLogin(login);
			req.setMdp(pass);

			LOGGER.finer("Envoie de la requête pour l'ouverture de la connexion");
			ConnectResponse res = stub.connect(req);
			auth = res.get_return();

		} catch (RemoteException e) {
			LOGGER.warning("Erreur de l'ouverture de la connexion: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
		} catch (GException e) {
			LOGGER.warning("Erreur de l'ouverture de la connexion: " + e.getMessage());
			e.printStackTrace();
            throw new ApiException(e.getMessage());
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
            CreateSession req = new CreateSession();
            req.setNameFormalism(nameFormalism);
            req.setUid(auth);

            LOGGER.finer("Envoie de la requête pour l'ouverture d'une session");
            CreateSessionResponse res = stub.createSession(req);
            session = res.get_return();

        } catch (RemoteException e) {
			LOGGER.warning("Erreur de l'ouverture d'une session: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GException e) {
			LOGGER.warning("Erreur de l'ouverture d'une session: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
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
			ChangeSession req = new ChangeSession();
			req.setUid(auth);
			req.setIdSession(idSession);

			LOGGER.finer("Envoie de la requête pour le changement de session");
			ChangeSessionResponse res = stub.changeSession(req);
			session = res.get_return();

		} catch (RemoteException e) {
			LOGGER.warning("Erreur lors du changement de session: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
		} catch (GException e) {
			LOGGER.warning("Erreur lors du changement de session: " + e.getMessage());
			e.printStackTrace();
            throw new ApiException(e.getMessage());
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
            CloseSession req = new CloseSession();
            req.setUid(auth);
            req.setIdSession(idSession);

            LOGGER.finer("Envoie de la requête pour la fermeture d'une session");
            CloseSessionResponse res = stub.closeSession(req);
            session = res.get_return();

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de la fermeture d'une session: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GException e) {
			LOGGER.warning("Erreur lors de la fermeture d'une session: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
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
            Disconnect req = new Disconnect();
            req.setId(auth);

            LOGGER.finer("Envoie de la requête pour la fermeture d'une conexion");
            DisconnectResponse res = stub.disconnect(req);
            unauth = res.get_return();

		} catch (RemoteException e) {
			LOGGER.warning("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GException e) {
			LOGGER.warning("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
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
			AnswerDb req = new AnswerDb();
			req.setAuth(auth);
			req.setDialog(answer);

            LOGGER.finer("Envoie de la requête pour la réponse à une boîte de dialogue");
			AnswerDbResponse res = stub.answerDb(req);
			toReturn = res.get_return();

		} catch (RemoteException e) {
			LOGGER.warning("Erreur lors de la reponse à une boîte de dialogue: " + e.getMessage());
			e.printStackTrace();
            throw new ApiException(e.getMessage());
		} catch (GException e) {
			LOGGER.warning("Erreur lors de la reponse à une boîte de dialogue: " + e.getMessage());
			e.printStackTrace();
            throw new ApiException(e.getMessage());
		}

		return toReturn;
	}

	/**
	 * {@inheritDoc}
	 */
	public final RService executService(String idSession, Question root, Question question, List<Option> options, Model theModel) throws ApiException {

		RService toReturn = null;

		try {
            if (stub == null) {
                throw new ApiException("Error of communcation : Stub is null");
            }

            ////////////////////////////////// Attention ceci sera supprimer plus tard
            //Model m = new Model();
            //String chaine = createModel("/home/mchaouki/workspace64_newapis/fr.lip6.move.coloane.apiws/ressource/modele.txt");
            //m.setCami(chaine);
            //m.setParsing(true);
            //m.setInvalidate(false);
            //req.setTheModel(m);
            ///////////////////////////////////////////////////////////////////////////

            // Construction d'un requête pour l'exécution d'un service
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

            LOGGER.finer("Envoie de la requête pour l'exécution d'un service");
            ExecuteServiceResponse res = stub.executeService(req);
            toReturn = res.get_return();

        } catch (RemoteException e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        } catch (GException e) {
			LOGGER.warning("Erreur lors de l'execution d'un service: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        }

        return toReturn;

	}

}
