package fr.lip6.move.coloane.apicami.objects;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;
import java.util.List;

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
 * </ul>
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

	/**la liste des lignes*/
	private List<String> lines;

	/**
	 * constructeur
	 * @param id lidentifiant de la boite de dialgue
	 * @param type le type du dialogue
	 * @param buttonType le type du boutton dans la boite de dialogue
	 * @param title le titre de la boite de dialogue
	 * @param help le message d'aide associe avec la boite de dialogue
	 * @param message le message a afficher dans la boite de dialogue
	 * @param inputType  le type de saisie
	 * @param multiLine le type du champ de saisie
	 * @param defaut le message par defaut a afficher dans la boite de dialogue
	 */
	public Dialog(int id, int type, int buttonType, String title, String help, String message, int inputType, int multiLine, String defaut) {
		this.id = id;
		this.type = type;
		this.buttonType = buttonType;
		this.title = title;
		this.help = help;
		this.message = message;
		this.inputType = inputType;
		this.multiLine = multiLine;
		this.defaut = defaut;
		this.lines = new ArrayList<String>();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getButtonType() {
		return this.buttonType;
	}
	/**
	 * {@inheritDoc}
	 */
	public String getDefaultValue() {
		return this.defaut;
	}
	/**
	 * {@inheritDoc}
	 */
	public String getHelp() {
		return this.help;
	}
	/**
	 * {@inheritDoc}
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * {@inheritDoc}
	 */
	public int getInputType() {
		return this.inputType;
	}
	/**
	 * {@inheritDoc}
	 */
	public int getLineType() {
		return this.multiLine;
	}
	/**
	 * {@inheritDoc}
	 */
	public List<String> getLines() {
		return this.lines;
	}
	/**
	 * {@inheritDoc}
	 */
	public String getMessage() {
		return this.message;
	}
	/**
	 * {@inheritDoc}
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * {@inheritDoc}
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * Ajoute une ligne à celles déjà existantes
	 * @param line La ligne à ajouter
	 */
	public void addLine(String line) {
		this.lines.add(line);
	}
}
