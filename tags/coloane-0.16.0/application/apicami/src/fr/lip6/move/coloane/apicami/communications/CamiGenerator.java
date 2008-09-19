package fr.lip6.move.coloane.apicami.communications;

import fr.lip6.move.coloane.apicami.session.SessionController;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * Cette classe génère les commandes CAMI prêtes à être envoyées à FK
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public final class CamiGenerator {
	/** Le Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

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
	public static String generateCmdMS(int date) {
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
	 * Dans le protocole cami, le 3ème paramètre de la commande PQ est inconnu<br>
	 * Dans les traces, ce paramètre est toujours à 1
	 * @param rootName le nom de la racine dans le menu
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
	 * Construction de la réponse à une boite de dialogue
	 * @param dialog la reponse a la boite de dialogue
	 * @return L'ensemble des commandes CAMI
	 */
	public static List<String> generateCmdDialogAnswer(IDialogAnswer dialog) {
		List <String> camiDialog = new ArrayList <String>();

		// Récupération de la boite de dialogue originale
		IDialog original = SessionController.getInstance().getActiveSession().getDialog(dialog.getIdDialog());
		if (original == null) {
			LOGGER.warning("La boite de dialogue n'a pas ete enregsitree...");
			return null;
		}

		// En cas d'annulation
		if (dialog.getButtonType() == 2) {
			String command = new String("RD(" + dialog.getIdDialog() + ",2,2,)");
			camiDialog.add(command);
			return camiDialog;
		}

		int freshStatus = 2;
		if (dialog.hasBeenModified()) { freshStatus = 1; }

		// Si la réponse tient sur une ligne
		if ((original.getLineType() == IDialog.SINGLE_LINE) || ((original.getLineType() == IDialog.MULTI_LINE_WITH_SINGLE_SELECTION) && (original.getInputType() == IDialog.INPUT_FORBIDDEN))) {
			String command;
			if (dialog.getAllValue().size() > 0) {
				String value = dialog.getAllValue().get(0);
				if (value == null) { value = ""; }
				command = new String("RD(" + dialog.getIdDialog() + "," + dialog.getButtonType() + "," + freshStatus + "," + value.length() + ":" + value + ")");
			} else {
				// Dans le cas où on clique sur OK et rien n'est selectionne
				command = new String("RD(" + dialog.getIdDialog() + ",2,2,)");
			}
			camiDialog.add(command);

			// Sinon la réponse est multi-lignes
		} else {
			String command = new String("RD(" + dialog.getIdDialog() + "," + dialog.getButtonType() + "," + freshStatus + ",)");
			camiDialog.add(command);
			String head = new String("DE()");
			camiDialog.add(head);
			for (int i = 0; i < dialog.getAllValue().size(); i++) {
				String body = new String("DS(" + dialog.getIdDialog() + "," +  dialog.getAllValue().get(i).length() + ":" + dialog.getAllValue().get(i) + ")");
				camiDialog.add(body);
			}
			String foot = new String("FE()");
			camiDialog.add(foot);
		}
		return camiDialog;
	}

	/**
	 * On genere le FP
	 * @return string
	 */
	public static String generateCmdFP() {
		String command = new String("FP()");
		return command;
	}
}
