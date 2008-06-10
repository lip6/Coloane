package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.properties.LabelText;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class NodeSizeSection extends AbstractSection<INodeImpl> {

	private Spinner size;

	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;

		size = new Spinner(composite, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH + 7);
		size.setLayoutData(data);
		size.setMinimum(0);
		size.setMaximum(Integer.MAX_VALUE);
//		size.addModifyListener(new SpinnerModifyListener(0));

		CLabel label = getWidgetFactory().createCLabel(composite, Messages.NodeSizeSection_0 + " :"); //$NON-NLS-1$
		data = new FormData();
		data.bottom = new FormAttachment(size, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);
	}

	@Override
	public final void refresh() {
		if (!isDisposed()) {
			int newValue = 0;
			size.setSelection(newValue);
		}
	}

	@Override
	public final void propertyChange(PropertyChangeEvent evt) {
		if (INodeImpl.ZOOM_PROP.equals(evt.getSource())) {
			refresh();
		}
	}
}
