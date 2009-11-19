package fr.lip6.move.coloane.its.tpnui;

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A transition label provider for table view
 * @author Yann
 *
 */
public final class TransitionLabelProvider
	extends LabelProvider
	implements ITableLabelProvider {



	private static final int VISCOL = 0;
	private static final int LABELCOL = 1;
	private static final int EFTCOL = 2;
	private static final int LFTCOL = 3;

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
		INode node = (INode) element;

		switch (columnIndex) {
		case VISCOL :
			return node.getAttribute("visibility").getValue();
		case LABELCOL :
			return node.getAttribute("label").getValue();
		case EFTCOL :
			return node.getAttribute("earliestFiringTime").getValue();
		case LFTCOL :
			return node.getAttribute("latestFiringTime").getValue();
		default :
			throw new RuntimeException("Column index out of bounds in TypeLabelProvider.");
		}
	}

}
