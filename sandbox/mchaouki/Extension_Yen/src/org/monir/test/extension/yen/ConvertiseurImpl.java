package org.monir.test.extension.yen;

import org.monir.test.convert.Convertiseur;

import fr.lip6.move.test.convert.tools.Tools;

public class ConvertiseurImpl implements Convertiseur {
	private double taux = 100;
	public ConvertiseurImpl() {
		// TODO Auto-generated constructor stub
	}

	public double convert(double prix) {
		// TODO Auto-generated method stub
		Tools tools = new Tools();
		
		double prixConvertie = tools.convertTo(prix, 4);
		
		System.out.println("Voicie le Prix convertie"+prixConvertie);
		return prixConvertie;
	}

	public double getTaux() {
		// TODO Auto-generated method stub
		return taux;
	}

	public void setTaux(double taux) {
		// TODO Auto-generated method stub
		
	}

}
