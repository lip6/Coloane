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

public class DialogCom implements IDialogCom {
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
	 * @see fr.lip6.move.coloane.interfaces.IDialog
	 */
	public DialogCom(int id, int type, int buttonType, String title, String help, String message, int inputType, int multiLine, String def) {
		this.id = id;
		this.type = type;
		this.buttonType = buttonType;
		this.title = title;
		this.help = help;
		this.message = message;
		this.inputType = inputType;
		this.multiLine = multiLine;
		this.def = def;
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
	public void setButtonType(int buttonType) {
		this.buttonType = buttonType;
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
	public void setDef(String def) {
		this.def = def;
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
	public void setHelp(String help) {
		this.help = help;
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
	public void setId(int id) {
		this.id = id;
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
	public void setInputType(int inputType) {
		this.inputType = inputType;
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
	public void setMessage(String message) {
		this.message = message;
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
	public void setMultiLine(int multiLine) {
		this.multiLine = multiLine;
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
	public void setTitle(String title) {
		this.title = title;
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
	public void setType(int type) {
		this.type = type;
	}
	
}