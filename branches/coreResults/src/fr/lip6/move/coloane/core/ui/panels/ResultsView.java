package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.core.results.ResultTreeList;
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
 * TODO : Les attributs peuvent être linker uniquement aux sub-result, i.e. au menu.
 *  On ne peut pas les associer à un noeud ou à un arc.
 * 
 * Gestion de la vue des résultats
 */
public class ResultsView extends ViewPart {
	public static final String SELECTION_CHANGE = "ResultsView.SelectionChange"; //$NON-NLS-1$

	private static final ISessionManager MANAGER = SessionManager.getInstance();

	/** Vue représentant l'arbre des résultats */
	private static CheckboxTreeViewer viewer;

	/** Action pour supprimer un résultat de l'arbre */
	private Action delete;

	/** Action pour supprimer tous les résultats de l'arbre */
	private Action deleteAll;

	/** Action pour collapser tous les résultats de l'arbre */
	private Action collapseAll;

	/** Action pour expand tous les résultats de l'arbre */
	private Action expandAll;
	
	/** Listener sur les cases cochées */
	private CheckStateListener checkStateListener;
	
	/** Map contenant le nombre de fois qu'un objet est coché */
	private static Map<ISpecialState, Integer> checkStateMap = new HashMap<ISpecialState, Integer>();
	
	/**
	 * Constructeur
	 */
	public ResultsView() {
		super();
		createActions();
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		unHighlightAll(MANAGER.getCurrentSession().getGraph());
		checkStateMap.clear();
		super.dispose();
	}
	
	/**
	 * Permet d'enlever un résultat de la vue et de modifier la checkStateMap en conséquence. Voir {@link ResultTreeList#add}
	 * @param result Le résultat à enlever de la vue 
	 */
	public static void reinitResultView(IResultTree result) {
		uncheckAllResult(MANAGER.getCurrentSession(),result);
	}
	
	
	/** {@inheritDoc} */
	@Override
	public final void createPartControl(final Composite parent) {
		viewer = new CheckboxTreeViewer(parent, SWT.MULTI | SWT.BORDER | SWT.CHECK);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ResultContentProvider());

		// Ajout d'une seul colonne si il en faut plus elles seront ajoutées dynamiquements
		new TreeViewerColumn(viewer, SWT.LEFT).setLabelProvider(new ResultColumnLabelProvider(0));

		ResultTreeList results = null;
		if (MANAGER.getCurrentSession() != null) {
			results = MANAGER.getCurrentSession().getServiceResults();
		}

		// Création d'un observer de ResultTreeList qui fera les mises à jours nécessaire en cas modification des résultats : ajouts/suppressions.
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
		checkStateListener = new CheckStateListener(viewer, checkStateMap);
		viewer.addCheckStateListener(checkStateListener);

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
				ResultTreeList list = session.getServiceResults();
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
	 * TODO : ?
	 * 
	 * @param session
	 * @param result
	 */
	private void removeTips(ISession session, IResultTree result) {
		session.removeAllTips(result.getTips());
		for (IResultTree child : result.getChildren()) {
			removeTips(session, child);
		}
	}

	/**
	 * This method uncheck results provided by the IResultTree. It also modify the checkStateMap consequently.
	 * 
	 * @param session The session in which is link the result.
	 * @param result The result to be unchecked.
	 */
	private static void uncheckAllResult(ISession session, IResultTree result) {
		// If the result is checked . . .
		if (viewer.getChecked(result)) {
			/* Model objects which are only highlight by this result will be unhighlighted
			 * For each object ID in the highlight list
			 */
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
						}
						// If the object is only check 1 time, it's only by this result so we unhighlight it
						else if (value == 1) {
							value--;
							checkStateMap.put(element, value);
							element.setSpecialState(false);
						}
					}
				}
			}

			// Now the attributes . . .
			Map<Integer,List<String>> attributesMap = result.getAttributesOutline();
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
							}
							// If the attribute is only check 1 time, it's only by this result so we unhighlight it
							else if (value == 1) {
								value--;
								checkStateMap.put(attribute, value);
								attribute.setSpecialState(false);
							}
						}
					}
				}
			}
			/** TODO : le removeAllTips doit-il etre présent dans la méthode uncheck*/
			session.removeAllTips(result.getTips());
		}
		
		// We call the method recursively on every child result
		for (IResultTree child : result.getChildren()) {
			uncheckAllResult(session, child);
		}
	}

	/**
	 * 
	 * @param session
	 */
	private void unHighlightAll(IGraph graph) {
		for(IArc arc : graph.getArcs()) {
			((ISpecialState)arc).setSpecialState(false);
			for (IAttribute attribute : arc.getAttributes()) {
				((ISpecialState)attribute).setSpecialState(false);
			}
		}
		for(INode node : graph.getNodes()) {
			((ISpecialState)node).setSpecialState(false);
			for (IAttribute attribute : node.getAttributes()) {
				((ISpecialState)attribute).setSpecialState(false);
			}
		}
	}
	
	/**
	 * Création de la barre d'outils des résultats
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
