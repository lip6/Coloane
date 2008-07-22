package fr.lip6.move.coloane.interfaces.objects.service;

import java.util.ArrayList;

public interface IQuestion {

    /**
     * Recupere le nom de la question
     * @return le nom de la question
     */
    public String getName();

    /**
     * Modifie le nom de la question
     * @param name le nouveau nom de la question
     */
    public void setName(String name);

    /**
     * Recupere la validite de la question
     * @return true si la question est valide, false sinon
     */
    public boolean isValidation();

    /**
     * Modifie la validite de la question
     * @param validation la nouvelle validite de la question
     */
    public void setValidation(boolean validation);

    /**
     * Recupere l'interactibilite de la question
     * @return true si la question est interactive, false sinon
     */
    public boolean isInteraction();

    /**
     * Modifie l'interactibilite de la question
     * @param interaction la nouvelle interactive de la question
     */
    public void setInteraction(boolean interaction);

    /**
     * Recupere l'interruptibilite de la question
     * @return true si la question est interruptible, false sinon
     */
    public boolean isStop();

    /**
     * Modifie l'interruptibilite de la question
     * @param stop la nouvelle interruptibilite de la question
     */
    public void setStop(boolean stop);

    /**
     * Recupere le domain de la question
     * @return le domain de la question
     */
    public String getDomain();

    /**
     * Modifie le domain de la question
     * @param domain le nouveau domain de la question
     */
    public void setDomain(String domain);

    /**
     * Recupere la visibilite de la question
     * @return la visibilite de la question
     */
    public boolean isVisibility();

    /**
     * Modifie la visibilite de la question
     * @param visibility la nouvelle visibilite de la question
     */
    public void setVisibility(boolean visibility);

    /**
     * Recupere la cardinalite de la question
     * @return la cardinalite de la question
     */
    public int getCardinality();

    /**
     * Modifie la cardinalite de la question
     * @param cardinality la nouvelle cardinalite de la question
     */
    public void setCardinality(int cardinality);
    
    /**
     * Recupere les messages d'aide sur la question
     * @return les messages d'aide sur la question
     */
    public ArrayList<String> getHelps();
	
	/**
     * Ajoute un message d'aide
     * @param help le message d'aide a ajouter
     */
    public void addHelp(String help);
    
    /**
     * Desactivation question because one submenu father was desactivate
     */
    public void desactivateFromSubMenu();
    
    /**
     * Activation question because one submenu father was desactivate
     */
    public void activateFromSubMenu();
    
}