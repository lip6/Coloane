package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;


/**
 * cette interface nous permet de génerer les commandes CAMI.
 * @author KAHOO & UU
 *
 */
public interface ICamiGenerator {

// onverture de la connexion


	/**
	 * nous génére la(les) commande(s) SC.
	 * @param le login.
	 * @param le password.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdSC(String login,String passeword);

	/**
	 * nous génére la(les) commande(s) OC.
	 * @param le nom du client (ici coloane).
	 * @param la version.
	 * @param le login.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdOC(String UiName, String version, String login);


	//ouverture de la session

	/**
	 * nous génére la(les) commande(s) OS.
	 * @param le nom de la session.
	 * @param la date.
	 * @param le formalisme.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdOS(String SessionName,String date,String SessionFormalism);

	/**
	 * nous génére la(les) commande(s) DI.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdDI();

	/**
	 * nous génére la(les) commande(s) CI.
	 * @param le nom de la session.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdCI(String SessionName);

	/**
	 * nous génére la(les) commande(s) FI.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdFI();



//demande de service
	/**
	 * nous génére la(les) commande(s) DT.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdDT();

	/**
	 * nous génére la(les) commande(s) PQ.
	 * @param le root.
	 * @param le nom du service.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdPQ(String rootName, String ServiceName);

	/**
	 * nous génére la(les) commande(s)FT .
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdFT();

	/**
	 * nous génére les commandes relatives a l'envoi du dialogue.
	 * @param le Modele.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCamiModel(IModel m);

	/**
	 * nous génére les commandes relatives a l'envoi du dialogue.
	 * @param le dialogue.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCamiDialogue(IDialog d);

	/**
	 * nous génére la(les) commande(s) SS.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdSS();

	/**
	 * nous génére la(les) commande(s) RS.
	 * @param le nom de la session.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdRS(String SessionName);

	/**
	 * nous génére la(les) commande(s) QQ.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdQQ();

	/**
	 * nous génére la(les) commande(s) MS.
	 * @param la date.
	 * @return un tableau de byte.
	 */
	ArrayList<byte[]> generateCmdMS(String date);
}
