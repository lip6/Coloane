package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Define a menu item.<br>
 * An item describes something (almost every elements) in a menu
 * 
 * @author Jean-Baptiste Voron
 */
public interface IItemMenu {

	/**
	 * @return The item name
	 */
	String getName();

	/**
     * @return The visible state of the item
     */
	boolean isVisible();

	/**
     * @return An help message (a tip) associated with the item
     */
	String getHelps();
}
