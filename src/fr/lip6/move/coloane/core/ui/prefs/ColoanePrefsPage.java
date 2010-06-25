package fr.lip6.move.coloane.core.ui.prefs;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.dialogs.Messages;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.preference.BooleanFieldEditor;
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
 * Preferences Page for Coloane.<br>
 * The first page presents a set of global preferences :
 * <ul>
 * 	<li>Authentication parameters</li>
 * 	<li>Debug Preferences</li>
 * 	<li>Stats Preferences</li>
 * </ul> 
 */
public class ColoanePrefsPage extends PreferencePage implements IWorkbenchPreferencePage {
	/** Limit size for text field */
	public static final int TXT_LIMIT = 255;

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private Combo comboServer = null;
	private Text framekitIp = null;
	private Text framekitPort = null;
	private Text serverType = null;
	private Label framekitIpLabel = null;
	private Label framekitPortLabel = null;
	private Label serverTypeLabel = null;

	private String ip;
	private String port;
	private String type;

	// Text fields for user to enter preferences
	private Text loginField;
	
	// Statistics Field
	BooleanFieldEditor statsStatus;
	
	// Debug Field
	Combo debugLevelCombo;


	/** {@inheritDoc} */
	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getInstance().getPreferenceStore());
	}
	
	/**
	 * Creates the composite which will contain all the preference controls for this page.
	 * @param parent the parent composite
	 * @return the composite for this page
	 */
	private Composite createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		return composite;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.jface.preference.PreferencePage
	 */
	@Override
	protected final Control createContents(Composite parent) {
		Composite composite = createComposite(parent);
		createAuthGroup(composite);
		createStatsGroup(composite);
		createLogGroup(composite);
		applyDialogFont(composite);
		return composite;
	}
	
	/**
	 * Create the group of components dedicated to statistics preferences
	 * @param parent The main composite
	 */
	private void createStatsGroup(Composite parent) {
		Group statsGroup = new Group(parent, SWT.LEFT);
		GridLayout layout = new GridLayout();
		statsGroup.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		statsGroup.setLayoutData(data);

		statsGroup.setText(Messages.ColoanePrefsPage_11);
		statsGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL /*| GridData.GRAB_HORIZONTAL */));
				
		Label statsLabel = new Label(statsGroup, SWT.WRAP);
		statsLabel.setText(Messages.ColoanePrefsPage_0);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		statsLabel.setLayoutData(gridData);

		statsStatus = new BooleanFieldEditor("STATS_STATUS", Messages.ColoanePrefsPage_12, BooleanFieldEditor.SEPARATE_LABEL, statsGroup);  //$NON-NLS-1$
		statsStatus.setPreferenceStore(getPreferenceStore());
		statsStatus.load();
	}

	/**
	 * Create the group of components dedicated to statistics preferences
	 * @param parent The main composite
	 */
	private final void createAuthGroup(Composite parent) {
		Group authGroup = new Group(parent, SWT.LEFT);
		GridLayout layout = new GridLayout(2, false);
		authGroup.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		authGroup.setLayoutData(data);
		authGroup.setText(Messages.ColoanePrefsPage_4);

		// Login box
		new Label(authGroup, SWT.NULL).setText(Messages.AuthenticationDialog_8);
		loginField = new Text(authGroup, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		loginField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		loginField.setTextLimit(TXT_LIMIT);
		loginField.setText(Coloane.getInstance().getPreference("LOGIN_DEFAULT")); //$NON-NLS-1$

		// ComboList for server choice
		new Label(authGroup, SWT.NULL).setText(Messages.AuthenticationDialog_10);
		comboServer = new Combo(authGroup, SWT.NULL);
		comboServer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Fetch values from plugin.properties file and fill the comboList with
		int nbservers = Integer.parseInt(Coloane.getParam("NB_SERVERS")); //$NON-NLS-1$
		String[] serversList = new String[nbservers + 2];
		int i = 0;
		while (i < nbservers) {
			serversList[i] = Coloane.getParam("NAME" + (i + 1)); //$NON-NLS-1$
			i++;
		}
		serversList[i] = Messages.AuthenticationDialog_13;
		serversList[i + 1] = Messages.AuthenticationDialog_14;

		// Set the list
		comboServer.setItems(serversList);
		comboServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// if "localhost server" has been chosen
					if (comboServer.getText().equals(Messages.AuthenticationDialog_13)) {
						framekitIp.setEnabled(false);
						framekitPort.setEnabled(false);
						serverType.setEnabled(false);
						ip = InetAddress.getByName("localhost").getHostAddress(); //$NON-NLS-1$
						port = String.valueOf(Coloane.getParam("PORT_DEFAULT")); //$NON-NLS-1$
						type = String.valueOf(Coloane.getParam("TYPE_DEFAULT")); //$NON-NLS-1$

					// otherwise
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

		// Server parameters
		framekitIpLabel = new Label(authGroup, SWT.NULL);
		framekitIpLabel.setText(Messages.AuthenticationDialog_26);
		framekitIp = new Text(authGroup, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitIp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitIp.setTextLimit(TXT_LIMIT);

		framekitPortLabel = new Label(authGroup, SWT.NULL);
		framekitPortLabel.setText(Messages.AuthenticationDialog_27);
		framekitPort = new Text(authGroup, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitPort.setTextLimit(TXT_LIMIT);

		serverTypeLabel = new Label(authGroup, SWT.NULL);
		serverTypeLabel.setText(Messages.ColoanePrefsPage_3);
		serverType = new Text(authGroup, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
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
	}
	
	/**
	 * Create the group of components dedicated to log preferences
	 * @param parent The main composite
	 */
	private void createLogGroup(Composite parent) {
		Group logGroup = new Group(parent, SWT.LEFT);
		GridLayout layout = new GridLayout(2, false);
		logGroup.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		logGroup .setLayoutData(data);
		logGroup.setText(Messages.ColoanePrefsPage_5);
		
		new Label(logGroup, SWT.NULL).setText(Messages.ColoanePrefsPage_6);
		debugLevelCombo = new Combo(logGroup, SWT.NULL);
		debugLevelCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String levelList[] = {"NORMAL", "BETA", "DEBUG"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		debugLevelCombo.setItems(levelList);
		debugLevelCombo.setText(Messages.ColoanePrefsPage_10);
		debugLevelCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (debugLevelCombo.getText().equals("NORMAL")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.INFO);
				} else if (debugLevelCombo.getText().equals("BETA")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.FINE);
				} else if (debugLevelCombo.getText().equals("DEBUG")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.ALL);
				}
			}
		});
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
		
		// Stats
		Coloane.getInstance().setPreference("STATS_STATUS", String.valueOf(statsStatus.getBooleanValue())); //$NON-NLS-1$
		
		// DEBUG
		Coloane.getInstance().setPreference("STATS_STATUS", String.valueOf(statsStatus.getBooleanValue())); //$NON-NLS-1$
		
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
