package org.monir.test.convert;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ConvertDialog extends Dialog {
	private Label prixLbl;
	private Text prixTxt;
	private Label monnaieLbl;
	private Combo monnaieCombo;
	
	private String monnaie = "";
	private double prix;
	
	
	public ConvertDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite dialog = (Composite) super.createDialogArea(parent);



		// On définie un Layout
		GridLayout myGL = new GridLayout(2, false);
		dialog.setLayout(myGL);
		//dialog.setLayout(new RowLayout(SWT.VERTICAL));

		// Création d'un label : Prix
		prixLbl = new Label(dialog, SWT.CENTER);
		prixLbl.setText("Prix");

		// Creation d'une zone de saisie pour le prix:
		prixTxt = new Text(dialog,SWT.BORDER);
		prixTxt.setText("");

		// Création d'un label : monnaie
		monnaieLbl = new Label(dialog, SWT.CENTER);
		monnaieLbl.setText("monnaie");

		// Création d'une liste de choix de monnaie
		monnaieCombo = new Combo(dialog, SWT.DROP_DOWN|SWT.READ_ONLY);
		monnaieCombo.setItems(ConvertExtension.getAllNameExtensionConvert());


		return dialog;
	}
	
	public String getMonnaie(){
		return monnaie;
	}
	
	public double getPrice(){
		return prix;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
	}

	protected void buttonPressed(int buttonId) {
		super.buttonPressed(buttonId);
	}

	protected final void okPressed() {
		this.monnaie = monnaieCombo.getItems()[monnaieCombo.getSelectionIndex()];
		this.prix = Double.parseDouble(prixTxt.getText());;
		super.okPressed();
	}

	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Convertiseur");
	}
}