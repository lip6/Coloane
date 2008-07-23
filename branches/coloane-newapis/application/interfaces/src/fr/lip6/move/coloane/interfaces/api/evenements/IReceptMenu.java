package fr.lip6.move.coloane.interfaces.api.evenements;

import fr.lip6.move.coloane.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.List;

/**
 * Cette interface définie l'objet menu à envoyer aux observateurs d'événements: réception de menus.
 */
public interface IReceptMenu {

	/**
	 * Recupere les menus
	 * @return les menus
	 */
	List<IRootMenu> getMenus();

	/**
	 * Recupere les mises a jour sur les menus
	 * @return les mises a jour sur les menus
	 */
	List<IUpdateMenu> getUpdateMenus();
}
