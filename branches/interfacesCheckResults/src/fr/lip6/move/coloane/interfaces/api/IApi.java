package fr.lip6.move.coloane.interfaces.api;

import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.List;
import java.util.Observer;

public interface IApi {

		public final int MENU_OBSERVER = 0;
		
		/**
		 * Set an observer to be able to be warned when something happens to the API
		 * @param observer The observer
		 * @param observerType The kind event to observe
		 */
		void setObserver(Observer observer, int observerType);
		
		List<ISubMenu> initApi();

}
