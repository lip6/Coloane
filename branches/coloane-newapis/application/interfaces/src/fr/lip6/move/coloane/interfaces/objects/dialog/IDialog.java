package fr.lip6.move.coloane.interfaces.objects.dialog;

import java.util.ArrayList;

public interface IDialog {
	
	/** Dialogue standart */
	static final int DLG_STANDARD = 1;
	/** Dialogue warning */
	static final int DLG_WARNING = 2;
	/** Dialogue erreur */
	static final int DLG_ERROR = 3;
	/** Dialogue interractif */
	static final int DLG_INTERACTIVE = 4;

	/** Aucun bouton */
	static final int DLG_NO_BUTTON = 1;
	/** Un bouton OK */
	static final int DLG_OK = 2;
	/** Un bouton OK et un bouton Cancel */
	static final int DLG_OK_CANCEL = 3;

	/** Saisie autorisee */
	static final int INPUT_AUTHORIZED = 1;
	/** Saisie interdite */
	static final int INPUT_FORBIDDEN = 2;
	/** Saisie autorisee et echappement possible */
	static final int INPUT_AND_ABORT_AUTHORIZED = 5;

	/** Affichage mono-ligne */
	static final int SINGLE_LINE = 1;
	/** Affichage multi-ligne avec selection simple */
	static final int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	/** Affichage multi-ligne avec selection multiple */
	static final int MULTI_LINE_WITH_MULTI_SELECTION = 5;
	
	
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
	 * 4 = Intercactive
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
	 * 1 = affichage mono-ligne 
	 * 2 = affichage multi-ligne avec selection simple
	 * 5 = affichage multi-ligne avec selection multiple
	 * @return le type du champ de saisie
	 */
	public int getLineType();
	
	/**
	 * Recupere la valeur par defaut de la boite de dialogue
	 * @return la valeur par defaut de la boite de dialogue
	 */
	public String getDefaultValue();
	
	/**
	 * Recupere la liste des lignes
	 * @return la liste des lignes
	 */
	public ArrayList<String> getLines();
	
}