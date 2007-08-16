package fr.lip6.move.coloane.results;

import java.util.Observable;
import java.util.Vector;

/**
 * La liste de resultats est observable.
 * Chaque ajout ou suppression provoque la mise a jour de la vue
 */

public class ResultsList extends Observable {
	private String actionName;
	private Vector<Result> resultsList;

	public ResultsList(String resultActionName) {
		this.actionName = resultActionName;
		resultsList = new Vector<Result>();
	}

	public final void add(Result result) {
		resultsList.add(result);
		setChanged();
		notifyObservers();
	}

	public final void removeAll() {
		resultsList.removeAllElements();
		setChanged();
		notifyObservers();
	}

	public final String getActionName() {
		return actionName;
	}

	public final Result getResult(int index) {
		return resultsList.get(index);
	}

	public final int getResultsNumber() {
		return resultsList.size();
	}
}
