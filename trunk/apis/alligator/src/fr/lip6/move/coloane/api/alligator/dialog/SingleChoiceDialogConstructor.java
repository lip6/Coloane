package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.Item;

import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Clément Démoulins
 */
public class SingleChoiceDialogConstructor implements ItemDialogConstructor {

	private Combo combo;
	private DescriptionItem description;

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#create(org.eclipse.swt.widgets.Composite, fr.lip6.move.alligator.interfaces.DescriptionItem)
	 */
	public final void create(Composite parent, DescriptionItem description) {
		this.description = description;

		Label label = new Label(parent, SWT.WRAP);
		label.setText(description.getName() + ":");
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));

		this.combo = new Combo(parent, SWT.NONE);
		this.combo.setItems(description.getChoices().toArray(new String[0]));
		this.combo.select(Math.max(0, description.getChoices().indexOf(description.getDefaultValue())));
		this.combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#getParameters()
	 */
	public final List<Item> getParameters() {
		return Collections.singletonList(new Item(description.getType(), description.getName(), combo.getText()));
	}

}
