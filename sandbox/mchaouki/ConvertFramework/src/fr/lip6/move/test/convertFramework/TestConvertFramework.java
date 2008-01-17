package fr.lip6.move.test.convertFramework;

public class TestConvertFramework {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConvertFramework cf = new ConvertFramework();
		
		double prixConvertie = 0;
		
		if (Integer.parseInt(args[1]) == 0){
			prixConvertie = cf.convertToDirham(Double.parseDouble(args[0]));
		}
		if (Integer.parseInt(args[1]) == 1){
			prixConvertie = cf.convertToDollar(Double.parseDouble(args[0]));
		}
		if (Integer.parseInt(args[1]) == 2){
			prixConvertie = cf.convertToFranc(Double.parseDouble(args[0]));
		}
		if (Integer.parseInt(args[1]) == 3){
			prixConvertie = cf.convertToLivre(Double.parseDouble(args[0]));
		}
		if (Integer.parseInt(args[1]) == 4){
			prixConvertie = cf.convertToYen(Double.parseDouble(args[0]));
		}
		
		System.out.println("Voicie le Prix convertie: "+prixConvertie);

	}

}
