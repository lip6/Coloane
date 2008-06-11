package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.commands.properties.NodeChangeBackgroundCmd;
import fr.lip6.move.coloane.core.ui.commands.properties.NodeChangeForegroundCmd;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.properties.LabelText;

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
 * Section qui permet de gérer les propriétés graphiques d'un noeud :
 * <li>Couleur du noeud</li>
 * <li>Couleur de l'arrière plan du noeud</li>
 */
public class NodeColorSection extends AbstractSection<INodeImpl> {
	/** Editeur de couleur pour la couleur du noeud */
	private ColorFieldEditor fg;
	/** Permet de mettre à jour le modèle du noeud */
	private IPropertyChangeListener fgListener = new IPropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent event) {
			getCommandStack().execute(new NodeChangeForegroundCmd(
					getElement(),
					new Color(
							fg.getColorSelector().getButton().getDisplay(),
							fg.getColorSelector().getColorValue())
			));
		}
	};

	/** Editeur de couleur pour la couleur de fond du noeud */
	private ColorFieldEditor bg;
	/** Permet de mettre à jour le modèle du noeud pour la couleur du fond */
	private IPropertyChangeListener bgListener = new IPropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent event) {
			getCommandStack().execute(new NodeChangeBackgroundCmd(
					getElement(),
					new Color(
							bg.getColorSelector().getButton().getDisplay(),
							bg.getColorSelector().getColorValue())
			));
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);

		FormData data;

		// Editeur pour la couleur du noeud
		fg = createColorFieldEditor(composite);
		fg.setPropertyChangeListener(fgListener);
		Control fgControl = fg.getColorSelector().getButton().getParent();
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH);
		data.right = new FormAttachment(100, -5);
		fgControl.setLayoutData(data);

		// Etiquette : "Foreground"
		CLabel label = getWidgetFactory().createCLabel(composite, Messages.NodeColorSection_0 + " :"); //$NON-NLS-1$
		data = new FormData();
		data.bottom = new FormAttachment(fgControl, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);

		// Editeur pour la couleur du fond du noeud
		bg = createColorFieldEditor(composite);
		bg.setPropertyChangeListener(bgListener);
		Control bgControl = bg.getColorSelector().getButton().getParent();
		data = new FormData();
		data.top = new FormAttachment(fgControl, 0);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH);
		data.right = new FormAttachment(100, -5);
		bgControl.setLayoutData(data);

		// Etiquette : "Background"
		label = getWidgetFactory().createCLabel(composite, Messages.NodeColorSection_1 + " :"); //$NON-NLS-1$
		data = new FormData();
		data.bottom = new FormAttachment(bgControl, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);
	}


	/**
	 * Création d'un ColorFieldEditor dans un composite car le ColorFieldEditor a besoin d'un GridLayout.
	 * @param parent
	 * @return le ColorFieldEditor, le composite peut être récupéré en faisant un
	 * cfe.getColorSelector().getButton().getParent()
	 */
	private ColorFieldEditor createColorFieldEditor(Composite parent) {
		Composite composite = getWidgetFactory().createComposite(parent);
		composite.setLayout(new GridLayout(3, false));
		ColorFieldEditor cfe = new ColorFieldEditor("", "", composite); //$NON-NLS-1$ //$NON-NLS-2$
		return cfe;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			fg.getColorSelector().setColorValue(getElement().getGraphicInfo().getForeground().getRGB());
			bg.getColorSelector().setColorValue(getElement().getGraphicInfo().getBackground().getRGB());
		}
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public final void propertyChange(java.beans.PropertyChangeEvent evt) {
		if (INodeImpl.FOREGROUND_COLOR_PROP.equals(evt.getPropertyName())) {
			refresh();
		} else if (INodeImpl.BACKGROUND_COLOR_PROP.equals(evt.getPropertyName())) {
			refresh();
		}
	}
}
