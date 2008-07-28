package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import java.util.ArrayList;
import java.util.List;
/**
 * Cette classe génère les commandes CAMI prêtes à être envoyées à FK
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public final class CamiGenerator {

	/**
	 * constructeur
	 */
	private CamiGenerator() { }
	/**
	 * @return Commande DB non formattee
	 */
	public static String generateCmdDB() {
		String command = new String("DB()");
		return command;
	}

	/**
	 * @return Commande FB non formattee
	 */
	public static String generateCmdFB() {
		String command = new String("FB()");
		return command;
	}

	/**
	 * Traduction du modèle de haut niveau en commandes CAMI
	 * @param model modele sous forme objet à traduire en Cami
	 * @return ensemble de commandes cami decrivant un modele
	 */
	//public static List<byte[]> generateCamiModel(IGraph model) {
		//return CamiModelTranslator.translateModel(model);
	//}

	/**
	 * @param interlocutor : outil interlocuteur
	 * @param mode : batch/interactif (0/1)
	 * @return string
	 */
	public static String generateCmdCI(String interlocutor, int mode) {
		String command = new String("CI(" + interlocutor.length() + ":" + interlocutor + "," + mode + ")");
		return command;
	}

	/**
	 * commande DI
	 * @return string
	 */
	public static String generateCmdDI() {
		String command = new String("DI()");
		return command;
	}

	/**
	 * commande DT
	 * @return string
	 */
	public static String generateCmdDT() {
		String command = new String("DT()");
		return command;
	}

	/**
	 * commmande FI
	 * @return string
	 */
	public static String generateCmdFI() {
		String command = new String("FI()");
		return command;
	}

	/**
	 * commande FT
	 * @return string
	 */
	public static String generateCmdFT() {
		String command = new String("FT()");
		return command;
	}

	/**
	 *
	 * @param date Nouvelle date du modèle
	 * @return string
	 */
	public static String generateCmdMS(String date) {
		// TODO Auto-generated method stub
		String command = new String("MS(" + date + ")");
		return command;
	}

	/**
	 *
	 * @param uiName Nom du client
	 * @param uiVersion Version du client
	 * @param login Nom d'utilisateur
	 * @return commande SC formatée pour FK
	 */
	public static String generateCmdOC(String uiName, String uiVersion, String login) {
		StringBuilder command = new StringBuilder();

		command.append("OC(").append(uiName.length()).append(":").append(uiName);
		command.append(",").append(uiVersion.length()).append(":").append(uiVersion);
		command.append(",").append(login.length()).append(":").append(login);
		command.append(",").append(0).append(")");
		return command.toString();
	}

	/**
	 *
	 * @param sessionName Nom de la session
	 * @param date la date
	 * @param sessionFormalism Formalisme de la session
	 * @return commande OS sous forme d'un tableau de bytes
	 */
	public static String generateCmdOS(String sessionName, String date, String sessionFormalism) {
		StringBuffer command = new StringBuffer();
		command.append("OS(").append(sessionName.length()).append(":").append(sessionName);
		command.append(",").append(date).append(",");
		command.append(sessionFormalism.length()).append(":").append(sessionFormalism).append(")");
		return command.toString();
	}

	/**
	 * Dans le protocole cami, le 3ème paramètre de la commande PQ est inconnu
	 * Dans les traces, ce paramètre est toujours à 1
	 * @param rootName le nom de la racine ds le menu
	 * @param serviceName le nom du service
	 * @return string
	 */
	public static String generateCmdPQ(String rootName, String serviceName) {
		String command = new String("PQ(" + rootName.length() + ":" + rootName
				+ "," + serviceName.length() + ":" + serviceName + "," + 1
				+ ")");
		return command;
	}

	/**
	 * commande QQ
	 * @return string
	 */
	public static String generateCmdQQ() {
		String command = new String("QQ()");
		return command;
	}

	/**
	 * commande RS
	 * @param sessionName le nom de la session
	 * @return string
	 */
	public static String generateCmdRS(String sessionName) {
		String command = new String("RS(" + sessionName.length() + ":"
				+ sessionName + ")");
		return command;
	}

	/**
	 * @param login login
	 * @param password mot de passe
	 * @return Commande cami SC
	 */
	public static String generateCmdSC(String login, String password) {
		StringBuffer command = new StringBuffer();
		command.append("SC(").append(login.length()).append(":").append(login);
		command.append(",").append(password.length()).append(":").append(password);
		command.append(")");
		return command.toString();

	}

	/**
	 * Construction de la commande FC
	 * @return string
	 */
	public static String generateCmdFC() {
		String command = new String("FC()");
		return command;
	}

	/**
	 * Méthode s'occupant de la construction de la commande SS
	 * @param sessionName
	 * @return string
	 */
	public static String generateCmdSS() {
		String command = new String("SS()");
		return command;
	}

	/**
	 * Construction de la commande FS
	 * @param continueProcessing bool
	 * @return string
	 */
	public static String generateCmdFS(boolean continueProcessing) {
		int i;
		if (continueProcessing) {
			i = 0;
		} else {
			i = 1;
		}
		String command = new String("FS(" + i + ")");
		return command;
	}

	/**********commandes pour la reponse a une boite de dialog**********/
    /**
     * la commande DP
     * @return string
     */
	public static String generateCmdDP() {
		String command = new String("DP()");
		return command;
	}

	/**
	 * repondre a une boite de dialogue
	 * @param d la reponse a la boite de dialogue
	 * @return  tableau de string
	 */
	public static List<String> generateCmdDialogAnswer(IDialogAnswer d) {
		List <String> camiDialog = new ArrayList <String>();
		int modify = 1;
		if (d.isModified()) {
			modify = 2;
		}
		if (d.getAllValue().size() == 1) {
			if (d.getAllValue().get(0) == null) {
			String command = new String("RD(" + d.getIdDialog() + "," + d.getButtonType() + "," + modify + "," + ")");
			camiDialog.add(command);
            } else {
				String command = new String("RD(" + d.getIdDialog() + "," + d.getButtonType() + "," + modify + "," + d.getAllValue().get(0).length() + ":" + d.getAllValue().get(0) + ")");
			camiDialog.add(command);
			}

	   } else {
			String command = new String("RD(" + d.getIdDialog() + "," + d.getAllValue() + "," + modify + "," + ")");
           camiDialog.add(command);
           String command1 = new String("DE()");
           camiDialog.add(command1);
           for (int i = 0; i < d.getAllValue().size(); i++) {
   			String command2 = new String("DS(" + d.getIdDialog() + "," +  d.getAllValue().get(i).length() + ":" + d.getAllValue().get(i) + ")");
   			camiDialog.add(command2);
			}

           String command3 = new String("FE()");
           camiDialog.add(command3);
		}
		return camiDialog;
	}

	/**
	 * on genere le FP
	 * @return string
	 */
	public static String generateCmdFP() {
		String command = new String("FP()");
		return command;
	}


}
