package fr.lip6.move.test.extension.cami2pnml;

import fr.lip6.move.pnml.cpnami.cami.CamiException;
import fr.lip6.move.pnml.cpnami.cami.CamiPackage;
import fr.lip6.move.pnml.cpnami.cami.Runner;
import fr.lip6.move.test.cami.interfaceConvert.Conversion;

public class ConversionImpl implements Conversion {

	public ConversionImpl() {
		// TODO Auto-generated constructor stub
	}

	public void convert(String inputFile, String outputFile) {
		// TODO Auto-generated method stub
		final Runner myRunner = CamiPackage.eINSTANCE.getCamiFactory().createRunner();
		
		try {
			System.out.println("aaaaaaaa");
			final String f1 = "-cami2p";
			final String f2 = "/home/mchaouki/Desktop/Test_PNMLConvert/Piscine";
			final String[] tab = new String[]{f1,f2};
			myRunner.run(tab);
			System.out.println("bbbbbbbbb");
		} catch (CamiException e) {
			System.err.println("Main: Problem when launching Runner: "
					+ e.getMessage());
			//System.exit(-1);
		}
	}

}
