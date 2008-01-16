package fr.lip6.move.convertiseur.main;

import fr.lip6.move.convertiseur.convert.Convertiseur;

public class Convert {
	public static void main(String[] args){
		Convertiseur myConvert = new Convertiseur();
		
		System.out.println(myConvert.convert(Double.parseDouble(args[0]),Double.parseDouble(args[1] )));
		
	}
}
