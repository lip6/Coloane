package fr.lip6.move.coloane.interfaces;
/**
 * Interface fournie par Coloane pour l'API.
 * Indications des constantes utiles pour la construction d'une boite de dialogue
 */
public class DialogConstants {

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
}
