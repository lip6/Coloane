package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

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
	private static final SessionManager MANAGER = Coloane.getDefault().getMotor().getSessionManager();


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

		final ResultTreeList results = MANAGER.getCurrentServiceResult();


		// Création d'un observer de ResultTreeList qui fera les mises à jours nécessaire
		// en cas modification des résultats : ajouts/suppressions.
		final Observer resultObserver = new Observer() {
			public void update(final Observable o, Object arg) {
				final Integer width = (Integer) arg;
				parent.getDisplay().syncExec(new Runnable() {
					public void run() {
						IModelImpl model = MANAGER.getCurrentSessionModel();

						if (model != null) {
							// Mise en avant des objets
							if (o instanceof ResultTreeList) {
								ResultTreeList treeList = (ResultTreeList) o;
								for (Integer id : treeList.getHighlight()) {
									model.getNode(id).setSpecial(true);
								}
							}

							// Ajout de colonnes si il faut
							for (int i = viewer.getTree().getColumnCount(); i < width; i++) {
								TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
								column.setLabelProvider(new ResultColumnLabelProvider(i));
							}
							updateColumnsWidth();
						}

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
				IModelImpl model = MANAGER.getCurrentSessionModel();
				if (model == null) {
					return;
				}

				IResultTree node = (IResultTree) ((TreeSelection) event.getSelection()).getFirstElement();
				for (INodeImpl nodeImpl : model.getNodes()) {
					nodeImpl.setSelect(false);
				}

				if (node != null) {
					// Selection d'un objet du model
					if (node.getId() != -1) {
						INodeImpl nodeImpl = model.getNode(node.getId());
						if (nodeImpl != null) {
							nodeImpl.setSelect(true);
						}

					// Mise en avant de tous les objets d'une réponse
					} else if (node.getParent() == null && MANAGER.getCurrentServiceResult() != null) {
						for (Integer id : MANAGER.getCurrentServiceResult().getHighlight(node)) {
							model.getNode(id).setSpecial(true);
						}
					}

					delete.setEnabled(true);
				}
			}
		});

		// Ajout d'un Observer sur les changements de sessions
		MANAGER.addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				if (arg instanceof Session) {
					final ResultTreeList currentResult = ((Session) arg).getServiceResults();
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
					while (node.getParent() != null) {
						node = node.getParent();
					}
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
				MANAGER.getCurrentServiceResult().removeAll();
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
