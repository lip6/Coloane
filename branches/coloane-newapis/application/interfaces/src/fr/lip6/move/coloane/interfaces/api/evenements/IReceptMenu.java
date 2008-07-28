package fr.lip6.move.coloane.interfaces.api.evenements;

import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.util.List;

/**
 * Cette interface définie l'objet menu à envoyer aux observateurs d'événements: réception de menus.
 */
public interface IReceptMenu {

	/**
	 * Recupere les menus
	 * @return les menus
	 */
	List<ISubMenu> getMenus();

	/**
	 * Recupere les services installés sur la plate-formes
	 * @return une liste de service
	 */
	List<IService> getServices();

	/**
	 * Recupere les mises a jour sur les menus
	 * @return les mises a jour sur les menus
	 */
	List<IUpdateMenu> getUpdateMenus();
}
