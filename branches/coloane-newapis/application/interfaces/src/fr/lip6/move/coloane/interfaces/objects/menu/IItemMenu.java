package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Le composant de base d'un menu.<br><b>Tout est item !</b>
 */
public interface IItemMenu {

	/**
	 * @return le nom de l'item
	 */
	String getName();

	/**
     * @return la visibilite de l'item
     */
	boolean isVisible();

	/**
     * @return les messages d'aide sur l'item
     */
	String getHelps();
}
