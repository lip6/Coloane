package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
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
 * Gestion de la vue des resultats
 */
public class ResultsView extends ViewPart {
	public static final String SELECTION_CHANGE = "ResultsView.SelectionChange"; //$NON-NLS-1$

	private static final ISessionManager MANAGER = SessionManager.getInstance();

	/** Vue représentant l'arbre des résultats */
	private CheckboxTreeViewer viewer;

	/** Action pour supprimer un resultat de l'arbre */
	private Action delete;

	/** Action pour supprimer tous les resultats de l'arbre */
	private Action deleteAll;

	/**
	 * Constructeur
	 */
	public ResultsView() {
		super();
		createActions();
	}

	/** {@inheritDoc} */
	@Override
	public final void createPartControl(final Composite parent) {
		viewer = new CheckboxTreeViewer(parent);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ResultContentProvider());

		// Ajout d'une seul colonne si il en faut plus elles seront ajoutées dynamiquements
		new TreeViewerColumn(viewer, SWT.LEFT).setLabelProvider(new ResultColumnLabelProvider(0));

		ResultTreeList results = null;
		if (MANAGER.getCurrentSession() != null) {
			results = MANAGER.getCurrentSession().getServiceResults();
		}

		// Création d'un observer de ResultTreeList qui fera les mises à jours nécessaire
		// en cas modification des résultats : ajouts/suppressions.
		final Observer resultObserver = new Observer() {
			public void update(final Observable o, Object arg) {
				final Integer width = (Integer) arg;
				parent.getDisplay().syncExec(new Runnable() {
					public void run() {
						// Ajout de colonnes si il faut
						for (int i = viewer.getTree().getColumnCount(); i < width; i++) {
							TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
							column.setLabelProvider(new ResultColumnLabelProvider(i));
						}
						updateColumnsWidth();

						// Rafraîchissement de la vue
						viewer.refresh();
					}
				});
			}
		};

		// Ajout d'un observer sur la liste de résultat courrante
		if (results != null) {
			viewer.setInput(results);
			results.addObserver(resultObserver);
		}

		// Action quand on clic dans l'arbre : activer la suppression des résultats
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				delete.setEnabled(true);
			}
		});
		viewer.addCheckStateListener(new ICheckStateListener() {
			private void checkResult(ISession session, IResultTree result, boolean check) {
				for (int id : result.getHighlighted()) {
					ISpecialState element = (ISpecialState) session.getGraph().getNode(id);
					if (element == null) {
						element = (ISpecialState) session.getGraph().getArc(id);
					}
					if (element != null) {
						element.setSpecialState(check);
					}
				}
				if (check) {
					session.addAllTips(result.getTips());
				} else {
					session.removeAllTips(result.getTips());
				}
				for (IResultTree child : result.getChildren()) {
					checkResult(session, child, check);
				}
			}

			public void checkStateChanged(CheckStateChangedEvent event) {
				IResultTree result = (IResultTree) event.getElement();
				viewer.setSubtreeChecked(event.getElement(), event.getChecked());
				checkResult(MANAGER.getCurrentSession(), result, event.getChecked());
			}
		});

		// Ajout d'un Observer sur les changements de sessions
		MANAGER.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(ISessionManager.PROP_CURRENT_SESSION)) {
					if (evt.getOldValue() != null) {
						final ResultTreeList previousResult = ((ISession) evt.getOldValue()).getServiceResults();
						parent.getDisplay().asyncExec(new Runnable() {
							public void run() {
								previousResult.deleteObserver(resultObserver);
							}
						});
					}
					if (evt.getNewValue() != null) {
						final ResultTreeList currentResult = ((ISession) evt.getNewValue()).getServiceResults();
						parent.getDisplay().asyncExec(new Runnable() {
							public void run() {
								viewer.setInput(currentResult);
								currentResult.addObserver(resultObserver);
								viewer.refresh();
							}
						});
					}
				}
			}
		});
//			public void update(Observable o, Object arg) {
//				if (arg instanceof Session) {
//				}
//			}
//		});

		createToolbar();

		updateColumnsWidth();
		Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	/**
	 * Création des actions associé à la vue des résultats
	 */
	private void createActions() {
		ImageDescriptor cross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_rem.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		ImageDescriptor doubleCross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_remall.gif"); //$NON-NLS-1$ //$NON-NLS-2$

		// Suppression d'un résultat
		delete = new Action(Messages.ResultsView_0) {
			@Override
			public void run() {
				for (Object obj : ((ITreeSelection) viewer.getSelection()).toList()) {
					IResultTree node = (IResultTree) obj;
					if (node != null) {
						node.remove();
						this.setEnabled(false);
					}
				}
			}
		};
		delete.setEnabled(false);
		delete.setToolTipText(Messages.ResultsView_1);
		delete.setImageDescriptor(cross);

		// Suppression de tous les résultats
		deleteAll = new Action(Messages.ResultsView_2) {
			@Override
			public void run() {
				MANAGER.getCurrentSession().getServiceResults().removeAll();
			}
		};
		deleteAll.setToolTipText(Messages.ResultsView_3);
		deleteAll.setImageDescriptor(doubleCross);
}

	/**
	 * Création de la barre d'outils des résultats
	 */
	private void createToolbar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(delete);
		toolbarManager.add(deleteAll);
	}

	/**
	 * Règle la largeur des colonnes
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
