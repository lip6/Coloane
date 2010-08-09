package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Define an option.<br>
 * An option provide the same features as a <b>checkbox</b> in a form.
 * 
 * @author Jean-Baptiste Voron
 */
public interface IOptionMenu extends IItemMenu {
	/** Checkbox type for 0:N selection */
	int TYPE_CHECKBOX = 0;

	/** Radio type for 0:1 selection */
	int TYPE_RADIO = 1;

	/**
     * @return <code>true</code> if the option is checked. <code>false</code> otherwise
     */
    boolean isChecked();

    /**
     * @return the kind of the option
     */
    int getType();
}
