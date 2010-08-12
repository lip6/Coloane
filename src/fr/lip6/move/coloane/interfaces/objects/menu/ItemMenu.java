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

	/**
	 * Constructor
	 * @param name The name of the item
	 * @param visible The visible state of the item
	 * @param helpMessage The list of help messages associated with the item
	 */
	public ItemMenu(String name, boolean visible, String helpMessage) {
		this.name = name;
		this.helpMessage = helpMessage;
		this.visible = visible;
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
	@Override
	public final String toString() {
		return name;
	}
}
