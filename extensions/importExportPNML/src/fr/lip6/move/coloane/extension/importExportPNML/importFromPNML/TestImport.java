package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class TestImport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImportFromImpl ifp = new ImportFromImpl();

		try {
			IGraph pnmlGraph = ifp.importFrom(
					"/Users/lom/Documents/PNML/Repository/ISOIEC-15909/Dev/Sources/pnml2dot_2/trunk/Main/XMLTestFilesRepository/philo.pnml",
					"Place/Transition Net", null);
			System.out.println(pnmlGraph.getAttribute("name"));
		} catch (ColoaneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
