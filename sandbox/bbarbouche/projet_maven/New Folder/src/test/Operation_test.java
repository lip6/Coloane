package test;
import fr.lip6.calcul.*;
import junit.framework.TestCase;


public class Operation_test extends TestCase {

	public int a=5;
	public int b=3;
	
public Operation op=new Operation();
	
	public void testAddition()
	{
		int resultat=a+b;
		 assertEquals(resultat,op.addition(a, b));
	}
	
	
	public void testSoustraction()
	{
		int resultat=a-b;
		 assertEquals(resultat,op.soustraction(a, b));
	}
	
	public void testMultiplication()
	{
		int resultat=a*b;
		 assertEquals(resultat,op.multiplication(a, b));
	}
	
	public void testdivision()
	{
		int resultat=a/b;
		 assertEquals(resultat,op.division(a, b));
	}
}
