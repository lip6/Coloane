package fr.lip6.move.coloane.interfaces.objects.dialog;

import java.util.List;

/**
 * Interface fournie par l'API pour Coloane
 * Cette interface propose les interractions possibles avec un element de dialogue
 * construit par l'API a l'initiative de la plate-forme
 */
public interface IDialog {

	/** Dialogue standart */
	int DLG_STANDARD = 1;
	/** Dialogue warning */
	int DLG_WARNING = 2;
	/** Dialogue erreur */
	int DLG_ERROR = 3;
	/** Dialogue interractif */
	int DLG_INTERACTIVE = 4;

	/** Aucun bouton */
	int DLG_NO_BUTTON = 1;
	/** Un bouton OK */
	int DLG_OK = 2;
	/** Un bouton OK et un bouton Cancel */
	int DLG_OK_CANCEL = 3;

	/** Saisie autorisee */
	int INPUT_AUTHORIZED = 1;
	/** Saisie interdite */
	int INPUT_FORBIDDEN = 2;
	/** Saisie autorisee et echappement possible */
	int INPUT_AND_ABORT_AUTHORIZED = 5;

	/** Affichage mono-ligne */
	int SINGLE_LINE = 1;
	/** Affichage multi-ligne avec selection simple */
	int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	/** Affichage multi-ligne avec selection multiple */
	int MULTI_LINE_WITH_MULTI_SELECTION = 5;

	/**
	 * Retourne le type des boutons affiches dans la boite dialogue<br>
	 * <ul>
	 * 	<li> 1 = pas de bouttons </li>
	 * 	<li> 2 = un boutton (OK) </li>
	 * 	<li> 3 = deux bouttons ( OK / CANCEL ) </li>
	 * </ul>
	 * @return Le type des boutons affiches dans la boite dialogue
	 */
	int getButtonType();

	/**
	 * Retourne le message par defaut a afficher dans la boite de dialogue
	 * @return La chaine de caracteres contenant la valeur par defaut
	 */
	String getDefaultValue();

	/**
	 * Indique le message d'aide associe avec la boite de dialogue
	 * @return La chaine de caracteres contenant le message d'aide
	 */
	String getHelp();

	/**
	 * Indique l'identifiant de la boite de dialogue.<br>
	 * Chaque boite de dialogue créée par la plate-forme possede un identifiant.
	 * @return L'identifiant de la boite de dialogue.
	 */
	int getId();

	/**
	 * Recupere le type de saisie<br>
	 * <ul>
	 * 	<li> 1 = saisie autoriser </li>
	 * 	<li> 2 = saisie non autoriser </li>
	 * 	<li> 3 = saisie autoriser avec possiblite d'annuler </li>
	 * </ul>
	 * @return le type de saisie
	 */
	int getInputType();

	/**
	 * Indique le message a afficher dans la boite de dialogue
	 * @return La chaine de caractere a afficher dans la boite de dialogue
	 */
	String getMessage();

	/**
	 * Recupere le type du champ de saisie<br>
	 * <ul>
	 * 	<li> 1 = affichage mono-ligne </li>
	 * 	<li> 2 = affichage multi-ligne avec selection simple </li>
	 * 	<li> 5 = affichage multi-ligne avec selection multiple </li>
	 * </ul>
	 * @return le type du champ de saisie
	 */
	int getLineType();

	/**
	 * Indique le titre de la boite de dialogue
	 * @return Le titre a afficher en tant que titre de la boite de dialogue
	 */
	String getTitle();

	/**
	 * Retourne le type de boite de dialogue<br>
	 * <ul>
	 * 	<li> 1 = Standard </li>
	 * 	<li> 2 = Avertissement </li>
	 * 	<li> 3 = Erreur </li>
	 * 	<li> 4 = Intercactive </li>
	 * </ul>
	 * @return Le type de boite de dialogue
	 */
	int getType();

	/**
	 * Recupere la liste des lignes.
	 * @return la liste des lignes.
	 */
	List<String> getLines();

}
