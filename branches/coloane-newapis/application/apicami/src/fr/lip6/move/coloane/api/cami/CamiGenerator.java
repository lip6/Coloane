package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

/**
 * Cette classe génère les commandes CAMI prêtes à être envoyées à FK
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public class CamiGenerator {

	/**
	 * Prepare la commande CAMI conformement aux besoins de FrameKit :
	 * <ul>
	 * 	<li>3 premiers octects a 0 </li>
	 * 	<li>4eme octet indiquant la longueur du message</li>
	 * </ul>
	 * @param command La commande à transmettre
	 * @return la commande formatée pour FK
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
	 * @return Commande DB formattee
	 */
	public static byte[] generateCmdDB() {
		String command = new String("DB()");
		return (initCommand(command));
	}

	/**
	 * @return Commande FB formattee
	 */
	public static byte[] generateCmdFB() {
		String command = new String("FB()");
		return (initCommand(command));
	}

	/**
	 * Traduction du modèle de haut niveau en commandes CAMI
	 * @param model modele sous forme objet à traduire en Cami
	 * @return ensemble de commandes cami decrivant un modele
	 */
	public static List<byte[]> generateCamiModel(IGraph model) {
		return null;
	}

	/**
	 * @param interlocutor : outil interlocuteur
	 * @param mode : batch/interactif (0/1)
	 * @return
	 */
	public static byte[] generateCmdCI(String interlocutor, int mode) {
		String command = new String("CI(" + interlocutor.length() + ":" + interlocutor + "," + mode + ")");
		return initCommand(command);
	}

	/**
	 *
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
	public static byte[] generateCmdFT() {
		String command = new String("FT()");
		return (initCommand(command));
	}

	/**
	 *
	 * @param date Nouvelle date du modèle
	 * @return
	 */
	public static byte[] generateCmdMS(String date) {
		// TODO Auto-generated method stub
		String command = new String("MS(" + date + ")");
		return (initCommand(command));
	}

	/**
	 *
	 * @param uiName Nom du client
	 * @param uiVersion Version du client
	 * @param login Nom d'utilisateur
	 * @return commande SC formatée pour FK
	 */
	public static byte[] generateCmdOC(String uiName, String uiVersion, String login) {
		StringBuilder command = new StringBuilder();

		command.append("OC(").append(uiName.length()).append(":").append(uiName);
		command.append(",").append(uiVersion.length()).append(":").append(uiVersion);
		command.append(",").append(login.length()).append(":").append(login);
		command.append(",").append(0).append(")");
		return (initCommand(command.toString()));
	}

	/**
	 *
	 * @param sessionName Nom de la session
	 * @param date
	 * @param sessionFormalism Formalisme de la session
	 * @return commande OS sous forme d'un tableau de bytes
	 */
	public static byte[] generateCmdOS(String sessionName, String date, String sessionFormalism) {
		StringBuffer command = new StringBuffer();
		command.append("OS(").append(sessionName.length()).append(":").append(sessionName);
		command.append(",").append(date).append(",");
		command.append(sessionFormalism.length()).append(":").append(sessionFormalism).append(")");
		return initCommand(command.toString());
	}

	/**
	 * Dans le protocole cami, le 3ème paramètre de la commande PQ est inconnu
	 * Dans les traces, ce paramètre est toujours à 1
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

	/**
	 * 
	 * @return
	 */
	public static byte[] generateCmdQQ() {
		String command = new String("QQ()");
		return initCommand(command);
	}

	/**
	 * 
	 * @param sessionName
	 * @return
	 */
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
		StringBuffer command = new StringBuffer();
		command.append("SC(").append(login.length()).append(":").append(login);
		command.append(",").append(password.length()).append(":").append(password);
		command.append(")");
		return initCommand(command.toString());

	}

	/**
	 * Construction de la commande FC
	 * @return
	 */
	public static byte[] generateCmdFC() {
		String command = new String("FC()");
		return initCommand(command);
	}

	/**
	 * Méthode s'occupant de la construction de la commande SS
	 * @param sessionName
	 * @return
	 */
	public static byte[] generateCmdSS() {
		String command = new String("SS()");
		return initCommand(command);
	}

	/**
	 * Construction de la commande FS
	 * @param continueProcessing
	 * @return
	 */
	public static byte[] generateCmdFS(boolean continueProcessing) {
		int i;
		if (continueProcessing == true) {
			i = 0;
		} else {
			i = 1;
		}
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
