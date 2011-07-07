/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.properties.sections;


import fr.lip6.move.coloane.core.ui.commands.properties.ChangeAttributeCmd;
import fr.lip6.move.coloane.core.ui.properties.IAttributeLabel;
import fr.lip6.move.coloane.core.ui.properties.LabelTextFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Classe abstraite qui permet d'afficher les propriétés d'un élément du model (IElement).
 * @param <T> représente le type d'élément à afficher.
 */
public abstract class AbstractElementSection<T extends IElement> extends AbstractSection<T> {

	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** Composite père de toutes les propriétés. */
	private Composite composite;

	/** Structure sauvegardant les listes de propriétés. */
	private Map<String, List<IAttributeLabel>> map = new HashMap<String, List<IAttributeLabel>>();

	/** Nom de la propriété courante. */
	private String currentType;

	/**
	 * ModifyListener that will update the model according to changes made in the
	 * properties window.
	 */
	class ModifyListenerImpl implements ModifyListener {

		private PropertyChangeListener pcl;

		/**
		 * Constructor to build a new modifylisterner implementation with a propertychangelistener
		 * @param pcl the listener that will be used to listen to property changes in new attributes
		 * built by the modifylistener
		 */
		ModifyListenerImpl(PropertyChangeListener pcl) {
			this.pcl = pcl;
		}

		/** {@inheritDoc} */
		public void modifyText(ModifyEvent e) {
			Control text = (Control) e.widget;

			// Recherche du LabelText modifié
			for (IAttributeLabel lt : getMap().get(getCurrentType())) {
				if (lt.getControlText() == text) {

					// Recherche de l'attribut modifié
					IAttribute attr = getElements().get(0).getAttribute(lt.getLabel());
					String newValue = lt.getText();
					// If the attribute did not exist yet, create it
					if (attr == null) {
						try {
							attr = getElements().get(0).getGraph().createAttribute(getElements().get(0), getElements().get(0).getElemFormalism(), lt.getLabel());
							attr.addPropertyChangeListener(pcl);
						} catch (ModelException e1) {
							//Should not pass here -- only attributes associated to labels are created
							//and labels are initialised according to the formalism... so only "correct"
							//attributes can be created.
							LOGGER.warning("Trying to build an attribute that is not correct according to the formalism. This should not have occured."); //$NON-NLS-1$
						}
					}
					if (!attr.getValue().equals(newValue)) {
						getCommandStack().execute(new ChangeAttributeCmd(attr, newValue));
					}
					break;
				}
			}
		}
	};
	
	/** Listener qui va modifier le modèle */
	private ModifyListener listener = new ModifyListenerImpl(this);

	/** ScrolledComposite gardé en mémoire pour le rafraichissement */
	private ScrolledComposite sc;

	/**
	 * Change l'état (visible ou non) des propriétés du type nodeType
	 * @param visible nouvelle état
	 * @param nodeType type de noeud concerné par le changement d'état
	 */
	private void setVisible(boolean visible, String nodeType) {
		for (IAttributeLabel lt : map.get(nodeType)) {
			lt.setVisible(visible);
		}
	}

	/** {@inheritDoc} */
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
		//sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		sc.setMinSize(200, 200);
		Composite tmp = composite;
		for (int i = 0; i < 20 && tmp != null; i++) {
			tmp.layout();
			tmp.redraw();
			tmp = tmp.getParent();
		}
	}

	/**
	 * @param isMulti <code>true</code> si on veut le style SWT multiligne
	 * @return le style SWT pour un élément simple ligne ou multiligne.
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
	 * @param nodeType type qui doit être afficher
	 * @param attributes attributs pour ce type de noeud
	 */
	protected final void refreshControls(String nodeType, List<IAttributeFormalism> attributes) {

		List<IAttributeLabel> list = map.get(nodeType);

		if (currentType != null && !currentType.equals(nodeType)) {
			setVisible(false, currentType);
			redraw();
		}
		if (list != null && !nodeType.equals(currentType)) {
			setVisible(true, nodeType);
			redraw();
		}
		if (list == null) {
			list = new ArrayList<IAttributeLabel>();
			LabelTextFactory factory = new LabelTextFactory(composite, getWidgetFactory());

			for (IAttributeFormalism attr : attributes) {
				IAttributeLabel lt;

				if (attr.isEnumerated()) {
					lt = factory.create(
							attr.getName(),
							attr.getDefaultValue(),
							attr.getEnumeration());
				} else if (attr.getInjector() != null) {
					//les editeurs sont toujours multilignes
					lt = factory.create(
							attr.getName(),
							attr.getDefaultValue(),
							attr.getInjector());
				} else {
					lt = factory.create(
						attr.getName(),
						attr.getDefaultValue(),
						getSWTStyle(attr.isMultiLine()));
				}

				lt.getParent().redraw();
				lt.addModifyListener(listener);
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
		for (IAttributeLabel lt : getMap().get(getCurrentType())) {
			IAttribute elem = getElements().get(0).getAttribute(lt.getLabel());
			if (elem == null) {
				continue;
			}
			String newValue = elem.getValue();
			if (!lt.getText().equals(newValue)) {
				lt.setText(newValue);
				lt.redraw();
			}
		}
	}


	/**
	 * @return map associant le nom d'une propriété avec un LabelText
	 */
	public final Map<String, List<IAttributeLabel>> getMap() {
		return map;
	}

	/**
	 * @return Nom de la propriété courrante
	 */
	public final String getCurrentType() {
		return currentType;
	}

	/** {@inheritDoc} */
	@Override
	public final void propertyChange(PropertyChangeEvent evt) {
		if (!isDisposed() && IAttribute.VALUE_PROP.equals(evt.getPropertyName())) {
			refreshContent();
		}
	}
}
