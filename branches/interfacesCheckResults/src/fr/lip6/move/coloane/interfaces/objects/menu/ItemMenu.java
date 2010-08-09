package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Define a menu item.<br>
 * An item describes something (almost every elements) in a menu
 * 
 * @author Jean-Baptiste Voron
 */
public class ItemMenu implements IItemMenu {
	/** The name of the item */
	private String name;

	/** Help messages associated with the item */
	private String helpMessage;

	/** The visibility state of the item */
	private boolean visible;

	/** Path */
	private String path;

	/**
	 * Constructor
	 * @param name The name of the item
	 * @param visible The visible state of the item
	 * @param helpMessage The list of help messages associated with the item
	 * @param path A path
	 */
	public ItemMenu(String name, boolean visible, String helpMessage, String path) {
		this.name = name;
		this.helpMessage = helpMessage;
		this.visible = visible;
		this.path = path;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getHelps() {
		return this.helpMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isVisible() {
		return this.visible;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getPath() {
		return this.path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return name;
	}
}
