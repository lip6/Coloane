package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.pnml.framework.general.PnmlImport;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;
import fr.lip6.move.pnml.framework.utils.ModelRepository;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;

public class ImportFromImpl implements IImportFrom {

	/**
	 * Constructeur
	 */
	public ImportFromImpl() {
	}

	/** {@inheritDoc} */
	public final IGraph importFrom(String filePath, String formalism, IProgressMonitor monitor) throws ColoaneException {
		IGraph graph = null;
		try {

			ModelRepository.getInstance().createDocumentWorkspace(filePath);

		} catch (final InvalidIDException e1) {

			throw new ColoaneException(e1.getMessage());
		}

		PnmlImport pim = new PnmlImport();
		pim.setFallUse(true);
		HLAPIRootClass imported = null;

		try {
			imported = (HLAPIRootClass) pim.importFile(filePath);
			final Processor proc = MainProcessor.getProcessor(imported);
			graph = proc.process(imported, formalism);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ColoaneException(e.getMessage());
		}

		try {
			ModelRepository.getInstance().destroyCurrentWorkspace();
		} catch (final VoidRepositoryException e) {
			e.printStackTrace();
		}

		return graph;
	}
}
