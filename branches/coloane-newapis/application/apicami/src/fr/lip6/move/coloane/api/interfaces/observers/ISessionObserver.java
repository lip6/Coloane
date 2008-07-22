package fr.lip6.move.coloane.api.interfaces.observers;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.ISessionInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;

/**
 * cette interface represente l'observeur de la session.
 * @author kahoo && UU
 *
 */
public interface ISessionObserver {
	void update( ArrayList<IMenu> menuList,ArrayList<IUpdateItem> updatesList);

}
