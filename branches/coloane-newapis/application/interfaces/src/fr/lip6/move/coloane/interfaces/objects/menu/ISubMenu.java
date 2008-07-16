package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.ArrayList;

public interface ISubMenu extends IQuestion {
   
    
    /**
     * Recupere la liste des sous-menu
     * @return la liste des sous-menu
     */
    public ArrayList<ISubMenu> getSubMenus();
    
    /**
     * Recupere la liste des options
     * @return la liste des options
     */
    public ArrayList<IOption> getOption();
    
    /**
     * Recupere la liste des services simples
     * @return la liste des services simples
     */
    public ArrayList<IServiceSimple> getServices();
    
    /**
     * Recupere la liste des services sur des objets mutiple
     * @return la liste des services sur des objets mutiple
     */
    public ArrayList<IServiceWithObjects> getServicesWithObjects();

    /**
     * Recupere la liste des services sur un objet
     * @return la liste des services sur un objet
     */
    public ArrayList<IServiceWithOneObject> getServicesWithOneObject();
    
    /**
     * Recupere la liste des services sur du textes mutiple
     * @return la liste des services sur du textes mutiple
     */
    public ArrayList<IServiceWithTexts> getServiceWithTexts();
    
    /**
     * Recupere la liste des services sur un texte
     * @return la liste des services sur un texte
     */
    public ArrayList<IServiceWithOneText> getServiceWithOneText();

    /**
     * Ajout une question dans la bonne liste
     * @param s la nouvelle question a ajouter
     */
    public void addObject(IQuestion s);
    
    /**
     * Return the SubMenu element identify by its name
     * @param name The SubMenu name element
     * @return The correct SubMenu element or null
     */
    public ISubMenu obtainSubMenu(String name);
    
    /**
     * Return the first question element which have the identify name.
     * The return is a Question and can be a SubMenu, Service, ServiceWithObject or ServiceWtihText
     * @param name The Question name element
     * @return Return the correct Question or null if element doasn't exist
     */
    public IQuestion obtainQuestion(String name);
    
    /**
     * Return the first question element which have the identify name.
     * The return is a Question and can be a SubMenu, Service, ServiceWithObject or ServiceWtihText
     * @param name The Question name element
     * @return Return the correct Question or null if element doasn't exist
     */
    public int obtainSelection(ArrayList<IQuestion> qs,ArrayList<ISubMenu>ps,ISubMenu menu);
    
    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(boolean visibility);
}