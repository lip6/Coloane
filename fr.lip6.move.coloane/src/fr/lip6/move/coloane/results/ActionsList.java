package fr.lip6.move.coloane.results;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.ui.panels.ResultsView;

public class ActionsList extends Observable
	implements Observer {
	private Vector<ResultsList> resultsLists;
	
	public ActionsList() {
		resultsLists = new Vector<ResultsList>();
	}
	
	public void addResultsList(ResultsList resultList) {
		/*
		 * First we search a ResultList with the same name that
		 * the list we want to add and we remove it.
		 * We do this because if we call, for example, the
		 * Petri Net Syntax Checker several times, we don't want
		 * to see all the results but only the last one.
		 */
		remove(resultList.getActionName());

		resultsLists.add(0, resultList);
		setChanged();
		notifyObservers();
	}
	
	public void display(String viewId) {
		IWorkbenchWindow window =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		try {
			window.getActivePage().showView(viewId);
			ResultsView resultView = (ResultsView)window.getActivePage().findView(viewId);
			
			this.deleteObservers();
			this.addObserver(resultView);
			setChanged();
			notifyObservers();
		} catch (Exception e) {
			ErrorDialog.openError(PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().getShell(),
					"Error during view initialization",
					"The view with id \"" + viewId + "\" cannot be opened", null);
		}
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
	
	public void remove(String actionName) {
		for (int i = 0; i < resultsLists.size(); i++)
			if (resultsLists.get(i).getActionName().equals(actionName))
				resultsLists.remove(i);
		
		setChanged();
		notifyObservers();
	}
	
	public void removeAll() {
		resultsLists.removeAllElements();
	}

	public void update(Observable o, Object arg1) {
		this.addResultsList((ResultsList)o);
	}
}
