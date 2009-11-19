package its.labelsui;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A crazy class
 * TODO: Remove this
 * @author Yann
 *
 */
public final class LabelsLabelProvider extends LabelProvider implements
ITableLabelProvider {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// do stuff if you want nice icons in the table
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		return (String) element;
	}


}
