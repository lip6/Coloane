package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.main.Coloane;

//import org.eclipse.core.runtime.Platform;
//import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;




public class ColoanePrefsPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	// Names for preferences
	//private static final String LOGIN = Coloane.getDefault().getPreference(Platform.getResourceBundle(Coloane.getDefault().getBundle()).getString("LOGIN"));

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

		// Get the preference store
		//IPreferenceStore preferenceStore = getPreferenceStore();

		// Create three text fields.
		// Set the text in each from the preference store
		new Label(composite, SWT.NULL).setText(Messages.AuthenticationDialog_8);
		loginField = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		loginField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		loginField.setText(Coloane.getDefault().getPreference("LOGIN"));

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
