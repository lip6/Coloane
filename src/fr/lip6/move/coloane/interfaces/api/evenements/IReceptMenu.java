package fr.lip6.move.coloane.interfaces.api.evenements;

import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.interfaces.objects.services.IApiService;

import java.util.List;
import java.util.Map;

/**
 * Cette interface définie l'objet menu à envoyer aux observateurs d'événements: réception de menus.
 */
public interface IReceptMenu {

	/**
	 * @return la liste des root menu disponibles
	 */
	List<ISubMenu> getMenus();

	/**
	 * @return une liste de service installés et disponibles sur la plate-forme
	 */
	List<IApiService> getServices();

	/**
	 * @return les mises a jour sur les menus
	 */
	Map<String, IUpdateMenu> getUpdateMenus();
}
