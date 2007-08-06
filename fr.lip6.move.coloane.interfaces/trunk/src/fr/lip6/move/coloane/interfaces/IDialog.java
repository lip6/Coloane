package fr.lip6.move.coloane.interfaces;
/**
 * Interface fournie par Coloane pour l'API.
 * Indications des constantes utiles pour la construction d'une boite de dialogue
 */
public interface IDialog {

	/** Dialogue standart */
	int DLG_STANDARD = 1;

	/** Dialogue warning */
	int DLG_WARNING = 2;

	/** Dialogue erreur */
	int DLG_ERROR = 3;

	/** Dialogue interractif */
	int DLG_INTERACTIVE = 4;

	/** Aucun bouton */
	int DLG_NO_BUTTON = 1;

	/** Un bouton OK */
	int DLG_OK = 2;

	/** Un bouton OK et un bouton Cancel */
	int DLG_OK_CANCEL = 3;

	/** Saisie autorisee */
	int INPUT_AUTHORIZED = 1;

	/** Saisie interdite */
	int INPUT_FORBIDDEN = 2;

	/** Saisie autorisee et echappement possible */
	int INPUT_AND_ABORT_AUTHORIZED = 5;

	/** Affichage mono-ligne */
	int SINGLE_LINE = 1;

	/** Affichage multi-ligne avec selection simple */
	int MULTI_LINE_WITH_SINGLE_SELECTION = 2;

	/** Affichage multi-ligne avec selection multiple */
	int MULTI_LINE_WITH_MULTI_SELECTION = 5;
}
