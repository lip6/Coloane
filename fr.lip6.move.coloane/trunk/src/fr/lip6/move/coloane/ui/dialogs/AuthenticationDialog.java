package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.layout.*;

import fr.lip6.move.coloane.communications.Com;
import fr.lip6.move.coloane.exceptions.GuiAuthenticationException;
import fr.lip6.move.coloane.main.Coloane;

/**
 * Boite de dialogue disponible dans le menu "Platform > Connection..." 
 * permettant de saisir les parametres pour la connexion a une 
 * plateforme FrameKit (login, passwd, ip, port)
 */
public class AuthenticationDialog extends Dialog {

	/* Initialisation a la chaine */
	private Text login = null;
	private Text password = null;
	private Text framekit_ip = null;
	private Text framekit_port = null;

	
	/** TODO : Regrouper les propriete dans un dictionnaire ¬*/
	/** Tag pour le champ homonyme */
	public static final String PASSWORD_TAG = "password";
	
	/** Tag pour le champ homonyme */
	public static final String LOGIN_TAG = "login";
	
	/** Tag pour l'ip de la plateforme FrameKit */
	public static final String FKIP_TAG = "fkip";
	
	/** Tag pour le port de la plateforme FrameKit */
	public static final String FKPORT_TAG = "fkport";
	
	/** Taiile limite du texte */
	public static final int TXT_LIMIT = 255;
	
	
	/** Titre de la boite de dialogue */
	public static final String MSG_TITLE = "Authentication";

	/** Login non valide et/ou erreur de mot de passe */
	private static final String MSG_AUTH_ERROR = "Identifiant ou mot de passe invalide";
	
	/** Champs login vide */
	private static final String MSG_LOGIN_ERROR = "Vous devez fournir un nom d'utilisateur";

	/** Champs login vide */
	private static final String MSG_PASS_ERROR = "Vous devez fournir un mot de passe";

	/** General Error */
	private static final String MSG_GNRL_ERROR = "Erreur globale";
	
	/**
	 * Constructeur
	 * 
	 * @param parentShell
	 *            Parent Shell
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
	protected Control createDialogArea(Composite parent) {
		Composite compo = (Composite) super.createDialogArea(parent);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		compo.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		compo.setLayoutData(gridData);
		
		new Label(compo, SWT.NULL).setText("Login :");
		login = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		tag(login, LOGIN_TAG);
		login.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		login.setTextLimit(TXT_LIMIT);
		
		new Label(compo, SWT.NULL).setText("Password :");
		password = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD | SWT.LEFT);
		tag(password, PASSWORD_TAG);
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.setTextLimit(TXT_LIMIT);
		
		/** pour un choix de la connexion a FrameKit
		 */
		new Label(compo, SWT.NULL).setText("Framekit IP :");
		framekit_ip = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		tag(framekit_ip, FKIP_TAG);
		framekit_ip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekit_ip.setTextLimit(TXT_LIMIT);
		setFrameKitIp(Coloane.getParam("PLATEFORME_DEFAUT"));
		
		new Label(compo, SWT.NULL).setText("Framekit Port :");
		framekit_port = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		tag(framekit_port, FKPORT_TAG);
		framekit_port.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setFrameKitPort(Coloane.getParam("PORT_DEFAUT"));

		return compo;
	}

	/**
	 * Configurer le shell pour la lŽgende
	 * 
	 * @param newShell Cette instance
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(MSG_TITLE);
	}

	/**
	 * Executer l'authentification en la transferant a la COM
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {

		// Appel de l'authentification du module de Com
		try {
			Com com = Coloane.getDefault().getCom();
			
			if (com.authentication(getLogin(), getPassword(),getFrameKitIp(), getFrameKitPort())) {
				super.okPressed();
			} else {
				Coloane.showErrorMsg(MSG_AUTH_ERROR);
			}
		} catch (GuiAuthenticationException erreur) {
			Coloane.showErrorMsg("Erreur: " + erreur.getMessage());
		} catch (Exception e) {
			Coloane.showErrorMsg(MSG_GNRL_ERROR + ": " + e.getMessage());
		}
	}

	/**
     * Obtenir la valeur de login
	 * @return String Retourne le login
	 * 
	 * @throws GuiAuthenticationException if login is blank
	 */
	public String getLogin() throws GuiAuthenticationException {
		String loginTemp = login.getText();
		if (loginTemp.trim().length() == 0) {
			throw new GuiAuthenticationException(MSG_LOGIN_ERROR);
		}
		return loginTemp;
	}

	/**
     * Donner une valeur ˆ login
	 * @param login le login
	 */
	public void setLogin (String login) {
		this.login.setText(login);
	}

	/**
	 * Obtenir le mot de passe
	 * @return Retourne le mot de passe
	 */
	public String getPassword() throws GuiAuthenticationException {
		String passTemp = password.getText();
		if(passTemp.trim().length() == 0) {
			throw new GuiAuthenticationException(MSG_PASS_ERROR);
		}
		return passTemp;
	}

	/**
     * Donner une valeur ˆ pwd
	 * @param pwd le mot de passe
	 */
	public void setPassword(String pwd) {
		this.password.setText(pwd);
	}
	
	/**
	 * Obtenir l'IP de la plateforme FrameKit
	 * @return Retourne l'IP fournie de la plateforme FrameKit
	 */
	public String getFrameKitIp() {
		return framekit_ip.getText();
	}

	/**
     * Donner une valeur ˆ l'ip
	 * @param fi l'ip de la plateforme Framekit
	 */
	public void setFrameKitIp(String fi) {
		this.framekit_ip.setText(fi);
	}
	
	/**
	 * Obtenir le port de la plateforme FrameKit tel qu'indiqué par l'utilisateur
	 * @return Retourne le port fourni de la plateforme FrameKit
	 */
	public int getFrameKitPort() {
		return Integer.parseInt(framekit_port.getText());
	}

	/**
     * Donner une valeur au port ou 
	 * @param fi l'ip de la plateforme Framekit
	 */
	public void setFrameKitPort(String fp) {
		this.framekit_port.setText(new String(fp));
	}
	
	/**
     * Donner une valeur au port ou 
	 * @param int fi l'ip de la plateforme Framekit
	 */
	public void setFrameKitPort(int fp) {
		this.framekit_port.setText((new Integer(fp)).toString());
	}
	
	
	/**
     * Donner une valeur au tag pour le widget ˆ tester
	 * Set tag for widget for testing 
	 * @param tagged widget to set tag
	 * @param data tag name
	 */
	public static void tag(Widget tagged, String data) {
		tagged.setData("name", data); //$NON-NLS-1$
	}
}
