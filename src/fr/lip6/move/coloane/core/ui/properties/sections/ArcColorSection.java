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

import fr.lip6.move.coloane.core.ui.commands.properties.ArcChangeColorCmd;
import fr.lip6.move.coloane.core.ui.properties.IAttributeLabel;
import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Section qui permet de gérer les propriétés graphiques d'un arc :
 * <li>Couleur de l'arc</li>
 */
public class ArcColorSection extends AbstractSection<IArc> {
	/** Editeur de couleur */
	private ColorFieldEditor color;

	/** Permet de mettre à jour le modèle d'arc */
	private IPropertyChangeListener listener = new IPropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			CompoundCommand cc = new CompoundCommand();
			for (IArc arc : getElements()) {
				cc.add(new ArcChangeColorCmd(
						arc,
						new Color(
								color.getColorSelector().getButton().getDisplay(),
								color.getColorSelector().getColorValue())
				));
			}
			getCommandStack().execute(cc);
		}
	};

	/** {@inheritDoc} */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);

		FormData data;

		// Editeur de couleur
		color = createColorFieldEditor(composite);
		color.setPropertyChangeListener(listener);
		Control fgControl = color.getColorSelector().getButton().getParent();
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, IAttributeLabel.LABEL_WIDTH);
		data.right = new FormAttachment(100, -5);
		fgControl.setLayoutData(data);

		// Etiquette
		CLabel label = getWidgetFactory().createCLabel(composite, Messages.ArcColorSection_0 + " :"); //$NON-NLS-1$
		data = new FormData();
		data.bottom = new FormAttachment(fgControl, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, IAttributeLabel.LABEL_WIDTH);
		label.setLayoutData(data);
	}

	/**
	 * Création d'un ColorFieldEditor dans un composite car le ColorFieldEditor a besoin d'un GridLayout.
	 * @param parent parent utilisé pour la construction
	 * @return le ColorFieldEditor, le composite peut être récupéré en faisant un
	 * cfe.getColorSelector().getButton().getParent()
	 */
	private ColorFieldEditor createColorFieldEditor(Composite parent) {
		Composite composite = getWidgetFactory().createComposite(parent);
		composite.setLayout(new GridLayout(3, false));
		ColorFieldEditor cfe = new ColorFieldEditor("", "", composite); //$NON-NLS-1$ //$NON-NLS-2$
		return cfe;
	}

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			color.getColorSelector().setColorValue(getElements().get(0).getGraphicInfo().getColor().getRGB());
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void propertyChange(java.beans.PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (IArc.COLOR_PROP.equals(prop)) {
			refresh();
		}
	}
}
