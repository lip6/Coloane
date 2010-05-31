package fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA.parser.TinaNDRLexer;
import fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA.parser.TinaNDRParser;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.IOException;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IProgressMonitor;


public class ImportFromImplNDR implements IImportFrom {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$


	/**
	 * Import a PROD format model
	 * @param filePath nom de fchier a importer
	 * @return le model adapte correspondant
	 * @throws ColoaneException si le fichier n'est pas valide
	 */
	public final IGraph importFrom(String filePath, String formalism, IProgressMonitor monitor) throws ColoaneException {
		LOGGER.finer("Creation du fichier..."); ////$NON-NLS-1$

		TinaNDRLexer lexer;
		try {
			lexer = new TinaNDRLexer (new ANTLRFileStream(filePath));
		} catch (IOException e) {
			throw new ColoaneException("Problem opening file "+ e.getMessage());
		}

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		TinaNDRParser parser = new TinaNDRParser(tokens);
		IGraph graph;
		try {
			graph = parser.tinaGraphicalModel();
		} catch (RecognitionException e) {
			throw new ColoaneException("Error parsing Tina graphical (ndr) file " + e.getMessage());
		}
		return graph;

	}

}