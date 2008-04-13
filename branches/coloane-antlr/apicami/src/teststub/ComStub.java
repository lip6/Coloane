package teststub;



import fr.lip6.move.coloane.api.apiPackage.Api;
import fr.lip6.move.coloane.api.interfaces.IApiConnection;

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
	final static String LOGIN = "TP1";
	
	/**
	 * Password
	 */
	final static String PASSWORD = "123456";
	
	
	/**
	 * Test l'ouverture d'une connexion
	 *
	 */	
	private static void testOpenConnection(){
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
		connection.setBrutalInterruptObserver(new BrutalInterruptObserver());
		connection.setConnectionObserver(new ConnectionObserver());
		connection.setDialogObserver(new DialogObserver());
		connection.setServiceObserver(new ServiceObserver());
		connection.setServiceStateObserver(new ServiceStateObserver());
		connection.setSessionObserver(new SessionObserver());
		connection.setTraceMessageObserver(new TraceMessageObserver());
		connection.setWarningObserver(new WarningObserver());

		/** Test ouverture connexion */
		connection.openConnection();

		
	}

	/**
	 * Test l'ouverture d'une session
	 *
	 */
	public static void testOpenSession(){
	// TODO
	}
	
	public static void main(String args[]){
		
		/** Test l'ouverture de la connexion */
		testOpenConnection();
		
		/** Test l'ouverture d'une session */
		testOpenSession();

	}

}
