package fr.lip6.move.coloane.interfaces.objects;


/**
 * Cette classe defini le contenu d'un boite de dialogue ainsi que sa structure.<br>
 * Divers elements composent une boite de dialogue :
 * <ul>
 * 	<li>Un identifiant unique</li>
 * 	<li>Un type </li>
 * 	<li>Une categorie de boutons a afficher</li>
 * 	<li>Un titre </li>
 * 	<li>Un message d'aide</li>
 * 	<li>Un message </li>
 * 	<li>Un type de saisie </li>
 * 	<li>Un indicateur de saisie multi-lignes</li>
 *  <li>Une valeur par defaut</li>
 * </lu>
 *
 * Beaucoup d'indicateurs utilisent des constantes definies dans l'interface IDialog
 * @see fr.lip6.move.coloane.interfaces.IDialog
 */

public final class DialogCom implements IDialogCom {

	/** Identifiant de la boite de dialogue */
	private int id;

	/** Type */
	private int type;

	/** Ensemble de boutons a afficher */
	private int buttonType;

	/** Titre de la boite de dialogue */
	private String title;

	/** Message d'aide attache a la boite de dialogue */
	private String help;

	/** Message preconstruit */
	private String message;

	/** Type de saisie */
	private int inputType;

	/** Indicateur de saisie multiligne */
	private int multiLine;

	/** Valeur par defaut */
	private String defaut = ""; //$NON-NLS-1$

	/**
	 * Constructeur
	 *
	 * @param id Identifiant de la boite de dialogue
	 * @see fr.lip6.move.coloane.interfaces.IDialog
	 */
	public DialogCom(int dialogId) {
		this.id = dialogId;
	}

	/** {@inheritDoc} */
	public int getButtonType() {
		return buttonType;
	}

	/** {@inheritDoc} */
	public void setButtonType(int dialogButtonType) {
		this.buttonType = dialogButtonType;
	}

	/** {@inheritDoc} */
	public String getDefault() {
		return defaut;
	}

	/** {@inheritDoc} */
	public void setDefault(String dialogDefaut) {
		this.defaut = dialogDefaut;
	}

	/** {@inheritDoc} */
	public String getHelp() {
		return help;
	}

	/** {@inheritDoc} */
	public void setHelp(String dialogHelp) {
		this.help = dialogHelp;
	}

	/** {@inheritDoc} */
	public int getId() {
		return id;
	}

	/** {@inheritDoc} */
	public void setId(int dialogId) {
		this.id = dialogId;
	}

	/** {@inheritDoc} */
	public int getInputType() {
		return inputType;
	}

	/** {@inheritDoc} */
	public void setInputType(int dialogInputType) {
		this.inputType = dialogInputType;
	}

	/** {@inheritDoc} */
	public String getMessage() {
		return message;
	}

	/** {@inheritDoc} */
	public void setMessage(String dialogMessage) {
		this.message = dialogMessage;
	}

	/** {@inheritDoc} */
	public int getMultiLine() {
		return multiLine;
	}

	/** {@inheritDoc} */
	public void setMultiLine(int dialogMultiLine) {
		this.multiLine = dialogMultiLine;
	}

	/** {@inheritDoc} */
	public String getTitle() {
		return title;
	}

	/** {@inheritDoc} */
	public void setTitle(String dialogTitle) {
		this.title = dialogTitle;
	}

	/** {@inheritDoc} */
	public int getType() {
		return type;
	}

	/** {@inheritDoc} */
	public void setType(int dialogType) {
		this.type = dialogType;
	}
}
