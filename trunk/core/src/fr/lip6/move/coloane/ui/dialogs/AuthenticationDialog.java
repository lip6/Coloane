package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.communications.Com;
import fr.lip6.move.coloane.exceptions.UIException;
import fr.lip6.move.coloane.main.Coloane;


import java.io.IOException;
import java.net.InetAddress;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

/**
 * Boite de dialogue disponible dans le menu "Platform > Connection..."
 * permettant de saisir les parametres pour la connexion a une plateforme
 * FrameKit (login, passwd, ip, port)
 */
public class AuthenticationDialog extends Dialog {

	/* Initialisation a la chaine */
	private Text login = null;
	private Text password = null;
	private Text framekitIp = null;
	private Text framekitPort = null;
	private Combo comboServer = null;
	private Label framekitIpLabel = null;
	private Label framekitPortLabel = null;
	private Composite compo = null;

	private Button detailsButton = null;

	/** Tag pour le champ homonyme */
	public static final String PASSWORD_TAG = "password"; //$NON-NLS-1$

	/** Tag pour le champ homonyme */
	public static final String LOGIN_TAG = "login"; //$NON-NLS-1$

	/** Tag pour l'ip de la plateforme FrameKit */
	public static final String FKIP_TAG = "fkip"; //$NON-NLS-1$

	/** Tag pour le port de la plateforme FrameKit */
	public static final String FKPORT_TAG = "fkport"; //$NON-NLS-1$

	/** Taille limite du texte */
	public static final int TXT_LIMIT = 255;

	/** Titre de la boite de dialogue */
	public static final String MSG_TITLE = Messages.AuthenticationDialog_0;

	/** Login non valide et/ou erreur de mot de passe */
	private static final String MSG_AUTH_ERROR = Messages.AuthenticationDialog_1;

	/** Champs login vide */
	private static final String MSG_LOGIN_ERROR = Messages.AuthenticationDialog_2;

	/** Champs login vide */
	private static final String MSG_PASS_ERROR = Messages.AuthenticationDialog_3;

	/** General Error */
	//private static final String MSG_GNRL_ERROR = "Global Error";

	/** Id du bouton Details */
	private static final int DETAILS_ID = IDialogConstants.CLIENT_ID;

	/** Label du bouton detail */
	private static final String SHOW_DETAILS_LABEL = Messages.AuthenticationDialog_4;

	private static final String HIDE_DETAILS_LABEL = Messages.AuthenticationDialog_5;

	/**Pour masquer/demasquer les composants a ajouter*/
	private boolean visibility = true;

	/**L'adresse IP de FrameKit */
	private String ip = ""; //$NON-NLS-1$

	/** Le port de Framekit */
	private String port = ""; //$NON-NLS-1$


	/**
	 * Constructeur
	 * @param parentShell Parent Shell
	 */
	public AuthenticationDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Creer les controles pour ce dialogue
	 *
	 * @param Composite parent Parent Composite
	 * @return Composite du dialogue
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected final Control createDialogArea(Composite parent) {
		compo = (Composite) super.createDialogArea(parent);

		detailsButton = (Button) super.createButton(parent, DETAILS_ID, SHOW_DETAILS_LABEL , false);
		detailsButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeVisibility();
			}
		});


		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		compo.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		compo.setLayoutData(gridData);

		// LOGIN
		new Label(compo, SWT.NULL).setText(Messages.AuthenticationDialog_8);
		login = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		tag(login, LOGIN_TAG);
		login.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		login.setTextLimit(TXT_LIMIT);
		if (Coloane.getDefault().getPreference("LOGIN") != "") {
			login.setText(Coloane.getDefault().getPreference("LOGIN"));
		}

		// PASSWORD
		new Label(compo, SWT.NULL).setText(Messages.AuthenticationDialog_9);
		password = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD | SWT.LEFT);
		tag(password, PASSWORD_TAG);
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.setTextLimit(TXT_LIMIT);

		// LISTE DES SERVEURS
		new Label(compo, SWT.NULL).setText(Messages.AuthenticationDialog_10);

		// Recuperation des valeurs dans le fichier LNG et les inserer dans la combo
		int nbservers = Integer.parseInt(Coloane.getParam("NB_SERVERS")); //$NON-NLS-1$
		String[] serversList = new String[nbservers + 2];
		int i = 0;
		while (i < nbservers) {
			serversList[i] = Coloane.getParam("NAME" + (i + 1)); //$NON-NLS-1$
			i++;
		}
		serversList[i] = Messages.AuthenticationDialog_13;
		serversList[i + 1] = Messages.AuthenticationDialog_14;

		comboServer = new Combo(compo, SWT.NULL);
		comboServer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comboServer.setItems(serversList);

		if (!(Coloane.getDefault().getPreference("SERVER").equals(""))) {
			comboServer.setText(Coloane.getDefault().getPreference("SERVER"));
			ip = Coloane.getDefault().getPreference("IP");
			port = Coloane.getDefault().getPreference("PORT");
		}

		comboServer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {

					int i = 0;
					while (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))	&& !comboServer.getText().equals(Coloane.getParam("NAME" + (i + 1)))) { //$NON-NLS-1$ //$NON-NLS-2$
						i++;
					}

					if (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))) { //$NON-NLS-1$
						ip = Coloane.getParam("IP" + (i + 1)); //$NON-NLS-1$
						port = Coloane.getParam("PORT" + (i + 1)); //$NON-NLS-1$

						if (!visibility) {
							setFrameKitIp(ip);
							setFrameKitPort(port);
							enableFields(false);
						}

					} else if (comboServer.getText().equals("Localhost")) { //$NON-NLS-1$
						ip = InetAddress.getByName(Messages.AuthenticationDialog_21).getHostAddress();
						port = String.valueOf(Coloane.getParam("PORT_DEFAUT")); //$NON-NLS-1$

						if (!visibility) {
							setFrameKitIp(ip);
							setFrameKitPort(port);
							enableFields(false);
						}
					} else { // Autre ..
						ip = "";
						port = "";
						if (visibility) {
							changeVisibility();
						} else {
							setFrameKitIp(""); //$NON-NLS-1$
							setFrameKitPort(""); //$NON-NLS-1$
							enableFields(true);
						}
					}
				} catch (IOException ef) {
					Coloane.getLogger().warning("IP introuvable"); //$NON-NLS-1$
				}
			}
		});

		return compo;
	}

	/**
	 * Configurer le shell pour la legende
	 * @param newShell Cette instance
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(MSG_TITLE);
	}

	/**
	 * Executer l'authentification en la transferant a la COM
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected final void okPressed() {
		try {
			// Appel de l'authentification du module de Com
			Com com = Coloane.getDefault().getCom();
			if (com.authentication(getLogin(), getPassword(), getFrameKitIp(), getFrameKitPort())) {
				super.okPressed();
			} else {
				Coloane.showErrorMsg(MSG_AUTH_ERROR);
			}
		} catch (UIException e) {
			Coloane.showErrorMsg(Messages.AuthenticationDialog_28 + e.getMessage());
		}
	}

	public final String getItem(String server) {
		return (String) comboServer.getData(server);
	}

	/**
	 * Obtenir la valeur de login
	 * @return String Retourne le login
	 * @throws UIException si le login est vide
	 */
	public final String getLogin() throws UIException {
		String loginTemp = login.getText();
		if (loginTemp.trim().length() == 0) {
			throw new UIException(MSG_LOGIN_ERROR);
		}
		return loginTemp;
	}

	/**
	 * Donner une valeur au login
	 * @param login le login
	 */
	public final void setLogin(String log) {
		this.login.setText(log);
	}

	/**
	 * Obtenir le mot de passe
	 * @return String Retourne le mot de passe
	 * @throws UIException si le pass est vide
	 */
	public final String getPassword() throws UIException {
		String passTemp = password.getText();
		if (passTemp.trim().length() == 0) {
			throw new UIException(MSG_PASS_ERROR);
		}
		return passTemp;
	}

	/**
	 * Donner une valeur au pwd
	 * @param pwd le mot de passe
	 */
	public final void setPassword(String pwd) {
		this.password.setText(pwd);
	}

	/**
	 * Obtenir l'IP de la plateforme FrameKit
	 * @return Retourne l'IP fournie de la plateforme FrameKit
	 */
	public final String getFrameKitIp() {
		//si l'ip et le port ne sont pas recuperable via les champs Text
		if (visibility) {
			return ip;
		}
		return framekitIp.getText();
	}

	/**
	 * Donner une valeur a l'ip
	 * @param fi l'ip de la plateforme Framekit
	 */
	public final void setFrameKitIp(String fi) {
		this.framekitIp.setText(fi);
	}

	/**
	 * Obtenir le port de la plateforme FrameKit tel qu'indique par l'utilisateur
	 * @return Retourne le port fourni de la plateforme FrameKit
	 */
	public final int getFrameKitPort() {
		//si l'ip et le port ne sont pas recuperable via les champs Text
		if (visibility) {
			return Integer.parseInt(port);
		}
		return Integer.parseInt(framekitPort.getText());
	}

	/**
	 * Donner une valeur au port ou
	 * @param fp l'ip de la plateforme Framekit
	 */
	public final void setFrameKitPort(String fp) {
		this.framekitPort.setText(fp);
	}

	/**
	 * Donner une valeur au tag pour le widget a tester
	 * Set tag for widget for testing
	 * @param tagged widget to set tag
	 * @param data tag name
	 */
	public static void tag(Widget tagged, String data) {
		tagged.setData("name", data); //$NON-NLS-1$
	}

	/**
	 * Methode qui affiche les details concernant le serveur Framekit
	 */
	private void showDetails(String serveur) {
		//Invisible a la creation de la boite
		framekitIpLabel = new Label(compo, SWT.NULL);
		framekitIpLabel.setText(Messages.AuthenticationDialog_26);

		framekitIp = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitIp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitIp.setTextLimit(TXT_LIMIT);

		framekitPortLabel = new Label(compo, SWT.NULL);
		framekitPortLabel.setText(Messages.AuthenticationDialog_27);

		framekitPort = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitPort.setTextLimit(TXT_LIMIT);

		setFrameKitIp(ip);
		setFrameKitPort(port);

		if (!serveur.equals(Messages.AuthenticationDialog_14)) {
			enableFields(false);
		}
	}

	/**
	 * Methode qui supprime les details concernant le serveur Framekit
	 */
	private void hideDetails() {
		framekitIp.dispose();
		framekitPort.dispose();
		framekitIpLabel.dispose();
		framekitPortLabel.dispose();
	}

	/** Methode de service qui permet de masquer les labels et les zones de texte a ajouter
	 * @param boolean v determine si ces composants sont visibles
	 */
	private void changeVisibility() {
		if (this.visibility) {
			detailsButton.setText(HIDE_DETAILS_LABEL);
			this.visibility = false;
			showDetails(comboServer.getText());
			this.getShell().pack();
		} else {
			detailsButton.setText(SHOW_DETAILS_LABEL);
			this.visibility = true;
			hideDetails();
			this.getShell().pack();
		}
	}

	/**
	 * Enable or Disable IP/Port fields in preference page
	 */
	public final void enableFields(boolean view) {
			framekitIp.setEnabled(view);
			framekitPort.setEnabled(view);
	}
}
