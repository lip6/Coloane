package fr.lip6.move.coloane.extensions.importfromgml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.core.model.factory.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class ImportFromGML implements IImportFrom {

	public ImportFromGML() {
		// TODO Auto-generated constructor stub
	}

	public IGraph importFrom(String filePath, IFormalism formalism,
			IProgressMonitor monitor) throws ExtensionException {
		IGraph graph = new GraphModelFactory().createGraph(formalism);
		File f = new File(filePath);
		BufferedReader reader;
		GMLParser parser = new GMLParser(graph);
		
		try {
			reader = new BufferedReader( new FileReader(f) );
			parser.parseXML(reader);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return graph;
	}

}
