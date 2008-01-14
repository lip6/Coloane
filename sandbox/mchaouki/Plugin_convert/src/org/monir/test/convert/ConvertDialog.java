package org.monir.test.convert;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ConvertDialog extends Dialog{
	Object result;

	public ConvertDialog(Shell parent) {
		super(parent, SWT.APPLICATION_MODAL);
		// TODO Auto-generated constructor stub
	}
	
	public Object open(){
		

		
		
		// Récupere la fenetre parent
		Shell parent = this.getParent();
		Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		
		// On donne un titre à la fenetre
		dialog.setText("Convertor");
		
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
		
		
		// Création d'un bouton: Convert
		Button convertBtn = new Button(dialog,SWT.NONE);
		convertBtn.setText("Convertire");
		
		// Affichage
		dialog.pack();
		dialog.open();
		
		Display display = parent.getDisplay();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		return result;
	}

}
