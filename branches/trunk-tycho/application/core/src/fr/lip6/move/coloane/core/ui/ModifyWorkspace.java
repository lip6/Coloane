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
 * This class allows to modify the workspace.<br>
 * It backups an new incoming model file and displays it.
 * 
 * @author Jean-Baptiste Voron
 * 
 * @see WorkspaceModifyOperation
 */
public class ModifyWorkspace extends WorkspaceModifyOperation {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** The graph that has to be displayed */
	private IGraph graph;

	/** The workbench window */
	private IWorkbenchWindow window;
	
	/** The file to write to the workspace */
	private IFile file;

	/**
	 * Constructor
	 * <b>Warning !</b> If a file has already the same name, it will be overwrote
	 * @param workbench The workbench window (to open the result model in a new editor)
	 * @param file A file ready to receive the model
	 * @param graph A model
	 */
	public ModifyWorkspace(IWorkbenchWindow workbench, IFile file, IGraph graph) {
		this.file = file;
		this.graph = graph;
		this.window = workbench;
	}

	/** {@inheritDoc} */
	@Override
	protected final void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		try {

			// XML translation
			String xmlString = ModelWriter.translateToXML(graph);
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());
			if (!this.file.exists()) {
				// Creating the file
				this.file.create(inputS, true, monitor);
			} else {
				// Replacing the file
				this.file.setContents(inputS, true, false, monitor);
			}

			// Displaying the new model a an editor
			IDE.openEditor(this.window.getActivePage(), file, true);
		} catch (CoreException e) {
			LOGGER.warning("Error while writing the file to the disk : " + e); //$NON-NLS-1$
		}
	}
}
