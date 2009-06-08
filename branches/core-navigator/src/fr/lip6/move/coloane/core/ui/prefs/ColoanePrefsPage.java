package fr.lip6.move.coloane.core.ui.prefs;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.dialogs.Messages;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

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

/**
 * Définition de la page de préférence dédiée à Coloane
 */
public class ColoanePrefsPage extends PreferencePage implements IWorkbenchPreferencePage {
	/** Limit size for text field */
	public static final int TXT_LIMIT = 255;

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private Combo combo = null;
	private Combo comboServer = null;
	private Text framekitIp = null;
	private Text framekitPort = null;
	private Text serverType = null;
	private Label framekitIpLabel = null;
	private Label framekitPortLabel = null;
	private Label serverTypeLabel = null;
	private Group connection = null;

	private String ip;
	private String port;
	private String type;

	// Text fields for user to enter preferences
	private Text loginField;


	/** {@inheritDoc} */
	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getInstance().getPreferenceStore());
	}

	/** {@inheritDoc} */
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
		loginField.setText(Coloane.getInstance().getPreference("LOGIN_DEFAULT")); //$NON-NLS-1$

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

		// Mise en place de la liste des serveurs recupérés
		comboServer.setItems(serversList);
		comboServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Dans le cas localhost
					if (comboServer.getText().equals(Messages.AuthenticationDialog_13)) {
						framekitIp.setEnabled(false);
						framekitPort.setEnabled(false);
						serverType.setEnabled(false);
						ip = InetAddress.getByName("localhost").getHostAddress(); //$NON-NLS-1$
						port = String.valueOf(Coloane.getParam("PORT_DEFAULT")); //$NON-NLS-1$
						type = String.valueOf(Coloane.getParam("TYPE_DEFAULT")); //$NON-NLS-1$

					// Dans le cas Autres...
					} else if (comboServer.getText().equals(Messages.AuthenticationDialog_14)) { // Autre ..
						framekitIp.setEnabled(true);
						framekitPort.setEnabled(true);
						serverType.setEnabled(true);
						ip = ""; //$NON-NLS-1$
						port = String.valueOf(Coloane.getParam("PORT_DEFAULT")); //$NON-NLS-1$
					} else {
						int indexServer = comboServer.indexOf(comboServer.getText());
						ip = Coloane.getParam("IP" + (indexServer + 1)); //$NON-NLS-1$
						port = Coloane.getParam("PORT" + (indexServer + 1)); //$NON-NLS-1$
						type = Coloane.getParam("TYPE" + (indexServer + 1)); //$NON-NLS-1$
						framekitIp.setEnabled(false);
						framekitPort.setEnabled(false);
						serverType.setEnabled(false);
					}
					framekitIp.setText(ip);
					framekitPort.setText(port);
					serverType.setText(type);
				} catch (IOException ef) {
					LOGGER.warning("IP introuvable"); //$NON-NLS-1$
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

		serverTypeLabel = new Label(connection, SWT.NULL);
		serverTypeLabel.setText(Messages.ColoanePrefsPage_3);
		serverType = new Text(connection, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		serverType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		serverType.setTextLimit(TXT_LIMIT);

		if (!Coloane.getInstance().getPreference("SERVER_DEFAULT").equals("")) { //$NON-NLS-1$ //$NON-NLS-2$
			comboServer.setText(Coloane.getInstance().getPreference("SERVER_DEFAULT")); //$NON-NLS-1$
			framekitIp.setText(Coloane.getInstance().getPreference("IP_DEFAULT")); //$NON-NLS-1$
			framekitPort.setText(Coloane.getInstance().getPreference("PORT_DEFAULT")); //$NON-NLS-1$
			serverType.setText(Coloane.getInstance().getPreference("TYPE_DEFAULT")); //$NON-NLS-1$
		}

		// Enable Ip/Port/Type fields if "Other..." is selected
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

	/** {@inheritDoc} */
	@Override
	protected final void performDefaults() {
		Coloane.getInstance().setDefaultPreference();
		loginField.setText(Coloane.getInstance().getPreference("LOGIN_DEFAULT"));   //$NON-NLS-1$
		framekitIp.setText(Coloane.getInstance().getPreference("IP_DEFAULT"));   //$NON-NLS-1$
		framekitPort.setText(Coloane.getInstance().getPreference("PORT_DEFAULT")); //$NON-NLS-1$
		serverType.setText(Coloane.getInstance().getPreference("TYPE_DEFAULT")); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performOk() {
		Coloane.getInstance().setPreference("LOGIN_DEFAULT", loginField.getText()); //$NON-NLS-1$
		Coloane.getInstance().setPreference("SERVER_DEFAULT", comboServer.getText()); //$NON-NLS-1$
		Coloane.getInstance().setPreference("IP_DEFAULT", framekitIp.getText()); //$NON-NLS-1$
		Coloane.getInstance().setPreference("PORT_DEFAULT", framekitPort.getText()); //$NON-NLS-1$
		Coloane.getInstance().setPreference("TYPE_DEFAULT", serverType.getText()); //$NON-NLS-1$
		return super.performOk();
	}

	/**
	 * Enable or Disable IP/Port fields in preference page
	 */
	public final void enableFields() {
		// Enable Ip/Port fields if "Other..." is selected
		if (Coloane.getInstance().getPreference("SERVER").equals(Messages.AuthenticationDialog_14)) { //$NON-NLS-1$
			framekitIp.setEnabled(true);
			framekitPort.setEnabled(true);
			serverType.setEnabled(true);
		} else {
			framekitIp.setEnabled(false);
			framekitPort.setEnabled(false);
			serverType.setEnabled(false);
		}
	}
}
