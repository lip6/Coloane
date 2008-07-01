package fr.lip6.move.coloane.apiws.interfaces.objects.menu;

import java.util.ArrayList;

public interface IQuestion {
	
	/**
	 * Recupere le nom de la question
	 * @return le nom de la question
	 */
	public String getName();
	
	/**
	 * Determine si la question est validee ou pas
	 * @return true, si la question est validee, false sinon
	 */
	public boolean isValidation();
	
	/**
	 * Detremine si la question est interactive ou pas
	 * @return true, si la question est interactive, false sinon
	 */
	public boolean isInteraction();
	
	/**
	 * 
	 * @return
	 */
	public boolean isStop();
	
	/**
	 * Recupere le domaine
	 * @return le domaine
	 */
	public String getDomain();
	
	/**
	 * Determine si la question est visible ou pas
	 * @return true, si la question est visible, false sinon
	 */
	public boolean isVisibility();
	
	/**
	 * Recupere la cardinalite de la question 
	 * @return la cardinalite de la question 
	 */
	public int getCardinality();
	
	/**
	 * Recupere l'aide de la question
	 * @return l'aide de la question
	 */
	public ArrayList<String> getHelps();
}
