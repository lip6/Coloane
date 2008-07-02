package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.interfaces.model.interfaces.IGraph;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

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

	private IFile file;
	private IGraph graph;
	private IWorkbenchWindow window;

	/**
	 * Constructeur
	 * <b>Attention !</b> Le fichier specifie sera ecrase s'il existe deja.
	 * @param window Pour permettre l'affichage final du modele recu
	 * @param file Le fichier precedemment decide (aucune verification d'existence n'est faite ici)
	 * @param model Le modele a sauvegarder (modele generique)
	 */
	public ModifyWorkspace(IWorkbenchWindow w, IFile f, IGraph graph) {
		this.file = f;
		this.graph = graph;
		this.window = w;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
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
			Coloane.getLogger().warning(ColoaneMessages.ModifyWorkspace_0);
		}

		// Affichage du nouveau modele dans un nouvel onglet
		IDE.openEditor(this.window.getActivePage(), file, true);
	}
}
