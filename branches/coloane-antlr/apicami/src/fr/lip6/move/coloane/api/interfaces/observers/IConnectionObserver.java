package fr.lip6.move.coloane.api.interfaces.observers;

import java.util.Observable;
import java.util.Observer;

import fr.lip6.move.coloane.api.interfaces.IConnectionVersion;

public interface IConnectionObserver{
	void update(IConnectionVersion arg);
}
