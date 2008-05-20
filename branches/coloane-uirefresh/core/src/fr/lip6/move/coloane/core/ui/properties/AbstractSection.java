package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @param <T> représente le type du model
 */
public abstract class AbstractSection<T> extends AbstractPropertySection {
	/** Element courrant */
	private T element;
	/** Composite père de toutes les propriétés. */
	private Composite composite;

	/** Structure sauvegardant les listes de propriétés. */
	private HashMap<String, List<LabelText>> map = new HashMap<String, List<LabelText>>();
	/** Nom de la propriété courrante. */
	private String currentType;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		EditPart editPart = (EditPart) ((IStructuredSelection) getSelection())
				.getFirstElement();
		element = (T) editPart.getModel();
	}

	/**
	 * @return le modèle de l'élément séléctionné
	 */
	public final T getElement() {
		return element;
	}

	/**
	 * Change l'état (visible ou non) des propriétés du type nodeType
	 *
	 * @param visible
	 * @param nodeType
	 */
	private void setVisible(boolean visible, String nodeType) {
		for (LabelText lt : map.get(nodeType)) {
			lt.setVisible(visible);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public final void createControls(final Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		composite = getWidgetFactory().createFlatFormComposite(parent);
	}

	/**
	 * Rafraichissement de la "Section"
	 */
	public final void redraw() {
		Composite tmp = composite;
		for (int i = 0; i < 3 && tmp != null; i++) {
			tmp.layout();
			tmp.redraw();
			tmp = tmp.getParent();
		}
	}

	/**
	 * @param isMulti
	 * @return
	 */
	private int getSWTStyle(boolean isMulti) {
		if (isMulti) {
			return SWT.MULTI;
		} else {
			return SWT.SINGLE;
		}
	}

	/**
	 * Affichage/Création des propriétés <i>attributes</i> du type <i>nodeType</i>
	 *
	 * @param nodeType
	 * @param attributes
	 */
	public final void refreshControls(String nodeType,
			List<AttributeFormalism> attributes) {
		List<LabelText> list = map.get(nodeType);

		if (currentType != null && !currentType.equals(nodeType)) {
			setVisible(false, currentType);
			redraw();
		}
		if (list != null && !nodeType.equals(currentType)) {
			setVisible(true, nodeType);
			redraw();
		}
		if (list == null) {
			list = new ArrayList<LabelText>();
			LabelTextFactory factory = new LabelTextFactory(composite, getWidgetFactory());

			for (AttributeFormalism attr : attributes) {
				LabelText lt = factory.create(
						attr.getName(),
						attr.getDefaultValue(),
						getSWTStyle(attr.isMultiLines()));
				lt.getParent().redraw();
				list.add(lt);
			}
			map.put(nodeType, list);
			redraw();
		}
		currentType = nodeType;
	}

	public final HashMap<String, List<LabelText>> getMap() {
		return map;
	}

	public final String getCurrentType() {
		return currentType;
	}
}
