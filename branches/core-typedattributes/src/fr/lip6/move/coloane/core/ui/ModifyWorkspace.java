package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

/**
 * Cette classe agit sur le contenu du workspace.
 * Elle sauvegarde le modele recu dans un nouveau fichier du workspace et l'affiche
 * @see WorkspaceModifyOperation
 */
public class ModifyWorkspace extends WorkspaceModifyOperation {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private IFile file;
	private IGraph graph;
	private IWorkbenchWindow window;

	/**
	 * Constructeur
	 * <b>Attention !</b> Le fichier specifie sera ecrase s'il existe deja.
	 * @param w Pour permettre l'affichage final du modele recu
	 * @param f Le fichier precedemment decide (aucune verification d'existence n'est faite ici)
	 * @param graph Le modele a sauvegarder (modele generique)
	 */
	public ModifyWorkspace(IWorkbenchWindow w, IFile f, IGraph graph) {
		this.file = f;
		this.graph = graph;
		this.window = w;
	}

	/** {@inheritDoc} */
	@Override
	protected final void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		// Traduction du modele au format xml
		String xmlString = ModelWriter.translateToXML(graph);

		// Creation de l'input stream a partir d'une chaine de caractere
		InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());

		// Ecriture du fichier de sauvegarder a partir du l'input stream
		try {
			if (!file.exists()) {
				file.create(inputS, true, monitor);
			} else {
				file.setContents(inputS, true, false, monitor);
			}
		} catch (CoreException e) {
			LOGGER.warning("Erreur lors de l'écriture du fichier : " + e); //$NON-NLS-1$
		}

		// Affichage du nouveau modele dans un nouvel onglet
		IDE.openEditor(this.window.getActivePage(), file, true);
	}
}
