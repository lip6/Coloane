package its.labelsui;


import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class LabelsTable {

	private List<String> list;
	private TableViewer viewer;

	private String [] columnTitles = {""}; 
	private int [] columnSizes = {100};


	//	public TypesTable(Composite parent, int style, TypeList types) {
	//		super(parent,style);
	//		createViewer(this);
	//		this.types = types ;
	//		viewer.setInput(types);
	//	}
	public LabelsTable(List<String> list, String title) {
		this.list = list;
		columnTitles[0] = title;
	}

	public void createWidgets(Composite parent) {
		createViewer(parent);
		viewer.setInput(list);
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent,SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new LabelsProvider());
		viewer.setLabelProvider(new LabelsLabelProvider());		
	}


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
