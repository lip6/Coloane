package fr.lip6.move.coloane.interfaces.api.observers;

import java.util.Observable;
import java.util.Observer;

import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;



public interface IConnectionObserver{
	void update(IConnectionInfo arg);
}
