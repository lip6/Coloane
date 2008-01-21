package fr.lip6.move.test.extension.cami2pnml;

import fr.lip6.move.pnml.cpnami.cami.CamiException;
import fr.lip6.move.pnml.cpnami.cami.CamiPackage;
import fr.lip6.move.pnml.cpnami.cami.Runner;
import fr.lip6.move.test.cami.interfaceConvert.Conversion;

public class ConversionImpl implements Conversion {

	public ConversionImpl() {
		// TODO Auto-generated constructor stub
	}

	public void convert(String inputFile, String outputFile) throws CamiException {
		// TODO Auto-generated method stub
		Runner myRunner = CamiPackage.eINSTANCE.getCamiFactory().createRunner();
		
		try {
			System.out.println("aaaaaaaa");
			String f1 = "-cami2p";
			String f2 = inputFile;
			String f3 = "-output";
			String f4 = outputFile;
			
			if (inputFile.equals("")){
				System.out.println("Pas de inputFile");
				return;
			}
			
			if (outputFile.equals("")){
				System.out.println("Pas de outputFile");
				String[] tab1 = new String[]{f1,f2};
				myRunner.run(tab1);
				System.out.println("Fin ecriture");
				return;
			}
			else{
				System.out.println("un outputFile");
				String[] tab2 = new String[]{f1,f2,f3,f4};
				myRunner.run(tab2);
				System.out.println("Fin ecriture");
				return;
			}
			
		} catch (CamiException e) {
			System.out.println("ERROR0");
			System.err.println("Main: Problem when launching Runner: "
					+ e.getMessage());
			throw new CamiException(e.getMessage());
			//System.out.println("ERROR1");
			//System.exit(-1);
		}
	}

}
