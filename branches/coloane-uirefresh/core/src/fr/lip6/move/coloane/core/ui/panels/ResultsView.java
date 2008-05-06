package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.results_new.IResultTree;
import fr.lip6.move.coloane.core.results_new.ResultTreeList;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;

/**
 * Gestion de la vue des resultats
 */
public class ResultsView extends ViewPart {
	private static ResultsView instance;



	/** Vue représentant l'arbre des résultats */
	private TreeViewer viewer;



	/**
	 * Constructeur privé, ResultView est un singleton 
	 */
	public ResultsView() {
		super();
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
		final IModelImpl model = Coloane.getDefault().getMotor().getSessionManager().getCurrentSessionModel();

		viewer = new TreeViewer(parent);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ResultContentProvider());

		
		// Ajout d'une seul colonne si il en faut plus elles seront ajoutées dynamiquements
		new TreeViewerColumn(viewer, SWT.LEFT).setLabelProvider(new ResultColumnLabelProvider(0));

		final ResultTreeList results = UserInterface.getInstance().getServiceResults();
		
		
		// Ajout d'un observer sur la liste de résultat
		results.addObserver(new Observer() {
			@Override
			public void update(final Observable o, Object arg) {
				final int width = (Integer)arg;
				parent.getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						// Mise en avant des objets
						if(o instanceof ResultTreeList) {
							ResultTreeList treeList = (ResultTreeList) o;
							for(Integer id:treeList.getHighlight())
								model.getNode(id).setSpecial(true);
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

		
		// Action quand on clic dans l'arbre
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
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
					else if(node.getParent()==null) {
						for(Integer id:results.getHighlight(node))
							model.getNode(id).setSpecial(true);
					}
				}
			}
		});

		updateColumnsWidth();
		Tree tree = viewer.getTree();
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		instance = this;
	}

	/**
	 * Règle la largeur des colonnes
	 */
	private void updateColumnsWidth() {
		Tree tree = viewer.getTree();
		for (int i = 0, n = tree.getColumnCount(); i < n; i++) {
			tree.getColumn(i).setWidth(200);
//			tree.getColumn(i).pack();
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
