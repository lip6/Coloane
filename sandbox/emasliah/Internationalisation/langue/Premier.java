package langue;

import java.util.*;

import langue.deuxieme.Deuxieme;


/** Cette classe permet de choisir la langue du logiciel */
public class Premier {

	public static Locale locale = null;
	public static ResourceBundle bundle = null;

	public Premier(String param1, String param2) {
		locale = new Locale(param1,param2);
		bundle = ResourceBundle.getBundle("LNG", locale);
	}

	public Premier() {
		locale = new Locale("","");
		bundle = ResourceBundle.getBundle("LNG", locale);
	}

	public void parler() {
		System.out.println(bundle.getString("1Test"));
		System.out.println(bundle.getString("2Test"));
	}


	public static void main (String args[]) {
		String param1; //= "fr";
		String param2; //= "FR";
		Premier test;
		if (args.length >= 2) {
			param1 = args[0];
			param2 = args[1];
			//System.out.println("param1 : " + param1);
			//System.out.println("param2 : " + param2);
			test = new Premier(param1,param2);
		} else {
			test = new Premier();
		}
		test.parler();
		Deuxieme test2;
		test2 = new Deuxieme();
		test2.parler();
	}

} // Premier

