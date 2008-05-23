package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @param <T> représente le type du model
 */
public abstract class AbstractSection<T extends IElement> extends AbstractPropertySection {
	/** Element courrant */
	private T element;
	/** Composite père de toutes les propriétés. */
	private Composite composite;

	/** Structure sauvegardant les listes de propriétés. */
	private HashMap<String, List<LabelText>> map = new HashMap<String, List<LabelText>>();
	/** Nom de la propriété courrante. */
	private String currentType;

	/** Listener qui va modifier le modèle */
	private ModifyListener listener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			Text text = (Text) e.widget;

			// Recherche du LabelText modifié
			for (LabelText lt : getMap().get(getCurrentType())) {
				if (lt.getTextWidget() == text) {

					// Recherche de l'attribut modifié
					IAttributeImpl attr = getElement().getProperties().get(lt.getId());
					if (attr != null && !attr.getValue().equals(lt.getText())) {
						System.err.println("avant modif");
						attr.setValue(attr.getValue(), lt.getText());
						System.err.println("après modif");
						break;
					}
				}
			}
		}
	};

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

		EditPart editPart = (EditPart) ((IStructuredSelection) getSelection()).getFirstElement();
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
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		composite = getWidgetFactory().createFlatFormComposite(parent);
	}

	private ScrolledComposite sc;
	/**
	 * Rafraichissement de la "Section"
	 */
	public final void redraw() {
		// Récupération du ScrolledComposite
		if (sc == null) {
			Composite tmp = composite;
			while (!(tmp instanceof ScrolledComposite)) {
				tmp = tmp.getParent();
			}
			sc = (ScrolledComposite) tmp;
		}
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		Composite tmp = composite;
		for (int i = 0; i < 20 && tmp != null; i++) {
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
	protected final void refreshControls(String nodeType,
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
						attr.getOrder(),
						attr.getName(),
						attr.getDefaultValue(),
						getSWTStyle(attr.isMultiLines()));
				lt.getParent().redraw();
				lt.getTextWidget().addModifyListener(listener);
				list.add(lt);
			}
			map.put(nodeType, list);
			redraw();
		}
		currentType = nodeType;
	}

	protected final void refreshContent() {
		for (LabelText lt : getMap().get(getCurrentType())) {
			IAttributeImpl attr = getElement().getProperties().get(lt.getId());
			if (attr != null) {
				String newValue = attr.getValue();
				if (!lt.getText().equals(newValue)) {
					lt.setText(attr.getValue());
					lt.redraw();
				}
			}
		}
	}

	public final HashMap<String, List<LabelText>> getMap() {
		return map;
	}

	public final String getCurrentType() {
		return currentType;
	}
}
