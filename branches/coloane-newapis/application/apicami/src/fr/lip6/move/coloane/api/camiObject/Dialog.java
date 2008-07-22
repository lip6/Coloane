package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;





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

public final class Dialog implements IDialog {

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
	private String defaut = ""; 

	/**
	 * Constructeur
	 *
	 * @param id Identifiant de la boite de dialogue
	 * @see fr.lip6.move.coloane.interfaces.IDialog
	 */
	public Dialog(int dialogId) {
		this.id = dialogId;
	}
   
	public Dialog(int id,int type,int buttonType, String title, String help,String message,
              int inputType, int multiLine, String defaut){
		this.id=id;
		this.type=type;
		this.buttonType=buttonType;
		this.title=title;
		this.help=help;
		this.message=message;
		this.inputType=inputType;
		this.multiLine=multiLine;
		this.defaut=defaut;
	}
	
	public int getButtonType() {
		return buttonType;
	}

	
	
	public String getDefault() {
		return defaut;
	}


	
	public String getHelp() {
		return help;
	}

	
	public int getId() {
		return id;
	}

	

	
	public int getInputType() {
		return inputType;
	}

	
	
	public String getMessage() {
		return message;
	}

	
	public int getMultiLine() {
		return multiLine;
	}

	

	public String getTitle() {
		return title;
	}

	
	
	public int getType() {
		return type;
	}

	

	
}