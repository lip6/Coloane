package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Define an option.<br>
 * An option provide the same features as a <b>checkbox</b> in a form.
 * 
 * @author Jean-Baptiste Voron
 */
public class OptionMenu extends ItemMenu implements IOptionMenu {
	/** Current state */
	private boolean checked;

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visible The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param checked The state of the option
	 * @param path The service path
	 */
	public OptionMenu(String name, boolean visible, String helpMessage, boolean checked, String path) {
		super(name, visible, helpMessage, path);
		this.checked = checked;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isChecked() {
		return this.checked;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return IOptionMenu.TYPE_CHECKBOX;
	}
}
