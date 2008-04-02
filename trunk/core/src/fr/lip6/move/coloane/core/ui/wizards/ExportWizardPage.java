package fr.lip6.move.coloane.core.ui.wizards;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

public class ExportWizardPage extends WizardExportResourcesPage {
	private Button destinationBrowseButton;
	protected Button headerCheckbox;
	private Text destinationNameField;

	/**
	 * Constructeur de la page de l'assistant
	 * @param pageName
	 * @param selection
	 */
	protected ExportWizardPage(String pageName, IStructuredSelection selection) {
		super("FileSystemExportPage", null);
		setTitle("Export...");
		setDescription("Export file to a secrete destination");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardExportResourcesPage#createDestinationGroup(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createDestinationGroup(Composite parent) {
		Font font = parent.getFont();
		// destination specification group
		Composite destinationSelectionGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		destinationSelectionGroup.setLayout(layout);
		destinationSelectionGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
		destinationSelectionGroup.setFont(font);

		Label destinationLabel = new Label(destinationSelectionGroup, SWT.NONE);
		destinationLabel.setText("To directory :");
		destinationLabel.setFont(font);

		// destination name entry field
		destinationNameField = new Text(destinationSelectionGroup,SWT.SINGLE | SWT.BORDER);
		destinationNameField.addListener(SWT.Modify, this );
		destinationNameField.addListener(SWT.Selection, this );
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		destinationNameField.setLayoutData(data);
		destinationNameField.setFont(font);

		// destination browse button
		destinationBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
		destinationBrowseButton.setText("Browse");
		destinationBrowseButton.addListener(SWT.Selection, this );
		setButtonLayoutData(destinationBrowseButton);

		new Label(parent, SWT.NONE); // vertical spacer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardDataTransferPage#createOptionsGroupButtons(org.eclipse.swt.widgets.Group)
	 */
	protected void createOptionsGroupButtons(Group optionsGroup) {
		Font font = optionsGroup.getFont();
		createHeader(optionsGroup, font);
	}

	/**
	 * Creation d'une checkbox dans la zone d'option pour ajouter un header aux fichier exportes
	 * @param optionsGroup Le groupe d'elements graphiques nomme "options"
	 * @param font La police utilisee dans ce group d'elements
	 */
	protected void createHeader(Group optionsGroup, Font font) {
		headerCheckbox = new Button(optionsGroup, SWT.CHECK | SWT.LEFT);
		headerCheckbox.setText("Add header to exported files");
		headerCheckbox.setFont(font);

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event e) {
		Widget source = e.widget;

		// Detecte le clic sur le bouton BROWSE
		if (source == destinationBrowseButton) {
			handleDestinationBrowseButtonPressed();
		}
		
		updatePageCompletion();
	}

	/**
	 * Dessine la fenetre de selection de la destination d'export
	 */
	protected void handleDestinationBrowseButtonPressed() {
		DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE);
		dialog.setMessage("Message1");
		dialog.setText("Message2");
		dialog.setFilterPath(getSelectedDirectory());
		String selectedDirectoryName = dialog.open();

		if (selectedDirectoryName != null) {
			setErrorMessage(null);
			setDestinationValue(selectedDirectoryName);
		}
	}

	/**
	 * Donne le focus sur le champs "directory"
	 */
	protected void giveFocusToDestination() {
		destinationNameField.setFocus();
	}

	/**
	 * Retourne les ressources selectionnees dans la fenetre
	 * @return Une liste de ressources
	 */
	@SuppressWarnings("unchecked")
	public List<IResource> getSelectedRessource() {
		List<IResource> selectedResources = getWhiteCheckedResources();
		return selectedResources;
	}

	/**
	 * Retourne la destination choisie pour l'export
	 * @return La chaine de caracteres correspondant au chemin choisi
	 */
	protected String getSelectedDirectory() {
		return destinationNameField.getText().trim();
	}

	/**
	 * Positionne la valeur du champ "directory" a la valeur choisie dans la fenetre BROWSE
	 * @param value La valeur de la destination
	 */
	protected void setDestinationValue(String value) {
		destinationNameField.setText(value);
		notify();
	}
	
	

	/**
	 * Verification que la destination existe ou potentiellement creeable
	 * @param directory La destination a veirifer
	 * @return true ou false
	 */
	protected boolean ensureDirectoryExists(File directory) {

		// Existence du repertoire 
		if (!directory.exists()) {
			if (!queryYesNoQuestion("This directory does not exists. Would you like to create it ?")) {
				return false;
			}

			// Est-il possible de la creer ?
			if (!directory.mkdirs()) {
				displayErrorDialog("The directory creation has failed. Please choose another one");
				giveFocusToDestination();
				return false;
			}
		}
		return true;
	}


	/**
	 * Verification que la destination est un repertoire et existe
	 * @param targetDirectory La destination finale
	 * @return un booleen
	 */
	public boolean ensureTargetIsValid(File targetDirectory) {
		if (targetDirectory.exists() && !targetDirectory.isDirectory()) {
			displayErrorDialog("Message 444");
			giveFocusToDestination();
			return false;
		}

		return ensureDirectoryExists(targetDirectory);
	}

}
