package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.commands.properties.ChangeAttributeCmd;
import fr.lip6.move.coloane.core.ui.properties.LabelText;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class AttributeSection extends AbstractSection<IAttribute> {

	private LabelText lt;

	private ModifyListener listener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			Text widget = (Text) e.widget;
			try {
			if (!isDisposed() && !widget.getText().equals(getElement().getValue())) {
				getCommandStack().execute(new ChangeAttributeCmd(getElement(), lt.getText()));
			}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	};

	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);

		lt = new LabelText(
				composite,
				getWidgetFactory(),
				Messages.AttributeSection_0,
				"", //$NON-NLS-1$
				SWT.MULTI);
		lt.getTextWidget().addModifyListener(listener);
	}

	@Override
	public final void refresh() {
		if (!isDisposed() && !lt.getText().equals(getElement().getValue())) {
			lt.getTextWidget().setText(getElement().getValue());
			lt.redraw();
		}
	}

	public final void propertyChange(PropertyChangeEvent evt) {
		if (IAttribute.VALUE_PROP.equals(evt.getPropertyName())) {
			refresh();
		}
	}
}
