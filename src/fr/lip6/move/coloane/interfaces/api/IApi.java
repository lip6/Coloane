package fr.lip6.move.coloane.interfaces.api;

import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.List;

public interface IApi extends IObservable {

		public final int MENU_OBSERVER = 0;
		
		/**
		 * @return The list of menus associated with the API
		 */
		List<ISubMenu> getApiMenus();
}
