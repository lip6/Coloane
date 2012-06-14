package fr.lip6.move.coloane.extensions.importExportLola;


import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.IOException;
import java.util.logging.Logger;

import fr.lip6.move.coloane.extensions.importExportLola.parser.LoLALexer;
import fr.lip6.move.coloane.extensions.importExportLola.parser.LoLAParser;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * Imports a model written in LoLA format
 * 
 * @author Anas Aouad
 *
 */
public class ImportFromLola implements IImportFrom {
	
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core");

	/**
	 * Import a LoLA file into a Graph object
	 * @param filePath The location of the file to be imported
	 * @param formalism The formalism
	 * @param monitor A monitor to follow the operation progress
	 * @return The resulting model {@link IGraph}
	 * @throws ExtensionException Something went wrong
	 */
	public final IGraph importFrom(String filePath, IFormalism formalism,
			IProgressMonitor monitor) throws ExtensionException {

		LOGGER.fine("Creating the workspace file...");

		LoLALexer lexer;
		try {
			lexer = new LoLALexer(new ANTLRFileStream(filePath));
		} catch (IOException e) {
			throw new ExtensionException("Problem opening file " + e.getMessage());
		}

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		LoLAParser parser = new LoLAParser(tokens);
		parser.setFormalism(formalism);
		IGraph graph;
		try {
			graph = parser.lolaModel();
		} catch (RecognitionException e) {
			throw new ExtensionException("Error parsing prod file " + e.getMessage());
		}
		return graph;
	}

}
