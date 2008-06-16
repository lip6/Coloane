	package teststub;
 	
 	
	import java.io.IOException;
 	
 	import fr.lip6.move.coloane.api.apiPackage.Api;
 	import fr.lip6.move.coloane.api.interfaces.IApiConnection;
 	import fr.lip6.move.coloane.api.interfaces.IApiSession;
 	
 	
 	/**
 	 *
 	 * @author UU & KAHOO
 		 * Bouchon de test cotÃ© Coloane
 	 **/
 	
 	public class ComStub {
 	
 	        /**
 	         * Adresse Ip de la machine Ã  contacter
 	         */
 	        final static String IP_ADRESS = "127.0.0.1" ;
 	
 	        /**
 	         * 	     * Port d'Ã©coute de FrameKit
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
 	
 	        final static IApiSession[] tab = new IApiSession[1];
 	
 	        /**
 	         * Test l'ouverture d'une connexion
 	         * @throws IOException
 	         *
 	         */
 	        private static IApiConnection testOpenConnection() throws IOException{
 	                /** Demander un objet implÃ©mentant l'interface ApiConnection */
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
 	                connection.setServiceStateObserver(new ServiceStateObserver(), false); 	                connection.setSessionObserver(new SessionObserver(), false);
	                connection.setTraceMessageObserver(new TraceMessageObserver(), false);
 	                connection.setWarningObserver(new WarningObserver(), false);
 	                connection.setIAskForModelObserver(new AskForModelObserver(tab), false);
 	
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
	                IApiSession session = testOpenSession(connection, "Mysession7.petri");
 	                tab[0] = session;
 	
 	            //  Thread.sleep(10000);
 	              //System.out.println("2eme session");
 	              /** Test l'ouverture d'une seconde ession */
 	             //  IApiSession session2 = testOpenSession(connection, "seconde.petri");
 	               //tab[0] = session2;
 	
 	
 	         //      Thread.sleep(10000);
 	           //   System.out.println("\n\n\nreprise de la 1Ã©re session");
 	             // session.resumeSession();
 	             // tab[0] = session;
 	
 	     Thread.sleep(10000);
 	  //  session.askForService("AMI-Net", "Modelling facilities", "Execute PetriScript program");
 	//  session.askForService("AMI-Net", "Behavioral Properties", "Get the reachability graph - no arc routing");
 	   session.askForService("AMI-Net", "Petri net syntax checker", "Petri net syntax checker");
 //session.askForService("AMI-Net", "Structural properties", "P-positive invariants");
 	    
 	       //      Thread.sleep(10000);
 	    //   System.out.println("\n\n\nfermeture 1eme session");
 	    //   session.closeSession();
 	    //   tab[0] = null;
 	     //  Thread.sleep(10000);
 	     //  System.out.println("\n\n\nfermeture 2eme session");
 	     //  session2.closeSession();
 	
 	     //  Thread.sleep(10000);
 	     //  connection.closeConnection();
 	
 	     Thread.sleep(10000);
	     session.invalidModel();
	
 	   
 	        }
 	
	}