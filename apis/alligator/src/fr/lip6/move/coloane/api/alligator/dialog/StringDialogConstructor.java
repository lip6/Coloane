package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.Item;

import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Clément Démoulins
 */
public class StringDialogConstructor implements ItemDialogConstructor {

	private Text input;
	private DescriptionItem description;

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#create(org.eclipse.swt.widgets.Composite, fr.lip6.move.alligator.interfaces.DescriptionItem)
	 */
	public final void create(Composite parent, DescriptionItem description) {
		this.description = description;

		Label label = new Label(parent, SWT.WRAP);
		label.setText(description.getName() + ":");
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));

		this.input = new Text(parent, SWT.BORDER | SWT.SINGLE);
		this.input.setText(description.getDefaultValue());
		this.input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#getParameters()
	 */
	public final List<Item> getParameters() {
		try {
			return Collections.singletonList(new Item(description.getType(), description.getName(), input.getText()));
		} finally {
			input.dispose();
		}
	}

}
