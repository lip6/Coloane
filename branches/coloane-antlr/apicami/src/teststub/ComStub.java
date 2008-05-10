package teststub;


import java.io.IOException;

import fr.lip6.move.coloane.api.apiPackage.Api;
import fr.lip6.move.coloane.api.interfaces.IApiConnection;
import fr.lip6.move.coloane.api.interfaces.IApiSession;


/**
 *
 * @author UU & KAHOO
 *
 * Bouchon de test coté Coloane
 */

public class ComStub {

	/**
	 * Adresse Ip de la machine à contacter
	 */
	final static String IP_ADRESS = "127.0.0.1" ;

	/**
	 * Port d'écoute de FrameKit
	 */
	final static int PORT = 7001;

	/**
	 * Login
	 */
	final static String LOGIN = "Administrator";

	/**
	 * Password
	 */
	final static String PASSWORD = "123456";


	/**
	 * Test l'ouverture d'une connexion
	 * @throws IOException
	 *
	 */
	private static IApiConnection testOpenConnection() throws IOException{
		/** Demander un objet implémentant l'interface ApiConnection */
		IApiConnection connection = Api.getApiConnection();

		/** Configurer la connexion
		 * IP + PORT + LOGIN + PASSWORD
		 */
		connection.setIPAdress(IP_ADRESS);
		connection.setPort(PORT);
		connection.setLogin(LOGIN);
		connection.setPassWord(PASSWORD);

		/** configurer les objets de callback */
		connection.setBrutalInterruptObserver(new BrutalInterruptObserver(), false);
		connection.setConnectionObserver(new ConnectionObserver(), false);
		connection.setDialogObserver(new DialogObserver(), false);
		connection.setServiceObserver(new ServiceObserver(), false);
		connection.setServiceStateObserver(new ServiceStateObserver(), false);
		connection.setSessionObserver(new SessionObserver(), false);
		connection.setTraceMessageObserver(new TraceMessageObserver(), false);
		connection.setWarningObserver(new WarningObserver(), false);

		/** Test ouverture connexion */
		connection.openConnection();
		return connection;

	}

	/**
	 * Test l'ouverture d'une session
	 * @throws IOException
	 * @throws InterruptedException
	 *
	 */
	public static IApiSession testOpenSession(IApiConnection connection, String nomSession) throws IOException, InterruptedException{
		IApiSession session = connection.getAPISession();
		session.openSession("241483", "AMI-Net", nomSession);
		return session;
	}

	public static void main(String args[]) throws IOException, InterruptedException{

		 Api.initialize();

		/** Test l'ouverture de la connexion */
		IApiConnection connection = testOpenConnection();

		/** Test l'ouverture d'une session */
		IApiSession session = testOpenSession(connection, "premier.petri");


		Thread.sleep(10000);
		System.out.println("2eme session");
		/** Test l'ouverture d'une seconde ession */
		IApiSession session2 = testOpenSession(connection, "second.petri");



		Thread.sleep(10000);
		System.out.println("\n\n\nreprise de la 1ére session");
        session.resumeSession();


	}

}
