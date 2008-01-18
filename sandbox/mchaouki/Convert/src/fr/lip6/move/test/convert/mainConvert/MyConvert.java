package fr.lip6.move.test.convert.mainConvert;

import fr.lip6.move.test.convert.tools.Tools;

public class MyConvert {
	public static void main(String[] args){
		Tools tools = new Tools();
		
		double prixConvertie = tools.convertTo(Double.parseDouble(args[0]), Integer.parseInt(args[1]));
		
		System.out.println("Voicie le Prix convertie :"+prixConvertie);
	}
}
