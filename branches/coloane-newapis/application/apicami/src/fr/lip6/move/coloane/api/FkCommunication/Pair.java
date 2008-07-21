package fr.lip6.move.coloane.api.FkCommunication;

import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

public class Pair<T1, T2> {
	public ISpeaker speaker;
	public IListener listener;

	public IListener getListener() {
		return this.listener;
	}

	public ISpeaker getSpeaker() {
		return this.speaker;
	}
}
