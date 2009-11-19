package its.tpnui;

import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 * A table view of a graph model.
 * @author Yann
 *
 */
public final class NodesTable {

	private static String [] columnTitles = {"Visibility", "Label", "eft", "lft"};
	private static int [] columnSizes = {100, 00, 00, 00};
	private IGraph model;
	private TableViewer viewer;


	/**
	 * Ctor
	 * @param model to be loaded
	 */
	public NodesTable(IGraph model) {
		this.model = model;
	}

	/**
	 * Create the widgets
	 * @param parent in this composite
	 */
	public void createWidgets(Composite parent) {
		createViewer(parent);
		viewer.setInput(model);
	}

	/**
	 * Create the table
	 * @param parent in this composite
	 */
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new TransitionTableProvider());
		viewer.setLabelProvider(new TransitionLabelProvider());
	}


	/**
	 * build the columns.
	 * @param viewer the table
	 */
	private void createColumns(TableViewer viewer) {
		for (int i = 0; i < columnSizes.length; ++i) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(columnTitles[i]);
			column.getColumn().setWidth(columnSizes[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
		}
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	/**
	 * update the table view using the model.
	 */
	public void refresh() {
		viewer.refresh();

	}

	/**
	 * @return the viewer for this table
	 */
	public TableViewer getViewer() {
		return viewer;
	}



}
