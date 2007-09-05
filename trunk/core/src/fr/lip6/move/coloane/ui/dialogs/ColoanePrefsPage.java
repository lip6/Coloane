package fr.lip6.move.coloane.ui.dialogs;

//import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.main.Coloane;

import java.io.IOException;
import java.net.InetAddress;
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
	private Combo comboServer = null;
	private Text framekitIp = null;
	private Text framekitPort = null;
	private Label framekitIpLabel = null;
	private Label framekitPortLabel = null;
	private Group connection = null;

	private String server;
	private String ip;
	private String port;

	/** Limit size for text field */
	public static final int TXT_LIMIT = 255;
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

		// Connection group
		connection = new Group(composite, SWT.NONE);
		connection.setText(Messages.AuthenticationDialog_0);
		connection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		connection.setLayout(layout);

		// Set login field from the preference store
		new Label(connection, SWT.NULL).setText(Messages.AuthenticationDialog_8);
		loginField = new Text(connection, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		loginField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		loginField.setText(Coloane.getDefault().getPreference("LOGIN")); //$NON-NLS-1$
		loginField.setTextLimit(TXT_LIMIT);

		new Label(connection, SWT.NULL).setText(Messages.AuthenticationDialog_10);

		comboServer = new Combo(connection, SWT.NULL);
		comboServer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		//Recuperation des valeurs dans le fichier plugin.properties et les inserer dans la combo
		int nbservers = Integer.parseInt(Coloane.getParam("NB_SERVERS")); //$NON-NLS-1$
		String[] serversList = new String[nbservers + 2];
		int i = 0;
		while (i < nbservers) {
			serversList[i] = Coloane.getParam("NAME" + (i + 1)); //$NON-NLS-1$
			i++;
		}
		serversList[i] = Messages.AuthenticationDialog_13;
		serversList[i + 1] = Messages.AuthenticationDialog_14;

		// Set the server list
		comboServer.setItems(serversList);
		comboServer.setText(Coloane.getDefault().getPreference("SERVER"));

		comboServer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {

					int i = 0;
					while (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))	&& !comboServer.getText().equals(Coloane.getParam("NAME" + (i + 1)))) { //$NON-NLS-1$ //$NON-NLS-2$
						i++;
					}

					if (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))) { //$NON-NLS-1$
						server = Coloane.getParam("NAME" + (i + 1));
						ip = Coloane.getParam("IP" + (i + 1)); //$NON-NLS-1$
						port = Coloane.getParam("PORT" + (i + 1)); //$NON-NLS-1$

						framekitIp.setEnabled(false);
						framekitPort.setEnabled(false);
					} else if (comboServer.getText().equals(Messages.AuthenticationDialog_13)) { //$NON-NLS-1$
						server = Messages.AuthenticationDialog_13;
						ip = InetAddress.getByName(Messages.AuthenticationDialog_21).getHostAddress();
						port = String.valueOf(Coloane.getParam("PORT_DEFAUT")); //$NON-NLS-1$

						framekitIp.setEnabled(false);
						framekitPort.setEnabled(false);
					} else { // Autre ..
						server = Messages.AuthenticationDialog_14;
						framekitIp.setEnabled(true);
						framekitPort.setEnabled(true);

						ip = "";
						port = "";
					}
					framekitIp.setText(ip);
					framekitPort.setText(port);
				} catch (IOException ef) {
					Coloane.getLogger().warning("IP introuvable"); //$NON-NLS-1$
				}
			}
		});

		// Framekit IP/Port details
		framekitIpLabel = new Label(connection, SWT.NULL);
		framekitIpLabel.setText(Messages.AuthenticationDialog_26);

		framekitIp = new Text(connection, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitIp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitIp.setText(Coloane.getDefault().getPreference("IP"));
		framekitIp.setTextLimit(TXT_LIMIT);

		framekitPortLabel = new Label(connection, SWT.NULL);
		framekitPortLabel.setText(Messages.AuthenticationDialog_27);

		framekitPort = new Text(connection, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		framekitPort.setText(Coloane.getDefault().getPreference("PORT"));
		framekitPort.setTextLimit(TXT_LIMIT);

		// Enable Ip/Port fields if "Other..." is selected
		enableFields();

		// Dimitri
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
		Coloane.getDefault().setDefaultPreference("SERVER");
		Coloane.getDefault().setDefaultPreference("IP");
		Coloane.getDefault().setDefaultPreference("PORT");

		loginField.setText(Coloane.getDefault().getPreference("LOGIN"));
		framekitIp.setText(Coloane.getDefault().getPreference("IP"));
		framekitPort.setText(Coloane.getDefault().getPreference("PORT"));
	}


	/**
	 * Method declared on IPreferencePage. Save the
	 * author name to the preference store.
	 */
	public final boolean performOk() {
		Coloane.getDefault().setPreference("LOGIN", loginField.getText());
		Coloane.getDefault().setPreference("SERVER", server);
		Coloane.getDefault().setPreference("IP", framekitIp.getText());
		Coloane.getDefault().setPreference("PORT", framekitPort.getText());
		return super.performOk();
	}

	/**
	 * Enable or Disable IP/Port fields in preference page
	 */
	public final void enableFields() {
//		 Enable Ip/Port fields if "Other..." is selected
		if (Coloane.getDefault().getPreference("SERVER").equals(Messages.AuthenticationDialog_14)) {
			framekitIp.setEnabled(true);
			framekitPort.setEnabled(true);
		} else {
			framekitIp.setEnabled(false);
			framekitPort.setEnabled(false);
		}
	}
}
