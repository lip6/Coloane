package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.utils.CamiParser;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

public class FKCommand {

	/**
	 * Prepare la commande CAMI conformement aux requirements de FK :<br>
	 * 3 premiers octects a 0
	 * 4eme octet indiquant la longueur du message
	 *
	 * @param command
	 * @return
	 */
	private byte[] fillZero(String command) {
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
	 * Creation de la commande SC : Authentification
	 * @param login login de l'usager
	 * @param password password de l'usager
	 * @return commande CAMI
	 */
	public final byte[] createCmdSC(String login, String password) {
		String command = new String("SC(" + login.length() + ":" + login + "," + password.length() + ":" + password + ")");
		return fillZero(command);
	}

	/**
	 * Creation de la commande FC : Fermeture de connexion
	 * @return commande CAMI
	 */
	public final byte[] createCmdFC() {
		String command = new String("FC()");
		return fillZero(command);
	}

	/**
	 * Creation de la commande DI : Debut choix interlocuteur
	 * @return commande CAMI
	 */
	public final byte[] createCmdDI() {
		String command = new String("DI()");
		return fillZero(command);
	}

	/**
	 * Creation de la commande CI : Choix interlocuteur
	 * @param serviceName : le nom du service
	 * @param mode : interactif ou batch
	 * @return commande CAMI
	 */
	public final byte[] createCmdCI(String serviceName, int mode) {
		String command = new String("CI(" + CamiParser.stringToCAMI(serviceName) + "," + mode + ")");
		return fillZero(command);
	}

	/**
	 * fonction qui cree une commande FI fin choix interlocuteur
	 * @return la commande
	 */
	public final byte[] createCmdFI() {
		String command = new String("FI()");
		return fillZero(command);
	}

	/**
	 * Fonction qui cree une commande OC
	 * @param nameUi le nom de l'API
	 * @param version la version du protocole CAMI
	 * @param login le login de l'usager
	 * @return la commande
	 */
	public final byte[] createCmdOC(String nameUi, String version, String login) {
		String command = new String("OC(" + nameUi.length() + ":" + nameUi + "," + version.length() + ":" + version + "," + login.length()	+ ":" + login + "," + 0 + ")");
		return fillZero(command);
	}

	/**
	 * construit une commande OS
	 * @param sName nom du modele
	 * @param date date du modele
	 * @param formalism formalism du modele
	 * @return la commande
	 */
	public final byte[] createCmdOS(String sName, int date, String formalism) {
		String versStg = new Integer(date).toString();
		String command = new String("OS(" + sName.length() + ":" + sName + ","	+ versStg + "," + formalism.length() + ":" + formalism + ")");
		return fillZero(command);
	}

	/**
	 * Reprise de la session
	 * @param sName le nom de la session
	 * @return la commande cami
	 */
	public final byte[] createCmdRS(String sName) {
		String command = new String("RS(" + sName.length() + ":" + sName + ")");
		return fillZero(command);
	}

	/**
	 * Fermeture session
	 * @param calcul permet de demander a framekit de continuer le calcul pendant la deconnexion: non supporter par
	 * framekit calcul vaut 1
	 * @return la commande cami
	 */
	public final byte[] createCmdFS(int calcul) {
		String calStg = new Integer(calcul).toString();
		String command = new String("FS("  + calStg + ")");
		return fillZero(command);
	}
	/**
	 * change la date du modele de la session courante
	 * @param date nouvelle date
	 * @return la commande cami
	 */
	public final byte[] createCmdMS(int date) {
		String calStg = new Integer(date).toString();
		String command = new String("MS("  + calStg + ")");
		return fillZero(command);
	}

	/**
	 * formate une commande CAMI sans arguments (rajoute la taille de la commande sur 4 octets avant celle-ci, et les parentheses)
	 * @param type type de la commande
	 * @return commande CAMI formatee
	 */
	public final byte[] createCmdSimple(String type) {
		String command = new String(type + "()");
		return fillZero(command);
	}

	/**
	 * formate une commande PQ CAMI (rajoute la taille de la commande sur 4 octets avant celle-ci)
	 * @param rootName racine du Menu
	 * @param label identifiant de la question
	 * @param suite defini si la suite a une question ou non
	 * @return commande CAMI formatee
	 */
	public final byte[] createCmdPQ(String rootName, String label, int suite) {
		String command = new String("PQ(" + CamiParser.stringToCAMI(rootName) + "," + CamiParser.stringToCAMI(label) + "," + suite + ")");
		return fillZero(command);
	}

	/**
	 * formate une commande CAMI (rajoute la taille de la commande sur 4 octets avant celle-ci)
	 * @param cami comande CAMI a formater
	 * @return commande CAMI formatee
	 */
	public final byte[] convertToFramekit(String cami) {
		return fillZero(cami);
	}

	/**
	 * Recupere sous forme de vecteur la commande recue et l'ensemble des argument de la commande
	 * @param commande commande recue
	 * @return Vecteur des arguements recus
	 */
	public static Vector<String> getArgs(String commande) throws SyntaxErrorException {
		String cmdString = "";
		Vector<String> liste = new Vector<String>();

		StringTokenizer st = new StringTokenizer(commande);
		CamiParser parser = null;

		try {
			// Construit le parser sur la chaine d'arguments
			parser = new CamiParser(commande);
			// Code de la commande
			cmdString = st.nextToken("(");
		} catch (NoSuchElementException e) {
			return null;
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}

		// Premier element du vecteur : le sigle de la commande
		liste.add(0, cmdString);

		// Deconnexion brutale :
		if (cmdString.equals("KO")) {
			String entier1;
			String reason;
			String severity;

			entier1 = parser.parseInt(",");
			reason = parser.parseString(",");
			severity = parser.parseInt(")");
			liste.add(entier1);
			liste.add(reason);
			liste.add(severity);
			return liste;
		}

		// Connexion a la plateforme:
		if (cmdString.equals("SC")) {
			String message = parser.parseString(")");
			liste.add(message);
			return liste;
		}

		// Connexion a la plateforme:
		if (cmdString.equals("OC")) {
			String entier1;
			String entier2;

			entier1 = parser.parseInt(",");
			entier2 = parser.parseInt(")");

			liste.add(entier1);
			liste.add(entier2);
			return liste;
		}

		// Connexion du modele:
		if (cmdString.equals("OS")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			return liste;
		}

		//suspendre session
		if (cmdString.equals("SS")) {
			return liste;
		}

		//Resume session
		if (cmdString.equals("RS")) {
			return liste;
		}

		//Fermer session
		if (cmdString.equals("FS")) {
			return liste;
		}

		if (cmdString.equals("TD")) {
			return liste;
		}

		if (cmdString.equals("FA")) {
			return liste;
		}

		if (cmdString.equals("TL")) {
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

			return liste;
		}

		if (cmdString.equals("FL")) {
			return liste;
		}

		// Menu : init
		if (cmdString.equals("DQ")) {
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

			return liste;
		}


		// Menu : End
		if (cmdString.equals("FQ")) {
			return liste;
		}


		// Service : afficher le menu
		if (cmdString.equals("VQ")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			return liste;
		}

		//Service : effacer le menu
		if (cmdString.equals("EQ")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			return liste;
		}

		//Service : supprimer le menu
		if (cmdString.equals("SQ")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
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

			return liste;
		}

		// Service : init
		if (cmdString.equals("QQ")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);

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

			return liste;
		}

		//debut de reponse
		if (cmdString.equals("DR")) {
			return liste;
		}

		//fin de reponse
		if (cmdString.equals("FR")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
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

			return liste;
		}

		// message de trace
		if (cmdString.equals("TR")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			return liste;
		}

		// message de warnnig
		if (cmdString.equals("WN")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			return liste;
		}

		// afficher dialogue
		if (cmdString.equals("AD")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			return liste;
		}

		// cacher dialogue
		if (cmdString.equals("HD")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			return liste;
		}

		// detruire dialogue
		if (cmdString.equals("DG")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			return liste;
		}

		// debut dialogue
		if (cmdString.equals("DC")) {
			return liste;
		}

		// fin dialogue
		if (cmdString.equals("FF")) {
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
			return liste;
		}

		if (cmdString.equals("DE")) {
			String s1;
			String entier1;
			if (commande.length() > 4) {
				s1 = parser.parseString(",");
				entier1 = parser.parseInt(")");
				liste.add(s1);
				liste.add(entier1);
			}
			return liste;
		}

		if (cmdString.equals("RT")) {
			String s1;
			s1 = parser.parseString(")");
			liste.add(s1);
			return liste;
		}

		if (cmdString.equals("RO")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			return liste;
		}

		if (cmdString.equals("ME")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			return liste;
		}

		if (cmdString.equals("XA")) {
			String entier1;
			String s1;
			String s2;
			entier1 = parser.parseInt(",");
			s1 = parser.parseString(",");
			s2 = parser.parseString(")");
			liste.add(entier1);
			liste.add(s1);
			liste.add(s2);
			return liste;
		}

		if (cmdString.equals("FE")) {
			String entier1;
			entier1 = parser.parseInt(")");
			liste.add(entier1);
			return liste;
		}

		return liste;
	}
}

