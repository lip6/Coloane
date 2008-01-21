package fr.lip6.dependancecalcul;
import junit.framework.TestCase;

public class OperationdependanceTest extends TestCase{

	public int a=5;
	public Operationdependance op=new Operationdependance();
	
	
	
	
	public void testfactorielle(){
		int resultat=5*4*3*2;
		assertEquals(resultat,op.factorielle(a));
	}
	
	
	
	
	public void testcarre(){
		int resultat=a*a;
		assertEquals(resultat,op.carre(a));
	}
	
	
}
