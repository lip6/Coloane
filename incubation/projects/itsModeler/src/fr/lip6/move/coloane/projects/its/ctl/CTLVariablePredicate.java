package fr.lip6.move.coloane.projects.its.ctl;

public class CTLVariablePredicate implements CTLFormula {

	private String variable;
	private String comparator;
	private String value;

	public String getOperator() {
		return CTLFormula.PREDICATE;
	}

	public CTLVariablePredicate(String var, String comp, String value) {
		this.variable = var;
		this.comparator = comp;
		this.value = value;
	}
	
	public String toString() {
		return variable + " " + comparator + " " + value;
	}
}
