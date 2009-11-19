package fr.lip6.move.coloane.its.conceptsui;

import fr.lip6.move.coloane.its.Concept;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * provide required labels of a concept
 * @author Yann
 *
 */
public final class ConceptLabelProvider extends LabelProvider implements
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
		Concept concept =  (Concept) element;
		switch (columnIndex) {
		case 0 :
			return concept.getName();
		case 1 :
			if (concept.getEffective() != null) {
				return concept.getEffective().getTypeName();
			} else {
				return "";
			}
		default :
			throw new RuntimeException("Column index out of bounds in ConceptLabelProvider.");
		}

	}

}
