package fr.lip6.move.test.convert.tools;

import java.util.ArrayList;

import fr.lip6.move.test.convertFramework.ConvertFramework;

public class Tools {
	
	public ArrayList<Double> convertToAll(double prix){
		ArrayList<Double> res = new ArrayList<Double>();
		ConvertFramework cf = new ConvertFramework();
		res.add(cf.convertToDirham(prix));
		res.add(cf.convertToDollar(prix));
		res.add(cf.convertToFranc(prix));
		res.add(cf.convertToLivre(prix));
		res.add(cf.convertToYen(prix));
		return res;
	}
	
	public double convertTo(double prix, int monnaie){
		System.out.println("Prix   :"+prix);
		System.out.println("Monnaie:"+monnaie);
		ArrayList<Double> res = convertToAll(prix);
		
		for (Double p: res){
			System.out.print("\t"+p);
		}
		System.out.println("");
		System.out.println("Convert:"+res.get(monnaie));
		
		return res.get(monnaie);
	}

}
