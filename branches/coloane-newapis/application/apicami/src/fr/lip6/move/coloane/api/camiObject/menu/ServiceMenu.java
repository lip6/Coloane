package fr.lip6.move.coloane.api.camiObject.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;

import java.util.List;
import java.util.logging.Logger;

/**
 * Définition d'un item de menu proposant un service
 */
public class ServiceMenu extends Item implements IServiceMenu {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Service associé à l'item de menu */
	private String associatedService;

	/**
	 * Constructeur
	 * @param name Le nom de l'item de menu
	 * @param visibility La visibilité de l'item de menu
	 * @param help La liste des messages d'aide associés au menu
	 * @param associatedService Le service associé à l'imte de menu
	 */
	public ServiceMenu(String name, boolean visibility, List<String> help, String associatedService) {
		super(name, visibility, help);
		this.associatedService = associatedService;
		LOGGER.finest("Ajout du menu de service " + name + " (visible = " + visibility + "; valeur = " + associatedService + ")");
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getAssociatedService() {
		return this.associatedService;
	}
}
