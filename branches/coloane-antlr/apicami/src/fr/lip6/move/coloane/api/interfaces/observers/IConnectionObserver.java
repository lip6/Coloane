package fr.lip6.move.coloane.api.interfaces.observers;

import java.util.Observable;
import java.util.Observer;

import fr.lip6.move.coloane.api.interfaces.IFkVersion;

public interface IConnectionObserver{
	void update(IFkVersion arg);
}
