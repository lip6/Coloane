package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.core.ui.commands.properties.ChangeAttributeCmd;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IElement;
import fr.lip6.move.coloane.core.ui.properties.LabelText;
import fr.lip6.move.coloane.core.ui.properties.LabelTextFactory;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Classe abstraite qui permet d'afficher les propriétés d'un élément du model (IElement).
 * @param <T> représente le type d'élément à afficher.
 */
public abstract class AbstractElementSection<T extends IElement> extends AbstractSection<T> {
	/** Composite père de toutes les propriétés. */
	private Composite composite;

	/** Structure sauvegardant les listes de propriétés. */
	private HashMap<String, List<LabelText>> map = new HashMap<String, List<LabelText>>();

	/** Nom de la propriété courrante. */
	private String currentType;

	/** Listener qui va modifier le modèle */
	private ModifyListener listener = new ModifyListener() {
		/* (non-Javadoc)
		 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
		 */
		public void modifyText(ModifyEvent e) {
			Text text = (Text) e.widget;
			IAttributeImpl attr = null;

			// Recherche du LabelText modifié
			for (LabelText lt : getMap().get(getCurrentType())) {
				if (lt.getTextWidget() == text) {

					// Recherche de l'attribut modifié
					attr = getElement().getProperties().get(lt.getId());
					if (attr != null && !attr.getValue().equals(lt.getText())) {
						String newValue = lt.getText();
						getCommandStack().execute(new ChangeAttributeCmd(attr, newValue));
						break;
					}
				}
			}
		}
	};

	/** ScrolledComposite gardé en mémoire pour le rafraichissement */
	private ScrolledComposite sc;

	/**
	 * Change l'état (visible ou non) des propriétés du type nodeType
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
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		composite = getWidgetFactory().createFlatFormComposite(parent);
	}

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
	 * @return le style SWT pour un élément simple ligne ou multi ligne.
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
	protected final void refreshControls(String nodeType, List<AttributeFormalism> attributes) {
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

	/**
	 * Actualise la valeur des propriétés
	 */
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

	/**
	 * @return map associant le nom d'une propriété avec un LabelText
	 */
	public final HashMap<String, List<LabelText>> getMap() {
		return map;
	}

	/**
	 * @return Nom de la propriété courrante
	 */
	public final String getCurrentType() {
		return currentType;
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public final void propertyChange(PropertyChangeEvent evt) {
		if (IAttributeImpl.VALUE_PROP.equals(evt.getPropertyName())) {
			refreshContent();
		}
	}
}
