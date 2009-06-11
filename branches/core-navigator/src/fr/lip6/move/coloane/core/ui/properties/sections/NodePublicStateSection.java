package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.commands.properties.NodeChangePublicCmd;
import fr.lip6.move.coloane.core.ui.properties.LabelText;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Section in property view to display if a node is public.
 *
 * @author Clément Démoulins
 */
public class NodePublicStateSection extends AbstractSection<INode> {

	private Button checkBox;
	private Listener listener = new Listener() {

		/** {@inheritDoc} */
		public void handleEvent(Event event) {
			CompoundCommand cc = new CompoundCommand();
			for (INode node : getElements()) {
				cc.add(new NodeChangePublicCmd(node, ((Button) event.widget).getSelection()));
			}
			getCommandStack().execute(cc);
		}

	};

	/** {@inheritDoc} */
	@Override
	public final void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;

		checkBox = getWidgetFactory().createButton(composite, null, SWT.CHECK);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH + 7);
		checkBox.setLayoutData(data);
		checkBox.addListener(SWT.Selection, listener);

		// Etiquette
		CLabel label = getWidgetFactory().createCLabel(composite, "Public :"); //$NON-NLS-1$
		data = new FormData();
		data.top = new FormAttachment(checkBox, 0, SWT.TOP);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);
	}

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		if (getElements().size() == 1) {
			checkBox.setSelection(getElements().get(0).isPublic());
		} else {
			checkBox.setSelection(false);
		}
		checkBox.redraw();
	}

	/** {@inheritDoc} */
	public void propertyChange(PropertyChangeEvent evt) { }
}
