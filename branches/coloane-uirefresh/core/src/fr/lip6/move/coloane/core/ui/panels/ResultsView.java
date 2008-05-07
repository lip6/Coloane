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
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * Gestion de la vue des resultats
 */
public class ResultsView extends ViewPart {
	private static ResultsView instance;
	private static final SessionManager manager = Coloane.getDefault().getMotor().getSessionManager();


	/** Vue représentant l'arbre des résultats */
	private TreeViewer viewer;

	/** Action pour supprimer un resultat de l'arbre */
	private Action delete;

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
		if(instance==null)
			instance = new ResultsView();
		return instance;
	}

	@Override
	public final void createPartControl(final Composite parent) {
		viewer = new TreeViewer(parent);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ResultContentProvider());


		// Ajout d'une seul colonne si il en faut plus elles seront ajoutées dynamiquements
		new TreeViewerColumn(viewer, SWT.LEFT).setLabelProvider(new ResultColumnLabelProvider(0));

		ResultTreeList results = manager.getCurrentServiceResult();


		// Ajout d'un observer sur la liste de résultat
		if(results!=null) {
			results.addObserver(new Observer() {
				public void update(final Observable o, Object arg) {
					final int width = (Integer)arg;
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							IModelImpl model = manager.getCurrentSessionModel();
							if(model==null)
								return;

							// Mise en avant des objets
							if(o instanceof ResultTreeList) {
								ResultTreeList treeList = (ResultTreeList) o;
								for(Integer id:treeList.getHighlight()) {
									try {
										model.getNode(id).setSpecial(true);
									} catch(NullPointerException e) {
										System.err.println("NullPointer " + id);
									}
								}
							}

							// Ajout de colonnes si il faut
							for(int i=viewer.getTree().getColumnCount();i<width;i++) {
								TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
								column.setLabelProvider(new ResultColumnLabelProvider(i));
							}
							updateColumnsWidth();

							// Rafraichissement de la vue
							viewer.refresh();
						}
					});
				}
			});
			viewer.setInput(results);
		}


		// Action quand on clic dans l'arbre
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IModelImpl model = manager.getCurrentSessionModel();
				if(model==null)
					return;

				IResultTree node = (IResultTree)((TreeSelection)event.getSelection()).getFirstElement();
				for(INodeImpl nodeImpl:model.getNodes())
					nodeImpl.setSelect(false);
				if(node!=null) {

					// Selection d'un objet du model
					if(node.getId()!=-1) {
						INodeImpl nodeImpl = model.getNode(node.getId());
						if(nodeImpl!=null)
							nodeImpl.setSelect(true);
					}

					// Mise en avant de tous les objets d'une réponse
					else if(node.getParent()==null && manager.getCurrentServiceResult()!=null) {
						for(Integer id:manager.getCurrentServiceResult().getHighlight(node))
							model.getNode(id).setSpecial(true);
					}

					delete.setEnabled(true);
				}
			}
		});

		
		manager.addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				System.err.println("aaaaaaaaaaaaaaaaaaa "+arg);
				if(arg instanceof Session) {
					System.err.println("bbbbbbbbbbbbbbbbbbbbbb");
					final ResultTreeList currentResult = ((Session) arg).getServiceResults();
					parent.getDisplay().asyncExec(new Runnable() {
						public void run() {
							System.err.println("ccccccccccccccccccccc");
							System.err.println(currentResult);
							viewer.setInput(currentResult);
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
		// Suppression d'un resultat
		delete = new Action("Delete") {
			@Override
			public void run() {
				IResultTree node = (IResultTree)((ITreeSelection)viewer.getSelection()).getFirstElement();
				if(node!=null) {
					while(node.getParent()!=null)
						node = node.getParent();
					node.remove();
					this.setEnabled(false);
				}
			}
		};
		delete.setEnabled(false);
		delete.setToolTipText("Delete result");
		ImageDescriptor cross = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
		delete.setImageDescriptor(cross);
	}

	/**
	 * Création de la barre d'outils des résultats
	 */
	private void createToolbar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(delete);
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
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public final void setFocus() {
		return;
	}
}
