/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.interfaces.objects.dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class AuthenticationDialog extends TitleAreaDialog {
	
	/** Login **/
	private String loginValue;
	private Text login;
	
	/** Password **/
	private String passwordValue;
	private Text password;
	

	/**
	 * Constructor
	 * @param parentShell Shell of the parent window
	 */
	public AuthenticationDialog(Shell parentShell) {
		super(parentShell);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create() {
		super.create();
		setTitle("Authentication");
		setMessage("This service requires an authentication.");
		setTitleImage(ImageDescriptor.createFromFile(this.getClass(), "/resources/dialog_auth.png").createImage());
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Authentication");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isHelpAvailable() {
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite root = (Composite) super.createDialogArea(parent);

		Composite area = new Composite(root, SWT.NONE);
		
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		area.setLayout(gridLayout);
		area.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		// LABEL
		GridData gridDataLabel = new GridData(GridData.FILL_HORIZONTAL);
		gridDataLabel.horizontalSpan = 2;
		Label label = new Label(area, SWT.NULL);
		label.setText("Please provide your authentication information:");
		label.setLayoutData(gridDataLabel);

		GridData gridDataText = new GridData(GridData.FILL_HORIZONTAL);

		// LOGIN
		new Label(area, SWT.NULL).setText("Login:");
		login = new Text(area, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		login.setLayoutData(gridDataText);
		login.setTextLimit(255);
		
		// PASSWORD
		new Label(area, SWT.NULL).setText("Password:");
		password = new Text(area, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD | SWT.LEFT);
		password.setLayoutData(gridDataText);
		password.setTextLimit(255);
		
		return area;
	}
	
	
	
	@Override
	protected void okPressed() {
		this.loginValue = login.getText();
		this.passwordValue = password.getText();
		
		if ("".equals(loginValue)) {
			setErrorMessage("You must provide a login");
			return;
		}
		
		if ("".equals(passwordValue)) {
			setErrorMessage("You must provide a password");
			return;
		}
		
		super.okPressed();
	}
	
	/**
	 * @return The login provided by the user
	 */
	public String getLoginValue() {
		return loginValue;
	}
	
	/**
	 * @return The password provided by the user
	 */
	public String getPasswordValue() {
		return passwordValue;
	}
}
