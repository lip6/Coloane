package fr.lip6.move.coloane.core.ui.panels;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import fr.lip6.move.coloane.core.results_new.IResultTree;

public class ResultColumnLabelProvider extends ColumnLabelProvider {
	private final int column;

	public ResultColumnLabelProvider(int column) {
		this.column = column;
	}
	
	@Override
	public String getText(Object element) {
		IResultTree node = (IResultTree)element;
		if(node.getElement().size()>column)
			return (String)node.getElement().get(column);
		else
			return "";
	}

}
