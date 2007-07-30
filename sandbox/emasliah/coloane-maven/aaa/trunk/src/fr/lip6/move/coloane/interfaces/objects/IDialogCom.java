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
	public abstract int getButtonType();

	/**
	 * TODO: Documenter
	 */
	public abstract String getDef();

	/**
	 * Indique le message d'aide associe avec la boite de dialogue
	 * @return La chaine de caractere contenant le message d'aide
	 */
	public abstract String getHelp();

	/**
	 * Indique l'identifiant de la boite de dialogue.<br>
	 * Chaque boite de dialogue creee par la plate-forme possede un identifiant.
	 * @return L'identifiant
	 */
	public abstract int getId();

	/**
	 * Indique le type de saisie autorise<br>
	 * Les valeurs possibles sont presentees dans IDialogCom
	 * @return Le type de saisie de la boite de dialogue
	 */
	public abstract int getInputType();

	/**
	 * Indique le message a afficher dans la boite de dialogue
	 * @return La chaine de caractere a afficher dans la boite de dialogue
	 */
	public abstract String getMessage();

	/**
	 * Indique si la saisie a le droit d'etre multilignes
	 * @return Un indicateur
	 * @see IDialogCom
	 */
	public abstract int getMultiLine();

	/**
	 * Indique le titre de la boite de dialogue
	 * @return Le titre a afficher en tant que titre de la boite de dialogue
	 */
	public abstract String getTitle();

	/**
	 * Retourne le type de boite de dialogue
	 * @return Le type de boite de dialogue
	 * @see IDialogCom
	 */
	public abstract int getType();
	
	/**
	 * Positionne la valeur du type de saisie de la boite de dialogue.<br>
	 * Les types possibles sont presentes dans IDialogCom
	 * @param inputType Le type de saisie associe a la boite de dialogue
	 */
	public void setInputType(int inputType);
	
	/**
	 * Permet d'indiquer quel est le type de boutons a afficher dans la boite de dialogue
	 * @param buttonType Un indicateur
	 * @see IDialogCom
	 */
	public void setButtonType(int buttonType);
	
	/**
	 * TODO : Documenter
	 */
	public void setDef(String def);
	
	/**
	 * Positionne la valeur du message d'aide de la boite de dialogue
	 * @param help Le message d'aide
	 */
	public void setHelp(String help);
	
	/**
	 * Positionne la valeur de l'identifiant de la boite de dialogue.<br>
	 * En general, la plate-forme fourni cet identifiant. Ne le gerez pas vous-meme !
	 * @param id L'identifiant
	 */
	public void setId(int id);
	
	/**
	 * Positionne la valeur du message de la boite de dialogue
	 * @param message Le message a afficher
	 */
	public void setMessage(String message);
	
	/**
	 * Positionne l'indicateur de saisie multiligne
	 * @param multiLine La valeur a affecter a l'indicateur de saisie multiligne
	 * @see IDialogCom
	 */
	public void setMultiLine(int multiLine);
	
	/**
	 * Positionne le titre de la boite de dialogue
	 * @param title Le titre de la boite de dialogue
	 */
	public void setTitle(String title);
	
	/**
	 * Positionne le type de la boite de dialogue
	 * @param type Le type de la boite de dialogue
	 * @see IDialogCom
	 */
	public void setType(int type);

}