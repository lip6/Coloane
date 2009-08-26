package its.tpnui;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import fr.lip6.move.coloane.interfaces.model.IGraph;

public class NodesTable {

	private IGraph model;
	private TableViewer viewer;
	
	
//	public TypesTable(Composite parent, int style, TypeList types) {
//		super(parent,style);
//		createViewer(this);
//		this.types = types ;
//		viewer.setInput(types);
//	}
	public NodesTable(IGraph model) {
		this.model = model;
	}

	public void createWidgets(Composite parent) {
		createViewer(parent);
		viewer.setInput(model);
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent,SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new TransitionTableProvider());
		viewer.setLabelProvider(new TransitionLabelProvider());
	}

	private static String [] columnTitles = {"Visibility","Label","eft","lft"}; 
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
