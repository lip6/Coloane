package fr.lip6.move.coloane.api.interfaces;

/**
 * Interface fournie par l'API pour Coloane
 * Cette interface propose les interractions possibles avec un element de dialogue
 * construit par l'API a l'initiative de la plate-forme
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

	/**
	 * Retourne le type des boutons affiches dans la boite dialogue
	 * @return un indicateur
	 * @see IDialog
	 */
	public int getButtonType();

	/**
	 * Retourne le message par defaut a afficher dans la boite de dialogue
	 * @return La chaine de caracteres contenant la valeur par defaut
	 */
	public String getDefault();

	/**
	 * Indique le message d'aide associe avec la boite de dialogue
	 * @return La chaine de caracteres contenant le message d'aide
	 */
	public String getHelp();

	/**
	 * Indique l'identifiant de la boite de dialogue.<br>
	 * Chaque boite de dialogue creee par la plate-forme possede un identifiant.
	 * @return L'identifiant
	 */
	public int getId();

	/**
	 * Indique le type de saisie autorise<br>
	 * Les valeurs possibles sont presentees dans IDialogCom
	 * @return Le type de saisie de la boite de dialogue
	 */
	public int getInputType();

	/**
	 * Indique le message a afficher dans la boite de dialogue
	 * @return La chaine de caractere a afficher dans la boite de dialogue
	 */
	public String getMessage();

	/**
	 * Indique si la saisie a le droit d'etre multilignes
	 * @return Un indicateur
	 * @see IDialog
	 */
	public int getMultiLine();

	/**
	 * Indique le titre de la boite de dialogue
	 * @return Le titre a afficher en tant que titre de la boite de dialogue
	 */
	public String getTitle();

	/**
	 * Retourne le type de boite de dialogue
	 * @return Le type de boite de dialogue
	 * @see IDialog
	 */
	public int getType();


	
}