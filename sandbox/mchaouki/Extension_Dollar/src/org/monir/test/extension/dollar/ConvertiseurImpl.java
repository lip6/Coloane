package org.monir.test.extension.dollar;

import org.monir.test.convert.Convertiseur;

public class ConvertiseurImpl implements Convertiseur {
	private double taux = 1.50;

	public ConvertiseurImpl() {
		// TODO Auto-generated constructor stub
	}

	public double convert(double prix) {
		// TODO Auto-generated method stub
		return taux*prix;
	}

	public double getTaux() {
		// TODO Auto-generated method stub
		return taux;
	}

	public void setTaux(double taux) {
		// TODO Auto-generated method stub
		this.taux = taux;
	}

}
