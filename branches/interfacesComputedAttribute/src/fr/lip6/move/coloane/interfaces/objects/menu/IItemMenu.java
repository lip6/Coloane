package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Le composant de base d'un menu.<br><b>Tout est item !</b>
 */
public interface IItemMenu {

	/**
	 * @return le nom de l'item
	 */
	String getName();

	/**
	 * @return chemin d'accès jusqu'à l'item
	 */
	String getPath();

	/**
     * @return la visibilite de l'item
     */
	boolean isVisible();

	/**
     * @return les messages d'aide sur l'item
     */
	String getHelps();
}
