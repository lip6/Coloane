package fr.lip6.move.coloane.communications.objects;

import fr.lip6.move.coloane.exceptions.WrongArgumentValueException;

/**
 * Fenetre envoyee par Framekit a l'API et donc a l'IHM
 * @author DQS equipe 2 (Styx)
 *
 */
public class WindowedDialogue  extends Dialogue {
	
	/**
	 *le type de dialogue (1 , 2 , 3, 4) le type 4 n'est pas gerer
	 */
	private int type;
	
	/**
	 * le nombre de bouton qui apparait sur la fenetre de dialogue (1,2,3)
	 */
	private int buttonNb;
	
	/**
	 * titre de la fenetre
	 */
	private String windowTitle;
	
	/**
	 * message d'aide associe a la fenetre
	 */
	private String helpMessasge;
	
	/**
	 * presence de mutli lignes
	 */
	private boolean mutliLines;
	
	/**
	 * plusieurs selections ou non si multi lignes
	 */
	private boolean manySelection;
		
	/**
	 * definit si l'utilisateur pourra saisir des donnees ou non
	 */
	private boolean allowedSeizure;
	
	/**
	 * definit si l'utilisateur pourra arreter
	 */
	private boolean allowedAbort;
	
	/**
	 * Constructeur
	 * @param uId uId identifiant unique de la fenetre
	 * @param msg msg message associe a la fenetre
	 * @param typ type de fenetre
	 * @param butNb nomtre de bouton(s)
	 * @param windowTitl titre de la fenetre
	 * @param helpMsg message d'aide associe a la fenetre
	 * @param xLines mutli lignes ou pas
	 * @param manySelect selection multiple autorisee
	 * @param allowedSz saisie autorisee
	 * @param allowedAbrt arret autorise
	 * @throws WrongArgumentValueException si typ n'est pas egal a (1, 2, 3 ou 4),
	 *  ou si le nombre de boutons n'est pas egal a (1, 2 ou 3)
	 */
	public WindowedDialogue(int uId, String msg, int typ, 
			int butNb, String windowTitl, String helpMsg, boolean xLines, boolean manySelect, 
			boolean allowedSz, boolean allowedAbrt) throws WrongArgumentValueException {		
		super(uId, msg);
		if (typ < 1 || typ > 4) {
			throw new WrongArgumentValueException("le type n'est pas correct (valeur 1, 2, 3 ou 4)");
		} else if (butNb < 1 || butNb > 3) {
			throw new WrongArgumentValueException("le nombre de boutons n'est pas correct (valeur 1, 2 ou 3)");
		}
		this.type = typ;
		this.buttonNb = butNb;
		this.windowTitle = windowTitl;
		this.helpMessasge = helpMsg;
		this.mutliLines = xLines;
		this.manySelection = manySelect;
		this.allowedSeizure = allowedSz;
		this.allowedAbort = allowedAbrt;
	}
	
	/**
	 * accesseur
	 * @return le type de la fenetre 
	 * 1:Standard
	 * 2:Warning
	 * 3:Error
	 * 4:Interactif (Non Gerer)
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * accesseur
	 * @return le nombre de bouton de la fenetre
	 * 1:Pas de boutton
	 * 2:Un boutton OK
	 * 3:Deux bouton (OK et Cancel)
	 */
	public int getButtonNb() {
		return this.buttonNb;
	}
	
	/**
	 * accesseur
	 * @return le titre de la fenetre
	 */
	public String getWindowTitle() {
		return this.windowTitle;
	}
	
	/**
	 * accesseur
	 * @return le message d'aide associe a la fenetre
	 */
	public String getHelpMessage() {
		return this.helpMessasge;
	}
	
	/**
	 * accesseur
	 * @return s'il y a des attributs multi lignes
	 */
	public boolean isMultiLines() {
		return this.mutliLines;
	}
	
	/**
	 * accesseur
	 * @return si la selection mutliple est autorisee dans le cas d'attributs multi lignes
	 */
	public boolean isManySelection() {
		return this.manySelection;
	}
	
	/**
	 * accesseur
	 * @return si la saisie est autorisee
	 */
	public boolean isAllowedSeizure() {
		return this.allowedSeizure;
	}
	
	/**
	 * accesseur
	 * @return si l'arret est autorise
	 */
	public boolean isAllowedAbort() {
		return this.allowedAbort;
	}

}
