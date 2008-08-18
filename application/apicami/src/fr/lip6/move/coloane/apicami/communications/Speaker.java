package fr.lip6.move.coloane.apicami.communications;

import fr.lip6.move.coloane.apicami.interfaces.ISpeaker;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;

/**
 * Le rôle de cette classe est d'offrir une interface de haut niveau
 * aux modules de API pour envoyer des commandes à FrameKit
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */

public class Speaker implements ISpeaker {
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Objet de communication bas niveau */
	private FkOutputStream outputStream;

	/**
	 * Constructeur
	 * @param socket La coket de communication entre l'api et la plate-forme
	 * @throws IOException En cas de problème de communication entre la plate-forme et l'api
	 */
	public Speaker(Socket socket) throws IOException {
		 this.outputStream = new FkOutputStream(socket.getOutputStream());
	}

	/**
	 * {@inheritDoc}
	 */
	public final void startCommunication(String login, String password) throws IOException {
		LOGGER.finer("Demande de connexion - Premiere etape");
		// Fabrique de la commande SC
		String cmdToSend = CamiGenerator.generateCmdSC(login, password);
		// Envoi de la commande
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void openConnection(String uiName, String uiVersion, String login) throws IOException {
		LOGGER.finer("Demande de connexion - Deuxieme etape");
		// Fabrique la commande OC
		String cmdToSend = (String) CamiGenerator.generateCmdOC(uiName, uiVersion, login);
		// Envoie la commande
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void closeConnection() throws IOException {
		LOGGER.finer("Demande de deconnexion");
		// Fabrique la commande FC
		String cmdToSend = CamiGenerator.generateCmdFC();
		// Envoie la commande
		try {
			this.outputStream.write(cmdToSend);
		} catch (IOException ioe) {
			LOGGER.warning("Impossible d'ecrire sur une socket fermee");
			throw ioe;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void openSession(String sessionName, int date, String sessionFormalism, String interlocutor, int mode) throws IOException {
		LOGGER.finer("Demande d'ouverture de session");
		// Fabrique et envoie la commande OS
		String cmdToSend = CamiGenerator.generateCmdOS(sessionName, String.valueOf(date), sessionFormalism);
		this.outputStream.write(cmdToSend);

		// Fabrique et envoie la commande DI
		cmdToSend = CamiGenerator.generateCmdDI();
		this.outputStream.write(cmdToSend);

		// Fabrique et envoie la commande CI
		cmdToSend = CamiGenerator.generateCmdCI(interlocutor, mode);
		this.outputStream.write(cmdToSend);

		// Fabrique et envoie la commande FI
		cmdToSend = CamiGenerator.generateCmdFI();
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void closeSession(boolean continueProcessing) throws IOException {
		LOGGER.finer("Demande de fermeture de session");
		// Fabrique la commande FS
		String cmdToSend = CamiGenerator.generateCmdFS(continueProcessing);
		// Envoie la commande
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void resumeSession(String sessionName) throws IOException {
		LOGGER.finer("Demande de reprise de session");
		// Fabrique et envoie la commande RS
		String cmdToSend = CamiGenerator.generateCmdRS(sessionName);
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void suspendSession() throws IOException {
		LOGGER.finer("Demande de suspension de session");
		// Fabrique et envoie la commande SS
		String cmdToSend = CamiGenerator.generateCmdSS();
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void askForService(String rootName, String menuName, String serviceName) throws IOException {
		// Fabrique et envoie la commande DT
		String cmdToSend = CamiGenerator.generateCmdDT();
		this.outputStream.write(cmdToSend);

		// Fabrique et envoie la commande PQ
		cmdToSend = CamiGenerator.generateCmdPQ(rootName, menuName);
		this.outputStream.write(cmdToSend);

		// Fabrique et envoie la commande PQ (la deuxième)
		cmdToSend = CamiGenerator.generateCmdPQ(rootName, serviceName);
		this.outputStream.write(cmdToSend);

		// Fabrique et envoie la commande FT
		cmdToSend = CamiGenerator.generateCmdFT();
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void sendDate(int date) throws IOException {
		// Génération de la commande MS pour l'envoi de la date de mise à jour
		String cmdToSend = CamiGenerator.generateCmdMS(date);
		this.outputStream.write(cmdToSend);

	}

	/**
	 * {@inheritDoc}
	 */
	public final void sendModel(IGraph model) throws IOException {
		// Transformation du modèle en CAMI
		List<String> commandes = CamiModelTranslator.translateModel(model);
		// Envoyer un DB : Début de transmission du modele
		String cmdToSend = CamiGenerator.generateCmdDB();
		this.outputStream.write(cmdToSend);

		// Envoyer le coeur du modèle
		for (int i = 0; i < commandes.size(); i++) {
			this.outputStream.write(commandes.get(i));
		}

		// Envoyer un FB : Fin de transmission du modele
		cmdToSend = CamiGenerator.generateCmdFB();
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void invalidModel() throws IOException {
		// Fabrique en envoie la commande QQ
		String cmdToSend = CamiGenerator.generateCmdQQ();
		this.outputStream.write(cmdToSend);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void sendDialogResponse(IDialogAnswer dialogAnswer) throws IOException {
		// Fabrique en envoie la commande DP
		String cmdToSend = CamiGenerator.generateCmdDP();
		this.outputStream.write(cmdToSend);

		// Le coeur de la réponse à une boite de dialogue
		List<String> camiDialog;
		camiDialog = CamiGenerator.generateCmdDialogAnswer(dialogAnswer);
		for (int i = 0; i < camiDialog.size(); i++) {
			this.outputStream.write(camiDialog.get(i));
		}

		// Fin de la réponse à la boite de dialogue
		String cmdToSend2 = CamiGenerator.generateCmdFP();
		this.outputStream.write(cmdToSend2);
	}
}
