package fr.lip6.move.coloane.ui.dialogs;

//import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.main.Coloane;

import java.util.logging.Level;

import org.eclipse.jface.preference.PreferencePage;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ColoanePrefsPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Combo combo = null;

	// Text fields for user to enter preferences
	private Text loginField;

	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getDefault().getPreferenceStore());
	}

	/**
	 * Creates the controls for this page
	 */
	protected final Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;

		// Set the text in each from the preference store
		new Label(composite, SWT.NULL).setText(Messages.AuthenticationDialog_8);
		loginField = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		loginField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		loginField.setText(Coloane.getDefault().getPreference("LOGIN"));

		Group p = new Group(composite, SWT.NONE);
		p.setText("Level");
		p.setLayoutData(data);
		p.setLayout(layout);

		(new Label(p, SWT.NULL)).setText("Niveau :");
		combo = new Combo(p, SWT.NULL);
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String levelList[] = {"NORMAL", "BETA", "DEBUG"};
		combo.setItems(levelList);
		combo.setText("Choisir le niveau");
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (combo.getText().equals("NORMAL")) {
					Coloane.setVerbosity(Level.INFO);
					//Api.setVerbosity(Level.INFO);
				} else if (combo.getText().equals("BETA")) {
					Coloane.setVerbosity(Level.FINE);
					//Api.setVerbosity(Level.FINE);
				} else if (combo.getText().equals("DEBUG")) {
					Coloane.setVerbosity(Level.FINEST);
					//Api.setVerbosity(Level.FINEST);
				}
			}
		});
		return composite;
	}

	/**
	 * Performs special processing when this page's Restore Defaults button has been pressed.
	 * Sets the contents of the nameEntry field to
	 * be the default
	 */
	protected final void performDefaults() {
		Coloane.getDefault().setDefaultPreference("LOGIN");
		loginField.setText(Coloane.getDefault().getPreference("LOGIN"));
	}
	/**
	 * Method declared on IPreferencePage. Save the
	 * author name to the preference store.
	 */
	public final boolean performOk() {
		Coloane.getDefault().setPreference("LOGIN", loginField.getText());
		return super.performOk();
	}
}
