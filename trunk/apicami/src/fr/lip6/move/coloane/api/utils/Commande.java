package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.api.log.LogsUtils;
import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.interfaces.utils.CamiParser;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Commande {

	private final LogsUtils logsutils = new LogsUtils();
	/**
	 * Fonction qui cree une commande SC
	 * @param login login de l'usager
	 * @param password password de l'usager
	 * @return commande
	 */
	public final byte[] createCmdSC(String login, String password) {
		Object[] param = {login, password};
		Api.apiLogger.entering("Commande", "createCmdSC", param);
		String commande = new String("SC(" + login.length() + ":" + login + "," + password.length() + ":" + password + ")");
		Api.apiLogger.finer("-->" + commande);
		//System.out.println("--> "+commande);

		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (mess.length);
		for (int i = 0; i < mess.length; i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdSC", send);
		return send;
	}

	/**
	 * fonction qui cree une commande de fermeture de connexion
	 * @return la commande
	 */
	public final byte[] createCmdFC() {
		Api.apiLogger.entering("Commande", "createCmdFC");
		//Integer versInt = new Integer(1);
		//String versStg = versInt.toString();
		String commande = new String("FC(" /*+ versStg + */ + ")");
		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (commande.length());
		for (int i = 0; i < commande.length(); i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdFC", send);
		return send;

	}

	/**
	 * fonction qui cree une commande DI debut choix interlocuteur
	 * @return la commande
	 */
	public final byte[] createCmdDI() {
		Api.apiLogger.entering("Commande", "createCmdDI");
		String commande = new String("DI()");
		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (commande.length());
		for (int i = 0; i < commande.length(); i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdDI", send);
		return send;
	}

	/**
	 * fonction qui cree une commande CI choix interlocuteur
	 * @param serviceName : le nom du service
	 * @param mode : interactif ou batch
	 * @return la commande
	 */
	public final byte[] createCmdCI(String serviceName, int mode) {
		Object[] param = {serviceName, mode};
		Api.apiLogger.entering("Commande", "createCmdCI", param);
		String commande = new String("CI(" + CamiParser.stringToCAMI(serviceName) + "," + mode + ")");
		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (commande.length());
		for (int i = 0; i < commande.length(); i++) {
			send[i + 4] = mess[i];
		}
		//System.out.println("commande :" + new String(send, 4, send.length - 4));
		Api.apiLogger.exiting("Commande", "createCmdCI", send);
		return send;
	}

	/**
	 * fonction qui cree une commande FI fin choix interlocuteur
	 * @return la commande
	 */
	public final byte[] createCmdFI() {
		Api.apiLogger.entering("Commande", "createCmdFI");
		String commande = new String("FI()");
		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (commande.length());
		for (int i = 0; i < commande.length(); i++) {
			send[i + 4] = mess[i];
		}
		//System.out.println("commande :" + new String(send, 4, send.length - 4));
		Api.apiLogger.exiting("Commande", "createCmdFI", send);
		return send;
	}




	/**
	 * Fonction qui cree une commande OC
	 * @param nameUi le nom de l'API
	 * @param version la version du protocole CAMI
	 * @param login le login de l'usager
	 * @return la commande
	 */
	public final byte[] createCmdOC(String nameUi, String version, String login) {
//		Integer versInt = new Integer(version);
//		String versStg = versInt.toString();
		Object[] param = {nameUi, version, login};
		Api.apiLogger.entering("Commande", "createCmdOC", param);
		String versStg = version;
		String commande = new String("OC(" + nameUi.length() + ":" + nameUi
				+ "," + versStg.length() + ":" + versStg + "," + login.length()
				+ ":" + login + "," + 0 + ")");
		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (commande.length());
		for (int i = 0; i < commande.length(); i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdOC", send);
		return send;
	}

	/**
	 * construit une commande OS
	 * @param sName nom du modele
	 * @param date date du modele
	 * @param formalism formalism du modele
	 * @return la commande
	 */
	public final byte[] createCmdOS(String sName, int date, String formalism) {
		Object[] param = {sName, date, formalism};
		Api.apiLogger.entering("Commande", "createCmdOS", param);
		Integer versInt = new Integer(date);
		String versStg = versInt.toString();
		String commande = new String("OS(" + sName.length() + ":" + sName + ","
				+ versStg + "," + formalism.length() + ":" + formalism + ")");

		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (mess.length);
		for (int i = 0; i < mess.length; i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdOS", send);
		return send;
	}

	/**
	 * Reprise de la session
	 * @param sName le nom de la session
	 * @return la commande cami
	 */
	public final byte[] createCmdRS(String sName) {
		Api.apiLogger.entering("Commande", "createCmdRS", sName);
		String commande = new String("RS(" + sName.length() + ":" + sName + ")");

		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		Api.apiLogger.finer("message RS :" + new String(mess, 0, mess.length));
		//System.out.println("message RS : " + new String(mess, 0, mess.length));
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (mess.length);
		for (int i = 0; i < mess.length; i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdRS", send);
		return send;
	}


	/**
	 * Fermeture session
	 * @param calcul permet de demander a framekit de continuer le calcul pendant la deconnexion: non supporter par
	 * framekit calcul vaut 1
	 * @return la commande cami
	 */
	public final byte[] createCmdFS(int calcul) {
		Api.apiLogger.entering("Commande", "createCmdFS", calcul);
		Integer calInt = new Integer(calcul);
		String calStg = calInt.toString();
		String commande = new String("FS("  + calStg + ")");

		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (mess.length);
		for (int i = 0; i < mess.length; i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdFS", send);
		return send;
	}
	/**
	 * change la date du modele de la session courante
	 * @param date nouvelle date
	 * @return la commande cami
	 */
	public final byte[] createCmdMS(int date) {
		Api.apiLogger.entering("Commande", "createCmdMS", date);
		Integer calInt = new Integer(date);
		String calStg = calInt.toString();
		String commande = new String("MS("  + calStg + ")");

		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (mess.length);
		for (int i = 0; i < mess.length; i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdMS", send);
		return send;
	}

	/**
	 * formate une commande CAMI sans arguments (rajoute la taille de la commande sur 4 octets avant celle-ci, et les parentheses)
	 * @param type type de la commande
	 * @return commande CAMI formatee
	 */
	public final byte[] createCmdSimple(String type) {
		Api.apiLogger.entering("Commande", "createCmdSimple", type);
		String commande = new String(type + "()");
		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (commande.length());
		for (int i = 0; i < commande.length(); i++) {
			send[i + 4] = mess[i];
		}
		//System.out.println("commande :" + new String(send, 4, send.length - 4));
		Api.apiLogger.exiting("Commande", "createCmdSimple", send);
		return send;


	}

	/**
	 * formate une commande PQ CAMI (rajoute la taille de la commande sur 4 octets avant celle-ci)
	 * @param rootName racine du Menu
	 * @param label identifiant de la question
	 * @param suite defini si la suite a une question ou non
	 * @return commande CAMI formatee
	 */
	public final byte[] createCmdPQ(String rootName, String label, int suite) {
		Object[] param = {rootName, label, suite};
		Api.apiLogger.entering("Commande", "createCmdPQ", param);
		String commande = new String("PQ(" + CamiParser.stringToCAMI(rootName) + "," + CamiParser.stringToCAMI(label) + "," + suite + ")");
		byte[] send = new byte[commande.length() + 4];
		byte[] mess = commande.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (commande.length());
		for (int i = 0; i < commande.length(); i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "createCmdSimple", send);
		//System.out.println("commande :" + new String(send, 4, send.length - 4));
		return send;
	}

	/**
	 * formate une commande CAMI (rajoute la taille de la commande sur 4 octets avant celle-ci)
	 * @param cami comande CAMI a formater
	 * @return commande CAMI formatee
	 */
	public final byte[] convertToFramekit(String cami) {
		Api.apiLogger.entering("Commande", "convertToFrameKit", cami);
		byte[] send = new byte[cami.length() + 4];
		byte[] mess = cami.getBytes();
		send[0] = 0;
		send[1] = 0;
		send[2] = 0;
		send[3] = (byte) (cami.length());
		for (int i = 0; i < mess.length; i++) {
			send[i + 4] = mess[i];
		}
		Api.apiLogger.exiting("Commande", "convertToFramekit", send);
		return send;
	}

	/**
	 * Recupere sous forme de vecteur la commande recu et l'ensemble des argument de la commande
	 * @param commande commande recu
	 * @return vecteur retourner
	 */
	public final Vector getArgs(String commande) {
		Api.apiLogger.entering("Commande", "getArgs", commande);
		String cmdString = "";
		Vector<String> liste = new Vector<String>();

		StringTokenizer st = new StringTokenizer(commande);
		CamiParser parser = null;

		try {
			// Construit le parser sur la chaine d'arguments
			parser = new CamiParser(commande.substring(3));
			// Code de la commande
			cmdString = st.nextToken("(");
		} catch (NoSuchElementException e) {
			Api.apiLogger.throwing("Commande", "getArgs", e);
			Api.apiLogger.warning("NosuchElement : " + e.getMessage());
			Api.apiLogger.warning(logsutils.stackToString(e));
			//System.out.println("NoSuchElement : " + e.getMessage());
			Api.apiLogger.exiting("Commande", "getArgs", null);
			Api.apiLogger.warning(logsutils.stackToString(e));
			return null;
		} catch (StringIndexOutOfBoundsException e) {
			Api.apiLogger.throwing("Commande", "getArgs", e);
			Api.apiLogger.warning("StringIndexOutOfBoundsException : " + e.getMessage());
			Api.apiLogger.warning(logsutils.stackToString(e));
			//System.out.println("StringIndexOutOfBoundsException : " + e.getMessage());
			Api.apiLogger.exiting("Commande", "getArgs", null);
			return null;
		}

		// Premier element du vecteur : le sigle de la commande
		liste.add(0, cmdString);

		// Connexion � la plateforme:
		if (cmdString.equals("SC")) {
			String message = parser.parseString(")");
			liste.add(message);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// Connexion � la plateforme:
		if (cmdString.equals("OC")) {
			String entier1;
			String entier2;

			entier1 = parser.parseInt(",");
			entier2 = parser.parseInt(")");

			liste.add(entier1);
			liste.add(entier2);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// Connexion du mod�le:
		if (cmdString.equals("OS")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		//suspendre session
		if (cmdString.equals("SS")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}
		//Resume session
		if (cmdString.equals("RS")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		//Fermer session
		if (cmdString.equals("FS")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("TD")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("FA")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("TL")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// STRING STRING INT INT
		if (cmdString.equals("VI")) {
			String s1;
			String s2;
			String entier1;
			String entier2;

			s1 = parser.parseString(",");
			s2 = parser.parseString(",");
			entier1 = parser.parseInt(",");
			entier2 = parser.parseInt(")");

			liste.add(s1);
			liste.add(s2);
			liste.add(entier1);
			liste.add(entier2);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("FL")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}
		// Menu : init
		if (cmdString.equals("DQ")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// Menu : root node
		if (cmdString.equals("CQ")) {
			String s1;
			String entier1;
			String entier2;


			s1 = parser.parseString(",");
			entier1 = parser.parseInt(",");
			entier2 = parser.parseInt(")");

			liste.add(s1);
			liste.add(entier1);
			liste.add(entier2);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// Menu : child node
		if (cmdString.equals("AQ")) {
			String s1;
			String s2;
			String entier1 = "";
			String entier2 = "";
			String entier3 = "";
			String entier4 = "";
			String entier5 = "";
			String s3 = "";
			String entier6 = "";

			s1 = parser.parseString(",");
			s2 = parser.parseString(",");
			entier1 = parser.parseInt(",");
			entier2 = parser.parseInt(",");
			entier3 = parser.parseInt(",");
			entier4 = parser.parseInt(",");
			entier5 = parser.parseInt(",");
			s3 = parser.parseString(",");
			entier6 = parser.parseInt(")");

			liste.add(s1);
			liste.add(s2);

			if (entier1.equals("")) {
				liste.add(null);
			} else {
				liste.add(entier1);
			}
			if (entier2.equals("")) {
				liste.add(null);
			} else {
				liste.add(entier2);
			}
			if (entier3.equals("")) {
				liste.add(null);
			} else {
				liste.add(entier3);
			}
			if (entier4.equals("")) {
				liste.add(null);
			} else {
				liste.add(entier4);
			}
			if (entier5.equals("")) {
				liste.add(null);
			} else {
				liste.add(entier5);
			}
			if (s3.equals("")) {
				liste.add(null);
			} else {
				liste.add(s3);
			}
			if (entier6.equals("")) {
				liste.add(null);
			} else {
				liste.add(entier6);
			}

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// Menu : help node
		if (cmdString.equals("HQ")) {
			String s1;
			String s2;

			s1 = parser.parseString(",");
			s2 = parser.parseString(")");

			liste.add(s1);
			liste.add(s2);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}


		// Menu : End
		if (cmdString.equals("FQ")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}


		// Service : afficher le menu
		if (cmdString.equals("VQ")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		//Service : effacer le menu
		if (cmdString.equals("EQ")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		//Service : supprimer le menu
		if (cmdString.equals("SQ")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}


		// Service : init
		if (cmdString.equals("TQ")) {

			String s1;
			String s2;
			String entier1;
			String s3; //DOC INCOMPLETE A CE SUJET


			s1 = parser.parseString(",");
			s2 = parser.parseString(",");
			entier1 = parser.parseInt(",");
			s3 = parser.parseString(")");

			liste.add(s1);
			liste.add(s2);
			liste.add(entier1);
			liste.add(s3);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// Service : init
		if (cmdString.equals("QQ")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// demande fichier
		if (cmdString.equals("DF")) {
			String entier1;
			String entier2;
			String entier3;

			entier1 = parser.parseInt(",");
			entier2 = parser.parseInt(",");
			entier3 = parser.parseInt(")");

			liste.add(entier1);
			liste.add(entier2);
			liste.add(entier3);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		//debut de reponse
		if (cmdString.equals("DR")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		//fin de reponse
		if (cmdString.equals("FR")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		//Question a laquelle on reponds
		if (cmdString.equals("RQ")) {
			String s1;
			String s2;
			String entier1;

			s1 = parser.parseString(",");
			s2 = parser.parseString(",");
			entier1 = parser.parseInt(")");

			liste.add(s1);
			liste.add(s2);
			liste.add(entier1);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// message speciale
		if (cmdString.equals("MO")) {
			String entier1;
			String s1;

			entier1 = parser.parseInt(",");
			s1 = parser.parseString(")");

			liste.add(entier1);
			liste.add(s1);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// message de trace
		if (cmdString.equals("TR")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// message de warnnig
		if (cmdString.equals("WN")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// afficher dialogue
		if (cmdString.equals("AD")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// cacher dialogue
		if (cmdString.equals("HD")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// detruire dialogue
		if (cmdString.equals("DG")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// debut dialogue
		if (cmdString.equals("DC")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// fin dialogue
		if (cmdString.equals("FF")) {
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// dialogue suite
		if (cmdString.equals("DS")) {
			String entier1;
			String s1;

			entier1 = parser.parseInt(",");
			s1 = parser.parseString("@");

			liste.add(entier1);
			liste.add(s1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		// creer dialogue
		if (cmdString.equals("CE")) {
			String entier1;
			String entier2;
			String entier3;
			String s1;
			String s2;
			String s3;
			String entier4;
			String entier5;
			String s4;

			entier1 = parser.parseInt(",");
			entier2 = parser.parseInt(",");
			entier3 = parser.parseInt(",");
			s1 = parser.parseString(",");
			s2 = parser.parseString(",");
			s3 = parser.parseString(",");
			entier4 = parser.parseInt(",");
			entier5 = parser.parseInt(",");
			s4 = parser.parseString(")");

			liste.add(entier1);
			liste.add(entier2);
			liste.add(entier3);
			liste.add(s1);
			liste.add(s2);
			liste.add(s3);
			liste.add(entier4);
			liste.add(entier5);
			liste.add(s4);

			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("DE")) {
			String s1;
			String entier1;
			Api.apiLogger.finer(commande);
			//System.out.println(commande);

			if (commande.length() > 4) {
				s1 = parser.parseString(",");
				entier1 = parser.parseInt(")");

				liste.add(s1);
				liste.add(entier1);
			}
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("RT")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("RO")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("ME")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		if (cmdString.equals("FE")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			Api.apiLogger.exiting("Commande", "getArgs", liste);
			return liste;
		}

		Api.apiLogger.warning("!Commande non traitee : " + cmdString);
		//System.out.println("! Commande non traitee : " + cmdString);
		//on retourne jsute l'entete de la commande
		Api.apiLogger.exiting("Commande", "getArgs", liste);
		return liste;

	}
}

