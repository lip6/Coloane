package fr.lip6.move.coloane.api.FkCommunication;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
 *
 * @author uu & kahoo
 * Classe de tests unitaires pour la classe FkComLowLevel
 */


public class FkComLowLevelTest extends TestCase {

	/** Adresse Ip de la machine à contacter */
	private final static String IP_ADRESS = "127.0.0.1" ;

	/** Port d'écoute de FrameKit */
	private final static int PORT = 7001;

	/** Login */
	private final static String LOGIN = "Administrator";

	/** Password */
	private final static String PASSWORD = "123456";


	FkComLowLevel fk;
	byte[] commandToWrite;
	ArrayList<String> commandToRead;

	/**
	 * 	setUp
	 */

	protected void setUp() throws Exception {
		fk = new FkComLowLevel(this.IP_ADRESS, this.PORT);


		// TODO code à remplacer par des méthodes  de CamiGenerator
		String tmp = "SC(" + this.LOGIN.length() + ":" +
			this.LOGIN + "," + this.PASSWORD.length() + ":" + this.PASSWORD + ")";

		byte[] message = tmp.getBytes();
		commandToWrite = new byte[tmp.length() + 4];
		// Entete
		commandToWrite[0] = 0;
		commandToWrite[1] = 0;
		commandToWrite[2] = 0;
		commandToWrite[3] = (byte) (message.length);

		// Remplissage
		for (int i = 0; i < message.length; i++) {
			commandToWrite[i + 4] = message[i];
		}


	}

	/**
	 * tearDown
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}


	/* test du constructeur
	public void testFkComLowLevel() throws IOException {
		fk = new FkComLowLevel(this.IP_ADRESS, this.PORT);
	}
	*/
	/**
	 * reads a command
	 * @throws IOException
	 */
	public void testReadCommand() throws IOException {
		fk.writeCommand(this.commandToWrite);
		this.commandToRead = fk.readCommand();

		this.assertNotNull(this.commandToRead);

		System.out.println(commandToRead.get(0));
	}

	/**
	 * writes a command
	 * @throws IOException
	 */
	public void testWriteCommand() throws IOException {
		boolean testWrite = fk.writeCommand(this.commandToWrite);
		this.assertTrue("La méthode write  écrit", testWrite);
	}

	public void testCloseCommunication() throws IOException {
		this.fk.closeCommunication();
	}

}
