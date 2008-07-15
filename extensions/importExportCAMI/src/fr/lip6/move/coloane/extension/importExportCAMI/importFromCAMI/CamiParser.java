package fr.lip6.move.coloane.extension.importExportCAMI.importFromCAMI;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

import java.util.StringTokenizer;

/**
 * Ensemble d'outils pour la manipulation de commandes CAMI.<br>
 * On trouvera entre autre :
 * <ul>
 * <li> Un parseur d'entier</li>
 * <li> Un parseur de chaine de caracteres</li>
 * <li> Un producteur de chaines conformes CAMI</li>
 * </ul>
 */

public class CamiParser {

	private String message;

	/**
	 * Constructeur
	 * @param commandeCAMI La commande CAMI qui va être parsée
	 */
	public CamiParser(String commandeCAMI) {
		this.message = commandeCAMI.substring(3);
	}

	/**
	 * Retourne l'entier CAMI du message CAMI
	 * Si aucun entier n'est trouve... La methode renvoie la chaine vide
	 * @param token Le separateur
	 * @return l'entier CAMI decouvert (sous forme de chaine de caracteres)
	 */
	public final String parseInt(String token) throws SyntaxErrorException {
		try {
			String res = ""; //$NON-NLS-1$
			int cpt = 0;

			if (!(this.message.subSequence(cpt, cpt + 1)).equals(token)) {
				while (!(this.message.subSequence(cpt, cpt + 1)).equals(token)) {
					res += this.message.subSequence(cpt, cpt + 1);
					cpt++;
				}
				cpt++;
				this.message = this.message.substring(cpt);
				return res;
			} else {
				this.message = this.message.substring(1);
				return ""; //$NON-NLS-1$
			}
		} catch (Exception e) {
			throw new SyntaxErrorException();
		}
	}

	/**
	 * Retourne la chaine CAMI du message CAMI
	 * @param token Le separateur
	 * @return la chaine CAMI
	 */
	public final String parseString(String token) throws SyntaxErrorException {
		try {
			StringTokenizer st = new StringTokenizer(this.message);

			if (!(this.message.subSequence(0, 1)).equals(token)) {

				// Recupere la taille indiquee par la commande CAMI
				String tailleChaine = st.nextToken(":"); //$NON-NLS-1$
				int taille = Integer.parseInt(tailleChaine);

				// Recupere le texte ecrit apres la taille (et les :) jusqu'au bout
				String res = this.message.substring(tailleChaine.length() + 1, tailleChaine.length() + 1 + taille);
				this.message = this.message.substring(tailleChaine.length() + 1 + taille + 1); // ??

				return res;
			} else {
				this.message = this.message.substring(1);
				return ""; //$NON-NLS-1$
			}
		} catch (Exception e) {
			throw new SyntaxErrorException("Erreur de parse : " + token); //$NON-NLS-1$
		}
	}

	/**
	 * Conversion d'une chaine de carateres en message CAMI.<br>
	 * En CAMI chaque chaine de carateres est precede de sa taille et de ':'
	 * @param s la chaine a convertir
	 * @return la chaine convertie
	 */
	public static String stringToCAMI(String s) {

		if (!s.equals("") || s == null) { //$NON-NLS-1$
			String cami = "" + s.length() + ":" + s; //$NON-NLS-1$ //$NON-NLS-2$
			return cami;
		} else {
			return ""; //$NON-NLS-1$
		}
	}
}
