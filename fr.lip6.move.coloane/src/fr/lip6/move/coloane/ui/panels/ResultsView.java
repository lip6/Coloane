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
import fr.lip6.move.coloane.results.ResultsList;

public class ResultsView extends ViewPart
	implements Observer {
	
	public static ResultsView instance;
	
	/**
	 * The ActionList displayed in this view 
	 */
	ActionsList oActionsList;
	
	/**
	 * The current result displayed
	 */
	private int currentResultDisplayed;
	
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
		
		currentResultDisplayed = 0;
		
		if (oActionsList != null)
			setLists();
		
		instance = this;
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
				text.setText(results.getResult(0).getDescription());
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
		setSelectionListeners();
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
