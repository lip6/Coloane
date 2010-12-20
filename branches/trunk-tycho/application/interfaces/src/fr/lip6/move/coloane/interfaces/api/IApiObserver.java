package fr.lip6.move.coloane.interfaces.api;

/**
 * Define methods that have to be implemented by API Observers.<br>
 * Cannot rely on {@link Observer} interface since it requires {@link Api} to extend {@link Observable}
 * 
 * @author Jean-Baptiste Voron
 */
public interface IApiObserver {

	int MENU_OBSERVER = 0;
	int MESSAGE_OBSERVER = 1;

	
	/** 
	 * Something has been updated on the API
	 * @param api The API that has been changed
	 * @param newValue The new value to take into account
	 */
	void update(IApi api, Object newValue);
}
