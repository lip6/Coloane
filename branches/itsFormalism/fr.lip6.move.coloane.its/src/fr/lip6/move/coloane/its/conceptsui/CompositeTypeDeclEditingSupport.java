package fr.lip6.move.coloane.its.conceptsui;

import fr.lip6.move.coloane.its.Concept;
import fr.lip6.move.coloane.its.TypeDeclaration;
import fr.lip6.move.coloane.its.TypeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

/**
 * Editing support for tabular editor: suggest concepts.
 * @author Yann
 *
 */
public final class CompositeTypeDeclEditingSupport extends EditingSupport {
	private TypeList tl;
	private TableViewer viewer;
	private int tlsize;
	private Map<Concept, ComboBoxCellEditor> editors;

	/**
	 * Constructor
	 * @param viewer the related viewer
	 * @param tl the types
	 */
	public CompositeTypeDeclEditingSupport(TableViewer viewer, TypeList tl) {
		super(viewer);
		this.viewer = viewer;
		this.tl = tl;
		tlsize = tl.size();
		editors = new HashMap<Concept, ComboBoxCellEditor>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		Concept concept =  (Concept) element;
		ComboBoxCellEditor editor = editors.get(concept);
		if (editor == null || tlsize < tl.size()) {
			tlsize = tl.size();
			String[] suggs = getSuggestions(concept);
			editor = new ComboBoxCellEditor(viewer.getTable(), suggs);
		}
		return editor;
	}

	/**
	 * Return suggestion list to fill the combo with.
	 * @param concept the concept to satisfy
	 * @return the available matching type names
	 */
	private String[] getSuggestions(Concept concept) {
		// build suggestion list
		List<String> req = concept.getLabels();
		List<String> suggestions = new ArrayList<String>();
		for (TypeDeclaration type : tl) {
			if (type == viewer.getInput()) {
				continue;
			}
			if (type.getLabels().containsAll(req)) {
				suggestions.add(type.getTypeName());
			}
		}
		return suggestions.toArray(new String[suggestions.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object getValue(Object element) {
		Concept concept =  (Concept) element;
		TypeDeclaration type = concept.getEffective();
		if (type == null) {
			return -1;
		} else {
			Object val = ((ComboBoxCellEditor) getCellEditor(concept)).getValue();
			if (val != null) {
				return val;
			}
			//			String[] suggs = getItems();
			//			for (int i = 0; i < suggs.length; i++) {
			//				if (suggs[i].equals(type.getTypeName()))
			//					return i;
			//			}
			return -1;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setValue(Object element, Object value) {
		Concept concept =  (Concept) element;
		Integer n = (Integer) value;
		if (n == -1) {
			return;
		}
		String[] suggs = ((ComboBoxCellEditor) getCellEditor(concept)).getItems();

		for (TypeDeclaration type : tl) {
			if (type.getTypeName().equals(suggs[n])) {
				concept.setEffective(type);
				break;
			}
		}
		getViewer().update(element, null);
		getViewer().refresh();
	}

}
