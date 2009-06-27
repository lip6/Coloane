package its.conceptsui;


import its.CompositeTypeDeclaration;
import its.TypeList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class ConceptsTable {

	private CompositeTypeDeclaration ctd;
	private TableViewer viewer;
	private TypeList tl;


	//	public TypesTable(Composite parent, int style, TypeList types) {
	//		super(parent,style);
	//		createViewer(this);
	//		this.types = types ;
	//		viewer.setInput(types);
	//	}
	public ConceptsTable(CompositeTypeDeclaration types, TypeList tl) {
		this.ctd = types;
		this.tl = tl;
	}

	public void createWidgets(Composite parent) {
		createViewer(parent);
		viewer.setInput(ctd);
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent,SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new ConceptProvider());
		viewer.setLabelProvider(new ConceptLabelProvider());		
	}

	private static String [] columnTitles = {"Concept Name","Concrete Type"}; 
	private static int [] columnSizes = {100,100};

	private void createColumns(TableViewer viewer2) {
		for (int i=0; i < columnSizes.length ; ++i ) {
			TableViewerColumn column = new TableViewerColumn(viewer,SWT.NONE);
			column.getColumn().setText(columnTitles[i]);
			column.getColumn().setWidth(columnSizes[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
			if (i==1) {
				column.setEditingSupport(new CompositeTypeDeclEditingSupport(viewer,tl));
			}
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
