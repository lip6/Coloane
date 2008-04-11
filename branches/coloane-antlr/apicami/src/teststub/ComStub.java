package teststub;


import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IAPIConnection;
import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.api.interfaces.observers.*;

public class ComStub {

	final static String IP_ADRESS = "10.0.0.1" ; // Adresse Ip de la machine à contacter à définir
	final static int PORT = 1700 // Port d'écoute de FrameKit à définir
	final static String LOGIN = "TP1";
	final static String PASSWORD = "123456";


	public static void main(String args[]){

		/** Instancier un objet Api	 */
		Api api = new Api();

		/** Demander un objet implémentant l'interface ApiConnection */
		IAPIConnection connection = Api.getApiConnection();

		/** Configurer la connexion
		 * IP + PORT + LOGIN + PASSWORD
		 */
		connection.setIPAdress(IP_ADRESS);
		connection.setPort(PORT);
		connection.setLogin(LOGIN);
		connection.setPassWord(PASSWORD);

		connection.set;





	}

}
