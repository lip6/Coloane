package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.results_new.ResultTreeList;
import fr.lip6.move.coloane.core.ui.UserInterface;

import java.util.Observable;
import java.util.Observer;

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
		viewer = new TreeViewer(parent);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ResultContentProvider());

		// Ajout d'une seul colonne si il en faut plus elles seront ajoutées dynamiquements
		new TreeViewerColumn(viewer, SWT.LEFT).setLabelProvider(new ResultColumnLabelProvider(0));

		ResultTreeList results = UserInterface.getInstance().getServiceResults();
		results.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				final int width = (Integer)arg;
				parent.getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						System.err.println("width "+width);
						System.err.println("current width "+viewer.getTree().getColumnCount());
						for(int i=viewer.getTree().getColumnCount();i<width;i++) {
							System.err.println("Ajout d'une colonne");
							TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
							column.setLabelProvider(new ResultColumnLabelProvider(i));
						}

						viewer.refresh();
					}
				});
			}
		});
		viewer.setInput(results);

		Tree tree = viewer.getTree();
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		for (int i = 0, n = tree.getColumnCount(); i < n; i++) {
			tree.getColumn(i).setWidth(200);
		}

		tree.setHeaderVisible(false);
		tree.setLinesVisible(false);

		instance = this;
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
