package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.ICamiGenerator;
import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IModel;


/**
 *
 * @author Kahoo & uu
 *
 * cette classe génère les commandes cami prêtes à être envoyées à FK
 *
 */


public class CamiGenerator implements ICamiGenerator{

	/**
	 * Prepare la commande CAMI conformement aux requirements de FK
	 * 3 premiers octects a 0
	 * 4eme octet indiquant la longueur du message
	 *
	 * @param command
	 * @return
	 */
	private byte[] initCommand(String command) {
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


	@Override
	public ArrayList<byte[]> generateCamiDialogue(IDialog d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCamiModel(IModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdCI(String SessionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdDI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdDT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdFI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdFT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdMS(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdOC(String UiName, String version, String login) {
		// TODO Auto-generated method stub
		String command = new String("OC(" + UiName.length() + ":" + UiName + "," + version.length() + ":" + version + "," + login.length()	+ ":" + login + "," + 0 + ")");
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdOS(String SessionName, String date,
			String SessionFormalism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdPQ(String rootName, String ServiceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdQQ() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<byte[]> generateCmdRS(String SessionName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param login
	 * @param password
	 * @return Commande cami SC
	 */

	// TODO revoir si arrayList est vraiment nécessaire
	public ArrayList<byte[]> generateCmdSC(String login, String password) {

		ArrayList<byte[]> cmdSet = new ArrayList<byte[]>();
		String command = new String("SC(" + login.length() + ":" + login + "," + password.length() + ":" + password + ")");
		cmdSet.add(this.initCommand(command));
		return cmdSet;
	}

	@Override
	public ArrayList<byte[]> generateCmdSS() {
		// TODO Auto-generated method stub
		return null;
	}

}
