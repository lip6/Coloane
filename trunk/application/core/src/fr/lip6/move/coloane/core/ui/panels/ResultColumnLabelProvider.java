package fr.lip6.move.coloane.core.ui.panels;


import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * Provide a label for each tree column
 */
public class ResultColumnLabelProvider extends ColumnLabelProvider {
	private final int column;

	/**
	 * @param column The interesting column number
	 */
	public ResultColumnLabelProvider(int column) {
		this.column = column;
	}

	/** {@inheritDoc} */
	@Override
	public final String getText(Object element) {
		IResultTree node = (IResultTree) element;
		if (node.getElement().size() > column) {
			return (String) node.getElement().get(column);
		} else {
			return ""; //$NON-NLS-1$
		}
	}
}
