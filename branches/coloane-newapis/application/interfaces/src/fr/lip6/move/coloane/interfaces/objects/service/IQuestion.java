package fr.lip6.move.coloane.interfaces.objects.service;

import java.util.List;

/**
 * ATTENTION CETTE INTERFACE N'EST PAS FIGEE
 */
public interface IQuestion {

    /**
     * Recupere le nom de la question
     * @return le nom de la question
     */
    String getName();

    /**
     * Modifie le nom de la question
     * @param name le nouveau nom de la question
     */
    void setName(String name);

    /**
     * Recupere la validite de la question
     * @return true si la question est valide, false sinon
     */
    boolean isValidation();

    /**
     * Modifie la validite de la question
     * @param validation la nouvelle validite de la question
     */
    void setValidation(boolean validation);

    /**
     * Recupere l'interactibilite de la question
     * @return true si la question est interactive, false sinon
     */
    boolean isInteraction();

    /**
     * Modifie l'interactibilite de la question
     * @param interaction la nouvelle interactive de la question
     */
    void setInteraction(boolean interaction);

    /**
     * Recupere l'interruptibilite de la question
     * @return true si la question est interruptible, false sinon
     */
    boolean isStop();

    /**
     * Modifie l'interruptibilite de la question
     * @param stop la nouvelle interruptibilite de la question
     */
    void setStop(boolean stop);

    /**
     * Recupere le domain de la question
     * @return le domain de la question
     */
    String getDomain();

    /**
     * Modifie le domain de la question
     * @param domain le nouveau domain de la question
     */
    void setDomain(String domain);

    /**
     * Recupere la visibilite de la question
     * @return la visibilite de la question
     */
    boolean isVisibility();

    /**
     * Modifie la visibilite de la question
     * @param visibility la nouvelle visibilite de la question
     */
    void setVisibility(boolean visibility);

    /**
     * Recupere la cardinalite de la question
     * @return la cardinalite de la question
     */
    int getCardinality();

    /**
     * Modifie la cardinalite de la question
     * @param cardinality la nouvelle cardinalite de la question
     */
    void setCardinality(int cardinality);

    /**
     * Recupere les messages d'aide sur la question
     * @return les messages d'aide sur la question
     */
    List<String> getHelps();

	/**
     * Ajoute un message d'aide
     * @param help le message d'aide a ajouter
     */
    void addHelp(String help);

    /**
     * Desactivation question because one submenu father was desactivate
     */
    void desactivateFromSubMenu();

    /**
     * Activation question because one submenu father was desactivate
     */
    void activateFromSubMenu();

}
