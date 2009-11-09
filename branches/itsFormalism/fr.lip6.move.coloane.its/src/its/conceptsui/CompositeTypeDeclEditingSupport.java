package its.conceptsui;

import its.Concept;
import its.TypeDeclaration;
import its.TypeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;


public class CompositeTypeDeclEditingSupport extends EditingSupport {
	private TypeList tl;
	private TableViewer viewer;
	private int tlsize;
	Map<Concept,ComboBoxCellEditor> editors;

	public CompositeTypeDeclEditingSupport(TableViewer viewer, TypeList tl ) {
		super(viewer);
		this.viewer = viewer;
		this.tl = tl;
		tlsize = tl.size();
		editors = new HashMap<Concept, ComboBoxCellEditor>();
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

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

	private String[] getSuggestions(Concept concept) {
		// build suggestion list
		List<String> req = concept.getLabels();
		ArrayList<String> suggestions = new ArrayList<String>();
		for (TypeDeclaration type : tl) {
			if (type == viewer.getInput())
				continue;
			if (type.getLabels().containsAll(req)) {
				suggestions.add(type.getTypeName());
			}
		}
		return (String[]) suggestions.toArray(new String[suggestions.size()]);
	}

	@Override
	protected Object getValue(Object element) {
		Concept concept =  (Concept) element;
		
		TypeDeclaration type = concept.getEffective();
		if (type==null) {
			return -1;
		} else {
			Object val = ((ComboBoxCellEditor)getCellEditor(concept)).getValue();
			if (val != null)
				return val;
//			String[] suggs = getItems();
//			for (int i = 0; i < suggs.length; i++) {
//				if (suggs[i].equals(type.getTypeName()))
//					return i;
//			}
			return -1;
		}
	}

	@Override
	protected void setValue(Object element, Object value) {
		Concept concept =  (Concept) element;
		Integer n = (Integer) value;
		if (n == -1)
			return;
		String[] suggs = ((ComboBoxCellEditor)getCellEditor(concept)).getItems();

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
