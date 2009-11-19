package fr.lip6.move.coloane.its.conceptsui;


import fr.lip6.move.coloane.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.its.TypeList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 * A class to represent a concept as a table
 * @author Yann
 *
 */
public final class ConceptsTable {

	private static String [] columnTitles = {"Concept Name", "Concrete Type"};
	private static int [] columnSizes = {100, 100};

	private CompositeTypeDeclaration ctd;
	private TableViewer viewer;
	private TypeList tl;

	/**
	 * Ctor.
	 * @param types the supporting parent composite type
	 * @param tl the types list
	 */
	public ConceptsTable(CompositeTypeDeclaration types, TypeList tl) {
		this.ctd = types;
		this.tl = tl;
	}
	/**
	 * build the table
	 * @param parent the parent composite
	 */
	public void createWidgets(Composite parent) {
		createViewer(parent);
		viewer.setInput(ctd);
	}
	/**
	 * create table viewer
	 * @param parent parent composite
	 */
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new ConceptProvider());
		viewer.setLabelProvider(new ConceptLabelProvider());
	}

	/**
	 * Add columns to table
	 * @param viewer the viewer to add to
	 */
	private void createColumns(TableViewer viewer) {
		for (int i = 0; i < columnSizes.length; ++i) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(columnTitles[i]);
			column.getColumn().setWidth(columnSizes[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
			if (i == 1) {
				column.setEditingSupport(new CompositeTypeDeclEditingSupport(viewer, tl));
			}
		}
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	/**
	 * refresh display
	 */
	public void refresh() {
		viewer.refresh();

	}
	/**
	 * accessor
	 * @return this table viewer object
	 */
	public TableViewer getViewer() {
		return viewer;
	}

}
