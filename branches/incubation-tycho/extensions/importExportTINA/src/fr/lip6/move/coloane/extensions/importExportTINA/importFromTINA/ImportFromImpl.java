package fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA;

import java.io.IOException;
import java.util.logging.Logger;

import main.antlr3.fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA.parser.TinaLexer;
import main.antlr3.fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA.parser.TinaParser;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;


public class ImportFromImpl implements IImportFrom {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$


	/**
	 * Import a PROD format model
	 * @param filePath nom de fchier a importer
	 * @return le model adapte correspondant
	 * @throws ExtensionException si le fichier n'est pas valide
	 */
	public final IGraph importFrom(String filePath, IFormalism formalism, IProgressMonitor monitor) throws ExtensionException {
		LOGGER.finer("Creation du fichier..."); ////$NON-NLS-1$

		TinaLexer lexer;
		try {
			lexer = new TinaLexer (new ANTLRFileStream(filePath));
		} catch (IOException e) {
			throw new ExtensionException("Problem opening file "+ e.getMessage());
		}

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		TinaParser parser = new TinaParser(tokens);
		parser.setFormalism(formalism);
		IGraph graph;
		try {
			graph = parser.tinaModel();
		} catch (RecognitionException e) {
			throw new ExtensionException("Error parsing prod file " + e.getMessage());
		}
		return graph;

	}

}