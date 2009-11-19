package its.typesui;


import its.TypeList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 *  A table to represent a set of types
 * @author Yann
 *
 */
public final class TypesTable {

	private static String [] columnTitles = {"Type Name", "Type", "Path", "Resolved"};
	private static int [] columnSizes = {100, 00, 00, 00};
	private TypeList types;
	private TableViewer viewer;

	/**
	 * Ctor
	 * @param types to display in the table
	 */
	public TypesTable(TypeList types) {
		this.types = types;
	}

	/**
	 * Build the widgets
	 * @param parent the composite that holds this table
	 */
	public void createWidgets(Composite parent) {
		createViewer(parent);
	}

	/**
	 * Build the table viewer.
	 * @param parent the composite that contains this.
	 */
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new TypeListProvider());
		viewer.setLabelProvider(new TypeLabelProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(types);

	}

	/**
	 * Build the table columns
	 * @param viewer the viewer to add to
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
	 * Update the view using the model
	 */
	public void refresh() {
		viewer.refresh();

	}

	/**
	 * @return the viewer
	 */
	public TableViewer getViewer() {
		return viewer;
	}

}
