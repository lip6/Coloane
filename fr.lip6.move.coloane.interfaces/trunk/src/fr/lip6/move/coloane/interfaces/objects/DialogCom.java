package fr.lip6.move.coloane.interfaces.objects;


/**
 * Cette classe defini le contenu d'un boite de dialogue ainsi que sa structure.<br>
 * Divers elements composent une boite de dialogue :
 * <ul>
 * 	<li> Un identifiant unique</li>
 * 	<li> Un type </li>
 * 	<li> Une categorie de boutons a afficher</li>
 * 	<li> Un titre </li>
 * 	<li> Un message d'aide</li>
 * 	<li> Un message </li>
 * 	<li> Un type de saisie </li>
 * 	<li> Un indicateur de saisie multi-lignes</li>
 * </lu>
 *
 * Beaucoup d'indicateurs utilisent des constantes definies dans l'interface IDialog
 * @see fr.lip6.move.coloane.interfaces.IDialog
 */

public final class DialogCom implements IDialogCom {
	private int id;
	private int type;
	private int buttonType;
	private String title;
	private String help;
	private String message;
	private int inputType;
	private int multiLine;
	private String def = "";

	/**
	 * Constructeur
	 *
	 * @param id Identifiant de la boite de dialogue
	 * @param type Type de la boite de dialogue
	 * @param buttonType Type de boutons places dans la boite de dialogue
	 * @param title Titre de la boite de dialogue
	 * @param help Message d'aide
	 * @param message Message affiche dans la boie de dialogue
	 * @param inputType Type de saisie autorise
	 * @param multiLine Indicateur de saisie multi-lignes
	 * @param def
	 *
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
	public String getDef() {
		return def;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IDialogCom#setDef(String)
	 */
	public void setDef(String dialogDef) {
		this.def = dialogDef;
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
