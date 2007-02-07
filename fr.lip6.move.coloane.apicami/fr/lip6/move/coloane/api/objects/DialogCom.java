package fr.lip6.move.coloane.api.objects;

import fr.lip6.move.coloane.interfaces.IDialogCom;

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
 * @see IDialog
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
	 * @see IDialog
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
	 * @see fr.lip6.move.coloane.interfaces.IDialogCom#getButtonType()
	 */
	public int getButtonType() {
		return buttonType;
	}

	/**
	 * Permet d'indiquer quel est le type de boutons a afficher dans la boite de dialogue
	 * @param buttonType Un indicateur
	 * @see IDialogCom
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

	/**
	 * TODO : Documenter
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

	/**
	 * Positionne la valeur du message d'aide de la boite de dialogue
	 * @param help Le message d'aide
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

	/**
	 * Positionne la valeur de l'identifiant de la boite de dialogue.<br>
	 * En general, la plate-forme fourni cet identifiant. Ne le gerez pas vous-meme !
	 * @param id L'identifiant
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

	/**
	 * Positionne la valeur du type de saisie de la boite de dialogue.<br>
	 * Les types possibles sont presentes dans IDialogCom
	 * @param inputType Le type de saisie associe a la boite de dialogue
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

	/**
	 * Positionne la valeur du message de la boite de dialogue
	 * @param message Le message a afficher
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

	/**
	 * Positionne l'indicateur de saisie multiligne
	 * @param multiLine La valeur a affecter a l'indicateur de saisie multiligne
	 * @see IDialogCom
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

	/**
	 * Positionne le titre de la boite de dialogue
	 * @param title Le titre de la boite de dialogue
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

	/**
	 * Positionne le type de la boite de dialogue
	 * @param type Le type de la boite de dialogue
	 * @see IDialogCom
	 */
	public void setType(int type) {
		this.type = type;
	}
	
}