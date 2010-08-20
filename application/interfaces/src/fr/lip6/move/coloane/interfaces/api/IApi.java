package fr.lip6.move.coloane.interfaces.api;

import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.util.List;

public interface IApi {

		/**
		 * @return The initial list of menus associated with the API
		 */
		List<IItemMenu> getInitialApiMenus();
		
		/**
		 * Add a specific observer to the API
		 * @param observer The observer object
		 * @param observerType The kind of observer
		 */
		void addObserver(IApiObserver observer, int observerType);
}
