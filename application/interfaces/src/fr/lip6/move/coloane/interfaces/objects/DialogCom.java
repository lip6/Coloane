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

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#getButtonType()
	 */
	public int getButtonType() {
		return buttonType;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setButtonType(int)
	 */
	public void setButtonType(int dialogButtonType) {
		this.buttonType = dialogButtonType;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getDef()
	 */
	public String getDefault() {
		return defaut;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setDef(String)
	 */
	public void setDefault(String dialogDefaut) {
		this.defaut = dialogDefaut;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getHelp()
	 */
	public String getHelp() {
		return help;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setHelp(String)
	 */
	public void setHelp(String dialogHelp) {
		this.help = dialogHelp;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getId()
	 */
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setId(int)
	 */
	public void setId(int dialogId) {
		this.id = dialogId;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getInputType()
	 */
	public int getInputType() {
		return inputType;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setInputType(int)
	 */
	public void setInputType(int dialogInputType) {
		this.inputType = dialogInputType;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getMessage()
	 */
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setMessage(String)
	 */
	public void setMessage(String dialogMessage) {
		this.message = dialogMessage;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getMultiLine()
	 */
	public int getMultiLine() {
		return multiLine;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setMultiLine(int)
	 */
	public void setMultiLine(int dialogMultiLine) {
		this.multiLine = dialogMultiLine;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setTitle(String)
	 */
	public void setTitle(String dialogTitle) {
		this.title = dialogTitle;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getType()
	 */
	public int getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setType(int)
	 */
	public void setType(int dialogType) {
		this.type = dialogType;
	}
}
