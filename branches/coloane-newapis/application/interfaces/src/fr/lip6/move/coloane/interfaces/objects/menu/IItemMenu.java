package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette interface définie un item (i.e. un service).
 */
public interface IItemMenu {

	/**
	 * Récupére le nom de l'item
	 * @return le nom de l'item
	 */
	String getName();

	/**
	 * Modifie le nom de l'item
	 * @param name le nouveau nom de l'item
	 */
	void setName(String name);

	/**
     * Recupere la visibilite de l'item
     * @return la visibilite de l'item
     */
	boolean isVisible();

    /**
     * Modifie la visibilite de l'item
     * @param visible la nouvelle visibilite de l'item
     */
	void setVisible(boolean visible);

    /**
     * Récupére l'identifiant du service associé à l'item
     * @return l'identifiant du service associé à l'item
     */
	String getAssociatedService();

    /**
     * Modifie l'identifiant du service associé à l'item
     * @param associatedService le nouveau identifiant du service associé à l'item
     */
	void setAssociatedService(String associatedService);

	/**
     * Recupere les messages d'aide sur l'item
     * @return les messages d'aide sur l'item
     */
	List<String> getHelps();

	/**
     * Ajoute un message d'aide
     * @param help le message d'aide a ajouter
     */
	void addHelp(String help);
}
