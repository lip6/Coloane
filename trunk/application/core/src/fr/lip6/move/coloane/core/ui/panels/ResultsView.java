/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.results.ResultManager;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.ISessionManager;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Result View
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 * @author Florian David
 */
public class ResultsView extends ViewPart {
	public static final String SELECTION_CHANGE = "ResultsView.SelectionChange"; //$NON-NLS-1$

	/** The SessionManager */
	private static final ISessionManager MANAGER = SessionManager.getInstance();

	/** Map that stores the special state for each object designed by results */
	private static Map<ISpecialState, Integer> checkStateMap = new HashMap<ISpecialState, Integer>();

	/** The tree view */
	private static CheckboxTreeViewer viewer;

	/** Action that deletes a result from the arc */
	private Action delete;

	/** Action that deletes all results from the result tree */
	private Action deleteAll;

	/** Action that collapses all children */
	private Action collapseAll;

	/** Action that expands all children */
	private Action expandAll;

	/** Listener on checkbox */
	private CheckStateListener checkStateListener;

	/**
	 * Constructor
	 */
	public ResultsView() {
		super();
		createActions();
	}

	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		ISession session = MANAGER.getCurrentSession();
		if (session != null) {
			unHighlightAll(session.getGraph());
		}
		checkStateMap.clear();
		super.dispose();
	}

	/**
	 * Remove a result from the view and modify the checkMap according to the new state of the view
	 * @param result The result to remove from the view
	 */
	public static void reinitResultView(IResultTree result) {
		uncheckAllResult(MANAGER.getCurrentSession(), result);
	}

	/** {@inheritDoc} */
	@Override
	public final void createPartControl(final Composite parent) {
		viewer = new CheckboxTreeViewer(parent, SWT.MULTI | SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ResultContentProvider());

		// Add the first column
		new TreeViewerColumn(viewer, SWT.LEFT).setLabelProvider(new ResultColumnLabelProvider(0));
		new TreeViewerColumn(viewer, SWT.LEFT).setLabelProvider(new ResultColumnLabelProvider(1));

		ResultManager resultsManager = null;
		if (MANAGER.getCurrentSession() != null) {
			resultsManager = MANAGER.getCurrentSession().getResultManager();
		}

		// Build an observer. It will observe a ResultTreeList.
		final Observer resultObserver = new Observer() {
			// Something has been updated !
			public void update(final Observable o, Object arg) {
				final Integer width = (Integer) arg;
				// Update the UI... Take care !!
				parent.getDisplay().syncExec(new Runnable() {
					public void run() {
						// Add columns if necessary
						for (int i = viewer.getTree().getColumnCount(); i < width; i++) {
							TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
							column.setLabelProvider(new ResultColumnLabelProvider(i));
						}
						updateColumnsWidth();
						// Update the view
						viewer.refresh();
					}
				});
			}
		};

		// Add the observer to the result list
		if (resultsManager != null) {
			viewer.setInput(resultsManager);
			resultsManager.addObserver(resultObserver);
		}

		// Allow the user to delete a result that has been selected (activate the action)
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				delete.setEnabled(true);
			}
		});

		// When a result is selected... Add a listener to this checkbox
		checkStateListener = new CheckStateListener(viewer, checkStateMap);
		viewer.addCheckStateListener(checkStateListener);

		// Add an observer to observe session switch
		MANAGER.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(ISessionManager.PROP_CURRENT_SESSION)) {
					final ISession previous = (ISession) evt.getOldValue();
					final ISession current = (ISession) evt.getNewValue();
					if (!parent.isDisposed() && !parent.getDisplay().isDisposed()) {
						parent.getDisplay().asyncExec(new Runnable() {
							public void run() {
								if (previous != null) {
									previous.getResultManager().deleteObserver(resultObserver);
								}
								if (current != null) {
									viewer.setInput(current.getResultManager());
									current.getResultManager().addObserver(resultObserver);
									viewer.refresh();
								} else {
									viewer.setInput(null);
									viewer.refresh();
								}
							}
						});
					}
				}
			}
		});

		createToolbar();
		updateColumnsWidth();
		Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	/**
	 * Create the toolbar
	 */
	private void createToolbar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(expandAll);
		toolbarManager.add(collapseAll);
		toolbarManager.add(new Separator());
		toolbarManager.add(delete);
		toolbarManager.add(deleteAll);
	}

	/**
	 * Create actions associated to the result view
	 */
	private void createActions() {
		ImageDescriptor cross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_rem.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		ImageDescriptor doubleCross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_remall.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		ImageDescriptor collapse = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/collapseall.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		ImageDescriptor expand = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.cheatsheets", "$nl$/icons/elcl16/expandall.gif"); //$NON-NLS-1$ //$NON-NLS-2$

		// Action for collapsing all results
		collapseAll = new Action(Messages.ResultsView_4) {
			@Override
			public void run() {
				viewer.collapseAll();
			}
		};
		collapseAll.setToolTipText(Messages.ResultsView_4);
		collapseAll.setImageDescriptor(collapse);

		// Action for expanding all results
		expandAll = new Action(Messages.ResultsView_5) {
			@Override
			public void run() {
				viewer.expandAll();
			}
		};
		expandAll.setToolTipText(Messages.ResultsView_5);
		expandAll.setImageDescriptor(expand);

		// Action for deleting a result
		delete = new Action(Messages.ResultsView_0) {
			@Override
			public void run() {
				for (Object obj : ((ITreeSelection) viewer.getSelection()).toList()) {
					IResultTree node = (IResultTree) obj;
					ISession session = MANAGER.getCurrentSession();
					ResultsView.uncheckAllResult(session, node);
					node.remove();
					viewer.refresh();
					this.setEnabled(false);
				}
			}
		};
		delete.setEnabled(false);
		delete.setToolTipText(Messages.ResultsView_1);
		delete.setImageDescriptor(cross);

		// Action for deleting all results
		deleteAll = new Action(Messages.ResultsView_2) {
			@Override
			public void run() {
				ISession session = MANAGER.getCurrentSession();
				ResultManager list = session.getResultManager();
				ResultsView.this.unHighlightAll(session.getGraph());
				for (IResultTree result : list.getChildren()) {
					ResultsView.this.removeTips(session, result);
				}
				list.removeAll();
			}
		};
		deleteAll.setToolTipText(Messages.ResultsView_3);
		deleteAll.setImageDescriptor(doubleCross);
	}

	/**
	 * Remove all session tips recursively.
	 * @param session the session where tips are removed.
	 * @param result the current result tree which contained tips to remove.
	 */
	private void removeTips(ISession session, IResultTree result) {
		session.removeTips(result.getTips());
		for (IResultTree child : result.getChildren()) {
			removeTips(session, child);
		}
	}

	/**
	 * This method unchecks results provided by the IResultTree. It also modifies the checkStateMap consequently.
	 * @param session The session in which is link the result.
	 * @param result The result to be unchecked.
	 */
	private static void uncheckAllResult(ISession session, IResultTree result) {
		// If the result is checked . . .
		if (viewer.getChecked(result)) {
			// Model objects which are only highlighted by this result will be unhighlighted
			for (int id : result.getHighlighted()) {
				// We get the associate element
				ISpecialState element = (ISpecialState) session.getGraph().getObject(id);
				if (element != null) {
					// The checkStateMap is modified
					Integer value = checkStateMap.get(element);
					if (value != null) {
						// If the object is checked strictly more than 1 time, we just decrement its check value
						if (value > 1) {
							value--;
							checkStateMap.put(element, value);
						} else if (value == 1) { // If the object is only check 1 time, it's only by this result so we unhighlight it
							value--;
							checkStateMap.put(element, value);
							element.setSpecialState(false);
						}
					}
				}
			}

			// Now the attributes . . .
			Map<Integer, List<String>> attributesMap = result.getAttributesOutline();
			// For each object ID in the checkStateMap
			for (int id : attributesMap.keySet()) {
				// We get the associate element
				IElement element = session.getGraph().getObject(id);
				if (element != null) {
					// Then we get the highlighted attributes list
					List<String> listAttribute = attributesMap.get(id);
					for (String strAttribute : listAttribute) {
						// For each attribute, we get its check value
						ISpecialState attribute = (ISpecialState) element.getAttribute(strAttribute);
						Integer value = checkStateMap.get(attribute);
						if (value != null) {
							// If the attribute is checked strictly more than 1 time, we just decrement its check value
							if (value > 1) {
								value--;
								checkStateMap.put(attribute, value);
							} else if (value == 1) { // If the attribute is only check 1 time, it's only by this result so we unhighlight it
								value--;
								checkStateMap.put(attribute, value);
								attribute.setSpecialState(false);
							}
						}
					}
				}
			}
			session.removeTips(result.getTips());
		}

		// We call the method recursively on every child result
		for (IResultTree child : result.getChildren()) {
			uncheckAllResult(session, child);
		}
	}

	/**
	 * Remove the highlight state on all graph elements.
	 * @param graph the graph where highlight state has to be removed.
	 */
	private void unHighlightAll(IGraph graph) {
		for (IArc arc : graph.getArcs()) {
			((ISpecialState) arc).setSpecialState(false);
			for (IAttribute attribute : arc.getAttributes()) {
				((ISpecialState) attribute).setSpecialState(false);
			}
		}
		for (INode node : graph.getNodes()) {
			((ISpecialState) node).setSpecialState(false);
			for (IAttribute attribute : node.getAttributes()) {
				((ISpecialState) attribute).setSpecialState(false);
			}
		}
	}

	/**
	 * Update the columns width
	 */
	private void updateColumnsWidth() {
		Tree tree = viewer.getTree();
		for (int i = 0, n = tree.getColumnCount(); i < n; i++) {
			tree.getColumn(i).setWidth(200);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void setFocus() {
		return;
	}
}
