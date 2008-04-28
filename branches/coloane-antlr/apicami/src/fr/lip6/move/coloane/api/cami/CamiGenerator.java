package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IModel;


/**
 *
 * @author Kahoo & uu
 *
 * cette classe génère les commandes cami prêtes à être envoyées à FK
 *
 */


public class CamiGenerator {

	/**
	 * Prepare la commande CAMI conformement aux requirements de FK
	 * 3 premiers octects a 0
	 * 4eme octet indiquant la longueur du message
	 *
	 * @param command
	 * @return
	 */
	private static byte[] initCommand(String command) {
		byte[] toSend = new byte[command.length() + 4];
		byte[] message = command.getBytes();

		// Entete
		toSend[0] = 0;
		toSend[1] = 0;
		toSend[2] = 0;
		toSend[3] = (byte) (message.length);

		// Remplissage
		for (int i = 0; i < message.length; i++) {
			toSend[i + 4] = message[i];
		}

		return toSend;
	}



	public ArrayList<byte[]> generateCamiDialogue(IDialog d) {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<byte[]> generateCamiModel(IModel m) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 *
	 * @param interlocutor : outil interlocuteur
	 * @param mode : batch/interactif (0/1)
	 * @return
	 */
	public static byte[] generateCmdCI(String interlocutor, int mode) {
		// TODO Auto-generated method stub
		String command = new String("CI(" + interlocutor.length()+":" + interlocutor + "," + mode + ")");
		return initCommand(command);
	}


	public static byte[] generateCmdDI() {
		String command = new String("DI()");
		return (initCommand(command));
	}

	public ArrayList<byte[]> generateCmdDT() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *
	 * @return
	 */
	public static byte[] generateCmdFI() {
		// TODO Auto-generated method stub
		String command = new String("FI()");
		return (initCommand(command));
	}

	public ArrayList<byte[]> generateCmdFT() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<byte[]> generateCmdMS(String date) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 *
	 * @param UiName  : nom du client
	 * @param version : version du client
	 * @param login   : nom d'utilisateur
	 * @return commande SC sous forme d'un tableau de bytes
	 */
	public static byte[] generateCmdOC(String UiName, String version, String login) {

		String command = new String("OC(" + UiName.length() + ":" + UiName + "," + version.length() + ":" + version + "," + login.length()	+ ":" + login + "," + 0 + ")");
		return (initCommand(command));

	}


	/**
	 *
	 * @param sessionName : nom de la session
	 * @param date
	 * @param sessionFormalism : formalisme de la session
	 * @return commande OS sous forme d'un tableau de bytes
	 */
	public static byte[] generateCmdOS(String sessionName, String date, String sessionFormalism) {

		String command = new String("OS(" + sessionName.length() + ":" + sessionName + ","	+ date + "," + sessionFormalism.length() + ":" + sessionFormalism + ")");
		return initCommand(command);
	}


	public ArrayList<byte[]> generateCmdPQ(String rootName, String ServiceName) {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<byte[]> generateCmdQQ() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<byte[]> generateCmdRS(String SessionName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param login
	 * @param password
	 * @return Commande cami SC
	 */


	public static byte[] generateCmdSC(String login, String password) {

		String command = new String("SC(" + login.length() + ":" + login + "," + password.length() + ":" + password + ")");
		return initCommand(command);

	}


	public ArrayList<byte[]> generateCmdSS() {
		// TODO Auto-generated method stub
		return null;
	}

}
