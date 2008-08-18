package fr.lip6.move.coloane.apicami.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import java.util.List;
import java.util.logging.Logger;

/**
 * Définition d'une option
 */
public class OptionMenu extends Item implements IOptionMenu {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

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
		LOGGER.finest("Ajout de l'option " + name + " (visible = " + visibility + "; valeur = " + validate + ")");
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isValidated() {
		return this.validate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return IOptionMenu.TYPE_CHECKBOX;
	}
}
