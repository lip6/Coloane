package its.tpnui;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import fr.lip6.move.coloane.interfaces.model.INode;

public class TransitionLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	
	
	private static final int VISCOL = 0;
	private static final int LABELCOL = 1;
	private static final int EFTCOL = 2;
	private static final int LFTCOL = 3;

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// do stuff if you want nice icons in the table
		return null;
	}

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

	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}

	@Override
	public void dispose() {
		// nothing to do

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// default to true to ensure update on any change 
		return true;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
