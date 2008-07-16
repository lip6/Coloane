package fr.lip6.move.coloane.interfaces.objects.menu;

public interface IRootMenu {
	
	/**
	 * Recupere le nom du menu principal
     * @return le nom du menu principale
     */
    public String getName();
    
    /**
     * Recupere le menu principal
     * @return le menu principal
     */
    public ISubMenu getRoot();
    
    /**
     * Obtain the sub menu element identify by its name
     * @param name The name element SubMenu to search
     * @return The correct submenu element or null if doesn't exist
     */
    public ISubMenu obtainSubMenu(String name);
    
    /**
     * Obtain a question from his name identification
     * @param name Name of the question
     * @return Return the Question element or null if not exit
     */
    public IQuestion obtainQuestion(String name);
    
}