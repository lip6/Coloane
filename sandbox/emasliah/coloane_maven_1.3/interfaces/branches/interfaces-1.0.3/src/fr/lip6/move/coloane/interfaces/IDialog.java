package fr.lip6.move.coloane.interfaces;
/**
 * Interface fournie par Coloane pour l'API.
 * Indications des constantes utiles pour la construction d'une boite de dialogue
 */
public interface IDialog {
	
	/** Dialogue standart */
	public static final int DLG_STANDARD = 1;
	
	/** Dialogue warning */
	public static final int DLG_WARNING = 2;
	
	/** Dialogue erreur */ 
	public static final int DLG_ERROR = 3;
	
	/** Dialogue interractif */
	public static final int DLG_INTERACTIVE = 4;
	
	
	/** Aucun bouton */
	public static final int DLG_NO_BUTTON = 1;
	
	/** Un bouton OK */
	public static final int DLG_OK = 2;
	
	/** Un bouton OK et un bouton Cancel */
	public static final int DLG_OK_CANCEL = 3;
	
	/** Saisie autorisee */
	public static final int INPUT_AUTHORIZED = 1;
	
	/** Saisie interdite */
	public static final int INPUT_FORBIDDEN = 2;
	
	/** Saisie autorisee et echappement possible */
	public static final int INPUT_AND_ABORT_AUTHORIZED = 5;
	
	
	/** Affichage mono-ligne */
	public static final int SINGLE_LINE = 1;
	
	/** Affichage multi-ligne avec selection simple */
	public static final int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	
	/** Affichage multi-ligne avec selection multiple */
	public static final int MULTI_LINE_WITH_MULTI_SELECTION = 5;
}
