import junit.framework.TestCase;


public class addition_test extends TestCase {

	
	//addition test=new addition(this.a,this.b);
	addition test=new addition(5,3);
	
		public void test_additionner(){
			int resultat=8;
			assertEquals(resultat,test.additionner(5, 3));
		}
}
