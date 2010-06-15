package fr.lip6.move.coloane.projects.its.ctl;

public interface  CTLFormula {

	public static String CONSTANT = "Constant";
	public static String PREDICATE = "Predicate";
	public static String REFERENCE = "Reference";
	public static String AF = "AF";
	public static String AG = "AG";
	public static String AU = "AU";
	public static String AX = "AX";
	public static String EF = "EF";
	public static String EG = "EG";
	public static String EU = "EU";
	public static String EX = "EX";
	public static String NOT = "!";
	public static String AND = "*";
	public static String OR = "+";
	public static String XOR = "^";
	public static String EQUIV = "<->";
	public static String IMPLY = "->";
	
	
	String getOperator();
	
	// Singleton constant instances
	public static CTLFormula TRUE = new CTLConstant(true);
	public static CTLFormula FALSE = new CTLConstant(true);
}
