package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
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
 * Gestion de la vue des résultats
 */
public class ResultsView extends ViewPart {
	public static final String SELECTION_CHANGE = "ResultsView.SelectionChange"; //$NON-NLS-1$

	private static final ISessionManager MANAGER = SessionManager.getInstance();

	/** Vue représentant l'arbre des résultats */
	private CheckboxTreeViewer viewer;

	/** Action pour supprimer un résultat de l'arbre */
	private Action delete;

	/** Action pour supprimer tous les résultats de l'arbre */
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

		// Action quand on check un des résultats : mise en valeur dans l'éditeur
		viewer.addCheckStateListener(new ICheckStateListener() {

			/**
			 * Map conservant pour chaque noeud mis en valeur, le nombre de résultat
			 * coché dans la vue le concernant (le noeud).
			 * */
			private Map<ISpecialState, Integer> map = new HashMap<ISpecialState, Integer>();

			/**
			 * Mise a jour de l'état check d'une ligne de résultat ainsi que de
			 * tous les fils (récursivement)
			 * @param session session courante
			 * @param result sous arbre de résultat
			 * @param wasCheck ancienne état de result
			 * @param toCheck nouvel état
			 */
			private void checkResult(ISession session, IResultTree result, boolean wasCheck, boolean toCheck) {
				// On vérifie qu'on passe de true à false ou inversement
				if (wasCheck != toCheck) {
					// On traite tous les éléments devant être mis en valeur
					for (int id : result.getHighlighted()) {
						ISpecialState element = (ISpecialState) session.getGraph().getObject(id);
						if (element != null) {
							// On compte le nombre de fois qu'un élément a été
							// demandé a être mis en valeur.
							Integer value = map.get(element);
							if (toCheck) {
								if (value == null) {
									value = 0;
								}
								element.setSpecialState(true);
								value++;
							} else {
								value--;
								if (value == 0) {
									element.setSpecialState(false);
								}
							}
							map.put(element, value);
						}
					}
				}
				// Gestion des Tips
				if (toCheck) {
					if (result.getHighlighted().size() > 0) {
						session.removeAllTips(result.getTips(result.getHighlighted()));
						session.addAllTips(result.getTips(result.getHighlighted()));
					} else {
						session.removeAllTips(result.getTips());
						session.addAllTips(result.getTips());
					}
				} else {
					if (result.getHighlighted().size() > 0) {
						session.removeAllTips(result.getTips(result.getHighlighted()));
					} else {
						session.removeAllTips(result.getTips());
					}
				}
				// Appel récursif sur tous les fils
				for (IResultTree child : result.getChildren()) {
					checkResult(session, child, viewer.getChecked(child), toCheck);
				}
			}

			/** {@inheritDoc} */
			public void checkStateChanged(CheckStateChangedEvent event) {
				IResultTree result = (IResultTree) event.getElement();
				checkResult(MANAGER.getCurrentSession(), result, !event.getChecked(), event.getChecked());
				viewer.setSubtreeChecked(event.getElement(), event.getChecked());
			}
		});

		// Ajout d'un Observer sur les changements de sessions
		MANAGER.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(ISessionManager.PROP_CURRENT_SESSION)) {
					final ISession previous = (ISession) evt.getOldValue();
					final ISession current = (ISession) evt.getNewValue();
					parent.getDisplay().asyncExec(new Runnable() {
						public void run() {
							if (previous != null) {
								previous.getServiceResults().deleteObserver(resultObserver);
							}
							if (current != null) {
								viewer.setInput(current.getServiceResults());
								current.getServiceResults().addObserver(resultObserver);
								viewer.refresh();
							} else {
								viewer.setInput(null);
								viewer.refresh();
							}
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
	}

	/**
	 * Création des actions associé à la vue des résultats
	 */
	private void createActions() {
		ImageDescriptor cross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_rem.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		ImageDescriptor doubleCross = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_remall.gif"); //$NON-NLS-1$ //$NON-NLS-2$

		// Suppression d'un résultat
		delete = new Action(Messages.ResultsView_0) {
			private void uncheckResult(ISession session, IResultTree result) {
				for (int id : result.getHighlighted()) {
					ISpecialState element = (ISpecialState) session.getGraph().getNode(id);
					if (element == null) {
						element = (ISpecialState) session.getGraph().getArc(id);
					}
					if (element != null) {
						element.setSpecialState(false);
					}
				}
				session.removeAllTips(result.getTips());
				for (IResultTree child : result.getChildren()) {
					uncheckResult(session, child);
				}
			}

			@Override
			public void run() {
				for (Object obj : ((ITreeSelection) viewer.getSelection()).toList()) {
					IResultTree node = (IResultTree) obj;
					ISession session = MANAGER.getCurrentSession();
					uncheckResult(session, node);
					node.remove();
					this.setEnabled(false);
				}
			}
		};
		delete.setEnabled(false);
		delete.setToolTipText(Messages.ResultsView_1);
		delete.setImageDescriptor(cross);

		// Suppression de tous les résultats
		deleteAll = new Action(Messages.ResultsView_2) {
			private void uncheckResult(ISession session, IResultTree result) {
				for (int id : result.getHighlighted()) {
					ISpecialState element = (ISpecialState) session.getGraph().getNode(id);
					if (element == null) {
						element = (ISpecialState) session.getGraph().getArc(id);
					}
					if (element != null) {
						element.setSpecialState(false);
					}
				}
				session.removeAllTips(result.getTips());
				for (IResultTree child : result.getChildren()) {
					uncheckResult(session, child);
				}
			}

			@Override
			public void run() {
				ISession session = MANAGER.getCurrentSession();
				ResultTreeList list = session.getServiceResults();
				for (IResultTree result : list.getChildren()) {
					uncheckResult(session, result);
				}
				list.removeAll();
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
