package fr.lip6.move.coloane.projects.its.ctl;

public class CTLNot extends CTLUnaryOp {

	public CTLNot(CTLFormula operand) {
		super(operand);
	}

	public String getOperator() {
		return CTLFormula.NOT;
	}

}
