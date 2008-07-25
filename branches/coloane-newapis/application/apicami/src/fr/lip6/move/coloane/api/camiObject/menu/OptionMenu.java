package fr.lip6.move.coloane.api.camiObject.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import java.util.List;

/**
 * Définition d'une option
 */
public class OptionMenu extends Item implements IOptionMenu {
	/** Etat de l'option */
	private boolean validate;

	/**
	 * Constructeur
	 * @param name Le nom de l'item de menu
	 * @param visibility La visibilité de l'item de menu
	 * @param help Les messages d'aide associés à l'item de menu
	 * @param validate L'état de l'option
	 */
	public OptionMenu(String name, boolean visibility, List<String> help, boolean validate) {
		super(name, visibility, help);
		this.validate = validate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isValidated() {
		return this.validate;
	}
}
