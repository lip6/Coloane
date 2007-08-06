package fr.lip6.move.coloane.interfaces.objects;

/**
 * Interface fournie par l'API pour Coloane
 * Cette interface propose les interractions possibles avec un element de dialogue
 * construit par l'API a l'initiative de la plate-forme
 */
public interface IDialogCom {

	/**
	 * Retourne le type des boutons affiches dans la boite dialogue
	 * @return un indicateur
	 * @see IDialogCom
	 */
	int getButtonType();

	/**
	 * TODO: Documenter
	 */
	String getDef();

	/**
	 * Indique le message d'aide associe avec la boite de dialogue
	 * @return La chaine de caractere contenant le message d'aide
	 */
	String getHelp();

	/**
	 * Indique l'identifiant de la boite de dialogue.<br>
	 * Chaque boite de dialogue creee par la plate-forme possede un identifiant.
	 * @return L'identifiant
	 */
	int getId();

	/**
	 * Indique le type de saisie autorise<br>
	 * Les valeurs possibles sont presentees dans IDialogCom
	 * @return Le type de saisie de la boite de dialogue
	 */
	int getInputType();

	/**
	 * Indique le message a afficher dans la boite de dialogue
	 * @return La chaine de caractere a afficher dans la boite de dialogue
	 */
	String getMessage();

	/**
	 * Indique si la saisie a le droit d'etre multilignes
	 * @return Un indicateur
	 * @see IDialogCom
	 */
	int getMultiLine();

	/**
	 * Indique le titre de la boite de dialogue
	 * @return Le titre a afficher en tant que titre de la boite de dialogue
	 */
	String getTitle();

	/**
	 * Retourne le type de boite de dialogue
	 * @return Le type de boite de dialogue
	 * @see IDialogCom
	 */
	int getType();

	/**
	 * Positionne la valeur du type de saisie de la boite de dialogue.<br>
	 * Les types possibles sont presentes dans IDialogCom
	 * @param inputType Le type de saisie associe a la boite de dialogue
	 */
	void setInputType(int inputType);

	/**
	 * Permet d'indiquer quel est le type de boutons a afficher dans la boite de dialogue
	 * @param buttonType Un indicateur
	 * @see IDialogCom
	 */
	void setButtonType(int buttonType);

	/**
	 * TODO : Documenter
	 */
	void setDef(String def);

	/**
	 * Positionne la valeur du message d'aide de la boite de dialogue
	 * @param help Le message d'aide
	 */
	void setHelp(String help);

	/**
	 * Positionne la valeur de l'identifiant de la boite de dialogue.<br>
	 * En general, la plate-forme fourni cet identifiant. Ne le gerez pas vous-meme !
	 * @param id L'identifiant
	 */
	void setId(int id);

	/**
	 * Positionne la valeur du message de la boite de dialogue
	 * @param message Le message a afficher
	 */
	void setMessage(String message);

	/**
	 * Positionne l'indicateur de saisie multiligne
	 * @param multiLine La valeur a affecter a l'indicateur de saisie multiligne
	 * @see IDialogCom
	 */
	void setMultiLine(int multiLine);

	/**
	 * Positionne le titre de la boite de dialogue
	 * @param title Le titre de la boite de dialogue
	 */
	void setTitle(String title);

	/**
	 * Positionne le type de la boite de dialogue
	 * @param type Le type de la boite de dialogue
	 * @see IDialogCom
	 */
	void setType(int type);

}
