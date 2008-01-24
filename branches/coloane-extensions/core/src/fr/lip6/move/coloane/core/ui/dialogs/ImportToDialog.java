package fr.lip6.move.coloane.core.ui.dialogs;

import java.io.File;

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

import fr.lip6.move.coloane.core.extensions.ImportToExtension;

public class ImportToDialog extends Dialog{
	
	/**
	 * Attributs de la boite de dialogue 'Import To...'
	 */
	private String format;
	private String filePath;
	private String fileName;
	
	/**
	 * Composants servant a recuperer le format dans lequel exporter
	 */
	private Label formatLbl;
	private Combo formatCombo;
	private Label rienLbl;// ne sert a rien; juste pour combler un "trou"
	
	/**
	 * Composants servant a recuperer le chemin du fichier a creer
	 */
	private Label filePathLbl;
	private Text filePathTxt;
	private Button filePathBtn;
	
	
	public ImportToDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Methode permettant la creation et le placement des composants de
	 * la boite de dialog
	 */
	protected Control createDialogArea(Composite parent) {
		final Composite dialog = (Composite) super.createDialogArea(parent);
		
		/**
		 * Definition du Layout
		 */
		GridLayout myGL = new GridLayout(3, false);
		dialog.setLayout(myGL);
		
		/**
		 * Creation de la partie permettant le choix du format
		 */
		formatLbl = new Label(dialog,SWT.CENTER);
		formatLbl.setText("Format");
		
		formatCombo = new Combo(dialog, SWT.DROP_DOWN|SWT.READ_ONLY);
		formatCombo.setItems(ImportToExtension.getAllNameExtensionConvert());
		
		rienLbl = new Label(dialog,SWT.NONE);
		rienLbl.setText("");
		
		/**
		 * Creation de la partie permettant de declarer le chemin du
		 * fichie a creer dans le format choisie precedement
		 */	
		filePathLbl = new Label(dialog,SWT.CENTER);
		filePathLbl.setText("Nom");
		
		filePathTxt = new Text(dialog,SWT.BORDER);
		filePathTxt.setText("");
		filePathTxt.setEditable(false);
		
		filePathBtn = new Button(dialog,SWT.NONE);
		filePathBtn.setText("Parcourire...");
		
		filePathBtn.addListener(SWT.Selection, 
				new Listener(){
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				FileDialog fileDialog = new FileDialog(dialog.getShell(),SWT.OPEN | SWT.SINGLE);
				filePath = fileDialog.open();
				fileName = new File(filePath).getName();
				filePathTxt.setText(filePath);
			}
	
		});
		
		return dialog;
	}
	
	/**
	 * 
	 * @return Le format dans lequel exporter le model courant
	 */
	public String getFormat(){
		return format;
	}
	
	/**
	 * 
	 * @return Le chemin du fichier dans lequel enregister le model courant
	 */
	public String getFilePath(){
		return filePath;
	}
	
	/**
	 * 
	 * @return Le nom du fichier dans lequel enregister le model courant
	 */
	public String getFileName(){
		return fileName;
	}
	
	/**
	 * Creer les bouttons 'Ok' et 'Cancel' ??? 
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
	}

	/**
	 * Actions a effecttuer si on presse un boutton ???
	 */
	protected void buttonPressed(int buttonId) {
		super.buttonPressed(buttonId);
	}

	/**
	 * Actions a effectuer si on presse le boutton 'Ok' ???
	 */
	protected final void okPressed() {
		this.format = formatCombo.getItems()[formatCombo.getSelectionIndex()];
		this.filePath = filePathTxt.getText();
		this.fileName = new File(this.filePath).getName();
		super.okPressed();
	}
	
	/**
	 * Configure le titre de la boite de dialogue
	 */
	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Import To...");
	}

}
