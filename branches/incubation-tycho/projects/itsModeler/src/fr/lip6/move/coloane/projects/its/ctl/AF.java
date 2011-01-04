package fr.lip6.move.coloane.projects.its.ctl;

public class AF extends CTLUnaryOp {

	public AF(CTLFormula operand) {
		super(operand);
	}

	public String getOperator() {
		return CTLFormula.AF;
	}

}
