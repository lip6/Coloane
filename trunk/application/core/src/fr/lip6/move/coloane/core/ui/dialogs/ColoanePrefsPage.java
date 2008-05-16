package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.core.main.Coloane;

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

public class ColoanePrefsPage extends PreferencePage implements IWorkbenchPreferencePage {

	private Combo combo = null;
	private Combo comboServer = null;
	private Text framekitIp = null;
	private Text framekitPort = null;
	private Label framekitIpLabel = null;
	private Label framekitPortLabel = null;
	private Group connection = null;

	private String ip;
	private String port;

	/** Limit size for text field */
	public static final int TXT_LIMIT = 255;
	// Text fields for user to enter preferences
	private Text loginField;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getDefault().getPreferenceStore());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected final Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;

		// Groupe authentification
		connection = new Group(composite, SWT.NONE);
		connection.setText(Messages.ColoanePrefsPage_4);
		connection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		connection.setLayout(layout);

		// Choix du login par defaut
		new Label(connection, SWT.NULL).setText(Messages.AuthenticationDialog_8);
		loginField = new Text(connection, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		loginField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		loginField.setTextLimit(TXT_LIMIT);

		// Combo List pour le choix du serveur
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

		// Mise en place de la liste des serveurs recupŽrŽs
		comboServer.setItems(serversList);
		comboServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Dans le cas localhost
					if (comboServer.getText().equals(Messages.AuthenticationDialog_13)) { //$NON-NLS-1$
						framekitIp.setEnabled(false);
						framekitPort.setEnabled(false);
						ip = InetAddress.getByName("localhost").getHostAddress();
						port = String.valueOf(Coloane.getParam("PORT_DEFAULT")); //$NON-NLS-1$

					// Dans le cas Autres...
					} else if (comboServer.getText().equals(Messages.AuthenticationDialog_14)) { // Autre ..
						framekitIp.setEnabled(true);
						framekitPort.setEnabled(true);
						ip = ""; //$NON-NLS-1$
						port = ""; //$NON-NLS-1$
					} else { //$NON-NLS-1$
						int indexServer = comboServer.indexOf(comboServer.getText());
						ip = Coloane.getParam("IP" + (indexServer + 1)); //$NON-NLS-1$
						port = Coloane.getParam("PORT" + (indexServer + 1)); //$NON-NLS-1$
						framekitIp.setEnabled(false);
						framekitPort.setEnabled(false);
					}
					framekitIp.setText(ip);
					framekitPort.setText(port);
				} catch (IOException ef) {
					Coloane.getLogger().warning("IP introuvable"); //$NON-NLS-1$
				}
			}
		});

		// Details des parametres serveur
		framekitIpLabel = new Label(connection, SWT.NULL);
		framekitIpLabel.setText(Messages.AuthenticationDialog_26);
		framekitIp = new Text(connection, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitIp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitIp.setTextLimit(TXT_LIMIT);

		framekitPortLabel = new Label(connection, SWT.NULL);
		framekitPortLabel.setText(Messages.AuthenticationDialog_27);
		framekitPort = new Text(connection, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitPort.setTextLimit(TXT_LIMIT);

		// Enable Ip/Port fields if "Other..." is selected
		enableFields();

		// Groupe pour le log
		Group p = new Group(composite, SWT.NONE);
		p.setText(Messages.ColoanePrefsPage_5);
		p.setLayoutData(data);
		p.setLayout(layout);

		(new Label(p, SWT.NULL)).setText(Messages.ColoanePrefsPage_6);
		combo = new Combo(p, SWT.NULL);
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String levelList[] = {"NORMAL", "BETA", "DEBUG"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		combo.setItems(levelList);
		combo.setText(Messages.ColoanePrefsPage_10);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo.getText().equals("NORMAL")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.INFO);
				} else if (combo.getText().equals("BETA")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.FINE);
				} else if (combo.getText().equals("DEBUG")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.ALL);
				}
			}
		});
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected final void performDefaults() {
		Coloane.getDefault().setDefaultPreference();
		loginField.setText("");   //$NON-NLS-1$
		framekitIp.setText("");   //$NON-NLS-1$
		framekitPort.setText(""); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public final boolean performOk() {
		Coloane.getDefault().setPreference("LOGIN_DEFAULT", loginField.getText()); //$NON-NLS-1$
		Coloane.getDefault().setPreference("SERVER_DEFAULT", comboServer.getText()); //$NON-NLS-1$
		Coloane.getDefault().setPreference("IP_DEFAULT", framekitIp.getText()); //$NON-NLS-1$
		Coloane.getDefault().setPreference("PORT_DEFAULT", framekitPort.getText()); //$NON-NLS-1$
		return super.performOk();
	}

	/**
	 * Enable or Disable IP/Port fields in preference page
	 */
	public final void enableFields() {
		// Enable Ip/Port fields if "Other..." is selected
		if (Coloane.getDefault().getPreference("SERVER").equals(Messages.AuthenticationDialog_14)) { //$NON-NLS-1$
			framekitIp.setEnabled(true);
			framekitPort.setEnabled(true);
		} else {
			framekitIp.setEnabled(false);
			framekitPort.setEnabled(false);
		}
	}
}
