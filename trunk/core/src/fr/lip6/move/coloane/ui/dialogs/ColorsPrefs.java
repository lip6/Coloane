package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.main.Coloane;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;



import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ColorsPrefs extends PreferencePage implements
		IWorkbenchPreferencePage {

	private ColorFieldEditor colorEditor;

	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getDefault().getPreferenceStore());
	}

	protected final Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		
		
		// Create a data that takes up the extra space in the dialog .
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);
		
		
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		
		
		Composite colorComposite = new Composite(composite, SWT.NONE);

		colorComposite.setLayout(new GridLayout());

		// Create a data that takes up the extra space in the dialog.
		colorComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		colorEditor = new ColorFieldEditor("COLOR1", "Color1", colorComposite);

		// Set the editor up to use this page
		colorEditor.setPreferenceStore(getPreferenceStore());
		colorEditor.load();

		
		return composite;
	}
}
