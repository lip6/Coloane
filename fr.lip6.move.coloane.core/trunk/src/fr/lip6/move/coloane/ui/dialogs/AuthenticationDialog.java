package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.communications.Com;
import fr.lip6.move.coloane.exceptions.GuiAuthenticationException;
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
	public static final String MSG_TITLE = "Authentication";

	/** Login non valide et/ou erreur de mot de passe */
	private static final String MSG_AUTH_ERROR = Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.5"); //$NON-NLS-1$

	/** Champs login vide */
	private static final String MSG_LOGIN_ERROR = Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.6"); //$NON-NLS-1$

	/** Champs login vide */
	private static final String MSG_PASS_ERROR = Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.7"); //$NON-NLS-1$

	/** General Error */
	private static final String MSG_GNRL_ERROR = Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.8"); //$NON-NLS-1$

	/** Id du bouton Details */
	private static final int DETAILS_ID = IDialogConstants.CLIENT_ID;

	/** Label du bouton detail */
	private static final String SHOW_DETAILS_LABEL = Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.13"); //$NON-NLS-1$

	private static final String HIDE_DETAILS_LABEL = "Hide details";

	/**Pour masquer/demasquer les composants a ajouter*/
	private boolean visibility = true;

	/**L'adresse IP de FrameKit */
	private String ip = "";

	/** Le port de Framekit */
	private String port = "";


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
		new Label(compo, SWT.NULL).setText(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.9")); //$NON-NLS-1$
		login = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		tag(login, LOGIN_TAG);
		login.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		login.setTextLimit(TXT_LIMIT);

		// PASSWORD
		new Label(compo, SWT.NULL).setText(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.10")); //$NON-NLS-1$
		password = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD | SWT.LEFT);
		tag(password, PASSWORD_TAG);
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.setTextLimit(TXT_LIMIT);

		// LISTE DES SERVEURS
		new Label(compo, SWT.NULL).setText(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.12")); //$NON-NLS-1$

		// Recuperation des valeurs dans le fichier LNG et les inserer dans la combo
		int nbservers = Integer.parseInt(Coloane.getParam("NB_SERVERS"));
		String[] serversList = new String[nbservers + 2];
		int i = 0;
		while (i < nbservers) {
			serversList[i] = Coloane.getParam("NAME" + (i + 1));
			i++;
		}
		serversList[i] = Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.1");
		serversList[i + 1] = Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.2");

		comboServer = new Combo(compo, SWT.NULL);
		comboServer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comboServer.setItems(serversList);
		comboServer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {

					int i = 0;
					while (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))	&& !comboServer.getText().equals(Coloane.getParam("NAME" + (i + 1)))) {
						i++;
					}

					if (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))) {
						ip = Coloane.getParam("IP" + (i + 1));
						setFrameKitIp(ip);
						port = Coloane.getParam("PORT" + (i + 1));
						setFrameKitPort(port);

					} else if (comboServer.getText().equals(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.1"))) {
						ip = InetAddress.getByName("localhost").getHostAddress();
						setFrameKitIp(ip);
						port = String.valueOf(Coloane.getParam("PORT_DEFAUT"));
						setFrameKitPort(port);
					} else { // Autre ..
						setFrameKitIp("");
						setFrameKitPort("");
						setVisibility(true);
						changeVisibility();
					}
				} catch (IOException ef) {
					System.out.println(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.11"));
				}
			}
		});

		// Invisible a la creation de la boite
		framekitIpLabel = new Label(compo, SWT.NULL);
		framekitIpLabel.setText(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.3"));
		framekitIpLabel.setVisible(false);
		framekitIp = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitIp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitIp.setTextLimit(TXT_LIMIT);
		framekitIp.setVisible(false);

		framekitPortLabel = new Label(compo, SWT.NULL);
		framekitPortLabel.setText(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.4")); //$NON-NLS-1$);
		framekitPortLabel.setVisible(false);
		framekitPort = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekitPort.setTextLimit(TXT_LIMIT);
		framekitPort.setVisible(false);

		return compo;
	}

	/**
	 * Configurer le shell pour la legende
	 *
	 * @param newShell Cette instance
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(MSG_TITLE);
	}

	/**
	 * Executer l'authentification en la transferant a la COM
	 *
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected final void okPressed() {

		// Appel de l'authentification du module de Com
		try {
			Com com = Coloane.getDefault().getCom();
			if (com.authentication(getLogin(), getPassword(),
					getFrameKitIp(), getFrameKitPort())) {
				super.okPressed();
			} else {
				Coloane.showErrorMsg(MSG_AUTH_ERROR);
			}
		} catch (GuiAuthenticationException erreur) {
			Coloane.showErrorMsg(Coloane.getTranslate().getString("ui.dialogs.AuthenticationDialog.15") + erreur.getMessage()); //$NON-NLS-1$
		} catch (Exception e) {
			Coloane.showErrorMsg(MSG_GNRL_ERROR + ": " + e.getMessage()); //$NON-NLS-1$
		}
	}

	public final String getItem(String server) {
		return (String) comboServer.getData(server);
	}

	/**
	 * Obtenir la valeur de login
	 *
	 * @return String Retourne le login
	 *
	 * @throws GuiAuthenticationException
	 *             if login is blank
	 */
	public final String getLogin() throws GuiAuthenticationException {
		String loginTemp = login.getText();
		if (loginTemp.trim().length() == 0) {
			throw new GuiAuthenticationException(MSG_LOGIN_ERROR);
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
	 *
	 * @return Retourne le mot de passe
	 */
	public final String getPassword() throws GuiAuthenticationException {
		String passTemp = password.getText();
		if (passTemp.trim().length() == 0) {
			throw new GuiAuthenticationException(MSG_PASS_ERROR);
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
		return Integer.parseInt(framekitPort.getText());
	}

	/**
	 * Donner une valeur au port ou
	 * @param fp l'ip de la plateforme Framekit
	 */
	public final void setFrameKitPort(String fp) {
		this.framekitPort.setText(new String(fp));
	}

	/**
	 * Donner une valeur au port ou
	 * @param int fp l'ip de la plateforme Framekit
	 */
	public final void setFrameKitPort(int fp) {
		this.framekitPort.setText((new Integer(fp)).toString());
	}

	/**
	 * Donner une valeur au tag pour le widget a tester
	 * Set tag for widget for testing
	 * @param tagged widget to set tag
	 * @param data tag name
	 */
	public static void tag(Widget tagged, String data) {
		tagged.setData("name", data); //$NON-NLS-1$
		System.out.println(tagged.getData("name"));
	}

	private void setVisibility(boolean newVisibility) {
		this.visibility = newVisibility;
	}

	/** Methode de service qui permet de masquer les labels et les zones de texte a ajouter
	 * @param boolean v determine si ces composants sont visibles
	 */
	private void changeVisibility() {
		framekitIpLabel.setVisible(this.visibility);
		framekitIp.setVisible(this.visibility);
		framekitPortLabel.setVisible(this.visibility);
		framekitPort.setVisible(this.visibility);

		if (this.visibility) {
			detailsButton.setText(HIDE_DETAILS_LABEL);
			this.visibility = false;
		} else {
			detailsButton.setText(SHOW_DETAILS_LABEL);
			this.visibility = true;
		}
	}

}
