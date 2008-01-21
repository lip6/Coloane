package fr.lip6.move.test.PNMLConvert.main;

import fr.lip6.move.test.PNMLConvert.convert.ConvertToPNML;

public class ConvertMainToPNML {
	public static void main(String[] args){
		
	ConvertToPNML convector = new ConvertToPNML();
	for (int i=0; i< args.length;i++){
		System.out.println("arguments "+i+": "+args[i]);
	}
	convector.convertToPNML(args);
	
	}
}
