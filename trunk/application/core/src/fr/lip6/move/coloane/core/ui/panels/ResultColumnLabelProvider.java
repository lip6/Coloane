package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.results.IResultTree;

import org.eclipse.jface.viewers.ColumnLabelProvider;

public class ResultColumnLabelProvider extends ColumnLabelProvider {
	private final int column;

	public ResultColumnLabelProvider(int column) {
		this.column = column;
	}

	@Override
	public final String getText(Object element) {
		IResultTree node = (IResultTree) element;
		if (node.getElement().size() > column) {
			return (String) node.getElement().get(column);
		} else {
			return "";
		}
	}

}
