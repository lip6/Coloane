package fr.lip6.move.coloane.core.ui.wizards;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard;

import fr.lip6.move.coloane.core.extensions.ExportToExtension;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.ColoaneMessages;
import fr.lip6.move.coloane.core.ui.ModelHandler;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;

/**
 * Assistant (Wizard) generique d'export de fichier modele<br/>
 * Le format d'export est defini par l'extension qui appelle l'assistant
 * 
 * @author Jean-Baptiste Voron
 */
public class ExportWizard extends FileSystemExportWizard implements IExecutableExtension {

	/** Le format d'export */
	protected String idWizard = null;

	/** La seule et unique page du wizard */
	private ExportWizardPage page;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		this.page = new ExportWizardPage("ExportPage", currentSelection);
		super.init(workbench, currentSelection);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		if ((this.idWizard != null) && (!page.getSelectedDirectory().equals("")) && (page.getSelectedRessource().size() > 0)) {
			return super.canFinish();
		} else {
			page.setErrorMessage("You must choose at least one file to export and the directory");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard#addPages()
	 */
	@Override
	public void addPages() {
		addPage(this.page);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		if (!page.ensureTargetIsValid(new File(page.getSelectedDirectory()))) {
			return false;
		}	

		FormalismManager formManager = Coloane.getDefault().getMotor().getFormalismManager();
		// Creation d'un instance du handler
		ModelHandler handler = new ModelHandler();


		// Parcours de toutes les ressources selectionnees
		for (IResource res : page.getSelectedRessource()) {
			Coloane.getLogger().finer("Fichier a exporter : "+res.getName()+" vers "+page.getSelectedDirectory());

			// Recuperation du FormalismManager existant
			Formalism formalism = formManager.getFormalismByExtension(res.getFileExtension());

			// Cast de la ressource en IFile pour recuperer le contenu
			IFile file = (IFile)res;

			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);	
				Source schemaSource = new StreamSource(this.getClass().getResourceAsStream("/resources/"+formalism.getSchema()));
				Schema schema = schemaFactory.newSchema(schemaSource);

				// Phase de validation
				Validator validator = schema.newValidator();
				validator.validate(new StreamSource(file.getContents()));

				SAXParser saxParser = factory.newSAXParser();
				saxParser.parse(file.getLocation().toString(), handler);
			} catch (Exception e) {
				Coloane.getLogger().warning("Erreur lors du chargement du fichier " + file.getName()); //$NON-NLS-1$
				Coloane.getLogger().finer("Details : " + e.getMessage() + " " + e.toString()); //$NON-NLS-1$ //$NON-NLS-2$
				Coloane.showErrorMsg(ColoaneMessages.Editor_1 + file.getName() + " - " + e.toString() + " " + e.getMessage()); //$NON-NLS-2$ //$NON-NLS-1$
			}

			try {
				// Creation du modele a partir du modele generique
				IModelImpl model = new ModelImplAdapter(handler.getModel(), formalism);

				// Manipulation du nom de fichier pour supprimer l'ancienne extension et remplacer par la nouvelle
				String newExtension = ExportToExtension.findExtension(this.idWizard);
				String newName = file.getName().substring(0, file.getName().lastIndexOf('.')+1)+newExtension;

				IExportTo exportInstance = ExportToExtension.createConvertInstance(this.idWizard);
				if (exportInstance == null) { return false;	}
				exportInstance.export(model, page.getSelectedDirectory()+"/"+newName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return true;
	}

	/**
	 * Indique le format d'export utilise dans cette instance d'assistant
	 * @param exportFormat Le format a utiliser pour l'export
	 */
	protected void setExportFormat(String idWizard) {
		Coloane.getLogger().finer("Wizard selectionne : "+idWizard);
		this.idWizard = idWizard;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Recuperation de l'identitifant de l'appelant permettant ansi de determiner le format d'export
		this.setExportFormat(config.getAttribute("id"));
	}
}
