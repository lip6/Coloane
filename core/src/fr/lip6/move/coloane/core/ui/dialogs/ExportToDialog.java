package fr.lip6.move.coloane.core.ui.dialogs;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.ui.IFileEditorInput;

import fr.lip6.move.coloane.core.extensions.ExportToExtension;
import fr.lip6.move.coloane.core.ui.Editor;

public class ExportToDialog extends Dialog {
	/**
	 * Attributs de la boite de dialogue 'Export To...'
	 */
	private String format;
	private String filePath;
	//private String fileName;
	private Editor editor;
	
	/**
	 * Composants servant a recuperer le format dans lequel exporter
	 */
	private Label formatLbl;
	private Combo formatCombo;
	private Label rienLbl; // Ne sert a rien; juste pour combler un "trou"
	
	/**
	 * Composants servant a recuperer le chemin du fichier a creer
	 */
	private Label filePathLbl;
	private Text filePathTxt;
	private Button filePathBtn;
	
	

	public ExportToDialog(Shell parentShell,Editor editor) {
		super(parentShell);
		this.editor = editor;
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
		formatLbl.setText(Messages.ExportToDialog_0);
		
		formatCombo = new Combo(dialog, SWT.DROP_DOWN|SWT.READ_ONLY);
		formatCombo.setItems(ExportToExtension.getAllNameExtensionConvert());
		
		rienLbl = new Label(dialog,SWT.NONE);
		rienLbl.setText(""); //$NON-NLS-1$
		
		/**
		 * Creation de la partie permettant de declarer le chemin du
		 * fichie a creer dans le format choisie precedement
		 */	
		filePathLbl = new Label(dialog,SWT.CENTER);
		filePathLbl.setText(Messages.ExportToDialog_2);
		
		filePathTxt = new Text(dialog,SWT.BORDER);
		filePathTxt.setText(""); //$NON-NLS-1$
		filePathTxt.setEditable(false);
		
		filePathBtn = new Button(dialog,SWT.NONE);
		filePathBtn.setText(Messages.ExportToDialog_4);
		
		filePathBtn.addListener(SWT.Selection, 
				new Listener(){
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				
				// Recupere le fichier de l'editeur courrant
				IFile file = ((IFileEditorInput) editor.getEditorInput()).getFile();
				
				// Cree une FileDialog
				FileDialog fileDialog = new FileDialog(dialog.getShell(),SWT.SAVE);
				
				// Definie un nom par defaut : celui de l'editeur courant
				int fLength = file.getName().length();
				String ext = file.getFileExtension();
				fileDialog.setFileName(file.getName().substring(0, fLength - ext.length() - 1));
				
				// Ouvre la FileDialog
				filePath = fileDialog.open();
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
	 * Creer les bouttons 'Ok' et 'Cancel' ??? 
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
	}

	/**
	 * Actions a effectuer si on presse un boutton ???
	 */
	protected void buttonPressed(int buttonId) {
		// Si le filePath n'est pas précisé, alors ne rien faire.
		if ( buttonId == Dialog.OK) {
			if ( filePath == null ){
				return ;
			}
			if ( filePath.equalsIgnoreCase("")){
				return ;
			}
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * Actions a effectuer si on presse le boutton 'Ok' ???
	 */
	protected final void okPressed() {
		this.format = formatCombo.getItems()[formatCombo.getSelectionIndex()];
		this.filePath = filePathTxt.getText();
		super.okPressed();
	}
	
	/**
	 * Configure le titre de la boite de dialogue
	 */
	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.ExportToDialog_5);
	}


}
