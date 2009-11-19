package fr.lip6.move.coloane.its.labelsui;


import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/** 
 * A very basic component to display a list of strings as a table.
 * @author Yann
 *
 */
public final class LabelsTable {

	private List<String> list;
	private TableViewer viewer;

	private String [] columnTitles = {""};
	private int [] columnSizes = {100};

	/**
	 * Ctor.
	 * @param list of labels
	 * @param title of the section
	 */
	public LabelsTable(List<String> list, String title) {
		this.list = list;
		columnTitles[0] = title;
	}

	/**
	 * Builds the objects.
	 * @param parent parent
	 */
	public void createWidgets(Composite parent) {
		createViewer(parent);
		viewer.setInput(list);
	}

	/**
	 * Creates the viewer.
	 * @param parent the parent
	 */
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new LabelsProvider());
		viewer.setLabelProvider(new LabelsLabelProvider());
	}

	/**
	 * Build the column.
	 * @param viewer the viewer to work in.
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
	 * update the display
	 */
	public void refresh() {
		viewer.refresh();

	}

	/**
	 * accessor for the viewer
	 * @return the base viewer of this table.
	 */
	public TableViewer getViewer() {
		return viewer;
	}

}
