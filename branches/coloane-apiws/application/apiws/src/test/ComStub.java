package test;

import fr.lip6.move.coloane.apiws.api.Api;
import fr.lip6.move.coloane.apiws.exceptions.ApiConnectionException;
import fr.lip6.move.coloane.apiws.exceptions.ApiSessionException;
import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.api.IApiConnection;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;

public class ComStub {
	
	final static String LOGIN ="login";
	
	final static String MDP ="mdp";
	
	public static void main(String args[]) throws WrapperException, ApiConnectionException, ApiSessionException, InterruptedException{
		
		/**
		 * Initialisation de la connection
		 */
		IApiConnection connection = Api.getApiConnection();
		
		connection.setLogin(LOGIN);
		connection.setPassword(MDP);
		
		connection.setOpenConnectionObserver(new OpenConnectionObserver(), false);
		connection.setOpenSessionObsever(new OpenSessionObserver(), false);
		connection.setAskDialogObserver(new AskDialogObserver(), false);
		connection.setSendDialogObserver(new SendDialogObserver(), false);
		connection.setTraceMessageObserver(new TraceMessageObserver(), false);
		connection.setCloseSessionObserver(new CloseSessionObserver(), false);
		connection.setCloseConnectionObserver(new CloseConnectionObserver(), false);
		connection.setSuspendSessionObserver(new SuspendSessionObserver(), false);
		connection.setResumeSessionObserver(new ResumeSessionObserver(), false);
		
		/**
		 * Ouveture d'une connection
		 */
		System.out.println("1 Ouveture connexion");
		Thread.sleep(1*1000);
		connection.openConnection();
		System.out.println("");
		
		/**
		 * Ouverture d'une session
		 */
		IApiSession session1 = connection.getApiSession();
		System.out.println("2-a Ouverture session");
		Thread.sleep(1*1000);
		session1.openSession("27-06-2008", "AMI-Net", "maseesion1");
		System.out.println("");

		
		
		/////////
		IApiSession session2 = connection.getApiSession();
		System.out.println("2-b Ouverture session");
		Thread.sleep(1*1000);
		session2.openSession("27-06-2008", "AM-Net", "maseesion2");
		System.out.println("");
		/////////
		
		
		/**
		 * Changement de session
		 */
		System.out.println("session2 -> session2");
		session2.changeSession(session1);
		//System.out.println("session1 -> session2");
		//session1.changeSession(session2);
		
		/**
		 * Fermeture d'une session
		 */
		
		System.out.println("3-b Fermeture session");
		Thread.sleep(1*1000);
		session2.closeSession();
		System.out.println("");


		/////////
		System.out.println("3-a Fermeture session");
		Thread.sleep(1*1000);
		session1.closeSession();
		System.out.println("");
		/////////
		
		/**
		 * Fermeture de la connection
		 */
		System.out.println("4 Fermeture Connexion");
		Thread.sleep(1*1000);
		connection.closeConnection();
		
	}
	
	
}
