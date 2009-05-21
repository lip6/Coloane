package fr.lip6.move.coloane.core.ui.wizards.exportmodel;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

/**
 * Page composant l'assistant d'export de fichiers.<br>
 * Cette page comporte :
 * <ul>
 * 	<li>Une zone pour la selection des fichiers</li>
 * 	<li>Une zone pour la destination finale</li>
 * 	<li>Une zone d'options (non utilisée pour le moment) </li>
 * </ul>
 */
public class ExportWizardPage extends WizardExportResourcesPage {
	private Button destinationBrowseButton;
	private Text destinationNameField;

	/**
	 * Constructeur de la page de l'assistant
	 * @param pageName Le nom de la page de l'assistant
	 * @param selection La sélection courante
	 */
	protected ExportWizardPage(String pageName, IStructuredSelection selection) {
		super("FileSystemExportPage", selection); //$NON-NLS-1$
		setTitle(Messages.ExportWizardPage_1);
		setDescription(Messages.ExportWizardPage_2);
	}

	/** {@inheritDoc} */
	@Override
	protected final void createDestinationGroup(Composite parent) {
		Font font = parent.getFont();
		// destination specification group
		Composite destinationSelectionGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		destinationSelectionGroup.setLayout(layout);
		destinationSelectionGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
		destinationSelectionGroup.setFont(font);

		Label destinationLabel = new Label(destinationSelectionGroup, SWT.NONE);
		destinationLabel.setText(Messages.ExportWizardPage_3);
		destinationLabel.setFont(font);

		// destination name entry field
		destinationNameField = new Text(destinationSelectionGroup, SWT.SINGLE | SWT.BORDER);
		destinationNameField.addListener(SWT.Modify, this);
		destinationNameField.addListener(SWT.Selection, this);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		destinationNameField.setLayoutData(data);
		destinationNameField.setFont(font);

		// destination browse button
		destinationBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
		destinationBrowseButton.setText(Messages.ExportWizardPage_4);
		destinationBrowseButton.addListener(SWT.Selection, this);
		setButtonLayoutData(destinationBrowseButton);

	}

	/** {@inheritDoc} */
	public final void handleEvent(Event e) {
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
	protected final void handleDestinationBrowseButtonPressed() {
		DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE);
		dialog.setMessage(Messages.ExportWizardPage_6);
		dialog.setText(Messages.ExportWizardPage_7);
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
	protected final void giveFocusToDestination() {
		destinationNameField.setFocus();
	}

	/**
	 * Retourne les ressources selectionnees dans la fenetre
	 * @return Une liste de ressources
	 */
	@SuppressWarnings("unchecked")
	public final List<IResource> getSelectedRessource() {
		List<IResource> selectedResources = getWhiteCheckedResources();
		return selectedResources;
	}

	/**
	 * Retourne la destination choisie pour l'export
	 * @return La chaine de caracteres correspondant au chemin choisi
	 */
	protected final String getSelectedDirectory() {
		return destinationNameField.getText().trim();
	}

	/**
	 * Positionne la valeur du champ "directory" a la valeur choisie dans la fenetre BROWSE
	 * @param value La valeur de la destination
	 */
	protected final void setDestinationValue(String value) {
		destinationNameField.setText(value);
	}

	/**
	 * Verification que la destination existe ou potentiellement creeable
	 * @param directory La destination a veirifer
	 * @return true ou false
	 */
	protected final boolean ensureDirectoryExists(File directory) {

		// Existence du repertoire
		if (!directory.exists()) {
			if (!queryYesNoQuestion(Messages.ExportWizardPage_9)) {
				return false;
			}

			// Est-il possible de la creer ?
			if (!directory.mkdirs()) {
				displayErrorDialog(Messages.ExportWizardPage_10);
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
	public final boolean ensureTargetIsValid(File targetDirectory) {
		if (targetDirectory.exists() && !targetDirectory.isDirectory()) {
			displayErrorDialog(Messages.ExportWizardPage_11);
			giveFocusToDestination();
			return false;
		}
		return ensureDirectoryExists(targetDirectory);
	}

}
