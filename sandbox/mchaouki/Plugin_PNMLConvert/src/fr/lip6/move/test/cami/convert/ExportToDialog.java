package fr.lip6.move.test.cami.convert;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ExportToDialog extends Dialog {
	private String format;
	private String inputFile;
	private String outputFile;
	
	private Label formatLbl;
	private Combo formatCombo;
	private Label rien;  //  <----- ne sert a rien ;-)

	private Label inputFileLbl;
	private Text inputFileTxt;
	private Button inputFileBtn;
	
	private Label outputFileLbl;
	private Text outputFileTxt;
	private Button outputFileBtn;

	protected ExportToDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(Composite parent) {
		final Composite dialog = (Composite) super.createDialogArea(parent); // <-- Ajout finale automatiquement ?



		// On definie un Layout
		GridLayout myGL = new GridLayout(3, false);
		dialog.setLayout(myGL);

		// Creation d'un label : Format
		formatLbl = new Label(dialog, SWT.CENTER);
		formatLbl.setText("Format");
		// Creation d'une liste des Formats
		formatCombo = new Combo(dialog, SWT.DROP_DOWN|SWT.READ_ONLY);
		formatCombo.setItems(ExportToExtension.getAllNameExtensionConvert());
		// Creation d'une liste des Formats
		rien = new Label(dialog,SWT.NONE);
		rien.setText("");
		
		
		// Creation d'un label : FichierSource
		inputFileLbl = new Label(dialog, SWT.CENTER);
		inputFileLbl.setText("Fichier source");
		// Creation d'une zone de saisie pour le fichier d'entrer
		inputFileTxt = new Text(dialog,SWT.BORDER);
		inputFileTxt.setText("");
		// Creation d'un boutton:
		inputFileBtn = new Button(dialog,SWT.NONE);
		inputFileBtn.setText("Parcourire...");
		// Abonement du bouton
		inputFileBtn.addListener(SWT.Selection, 
				new Listener(){

					
					public void handleEvent(Event event) {
						// TODO Auto-generated method stub
						FileDialog fileDialog = new FileDialog(dialog.getShell());
						inputFile = fileDialog.open();
						inputFileTxt.setText(inputFile);
					}
			
		});
		
		
		// Creation d'un label : FichierDestination
		outputFileLbl = new Label(dialog, SWT.CENTER);
		outputFileLbl.setText("Fichier Destination");
		// Creation d'une zone de saisie pour le fichier d'entrer
		outputFileTxt = new Text(dialog,SWT.BORDER);
		outputFileTxt.setText("");
		// Creation d'un boutton:
		outputFileBtn = new Button(dialog,SWT.NONE);
		outputFileBtn.setText("Parcourire...");
		// Abonement du bouton
		outputFileBtn.addListener(SWT.Selection, 
				new Listener(){

					
					public void handleEvent(Event event) {
						// TODO Auto-generated method stub
						FileDialog fileDialog = new FileDialog(dialog.getShell());
						outputFile = fileDialog.open();
						outputFileTxt.setText(inputFile);
					}
			
		});
		
		return dialog;
	}
	
	public String getFormat(){
		return format;
	}
	
	public String getInputFile(){
		return inputFile;
	}
	
	public String getOutputFile(){
		return outputFile;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
	}

	protected void buttonPressed(int buttonId) {
		super.buttonPressed(buttonId);
	}

	protected final void okPressed() {
		this.format = formatCombo.getItems()[formatCombo.getSelectionIndex()];
		this.inputFile = inputFileTxt.getText();
		this.outputFile = outputFileTxt.getText();
		super.okPressed();
	}
	
	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Export To...");
	}
	

}