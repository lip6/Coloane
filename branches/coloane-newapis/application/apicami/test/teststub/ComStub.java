package teststub;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import fr.lip6.move.coloane.api.Api;
import fr.lip6.move.coloane.api.camiObject.Arc;
import fr.lip6.move.coloane.api.camiObject.Attribute;
import fr.lip6.move.coloane.api.camiObject.InflexPoint;
import fr.lip6.move.coloane.api.camiObject.Model;
import fr.lip6.move.coloane.api.camiObject.Node;
import fr.lip6.move.coloane.api.interfaces.IApiConnection;
import fr.lip6.move.coloane.api.interfaces.IApiSession;
import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.IAttribute;
import fr.lip6.move.coloane.api.interfaces.IInflexPoint;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.INode;
import fr.lip6.move.coloane.api.interfaces.ISessionInfo;

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

	//  final static IApiSession[] tab = new IApiSession[1];

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
		connection.setReceptDialogObserver(new ReceptDialogObserver(), false);
		connection.setReceptResultObserver(new ReceptResultObserver(), false);
		//  connection.setServiceStateObserver(new ServiceStateObserver(), false); 	           
		connection.setSessionObserver(new SessionObserver(), false);
		//connection.setTraceMessageObserver(new TraceMessageObserver(), false);
		//connection.setWarningObserver(new WarningObserver(), false);
		connection.setCloseConnectionObserver(new CloseConnectionObserver(), false);
		connection.setCloseSessionObserver(new CloseSessionObserver(), false);
		connection.setSpecialMessageObserver(new SpecialMessageObserver(), false);
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
		ISessionInfo seInfo ;
		seInfo =   session.openSession("241483", "AMI-Net", nomSession);
		System.out.println("session Info \n "+ seInfo.getAboutService());
		System.out.println(seInfo.getIncremental());
		System.out.println(seInfo.getNameService());
		System.out.println(seInfo.getResultatCalcule());
		return session;
	}

	public static void main(String args[]) throws IOException, InterruptedException{


		Api.initialize();

		/** Test l'ouverture de la connexion */
		IApiConnection connection = testOpenConnection();
		/** Test l'ouverture d'une session */
		IApiSession session = testOpenSession(connection, "Mysession1.petri");
		//    tab[0] = session;

		Thread.sleep(10000);
		System.out.println("2eme session");
		/** Test l'ouverture d'une seconde ession */
		IApiSession session2 = testOpenSession(connection, "seconde.petri");
		//tab[0] = session2;


		Thread.sleep(10000);
		System.out.println("\n\n\nreprise de la 1Ã©re session");
		session.resumeSession();
		// tab[0] = session;




		//creation des noeuds
		ArrayList<INode> nodes = new ArrayList<INode>();

		Vector<IAttribute> listOfAttr1 = new Vector<IAttribute>();
		IAttribute attribute1 =(IAttribute) new Attribute("version",1,"0,0",0,0);
		listOfAttr1.add(attribute1);
		INode node1 = new Node("net",1,0,0,listOfAttr1);

		Vector<IAttribute> listOfAttr2= new Vector<IAttribute>();
		IAttribute attribute2 =(IAttribute) new Attribute("name",2,"p2",491,207);
		listOfAttr2.add(attribute2);
		INode node2 = new Node("place",2,471,207,listOfAttr2);

		Vector<IAttribute> listOfAttr3= new Vector<IAttribute>();
		IAttribute attribute3 =(IAttribute) new Attribute("name",3,"p4",217,217);
		listOfAttr3.add(attribute3);
		INode node3 = new Node("place",3,237,215,listOfAttr3);

		Vector<IAttribute> listOfAttr4= new Vector<IAttribute>();
		IAttribute attribute4 =(IAttribute) new Attribute("name",4,"p1",363,12);
		listOfAttr4.add(attribute4);
		INode node4 = new Node("place",4,362,28,listOfAttr4);

		Vector<IAttribute> listOfAttr5= new Vector<IAttribute>();
		IAttribute attribute5 =(IAttribute) new Attribute("name",5,"p3",493,292);
		listOfAttr5.add(attribute5);
		INode node5 = new Node("place",5,471,291,listOfAttr5);

		Vector<IAttribute> listOfAttr6= new Vector<IAttribute>();
		IAttribute attribute6 =(IAttribute) new Attribute("guard",6,"true",0,0);
		IAttribute attribute7 =(IAttribute) new Attribute("priority",6,"0",0,0);
		listOfAttr6.add(attribute6);
		listOfAttr6.add(attribute7);
		INode node6 = new Node("transition",6,241,348,listOfAttr6);

		Vector<IAttribute> listOfAttr7= new Vector<IAttribute>();
		IAttribute attribute8 =(IAttribute) new Attribute("guard",7,"true",0,0);
		IAttribute attribute9 =(IAttribute) new Attribute("priority",7,"0",0,0);
		listOfAttr7.add(attribute8);
		listOfAttr7.add(attribute9);
		INode node7 = new Node("transation",7,367,259,listOfAttr7);

		Vector<IAttribute> listOfAttr8= new Vector<IAttribute>();
		IAttribute attribute10 =(IAttribute) new Attribute("guard",8,"true",0,0);
		IAttribute attribute11 =(IAttribute) new Attribute("priority",8,"0",0,0);
		listOfAttr8.add(attribute10);
		listOfAttr8.add(attribute11);
		INode node8 = new Node("transation",8,356,118,listOfAttr8);

		Vector<IAttribute> listOfAttr9 = new Vector<IAttribute>() ;
		IAttribute attribute12 =(IAttribute) new Attribute("guard",9,"true",0,0);
		IAttribute attribute13 =(IAttribute) new Attribute("priority",9,"0",0,0);
		listOfAttr9.add(attribute12);
		listOfAttr9.add(attribute13);
		INode node9 = new Node("transation",9,116,344,listOfAttr9);

		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);
		nodes.add(node7);
		nodes.add(node8);
		nodes.add(node9);


		//creation des arcs
		ArrayList<IArc> arcs = new ArrayList<IArc>();
		Vector<IAttribute> listOfAtt1= new Vector<IAttribute>();
		IAttribute attribut1 =(IAttribute) new Attribute("valuation",10,"1",0,0);
		listOfAtt1.add(attribut1);
		IArc arc1 = new Arc("arc",10,9,3,listOfAtt1,new Vector<IInflexPoint>());

		Vector<IAttribute> listOfAtt2= new Vector<IAttribute>();
		IAttribute attribut2 =(IAttribute) new Attribute("valuation",11,"1",0,0);
		listOfAtt2.add(attribut2);
		IArc arc2 = new Arc("arc",11,3,8,listOfAtt2,new Vector<IInflexPoint>());

		Vector<IAttribute> listOfAtt3= new Vector<IAttribute>();
		IAttribute attribut3 =(IAttribute) new Attribute("valuation",12,"1",0,0);
		listOfAtt3.add(attribut3);
		IArc arc3 = new Arc("arc",12,8,4,listOfAtt3,new Vector<IInflexPoint>());

		Vector<IAttribute> listOfAtt4= new Vector<IAttribute>();
		IAttribute attribut4 =(IAttribute) new Attribute("valuation",13,"1",0,0);
		listOfAtt4.add(attribut4);
		IArc arc4 = new Arc("arc",13,8,2,listOfAtt4,new Vector<IInflexPoint>());

		Vector<IAttribute> listOfAtt5= new Vector<IAttribute>();
		IAttribute attribut5 =(IAttribute) new Attribute("valuation",14,"1",0,0);
		listOfAtt5.add(attribut5);
		IArc arc5 = new Arc("arc",14,3,7,listOfAtt5,new Vector<IInflexPoint>());

		Vector<IAttribute> listOfAtt6= new Vector<IAttribute>();
		IAttribute attribut6 =(IAttribute) new Attribute("valuation",15,"1",0,0);
		listOfAtt6.add(attribut6);
		IArc arc6 = new Arc("arc",15,7,5,listOfAtt6,new Vector<IInflexPoint>());

		Vector<IAttribute> listOfAtt7= new Vector<IAttribute>();
		IAttribute attribut7 =(IAttribute) new Attribute("valuation",16,"1",0,0);
		listOfAtt7.add(attribut7);
		IInflexPoint pi1 = new InflexPoint(270,262);
		Vector<IInflexPoint> listOfPI7= new Vector<IInflexPoint>();
		listOfPI7.add(pi1);
		IArc arc7 = new Arc("arc",16,2,6,listOfAtt7,listOfPI7);

		Vector<IAttribute> listOfAtt8= new Vector<IAttribute>();
		IAttribute attribut8 =(IAttribute) new Attribute("valuation",17,"1",0,0);
		listOfAtt8.add(attribut8);
		IInflexPoint pi2 = new InflexPoint(478,353);
		Vector<IInflexPoint> listOfPI8= new Vector<IInflexPoint>();
		listOfPI8.add(pi2);
		IArc arc8 = new Arc("arc",17,5,6,listOfAtt8,listOfPI8);

		arcs.add(arc1);
		arcs.add(arc2);
		arcs.add(arc3);
		arcs.add(arc4);
		arcs.add(arc5);
		arcs.add(arc6);
		arcs.add(arc7);
		arcs.add(arc8);
		IModel model = new Model(arcs,null,nodes);




		//   Thread.sleep(10000);
		// session.askForService("AMI-Net", "Modelling facilities", "Execute PetriScript program",model);
		//	 session.askForService("AMI-Net", "Behavioral Properties", "Get the reachability graph - no arc routing",model);
		//   session.askForService("AMI-Net", "Petri net syntax checker", "Petri net syntax checker",model);
		// session.askForService("AMI-Net", "Structural properties", "P-positive invariants",model);

		//      Thread.sleep(10000);
		//   System.out.println("\n\n\nfermeture 1eme session");
		//   session.closeSession();
		//   tab[0] = null;
		Thread.sleep(10000);
		System.out.println("\n\n\nfermeture 2eme session");
		session2.closeSession();

		//  Thread.sleep(10000);
		//  connection.closeConnection();

		//  Thread.sleep(10000);
		// session.invalidModel();

		//  Thread.sleep(10000);  
		//System.out.println("\n\n\n deconnection");
		//connection.closeConnection();

		//  Thread.sleep(20000);
		//ArrayList<String> answer = new ArrayList<String>();
		//answer.add("create place \"B\";");
		//answer.add("delete place \"p1\";");
		// DialogAnswer dialog = new DialogAnswer(answer,1,1,false,true);
		// session.sendDilaogAnswer(dialog);
	}

}