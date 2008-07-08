package fr.lip6.move.coloane.apiws.interfaces.objects.menu;

import java.util.ArrayList;

public interface IItemMenu {
	/**
	 * Recupere le nom de la question
	 * @return le nom de la question
	 */
	public String getName();
	
	/**
	 * Determine si la question est visible ou pas
	 * @return true, si la question est visible, false sinon
	 */
	public boolean isVisibility();
	
	/**
	 * Recupere l'aide de la question
	 * @return l'aide de la question
	 */
	public ArrayList<String> getHelps();
	
	/**
	 * 
	 * @return
	 */
	public boolean isValidation();
}
