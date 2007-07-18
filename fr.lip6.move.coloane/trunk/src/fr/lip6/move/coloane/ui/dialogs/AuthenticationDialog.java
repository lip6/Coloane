package fr.lip6.move.coloane.ui.dialogs;

import java.io.IOException;
import java.net.InetAddress;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Combo;

import fr.lip6.move.coloane.communications.Com;
import fr.lip6.move.coloane.exceptions.GuiAuthenticationException;
import fr.lip6.move.coloane.main.Coloane;


/**
 * Boite de dialogue disponible dans le menu "Platform > Connection..."
 * permettant de saisir les parametres pour la connexion a une plateforme
 * FrameKit (login, passwd, ip, port)
 */
public class AuthenticationDialog extends Dialog {

	/* Initialisation a la chaine */
	private Text login = null;

	private Text password = null;

	private Text framekit_ip = null;

	private Text framekit_port = null;

	private Combo combo_server = null;

	private Label framekit_ip_label = null;

	private Label framekit_port_label = null;

	private Composite compo = null;

	private Button b = null;

	/** TODO : Regrouper les propriete dans un dictionnaire */

	/** Tag pour le champ homonyme */
	public static final String PASSWORD_TAG = "password"; //$NON-NLS-1$

	/** Tag pour le champ homonyme */
	public static final String LOGIN_TAG = "login"; //$NON-NLS-1$

	/** Tag pour l'ip de la plateforme FrameKit */
	public static final String FKIP_TAG = "fkip"; //$NON-NLS-1$

	/** Tag pour le port de la plateforme FrameKit */
	public static final String FKPORT_TAG = "fkport"; //$NON-NLS-1$

	/** Taiile limite du texte */
	public static final int TXT_LIMIT = 255;

	/** Titre de la boite de dialogue */
	public static final String MSG_TITLE = "Authentication";

	/** Login non valide et/ou erreur de mot de passe */
	private static final String MSG_AUTH_ERROR = Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.5"); //$NON-NLS-1$

	/** Champs login vide */
	private static final String MSG_LOGIN_ERROR = Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.6"); //$NON-NLS-1$

	/** Champs login vide */
	private static final String MSG_PASS_ERROR = Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.7"); //$NON-NLS-1$

	/** General Error */
	private static final String MSG_GNRL_ERROR = Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.8"); //$NON-NLS-1$
	
	/**Id du bouton Details*/
	private static final int Details_ID = IDialogConstants.CLIENT_ID;

	/**Label du bouton detail*/	
	private static final String DETAILS_LABEL = Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.2");//$NON-NLS-1$
	
	/**Pour masquer/demasquer les composants a ajouter*/
	private boolean visibilite = false;

	/**L'adresse IP de FrameKit */
	private String ip = ""; 
	
	/** Le port de Framekit */
	private String port = "";
	

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
	 * @param Composite
	 *            parent Parent Composite
	 * @return Composite du dialogue
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		compo = (Composite) super.createDialogArea(parent);
		b = (Button) super.createButton(parent, Details_ID,DETAILS_LABEL , false);
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (visibilite == true)
					visibilite(false);
				else {
					visibilite(true);
				}
			}
		});
		
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		compo.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		compo.setLayoutData(gridData);

		new Label(compo, SWT.NULL).setText(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.9")); //$NON-NLS-1$
		login = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		tag(login, LOGIN_TAG);
		login.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		login.setTextLimit(TXT_LIMIT);

		new Label(compo, SWT.NULL).setText(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.10")); //$NON-NLS-1$
		password = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD
				| SWT.LEFT);
		tag(password, PASSWORD_TAG);
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.setTextLimit(TXT_LIMIT);

		new Label(compo, SWT.NULL).setText(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.12")); //$NON-NLS-1$
		
		//Recuperation des valeurs dans le fichier LNG et les inserer dans la combo
		int nbservers = Integer.parseInt(Coloane.getParam("NB_SERVERS"));
		String[] liste_serveurs = new String[nbservers + 2];
		int i=0;
		while(i < nbservers){
			liste_serveurs[i] = Coloane.getParam("NAME"+(i+1));
			i++;
		}
		liste_serveurs[i] = Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.1");
		liste_serveurs[i+1] = Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.2");

		combo_server = new Combo(compo, SWT.NULL);
		combo_server.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		combo_server.setItems(liste_serveurs);
		combo_server.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {

					int i = 0;
					while (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))
							&& !combo_server.getText().equals(
									Coloane.getParam("NAME" + (i + 1)))) {
						i++;
					}

					if (i < Integer.parseInt(Coloane.getParam("NB_SERVERS"))) {
						ip = Coloane.getParam("IP" + (i + 1));
						setFrameKitIp(ip);
						port = Coloane.getParam("PORT" + (i + 1));
						setFrameKitPort(port);

					} else if (combo_server.getText().equals(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.1"))) {
						ip = InetAddress.getByName("localhost").getHostAddress();
						setFrameKitIp(ip);
						port = String.valueOf(8080);
						setFrameKitPort(port);
					} else { // Autre ..
						setFrameKitIp("");
						setFrameKitPort("");
						visibilite(true);
					}
				} catch (IOException ef) {
					System.out.println(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.11"));
				}
			}
		});

		// Invisible a la creation de la boite
		
		framekit_ip_label = new Label(compo, SWT.NULL);
		framekit_ip_label.setText(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.3"));
		framekit_ip = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekit_ip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekit_ip.setTextLimit(TXT_LIMIT);

		framekit_port_label = new Label(compo, SWT.NULL);
		framekit_port_label.setText(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.4")); //$NON-NLS-1$);
		framekit_port = new Text(compo, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekit_port.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		framekit_port.setTextLimit(TXT_LIMIT);
		
		visibilite(false);

		return compo;
	}

	/**
	 * Configurer le shell pour la legende
	 * 
	 * @param newShell
	 *            Cette instance
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
				if (com.authentication(getLogin(), getPassword(),
								getFrameKitIp(), getFrameKitPort())) {
					super.okPressed();
				} else {
					Coloane.showErrorMsg(MSG_AUTH_ERROR);
				}			
		} catch (GuiAuthenticationException erreur) {
			Coloane.showErrorMsg(Coloane.traduction.getString("ui.dialogs.AuthenticationDialog.15") + erreur.getMessage()); //$NON-NLS-1$
		} catch (Exception e) {
			Coloane.showErrorMsg(MSG_GNRL_ERROR + ": " + e.getMessage()); //$NON-NLS-1$
		}
	}

	public String getItem(String Server) {
		return (String) combo_server.getData(Server);
	}

	/**
	 * Obtenir la valeur de login
	 * 
	 * @return String Retourne le login
	 * 
	 * @throws GuiAuthenticationException
	 *             if login is blank
	 */
	public String getLogin() throws GuiAuthenticationException {
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
	public void setLogin(String login) {
		this.login.setText(login);
	}

	/**
	 * Obtenir le mot de passe
	 * 
	 * @return Retourne le mot de passe
	 */
	public String getPassword() throws GuiAuthenticationException {
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
	public void setPassword(String pwd) {
		this.password.setText(pwd);
	}

	/**
	 * Obtenir l'IP de la plateforme FrameKit
	 * 
	 * @return Retourne l'IP fournie de la plateforme FrameKit
	 */
	public String getFrameKitIp() {
		return framekit_ip.getText();
	}

	/**
     * Donner une valeur a l'ip
	 * @param fi l'ip de la plateforme Framekit
	 */
	public void setFrameKitIp(String fi) {
		this.framekit_ip.setText(fi);
	}

	/**
	 * Obtenir le port de la plateforme FrameKit tel qu'indique par l'utilisateur
	 * @return Retourne le port fourni de la plateforme FrameKit
	 */
	public int getFrameKitPort() {
		return Integer.parseInt(framekit_port.getText());
	}

	/**
	 * Donner une valeur au port ou
	 * 
	 * @param fi
	 *            l'ip de la plateforme Framekit
	 */
	public void setFrameKitPort(String fp) {
		this.framekit_port.setText(new String(fp));
	}

	/**
	 * Donner une valeur au port ou
	 * 
	 * @param int
	 *            fi l'ip de la plateforme Framekit
	 */
	public void setFrameKitPort(int fp) {
		this.framekit_port.setText((new Integer(fp)).toString());
	}

	/**
     * Donner une valeur au tag pour le widget a tester
	 * Set tag for widget for testing 
	 * @param tagged widget to set tag
	 * @param data tag name
	 */
	public static void tag(Widget tagged, String data) {
		tagged.setData("name", data);//$NON-NLS-1$
		System.out.println(tagged.getData("name"));
	}

	/** Methode de service qui permet de masquer les labels et les 
	 * zones de texte à ajouter
	 * @param boolean b determine si ces composants sont visibles
	 */
	private void visibilite(boolean b) {
		visibilite = b;
		framekit_ip_label.setVisible(b);
		framekit_ip.setVisible(b);

		framekit_port_label.setVisible(b);
		framekit_port.setVisible(b);

	}

}
