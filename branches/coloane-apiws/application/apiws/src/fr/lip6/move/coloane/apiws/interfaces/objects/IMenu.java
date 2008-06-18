package fr.lip6.move.coloane.apiws.interfaces.objects;

import java.util.ArrayList;

public interface IMenu {
	
	/**
	 * Recupere le parent du menu parent de la question ???
	 * @return le parent du menu parent de la question
	 */
	public IMenu getParent();
	
	/**
	 * Recupere le nom du menu
	 * @return le nom du menu
	 */
	public String getName();
	
	/**
	 * Recupere le type de la question ???
	 * @return le type de la question
	 */
	public int getTypeQuestion();
	
	/**
	 * Recupere le type de choix
	 * @return le type de choix
	 */
	public int getTypeChoix();
	
	/**
	 * Determine si la validation par defaut ou pas ???
	 * @return true, si valider, false si ne pas valider (par defaut)
	 */
	public boolean isValid();
	
	/**
	 * Determine si la question est interactive ou pas
	 * @return true, si la question est interactive, false sinon
	 */
	public boolean isDialogInteractive();
	
	/**
	 * Determine si la question est interruptible ou pas
	 * @return true, si la question est interruptible, false sinon
	 */
	public boolean isStopAutorized();
	
	/**
	 * recupere le domaine ???
	 * @return le doamine
	 */
	public String getDomaine();
	
	/**
	 * Determine si la question est disponible ou pas
	 * @return true, si la question est disponible, false sinon
	 */
	public boolean isActiv();
	
	/**
	 * Recupere les sous elements fils de l'element
	 * @return le tableau des sous elements fils de l'element
	 */
	public ArrayList<IMenu> getChildren();
	
	
}
