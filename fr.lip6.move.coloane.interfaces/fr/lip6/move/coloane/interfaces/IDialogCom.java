package fr.lip6.move.coloane.interfaces;

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

}