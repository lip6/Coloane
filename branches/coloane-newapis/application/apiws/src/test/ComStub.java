package test;

import fr.lip6.move.coloane.apiws.connection.Api;
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
		
		connection.setReceptDialogObserver(new ReceptDialogObserver(), false);
		connection.setReceptMenuObserver(new ReceptMenuObserver(), false);
		connection.setReceptMessageObserver(new ReceptMessageObserver(), false);
		
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
		IApiSession session1 = connection.getApiSession();
		System.out.println("SESSION   :");
		session1.openSession("27-06-2008", "AMI-Net", "maseesion1");
		System.out.println("");
		Thread.sleep(1*1000);
		
		System.out.println("2-b Ouverture session");
		IApiSession session2 = connection.getApiSession();
		System.out.println("SESSION   :");
		session2.openSession("27-06-2008", "AMI-Net", "maseesion1");
		System.out.println("");
		Thread.sleep(1*1000);
		
		System.out.println("2-c Ouverture session");
		IApiSession session3 = connection.getApiSession();
		System.out.println("SESSION   :");
		session3.openSession("27-06-2008", "AMI-Net", "maseesion1");
		System.out.println("");
		Thread.sleep(1*1000);
		
		System.out.println("resume session2");
		session2.resumeSession();
		
		System.out.println("2-d Ouverture session");
		IApiSession session4 = connection.getApiSession();
		System.out.println("SESSION   :");
		session4.openSession("27-06-2008", "AMI-Net", "maseesion1");
		System.out.println("");
		Thread.sleep(1*1000);
		
		System.out.println("resume session1");
		session1.resumeSession();
		
		
		
		
		/**
		 * Fermeture d'une session
		 */
		System.out.println("3-c Fermeture session");
		session3.closeSession();
		System.out.println("");
		Thread.sleep(1*1000);
		
		System.out.println("resume session2");
		session2.resumeSession();
		
		/**
		 * Fermeture de la connection
		 */
		System.out.println("4 Fermeture Connexion");
		connection.closeConnection();
		Thread.sleep(1*1000);
		
	}
}