package fr.lip6.move.coloane.results;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import org.eclipse.ui.IWorkbenchWindow;

import fr.lip6.move.coloane.ui.panels.ResultsView;

public class ActionsList extends Observable implements Observer {
	private Vector<ResultsList> list;
	
	public ActionsList() {
		list = new Vector<ResultsList>();
	}
	
	public void setResultsList(ResultsList resultsList) {
		aggregate(resultsList);
		list.add(resultsList);
	}
	
	public void addResultsList() {
		setChanged();
		notifyObservers();
	}
	
	public void display(String viewId, IWorkbenchWindow mwindow) {
		ResultsView resultView = (ResultsView)mwindow.getActivePage().findView(viewId);
		
		this.deleteObservers();
		// resultview devient un observer de Actionslist
		this.addObserver(resultView);
		
		setChanged();
		notifyObservers();
	}
	
	public ResultsList getResultsList(int index) {
		return list.get(index);
	}
	
	public ResultsList getResultsList(String resultName) {
		for(ResultsList r : list) {
			if (r.getActionName().equals(resultName)) {
				return r;
			}
		}
		
		return null;
	}
	
	public int getResultsListSize() {
		return list.size();
	}
	
	public void aggregate(ResultsList r) {
		
		// Parcours de la liste actuelle
		for (int i = 0; i < list.size(); i++) {

			// Si un item (servicename) de la liste actuelle est le meme que la nouvelle liste
			if (list.get(i).getActionName().equals(r.getActionName())) {
				for (int j = 0; j < list.get(i).getResultsNumber(); j++) {
					r.add(list.get(i).getResult(j));
				}
				list.remove(i);
			}
		}
	}
	
	public void removeAll() {
		list.removeAllElements();
	}

	public void update(Observable o, Object arg1) {
		this.setResultsList((ResultsList) o);
		this.addResultsList();
	}
}
