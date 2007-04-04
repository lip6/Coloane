package fr.lip6.move.coloane.results;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import org.eclipse.ui.IWorkbenchWindow;

import fr.lip6.move.coloane.ui.panels.ResultsView;

public class ActionsList extends Observable implements Observer {
	private Vector<ResultsList> resultsLists;
	
	public ActionsList() {
		resultsLists = new Vector<ResultsList>();
	}
	
	public void setResultsList(ResultsList resultsList) {
		aggregate(resultsList);
		resultsLists.add(resultsList);
	}
	
	public void addResultsList() {
		/*
		 * First we search a ResultList with the same name that
		 * the list we want to add and we remove it.
		 * We do this because if we call, for example, the
		 * Petri Net Syntax Checker several times, we don't want
		 * to see all the results but only the last one.
		 */
		
		setChanged();
		notifyObservers();
	}
	
	public void display(String viewId, IWorkbenchWindow mwindow) {
		ResultsView resultView = (ResultsView)mwindow.getActivePage().findView(viewId);
		
		this.deleteObservers();
		this.addObserver(resultView);
		setChanged();
		notifyObservers();
	}
	
	public ResultsList getResultsList(int index) {
		return resultsLists.get(index);
	}
	
	public ResultsList getResultsList(String resultName) {
		for(ResultsList r : resultsLists)
			if (r.getActionName().equals(resultName))
				return r;
		
		return null;
	}
	
	public int getResultsListSize() {
		return resultsLists.size();
	}
	
	public void aggregate(ResultsList r) {
		for (int i = 0; i < resultsLists.size(); i++) {
			if (resultsLists.get(i).getActionName().equals(r.getActionName())) {
				for (int j = 0; j < resultsLists.get(i).getResultsNumber(); j++)
					r.add(resultsLists.get(i).getResult(j));
				resultsLists.remove(i);
			}
		}
	}
	
	public void removeAll() {
		resultsLists.removeAllElements();
	}

	public void update(Observable o, Object arg1) {
		this.setResultsList((ResultsList) o);
		this.addResultsList();
	}
}
