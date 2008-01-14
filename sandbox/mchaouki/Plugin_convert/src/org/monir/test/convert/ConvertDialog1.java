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

public class ConvertDialog1 extends Dialog {
  
  public ConvertDialog1(Shell parentShell) {
    super(parentShell);
  }

  protected Control createDialogArea(Composite parent) {
    Composite dialog = (Composite) super.createDialogArea(parent);

    
	
	// On définie un Layout
	GridLayout myGL = new GridLayout(2, false);
	dialog.setLayout(myGL);
	//dialog.setLayout(new RowLayout(SWT.VERTICAL));
	
	// Création d'un label : Prix
	Label prixLbl = new Label(dialog, SWT.CENTER);
	prixLbl.setText("Prix");
	
	// Creation d'une zone de saisie pour le prix:
	Text prixTxt = new Text(dialog,SWT.BORDER);
	prixTxt.setText("");
	
	// Création d'un label : monnaie
	Label monnaieLbl = new Label(dialog, SWT.CENTER);
	monnaieLbl.setText("monnaie");
	
	// Création d'une liste de choix de monnaie
	Combo monnaieCombo = new Combo(dialog, SWT.DROP_DOWN|SWT.READ_ONLY);
	monnaieCombo.setItems(new String[]{"Euro","Dollar","Yen"});
	

    return dialog;
  }

  protected void createButtonsForButtonBar(Composite parent) {
    super.createButtonsForButtonBar(parent);
  }

  protected void buttonPressed(int buttonId) {
      super.buttonPressed(buttonId);
  }
  
  protected final void configureShell(Shell newShell) {
	super.configureShell(newShell);
	newShell.setText("Convertiseur");
	}
}