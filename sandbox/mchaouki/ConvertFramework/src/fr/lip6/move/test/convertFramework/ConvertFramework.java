package fr.lip6.move.test.convertFramework;

import fr.lip6.move.test.utils1.Utils1;
import fr.lip6.move.test.utils2.Utils2;

public class ConvertFramework {
	public double convertToDollar(double prix){
		System.out.println("ToDollar");
		Utils2 utils2 = new Utils2();
		System.out.println("EndTo");
		return utils2.multiplication(prix, 1.5);
	}
	
	public double convertToFranc(double prix){
		System.out.println("ToFranc");
		Utils2 utils2 = new Utils2();
		System.out.println("EndTo");
		return utils2.multiplication(prix, 6.55);
	}
	
	public double convertToLivre(double prix){
		System.out.println("ToLivre");
		Utils2 utils2 = new Utils2();
		System.out.println("EndTo");
		return utils2.multiplication(prix, 1.33);
	}
	
	public double convertToDirham(double prix){
		System.out.println("ToDirham");
		Utils2 utils2 = new Utils2();
		System.out.println("EndTo");
		return utils2.multiplication(prix, 10);
	}
	
	public double convertToYen(double prix){
		System.out.println("ToYen");
		Utils2 utils2 = new Utils2();
		System.out.println("EndTo");
		return utils2.multiplication(prix, 100);	
	}
	
	public double taxer(double prix){
		Utils1 utils1 = new Utils1();
		return utils1.addition(prix, 50);
	}
	
	public double detaxer(double prix){
		Utils1 utils1 = new Utils1();
		return utils1.soustraction(prix, 50);	
	}

}
