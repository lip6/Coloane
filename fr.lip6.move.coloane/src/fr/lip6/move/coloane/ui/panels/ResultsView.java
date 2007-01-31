package fr.lip6.move.coloane.ui.panels;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;

import fr.lip6.move.coloane.results.ActionsList;
import fr.lip6.move.coloane.results.Result;
import fr.lip6.move.coloane.results.ResultsList;

public class ResultsView extends ViewPart
	implements Observer {
	/**
	 * The ActionList displayed in this view 
	 */
	ActionsList oActionsList;
	
	/**
	 * The current action displayed
	 */
	int currentActiondisplayed;
	
	/**
	 * The current result displayed
	 */
	int currentResultDisplayed;
	
	/**
	 * The widget which will display the list of actions.
	 */
	private List actionsList;
	
	/**
	 * The widget which will display the list of results
	 * for an action.
	 */
	private List resultsList;
	
	/**
	 * The widget which will display the 
	 */
	private StyledText text;
	
	/**
	 * 
	 * 
	 * @param oActionsList The ActionList which will be observed.
	 */
	public ResultsView() {
		super();		
	}
	
	@Override
	public void createPartControl(Composite parent) {
		/* This view will be divided in three parts :
		 * - in first we will have a list off actions' names
		 *   (Syntax check, ...)
		 * - in second we will have a list of error for a given action
		 * - in third we will have the text correponding to the
		 *   selected error
		 */
		actionsList = new List(parent, SWT.SINGLE | SWT.BORDER);
		resultsList = new List(parent, SWT.SINGLE | SWT.BORDER);
		
		text =
			new StyledText(parent, SWT.READ_ONLY | SWT.BORDER);
		text.setJustify(true);
		text.setAlignment(SWT.CENTER);
		
		oActionsList = new ActionsList();
		ResultsList r = new ResultsList("Action 1");
		r.add(new Result("Result 1-1", "Description 1-1"));
		r.add(new Result("Result 1-2", "Description 1-2"));
		r.add(new Result("Result 1-3", "Description 1-3"));
		r.add(new Result("Result 1-4", "Description 1-4"));
		oActionsList.addResultsList(r);
		
		r = new ResultsList("Action 2");
		r.add(new Result("Result 2-1", "Description 2-1"));
		r.add(new Result("Result 2-2", "Description 2-2"));
		r.add(new Result("Result 2-3", "Description 2-3"));
		
		oActionsList.addResultsList(r);
		
		r = new ResultsList("Action 3");
		r.add(new Result("Result 3-1", "Description 3-1"));
		r.add(new Result("Result 3-2", "Description 3-2"));
		
		oActionsList.addResultsList(r);
		
		currentActiondisplayed = 0;
		currentResultDisplayed = 0;
		
		setLists();
		setSelectionListeners();
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
	
	private void setActionsListSelectionListener() {
		actionsList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				int selectedIndex = actionsList.getSelectionIndex();
				ResultsList results = oActionsList.getResultsList(selectedIndex);
				
				currentResultDisplayed = selectedIndex;
				resultsList.removeAll();
				
				for (int i = 0; i < results.getResultsNumber(); i++)
					resultsList.add(results.getResult(i).getName());
				
				setResultsListSelectionListener();
				resultsList.select(0);
			}
		});
	}
	
	private void setLists() {
		actionsList.removeAll();
		resultsList.removeAll();
		
		/*
		 * We build the first list with the actions' names.
		 */
		for (int i = 0; i < oActionsList.getResultsListSize(); i++)
			actionsList.add(oActionsList.getResultsList(i).getActionName());
		
		/*
		 * We build the second list with the first action's results.
		 */
		for (int i = 0; i < oActionsList.getResultsList(0).getResultsNumber(); i++)
			resultsList.add(oActionsList.getResultsList(0).getResult(i).getName());
		
		text.setText(oActionsList.getResultsList(0).getResult(0).getDescription());
	}
	
	private void setResultsListSelectionListener() {
		resultsList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				text.setText(oActionsList.getResultsList(currentResultDisplayed).
						getResult(resultsList.getSelectionIndex()).getDescription());
			}
		});
	}

	private void setSelectionListeners() {
		setActionsListSelectionListener();
		setResultsListSelectionListener();
	}
	
	public void setActionsList(ActionsList oActionsList) {
		this.oActionsList = oActionsList;
	}
	
	
	/**
	 * The method called when an observed ActionsList is
	 * modified.
	 */
	public void update(Observable o, Object arg1) {
		/*
		 * When an update query is received, we erase the list and
		 * then we rebuild it from scratch.
		 */
		oActionsList = (ActionsList)o;
		setLists();
	}
}
