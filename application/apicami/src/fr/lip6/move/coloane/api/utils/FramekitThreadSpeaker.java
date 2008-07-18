package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.interfaces.IDialogResult;

import java.util.Vector;

/**
 * Classe implementant le comportement du haut-parleur
 */
public class FramekitThreadSpeaker extends Thread {

	/** Reference vers l'API */
	private Api api;

	/** Reference vers l'objet ComLowLevel */
	private ComLowLevel lowCom;

	/**
	 * Constructeur
	 * @param api point d'entre vers l'api
	 * @param lowCom point d'entree vers la couche bas niveau
	 * @param verrou
	 */
	public FramekitThreadSpeaker(Api a, ComLowLevel lc) {
		this.api = a;
		this.lowCom = lc;
	}


	/**
	 * Ouverture d'une session du cote de FrameKit
	 * @param sessionName Le nom de la session (utilise pour FK)
	 * @param date la date de derniere mise a jour du modele
	 * @param sessionFormalism Le formalisme du modele qu'on envoie
	 * @return booleen Reussite de l'operation d'ouverture de session
	 */
	public final boolean openSession(String sessionName, int date, String sessionFormalism) {
		FKCommand cmd = new FKCommand();

		// Composition de la commande OS
		byte[] send = cmd.createCmdOS(sessionName, date, sessionFormalism);

		try {
			lowCom.writeCommande(send);
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 2);
			return false;
		}

		try {
			lowCom.writeCommande(cmd.createCmdDI());
			lowCom.writeCommande(cmd.createCmdCI(sessionName, 1)); /***/
			lowCom.writeCommande(cmd.createCmdFI());
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 1);
			return false;
		}

		return true;
	}

	/**
	 * Execute un service
	 * @param rootMenuName nom du menu pere
	 * @param menuName nom du menu
	 * @param serviceName nom du service
	 */
	public final void execService(String rootMenuName, String menuName, String serviceName) {
		Object[] param = {rootMenuName, menuName, serviceName};
		Api.getLogger().entering("FrameKitThreadSpeaker", "execService", param);
		FKCommand cmd = new FKCommand();
		try {

			// Si le modele a ete mis a jour depuis le dernier appel de service on doit envoyer un <MS>
			if (this.api.getDirtyState()) {
				byte[] commande = cmd.createCmdMS(this.api.getDateModel());
				lowCom.writeCommande(commande);
			}

			// En-tete du message
			byte[] dt = cmd.createCmdSimple("DT");
			lowCom.writeCommande(dt);

			byte[] pq;

			// Si le nom du pere est vide... Alors le service est directement sous la racine
			if (!menuName.equals(rootMenuName)) {
				pq = cmd.createCmdPQ(rootMenuName , menuName , 1);
				lowCom.writeCommande(pq);
			}

			pq = cmd.createCmdPQ(rootMenuName , serviceName , 1);
			lowCom.writeCommande(pq);

			// Pied du message
			byte[] ft = cmd.createCmdSimple("FT");
			lowCom.writeCommande(ft);

		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 1);

		}
	}

	/**
	 * Envoyer un modele vers la plate-forme
	 */
	public final void sendModel() {
		FKCommand cmd = new FKCommand();

//		Vector<String> modelCami = this.api.getGraph().translate();

		try {
			byte[] commande = cmd.createCmdSimple("DB");
			lowCom.writeCommande(commande);

//			for (String line : modelCami) {
//				commande = cmd.convertToFramekit(line);
//				lowCom.writeCommande(commande);
//			}

			commande = cmd.createCmdSimple("FB");
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 1);
		}
	}

	/**
	 * Changer la date du modele de la session courante
	 * @param newDate nouvelle date
	 * @return boolean
	 */
	public final boolean sendNewDate(int newDate) {
		FKCommand cmd = new FKCommand();
		byte[] commande = cmd.createCmdSimple("QQ");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 1);
			return false;
		}
		return true;
	}

	/**
	 * Envoi a FrameKit une reponse a un dialogue
	 * @param results La reponse a envoyee
	 * @return boolean
	 */
	public final boolean sendDialogueResponse(IDialogResult results) {
		FKCommand cmd = new FKCommand();

		try {
			// Message DP
			byte[] commande = cmd.createCmdSimple("DP");
			lowCom.writeCommande(commande);

			// La reponse effective (Message RD)
			StringBuffer rd = new StringBuffer();
			rd.append("RD(");
			rd.append(results.getDialogId());
			rd.append(",");
			rd.append(results.getAnswerType());
			rd.append(",");
			rd.append(results.hasBeenModified() == true ? 1 : 2);
			rd.append(",");

			if (!results.isMultiLineAnswer()) {
				rd.append(results.getAnswer().get(0).length() + ":" + results.getAnswer().get(0));
			}

			rd.append(")");
			String answer = rd.toString();
			commande = cmd.convertToFramekit(answer);
			lowCom.writeCommande(commande);

			if ((results.getAnswerType() != 2) && (results.isMultiLineAnswer())) {

				if (results.getAnswer().size() <= 0) {
					Api.getLogger().warning("Pas de reponse a transmettre...");
				} else if ((results.getAnswer().size() <= 1) && (results.getAnswer().get(0) == "")) {
					Api.getLogger().warning("Pas de reponse a transmettre...");
				} else {
					// Ensemble de resultats
					commande = cmd.createCmdSimple("DE");
					lowCom.writeCommande(commande);

					// Contenu de la boite de dialogue

					for (String part : results.getAnswer()) {
						StringBuffer ds = new StringBuffer();
						ds.append("DS(");
						ds.append(results.getDialogId());
						ds.append(",");
						ds.append(part.length());
						ds.append(":");
						ds.append(part);
						ds.append(")");
						String value = ds.toString();
						commande = cmd.convertToFramekit(value);
						lowCom.writeCommande(commande);
					}

					commande = cmd.createCmdSimple("FE");
					lowCom.writeCommande(commande);
				}
			}

			// Message FP
			commande = cmd.createCmdSimple("FP");
			lowCom.writeCommande(commande);

		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par FrameKit", 1);
			return false;
		}
		return true;
	}

	/**
	 * Suspendre la session courante
	 */
	public final boolean sendSuspend() {
		byte[] commande;
		FKCommand cmd = new FKCommand();
		commande = cmd.createCmdSimple("SS");
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 1);
		}

		return true;
	}

	/**
	 * Reprendre une session
	 * @param sName le nom de la session
	 */
	public final void sendResume(String sName) {
		byte[] commande;
		FKCommand cmd = new FKCommand();
		commande = cmd.createCmdRS(sName);
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 1);
		}
	}

	/**
	 * Fermer la session courante
	 */
	public final boolean sendClose() {
		byte[] commande;
		FKCommand cmd = new FKCommand();
		commande = cmd.createCmdFS(1);
		try {
			lowCom.writeCommande(commande);
		} catch (CommunicationCloseException e) {
			this.api.closeConnexion(1, "Connexion detruite par Framekit", 1);
		}

		return true;
	}

	/**
	 * arreter un service
	 * @param root le menu
	 * @param service le service
	 * @param option les option du service (vide)
	 * @return boolean
	 */
	public final boolean stopServive(String root, String service, String option) {
		return true;
	}
}
