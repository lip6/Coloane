package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.properties.LabelText;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Section qui permet de gérer les propriétés graphiques d'un noeud :
 * <li>Couleur du noeud</li>
 * <li>Couleur de l'arrière plan du noeud</li>
 */
public class NodeColorSection extends AbstractPropertySection {
	private ColorFieldEditor fg;
	private IPropertyChangeListener fgListener = new IPropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			Object editPart = getElement();
			if (editPart instanceof AbstractGraphicalEditPart) {
				AbstractGraphicalEditPart eep = (AbstractGraphicalEditPart) editPart;
				eep.getFigure().setForegroundColor(new Color(
						fg.getColorSelector().getButton().getDisplay(),
						fg.getColorSelector().getColorValue()));
			}
		}
	};

	private ColorFieldEditor bg;
	private IPropertyChangeListener bgListener = new IPropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			Object editPart = getElement();
			if (editPart instanceof AbstractGraphicalEditPart) {
				AbstractGraphicalEditPart eep = (AbstractGraphicalEditPart) editPart;
				eep.getFigure().setBackgroundColor(new Color(
						bg.getColorSelector().getButton().getDisplay(),
						bg.getColorSelector().getColorValue()));
			}
		}
	};

	private Object element;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);

		FormData data;

		// Foreground
		fg = createColorFieldEditor(composite);
		fg.setPropertyChangeListener(fgListener);
		Control fgControl = fg.getColorSelector().getButton().getParent();
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH);
		data.right = new FormAttachment(100, -5);
		fgControl.setLayoutData(data);

		CLabel label = getWidgetFactory().createCLabel(composite, "Foreground :");
		data = new FormData();
		data.bottom = new FormAttachment(fgControl, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);

		// Background
		bg = createColorFieldEditor(composite);
		bg.setPropertyChangeListener(bgListener);
		Control bgControl = bg.getColorSelector().getButton().getParent();
		data = new FormData();
		data.top = new FormAttachment(fgControl, 0);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH);
		data.right = new FormAttachment(100, -5);
		bgControl.setLayoutData(data);

		label = getWidgetFactory().createCLabel(composite, "Background :");
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
		ColorFieldEditor cfe = new ColorFieldEditor("", "", composite);
		return cfe;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public final void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		element = ((IStructuredSelection) getSelection()).getFirstElement();
	}

	/**
	 * @return l'élément séléctionné
	 */
	public final Object getElement() {
		return element;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public final void refresh() {
		Object editPart = getElement();
		if (editPart instanceof AbstractGraphicalEditPart) {
			AbstractGraphicalEditPart eep = (AbstractGraphicalEditPart) editPart;
			fg.getColorSelector().setColorValue(eep.getFigure().getForegroundColor().getRGB());
			bg.getColorSelector().setColorValue(eep.getFigure().getBackgroundColor().getRGB());
		}
	}
}
