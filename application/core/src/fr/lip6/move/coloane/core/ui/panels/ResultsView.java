package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.core.ui.model.IElement;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
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
	private static ResultsView instance;
	private static final ISessionManager MANAGER = SessionManager.getInstance();


	/** Vue représentant l'arbre des résultats */
	private TreeViewer viewer;

	/** Action pour supprimer un resultat de l'arbre */
	private Action delete;

	/** Action pour supprimer tous les resultats de l'arbre */
	private Action deleteAll;

	/**
	 * Constructeur privé, ResultView est un singleton
	 */
	public ResultsView() {
		super();
		createActions();
	}

	/**
	 * @return Instance du ResultView
	 */
	public static ResultsView getInstance() {
		if (instance == null) {
			instance = new ResultsView();
		}
		return instance;
	}

	@Override
	public final void createPartControl(final Composite parent) {
		viewer = new TreeViewer(parent);
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

						// Rafraichissement de la vue
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

		// Action quand on clic dans l'arbre : mettre en valeur les objets sélectionnés
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IModelImpl model = MANAGER.getCurrentSession().getModel();
				if (model == null) {
					return;
				}

				// Mise a zero de tous les objets (retour a leur apparence normale)
				for (IElement elt : model.getModelObjects()) {
					elt.setSpecial(false);
				}

				// Recuperation de l'arbre de sous-resultat (ou de resultat)
				IResultTree node = (IResultTree) ((TreeSelection) event.getSelection()).getFirstElement();

				if (node != null) {
					// Selection des objets du modele
					for (Integer toHighlight : node.getHighlighted()) {
						if (toHighlight != -1) {
							IElement elt = model.getModelObject(toHighlight);
							if (elt != null) {
								elt.setSpecial(true);
							}
						}
					}
					delete.setEnabled(true);
				}
			}
		});

		// Ajout d'un Observer sur les changements de sessions
		((Observable) MANAGER).addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				if (arg instanceof Session) {
					final ResultTreeList currentResult = ((ISession) arg).getServiceResults();
					parent.getDisplay().asyncExec(new Runnable() {
						public void run() {
							viewer.setInput(currentResult);
							currentResult.addObserver(resultObserver);
							viewer.refresh();
						}
					});
				}
			}
		});

		createToolbar();

		updateColumnsWidth();
		Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		instance = this;
	}

	private void createActions() {
		ImageDescriptor cross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_rem.gif");
		ImageDescriptor doubleCross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_remall.gif");

		// Suppression d'un resultat
		delete = new Action("Delete") {
			@Override
			public void run() {
				IResultTree node = (IResultTree) ((ITreeSelection) viewer.getSelection()).getFirstElement();
				if (node != null) {
					node.remove();
					this.setEnabled(false);
				}
			}
		};
		delete.setEnabled(false);
		delete.setToolTipText("Delete result");
		delete.setImageDescriptor(cross);

		// Suppression de tous les résultats
		deleteAll = new Action("Delete All") {
			@Override
			public void run() {
				MANAGER.getCurrentSession().getServiceResults().removeAll();
			}
		};
		deleteAll.setToolTipText("Delete all results");
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public final void setFocus() {
		return;
	}
}
