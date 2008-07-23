package test;

import fr.lip6.move.coloane.apiws.Api;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

public class ComStub {
	final static String LOGIN ="login";
	
	final static String MDP ="mdp";
	
	public static void main(String args[]) throws InterruptedException, ApiException{
		Api api = new Api();
		IApiConnection connection = api.getApiConnection();
		
		connection.setLogin(LOGIN);
		connection.setPassword(MDP);
		
		ReceptDialogObserver receptDialogObserver = new ReceptDialogObserver();
		ReceptMenuObserver receptMenuObserver = new ReceptMenuObserver();
		ReceptMessageObserver receptMessageObserver = new ReceptMessageObserver();
		connection.setReceptDialogObserver(receptDialogObserver, false);
		connection.setReceptMenuObserver(receptMenuObserver, false);
		connection.setReceptMessageObserver(receptMessageObserver, false);
		
		/**
		 * Ouveture d'une connection
		 */
		System.out.println("1 Ouveture connexion");
		IConnectionInfo connectionInfo = connection.openConnection();
		System.out.println("CONNECTION: Version="+connectionInfo.getFkName()+" major="+connectionInfo.getFkMajor()+" minor="+connectionInfo.getFkMinor());
		System.out.println("");
		Thread.sleep(1*1000);
		
		/**
		 * Ouverture d'une session
		 */
		System.out.println("2-a Ouverture session");
		IApiSession session1 = connection.createApiSession();
		receptDialogObserver.setSession(session1);
		System.out.println("SESSION   :");
		session1.openSession(27062008, "AM-Net", "maseesion1");
		
		//session1.sendDialogAnswer(receptDialogObserver.getIdDialog(), IDialog.DLG_NO_BUTTON, false, "", null, null);
		System.out.println("");
		Thread.sleep(1*1000);
		
		
		
		
		
		
		/**
		 * Fermeture d'une session
		 */
		System.out.println("3-a Fermeture session");
		session1.closeSession();
		System.out.println("");
		Thread.sleep(1*1000);
		
		/**
		 * Fermeture de la connection
		 */
		System.out.println("4 Fermeture Connexion");
		connection.closeConnection();
		Thread.sleep(1*1000);
		
	}
}