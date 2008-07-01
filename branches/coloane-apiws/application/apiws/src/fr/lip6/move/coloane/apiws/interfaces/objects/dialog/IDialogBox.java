package fr.lip6.move.coloane.apiws.interfaces.objects.dialog;

import java.util.ArrayList;

public interface IDialogBox {
	
	/**
	 * Recupere l'identifiant de la boite de dialogue
	 * @return l'identifiant de la boite de dialogue
	 */
	public int getId();
	
	/**
	 * Recupere le type de la boite de dialogue
	 * 1 = Standard
	 * 2 = Avertissement
	 * 3 = Erreur
	 * 4 = intercactive
	 * @return le type de la boite de dialogue
	 */
	public int getType();
	
	/**
	 * Recupere le type de bouttons
	 * 1 = pas de bouttons
	 * 2 = un boutton (OK)
	 * 3 = deux bouttons ( OK / CANCEL )
	 * @return le type de bouttons
	 */
	public int getTypeButton();
	
	/**
	 * Recupere le titre de la boite de dialogue
	 * @return le titre de la boite de dialogue
	 */
	public String getTitle();
	
	/**
	 * Recupere l'aide de la boite de dialogue
	 * @return l'aide de la boite de dialogue
	 */
	public String getHelp();
	
	/**
	 * Recupere le message de la boite de dialogue
	 * @return le message de la boite de dialogue
	 */
	public String getMessage();
	
	/**
	 * Recupere le type de saisie
	 * 1 = saisie autoriser
	 * 2 = saisie non autoriser
	 * 3 = saisie autoriser avec possiblite d'annuler
	 * @return le type de saisie
	 */
	public int getInputType();
	
	/**
	 * Recupere le type du champ de saisie
	 * 1 = 
	 * 2 = 
	 * 5 =
	 * @return le type du champ de saisie
	 */
	public int getLineType();
	
	/**
	 * Recupere la valeur par defaut de la boite de dialogue
	 * @return la valeur par defaut de la boite de dialogue
	 */
	public String getDefaultValue();
	
	public ArrayList<String> getLine();
	
	public boolean getShow();
	
	public IDBAnswer getAnswer();
	
	public boolean isSent();

}
