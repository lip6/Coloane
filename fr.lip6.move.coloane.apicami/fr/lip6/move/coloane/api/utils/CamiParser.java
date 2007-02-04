package fr.lip6.move.coloane.api.utils;

import java.util.StringTokenizer;

public class CamiParser {

	private String message;
	
	public CamiParser(String commandeCAMI){
		this.message = commandeCAMI;
	}

	/**
	 * Retourne l'entier CAMI du message CAMI  
	 * @param token : le separateur 
	 * @return l'entier CAMI 
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
	 * @param token : le separateur 
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
	 * Convertie un string en chaine cami
	 * @param s la chaine a convertir
	 * @return la chiane convertie
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

