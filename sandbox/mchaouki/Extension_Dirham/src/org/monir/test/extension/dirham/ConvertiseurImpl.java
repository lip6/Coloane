package org.monir.test.extension.dirham;

import org.monir.test.convert.Convertiseur;

public class ConvertiseurImpl implements Convertiseur {
	private double taux = 10;

	public ConvertiseurImpl() {
		// TODO Auto-generated constructor stub
	}

	public double convert(double prix) {
		// TODO Auto-generated method stub
		return prix*taux;
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
