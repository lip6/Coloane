package fr.lip6.move.test.PNMLConvert.convert;

import fr.lip6.move.pnml.cpnami.cami.CamiException;
import fr.lip6.move.pnml.cpnami.cami.CamiPackage;
import fr.lip6.move.pnml.cpnami.cami.Runner;

public class ConvertToPNML {

	public void convertToPNML(String[] args){
		Runner myRunner = CamiPackage.eINSTANCE.getCamiFactory().createRunner();
		try {
			myRunner.run(args);
		} catch (CamiException e) {
			System.err.println("Main: Problem when launching Runner: "
					+ e.getMessage());
			System.exit(-1);
		}
	}
}
