package fr.lip6.move.coloane.api.interfaces.observers;

import fr.lip6.move.coloane.api.interfaces.IFKInfo;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;

public interface ISessionObserver {
	void update(IFKInfo fkInfo, IMenu menu,IUpdateItem update);
}
