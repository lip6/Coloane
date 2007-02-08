package fr.lip6.move.coloane.api.utils;

import java.util.StringTokenizer;

/**
 * Ensemble d'outils pour la manipulation de commandes CAMI.<br>
 * On trouvera entre autre :
 * <ul>
 * 	<li> Un parseur d'entier</li>
 * 	<li> Un parseur de chaie de caracteres</li>
 * 	<li> Un producteur de chaines conformes CAMI</li>
 * </ul>
 */

public class CamiParser {

	private String message;
	
	public CamiParser(String commandeCAMI){
		this.message = commandeCAMI;
	}

	/**
	 * Retourne l'entier CAMI du message CAMI  
	 * @param token Le separateur 
	 * @return l'entier CAMI decouvert (sous forme de chaine de caracteres)
	 * Si aucun entier n'est trouve... La methode renvoie la chaine vide 
	 */
	public String parseInt(String token){
		String res = "";
		int cpt = 0;
		
		if (!(this.message.subSequence(cpt , cpt + 1)).equals(token)) {
			while (!(this.message.subSequence(cpt , cpt + 1)).equals(token)) {
				res += this.message.subSequence(cpt , cpt + 1);
				cpt++;
			}
			cpt++;
			this.message = this.message.substring(cpt);
			return res;
		} else {
			this.message = this.message.substring(1);
			return "";
		}
	}

	/**
	 * Retourne la chaine CAMI du message CAMI  
	 * @param token Le separateur 
	 * @return la chaine CAMI 
	 */
	public String parseString(String token){
		StringTokenizer st = new StringTokenizer(this.message);
		String tailleChaine;
		int taille;
		String res="";
				
		if (!(this.message.subSequence(0 , 1)).equals(token)) {
			
			// Recupère la taille indiquee par la commande CAMI
			tailleChaine = st.nextToken(":");
			taille = Integer.parseInt(tailleChaine);
			
			// Recupère le texte ecrit après la taille (et les :) jusqu'au bout
			res = this.message.substring(tailleChaine.length() + 1 , tailleChaine.length() + 1 + taille);	
			this.message = this.message.substring(tailleChaine.length() + 1 + taille + 1); // ??
			return res;
		} else {
			this.message = this.message.substring(1);
			return "";
		}
	}

	/**
	 * Conversion d'une chaine de carateres en message CAMI.
	 * En CAMI chaque chaine de carateres est precede de sa taille et de ':'
	 * @param s la chaine a convertir
	 * @return la chaine convertie
	 */
	public static String stringToCAMI(String s) {
		if (!s.equals("") || s == null) {
			String cami = "" + s.length() + ":" + s;
			return cami;
		} else {
			return "";
		}
	}
}

