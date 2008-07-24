package fr.lip6.move.coloane.api.FkCommunication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import fr.lip6.move.coloane.api.cami.CamiGenerator;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

/**
 *
 * @author UU & Kahoo
 * Le rôle de cette classe est d'offrir une interface de haut niveau
 * aux modules de API pour envoyer des commandes à FrameKit.
 */

public class Speaker implements ISpeaker{
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Objet de communication bas niveau */
	private FkComLowLevel fkLowLevel;

	/**
	 * Constructeur
	 * @param lowLevel Un objet de type FkComLowLevel pour qui Speaker délèguera les écritures sur la socket.
	 */
	public Speaker(FkComLowLevel lowLevel) {
		this.fkLowLevel = lowLevel;
	}

	/**
	 * {@inheritDoc}
	 */
	public void startCommunication(String login, String password) throws IOException {
		// Fabrique de la commande SC
		byte[] cmdToSend = CamiGenerator.generateCmdSC(login, password);
		// Envoi de la commande
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}

	/**
	 * {@inheritDoc}
	 */
	public void openConnection(String uiName, String uiVersion, String login) throws IOException {
		// Fabrique la commande OC
		byte[] cmdToSend = CamiGenerator.generateCmdOC(uiName,uiVersion, login);
		// Envoie la commande
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}

	/**
	 * {@inheritDoc}
	 */
	public void closeConnection() throws IOException {
		// Fabrique la commande FC
		byte[] cmdToSend = CamiGenerator.generateCmdFC();
		// Envoie la commande
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void openSession(String sessionName, int date, String sessionFormalism, String interlocutor, int mode) throws IOException {
		// Fabrique et envoie la commande OS
		byte[] cmdToSend = CamiGenerator.generateCmdOS(sessionName, String.valueOf(date), sessionFormalism);
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Fabrique et envoie la commande DI
		cmdToSend = CamiGenerator.generateCmdDI();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Fabrique et envoie la commande CI
		cmdToSend = CamiGenerator.generateCmdCI(interlocutor, mode);
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Fabrique et envoie la commande FI
		cmdToSend = CamiGenerator.generateCmdFI();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void closeSession(boolean continueProcessing) throws IOException {
		// Fabrique la commande FS
		byte[] cmdToSend = CamiGenerator.generateCmdFS(continueProcessing);
		// Envoie la commande
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void resumeSession(String sessionName) throws IOException {
		// Fabrique et envoie la commande RS
		byte[] cmdToSend = CamiGenerator.generateCmdRS(sessionName);
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}

	/**
	 * {@inheritDoc}
	 */
	public void suspendSession() throws IOException {
		// Fabrique et envoie la commande SS
		byte[] cmdToSend = CamiGenerator.generateCmdSS();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}

	/**
	 * {@inheritDoc}
	 */
	public void askForService(String rootName, String menuName, String serviceName) throws IOException {
		// Fabrique et envoie la commande DT
		byte[] cmdToSend = CamiGenerator.generateCmdDT();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Fabrique et envoie la commande PQ
		cmdToSend = CamiGenerator.generateCmdPQ(rootName, menuName);
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Fabrique et envoie la commande PQ (la deuxième)
		cmdToSend = CamiGenerator.generateCmdPQ(rootName, serviceName);
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Fabrique et envoie la commande FT
		cmdToSend = CamiGenerator.generateCmdFT();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}

	/**
	 * {@inheritDoc}
	 */
	public void askForService(String rootName, String menuName, String serviceName, String date) throws IOException {
		// Génération de la commande MS pour l'envoi de la date de mise à jour
		byte[] cmdToSend = CamiGenerator.generateCmdMS(date);
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Appel de la méthode habituelle
		askForService(rootName, menuName, serviceName);
	}

	/**
	 * {@inheritDoc}
	 */
	public void sendModel(IGraph model) {
		// Transformation du modèle en CAMI
		ArrayList<byte[]> camiModel;
		camiModel = CamiGenerator.generateCamiModel(model);

		// Envoyer un DB : Début de transmission du modele
		byte[] cmdToSend = CamiGenerator.generateCmdDB();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Envoyer le coeur du modèle
		for(int i=0; i<camiModel.size(); i++){
			this.fkLowLevel.writeCommand(camiModel.get(i));
			LOGGER.finer("[CO-->FK] : " + new String(camiModel.get(i), 4, camiModel.get(i).length - 4));
		}

		// Envoyer un FB : Fin de transmission du modele
		cmdToSend = CamiGenerator.generateCmdFB();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}

	/**
	 * {@inheritDoc}
	 */
	public void invalidModel() throws IOException {
		// Fabrique en envoie la commande QQ
		byte[] cmdToSend = CamiGenerator.generateCmdQQ();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));
	}

	/**
	 * {@inheritDoc}
	 */
	public void sendDialogResponse(IDialogAnswer dialogAnswer) throws IOException {
		// Fabrique en envoie la commande DP
		byte[] cmdToSend = CamiGenerator.generateCmdDP();
		this.fkLowLevel.writeCommand(cmdToSend);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend, 4, cmdToSend.length - 4));

		// Le coeur dela réponse à une boite de dialogue
		ArrayList<byte[]> camiDialog;
		camiDialog = CamiGenerator.generateCmdDialogAnswer(dialogAnswer);
		for(int i=0; i<camiDialog.size(); i++){
			this.fkLowLevel.writeCommand(camiDialog.get(i));
			LOGGER.finer("[CO-->FK] : " + new String(camiDialog.get(i), 4, camiDialog.get(i).length - 4));
		}
		
		// Fin de la réponse à la boite de dialogue
		byte[] cmdToSend2 = CamiGenerator.generateCmdFP();
		this.fkLowLevel.writeCommand(cmdToSend2);
		LOGGER.finer("[CO-->FK] : " + new String(cmdToSend2, 4, cmdToSend2.length - 4));
	}
}
