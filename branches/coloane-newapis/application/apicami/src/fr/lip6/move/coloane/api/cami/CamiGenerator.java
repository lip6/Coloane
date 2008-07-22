package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IAttribute;

import fr.lip6.move.coloane.api.interfaces.IInflexPoint;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.INode;
import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

/**
 *
 * @author Kahoo & uu
 *
 * cette classe génère les commandes cami prêtes à être envoyées à FK
 *
 */

public class CamiGenerator {

	/**
	 * Prepare la commande CAMI conformement aux requirements de FK 3 premiers
	 * octects a 0 4eme octet indiquant la longueur du message
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

	

	/**
	 *
	 * @return Commande DB formattee
	 */
	public static byte[] generateCmdDB() {
		String command = new String("DB()");
		return (initCommand(command));
	}

	/**
	 *
	 * @return Commande FB formattee
	 */
	public static byte[] generateCmdFB() {
		String command = new String("FB()");
		return (initCommand(command));
	}

	/**
	 *
	 * @param m modele sous forme objet à traduire en Cami
	 * @return ensemble de commandes cami decrivant un modele
	 */
	//TODO Noeuds : Attributs multiLines
	//TODO Arc    : positions intermediaires
	public static ArrayList<byte[]> generateCamiModel(IModel m) {
		ArrayList <byte[]> camiModel = new ArrayList <byte[]>();

		/** generer les noeuds cami */
		for(int i=0; i<m.getNodes().size(); i++){
			INode node = m.getNodes().get(i);
			/** creer le noeud */
			String command = new String("CN(" + node.getNodeType().length()
					+  ":" + node.getNodeType() + ","+ node.getId() + ")");
			camiModel.add(initCommand(command));

			/** ses positions X et Y  PO */
			command = new String("PO(" + node.getId()
					+ ","+ node.getXPosition() + "," + node.getYPosition() + ")");
			camiModel.add(initCommand(command));

			/** ses attributs monolignes */
			for(int j=0; j<node.getListOfAttr().size(); j++){
				IAttribute att = node.getListOfAttr().get(j);
				command = new String("CT(" + att.getName().length()
						+ ":" + att.getName()
						+ ","+ node.getId() + "," + att.getValue().length()
						+ ":" + att.getValue()
						 + ")");
				camiModel.add(initCommand(command));
			}
		}

		/** generer les arcs cami   */

		for(int i=0; i<m.getArcs().size(); i++){
			IArc arc = m.getArcs().get(i);
			/** creer le noeud */
			String command = new String("CA(" + arc.getArcType().length()
					+ ":"+arc.getArcType()
					+ ","+ arc.getId()
					+ ","+ arc.getStartingNode()
					+ ","+ arc.getEndingNode()
					+ ")");
			
			camiModel.add(initCommand(command));

			/** ses attributs monolignes */
			for(int j=0; j<arc.getListOfAttr().size(); j++){
				IAttribute att = arc.getListOfAttr().get(j);
				command = new String("CT(" + att.getName().length()
						+ ":" + att.getName()
						+ ","+ arc.getId() + ","+ att.getValue().length()
						+ ":" + att.getValue()
						 + ")");
				camiModel.add(initCommand(command));
			}

			
			/** ses point intermediaires*/
		
			for(int j=0; j<arc.getListOfPI().size(); j++){
				IInflexPoint att = (IInflexPoint) arc.getListOfPI().get(j);
				command = new String("PI(-1," + arc.getId()
						+ "," + att.getXPosition()
						+ ","+ att.getYPosition()
						 + ",-1)");
				camiModel.add(initCommand(command));
			}
			

		}

		return camiModel;
	}

	/**
	 *
	 * @param interlocutor : outil interlocuteur
	 * @param mode : batch/interactif (0/1)
	 * @return
	 */
	public static byte[] generateCmdCI(String interlocutor, int mode) {
		String command = new String("CI(" + interlocutor.length() + ":"
				+ interlocutor + "," + mode + ")");
		return initCommand(command);
	}

	public static byte[] generateCmdDI() {
		String command = new String("DI()");
		return (initCommand(command));
	}

	/**
	 *
	 * @return
	 */
	public static byte[] generateCmdDT() {
		String command = new String("DT()");
		return initCommand(command);
	}

	/**
	 *
	 * @return
	 */
	public static byte[] generateCmdFI() {
		String command = new String("FI()");
		return (initCommand(command));
	}

	public static byte[] generateCmdFT() {
		String command = new String("FT()");
		return (initCommand(command));
	}

	/**
	 *
	 * @param date
	 *            nouvelle date du modèle
	 * @return
	 */
	public static byte[] generateCmdMS(String date) {
		// TODO Auto-generated method stub
		String command = new String("MS(" + date + ")");
		return (initCommand(command));
	}

	/**
	 *
	 * @param UiName :
	 *            nom du client
	 * @param version :
	 *            version du client
	 * @param login :
	 *            nom d'utilisateur
	 * @return commande SC sous forme d'un tableau de bytes
	 */
	public static byte[] generateCmdOC(String UiName, String version,
			String login) {

		String command = new String("OC(" + UiName.length() + ":" + UiName
				+ "," + version.length() + ":" + version + "," + login.length()
				+ ":" + login + "," + 0 + ")");
		return (initCommand(command));

	}

	/**
	 *
	 * @param sessionName :
	 *            nom de la session
	 * @param date
	 * @param sessionFormalism :
	 *            formalisme de la session
	 * @return commande OS sous forme d'un tableau de bytes
	 */
	public static byte[] generateCmdOS(String sessionName, String date,
			String sessionFormalism) {

		String command = new String("OS(" + sessionName.length() + ":"
				+ sessionName + "," + date + "," + sessionFormalism.length()
				+ ":" + sessionFormalism + ")");
		return initCommand(command);
	}

	/**
	 * Dans le protocole cami, le 3ème paramètre de la commande PQ est inconnu
	 * Dans les traces, ce paramètre est toujours à 1
	 *
	 * @param rootName
	 * @param serviceName
	 * @return
	 */
	public static byte[] generateCmdPQ(String rootName, String serviceName) {

		String command = new String("PQ(" + rootName.length() + ":" + rootName
				+ "," + serviceName.length() + ":" + serviceName + "," + 1
				+ ")");
		return initCommand(command);

	}

	public static byte[] generateCmdQQ() {
		String command = new String("QQ()");
		return initCommand(command);
	}

	public static byte[] generateCmdRS(String sessionName) {
		String command = new String("RS(" + sessionName.length() + ":"
				+ sessionName + ")");
		return initCommand(command);
	}

	/**
	 * @param login
	 * @param password
	 * @return Commande cami SC
	 */
	public static byte[] generateCmdSC(String login, String password) {

		String command = new String("SC(" + login.length() + ":" + login + ","
				+ password.length() + ":" + password + ")");
		return initCommand(command);

	}

	/**
	 * Construction de la commande FC
	 *
	 * @return
	 */
	public static byte[] generateCmdFC() {

		String command = new String("FC()");
		return initCommand(command);

	}

	/**
	 * Méthode s'occupant de la construction de la commande SS
	 *
	 * @param sessionName
	 * @return
	 */
	public static byte[] generateCmdSS() {
		String command = new String("SS()");
		return initCommand(command);
	}

	/**
	 * Construction de la commande FS
	 *
	 * @param continueProcessing
	 * @return
	 */
	public static byte[] generateCmdFS(boolean continueProcessing) {
		int i;
		if (continueProcessing == true)
			i = 0;
		else
			i = 1;
		String command = new String("FS(" + i + ")");
		return initCommand(command);
	}

	/**********commandes pour la reponse a une boite de dialog**********/

	public static byte[] generateCmdDP() {
		String command = new String("DP()");
		return initCommand(command);
	}

	public static ArrayList<byte[]> generateCmdDialogAnswer(IDialogAnswer d) {
		ArrayList <byte[]> camiDialog = new ArrayList <byte[]>();
		int modify = 1;
		if(d.hasBeenModified())
		{
			modify = 2;
		}
		
		if(!d.isMultiLineAnswer()) {
			if(d.getAnswer()==null){
			
			String command = new String("RD(" + d.getDialogId()+ "," + d.getAnswerType()+"," +
					                    modify +","+ ")");
			camiDialog.add(initCommand(command));

			}
			else {
				String command = new String("RD(" + d.getDialogId()+ "," + d.getAnswerType()+"," +
	                    modify +","+ d.getAnswer().get(0).length()+":" +d.getAnswer().get(0) +")");
			camiDialog.add(initCommand(command));
			}
			
		}
		
		else {
			String command = new String("RD(" + d.getDialogId()+ "," + d.getAnswerType()+"," +
                    modify +","+ ")");
           camiDialog.add(initCommand(command));
           String command1 = new String("DE()");
           camiDialog.add(initCommand(command1));
           
           for(int i=0; i<d.getAnswer().size(); i++){
   			String command2 = new String("DS(" + d.getDialogId() +"," +  d.getAnswer().get(i).length() +":" + d.getAnswer().get(i) + ")");
   			camiDialog.add(initCommand(command2));
			}
           
           String command3 = new String("FE()");
           camiDialog.add(initCommand(command3));
		}
		return camiDialog;
	}
	
	public static byte[] generateCmdFP() {
		String command = new String("FP()");
		return initCommand(command);
	}


}
