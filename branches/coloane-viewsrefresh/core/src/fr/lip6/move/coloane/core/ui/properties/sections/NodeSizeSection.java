package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.commands.properties.NodeChangeSizeCmd;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.properties.LabelText;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class NodeSizeSection extends AbstractSection<INodeImpl> {
	private static final int INCREMENT_VALUE = 10;

	private Spinner size;

	private ModifyListener listener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			if (size.getSelection() != getElement().getGraphicInfo().getZoom()) {
				getCommandStack().execute(new NodeChangeSizeCmd(getElement(), size.getSelection()));
			}
		}
	};

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
		size.setMinimum(80);
		size.setMaximum(400);
		size.setIncrement(INCREMENT_VALUE);
		size.addModifyListener(listener);

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
			size.setSelection(getElement().getGraphicInfo().getZoom());
			size.layout();
		}
	}

	@Override
	public final void propertyChange(PropertyChangeEvent evt) {
		if (INodeImpl.RESIZE_PROP.equals(evt.getPropertyName())) {
			refresh();
		}
	}
}
