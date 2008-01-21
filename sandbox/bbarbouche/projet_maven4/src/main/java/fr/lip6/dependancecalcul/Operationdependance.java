package fr.lip6.dependancecalcul;
import fr.lip6.calcul.*;

public class Operationdependance {

	public Operation op=new Operation();
	
	public int carre(int a){
		return op.multiplication(a,a);
	}

	public int factorielle(int a){
		int res=a;
		for(int i=a-1;i>=1;i--)
		{
			res=op.multiplication(res, i);
		}
		return res;
		
	}
	
}
