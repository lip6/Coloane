package fr.lip6.move.coloane.ui.panels;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.results.ActionsList;
import fr.lip6.move.coloane.results.Result;
import fr.lip6.move.coloane.results.ResultsList;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;

/**
 * Gestion de la vue des resultats
 */

public class ResultsView extends ViewPart implements Observer {

	private static ResultsView instance;

	/** The ActionList displayed in this view. */
	private ActionsList actionsList;

	/** The current action displayed. */
	private int currentActionDisplayed;

	/** The widget which will display the list of actions. */
	private List actionsWidget;

	/** The widget which will display the list of results for an action. */
	private List resultsList;

	/** The widget which will display the */
	private StyledText text;

	/** Constructor for ResultsView */
	public ResultsView() {
		super();
	}

	@Override
	public final void createPartControl(Composite parent) {
		/* This view will be divided in three parts :
		 * - in first we will have a list of actions' names (Syntax check, ...)
		 * - in second we will have a list of error for a given action
		 * - in third we will have the text correponding to the selected error
		 */
		actionsWidget = new List(parent, SWT.SINGLE | SWT.BORDER);
		resultsList = new List(parent, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);

		text = new StyledText(parent, SWT.READ_ONLY | SWT.BORDER | SWT.WRAP);
		text.setJustify(false);
		text.setAlignment(SWT.CENTER);

		currentActionDisplayed = 0;

		if (actionsList != null) {
			setLists();
		}

		instance = this;
	}

	@Override
	public final void setFocus() {
		return;
	}

	/**
	 * Adds a listener on the actions' list.
	 */
	private void setActionsListSelectionListener() {
		actionsWidget.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				currentActionDisplayed = actionsWidget.getSelectionIndex();
				ResultsList results = actionsList.getResultsList(currentActionDisplayed);

				/* We zero the second list to fill it with the results of the action currently selected */
				resultsList.removeAll();

				if (results.getResultsNumber() == 0) {
					return;
				}

				for (int i = 0; i < results.getResultsNumber(); i++) {
					resultsList.add(results.getResult(i).getName());
				}

				/* We add the listner for the list we have created. */
				setResultsListSelectionListener();

				resultsList.select(0);
				text.setText(results.getResult(0).getDescription());
			}
		});
	}

	private void setLists() {
		actionsWidget.removeAll();
		resultsList.removeAll();

		/*
		 * We build the first list with the actions' names.
		 */
		for (int i = 0; i < actionsList.getResultsListSize(); i++) {
			actionsWidget.add(actionsList.getResultsList(i).getActionName());
		}

		/*
		 * We build the second list with the first action's results.
		 */
		for (int i = 0; i < actionsList.getResultsList(0).getResultsNumber(); i++) {
			resultsList.add(actionsList.getResultsList(0).getResult(i).getName());
		}

		text.setText(actionsList.getResultsList(0).getResult(0).getDescription());
		setSelectionListeners();
	}

	/**
	 * Modifies the text in the third part of this view
	 * when an selection event is received on the
	 * results' list.
	 */
	private void setResultsListSelectionListener() {
		resultsList.addSelectionListener(new SelectionListener() {
			private String mem = "0";

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			/**
			 * Selection d'un item dans la liste
			 */
			public void widgetSelected(SelectionEvent e) {

				Result r = actionsList.getResultsList(currentActionDisplayed).getResult(resultsList.getSelectionIndex());
				text.setText(r.getDescription());


				// Activation de l'objet designe
				System.out.println("... :" + r.getName());
				Coloane.getDefault().getMotor().getSessionManager().getCurrentSession().getModel().highlightNode(r.getName(), mem);
				mem = r.getName();
			}
		});
	}

	/**
	 * Adds the SelectionListeners for the actions' list
	 * and the results' list.
	 */
	private void setSelectionListeners() {
		setActionsListSelectionListener();
		setResultsListSelectionListener();
	}

	public final void setActionsList(ActionsList oActionsList) {
		this.actionsList = oActionsList;
	}

	public final void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		actionsList = (ActionsList) o;
		setLists();

	}

	public static ResultsView getInstance() {
		return instance;
	}
}
