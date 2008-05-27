package fr.lip6.move.coloane.api.interfaces.observers;

import java.io.IOException;

import fr.lip6.move.coloane.api.interfaces.IModel;

public interface IAskForModelObserver {
	void update() throws IOException;
}
