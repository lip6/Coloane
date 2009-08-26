package its.typesui;


import its.TypeList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class TypesTable {

	private TypeList types;
	private TableViewer viewer;


	//	public TypesTable(Composite parent, int style, TypeList types) {
	//		super(parent,style);
	//		createViewer(this);
	//		this.types = types ;
	//		viewer.setInput(types);
	//	}
	public TypesTable(TypeList types) {
		this.types = types;
	}

	public void createWidgets(Composite parent) {
		createViewer(parent);
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent,SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new TypeListProvider());
		viewer.setLabelProvider(new TypeLabelProvider());		
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(types);
	
	}

	private static String [] columnTitles = {"Type Name","Type","Path","Resolved"}; 
	private static int [] columnSizes = {100,100,100,100};

	private void createColumns(TableViewer viewer2) {
		for (int i=0; i < columnSizes.length ; ++i ) {
			TableViewerColumn column = new TableViewerColumn(viewer,SWT.NONE);
			column.getColumn().setText(columnTitles[i]);
			column.getColumn().setWidth(columnSizes[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);				
		}
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	public void refresh() {
		viewer.refresh();

	}

	public TableViewer getViewer() {
		return viewer;
	}

}
